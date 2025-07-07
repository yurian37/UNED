using BibiliotecaServidor.Entidades;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;

namespace BibiliotecaServidor.Datos
{
    public class AccesoDatosBiblioteca
    {
        private string cadenaConexion;
        public AccesoDatosBiblioteca()
        {
            //Se obtiene la cadena de conexión del app config del proyecto de interfaz
            cadenaConexion = ConfigurationManager.ConnectionStrings["conexionBiblioteca"].ConnectionString;
        }

        /// <summary>
        /// Agrega un nuevo autor en la base de datos
        /// </summary>
        /// <param name="pAutor">Nuevo autor a agregar</param>
        public void AgregarAutor(Autor pAutor)
        {

            SqlConnection conexion;
            SqlCommand comando = new SqlCommand();
            string sentencia;

            conexion = new SqlConnection(cadenaConexion);
            sentencia = " Insert	Into	Autor (IdAutor,		Nombre,		PrimerApellido,		SegundoApellido)" +
                        " Values	(@IdAutor,		@Nombre,		@PrimerApellido,		@SegundoApellido)";

            comando.CommandType = CommandType.Text;
            comando.CommandText = sentencia;
            comando.Connection = conexion;
            comando.Parameters.AddWithValue("@IdAutor", pAutor.IdAutor);
            comando.Parameters.AddWithValue("@Nombre", pAutor.Nombre);
            comando.Parameters.AddWithValue("@PrimerApellido", pAutor.PrimerApellido);
            comando.Parameters.AddWithValue("@SegundoApellido", pAutor.SegundoApellido);

            conexion.Open();

            comando.ExecuteNonQuery();

            conexion.Close();
        }

        /// <summary>
        /// Agrega un nuevo libro en la base de datos
        /// </summary>
        /// <param name="pLibro">Nuevo libro a agregar</param>
        public bool AgregarLibro(Libro pLibro)
        {
          
            string sentencia = " Insert	Into	Libro (ISBN,	IdAutor,	Titulo,		Descripcion,	NumeroEdicion)" +
                        " Values (@ISBN,	@IdAutor,	@Titulo,		@Descripcion,	@NumeroEdicion)";

            using (SqlConnection conexion = new SqlConnection(cadenaConexion))
            {
                conexion.Open();

                using (SqlCommand comando = new SqlCommand())
                {
                    comando.CommandType = CommandType.Text;
                    comando.CommandText = sentencia;
                    comando.Connection = conexion;
                    comando.Parameters.AddWithValue("@ISBN", pLibro.ISBN);
                    comando.Parameters.AddWithValue("@IdAutor", pLibro.IdAutor);
                    comando.Parameters.AddWithValue("@Titulo", pLibro.Titulo);
                    comando.Parameters.AddWithValue("@Descripcion", pLibro.Descripcion);
                    comando.Parameters.AddWithValue("@NumeroEdicion", pLibro.NumeroEdicion);
                    return comando.ExecuteNonQuery() > 0 ;
                }
            }

           
        }


        /// <summary>
        /// Retorna una colección genérica de autores de la base de datos
        /// </summary>
        /// <returns></returns>
        public List<Autor> ObtenerAutores()
        {
            List<Autor> listaAutores = new List<Autor>();
            
            string sentencia = " Select	IdAutor,    Nombre,	    PrimerApellido,     SegundoApellido" +
                        " From	    Autor";

            using (SqlConnection conexion= new SqlConnection(cadenaConexion))
            {
                using (SqlCommand comando = new SqlCommand())
                {
                    comando.CommandType = CommandType.Text;
                    comando.CommandText = sentencia;
                    comando.Connection = conexion;

                    conexion.Open();

                    using (SqlDataReader reader = comando.ExecuteReader())
                    {
                        if (reader.HasRows)
                        {
                            while (reader.Read())
                            {
                                listaAutores.Add(new Autor
                                {
                                    IdAutor = reader.GetString(0),
                                    Nombre = reader.GetString(1),
                                    PrimerApellido = reader.GetString(2),
                                    SegundoApellido = reader.IsDBNull(3) ? string.Empty : reader.GetString(3) //Si es nulo asigna string.Empty 
                                });
                            }
                        }
                    }
                }
            }

            return listaAutores;
        }

        /// <summary>
        /// Retorna una colección genérica de libros de la base de datos
        /// </summary>
        /// <param name="pIdAutor">Id del autor</param>
        /// <returns></returns>
        public List<Libro> ObtenerLibrosAutor(string pIdAutor)
        {
            List<Libro> listaLibros = new List<Libro>();

            SqlConnection conexion;
            SqlCommand comando = new SqlCommand();
            string sentencia;
            SqlDataReader reader;

            conexion = new SqlConnection(cadenaConexion);

            sentencia = " Select	ISBN,   Titulo,	Descripcion, NumeroEdicion, IdAutor " +
                "From	    Libro " +
                "Where     IdAutor =   @idautor";


            comando.CommandType = CommandType.Text;
            comando.CommandText = sentencia;
            comando.Connection = conexion;
            comando.Parameters.AddWithValue("@idautor", pIdAutor);

            conexion.Open();

            reader = comando.ExecuteReader();

            if (reader.HasRows)
            {
                while (reader.Read())
                {
                    listaLibros.Add(new Libro
                    {
                        ISBN = reader.GetString(0),
                        Titulo = reader.GetString(1),
                        Descripcion = reader.GetString(2),
                        NumeroEdicion = reader.GetInt32(3),
                        IdAutor = reader.GetString(4)
                    });
                }
            }

            conexion.Close();

            return listaLibros;
        }
    }
}
