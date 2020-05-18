package control;

import modelo.Batallon;
import modelo.Casilla;
import modelo.Coordenada;

public class JuegoMover extends Juego {
	private Batallon batallonAMover = null;
	private int movimientos = 0;
	private Coordenada origenTemp;
	
	public JuegoMover(int ancho, int alto) {
		super(ancho, alto);
	}

	public JuegoMover(Juego juego) {
		super(juego);
	}

	@Override
	public boolean poner(Coordenada coordenada) {
		boolean mover = moverBatallon(coordenada);
		if (!mover) {
			System.out.println(this.origenTemp);
			mover = moverAdonde(coordenada);
		}
		return mover;
	}
	public boolean moverBatallon(Coordenada coordenada) {
		Casilla casilla = getTablero().getCasilla(coordenada);
		boolean mover = comprobarBatallon(casilla);
		if (mover && this.batallonAMover == null) {
			this.batallonAMover = (Batallon) casilla;
			this.origenTemp = coordenada;
			getTablero().borrar(casilla);
		}
		return mover;
	}
	public boolean moverAdonde(Coordenada coordenada) {
		boolean insertar = comprobarPasos(coordenada);
		boolean pelear = comprobarRango(coordenada);
		System.out.println(insertar);
		Casilla casilla = getTablero().getCasilla(coordenada);
		if (comprobarBatallonEnemigo(casilla) && pelear) {
			System.out.println("pelea");
		} else if (insertar) {
			System.out.println("si");
			insertar = tablero.insertar(batallonAMover, coordenada);
			if (insertar) {
				siguienteTurno();
			}
		}
		return insertar;
	}
	protected boolean comprobarPasos(Coordenada destino) {
		if (this.batallonAMover!=null) {
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
	protected boolean comprobarRango(Coordenada destino) {
		if (this.batallonAMover!=null) {
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
	protected void siguienteTurno() {
		batallonAMover = null;
		this.movimientos++;
		if (this.movimientos == 3) {
			setSiguienteEjercito();
			this.movimientos = 0;
		}
	}
}
