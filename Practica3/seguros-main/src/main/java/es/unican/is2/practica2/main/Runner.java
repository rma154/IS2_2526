package es.unican.is2.practica2.main;

import es.unican.is2.practica2.business.GestionSeguros;
import es.unican.is2.practica2.dao.api.IClientesDAO;
import es.unican.is2.practica2.dao.api.ISegurosDAO;
import es.unican.is2.practica2.dao.h2.ClientesDAO;
import es.unican.is2.practica2.dao.h2.SegurosDAO;
import es.unican.is2.practica2.gui.VistaAgente;

public class Runner {

    public static void main(String[] args) {
        IClientesDAO daoClientes = new ClientesDAO();
        ISegurosDAO daoSeguros = new SegurosDAO();
        GestionSeguros negocio = new GestionSeguros(daoClientes, daoSeguros);

        VistaAgente vista = new VistaAgente(negocio, negocio, negocio);
        vista.setVisible(true);
    }
}