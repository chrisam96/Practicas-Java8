package java8.interfazFuncional;

@FunctionalInterface
public interface Operacion {
	public double calcular(double x, double y);
	/**INTERAZ FUNCIONAL
	 * >Una interfaz funcional es una INTERFAZ DE UN SOLO METODO
	 * 
	 * >Tiene su propia Anotacion @FunctionalInterface para 
	 * declarar dicho tipo de interaz 
	 * 
	 * Una interfaz funcional es aquella que 
	 * solamente define una única operación .:
	 * >Al colocar uno o más métodos en la Interfaz,
	 * marcará error
	 * 
	 * 
	 * */
	//int calcular2();
}
