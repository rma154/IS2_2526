package es.unican.is2.practica6;

import java.util.LinkedList;
import java.util.List;
import fundamentos.*;

/**
 * Gestion de una empresa de transportes.
 *
 * METRICAS INICIALES:
 * Criterio WMC: cada constructor/metodo con cuerpo parte de 1. Se suma 1 por
 * cada if, bucle, catch o case de switch. En expresiones booleanas complejas
 * dentro de una estructura de control se suma 1 por cada booleano adicional.
 * Criterio CCog: se suma 1 por cada estructura de control, mas anidamiento.
 * else/else if suman 1. Cada secuencia de && u || suma 1.
 *
 * Calculo WMC:
 * - main(...): 1 + while + 4 cases del switch principal + if anhadeConductor
 *   + if c != null al anhadir transporte + 3 cases del switch de tipo
 *   + if c != null de sueldo + for de mejores + if mayor sueldo + else-if empate
 *   + if resultado vacio + for de impresion = 17
 * - mensaje(...): 1
 * Total WMC = 18; WMCn = 18 / 2 = 9.00
 *
 * Calculo CCog:
 * - main(...): while (+1), switch principal anidado (+2), if alta conductor (+3),
 *   if conductor existe al anhadir transporte (+3), else conductor no existe (+1),
 *   switch de tipo anidado (+4), if conductor existe en sueldo (+3), else sueldo (+1),
 *   for mejores (+3), if mayor sueldo (+4), else-if empate (+1),
 *   if resultado vacio (+3), else resultado no vacio (+1), for impresion anidado (+4) = 34
 * - mensaje(...): 0
 * Total CCog = 34; CCogn = 34 / 2 = 17.00
 *
 * CBO = 7: Menu; Lectura; Mensaje; gestionTransportes; Conductor; Transporte; CategoriaTransporte.
 * DIT = 1; NOC = 0.
 */
// CBO = 7: Menu, Lectura, Mensaje, gestionTransportes, Conductor, Transporte y CategoriaTransporte.
// DIT = 1; NOC = 0.
public class GestionTransportesGUI {

    /**
     * Programa principal basado en menu.
     */
    public static void main(String[] args) { // WMC +1
        // opciones del menu
        final int ANHADE_CONDUCTOR = 0, ANHADE_TRANSPORTE = 1,
        SUELDO_CONDUCTOR = 2, MEJOR_CONDUCTOR = 3;

        // variables auxiliares
        String dni;
        Lectura lect;
        Conductor c;

        // crea la empresa de transportes
        gestionTransportes gt = new gestionTransportes();
        // crea la ventana de menu
        Menu menu = new Menu("Transportes");
        menu.insertaOpcion("Anhade conductor", ANHADE_CONDUCTOR);
        menu.insertaOpcion("Anhade transporte", ANHADE_TRANSPORTE);
        menu.insertaOpcion("Sueldo conductor", SUELDO_CONDUCTOR);
        menu.insertaOpcion("Mejor conductor", MEJOR_CONDUCTOR);

        int opcion;

        // lazo de espera de comandos del usuario
        while(true) { // WMC +1; CCog +1
            opcion = menu.leeOpcion();

            // realiza las acciones dependiendo de la opcion elegida
            switch (opcion) { // WMC +4 cases; CCog +2 por estar dentro del while
            case  ANHADE_CONDUCTOR:
                lect = new Lectura("Datos Conductor");
                lect.creaEntrada("DNI", "");
                lect.creaEntrada("Nombre","");
                lect.creaEntrada("Apellido1", "");
                lect.creaEntrada("Apellido2", "");
                lect.creaEntrada("Direccion", "");
                lect.esperaYCierra();
                dni = lect.leeString("DNI");
                String nombre = lect.leeString("Nombre");
                String apellido1 = lect.leeString("Apellido1");
                String apellido2 = lect.leeString("Apellido2");
                String direccion = lect.leeString("Direccion");
                // Anhade el conductor
                if (!gt.anhadeConductor(dni, nombre, apellido1, apellido2, direccion)) { // WMC +1; CCog +3
                    mensaje("ERROR", "Ya existe un conductor con DNI "+dni);
                }
                break;

            case ANHADE_TRANSPORTE:
                lect = new Lectura("Nuevo transporte");
                lect.creaEntrada("DNI", "");
                lect.creaEntrada("Tipo Transporte: P | M | MP", "");
                lect.creaEntrada("Horas", 0);
                lect.creaEntrada("Personas", 0);
                lect.creaEntrada("Toneladas", 0);
                lect.esperaYCierra();
                dni = lect.leeString("DNI");
                String tipo = lect.leeString("Tipo Transporte: P | M | MP");
                int horas = lect.leeInt("Horas");
                int personas = lect.leeInt("Personas");
                int toneladas = lect.leeInt("Toneladas");

                Transporte t = null;
                c = gt.buscaConductor(dni);
                if (c!=null) { // WMC +1; CCog +3
                    switch (tipo) { // WMC +3 cases; CCog +4
                        case "P":
                            t = new Transporte(horas,CategoriaTransporte.Personas, personas);
                            c.anhadeTransporte(t);
                            break;
                        case "M":
                            t = new Transporte(horas, CategoriaTransporte.Mercancias, toneladas);
                            c.anhadeTransporte(t);
                            break;
                        case "MP":
                            t = new Transporte(horas, CategoriaTransporte.MercanciasPeligrosas, toneladas);
                            c.anhadeTransporte(t);
                            break;
                    }
                } else { // CCog +1
                    mensaje("ERROR", "No existe un conductor con DNI "+dni);
                }
                break;

            case SUELDO_CONDUCTOR:
                lect = new Lectura("Transportes Peligrosos");
                lect.creaEntrada("DNI", "");
                lect.esperaYCierra();
                dni = lect.leeString("DNI");
                c = gt.buscaConductor(dni);
                if (c!=null){ // WMC +1; CCog +3
                    mensaje("Sueldo", "El sueldo del conductor es: "+c.sueldo());
                } else { // CCog +1
                    mensaje("ERROR", "No existe un conductor con DNI "+dni);
                }
                break;

            case MEJOR_CONDUCTOR:
                List<Conductor> resultado = new LinkedList<Conductor>();
                double maxSueldo = 0.0;
                for (Conductor conductor : gt.conductores()) { // WMC +1; CCog +3
                    if (conductor.sueldo() > maxSueldo) { // WMC +1; CCog +4
                        maxSueldo = conductor.sueldo();
                        resultado.clear();
                        resultado.add(conductor);
                    } else if (conductor.sueldo() == maxSueldo) { // WMC +1; CCog +1
                        resultado.add(conductor);
                    }
                }
                String msj = "";
                if (resultado.size() == 0) { // WMC +1; CCog +3
                    msj = "No hay conductores";
                } else { // CCog +1
                    for (Conductor conductor : resultado) { // WMC +1; CCog +4
                        msj += conductor.getNombre() + " "+conductor.getNombre()+"\n";
                    }
                }
                mensaje("MEJOR CONDUCTOR", msj);
                break;
            }
        }
    }

    /**
     * Metodo auxiliar que muestra un ventana de mensaje.
     * @param titulo titulo de la ventana
     * @param txt texto contenido en la ventana
     */
    private static void mensaje(String titulo, String txt) { // WMC +1
        Mensaje msj = new Mensaje(titulo);
        msj.escribe(txt);

    }

}
