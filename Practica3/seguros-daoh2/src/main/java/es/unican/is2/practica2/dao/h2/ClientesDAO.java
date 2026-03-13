package es.unican.is2.practica2.dao.h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import es.unican.is2.practica2.common.DataAccessException;
import es.unican.is2.practica2.dao.api.IClientesDAO;
import es.unican.is2.practica2.domain.Cliente;
import es.unican.is2.practica2.domain.Seguro;

public class ClientesDAO implements IClientesDAO {

    public Cliente creaCliente(Cliente c) throws DataAccessException {
    	
        String insertStatement = String.format(
        		
                "insert into Clientes(dni, nombre, minusvalia) values ('%s', '%s', '%b')",
                c.getDni(), c.getNombre(), c.getMinusvalia());
        
        H2ServerConnectionManager.executeSqlStatement(insertStatement);
        
        return c;
    }

    public Cliente cliente(String dni) throws DataAccessException {
    	
        Cliente result = null;
        
        Connection con = H2ServerConnectionManager.getConnection();
        
        try {
        	
            Statement statement = con.createStatement();
            
            String statementText = "select * from Clientes where dni = '" + dni + "'";
            
            ResultSet results = statement.executeQuery(statementText);
            
            if (results.next()) {
            	
                result = procesaCliente(con, results);
            }
            
            statement.close();
            
        } catch (SQLException e) {
        	
            throw new DataAccessException();
        }
        
        return result;
    }

    public Cliente actualizaCliente(Cliente nuevo) throws DataAccessException {
    	
        Cliente old = cliente(nuevo.getDni());

        String statementText = String.format(
        		
                "update Clientes set nombre = '%s', minusvalia = '%b' where dni = '%s'",
                nuevo.getNombre(), nuevo.getMinusvalia(), nuevo.getDni());
        
        H2ServerConnectionManager.executeSqlStatement(statementText);

        // (lo dejo como estaba; no lo necesitas para Consulta Cliente)
        if (old != null) {
        	
            for (Seguro s : old.getSeguros()) {
            	
                if (!nuevo.getSeguros().contains(s)) {
                	
                    statementText = String.format(
                            "update Seguros set cliente_FK = null where id = '%d'",
                            s.getId());
                    
                    H2ServerConnectionManager.executeSqlStatement(statementText);
                }
            }
        }

        return cliente(nuevo.getDni());
    }

    public Cliente eliminaCliente(String dni) throws DataAccessException {
    	
        Cliente cliente = cliente(dni);
        
        String statementText = "delete from Clientes where dni = '" + dni + "'";
        
        H2ServerConnectionManager.executeSqlStatement(statementText);
        
        return cliente;
    }

    public List<Cliente> clientes() throws DataAccessException {
    	
        List<Cliente> clientes = new LinkedList<>();
        
        Connection con = H2ServerConnectionManager.getConnection();
        
        try {
        	
            Statement statement = con.createStatement();
            
            ResultSet results = statement.executeQuery("select * from Clientes");
            
            while (results.next()) {
            	
                clientes.add(procesaCliente(con, results));
            }
            
            statement.close();
            
        } catch (SQLException e) {
        	
            throw new DataAccessException();
        }
        
        return clientes;
    }

    private Cliente procesaCliente(Connection con, ResultSet results) throws SQLException, DataAccessException {
    	
        Cliente result = ClienteMapper.toCliente(results);
        
        Statement statement = con.createStatement();
        
        ResultSet rs = statement.executeQuery(
                String.format("select * from Seguros where cliente_FK = '%s'", result.getDni()));
        
        while (rs.next()) {
        	
            result.getSeguros().add(SeguroMapper.toSeguro(rs));
        }
        
        statement.close();
        
        return result;
    }
}