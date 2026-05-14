package es.unican.is2.practica6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Conductor de la empresa de transportes.
 *
 * METRICAS FINALES:
 * Criterio WMC: cada constructor/metodo con cuerpo parte de 1. Se suma 1 por
 * cada if, bucle, catch o case de switch. En expresiones booleanas complejas
 * dentro de una estructura de control se suma 1 por cada booleano adicional.
 * Criterio CCog: se suma 1 por cada estructura de control, mas anidamiento.
 * else/else if suman 1. Cada secuencia de && u || suma 1.
 *
 * Calculo WMC:
 * - Conductor(...): 1
 * - validaDatosObligatorios(...): 1 + if datos nulos (1 + 3 booleanos adicionales por ||) = 5
 * - dni(), getDni(), getNombre(), getApellido1(), apellido2(), getDire(): 6 * 1 = 6
 * - sueldo(): 1
 * - anhadeTransporte(...): 1 + if(transporte nulo) = 2
 * - transportes(): 1
 * Total WMC = 16; WMCn = 16 / 11 = 1.45
 *
 * Calculo CCog:
 * - validaDatosObligatorios(...): if (+1) + secuencia || (+1) = 2
 * - anhadeTransporte(...): if (+1) = 1
 * - resto de metodos: 0
 * Total CCog = 3; CCogn = 3 / 11 = 0.27
 *
 * CBO = 2: Transporte; IllegalArgumentException.
 * DIT = 1; NOC = 0.
 */
// CBO = 2: Transporte e IllegalArgumentException.
// DIT = 1; NOC = 0.
public class Conductor {

    private static final double SUELDO_BASE = 700.0;

    private final List<Transporte> transportes = new ArrayList<>();
    private final String dni;
    private final String nombre;
    private final String apellido1;
    private final String apellido2;
    private final String dire;

    public Conductor(String dni, String nombre, String apellido1,
            String apellido2, String direccion) { // WMC +1
        validaDatosObligatorios(dni, nombre, apellido1, direccion);
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dire = direccion;
    }

    private static void validaDatosObligatorios(String dni, String nombre, String apellido1, String direccion) { // WMC +1
        if (dni == null || nombre == null || apellido1 == null || direccion == null) { // WMC +4; CCog +2
            throw new IllegalArgumentException("DNI, nombre, primer apellido y direccion son obligatorios");
        }
    }

    public String dni() { // WMC +1
        return dni;
    }

    public String getDni() { // WMC +1
        return dni;
    }

    public String getNombre() { // WMC +1
        return nombre;
    }

    public String getApellido1() { // WMC +1
        return apellido1;
    }

    public String apellido2() { // WMC +1
        return apellido2;
    }

    public String getDire() { // WMC +1
        return dire;
    }

    public double sueldo() { // WMC +1
        return SUELDO_BASE + transportes.stream()
                .mapToDouble(Transporte::importeSalarial)
                .sum();
    }

    public void anhadeTransporte(Transporte transporte) { // WMC +1
        if (transporte == null) { // WMC +1; CCog +1
            throw new IllegalArgumentException("El transporte no puede ser nulo");
        }
        transportes.add(transporte);
    }

    public List<Transporte> transportes() { // WMC +1
        return Collections.unmodifiableList(transportes);
    }
}
