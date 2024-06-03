/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.ejercicio01_245804;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Diana Sofia Bastidas Osuna-245804
 */
public class BdaCrud {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         final String SERVER = "localhost";
        final String BASE_DATOS = "activistas";
        final String CADENA_CONEXION = "jdbc:mysql://" + SERVER + "/" + BASE_DATOS;
        final String USUARIO = "root";
        final String CONTRASEÑA = "120504Bas";

//        try {
//            Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);
//            
//            String sentenciaSql = "INSERT INTO 'alumnos' ('nombres', 'apellidoPaterno', 'apellidoMaterno') VALUES (?, ?, ?);";
//            
//            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);
//            
//            preparedStatement.setString(1, "Diana");
//            preparedStatement.setString(2, "Bastidas");
//            preparedStatement.setString(3, "Jocobi");
//            
//            preparedStatement.executeUpdate();
//            
//            ResultSet resultado = preparedStatement.getGeneratedKeys();
//            
//            while(resultado.next()){
//                System.out.println(resultado.getInt(1));
//            }
//        } catch (SQLException ex) {
//            System.out.println("Ocurrio un error: "+ex.getMessage());
//        }
        try {

            // Establecer la conexión a la base de datos
            Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);

            // Sentencia SQL para insertar un nuevo alumno en la tabla 'alumnos'
            String sentenciaSql = "INSERT INTO alumnos (nombres, apellidoPaterno, apellidoMaterno) VALUES (?, ?, ?);";
            // Preparar la sentencia SQL, permitiendo obtener las claves generadas automáticamente
            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);
            // Establecer los valores para los parámetros de la sentencia SQL
            preparedStatement.setString(1, "Sofia");
            preparedStatement.setString(2, "Osuna");
            preparedStatement.setString(3, "Otro");

            // Ejecutar la sentencia SQL de inserción
            preparedStatement.executeUpdate();

            // Obtener las claves generadas automáticamente (por ejemplo, el ID del nuevo registro)
            ResultSet resultado = preparedStatement.getGeneratedKeys();
            while (resultado.next()) {
                // Imprimir el ID generado para el nuevo registro
                System.out.println("Alumno: " + resultado.getInt(1));
            }
        } catch (SQLException ex) {
            // Capturar y manejar cualquier excepción SQL que ocurra
            System.out.println("Ocurrio un error  " + ex.getMessage());
        }

        try {
            Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);

            String sentenciaSql = "SELECT * FROM alumnos WHERE idAlumno = ?;";

            PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSql);

            preparedStatement.setInt(1, 1);

            ResultSet resultado = preparedStatement.executeQuery();

            while (resultado.next()) {
                int idAlumno = resultado.getInt("idAlumno");
                String nombres = resultado.getString("nombres");
                String apellidoPaterno = resultado.getString("apellidoPaterno");
                String apellidoMaterno = resultado.getString("apellidoMaterno");
                boolean eliminado = resultado.getBoolean("eliminado");
                boolean activo = resultado.getBoolean("activo");

                System.out.println("ID del alumno: " + idAlumno);
                System.out.println("Nombres: " + nombres);
                System.out.println("Apellido Paterno: " + apellidoPaterno);
                System.out.println("Apellido Materno: " + apellidoMaterno);
                System.out.println("Eliminado: " + eliminado);
                System.out.println("Activo: " + activo);
            }

        } catch (SQLException ex) {
            System.out.println("Ocurrio un error. " + ex.getMessage());
        }
    }
    }
    

