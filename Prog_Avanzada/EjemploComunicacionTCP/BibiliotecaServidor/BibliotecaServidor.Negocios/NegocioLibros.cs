using BibiliotecaServidor.Datos;
using BibiliotecaServidor.Entidades;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BibliotecaServidor.Negocios
{
    public class NegocioLibros
    {
        AccesoDatosBiblioteca ADBiblioteca = new AccesoDatosBiblioteca();
        public bool AgregarLibro(Libro pLibro)
        {
            if (pLibro is null)
            {
                throw new NullReferenceException("La entidad libro se encuentra vacía.");
            }

            if (string.IsNullOrEmpty(pLibro.Titulo) || string.IsNullOrEmpty(pLibro.ISBN) || string.IsNullOrEmpty(pLibro.Descripcion))
            {
                throw new NullReferenceException("Complete todos los datos requeridos");
            }

            if (pLibro.NumeroEdicion == 0)
            {
                throw new NullReferenceException("El número de edición es un dato requerido.");
            }

            try
            {
                return ADBiblioteca.AgregarLibro(pLibro);
            }
            catch (SqlException ex) {
                throw new Exception("Se presentó un error al conectar a la base de datos.", ex);
            }
            catch (Exception ex) {
                throw new Exception("Se presentó un error en el sistema al guardar los datos.", ex);
            }
        }
        
        public List<Libro> ListarLibrosAutor(string pIdAutor) 
        {
            return ADBiblioteca.ObtenerLibrosAutor(pIdAutor);
        }
    }
}
