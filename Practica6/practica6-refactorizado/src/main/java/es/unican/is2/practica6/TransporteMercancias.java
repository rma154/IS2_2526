package es.unican.is2.practica6;

/**
 * Transporte de mercancias no peligrosas.
 *
 * METRICAS FINALES:
 * Calculo WMC:
 * - TransporteMercancias(...): 1
 * - validaToneladas(...): 1 + if(toneladas invalidas) = 2
 * - ton(): 1
 * - extraAdicional(): 1
 * Total WMC = 5; WMCn = 5 / 4 = 1.25
 *
 * Calculo CCog:
 * - validaToneladas(...): if (+1) = 1
 * - resto de metodos: 0
 * Total CCog = 1; CCogn = 1 / 4 = 0.25
 *
 * CBO = 2: Transporte; IllegalArgumentException.
 * DIT = 2; NOC = 1.
 */
// CBO = 2: Transporte e IllegalArgumentException.
// DIT = 2; NOC = 1.
public class TransporteMercancias extends Transporte {

    private static final double EXTRA_POR_TONELADA = 2.0;

    private final int toneladas;

    public TransporteMercancias(double horas, int toneladas) { // WMC +1
        super(horas);
        validaToneladas(toneladas);
        this.toneladas = toneladas;
    }

    private static void validaToneladas(int toneladas) { // WMC +1
        if (toneladas <= 0) { // WMC +1; CCog +1
            throw new IllegalArgumentException("Las toneladas deben ser positivas");
        }
    }

    public int ton() { // WMC +1
        return toneladas;
    }

    @Override
    protected double extraAdicional() { // WMC +1
        return toneladas * EXTRA_POR_TONELADA;
    }
}
