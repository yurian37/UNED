using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Net.Sockets;
using System.IO;
using BibiliotecaServidor.Entidades;
using Newtonsoft.Json;

namespace BibiliotecaCliente.Interfaz
{
    /// <summary>
    /// Clase utilitaria para administrar la conexión y solicitudes del cliente con el servidor
    /// </summary>
    public class ClienteTCP
    {
        private static IPAddress ipServidor;
        private static TcpClient cliente;
        private static IPEndPoint serverEndPoint;
        private static StreamWriter clienteStreamWriter;
        private static StreamReader clienteStreamReader;

        private static bool EnviarRespuesta(string mensaje)
        {
            try
            {
                clienteStreamWriter.WriteLine(mensaje);
                clienteStreamWriter.Flush();

                return true;
            }
            catch (Exception)
            {
                return false;
            }

        }

        /// <summary>
        /// Conecta el cliente tcp con el servidor
        /// </summary>
        /// <param name="pIdentificadorCliente">Identificador o nombre del cliente</param>
        /// <returns>Retorna true si se conecta con el servidor</returns>
        public static bool Conectar(string pIdentificadorCliente)
        {
            try
            {
                ipServidor = IPAddress.Parse("127.0.0.1");
                cliente = new TcpClient();
                serverEndPoint = new IPEndPoint(ipServidor, 30000);
                cliente.Connect(serverEndPoint);               
                MensajeSocket<string> mensajeConectar = new MensajeSocket<string> { Metodo = "Conectar", Entidad = pIdentificadorCliente};

                clienteStreamReader = new StreamReader(cliente.GetStream());
                clienteStreamWriter = new StreamWriter(cliente.GetStream());
                EnviarRespuesta(JsonConvert.SerializeObject(mensajeConectar));
            }
            catch { return false; }

            return true;
        }

        /// <summary>
        /// Solicita al servidor los libros de un autor en específico 
        /// </summary>
        /// <param name="pIdAutor">Es el id del autor</param>
        /// <returns>Retorna la lista de libros del autor</returns>
        public static List<Libro> ObtenerLibrosDeAutor(string pIdAutor)
        {
            MensajeSocket<string> mensajeObtenerLibrosDeAutor = new MensajeSocket<string> { Metodo = "ObtenerLibrosDeAutor", Entidad = pIdAutor };
            EnviarRespuesta(JsonConvert.SerializeObject(mensajeObtenerLibrosDeAutor));

            var mensaje = clienteStreamReader.ReadLine();
            var listaLibros = JsonConvert.DeserializeObject<List<Libro>>(mensaje);

            return listaLibros;
        }

        /// <summary>
        /// Desconecta el cliente del servidor
        /// </summary>
        /// <param name="pIdentificadorCliente">Identificador o nombre del cliente</param>
        public static void Desconectar(string pIdentificadorCliente)
        {
            MensajeSocket<string> mensajeDesconectar = new MensajeSocket<string> { Metodo = "Desconectar", Entidad = pIdentificadorCliente };
            EnviarRespuesta(JsonConvert.SerializeObject(mensajeDesconectar));

            //Se cierra la conexión del cliente
            cliente.Close();
        }      

        /// <summary>
        /// Envía al servidor un nuevo libro para ser agregado
        /// </summary>
        /// <param name="pNuevoLibro">Nuevo libro para agregar</param>
        /// <returns>Retorna true si agrega el libro</returns>
        public static bool AgregarLibro(Libro pNuevoLibro)
        {
            MensajeSocket<Libro> mensajeLibro = new MensajeSocket<Libro> { Metodo = "AgregarLibro", Entidad = pNuevoLibro };
            return EnviarRespuesta(JsonConvert.SerializeObject(mensajeLibro));
        }

        /// <summary>
        /// Solicita al servidor la lista de autores registrados en la base de datos
        /// </summary>
        /// <returns>Retorna la lista de autores</returns>
        public static List<Autor> ObtenerAutores()
        {
            try
            {
                MensajeSocket<string> mensajeObtenerAutores = new MensajeSocket<string> { Metodo = "ObtenerAutores" };
                EnviarRespuesta(JsonConvert.SerializeObject(mensajeObtenerAutores));

                var mensaje = clienteStreamReader.ReadLine();
                var listaAutores = JsonConvert.DeserializeObject<List<Autor>>(mensaje);

                return listaAutores;
            }
            catch (Exception)
            {

                throw;
            }           
        }

        /// <summary>
        /// Solicita al servidor agregar un nuevo autor
        /// </summary>
        /// <param name="pNuevoAutor">Nuevo autor</param>
        /// <returns>Retorna true si logra agregar el autor</returns>
        public static bool AgregarAutor(Autor pNuevoAutor)
        {
            MensajeSocket<Autor> mensajeAutor = new MensajeSocket<Autor> { Metodo = "AgregarAutor", Entidad = pNuevoAutor };
            return EnviarRespuesta(JsonConvert.SerializeObject(mensajeAutor));  
        }
    }
}
