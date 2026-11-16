package componentes;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import componentes.BotonEspecial;
import javafx.application.Platform;

public class TestBotonEspecial {

	@BeforeAll
	static void iniciarJavaFX() throws Exception {
		// Creamos un contador que permitirá esperar a que JavaFX arranque.
		CountDownLatch latch = new CountDownLatch(1);
		// Guardamos posibles errores producidos durante la inicialización.
		AtomicReference<Throwable> error = new AtomicReference<>();
		// Creamos un hilo independiente para iniciar JavaFX.
		Thread hilo = new Thread(() -> {
			try {
				// Iniciamos JavaFX y avisamos cuando esté preparado.
				Platform.startup(latch::countDown);
			} catch (Throwable t) {
				// Guardamos el error si JavaFX no consigue iniciarse.
				error.set(t);
				// Liberamos la espera para que el programa pueda continuar.
				latch.countDown();
			}
		});
		// Indicamos que el hilo no impedirá finalizar el programa.
		hilo.setDaemon(true);
		// Ponemos en marcha el hilo.
		hilo.start();
		// Esperamos un máximo de 10 segundos a que JavaFX se inicie.
		assertTrue(
			latch.await(10, TimeUnit.SECONDS),
			"JavaFX no se ha iniciado"
		);
		// Si se ha producido un error, hacemos que JUnit lo detecte.
		if (error.get() != null) {
			throw new AssertionError(
				"No se pudo inicializar JavaFX",
				error.get()
			);
		}
	}
	/*
	 * MÉTODO AUXILIAR:
	 * Ejecuta una prueba en el hilo de JavaFX.
	 *
	 * Los componentes gráficos de JavaFX deben crearse y utilizarse
	 * desde su hilo de aplicación, no desde el hilo de JUnit.
	 */
	 private void ejecutarEnJavaFX(Runnable prueba)
		 throws Exception {
			 // Creamos un contador para esperar a que termine la prueba.
			 CountDownLatch latch = new CountDownLatch(1);
			 // Reservamos una variable para guardar posibles errores.
			 AtomicReference<Throwable> error = new AtomicReference<>();
			 // Enviamos la prueba al hilo de aplicación de JavaFX.
			 Platform.runLater(() -> {
				 try {
				 // Ejecutamos el código de la prueba.
				 prueba.run();
				 } catch (Throwable t) {
				 // Guardamos cualquier error o fallo de una aserción.
				 error.set(t);
				 } finally {
				 // Avisamos de que la ejecución ha terminado.
				 latch.countDown();
				 }
			 });
			 // Esperamos un máximo de 5 segundos a que termine la prueba.
			 assertTrue(
				 latch.await(5, TimeUnit.SECONDS),
				 "La prueba ha excedido el tiempo de espera"
			 );
			 // Si se ha producido un error, lo comunicamos a JUnit.
			 if (error.get() != null) {
				 throw new AssertionError(
				 "La prueba ha fallado",
				 error.get()
				 );
			 }
	 	}
	
}
