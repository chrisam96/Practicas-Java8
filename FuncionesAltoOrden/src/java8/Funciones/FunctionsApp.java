package java8.Funciones;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionsApp {

	/*
	 * FUNCIONES DE ALTO ORDEN
	 * >Son funciones que se toman como si fueran objetos, 
	 * tal como lo hace Javascript con sus objectos-funcion
	 * 
	 * >La programación de Funciones de Alto Orden se refieren
	 * al paso o devolución de funciones como parámetros
	 * (conocido como "programación entre paso de funciones")
	 * 
	 * >Al cuerpo de una Funcion de Alto Orden se le 
	 * puede entender como una expresión lambda:
	 * 
	 * FUNCTION: Expresión Lamda con un solo parametro de Entrada
	 * BIFUNCTION: Expresión Lamda con 2 parametros de Entrada
	 * 
	 * >La estructura de una Function es la Siguiente
	 * 
	 * 		Function<[Parametro#1],[Parametro#2]>
	 * 
	 * Parametro#1 = Tipo de dato que va a tener como ENTRADA
	 * Parametro#2 = Tipo de dato de SALIDA que retorna la función
	 * 
	 * >Para ejecutar al objeto Function se hace a tráves del
	 * método apply()}.
	 * 
	 */
	
	private Function<String, String> convertirMayus = x -> x.toUpperCase(); 
	private Function<String, String> convertirMinus = x -> x.toLowerCase();
	private Function<String, Integer> convertirNum = x -> Integer.parseInt(x);
	
	public void imprimir(Function<String, String> func, String valor) {
		System.out.println(func.apply(valor));
	}
	
	public void imprimirNum(Function<String, Integer> func, String valor) {
		System.out.println(func.apply(valor));
	}
	
	/**
	 * También pueden ser usados como métodos normales,
	 * donde siempre se retornará una Function 
	 * (funciona similar a la de JavaScript).
	 * 
	 * Al ser manejados así, se pueden envíar parametros extra
	 * dentro de la lambda.
	 */
	public Function<String, String> mostrar(String mensaje){
		return (String x) -> mensaje + x;
	}
	
	/*
	 * Un Predicate es basicamente una expresión Lambda con reglas.
	 * 
	 * Un Lambda de tipo Predicate obedece ciertas reglas:
	 * 
	 * > Solo posen un argumento (parametro de la Lambda)
	 * > Son Lambdas que siempre retornan un Booleano como respuesta
	 * (es decir, el cuerpo de la expresión debe devolver true/false)
	 *  
	 */
	public Predicate<String> filtroTamTexto(int longitud){
		return texto -> texto.length() < longitud;
	}
	
	public Predicate<String> filtroContieneTexto(String cadena){
		return texto -> texto.contains(cadena);
	}
	
	/*
	 * Se pueden usar los Predicate o Function en general,
	 * para operaciones más complejas
	 * 
	 */
	public void filtrarTam
		(List<String> lista, Consumer<String> consumidor,
		int longitud, String cadena) 
	{
		/*Recordemos que filter() recibe una Lambda(Function)
		 * de tipo Predicate como parametro*/
		lista.stream().filter(this.filtroTamTexto(longitud))
			.forEach(consumidor);
	}
	
	public void filtrarContieneTexto
	(List<String> lista, Consumer<String> consumidor,
			int longitud, String cadena) 
	{
		/*Recordemos que filter() recibe una Lambda(Function)
		 * de tipo Predicate como parametro*/
		lista.stream().filter(this.filtroContieneTexto(cadena))
			.forEach(consumidor);
	}

	public static void espacio() {
		System.out.println("\n==================================\n");
	}
	
	public static void main(String[] args) {
		FunctionsApp app = new FunctionsApp();
		
		app.imprimir(app.convertirMayus, "Hola Function");
		app.imprimir(app.convertirMinus, "Hola Function");
		app.imprimirNum(app.convertirNum, "100");
		
		espacio();
		
		String resp =  app.mostrar("Parametro").apply(" apply");
		System.out.println(resp);
		
		espacio();
		
		List<String> lista = new ArrayList<>();
		lista.add("uno");
		lista.add("dos");
		lista.add("tres");
		lista.add("cuatro");
		
		app.filtrarTam(lista, (String t) -> System.out.println(t), 4, "");
		//app.filtrarTam(lista, System.out::println, 4, "");
		
		espacio();
		
		app.filtrarContieneTexto(lista, t -> System.out.println(t), 0, "o");
		//app.filtrarContieneTexto(lista, System.out::println, 0, "o");
	}

}
