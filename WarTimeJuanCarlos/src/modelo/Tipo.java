package modelo;

public enum Tipo {
	infanteria(1), caballeria(2), arqueria(1);
	private int step;
	Tipo(int i) {
		// TODO Auto-generated constructor stub
	}
	public int getStep() {
		return step;
	}
	@Override
	public String toString() {
		return super.toString();
	}
}
