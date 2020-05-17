package control;

import java.util.ArrayDeque;

import modelo.Batallon;
import modelo.Blanca;
import modelo.Casilla;
import modelo.Castillo;
import modelo.Coordenada;
import modelo.Ejercito;
import modelo.Error;
import modelo.Soldado;
import modelo.Tablero;

public class Juego {
	private Tablero tablero;
	private int ancho, alto;
	private ArrayDeque<Ejercito> ejercitos = new ArrayDeque<Ejercito>();
	private boolean localizarEstado = true;
	private Ejercito primerEjercito;
	private Error errorActualError = null;
	private Batallon batallonAMover = null;
	private int movimientos = 0;
	private Coordenada origenTemp;

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

	public Tablero getTablero() {
		return tablero;
	}

	public boolean moverBatallon(Coordenada coordenada) {
		Casilla casilla = getTablero().getCasilla(coordenada);
		boolean mover = comprobarBatallon(casilla);
		if (mover && batallonAMover == null) {
			batallonAMover = (Batallon) casilla;
			getTablero().borrar(casilla);
			this.origenTemp = coordenada;
		}
		return mover;
	}

	public boolean moverAdonde(Coordenada coordenada) {
		boolean insertar = comprobarPasos(coordenada);
		boolean pelear = comprobarRango(coordenada);
		Casilla casilla = getTablero().getCasilla(coordenada);
		if (comprobarBatallonEnemigo(casilla) && pelear) {
			System.out.println("pelea");
		} else if (insertar) {
			insertar = tablero.insertar(batallonAMover, coordenada);
			if (insertar) {
				siguienteTurno();
			}
		}
		return insertar;
	}

	private void siguienteTurno() {
		batallonAMover = null;
		this.movimientos++;
		if (this.movimientos == 3) {
			setSiguienteEjercito();
			this.movimientos = 0;
		}
	}

	private boolean comprobarRango(Coordenada destino) {
		if (this.origenTemp != null) {
			int maxY = this.origenTemp.getY() + batallonAMover.getRango().getMax();
			int maxX = this.origenTemp.getX() + batallonAMover.getRango().getMax();
			int minY = this.origenTemp.getY() - batallonAMover.getRango().getMax();
			int minX = this.origenTemp.getX() - batallonAMover.getRango().getMax();
			if (destino.getX() >= minX && destino.getY() >= minY && destino.getX() <= maxX && destino.getY() <= maxY) {
				return true;
			}
		}
		return false;
	}

	private boolean comprobarPasos(Coordenada destino) {
		if (this.origenTemp != null) {
			int maxY = this.origenTemp.getY() + batallonAMover.getSteps();
			int maxX = this.origenTemp.getX() + batallonAMover.getSteps();
			int minY = this.origenTemp.getY() - batallonAMover.getSteps();
			int minX = this.origenTemp.getX() - batallonAMover.getSteps();
			if (destino.getX() >= minX && destino.getY() >= minY && destino.getX() <= maxX && destino.getY() <= maxY) {
				return true;
			}
		}
		return false;
	}

	private boolean comprobarBatallon(Casilla casilla) {
		Ejercito ejercito = ejercitos.peek();
		return casilla instanceof Batallon && ejercito.comprobarBatallon((Batallon) casilla);
	}

	private boolean comprobarBatallonEnemigo(Casilla casilla) {
		Ejercito ejercito = ejercitos.peek();
		return casilla instanceof Batallon && !ejercito.comprobarBatallon((Batallon) casilla);
	}

	public boolean localizarBatallon(Coordenada coordenada) {
		boolean insertar = comprobarLocalizacion(coordenada);
		if (!insertar) {
			errorActualError = Error.posicion;
		} else if (localizarEstado) {
			Ejercito ejercito = ejercitos.peek();
			Batallon batallonActual = ejercito.getBatallonActual();
			insertar = tablero.insertar(batallonActual, coordenada);
			if (insertar) {
				if (!ejercito.setSiguienteBatallon()) {
					setSiguienteEjercito();
				}
			} else {
				errorActualError = Error.ocupada;
			}
		}
		return insertar && localizarEstado;
	}

	public Error getErrorActual() {
		Error response = errorActualError;
		errorActualError = Error.noerror;
		return response;
	}

	private boolean comprobarLocalizacion(Coordenada coordenada) {
		return getTablero().isEnSuMitad(getEjercitoActual(), coordenada);
	}

	private void setSiguienteEjercito() {
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

}
