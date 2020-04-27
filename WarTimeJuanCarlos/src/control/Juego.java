package control;

import java.util.LinkedList;

import modelo.Especialidad;
import modelo.Soldado;
import modelo.Tablero;

public class Juego {
	private Tablero tablero;
	private LinkedList<Soldado> soldados = new LinkedList<Soldado>();

	public Juego(int ancho, int alto) {
		super();
		tablero = new Tablero(ancho, alto);
		soldados.add(new Soldado(Especialidad.ligera));
	}

	public Tablero getTablero() {
		return tablero;
	}

	public LinkedList<Soldado> getSoldados() {
		return soldados;
	}

}
