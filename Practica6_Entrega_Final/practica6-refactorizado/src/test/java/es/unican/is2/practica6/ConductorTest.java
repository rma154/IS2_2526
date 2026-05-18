package es.unican.is2.practica6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ConductorTest {

    private static Conductor sut;

    @Test
    public void testConstructor() {
    	
        sut = new Conductor("123123123X", "Pepe", "Martinez", "Fernandez", "Avda. de los Castros s/n");
        
        assertEquals("123123123X", sut.dni());
        
        assertEquals("123123123X", sut.getDni());
        
        assertEquals("Pepe", sut.getNombre());
        
        assertEquals("Martinez", sut.getApellido1());
        
        assertEquals("Fernandez", sut.apellido2());
        
        assertEquals("Avda. de los Castros s/n", sut.getDire());

        sut = new Conductor("123123123X", "Pepe", "Martinez", null, "Avda. de los Castros s/n");
        
        assertEquals("123123123X", sut.dni());
        
        assertEquals("123123123X", sut.getDni());
        
        assertEquals("Pepe", sut.getNombre());
        
        assertEquals("Martinez", sut.getApellido1());
        
        assertNull(sut.apellido2());
        
        assertEquals("Avda. de los Castros s/n", sut.getDire());

        assertThrows(IllegalArgumentException.class,
                () -> new Conductor(null, "Pepe", "Martinez", "Fernandez", "Avda. de los Castros s/n"));
        
        assertThrows(IllegalArgumentException.class,
                () -> new Conductor("123123123X", null, "Martinez", "Fernandez", "Avda. de los Castros s/n"));
        
        assertThrows(IllegalArgumentException.class,
                () -> new Conductor("123123123X", "Pepe", null, "Fernandez", "Avda. de los Castros s/n"));
        
        assertThrows(IllegalArgumentException.class,
                () -> new Conductor("123123123X", "Pepe", "Martinez", "Fernandez", null));
    }

    @Test
    public void testSueldoYAnhadeTransporte() {
    	
        sut = new Conductor("123123123X", "Pepe", "Martinez", "Fernandez", "Avda. de los Castros s/n");

        assertEquals(700, sut.sueldo());
        
        assertTrue(sut.transportes().isEmpty());

        sut.anhadeTransporte(new TransportePersonas(1, 1));
        
        assertEquals(705.5, sut.sueldo());
        
        sut.anhadeTransporte(new TransportePersonas(10, 9));
        
        assertEquals(760.5, sut.sueldo());
        
        sut.anhadeTransporte(new TransportePersonas(1, 10));
        
        assertEquals(766.5, sut.sueldo());
        
        sut.anhadeTransporte(new TransportePersonas(10, 20));
        
        assertEquals(826.5, sut.sueldo());

        sut.anhadeTransporte(new TransporteMercancias(1, 1));
        
        assertEquals(833.5, sut.sueldo());
        
        sut.anhadeTransporte(new TransporteMercanciasPeligrosas(10, 100));
        
        assertEquals(1133.5, sut.sueldo());

        assertThrows(IllegalArgumentException.class, () -> sut.anhadeTransporte(null));
    }
}
