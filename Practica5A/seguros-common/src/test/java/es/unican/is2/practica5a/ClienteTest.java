package es.unican.is2.practica5a;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import es.unican.is2.practica2.domain.Cliente;
import es.unican.is2.practica2.domain.Cobertura;
import es.unican.is2.practica2.domain.Seguro;

class ClienteTest {

    @Test
    void totalSegurosDevuelveCeroSiNoHaySeguros() {
        Cliente cliente = new Cliente();

        double resultado = cliente.totalSeguros();

        assertEquals(0.0, resultado, 0.001);
    }

    @Test
    void totalSegurosDevuelveCeroSiLaListaEsNull() {
        Cliente cliente = new Cliente();
        cliente.setSeguros(null);

        double resultado = cliente.totalSeguros();

        assertEquals(0.0, resultado, 0.001);
    }

    @Test
    void totalSegurosSumaPreciosSinMinusvalia() {
        Cliente cliente = new Cliente();
        cliente.setMinusvalia(false);

        Seguro s1 = new Seguro();
        s1.setCobertura(Cobertura.TERCEROS);
        s1.setPotencia(80);
        s1.setFechaInicio(LocalDate.now().minusYears(2)); // precio = 400

        Seguro s2 = new Seguro();
        s2.setCobertura(Cobertura.TODO_RIESGO);
        s2.setPotencia(80);
        s2.setFechaInicio(LocalDate.now().minusYears(2)); // precio = 1000

        List<Seguro> seguros = new LinkedList<>();
        seguros.add(s1);
        seguros.add(s2);

        cliente.setSeguros(seguros);

        double resultado = cliente.totalSeguros();

        assertEquals(1400.0, resultado, 0.001);
    }

    @Test
    void totalSegurosAplicaDescuentoSiHayMinusvalia() {
        Cliente cliente = new Cliente();
        cliente.setMinusvalia(true);

        Seguro s1 = new Seguro();
        s1.setCobertura(Cobertura.TERCEROS);
        s1.setPotencia(80);
        s1.setFechaInicio(LocalDate.now().minusYears(2)); // precio = 400

        Seguro s2 = new Seguro();
        s2.setCobertura(Cobertura.TODO_RIESGO);
        s2.setPotencia(80);
        s2.setFechaInicio(LocalDate.now().minusYears(2)); // precio = 1000

        List<Seguro> seguros = new LinkedList<>();
        seguros.add(s1);
        seguros.add(s2);

        cliente.setSeguros(seguros);

        double resultado = cliente.totalSeguros();

        assertEquals(1050.0, resultado, 0.001); // 1400 * 0.75
    }

    @Test
    void totalSegurosIgnoraSegurosNull() {
        Cliente cliente = new Cliente();
        cliente.setMinusvalia(false);

        Seguro s1 = new Seguro();
        s1.setCobertura(Cobertura.TERCEROS);
        s1.setPotencia(80);
        s1.setFechaInicio(LocalDate.now().minusYears(2)); // precio = 400

        List<Seguro> seguros = new LinkedList<>();
        seguros.add(s1);
        seguros.add(null);

        cliente.setSeguros(seguros);

        double resultado = cliente.totalSeguros();

        assertEquals(400.0, resultado, 0.001);
    }
}