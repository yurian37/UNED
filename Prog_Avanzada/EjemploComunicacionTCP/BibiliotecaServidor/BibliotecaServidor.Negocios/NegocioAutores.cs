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
    public class NegocioAutores
    {
        AccesoDatosBiblioteca ADBiblioteca = new AccesoDatosBiblioteca();

        public bool AgregarAutor(Autor pAutor)
        {
            if (pAutor is null)
            {
                throw new NullReferenceException("La entidad autor se encuentra vacía.");
            }

            if (string.IsNullOrEmpty(pAutor.IdAutor) || string.IsNullOrEmpty(pAutor.Nombre) || string.IsNullOrEmpty(pAutor.PrimerApellido))
            {
                throw new NullReferenceException("Complete todos los datos requeridos");
            }

            try
            {
                ADBiblioteca.AgregarAutor(pAutor);
            }
            catch (SqlException ex)
            {
                throw new Exception("Se presentó un error al conectar a la base de datos.", ex);
            }
            catch (Exception ex)
            {
                throw new Exception("Se presentó un error en el sistema al guardar los datos.", ex);
            }

            return true;
        }

        public List<Autor> ObtenerAutores()
        {
            return ADBiblioteca.ObtenerAutores();
        }
    }
}
