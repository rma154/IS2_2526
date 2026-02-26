package es.unican.is2.practica2.dao.h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import es.unican.is2.practica2.common.DataAccessException;
import es.unican.is2.practica2.dao.api.ISegurosDAO;
import es.unican.is2.practica2.domain.Seguro;

public class SegurosDAO implements ISegurosDAO {

    @Override
    public Seguro creaSeguro(Seguro s) throws DataAccessException {

        String conductorSql = (s.getConductorAdicional() == null)
                ? "NULL"
                : "'" + s.getConductorAdicional() + "'";

        String insertStatement = String.format(
                "insert into Seguros(matricula, fechaInicio, cobertura, potencia, conductorAdicional, cliente_FK) " +
                "values ('%s', '%s', '%s', %d, %s, '%s')",
                s.getMatricula(),
                s.getFechaInicio().toString(),
                s.getCobertura().toString(),
                s.getPotencia(),
                conductorSql,
                s.getDniCliente()
        );

        H2ServerConnectionManager.executeSqlStatement(insertStatement);
        
        return s;
    }

    @Override
    public Seguro eliminaSeguro(long id) throws DataAccessException {
    	
        Seguro seguro = seguro(id);
        
        String statementText = "delete from Seguros where id = " + id;
        
        H2ServerConnectionManager.executeSqlStatement(statementText);
        
        return seguro;
    }

    @Override
    public Seguro actualizaSeguro(Seguro nuevo) throws DataAccessException {

        String conductorSql = (nuevo.getConductorAdicional() == null)
                ? "NULL"
                : "'" + nuevo.getConductorAdicional() + "'";

        String statementText = String.format(
                "update Seguros set matricula = '%s', fechaInicio = '%s', cobertura = '%s', potencia = '%d', " +
                "conductorAdicional = %s, cliente_FK = '%s' where id = '%d'",
                nuevo.getMatricula(),
                nuevo.getFechaInicio().toString(),
                nuevo.getCobertura().toString(),
                nuevo.getPotencia(),
                conductorSql,
                nuevo.getDniCliente(),
                nuevo.getId()
        );

        H2ServerConnectionManager.executeSqlStatement(statementText);
        
        return seguro(nuevo.getId());
    }

    @Override
    public Seguro seguro(long id) throws DataAccessException {
    	
        Seguro result = null;
        
        Connection con = H2ServerConnectionManager.getConnection();
        
        try {
        	
            Statement statement = con.createStatement();
            
            ResultSet results = statement.executeQuery("select * from Seguros where id = '" + id + "'");
            
            if (results.next()) result = SeguroMapper.toSeguro(results);
            
            statement.close();
            
        } catch (SQLException e) {
        	
            throw new DataAccessException();
        }
        
        return result;
    }

    @Override
    public List<Seguro> seguros() throws DataAccessException {
    	
        List<Seguro> seguros = new LinkedList<>();
        
        Connection con = H2ServerConnectionManager.getConnection();
        
        try {
        	
            Statement statement = con.createStatement();
            
            ResultSet results = statement.executeQuery("select * from Seguros");
            
            while (results.next()) seguros.add(SeguroMapper.toSeguro(results));
            
            statement.close();
            
        } catch (SQLException e) {
        	
            throw new DataAccessException();
        }
        
        return seguros;
    }

    @Override
    public Seguro seguroPorMatricula(String matricula) throws DataAccessException {
    	
        Seguro result = null;
        
        Connection con = H2ServerConnectionManager.getConnection();
        
        try {
        	
            Statement statement = con.createStatement();
            
            ResultSet results = statement.executeQuery("select * from Seguros where matricula = '" + matricula + "'");
            
            if (results.next()) result = SeguroMapper.toSeguro(results);
            
            statement.close();
            
        } catch (SQLException e) {
        	
            throw new DataAccessException();
        }
        
        return result;
    }
}