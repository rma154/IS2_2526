package es.unican.is2.practica2.business.api;

import es.unican.is2.practica2.common.DataAccessException;
import es.unican.is2.practica2.common.OperacionNoValida;
import es.unican.is2.practica2.domain.Cliente;

public interface IGestionClientes {

    Cliente nuevoCliente(Cliente c) throws DataAccessException;

    Cliente bajaCliente(String dni) throws OperacionNoValida, DataAccessException;
}