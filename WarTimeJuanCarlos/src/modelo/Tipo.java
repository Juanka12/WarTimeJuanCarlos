package modelo;

public enum Tipo {
	infanteria(1), caballeria(2), arqueria(1);
	private int steps;
	
	public int getSteps() {
		return steps;
	}

	Tipo(int steps) {
		this.steps = steps;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
