package es.unican.is2.practica2.dao.api;

import java.util.List;

import es.unican.is2.practica2.common.DataAccessException;
import es.unican.is2.practica2.domain.Cliente;

public interface IClientesDAO {

    Cliente creaCliente(Cliente c) throws DataAccessException;

    Cliente cliente(String dni) throws DataAccessException;

    Cliente actualizaCliente(Cliente nuevo) throws DataAccessException;

    Cliente eliminaCliente(String dni) throws DataAccessException;

    List<Cliente> clientes() throws DataAccessException;
}