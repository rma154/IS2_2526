package es.unican.is2.practica6;

/* Clase que representa un transporte realizado por un conductor. */
/**
 * METRICAS INICIALES:
 * Criterio WMC: cada constructor/metodo con cuerpo parte de 1. Se suma 1 por
 * cada if, bucle, catch o case de switch. En expresiones booleanas complejas
 * dentro de una estructura de control se suma 1 por cada booleano adicional.
 * Criterio CCog: se suma 1 por cada estructura de control, mas anidamiento.
 * else/else if suman 1. Cada secuencia de && u || suma 1.
 *
 * Calculo WMC:
 * - Transporte(...): 1 + if invalido (1 + 2 booleanos adicionales por ||) + if categoria Personas = 5
 * - horas(): 1
 * - categoria(): 1
 * - ton(): 1
 * - getPersonas(): 1
 * Total WMC = 9; WMCn = 9 / 5 = 1.80
 *
 * Calculo CCog:
 * - Transporte(...): if invalido (+1) + secuencia || (+1) + if categoria (+1) + else (+1) = 4
 * - resto de metodos: 0
 * Total CCog = 4; CCogn = 4 / 5 = 0.80
 *
 * CBO = 2: CategoriaTransporte; IllegalArgumentException.
 * DIT = 1; NOC = 0.
 */
// CBO = 2: CategoriaTransporte e IllegalArgumentException.
// DIT = 1; NOC = 0.
public class Transporte {

    private double horas;
    private int ton;
    private int personas;
    private CategoriaTransporte cat;

    /**
     * Constructor de la clase Transporte
     * @param horas Horas que ha durado el transporte
     * @param cat Categoria del transporte
     * @param valor En caso de ser un transporte de tipo Personas,
     * representa el numero de personas, en caso de ser de tipo Mercancias
     * representa las toneladas
     */
    public Transporte(double horas, CategoriaTransporte cat, int valor) throws IllegalArgumentException { // WMC +1
        if (horas <= 0 || valor <= 0 || cat == null) { // WMC +3; CCog +2
            throw new IllegalArgumentException();
        }
        this.horas = horas;
        this.cat = cat;
        if (cat.equals(CategoriaTransporte.Personas)) { // WMC +1; CCog +1
            this.personas = valor;
        } else  { // CCog +1
            this.ton = valor;
        }
    }

    public double horas() { // WMC +1
        return horas;
    }

    public CategoriaTransporte categoria() { // WMC +1
        return cat;
    }

    public int ton() { // WMC +1
        return ton;
    }

    public int getPersonas() { // WMC +1
        return personas;
    }

}
