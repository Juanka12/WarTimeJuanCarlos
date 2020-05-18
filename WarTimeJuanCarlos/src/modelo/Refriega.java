package modelo;

public class Refriega {
	private Soldado defensor, atacante;
	private int incrementoExperiencia = 1, incrementoDefensa = 1, incrementoAtaque = 1;
	private int staminaInicialDefensor,staminaInicialAtacante;

	public Refriega(Soldado defensor, Soldado atacante) {
		super();
		this.defensor = defensor;
		this.atacante = atacante;
		staminaInicialAtacante=atacante.getStamina();
		staminaInicialDefensor=defensor.getStamina();
	}

	public void lucha() {
		float defensa = 0, ataque = 0;
		while (!isAlguienSeveramenteHerido()) {
			defensa = defensor.defender();
			ataque = atacante.atacar();
			defensor.infringir(ataque);
			atacante.infringir(defensa);
		}
		finalizar();
	}

	private void finalizar() {
		// aumentar experiencia de ambos
		// la defensa del defensor
		// en caso de victoria del atacante hay que
		// aumentar el ataque del atacante
		defensor.incrementarExperiencia(incrementoExperiencia);
		atacante.incrementarExperiencia(incrementoExperiencia);
		defensor.incrementarDefensa(incrementoDefensa);
		if (isVencedorAtacante())
			atacante.incrementaAtaque(incrementoAtaque);
	}

	private boolean isAlguienSeveramenteHerido() {
		// TODO Auto-generated method stub
		// Cuando tiene la mitad de la stamina que al comienzo de la refriega
		return false;
	}

	public Soldado getVencedor() {
		// TODO
		return null;
	}

	public boolean isVencedorAtacante() {
		// TODO
		return false;
	}

	public Soldado getDefensor() {
		return defensor;
	}

	public Soldado getAtacante() {
		return atacante;
	}

}
