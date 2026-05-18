package es.unican.is2.practica6;

import java.util.ArrayList;
import java.util.List;

/**
 * METRICAS INICIALES:
 * Criterio WMC: cada constructor/metodo con cuerpo parte de 1. Se suma 1 por
 * cada if, bucle, catch o case de switch. En expresiones booleanas complejas
 * dentro de una estructura de control se suma 1 por cada booleano adicional.
 * Criterio CCog: se suma 1 por cada estructura de control, mas anidamiento.
 * else/else if suman 1. Cada secuencia de && u || suma 1.
 *
 * Calculo WMC:
 * - buscaConductor(...): 1 + for + if = 3
 * - anhadeConductor(...): 1 + if = 2
 * - conductores(): 1
 * Total WMC = 6; WMCn = 6 / 3 = 2.00
 *
 * Calculo CCog:
 * - buscaConductor(...): for (+1) + if anidado en for (+2) = 3
 * - anhadeConductor(...): if (+1) = 1
 * - conductores(): 0
 * Total CCog = 4; CCogn = 4 / 3 = 1.33
 *
 * CBO = 1: Conductor.
 * DIT = 1; NOC = 0.
 */
// CBO = 1: Conductor.
// DIT = 1; NOC = 0.
public class gestionTransportes {

    private ArrayList<Conductor> cs = new ArrayList<Conductor>();

    public Conductor buscaConductor(String DNI) { // WMC +1
        for (Conductor c: cs) { // WMC +1; CCog +1
            if (c.dni().equals(DNI)) { // WMC +1; CCog +2 por estar dentro del for
                return c;
            }
        }

        return null;
    }

    public boolean anhadeConductor(String dni, String nombre, String apellido1, String apellido2, String direccion) { // WMC +1
        if (buscaConductor(dni) != null) { // WMC +1; CCog +1
            return false;
        }
        cs.add(new Conductor(dni, nombre, apellido1, apellido2,direccion));
        return true;
    }

    public List<Conductor> conductores() { // WMC +1
        return cs;
    }

}
