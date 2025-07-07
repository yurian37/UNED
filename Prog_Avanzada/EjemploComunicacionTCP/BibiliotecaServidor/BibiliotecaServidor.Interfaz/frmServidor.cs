using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using System.Net;
using System.Net.Sockets;
using System.Threading;
using BibiliotecaServidor.Entidades;
using Newtonsoft.Json;
using System.IO;
using BibiliotecaServidor.Datos;
using BibliotecaServidor.Negocios;


namespace BibiliotecaServidor.Interfaz
{
    public partial class frmServidor : Form
    {
        //Instancias del negocio
        private readonly NegocioLibros negocioLibros = new NegocioLibros();
        private readonly NegocioAutores negocioAutores = new NegocioAutores();
        private readonly NegocioComunicacionTCP comunicacionTCP = new NegocioComunicacionTCP();

        //Delegado, necesario para modificar controles de la interfaz gráfica desde un subproceso
        private delegate void EscribirEnTextboxDelegado(string texto);
        private delegate void ModoficarListBoxDelegado(string texto, bool agregar);

        //Declarar delegados
        EscribirEnTextboxDelegado modificarTextotxtBitacora;
        ModoficarListBoxDelegado modificarListBoxClientes; 

        public frmServidor()
        {
            InitializeComponent();
            //Suscribir el evento y los delegados
            comunicacionTCP.MensajeRecibido += ComunicacionTCP_MensajeRecibido;
            modificarTextotxtBitacora = new EscribirEnTextboxDelegado(EscribirEnTextbox);
            modificarListBoxClientes = new ModoficarListBoxDelegado(ModificarListBox);

            lblEstado.ForeColor = Color.Red;
            btnDetener.Enabled = false;

        }

        /// <summary>
        /// Método asociado al delegado: es un método que tiene la firma adecuada para actuar como un controlador de eventos. 
        /// Su firma debe coincidir con el delegado en este caso EventHandler.
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void ComunicacionTCP_MensajeRecibido(object sender, (string mensaje, StreamWriter streamWriter) e)
        {
            // Manejar el mensaje recibido
            try
            {
                var mensajeRecibido = JsonConvert.DeserializeObject<MensajeSocket<object>>(e.mensaje);
                SeleccionarMetodo(mensajeRecibido.Metodo, mensajeRecibido.Entidad, ref e.streamWriter);
            }
            catch (JsonException)
            {
                MessageBox.Show("No fue posible convertir el objeto.");
                // Manejar el error de deserialización JSON si es necesario
            }
            catch (Exception ex) 
            {
                MessageBox.Show("No fue posible guardar los datos correctamente.", ex.Message);
            }
        }

        //Método utilizado por el delegado para modificar la interfaz gráfica desde un subproceso
        private void EscribirEnTextbox(string texto)
        {
            txtBitacora.AppendText(DateTime.Now.ToString() + " - " + texto);
            txtBitacora.AppendText(Environment.NewLine);
        }

        //Método utilizado por el delegado para modificar la interfaz gráfica desde un subproceso
        private void ModificarListBox(string texto, bool agregar)
        {
            if (agregar)
                lstClientesConectados.Items.Add(texto);
            else      
                lstClientesConectados.Items.Remove(texto);
        }

        private void btnIniciar_Click(object sender, EventArgs e)
        {           
            comunicacionTCP.Iniciar();

            lblEstado.ForeColor = Color.Green;
            btnIniciar.Enabled = false;
            btnDetener.Enabled = true;

            txtBitacora.Text = "Servidor iniciado... en (127.0.0.1, 30000)";
            txtBitacora.AppendText(Environment.NewLine);
        }

        private void btnDetener_Click(object sender, EventArgs e)
        {
            comunicacionTCP.Detener();
            lblEstado.ForeColor = Color.Red;
            lblEstado.Text = "Sin iniciar";
            btnIniciar.Enabled = true;
            btnDetener.Enabled = false;
        }


