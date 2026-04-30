package es.unican.is2.practica2.business.api;

import es.unican.is2.practica2.common.DataAccessException;
import es.unican.is2.practica2.domain.Cliente;
import es.unican.is2.practica2.domain.Seguro;

public interface IInfoSeguros {

    Cliente cliente(String dni) throws DataAccessException;

    Seguro seguro(String matricula) throws DataAccessException;
}