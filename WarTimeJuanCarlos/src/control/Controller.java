package control;

import java.util.List;

import modelo.Batallon;
import modelo.Coordenada;
import modelo.Soldado;
import vista.info.EspecificacionSoldadosInfo;

public class Controller {
	private Juego juego;
	private Batallon batallon;

	public Controller(Juego juego) {
		super();
		this.juego = juego;
	}

	public Controller(Batallon batallon) {
		super();
		this.batallon = batallon;
	}

	public boolean localizar(Coordenada coordenada) {
		return juego.localizarBatallon(coordenada);
	}

	public Juego getJuego() {
		return juego;
	}

	public Batallon getBatallon() {
		return batallon;
	}

	public void poblarBatallon(List<EspecificacionSoldadosInfo> especificacion) {
		assert especificacion != null;
		for (EspecificacionSoldadosInfo especificacionSoldadosInfo : especificacion) {
			for (int i = 0; i < especificacionSoldadosInfo.getCantidad(); i++) {
				this.batallon.alistarSoldado(new Soldado(especificacionSoldadosInfo.getEspecialidad()));
			}
		}

	}
}
