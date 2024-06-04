/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import DTOs.AlumnoTablaDTO;
import entidad.AlumnoEntidad;
import java.util.ArrayList;
import java.util.List;
import persistencia.IAlumnoDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author Diana Sofia Bastidas Osuna-245804
 */
public class AlumnoNegocio implements IAlumnoNegocio {

    private IAlumnoDAO alumnoDAO;

    public AlumnoNegocio(IAlumnoDAO alumnoDAO) {
        this.alumnoDAO = alumnoDAO;
    }

    @Override
    public List<AlumnoTablaDTO> buscarAlumnosTabla() throws NegocioException {
        try {
            List<AlumnoEntidad> alumnos = this.alumnoDAO.buscarAlumnosTabla();
            return this.convertirAlumnoTablaDTO(alumnos);
        } catch (PersistenciaException ex) {
            // hacer uso de Logger
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }
    }

    private List<AlumnoTablaDTO> convertirAlumnoTablaDTO(List<AlumnoEntidad> alumnos) throws NegocioException {
        if (alumnos == null) {
            throw new NegocioException("No se pudieron obtener los alumnos. Debido a que no hay registros.");
        }

        List<AlumnoTablaDTO> alumnosDTO = new ArrayList<>();
        for (AlumnoEntidad alumno : alumnos) {
            AlumnoTablaDTO dto = new AlumnoTablaDTO();
            dto.setIdAlumno(alumno.getIdAlumno());
            dto.setNombres(alumno.getNombres());
            dto.setApellidoPaterno(alumno.getApellidoPaterno());
            dto.setApellidoMaterno(alumno.getApellidoMaterno());
            dto.setEstatus(alumno.isActivo() == true ? "Activo" : "Inactivo");
            alumnosDTO.add(dto);
        }
        return alumnosDTO;
    }
    
    /**
     *
     * @param nombres
     * @param apellidoPaterno
     * @param apellidoMaterno
     * @throws NegocioException
     */
    @Override
    public void registrarAlumno(String nombres, String apellidoPaterno, String apellidoMaterno) throws NegocioException {
        if (nombres == null || nombres.trim().isEmpty() || apellidoPaterno == null || apellidoPaterno.trim().isEmpty()) {
            throw new NegocioException("Por favor, ingresa la información obligatoria correctamente solicitada en este formulario.");
        }

        if (!esNombreValido(nombres)) {
            throw new NegocioException("El nombre ingresado no es válido. Por favor, verifica que solo contenga letras y espacios.");
        }

        AlumnoEntidad nuevoAlumno = new AlumnoEntidad(0, nombres, apellidoPaterno, apellidoMaterno, true, true);
        nuevoAlumno.setNombres(nombres.trim());
        nuevoAlumno.setApellidoPaterno(apellidoPaterno.trim());
        nuevoAlumno.setApellidoMaterno(apellidoMaterno != null ? apellidoMaterno.trim() : null);
        nuevoAlumno.setEliminado(false);
        nuevoAlumno.setActivo(true);

        try {
            alumnoDAO.registrarAlumno(nuevoAlumno);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al registrar el alumno: " + ex.getMessage());
        }
    }
    
    @Override
    public void editarAlumno(int idAlumno, String nombres, String apellidoPaterno, String apellidoMaterno) throws NegocioException {
        if (idAlumno <= 0 || nombres == null || nombres.trim().isEmpty() || apellidoPaterno == null || apellidoPaterno.trim().isEmpty()) {
            throw new NegocioException("Por favor, ingresa la información obligatoria correctamente solicitada en este formulario.");
        }

        if (!esNombreValido(nombres)) {
            throw new NegocioException("El nombre ingresado no es válido. Por favor, verifica que solo contenga letras y espacios.");
        }

        AlumnoEntidad alumno = new AlumnoEntidad(idAlumno, nombres, apellidoPaterno, apellidoMaterno, true, true);
        alumno.setIdAlumno(idAlumno);
        alumno.setNombres(nombres.trim());
        alumno.setApellidoPaterno(apellidoPaterno.trim());
        alumno.setApellidoMaterno(apellidoMaterno != null ? apellidoMaterno.trim() : null);

        try {
            alumnoDAO.editarAlumno(alumno);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al editar el alumno: " + ex.getMessage());
        }
    }
    
    @Override
    public void eliminarAlumno(int idAlumno) throws NegocioException {
        if (idAlumno <= 0) {
            throw new NegocioException("ID de alumno no válido.");
        }

        try {
            alumnoDAO.eliminarAlumno(idAlumno);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al eliminar el alumno: " + ex.getMessage());
        }
    }

    private boolean esNombreValido(String nombre) {
        return nombre.matches("^[\\p{L} .'-]+$");
    }
}


