package es.unican.is2.practica6;

import java.util.List;

import fundamentos.Lectura;
import fundamentos.Mensaje;
import fundamentos.Menu;

/**
 * Interfaz de usuario de la aplicacion refactorizada.
 * Se mantiene el uso de la libreria docente fundamentos.jar.
 *
 * METRICAS FINALES:
 * Criterio WMC: cada constructor/metodo con cuerpo parte de 1. Se suma 1 por
 * cada if, bucle, catch o case de switch. En expresiones booleanas complejas
 * dentro de una estructura de control se suma 1 por cada booleano adicional.
 * Criterio CCog: se suma 1 por cada estructura de control, mas anidamiento.
 * else/else if suman 1. Cada secuencia de && u || suma 1.
 *
 * Calculo WMC:
 * - main(...): 1
 * - GestionTransportesGUI(): 1
 * - GestionTransportesGUI(GestionTransportes): 1
 * - creaMenu(): 1
 * - ejecutar(): 1 + while = 2
 * - ejecutaOpcion(...): 1 + 4 cases del switch = 5
 * - anhadeConductor(): 1 + if(alta duplicada) = 2
 * - anhadeTransporte(): 1 + if(conductor inexistente) + catch(error de transporte) = 3
 * - muestraSueldo(): 1 + if(conductor inexistente) = 2
 * - muestraMejorConductor(): 1 + if(lista vacia) + for(impresion) = 3
 * - mensaje(...): 1
 * Total WMC = 22; WMCn = 22 / 11 = 2.00
 *
 * Calculo CCog:
 * - ejecutar(): while (+1) = 1
 * - ejecutaOpcion(...): switch (+1) = 1
 * - anhadeConductor(): if (+1) = 1
 * - anhadeTransporte(): if (+1) + catch (+1) = 2
 * - muestraSueldo(): if (+1) + else (+1) = 2
 * - muestraMejorConductor(): if (+1) + else (+1) + for anidado en else (+2) = 4
 * - resto de metodos: 0
 * Total CCog = 11; CCogn = 11 / 11 = 1.00
 *
 * CBO = 8: Menu; Lectura; Mensaje; GestionTransportes; Conductor; Transporte;
 * TransporteFactory; IllegalArgumentException.
 * DIT = 1; NOC = 0.
 */
// CBO = 8: Menu, Lectura, Mensaje, GestionTransportes, Conductor, Transporte, TransporteFactory e IllegalArgumentException.
// DIT = 1; NOC = 0.
public class GestionTransportesGUI {

    private static final int ANHADE_CONDUCTOR = 0;
    
    private static final int ANHADE_TRANSPORTE = 1;
    
    private static final int SUELDO_CONDUCTOR = 2;
    
    private static final int MEJOR_CONDUCTOR = 3;

    private final GestionTransportes gestionTransportes;
    
    private final Menu menu;

    public static void main(String[] args) { // WMC +1
    	
        new GestionTransportesGUI().ejecutar();
    }

    public GestionTransportesGUI() { // WMC +1
    	
        this(new GestionTransportes());
    }

    GestionTransportesGUI(GestionTransportes gestionTransportes) { // WMC +1
    	
        this.gestionTransportes = gestionTransportes;
        
        this.menu = creaMenu();
    }

    private static Menu creaMenu() { // WMC +1
    	
        Menu menu = new Menu("Transportes");
        
        menu.insertaOpcion("Anhade conductor", ANHADE_CONDUCTOR);
        
        menu.insertaOpcion("Anhade transporte", ANHADE_TRANSPORTE);
        
        menu.insertaOpcion("Sueldo conductor", SUELDO_CONDUCTOR);
        
        menu.insertaOpcion("Mejor conductor", MEJOR_CONDUCTOR);
        
        return menu;
    }

    public void ejecutar() { // WMC +1
    	
        while (true) { // WMC +1; CCog +1
        	
            ejecutaOpcion(menu.leeOpcion());
        }
    }

