using BibiliotecaServidor.Entidades;
using BibliotecaServidor.Negocios;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace BibiliotecaServidor.Interfaz
{
    public partial class FrmAgregarAutores : Form
    {
        NegocioAutores negocioAutores = new NegocioAutores();
        public FrmAgregarAutores()
        {
            InitializeComponent();
        }

        private void btnGuardar_Click(object sender, EventArgs e)
        {
            try
            {
                //Se crea una nueva instancia del objeto Autor
                Autor nuevoAutor = new Autor();

                //Se realiza la asignacion de los valores en las propiedades correspondientes
                nuevoAutor.IdAutor = txtId.Text;
                nuevoAutor.Nombre = txtNombre.Text;
                nuevoAutor.PrimerApellido = txtApe1.Text;
                nuevoAutor.SegundoApellido = txtApe2.Text;

                //Se agrega el nuevo Autor mediante la conexión tcp              
                bool agregaAutor = negocioAutores.AgregarAutor(nuevoAutor);

                if (agregaAutor)
                {
                    //Se limpian los TextBox para un nuevo ingreso de información
                    txtId.Clear();
                    txtNombre.Clear();
                    txtApe1.Clear();
                    txtApe2.Clear();
                    grdListaAutores.DataSource = negocioAutores.ObtenerAutores();

                    MessageBox.Show("Autor agregado correctamente", "Información", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Ha ocurrido un error al crear el Autor " + ex.Message, "Atención", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void FrmAgregarAutores_Load(object sender, EventArgs e)
        {
            //Ejemplo 1:
            grdListaAutores.AutoGenerateColumns = false;
            //grdListaAutores.DataSource = negocioAutores.ObtenerAutores();

            //Ejemplo 2:
            foreach (var item in negocioAutores.ObtenerAutores())
            {
                grdListaAutores.Rows.Add(item.IdAutor, item.Nombre, item.PrimerApellido, item.SegundoApellido);
            }
        }
    }
}
