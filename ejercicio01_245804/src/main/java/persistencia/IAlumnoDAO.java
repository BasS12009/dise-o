/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import entidad.AlumnoEntidad;
import java.util.List;

/**
 *
 * @author Diana Sofia Bastidas Osuna-245804
 */
public interface IAlumnoDAO {
    public List<AlumnoEntidad> buscarAlumnosTabla() throws PersistenciaException;
}
