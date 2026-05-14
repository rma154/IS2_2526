package es.unican.is2.practica6;

/**
 * Abstraccion comun de cualquier transporte realizado por un conductor.
 *
 * METRICAS FINALES:
 * Criterio WMC: cada constructor/metodo con cuerpo parte de 1. Se suma 1 por
 * cada if, bucle, catch o case de switch. Los metodos abstractos sin cuerpo no se cuentan.
 * Criterio CCog: se suma 1 por cada estructura de control, mas anidamiento.
 * else/else if suman 1. Cada secuencia de && u || suma 1.
 *
 * Calculo WMC:
 * - Transporte(...): 1 + if(horas invalidas) = 2
 * - horas(): 1
 * - importeSalarial(): 1
 * Total WMC = 4; WMCn = 4 / 3 = 1.33
 *
 * Calculo CCog:
 * - Transporte(...): if (+1) = 1
 * - resto de metodos: 0
 * Total CCog = 1; CCogn = 1 / 3 = 0.33
 *
 * CBO = 1: IllegalArgumentException.
 * DIT = 1; NOC = 2.
 */
// CBO = 1: IllegalArgumentException.
// DIT = 1; NOC = 2.
public abstract class Transporte {

    private static final double EXTRA_BASICO_HORA = 5.0;

    private final double horas;

    protected Transporte(double horas) { // WMC +1
        if (horas <= 0) { // WMC +1; CCog +1
            throw new IllegalArgumentException("Las horas deben ser positivas");
        }
        this.horas = horas;
    }

    public double horas() { // WMC +1
        return horas;
    }

    public double importeSalarial() { // WMC +1
        return horas * EXTRA_BASICO_HORA + extraAdicional();
    }

    protected abstract double extraAdicional();
}
