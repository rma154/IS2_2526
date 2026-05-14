package es.unican.is2.practica6;

/**
 * Transporte de mercancias peligrosas: reutiliza el calculo por toneladas y suma un fijo.
 *
 * METRICAS FINALES:
 * Calculo WMC:
 * - TransporteMercanciasPeligrosas(...): 1
 * - extraAdicional(): 1
 * Total WMC = 2; WMCn = 2 / 2 = 1.00
 *
 * Calculo CCog:
 * - no contiene estructuras de control.
 * Total CCog = 0; CCogn = 0 / 2 = 0.00
 *
 * CBO = 1: TransporteMercancias.
 * DIT = 3; NOC = 0.
 */
// CBO = 1: TransporteMercancias.
// DIT = 3; NOC = 0.
public class TransporteMercanciasPeligrosas extends TransporteMercancias {

    private static final double EXTRA_FIJO_MERCANCIA_PELIGROSA = 50.0;

    public TransporteMercanciasPeligrosas(double horas, int toneladas) { // WMC +1
        super(horas, toneladas);
    }

    @Override
    protected double extraAdicional() { // WMC +1
        return super.extraAdicional() + EXTRA_FIJO_MERCANCIA_PELIGROSA;
    }
}