    private void ejecutaOpcion(int opcion) { // WMC +1
    	
        switch (opcion) { // WMC +4 cases; CCog +1
        
            case ANHADE_CONDUCTOR -> anhadeConductor();
            
            case ANHADE_TRANSPORTE -> anhadeTransporte();
            
            case SUELDO_CONDUCTOR -> muestraSueldo();
            
            case MEJOR_CONDUCTOR -> muestraMejorConductor();
            
            default -> mensaje("ERROR", "Opcion no valida");
        }
    }

    private void anhadeConductor() { // WMC +1
    	
        Lectura lect = new Lectura("Datos Conductor");
        
        lect.creaEntrada("DNI", "");
        
        lect.creaEntrada("Nombre", "");
        
        lect.creaEntrada("Apellido1", "");
        
        lect.creaEntrada("Apellido2", "");
        
        lect.creaEntrada("Direccion", "");
        
        lect.esperaYCierra();

        String dni = lect.leeString("DNI");
        
        String nombre = lect.leeString("Nombre");
        
        String apellido1 = lect.leeString("Apellido1");
        
        String apellido2 = lect.leeString("Apellido2");
        
        String direccion = lect.leeString("Direccion");

        if (!gestionTransportes.anhadeConductor(dni, nombre, apellido1, apellido2, direccion)) { // WMC +1; CCog +1
        	
            mensaje("ERROR", "Ya existe un conductor con DNI " + dni);
        }
    }

    private void anhadeTransporte() { // WMC +1
    	
        Lectura lect = new Lectura("Nuevo transporte");
        
        lect.creaEntrada("DNI", "");
        
        lect.creaEntrada("Tipo Transporte: P | M | MP", "");
        
        lect.creaEntrada("Horas", 0);
        
        lect.creaEntrada("Personas", 0);
        
        lect.creaEntrada("Toneladas", 0);
        
        lect.esperaYCierra();

        String dni = lect.leeString("DNI");
        
        Conductor conductor = gestionTransportes.buscaConductor(dni);
        
        if (conductor == null) { // WMC +1; CCog +1
        	
            mensaje("ERROR", "No existe un conductor con DNI " + dni);
            
            return;
        }

        try {
        	
            String tipo = lect.leeString("Tipo Transporte: P | M | MP");
            
            int horas = lect.leeInt("Horas");
            
            int personas = lect.leeInt("Personas");
            
            int toneladas = lect.leeInt("Toneladas");
            
            Transporte transporte = TransporteFactory.creaDesdeCodigo(tipo, horas, personas, toneladas);
            
            conductor.anhadeTransporte(transporte);
            
        } catch (IllegalArgumentException e) { // WMC +1; CCog +1
        	
            mensaje("ERROR", e.getMessage());
        }
    }

    private void muestraSueldo() { // WMC +1
    	
        Lectura lect = new Lectura("Sueldo conductor");
        
        lect.creaEntrada("DNI", "");
        
        lect.esperaYCierra();

        String dni = lect.leeString("DNI");
        
        Conductor conductor = gestionTransportes.buscaConductor(dni);
        
        if (conductor == null) { // WMC +1; CCog +1
        	
            mensaje("ERROR", "No existe un conductor con DNI " + dni);
            
        } else { // CCog +1
        	
            mensaje("Sueldo", "El sueldo del conductor es: " + conductor.sueldo());
        }
    }

    private void muestraMejorConductor() { // WMC +1
    	
        List<Conductor> mejores = gestionTransportes.mejoresConductores();
        
        String msj = "";
        
        if (mejores.isEmpty()) { // WMC +1; CCog +1
        	
            msj = "No hay conductores";
            
        } else { // CCog +1
        	
            for (Conductor conductor : mejores) { // WMC +1; CCog +2
            	
                msj += conductor.getNombre() + " " + conductor.getApellido1() + "\n";
            }
        }
        
        mensaje("MEJOR CONDUCTOR", msj);
    }

    private static void mensaje(String titulo, String txt) { // WMC +1
    	
        Mensaje msj = new Mensaje(titulo);
        
        msj.escribe(txt);
    }
}
