package control;

import java.util.List;

import modelo.Batallon;
import modelo.Coordenada;
import modelo.Ejercito;
import modelo.Soldado;
import modelo.Tablero;
import vista.info.EspecificacionSoldadosInfo;

public class Controller {
	private Juego juego;
	private Juego juego2;
	// Desde el mismo momento en que toco el modelo tengo que hacer pruebas
	public void poblarBatallon(List<EspecificacionSoldadosInfo> especificacion) {
		assert especificacion != null;
		for (EspecificacionSoldadosInfo especificacionSoldadosInfo : especificacion) {
			for (int i = 0; i < especificacionSoldadosInfo.getCantidad(); i++) {
				juego.alistarSoldadoBatallonActual(new Soldado(especificacionSoldadosInfo.getEspecialidad()));
			}
		}

	}
	public void realizarBatalla() {
		juego.realizarBatalla();
	}
	
	public boolean isLocalizarEstado() {
		return juego.isLocalizarEstado();
	}

	public Controller(int ancho,int alto) {
		super();
		juego = new JuegoComenzar(ancho, alto);
		juego2 = new JuegoMover(juego);
	}
	
	public boolean poner(Coordenada coordenada) {
		boolean poner = juego.poner(coordenada);
		if(!juego.localizarEstado) {
			juego=juego2;
		}
		return poner;
	}

	public String getError() {
		return juego.getErrorActual().getMensaje();
	}
	public Juego getJuego() {
		return juego;
	}

	public Ejercito getEjercitoActual() {
		return juego.getEjercitoActual();
	}

	public Batallon getBatallonActual() {
		return juego.getBatallonActual();
	}

	public Tablero getTablero() {
		return juego.getTablero();
	}
	public int getAncho() {
		return juego.getAncho();
	}
	public int getAlto() {
		return juego.getAlto();
	}
}
