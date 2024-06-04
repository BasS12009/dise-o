/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import entidad.AlumnoEntidad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Diana Sofia Bastidas Osuna-245804
 */
public class AlumnoDAO implements IAlumnoDAO {

    private IConexionBD conexionBD;

    public AlumnoDAO(IConexionBD conexionBD) {
        this.conexionBD = conexionBD;
    }

    /**
     * Busca y devuelve una lista de todos los alumnos en la tabla "alumnos".
     * 
     * @return Lista de objetos AlumnoEntidad.
     * @throws PersistenciaException Si ocurre un error al leer la base de datos.
     */
    @Override
    public List<AlumnoEntidad> buscarAlumnosTabla() throws PersistenciaException {
        try {
            List<AlumnoEntidad> alumnosLista = null;
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT idAlumno, nombres, apellidoPaterno, apellidoMaterno, eliminado, activo FROM alumnos";
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            while (resultado.next()) {
                if (alumnosLista == null) {
                    alumnosLista = new ArrayList<>();
                }
                AlumnoEntidad alumno = this.convertirAEntidad(resultado);
                alumnosLista.add(alumno);
            }
            conexion.close();
            return alumnosLista;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistenciaException("Ocurrió un error al leer la base de datos, inténtelo de nuevo.");
        }

    }

     /**
     * Convierte un ResultSet en un objeto AlumnoEntidad.
     * 
     * @param resultado El ResultSet obtenido de una consulta SQL.
     * @return Un objeto AlumnoEntidad.
     * @throws SQLException Si ocurre un error al leer el ResultSet.
     */
    private AlumnoEntidad convertirAEntidad(ResultSet resultado) throws SQLException {
        int id = resultado.getInt("idAlumno");
        String nombre = resultado.getString("nombres");
        String paterno = resultado.getString("apellidoPaterno");
        String materno = resultado.getString("apellidoMaterno");
        boolean eliminado = resultado.getBoolean("eliminado");
        boolean activo = resultado.getBoolean("activo");
        return new AlumnoEntidad(id, nombre, paterno, materno, eliminado, activo);
    }

   /**
     * Registra un nuevo alumno en la base de datos.
     * 
     * @param alumno El objeto AlumnoEntidad a registrar.
     * @throws PersistenciaException Si ocurre un error al registrar el alumno.
     */
    @Override
    public void registrarAlumno(AlumnoEntidad alumno) throws PersistenciaException {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultado = null;

        try {
            conexion = conexionBD.crearConexion();
            conexion.setAutoCommit(false);

            String sentenciaSql = "INSERT INTO alumnos (nombres, apellidoPaterno, apellidoMaterno, eliminado, activo) VALUES (?, ?, ?, ?, ?);";
            preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);

             // Asignar valores a la sentencia SQL
            preparedStatement.setString(1, alumno.getNombres());
            preparedStatement.setString(2, alumno.getApellidoPaterno());
            preparedStatement.setString(3, alumno.getApellidoMaterno());
            preparedStatement.setBoolean(4, alumno.isEliminado());
            preparedStatement.setBoolean(5, alumno.isActivo());

            // Ejecutar la sentencia SQL
            preparedStatement.executeUpdate();

            resultado = preparedStatement.getGeneratedKeys();
            if (resultado.next()) {
                alumno.setIdAlumno(resultado.getInt(1));
            }

            conexion.commit();
        } catch (SQLException ex) {
            if (conexion != null) {
                try {
                    conexion.rollback();
                } catch (SQLException e) {
                    throw new PersistenciaException("Error al revertir la transacción: " + e.getMessage());
                }
            }
            throw new PersistenciaException("Error al registrar el alumno: " + ex.getMessage());
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }


    @Override
    public void editarAlumno(AlumnoEntidad alumno) throws PersistenciaException {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;

        try {
            conexion = conexionBD.crearConexion();
            String sentenciaSql = "UPDATE alumnos SET nombres = ?, apellidoPaterno = ?, apellidoMaterno = ? WHERE idAlumno = ?";
            preparedStatement = conexion.prepareStatement(sentenciaSql);
            preparedStatement.setString(1, alumno.getNombres());
            preparedStatement.setString(2, alumno.getApellidoPaterno());
            preparedStatement.setString(3, alumno.getApellidoMaterno());
            preparedStatement.setInt(4, alumno.getIdAlumno());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al editar el alumno: " + ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }
    

    @Override
    public void eliminarAlumno(int idAlumno) throws PersistenciaException {
        Connection conexion = null;
        PreparedStatement preparedStatement = null;

        try {
            conexion = conexionBD.crearConexion();
            String sentenciaSql = "DELETE FROM alumnos WHERE idAlumno = ?";
            preparedStatement = conexion.prepareStatement(sentenciaSql);
            preparedStatement.setInt(1, idAlumno);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al eliminar el alumno: " + ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                throw new PersistenciaException("Error al cerrar los recursos: " + e.getMessage());
            }
        }
    }

}
