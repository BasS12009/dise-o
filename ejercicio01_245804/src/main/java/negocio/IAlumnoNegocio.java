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
}
