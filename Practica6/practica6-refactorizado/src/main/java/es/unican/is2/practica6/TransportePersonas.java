package es.unican.is2.practica6;

/**
 * Transporte de personas. Si lleva 10 o mas personas, se considera colectivo.
 *
 * METRICAS FINALES:
 * Calculo WMC:
 * - TransportePersonas(...): 1
 * - validaPersonas(...): 1 + if(personas invalidas) = 2
 * - getPersonas(): 1
 * - esColectivo(): 1
 * - extraAdicional(): 1
 * - extraPorHora(): 1 + operador ternario = 2
 * Total WMC = 8; WMCn = 8 / 6 = 1.33
 *
 * Calculo CCog:
 * - validaPersonas(...): if (+1) = 1
 * - extraPorHora(): ternario (+1) = 1
 * - resto de metodos: 0
 * Total CCog = 2; CCogn = 2 / 6 = 0.33
 *
 * CBO = 2: Transporte; IllegalArgumentException.
 * DIT = 2; NOC = 0.
 */
// CBO = 2: Transporte e IllegalArgumentException.
// DIT = 2; NOC = 0.
public class TransportePersonas extends Transporte {

    private static final int PERSONAS_TRANSPORTE_COLECTIVO = 10;
    private static final double EXTRA_HORA_NO_COLECTIVO = 0.5;
    private static final double EXTRA_HORA_COLECTIVO = 1.0;

    private final int personas;

    public TransportePersonas(double horas, int personas) { // WMC +1
        super(horas);
        validaPersonas(personas);
        this.personas = personas;
    }

    private static void validaPersonas(int personas) { // WMC +1
        if (personas <= 0) { // WMC +1; CCog +1
            throw new IllegalArgumentException("El numero de personas debe ser positivo");
        }
    }

    public int getPersonas() { // WMC +1
        return personas;
    }

    public boolean esColectivo() { // WMC +1
        return personas >= PERSONAS_TRANSPORTE_COLECTIVO;
    }

    @Override
    protected double extraAdicional() { // WMC +1
        return horas() * extraPorHora();
    }

    private double extraPorHora() { // WMC +1
        return esColectivo() ? EXTRA_HORA_COLECTIVO : EXTRA_HORA_NO_COLECTIVO; // WMC +1; CCog +1
    }
}
