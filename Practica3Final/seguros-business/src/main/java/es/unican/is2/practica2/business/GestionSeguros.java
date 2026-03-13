package es.unican.is2.practica2.business;

import es.unican.is2.practica2.business.api.IGestionClientes;
import es.unican.is2.practica2.business.api.IGestionSeguros;
import es.unican.is2.practica2.business.api.IInfoSeguros;
import es.unican.is2.practica2.common.DataAccessException;
import es.unican.is2.practica2.common.OperacionNoValida;
import es.unican.is2.practica2.dao.api.IClientesDAO;
import es.unican.is2.practica2.dao.api.ISegurosDAO;
import es.unican.is2.practica2.domain.Cliente;
import es.unican.is2.practica2.domain.Seguro;

public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros {

    private final IClientesDAO daoClientes;
    private final ISegurosDAO daoSeguros;

    public GestionSeguros(IClientesDAO daoClientes, ISegurosDAO daoSeguros) {
        this.daoClientes = daoClientes;
        this.daoSeguros = daoSeguros;
    }

    // --- IGestionClientes ---

    public Cliente nuevoCliente(Cliente c) throws DataAccessException {
        if (c == null || c.getDni() == null) throw new IllegalArgumentException("Cliente/DNI null");
        if (daoClientes.cliente(c.getDni()) != null) return null; // ya existe
        return daoClientes.creaCliente(c);
    }

    public Cliente bajaCliente(String dni) throws OperacionNoValida, DataAccessException {
        Cliente c = daoClientes.cliente(dni);
        if (c == null) return null;
        if (c.getSeguros() != null && !c.getSeguros().isEmpty()) {
            throw new OperacionNoValida("El cliente tiene seguros a su nombre");
        }
        return daoClientes.eliminaCliente(dni);
    }

    // --- IGestionSeguros ---

    public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
        if (s == null || s.getMatricula() == null) throw new IllegalArgumentException("Seguro/matrícula null");

        Cliente c = daoClientes.cliente(dni);
        if (c == null) return null;

        if (daoSeguros.seguroPorMatricula(s.getMatricula()) != null) {
            throw new OperacionNoValida("Ya existe un seguro para esa matrícula");
        }

        s.setDniCliente(dni);
        daoSeguros.creaSeguro(s);

        // devuelve el persistido (con id)
        return daoSeguros.seguroPorMatricula(s.getMatricula());
    }

    public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {
        Seguro s = daoSeguros.seguroPorMatricula(matricula);
        if (s == null) return null;

        Cliente c = daoClientes.cliente(dni);
        if (c == null) return null;

        if (s.getDniCliente() != null && !dni.equals(s.getDniCliente())) {
            throw new OperacionNoValida("El seguro no pertenece al cliente indicado");
        }

        return daoSeguros.eliminaSeguro(s.getId());
    }

    public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
        Seguro s = daoSeguros.seguroPorMatricula(matricula);
        if (s == null) return null;

        s.setConductorAdicional(conductor);
        return daoSeguros.actualizaSeguro(s);
    }

    // --- IInfoSeguros ---

    public Cliente cliente(String dni) throws DataAccessException {
        return daoClientes.cliente(dni);
    }

    public Seguro seguro(String matricula) throws DataAccessException {
        return daoSeguros.seguroPorMatricula(matricula);
    }
}