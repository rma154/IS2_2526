package es.unican.is2.practica6;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TransporteFactoryTest {

    @Test
    public void testCreaDesdeCodigo() {
        assertInstanceOf(TransportePersonas.class, TransporteFactory.creaDesdeCodigo("P", 1, 1, 0));
        assertInstanceOf(TransporteMercancias.class, TransporteFactory.creaDesdeCodigo("M", 1, 0, 1));
        assertInstanceOf(TransporteMercanciasPeligrosas.class, TransporteFactory.creaDesdeCodigo("MP", 1, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> TransporteFactory.creaDesdeCodigo("X", 1, 1, 1));
    }
}
