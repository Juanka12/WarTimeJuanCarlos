package modelo;

public class Ataque {
	private Batallon defensor, atacante;

	public Ataque(Batallon defensor, Batallon atacante) {
		super();
		this.defensor = defensor;
		this.atacante = atacante;
	}

	public Batallon getDefensor() {
		return defensor;
	}

	public Batallon getAtacante() {
		return atacante;
	}

	public boolean combatir() {
		//Porque estamos sacando a los soldados que esten por debajo de staminaCritica
		Soldado defensorSoldado;
		Soldado atacanteSoldado;
		boolean response=false;
		while (hayaSoldados()) {
			response=true;
			defensorSoldado = defensor.getSoldado();
			atacanteSoldado = atacante.getSoldado();
			Refriega refriega = new Refriega(defensorSoldado, atacanteSoldado);
			refriega.lucha();
			defensor.tratarSoldado(defensorSoldado);
			atacante.tratarSoldado(atacanteSoldado);
		}
		return response;
	}

	public boolean isAtacanteVencedor() {
		return atacante.haySoldados();
	}
	
	private boolean hayaSoldados() {
		return defensor.haySoldados() && atacante.haySoldados();
	}
}
