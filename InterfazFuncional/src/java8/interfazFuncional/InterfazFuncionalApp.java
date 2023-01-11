package java8.interfazFuncional;

public class InterfazFuncionalApp implements Operacion{

	/**INTERAZ FUNCIONAL
	 * >Una interfaz funcional es una INTERFAZ DE UN SOLO METODO
	 * 
	 * >Tiene su propia Anotacion @FunctionalInterface para 
	 * declarar dicho tipo de interaz 
	 * 
	 * Una interfaz funcional es aquella que 
	 * solamente define una única operación .:
	 * >Por lo tanto, al colocar uno o más métodos 
	 * en la Interfaz, marcará error
	 * 
	 * 
	 * */
	
	public double operar(double x, double y) {
		Operacion op = (n1, n2) -> n1 + n2;
		return op.calcular(x, y);
	}
	
	/**
	 * Se puede implementar la operacion de la Interfaz Funcional,
	 * ya sea a través de una expresión Lambda o sobreescribiendo
	 * el método
	 */
	@Override
	public double calcular(double x, double y) {
		return (x + y)/2;
	}
	
	public static void main(String[] args) {
		InterfazFuncionalApp app = new InterfazFuncionalApp();
		//Interfaz Funcional con Lambda
		double resul = app.operar(12,13);
		System.out.println(resul);
		//Interfaz Funcional con Sobreescritura de métodos
		double sobre = app.calcular(22, 23);
		System.out.println(sobre);
	}
	
}
