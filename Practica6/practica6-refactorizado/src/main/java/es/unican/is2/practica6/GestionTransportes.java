package es.unican.is2.practica6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio de aplicacion que gestiona el conjunto de conductores.
 *
 * METRICAS FINALES:
 * Criterio WMC: cada constructor/metodo con cuerpo parte de 1. Se suma 1 por
 * cada if, bucle, catch o case de switch. En expresiones booleanas complejas
 * dentro de una estructura de control se suma 1 por cada booleano adicional.
 * Criterio CCog: se suma 1 por cada estructura de control, mas anidamiento.
 * else/else if suman 1. Cada secuencia de && u || suma 1.
 *
 * Calculo WMC:
 * - buscaConductor(...): 1
 * - anhadeConductor(...): 1 + if(dni duplicado) = 2
 * - conductores(): 1
 * - mejoresConductores(): 1 + if(no hay conductores) = 2
 * Total WMC = 6; WMCn = 6 / 4 = 1.50
 *
 * Calculo CCog:
 * - anhadeConductor(...): if (+1) = 1
 * - mejoresConductores(...): if (+1) = 1
 * - resto de metodos: 0
 * Total CCog = 2; CCogn = 2 / 4 = 0.50
 *
 * CBO = 1: Conductor.
 * DIT = 1; NOC = 0.
 */
// CBO = 1: Conductor.
// DIT = 1; NOC = 0.
public class GestionTransportes {

    private final Map<String, Conductor> conductoresPorDni = new LinkedHashMap<>();

    public Conductor buscaConductor(String dni) { // WMC +1
        return conductoresPorDni.get(dni);
    }

    public boolean anhadeConductor(String dni, String nombre, String apellido1, String apellido2, String direccion) { // WMC +1
        if (conductoresPorDni.containsKey(dni)) { // WMC +1; CCog +1
            return false;
        }
        conductoresPorDni.put(dni, new Conductor(dni, nombre, apellido1, apellido2, direccion));
        return true;
    }

    public List<Conductor> conductores() { // WMC +1
        return Collections.unmodifiableList(new ArrayList<>(conductoresPorDni.values()));
    }

    public List<Conductor> mejoresConductores() { // WMC +1
        if (conductoresPorDni.isEmpty()) { // WMC +1; CCog +1
            return Collections.emptyList();
        }

        double maxSueldo = conductoresPorDni.values().stream()
                .mapToDouble(Conductor::sueldo)
                .max()
                .getAsDouble();

        return conductoresPorDni.values().stream()
                .filter(conductor -> Double.compare(conductor.sueldo(), maxSueldo) == 0)
                .toList();
    }
}
