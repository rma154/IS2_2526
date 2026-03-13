package es.unican.is2.practica2.business.api;

import es.unican.is2.practica2.common.DataAccessException;
import es.unican.is2.practica2.common.OperacionNoValida;
import es.unican.is2.practica2.domain.Seguro;

public interface IGestionSeguros {

    Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException;

    Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException;

    Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException;
}