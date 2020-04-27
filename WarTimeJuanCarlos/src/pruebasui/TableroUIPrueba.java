package pruebasui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Juego;
import modelo.Batallon;
import modelo.Coordenada;
import modelo.Especialidad;
import modelo.Soldado;
import modelo.Tablero;
import modelo.Tipo;
import vista.TableroUI;
import vista.info.TableroUIInfo;

public class TableroUIPrueba extends JFrame {

	private JPanel contentPane;
	private TableroUI tableroUI;
	private TableroUIInfo tableroUIInfo;
	private Juego juego = new Juego(6, 12);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableroUIPrueba frame = new TableroUIPrueba();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TableroUIPrueba() {
//		Para probar
		LinkedList<Soldado> soldados = new LinkedList<Soldado>();
		soldados.add(new Soldado(Especialidad.ligera));
//		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				JPanel panel = (JPanel) e.getSource();
				Coordenada coordenada = conseguirCoord(panel.getName());
				getTablero().insertar(new Batallon(4, Tipo.infanteria, soldados, Color.BLACK), coordenada);
				tableroUI.actualizarTablero(tableroUIInfo);
				// Por esta razon el mouseAdapter tiene que ser una propiedad del tableroui
//				tableroUI.actualizarTablero(mouseAdapter);
//				tableroUI.actualizarTablero();
			}

			private Coordenada conseguirCoord(String name) {
				int coordX = Integer.valueOf(name.substring(0, name.indexOf("_")));
				int coordY = Integer.valueOf(name.substring(name.indexOf("_")+1));
				return new Coordenada(coordX, coordY);
			}
		};
		tableroUIInfo=new TableroUIInfo(getTablero());
		tableroUI = new TableroUI(getTablero().getAncho(), getTablero().getAlto(), mouseAdapter,tableroUIInfo);
		contentPane.add(tableroUI, BorderLayout.CENTER);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}
	public Tablero getTablero() {
		return juego.getTablero();
	}
}
