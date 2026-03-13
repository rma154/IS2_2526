package es.unican.is2.practica2.dao.api;

import java.util.List;

import es.unican.is2.practica2.common.DataAccessException;
import es.unican.is2.practica2.domain.Seguro;

public interface ISegurosDAO {

    Seguro creaSeguro(Seguro v) throws DataAccessException;

    Seguro eliminaSeguro(long id) throws DataAccessException;

    Seguro actualizaSeguro(Seguro nuevo) throws DataAccessException;

    Seguro seguro(long id) throws DataAccessException;

    Seguro seguroPorMatricula(String matricula) throws DataAccessException;

    List<Seguro> seguros() throws DataAccessException;
}