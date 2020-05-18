package control;

import modelo.Batallon;
import modelo.Coordenada;
import modelo.Ejercito;
import modelo.Error;

public class JuegoComenzar extends Juego {

	public JuegoComenzar(int ancho, int alto) {
		super(ancho, alto);
	}
	

	public JuegoComenzar(Juego juego) {
		super(juego);
	}


	@Override
	public boolean poner(Coordenada coordenada) {
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
}
