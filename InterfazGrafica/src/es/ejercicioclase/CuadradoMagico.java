package es.ejercicioclase;


public class CuadradoMagico {
	int numero;
	int [][] matrizMagica;
	int numeroDatos;
	
	

	
	
	//CONSTRUCTOR
	public CuadradoMagico(int numero) {
		this.numero = numero;
		this.matrizMagica= new int[numero][numero];
		this.numeroDatos = (int) Math.pow(numero, 2);
	
	}

	public
	
	
	//SETTER AND GETTER
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int[][] getMatrizCuadrada() {
		return matrizMagica;
	}

	public void setMatrizCuadrada(int[][] matrizMagica) {
		this.matrizMagica = matrizMagica;
	}

	public int getNumeroDatos() {
		return numeroDatos;
	}

	public void setNumeroDatos(int numeroDatos) {
		this.numeroDatos = numeroDatos;
	}
	
	
}
