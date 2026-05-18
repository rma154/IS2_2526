package es.unican.is2.practica6;

import java.util.ArrayList;

/**
 * Clase que representa a un conductor, con sus datos personales
 * y los transportes que ha realizado.
 *
 * METRICAS INICIALES:
 * Criterio WMC: cada constructor/metodo con cuerpo parte de 1. Se suma 1 por
 * cada if, bucle, catch o case de switch. En expresiones booleanas complejas
 * dentro de una estructura de control se suma 1 por cada booleano adicional.
 * Criterio CCog: se suma 1 por cada estructura de control, mas anidamiento.
 * else/else if suman 1. Cada secuencia de && u || suma 1.
 *
 * Calculo WMC:
 * - Conductor(...): 1 + if datos obligatorios nulos (1 + 3 booleanos adicionales por ||) = 5
 * - dni(), getDni(), getNombre(), getApellido1(), apellido2(), getDire(): 6 * 1 = 6
 * - sueldo(): 1 + for + 3 case de switch + if(personas < 10) = 6
 * - anhadeTransporte(...): 1
 * Total WMC = 18; WMCn = 18 / 9 = 2.00
 *
 * Calculo CCog:
 * - Conductor(...): if (+1) + secuencia || (+1) = 2
 * - sueldo(): for (+1) + switch anidado en for (+2) + if anidado en for/switch (+3) + else (+1) = 7
 * - resto de metodos: 0
 * Total CCog = 9; CCogn = 9 / 9 = 1.00
 *
 * CBO = 3: Transporte; CategoriaTransporte; IllegalArgumentException.
 * DIT = 1; NOC = 0.
 */
// CBO = 3: Transporte, CategoriaTransporte e IllegalArgumentException.
// DIT = 1; NOC = 0.
public class Conductor {

    private ArrayList<Transporte> transportes = new ArrayList<Transporte>();
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String dire;

    public Conductor(String dni, String nombre, String apellido1,
            String apellido2, String direccion) { // WMC +1
        if (dni == null || nombre == null || apellido1 == null || direccion == null) { // WMC +4; CCog +2
            throw new IllegalArgumentException();
        }
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dire = direccion;
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
        double sueldoTransportes = 0;
        for (Transporte t : transportes) { // WMC +1; CCog +1
            double sueldoExtraTransporte = 0.0;
            switch (t.categoria()) { // WMC +3 cases; CCog +2 por estar dentro del for
                case Mercancias:
                    sueldoExtraTransporte = t.ton() * 2;
                    break;
                case MercanciasPeligrosas:
                    sueldoExtraTransporte = t.ton() * 2 + 50;
                    break;
                case Personas:
                    if (t.getPersonas() < 10) { // WMC +1; CCog +3 por estar dentro de for y switch
                        sueldoExtraTransporte = t.horas() * 0.5;
                    } else { // CCog +1
                        sueldoExtraTransporte = t.horas();
                    }
                    break;
            }
            sueldoTransportes += t.horas() * 5 + sueldoExtraTransporte;
        }
        return 700 + sueldoTransportes;
    }

    public void anhadeTransporte(Transporte t) { // WMC +1
        transportes.add(t);
    }

}
