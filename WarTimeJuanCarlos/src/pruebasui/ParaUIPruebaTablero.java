package pruebasui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import control.Controller;
import control.Juego;
import modelo.Coordenada;
import utiles.Utiles;
import vista.Conversores.Generador;

public class ParaUIPruebaTablero extends TableroUIPrueba {
	private Juego juego;
	private Controller controller;
	MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			JPanel panel = (JPanel) e.getSource();
			Coordenada coordenada = Utiles.getCoordenada(panel.getName());
			if (comprobarLadoTablero(coordenada)) {

				if (!controller.localizar(coordenada)) {
					System.out.println("algo va mal");
				}
				getTableroUI().actualizarTablero(Generador.getTableroUIInfo(controller.getJuego()));
			}
		}
	};
	

	public ParaUIPruebaTablero() {
		super();
		getTableroUI().setMouseAdapter(mouseAdapter);
		juego = new Juego(6, 12);
		controller = new Controller(juego);
		getTableroUI().actualizarTablero(Generador.getTableroUIInfo(juego));
	}

	protected boolean comprobarLadoTablero(Coordenada coordenada) {
		if (coordenada.getY() <= juego.getAlto()/2-1 && juego.getIdEjercitoActual()==0) {
			return true;
		}else if (coordenada.getY() >= juego.getAlto()/2 && juego.getIdEjercitoActual()==1) {
			return true;
		}
		return false;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParaUIPruebaTablero frame = new ParaUIPruebaTablero();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
