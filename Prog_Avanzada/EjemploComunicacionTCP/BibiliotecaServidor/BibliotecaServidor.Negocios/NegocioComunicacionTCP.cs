using BibiliotecaServidor.Entidades;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace BibliotecaServidor.Negocios
{
    public class NegocioComunicacionTCP
    {
        private TcpListener tcpListener;
        private bool servidorIniciado;

        //Delegado genérico predefinido que se utiliza comúnmente para manejar eventos
        public event EventHandler<(string mensaje, StreamWriter streamWriter)> MensajeRecibido;

        public NegocioComunicacionTCP()
        {
            // Configurar la comunicación TCP
            var local = System.Net.IPAddress.Parse("127.0.0.1");
            tcpListener = new TcpListener(local, 30000);
        }

        public void Iniciar()
        {
            servidorIniciado = true;
            tcpListener.Start();

            // Iniciar un hilo para escuchar clientes
            var subprocesoEscuchaClientes = new Thread(EscucharClientes);
            subprocesoEscuchaClientes.IsBackground = true;
            subprocesoEscuchaClientes.Start();
        }

        public void Detener()
        {
            servidorIniciado = false;
            tcpListener.Stop();
        }

        private void EscucharClientes()
        {
            while (servidorIniciado)
            {
                try
                {
                    var client = tcpListener.AcceptTcpClient();
                    var clientThread = new Thread(ComunicacionCliente);
                    clientThread.Start(client);
                }
                catch (SocketException)
                {
                    // SocketException se lanza cuando se detiene el servidor, no es necesario manejarlo
                }
            }
        }

        private void ComunicacionCliente(object cliente)
        {
            var tcpClient = (TcpClient)cliente;
            var reader = new StreamReader(tcpClient.GetStream());
            var writer = new StreamWriter(tcpClient.GetStream());


            while (servidorIniciado)
            {
                try
                {
                    var mensaje = reader.ReadLine();
                    //Utilizamos invoke para disparar el evento.
                    MensajeRecibido?.Invoke(this, (mensaje, writer));
                }
                catch (IOException)
                {
                    // IOException se lanza cuando se desconecta el cliente, no es necesario manejarlo
                    break;
                }
            }

            tcpClient.Close();
        }
    }
}
