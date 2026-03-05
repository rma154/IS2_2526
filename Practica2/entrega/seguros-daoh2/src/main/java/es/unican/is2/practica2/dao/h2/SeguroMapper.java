package es.unican.is2.practica2.dao.h2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import es.unican.is2.practica2.common.DataAccessException;
import es.unican.is2.practica2.domain.Cobertura;
import es.unican.is2.practica2.domain.Seguro;

public class SeguroMapper {

    public static Seguro toSeguro(ResultSet results) throws DataAccessException {
    	
        try {
        	
            long id = results.getLong("id");
            
            String matricula = results.getString("matricula");
            
            LocalDate fecha = results.getDate("fechaInicio").toLocalDate();
            
            Cobertura cobertura = Cobertura.valueOf(results.getString("cobertura"));
            
            int potencia = results.getInt("potencia");
            
            String conductorAdicional = results.getString("conductorAdicional");
            
            String dniCliente = results.getString("cliente_FK");

            Seguro seg = new Seguro();
            
            seg.setId(id);
            
            seg.setMatricula(matricula);
            
            seg.setFechaInicio(fecha);
            
            seg.setCobertura(cobertura);
            
            seg.setPotencia(potencia);
            
            seg.setConductorAdicional(conductorAdicional);
            
            seg.setDniCliente(dniCliente);

            return seg;
            
        } catch (SQLException e) {
        	
            throw new DataAccessException();
        }
    }
}