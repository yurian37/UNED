using BibiliotecaServidor.Datos;
using BibiliotecaServidor.Entidades;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BibiliotecaServidor.Negocio
{
    public class NegocioLibros
    {
        DatosBiblioteca ADBiblioteca = new DatosBiblioteca();
        public bool AgregarLibro(Libro pLibro) 
        {
            if (pLibro is null)
            {
                throw new ArgumentException("La entidad está vacía");
            }

            if (string.IsNullOrEmpty(pLibro.Titulo))
            {
                throw new ArgumentException("El nombre está vacío");
            }

            ADBiblioteca.AgregarLibro(pLibro);
            return true;
        }
    }
}
