package es.unican.is2.practica5a;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.timing.Pause;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.practica2.business.GestionSeguros;
import es.unican.is2.practica2.dao.api.IClientesDAO;
import es.unican.is2.practica2.dao.api.ISegurosDAO;
import es.unican.is2.practica2.dao.h2.ClientesDAO;
import es.unican.is2.practica2.dao.h2.SegurosDAO;
import es.unican.is2.practica2.gui.VistaAgente;

class VistaAgenteIT {

    private FrameFixture window;
    private VistaAgente frame;

    @BeforeAll
    static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    void setUp() {
        frame = GuiActionRunner.execute(() -> {
            IClientesDAO daoClientes = new ClientesDAO();
            ISegurosDAO daoSeguros = new SegurosDAO();
            GestionSeguros negocio = new GestionSeguros(daoClientes, daoSeguros);

            VistaAgente vista = new VistaAgente(negocio, negocio, negocio);
            vista.setVisible(true);
            return vista;
        });

        window = new FrameFixture(frame);
        window.show();
    }

    @AfterEach
    void tearDown() {
        if (window != null) {
            window.cleanUp();
        }
    }

    @Test
    void consultaClienteValidoMuestraNombreYTotal() {
        window.textBox("txtDNICliente").setText("11111111A");
        window.textBox("txtDNICliente").requireText("11111111A");

        GuiActionRunner.execute(() -> window.button("btnBuscar").target().doClick());
        window.robot().waitForIdle();
        Pause.pause(500);

        window.textBox("txtNombreCliente").requireText("Juan");
        window.textBox("txtTotalCliente").requireText("1820.0");
    }

    @Test
    void consultaClienteNoValidoMuestraMensaje() {
        window.textBox("txtDNICliente").setText("99999999Z");
        window.textBox("txtDNICliente").requireText("99999999Z");

        GuiActionRunner.execute(() -> window.button("btnBuscar").target().doClick());
        window.robot().waitForIdle();
        Pause.pause(500);

        window.textBox("txtNombreCliente").requireText("DNI No Válido");
        window.textBox("txtTotalCliente").requireText("");
    }

    @Test
    void consultaClienteValidoMuestraSeguros() {
        window.textBox("txtDNICliente").setText("11111111A");
        window.textBox("txtDNICliente").requireText("11111111A");

        GuiActionRunner.execute(() -> window.button("btnBuscar").target().doClick());
        window.robot().waitForIdle();
        Pause.pause(500);

        assertThat(window.list("listSeguros").contents()).containsExactly(
                "1111AAA TERCEROS",
                "1111BBB TODO_RIESGO",
                "1111CCC TERCEROS");
    }
}