        /// <summary>
        /// Método donde se procesa el mensaje recibido para seleccionar el método que está solicitanto el cliente
        /// </summary>
        /// <param name="pMetodo">Nombre del método que se debe invocar</param>
        /// <param name="pMensaje">Mensaje enviado por el cliente</param>
        /// 
        public void SeleccionarMetodo(string pMetodo, object entidad, ref StreamWriter servidorStreamWriter)
        {
            switch (pMetodo)
            {
                case "Conectar":
                    Conectar((string)entidad);
                    break;
                case "AgregarAutor":
                    var entidadAutor = JsonConvert.DeserializeObject<Autor>(JsonConvert.SerializeObject(entidad));
                    AgregarAutor(entidadAutor);
                    break;
                case "AgregarLibro":
                    var entidadLibro = JsonConvert.DeserializeObject<Libro>(JsonConvert.SerializeObject(entidad));
                    AgregarLibro(entidadLibro);
                    break;
                case "ObtenerAutores":
                    ObtenerAutores(ref servidorStreamWriter);
                    break;
                case "ObtenerLibrosDeAutor":
                    var idAutor = JsonConvert.DeserializeObject<string>(JsonConvert.SerializeObject(entidad));
                     ObtenerLibrosDeAutor(idAutor,ref servidorStreamWriter);
                    break;
                case "Desconectar":
                    Desconectar((string)entidad);
                    break;
                default:
                    // Manejar el método desconocido si es necesario
                    break;
            }
        }
        /// <summary>
        /// Método utilizado para liberar el stream
        /// </summary>
        /// <param name="respuesta"></param>
        /// <param name="servidorStreamWriter"></param>
        private void EnviarRespuesta(string respuesta,ref StreamWriter servidorStreamWriter)
        {
            try
            {
                servidorStreamWriter.WriteLine(respuesta);
                servidorStreamWriter.Flush();
            }
            catch
            {
                // Manejo de excepciones al enviar la respuesta
                MessageBox.Show("No fue posible enviar los datos del stream writer");
            }
        }

        private void Desconectar(string pIdentificadorCliente)
        {
            txtBitacora.Invoke(modificarTextotxtBitacora, new object[] { pIdentificadorCliente + " se ha desconectado!" });
            lstClientesConectados.Invoke(modificarListBoxClientes, new object[] { pIdentificadorCliente, false });
        }
        /// <summary>
        /// Método para agregar un libro a la capa de negocio
        /// </summary>
        /// <param name="pNuevoLibro"></param>
        private void AgregarLibro(Libro pNuevoLibro)
        {
            try
            {
                if (negocioLibros.AgregarLibro(pNuevoLibro))
                    MessageBox.Show("Sus datos se han agregado correctamente.", "Guardando...", MessageBoxButtons.OK, MessageBoxIcon.Information);
                else
                    MessageBox.Show("No ha sido posible guardar los datos.", "Guardando...", MessageBoxButtons.OK, MessageBoxIcon.Error);

            }
            catch (Exception ex)
            {
                MessageBox.Show("Se presentó un error: {0}", ex.Message);
            }
        }

        /// <summary>
        /// Método para obtener libros desde la capa de negocio
        /// </summary>
        /// <param name="pIdAutor"></param>
        /// <param name="servidorStreamWriter"></param>
        private void ObtenerLibrosDeAutor(string pIdAutor, ref StreamWriter servidorStreamWriter)
        {
            try
            {
                List<Libro> listaLibros = new List<Libro>();
                listaLibros = negocioLibros.ListarLibrosAutor(pIdAutor);
                EnviarRespuesta(JsonConvert.SerializeObject(listaLibros),ref servidorStreamWriter);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Se presentó un error: {0}", ex.Message);
            }
        }

        /// <summary>
        /// Método para obtener autores desde la capa de negocio
        /// </summary>
        /// <param name="servidorStreamWriter"></param>
        private void ObtenerAutores(ref StreamWriter servidorStreamWriter)
        {
            try
            {
                List<Autor> listaAutores = new List<Autor>();
                listaAutores = negocioAutores.ObtenerAutores();
                EnviarRespuesta(JsonConvert.SerializeObject(listaAutores),ref servidorStreamWriter);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Se presentó un error: {0}", ex.Message);
            }
        }

        private void AgregarAutor(Autor pNuevoAutor)
        {
            try
            {
                if (negocioAutores.AgregarAutor(pNuevoAutor))
                    MessageBox.Show("Sus datos se han agregado correctamente.", "Guardando...", MessageBoxButtons.OK);
                else
                    MessageBox.Show("No ha sido posible guardar los datos.", "Guardando...", MessageBoxButtons.OK);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Se presentó un error: {0}", ex.Message);
            }
        }

        private void Conectar(string pIdentificadorCliente)
        {
            txtBitacora.Invoke(modificarTextotxtBitacora, new object[] { pIdentificadorCliente + " se ha conectado..." });
            lstClientesConectados.Invoke(modificarListBoxClientes, new object[] { pIdentificadorCliente, true });
        }

        private void btnMantenimiento_Click(object sender, EventArgs e)
        {
            FrmAgregarAutores frmAgregarAutores = new FrmAgregarAutores();
            frmAgregarAutores.ShowDialog();
        }
    }
}
