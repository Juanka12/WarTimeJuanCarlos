package utiles;

import modelo.Coordenada;

public class Utiles {
	// Todos los metodos aqui definidos (o casi todos) se pasan a clases para dejar
		// de ser estaticos
	//TODO un test si sabes lo que te conviene
	
	public static String nombrar(int i, int j) {
		return String.valueOf(i)+":"+String.valueOf(j);
	}
	public static Coordenada getCoordenada(String coordenada) {
		String[] split = coordenada.split(":");
		return new Coordenada(Integer.valueOf(split[0]), Integer.valueOf(split[1]));
	}

	public static int getAleatorioSesgado(int min,int max,int porcentajeSesgo) {
		assert max>min&& min>=0;
		return (int)((Math.random()*((max+1)-min))+min);
	}
	
	public static int dadoConSesgo(int caras, double mod) {
		int num = 0;
		double valor = Math.random()*100;
		double [] dado = new double[caras];
		double porcentaje = (1/(double)caras)*100;
		int suma = (int) (mod/(caras-1));
		dado[0]=porcentaje-(mod/2);
		for (int i = 1; i < dado.length; i++) {
			dado[i]=dado[i-1]+suma;
		}
		for (int i = 1; i < dado.length; i++) {
			dado[i]+=dado[i-1];
		}
		for (int i = 0; i < dado.length; i++) {
			if (valor<=dado[i]) {
				num = i+1;
				return num;
			}
		}
		return num;
	}
}
