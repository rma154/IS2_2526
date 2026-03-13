package es.unican.is2.practica2.dao.h2;

import java.sql.ResultSet;
import java.sql.SQLException;

import es.unican.is2.practica2.common.DataAccessException;
import es.unican.is2.practica2.domain.Cliente;

public class ClienteMapper {

    public static Cliente toCliente(ResultSet results) throws DataAccessException {
    	
        try {
        	
            String dni = results.getString("dni");
            
            String nombre = results.getString("nombre");
            
            boolean minusvalia = results.getBoolean("minusvalia");

            Cliente cont = new Cliente();
            
            cont.setDni(dni);
            
            cont.setNombre(nombre);
            
            cont.setMinusvalia(minusvalia);
            
            return cont;

        } catch (SQLException e) {
        	
            throw new DataAccessException();
        }
    }
}