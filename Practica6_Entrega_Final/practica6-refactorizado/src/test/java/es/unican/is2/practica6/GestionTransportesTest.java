package es.unican.is2.practica6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class GestionTransportesTest {

    @Test
    public void testAnhadeYBuscaConductor() {
    	
        GestionTransportes gestion = new GestionTransportes();

        assertNull(gestion.buscaConductor("123123123X"));
        
        assertTrue(gestion.anhadeConductor("123123123X", "Pepe", "Martinez", "Fernandez", "Direccion"));
        
        assertFalse(gestion.anhadeConductor("123123123X", "Otro", "Martinez", "Fernandez", "Direccion"));
        
        assertEquals("Pepe", gestion.buscaConductor("123123123X").getNombre());
        
        assertEquals(1, gestion.conductores().size());
    }

    @Test
    public void testMejoresConductores() {
    	
        GestionTransportes gestion = new GestionTransportes();
        
        assertTrue(gestion.mejoresConductores().isEmpty());

        gestion.anhadeConductor("1", "Ana", "Garcia", null, "Direccion");
        
        gestion.anhadeConductor("2", "Luis", "Lopez", null, "Direccion");
        
        gestion.anhadeConductor("3", "Eva", "Diaz", null, "Direccion");

        gestion.buscaConductor("1").anhadeTransporte(new TransportePersonas(1, 1));
        
        gestion.buscaConductor("2").anhadeTransporte(new TransporteMercanciasPeligrosas(10, 100));
        
        gestion.buscaConductor("3").anhadeTransporte(new TransporteMercanciasPeligrosas(10, 100));

        List<Conductor> mejores = gestion.mejoresConductores();
        
        assertEquals(2, mejores.size());
        
        assertEquals("Luis", mejores.get(0).getNombre());
        
        assertEquals("Eva", mejores.get(1).getNombre());
    }
}
