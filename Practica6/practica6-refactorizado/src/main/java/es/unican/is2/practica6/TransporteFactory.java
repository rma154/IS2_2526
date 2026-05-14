package es.unican.is2.practica6;

/**
 * Factoria que traduce los codigos usados por la interfaz a objetos de dominio.
 *
 * METRICAS FINALES:
 * Criterio WMC: cada constructor/metodo con cuerpo parte de 1. Se suma 1 por
 * cada if, bucle, catch o case de switch. En switch se cuentan los case, no el default.
 * Criterio CCog: switch suma 1 con independencia del numero de cases.
 *
 * Calculo WMC:
 * - TransporteFactory(): 1
 * - creaDesdeCodigo(...): 1 + 3 cases del switch expression = 4
 * Total WMC = 5; WMCn = 5 / 2 = 2.50
 *
 * Calculo CCog:
 * - creaDesdeCodigo(...): switch (+1) = 1
 * - constructor: 0
 * Total CCog = 1; CCogn = 1 / 2 = 0.50
 *
 * CBO = 5: Transporte; TransportePersonas; TransporteMercancias;
 * TransporteMercanciasPeligrosas; IllegalArgumentException.
 * DIT = 1; NOC = 0.
 */
// CBO = 5: Transporte, TransportePersonas, TransporteMercancias, TransporteMercanciasPeligrosas e IllegalArgumentException.
// DIT = 1; NOC = 0.
public final class TransporteFactory {

    private TransporteFactory() { // WMC +1
        // Clase de utilidad.
    }

    public static Transporte creaDesdeCodigo(String tipo, double horas, int personas, int toneladas) { // WMC +1
        return switch (tipo) { // WMC +3 cases; CCog +1
            case "P" -> new TransportePersonas(horas, personas);
            case "M" -> new TransporteMercancias(horas, toneladas);
            case "MP" -> new TransporteMercanciasPeligrosas(horas, toneladas);
            default -> throw new IllegalArgumentException("Tipo de transporte no soportado: " + tipo);
        };
    }
}
