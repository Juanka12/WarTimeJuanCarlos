package modelo;

import java.util.HashSet;
import java.util.Iterator;

public class Batalla {
	// uso esta implementacion porque los elementos no deben repetirse
	// pero no necesito que esten ordenados
	private HashSet<Ataque> ataques = new HashSet<Ataque>();
	private Tablero tablero;
	
	

	public Batalla(Tablero tablero) {
		super();
		this.tablero = tablero;
	}

	public boolean insertarAtaque(Batallon defensor, Batallon atacante) {
		return ataques.add(new Ataque(defensor, atacante));
	}

	//comentario solo valido para poder diferenciar entre cosas
	public void guerrear() {
		for (Iterator iterator = ataques.iterator(); iterator.hasNext();) {
			Ataque ataque = (Ataque) iterator.next();
			if (ataque.combatir()) {
				if(ataque.isAtacanteVencedor()) {
					tablero.conquistar(ataque.getAtacante(),ataque.getDefensor());
				}else {
					tablero.borrar(ataque.getAtacante());
				}
			}
			iterator.remove();
		}
	}
}
