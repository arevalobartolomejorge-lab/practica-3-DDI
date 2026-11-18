package componentes;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * La clase BotonEspecial es un boton en el cual puedes cambiar el texto, su tamaño y color, el color del fondo del botón, el texto de alluda y avilitarlo o desavilitarlo.
 */

public class BotonEspecial extends Button{

	private String texto;
	private String colorFondo;
	private String colorTexto;
	private int tamannoFuente;
	private boolean activado;
	private String textoAlluda;
	private Tooltip alluda;
	private Event evento;
	
	/**
	 * El constructor de la clase de botonEspecial
	 * @param texto: El texto que aparecerá en el botón
	 * @param colorFondo: El color del fondo del botón
	 * @param colorTexto: El color del texto del botón
	 * @param tamannoFuente: El tamaño del texto del botón
	 * @param activado>: Si el botón puede pulsarse o no
	 * @param textoAlluda: El texto de alluda del botón
	 */
	public BotonEspecial(String texto, String colorFondo, String colorTexto, int tamannoFuente, boolean activado, String textoAlluda) {
		this.texto = texto;
		this.colorFondo = colorFondo;
		this.colorTexto = colorTexto;
		this.tamannoFuente = tamannoFuente;
		this.activado = activado;
		this.textoAlluda = textoAlluda;
		this.alluda = new Tooltip(textoAlluda);
		setText(texto);
		setColorFondo(colorFondo);
		setColorTexto(colorTexto);
		setTamannoFuente(tamannoFuente);
		setDefaultButton(activado);
		setTooltip(alluda);	
	}
	/**
	 * El método para obtener el texto del botón
	 * @return El texto del Botón
	 */
	public String getTexto() {
		return texto;
	}
	/**
	 * El método para cambiar el texto del botón
	 * @param texto: El nuevo texto para el botón
	 */
	public void setTexto(String texto) {
		this.texto = texto;
		setText(texto);
	}
	/**
	 * El método para obtener el color del fondo botón
	 * @return El color del fono del Botón en código exadecimal
	 */
	public String getColorFondo() {
		return colorFondo;
	}
	/**
	 * El método para cambia el color del fono del botón
	 * @param colorFondo: el nuevo color del fondo del texto
	 * @throws IllegalArgumentException: Se lanza en caso de que el código exadecimal del color no tenga 6 caracteres
	 */
	public void setColorFondo(String colorFondo) throws IllegalArgumentException {
		if(colorFondo.length() == 6) {
			setStyle("-fx-background-color:" + colorFondo + ";");
			this.colorFondo = colorFondo;
		} else {
			throw new IllegalArgumentException("El tamaño de el código del color del fondo no es valido");
		}
		setStyle("-fx-background-color:" + colorFondo + ";");
	}
	/**
	 * El método para obtener el color del texto del botón
	 * @return El color del texto del Botón en código exadecimal
	 */
	public String getColorTexto() {
		return colorTexto;
	}
	/**
	 * El método para cambiar el color del texto del botón
	 * @param colorTexto: El nuevo color del texto del botón
	 * @throws IllegalArgumentException: Se lanza en caso de que el código exadecimal del color no tenga 6 caracteres
	 */
	public void setColorTexto(String colorTexto) throws IllegalArgumentException {
		if(colorTexto.length() == 6) {
			this.colorTexto = colorTexto;
			setTextFill(Color.web(colorTexto));
		} else {
			throw new IllegalArgumentException("El tamaño de el código del color del texto no es valido");
		}
		setTextFill(Color.web(colorTexto));
	}
	/**
	 * El método para obtener el tamaño del texto del botón
	 * @return El tamaño del texto del Botón
	 */
	public int getTamannoFuente() {
		return tamannoFuente;
	}
	/**
	 * Método para cambiar el tamaño del texto del botón
	 * @param tamannoFuente: El nuevo tamaño del texto
	 * @throws IllegalArgumentException: Se lanza en caso de que el tamaño de la fuente sea igual o menor a 0
	 */
	public void setTamannoFuente(int tamannoFuente) throws IllegalArgumentException {
		if(tamannoFuente > 0) {
			this.tamannoFuente = tamannoFuente;
			setFont(new Font(tamannoFuente));
		} else {
			throw new IllegalArgumentException("El tamaño de la fuente no puede ser negativo");
		}
		
	}
	/**
	 * El método para obtener el estado de activado y desactivado del botón
	 * @return El estado del Botón
	 */
	public boolean getActivado() {
		return activado;
	}
	/**
	 * Método para cambiar el estado de activado y desactivado del botón
	 * @param activado: El nuevo estado del boton
	 */
	public void setActivado(boolean activado) {
		if(activado == true) {
			arm();
			this.activado = activado;
		}
		else {
			disarm();
			this.activado = activado;
		}
	}
	/**
	 * El método para obtener el texto de alluda del botón
	 * @return El texto de alluda del Botón
	 */
	public String getTextoAlluda() {
		return textoAlluda;
	}
	/**
	 * El método para cambiar el texto de alluda del botón
	 * @param textoAlluda: El nuevo texto de alluda del botón
	 */
	public void setTextoAlluda(String textoAlluda) {
		this.textoAlluda = textoAlluda;
		this.alluda.setText(textoAlluda);
	}
	
	
	
	
}
