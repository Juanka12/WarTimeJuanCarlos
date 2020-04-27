package modelo;

public class Soldado {
	private Especialidad especialidad;
	private int experience = 1;
	private int attack = 1;
	private int defence = 1;
	private int stamina = 100;
	
	public int getExperience() {
		return experience;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefence() {
		return defence;
	}

	public int getStamina() {
		return stamina;
	}

	public Soldado(Especialidad especialidad) {
		super();
		this.especialidad = especialidad;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}
	
}
