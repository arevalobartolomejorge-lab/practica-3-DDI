package componentes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.scene.paint.Color;

import componentes.BotonEspecial;
import javafx.application.Platform;

public class TestBotonEspecial {

	private BotonEspecial boton;

    /**
     * Inicializa el Toolkit de JavaFX una única vez antes de todos los tests.
     * Sin esto, instanciar Button o Tooltip lanza IllegalStateException.
     */
    @BeforeAll
    static void iniciarJavaFX() {
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
            // El toolkit ya estaba inicializado
        }
    }

    /**
     * Crea una instancia limpia del botón antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        boton = new BotonEspecial("Aceptar", "FFFFFF", "000000", 14, true, "Pulsa para aceptar");
    }

    @Test
    @DisplayName("1. Creación del botón con un texto")
    void testCreacionBotonConTexto() {
        BotonEspecial nuevoBoton = new BotonEspecial("Guardar", "FF0000", "FFFFFF", 12, true, "Ayuda guardar");
        assertNotNull(nuevoBoton);
        assertEquals("Guardar", nuevoBoton.getTexto());
        assertEquals("Guardar", nuevoBoton.getText());
    }

    @Test
    @DisplayName("2. Modificación y recuperación del texto")
    void testModificacionYRecuperacionTexto() {
        boton.setTexto("Cancelar");
        assertEquals("Cancelar", boton.getTexto());
        assertEquals("Cancelar", boton.getText());
    }

    @Test
    @DisplayName("3. Cambio del color de fondo")
    void testCambioColorFondo() {
        boton.setColorFondo("123456");
        assertEquals("123456", boton.getColorFondo());
        assertEquals("-fx-background-color:123456;", boton.getStyle());

        // Comprobación extra: longitud distinta de 6 lanza excepción
        assertThrows(IllegalArgumentException.class, () -> boton.setColorFondo("FFF"));
    }

    @Test
    @DisplayName("4. Cambio del color del texto")
    void testCambioColorTexto() {
        boton.setColorTexto("FF00FF");
        assertEquals("FF00FF", boton.getColorTexto());
        assertEquals(Color.web("FF00FF"), boton.getTextFill());

        // Comprobación extra: longitud distinta de 6 lanza excepción
        assertThrows(IllegalArgumentException.class, () -> boton.setColorTexto("1234567"));
    }

    @Test
    @DisplayName("5. Establecimiento del tamaño de fuente")
    void testEstablecimientoTamannoFuente() {
        boton.setTamannoFuente(20);
        assertEquals(20, boton.getTamannoFuente());
        assertEquals(20.0, boton.getFont().getSize());
    }

    @Test
    @DisplayName("6. Establecimiento del texto de ayuda para el Tooltip")
    void testEstablecimientoTextoAyudaTooltip() {
        boton.setTextoAlluda("Nueva ayuda del botón");
        assertEquals("Nueva ayuda del botón", boton.getTextoAlluda());
        assertNotNull(boton.getTooltip());
        assertEquals("Nueva ayuda del botón", boton.getTooltip().getText());
    }

    @Test
    @DisplayName("7. Activación y desactivación del botón")
    void testActivacionYDesactivacionBoton() {
        boton.setActivado(true);
        assertTrue(boton.getActivado());
        assertTrue(boton.isArmed());

        boton.setActivado(false);
        assertFalse(boton.getActivado());
        assertFalse(boton.isArmed());
    }

    @Test
    @DisplayName("8. Ejecución de una acción al pulsar el botón")
    void testEjecucionAccionAlPulsar() {
        AtomicBoolean accionEjecutada = new AtomicBoolean(false);
        
        boton.setOnAction(event -> accionEjecutada.set(true));
        boton.fire(); // Simula el clic sobre el botón

        assertTrue(accionEjecutada.get(), "La acción asociada al botón debería haberse ejecutado");
    }

    @Test
    @DisplayName("9. Rechazo de tamaños de fuente negativos o iguales a cero")
    void testRechazoTamannosFuenteInvalidos() {
        // Caso igual a 0
        assertThrows(IllegalArgumentException.class, () -> boton.setTamannoFuente(0));

        // Caso negativo
        assertThrows(IllegalArgumentException.class, () -> boton.setTamannoFuente(-5));
    }
	
}
