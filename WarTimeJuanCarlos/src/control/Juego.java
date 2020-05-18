package control;

import java.util.ArrayDeque;

import modelo.Batalla;
import modelo.Batallon;
import modelo.Blanca;
import modelo.Casilla;
import modelo.Castillo;
import modelo.Coordenada;
import modelo.Ejercito;
import modelo.Error;
import modelo.Soldado;
import modelo.Tablero;

public abstract class Juego {
	protected Tablero tablero;
	private int ancho, alto;
	protected ArrayDeque<Ejercito> ejercitos = new ArrayDeque<Ejercito>();
	protected boolean localizarEstado = true;
	private Ejercito primerEjercito;
	protected Error errorActualError = null;
	protected Batalla batallaActual;

	public boolean isLocalizarEstado() {
		return localizarEstado;
	}

	public Juego(int ancho, int alto) {
		super();
		this.ancho = ancho;
		this.alto = alto;
		tablero = new Tablero(ancho, alto);
		Ejercito ejercitoCero = new Ejercito(0);
		tablero.insertar(new Castillo(ejercitoCero), new Coordenada(3, 1));
		ejercitos.offer(ejercitoCero);
		Ejercito ejercitoUno = new Ejercito(1);
		tablero.insertar(new Castillo(ejercitoUno), new Coordenada(3, ancho - 2));
		ejercitos.offer(ejercitoUno);
		primerEjercito = ejercitos.peek();
	}

	public Juego(Juego juego) {
		super();
		this.tablero = juego.tablero;
		this.ancho = juego.ancho;
		this.alto = juego.alto;
		this.ejercitos = juego.ejercitos;
		this.localizarEstado = juego.localizarEstado;
		this.primerEjercito = juego.primerEjercito;
		this.errorActualError = juego.errorActualError;
	}

	public Tablero getTablero() {
		return tablero;
	}

	protected boolean comprobarBatallon(Casilla casilla) {
		Ejercito ejercito = ejercitos.peek();
		return casilla instanceof Batallon && ejercito.comprobarBatallon((Batallon) casilla);
	}

	protected boolean comprobarBatallonEnemigo(Casilla casilla) {
		Ejercito ejercito = ejercitos.peek();
		return casilla instanceof Batallon && !ejercito.comprobarBatallon((Batallon) casilla);
	}

	public abstract boolean poner(Coordenada coordenada);

	public Error getErrorActual() {
		Error response = errorActualError;
		errorActualError = Error.noerror;
		return response;
	}

	protected boolean comprobarLocalizacion(Coordenada coordenada) {
		return getTablero().isEnSuMitad(getEjercitoActual(), coordenada);
	}

	protected void setSiguienteEjercito() {
		ejercitos.offer(ejercitos.poll());
		if (ejercitos.peek().equals(primerEjercito)) {
			localizarEstado = false;
		}
	}

	public Ejercito getEjercitoActual() {
		return ejercitos.peek();
	}

	public void alistarSoldadoBatallonActual(Soldado soldado) {
		// Demeter
		getEjercitoActual().getBatallonActual().alistarSoldado(soldado);
	}

	public Batallon getBatallonActual() {
		return getEjercitoActual().getBatallonActual();
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	public Casilla getCasilla(Coordenada coordenada) {
		Casilla casilla = tablero.getCasilla(coordenada);
		if (casilla == null) {
			return new Blanca();
		}
		return casilla;
	}

	public void realizarBatalla() {
		batallaActual.guerrear();
	}
}
