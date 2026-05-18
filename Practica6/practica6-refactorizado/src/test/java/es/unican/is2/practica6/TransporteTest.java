package es.unican.is2.practica6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TransporteTest {

    @Test
    public void testConstructorTransporteMercancias() {
    	
        TransporteMercancias sut = new TransporteMercancias(1, 1);
        
        assertEquals(1, sut.horas());
        
        assertEquals(1, sut.ton());
        
        assertEquals(7, sut.importeSalarial());

        sut = new TransporteMercancias(10, 1000);
        
        assertEquals(10, sut.horas());
        
        assertEquals(1000, sut.ton());
        
        assertEquals(2050, sut.importeSalarial());

        assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(0, 1));
        
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(10, 0));
    }

    @Test
    public void testConstructorTransporteMercanciasPeligrosas() {
    	
        TransporteMercanciasPeligrosas sut = new TransporteMercanciasPeligrosas(10, 100);
        
        assertEquals(10, sut.horas());
        
        assertEquals(100, sut.ton());
        
        assertEquals(300, sut.importeSalarial());

        assertThrows(IllegalArgumentException.class, () -> new TransporteMercanciasPeligrosas(0, 1));
        
        assertThrows(IllegalArgumentException.class, () -> new TransporteMercanciasPeligrosas(10, 0));
    }

    @Test
    public void testConstructorTransportePersonas() {
    	
        TransportePersonas sut = new TransportePersonas(10, 10);
        
        assertEquals(10, sut.horas());
        
        assertEquals(10, sut.getPersonas());
        
        assertEquals(60, sut.importeSalarial());

        sut = new TransportePersonas(1, 1);
        
        assertEquals(1, sut.horas());
        
        assertEquals(1, sut.getPersonas());
        
        assertEquals(5.5, sut.importeSalarial());

        assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(0, 10));
        
        assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(10, 0));
    }
}
