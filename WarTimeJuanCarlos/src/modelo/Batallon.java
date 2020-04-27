package modelo;

import java.awt.Color;
import java.util.LinkedList;

public class Batallon implements Casilla{
	private int id;
	private final int maximoSoldados = 10;
	private Tipo tipo;
	private LinkedList<Soldado> soldados = new LinkedList<Soldado>();
	private Color colorAtacante;
	private int experience;
	private int attack;
	private int defence;
	private int stamina;

	public int getExperience() {
		int totalExp = 0;
		for (Soldado soldado : soldados) {
			totalExp += soldado.getExperience();
		}
		this.experience = totalExp;
		return this.experience;
	}


	public int getAttack() {
		int totalAtt = 0;
		for (Soldado soldado : soldados) {
			totalAtt += soldado.getAttack();
		}
		this.attack = totalAtt;
		return this.attack;
	}


	public int getDefence() {
		int totalDef = 0;
		for (Soldado soldado : soldados) {
			totalDef += soldado.getDefence();
		}
		this.defence = totalDef;
		return this.defence;
	}


	public int getStamina() {
		int totalSta = 0;
		for (Soldado soldado : soldados) {
			totalSta += soldado.getStamina();
		}
		this.stamina = totalSta;
		return this.stamina;
	}


	public Batallon(int id, Tipo tipo) {
		super();
		this.id = id;
		this.tipo = tipo;
	}

	
	public Batallon(int id, Tipo tipo, LinkedList<Soldado> soldados, Color colorEjercitoAtacante) {
		this(id,tipo);
		this.soldados = soldados;
		this.colorAtacante = colorEjercitoAtacante;
	}


	public int getId() {
		return id;
	}

	public int getMaximoSoldados() {
		return maximoSoldados;
	}

	public Tipo getTipo() {
		return tipo;
	}


	public Integer getCantidadSoldados() {
		return soldados.size();
	}


	public boolean alistarSoldado(Soldado soldado) {
//		//programacion por contrato
		assert soldado.getEspecialidad().getTipo().equals(tipo):"epecialidad no compatible";
		return soldados.add(soldado);
		//programacion defensiva
//		boolean response = false;
//		if (soldado.getEspecialidad().getTipo().equals(tipo)) {
//			response = soldados.add(soldado);
//		}
//		return response;
	}

}
