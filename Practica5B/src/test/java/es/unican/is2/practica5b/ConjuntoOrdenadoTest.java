package es.unican.is2.practica5b;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.practica5b.ConjuntoOrdenado;

class ConjuntoOrdenadoTest {

    private ConjuntoOrdenado<Integer> conjunto;

    @BeforeEach
    void setUp() {
        conjunto = new ConjuntoOrdenado<>();
    }

    private void addTodos(Integer... elementos) {
        for (Integer elemento : elementos) {
            assertTrue(conjunto.add(elemento));
        }
    }

    private void assertContenido(Integer... esperado) {
        assertEquals(esperado.length, conjunto.size());
        for (int i = 0; i < esperado.length; i++) {
            assertEquals(esperado[i], conjunto.get(i));
        }
    }

    @Test
    void sizeInicialEsCero() {
        assertEquals(0, conjunto.size());
    }

    @Test
    void addElementoNuloLanzaNullPointerExceptionYNoCambiaSize() {
        assertThrows(NullPointerException.class, () -> conjunto.add(null));
        assertEquals(0, conjunto.size());
    }

    @Test
    void addEnConjuntoVacioDevuelveTrueEInsertaElemento() {
        assertTrue(conjunto.add(5));

        assertAll(
            () -> assertEquals(1, conjunto.size()),
            () -> assertEquals(5, conjunto.get(0))
        );
    }

    @Test
    void addMantieneOrdenNaturalAscendenteEnInicioMedioYFinal() {
        addTodos(20, 10, 30, 25);

        assertContenido(10, 20, 25, 30);
    }

    @Test
    void addDuplicadoDevuelveFalseYNoModificaElConjunto() {
        addTodos(10, 20);

        assertFalse(conjunto.add(10));

        assertContenido(10, 20);
    }

    @Test
    void getIndiceValidoDevuelveElemento() {
        addTodos(10);

        assertEquals(10, conjunto.get(0));
    }

    @Test
    void getSobreConjuntoVacioLanzaIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.get(0));
    }

    @Test
    void getConIndiceNegativoLanzaIndexOutOfBoundsException() {
        addTodos(10);

        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.get(-1));
    }

    @Test
    void getConIndiceIgualASizeLanzaIndexOutOfBoundsException() {
        addTodos(10);

        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.get(1));
    }

    @Test
    void removeUnicoElementoValidoDevuelveElementoYReduceSize() {
        addTodos(10);

        Integer eliminado = conjunto.remove(0);

        assertAll(
            () -> assertEquals(10, eliminado),
            () -> assertEquals(0, conjunto.size())
        );
    }

    @Test
    void removeEnPrimeraMediaYUltimaPosicion() {
        addTodos(10, 20, 30, 40);

        assertEquals(10, conjunto.remove(0));
        assertContenido(20, 30, 40);

        assertEquals(30, conjunto.remove(1));
        assertContenido(20, 40);

        assertEquals(40, conjunto.remove(1));
        assertContenido(20);
    }

    @Test
    void removeSobreConjuntoVacioLanzaIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.remove(0));
    }

    @Test
    void removeConIndiceNegativoLanzaIndexOutOfBoundsException() {
        addTodos(10);

        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.remove(-1));
    }

    @Test
    void removeConIndiceIgualASizeLanzaIndexOutOfBoundsException() {
        addTodos(10);

        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.remove(1));
    }

    @Test
    void clearSobreConjuntoVacioNoFalla() {
        conjunto.clear();

        assertEquals(0, conjunto.size());
    }

    @Test
    void clearConElementosVaciaConjuntoYPermiteReutilizarlo() {
        addTodos(20, 10, 30);

        conjunto.clear();

        assertAll(
            () -> assertEquals(0, conjunto.size()),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> conjunto.get(0))
        );

        assertTrue(conjunto.add(5));
        assertContenido(5);
    }
}
