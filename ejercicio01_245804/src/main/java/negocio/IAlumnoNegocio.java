/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import DTOs.AlumnoTablaDTO;
import java.util.List;

/**
 *
 * @author Diana Sofia Bastidas Osuna-245804
 */
public interface IAlumnoNegocio  {
    public List<AlumnoTablaDTO> buscarAlumnosTabla() throws NegocioException;
//    public AlumnoLecturaDTO insertar(GuardarAlumnoDTO alumno) throws NegocioException;
//    
//    public AlumnoLecturaDTO obtenerPorId(int id) throws NegocioException;
//    
//    public EditarAlumnoDTO editar(EditarAlumno alumno) throws NegocioException;

    public void registrarAlumno(String nombres, String apellidoPaterno, String apellidoMaterno) throws NegocioException;

    public void editarAlumno(int idAlumno, String nombres, String apellidoPaterno, String apellidoMaterno) throws NegocioException;
    
    public void eliminarAlumno(int idAlumno) throws NegocioException;
}
