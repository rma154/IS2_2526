package es.unican.is2.practica5a;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import es.unican.is2.practica2.domain.Cobertura;
import es.unican.is2.practica2.domain.Seguro;

class SeguroTest {

    @Test
    void precioDevuelveCeroSiFechaInicioEsNull() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(80);
        seguro.setFechaInicio(null);

        double resultado = seguro.precio();

        assertEquals(0.0, resultado, 0.001);
    }

    @Test
    void precioDevuelveCeroSiFechaInicioEsFutura() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(80);
        seguro.setFechaInicio(LocalDate.now().plusDays(1));

        double resultado = seguro.precio();

        assertEquals(0.0, resultado, 0.001);
    }

    @Test
    void precioTercerosSinRecargoNiOferta() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(80);
        seguro.setFechaInicio(LocalDate.now().minusYears(2));

        double resultado = seguro.precio();

        assertEquals(400.0, resultado, 0.001);
    }

    @Test
    void precioTercerosLunasConRecargoDelCincoPorCiento() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS_LUNAS);
        seguro.setPotencia(100);
        seguro.setFechaInicio(LocalDate.now().minusYears(2));

        double resultado = seguro.precio();

        assertEquals(630.0, resultado, 0.001);
    }

    @Test
    void precioTodoRiesgoConRecargoDelVeintePorCiento() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TODO_RIESGO);
        seguro.setPotencia(120);
        seguro.setFechaInicio(LocalDate.now().minusYears(2));

        double resultado = seguro.precio();

        assertEquals(1200.0, resultado, 0.001);
    }

    @Test
    void precioAplicaOfertaDurantePrimerAnio() {
        Seguro seguro = new Seguro();
        seguro.setCobertura(Cobertura.TERCEROS);
        seguro.setPotencia(80);
        seguro.setFechaInicio(LocalDate.now().minusMonths(6));

        double resultado = seguro.precio();

        assertEquals(320.0, resultado, 0.001);
    }
}