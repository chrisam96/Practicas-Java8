package java8.StreamAPI;

import java.util.ArrayList;
import java.util.List;

public class StreamApp {

	/*
	 * API STREAM
	 * A partir de JDK 1.8 y bajo el paradigma declarativo,
	 * se ha implementado una API para la manipulación de 
	 * las Colecciones.
	 * 
	 * Se invoca de la siguiente forma:
	 * [coleccion].stream().[nombreMetodo()]
	 */
	private List<String> lista;
	private List<String> numeros;
	
	public StreamApp() {
		lista =  new ArrayList<>();
		lista.add("Uno");
		lista.add("Dos");
		lista.add("Tres");
		lista.add("Cuatro");
		
		numeros =  new ArrayList<>();
		numeros.add("1");
		numeros.add("2");
		numeros.add("3");
	}
	
	public void filtrar() {
		//A partir del método stream(), tenemos varios filtros
		lista.stream().filter(x -> x.startsWith("C")).
			forEach(System.out::println);
	}

	public void ordenar() {
		//Por defecto se ordena de forma ascendente
		lista.stream().sorted().
			forEach(System.out::println);
		
		System.out.println("\nOrdenamiento de forma descendente");
		
		//Ordenamiento de forma descendente
		lista.stream().sorted((x, y) -> y.compareToIgnoreCase(x))
			.forEach(System.out::println);;
	}

	public void transformar() {
		/**
		 * FUNCION MAP()
		 * >Es una función de transformación
		 * 
		 * >Transformar a los elementos de una colección,
		 * a tráves de la expresión Lambda que se le pasa 
		 * como parametro
		 */
		
		//Aquí se transforma a mayúsculas
		lista.stream().map(x -> x.toUpperCase()).
			forEach(System.out::println);
		
		/*
		 * Aquí se transforma la lista <<números>> 
		 * de tipo String a Integer (antes JDK < 1.8)
		 * */
		for (String x : numeros) {
			int num = Integer.parseInt(x) + 1;
			System.out.println(num);
		}
		
		/*
		 * Aquí se transforma la lista <<números>> 
		 * de tipo String a Integer (JDK 1.8)
		 * */
		numeros.stream().map(x -> Integer.parseInt(x) + 1)
			.forEach(System.out::println);
	}

	public void limitar() {
		//Límitar la cantidad de elementos de una colección
		lista.stream().limit(2).forEach(System.out::println);
	}

	public void contar() {
		//Contar la cantidad de elementos de una colección
		long cuenta = lista.stream().count();
		System.out.print("Hay " + cuenta + " elemento(s)");
	}
	
	public static void main(String[] args) {
		StreamApp app = new StreamApp();
		System.out.println("Filtrar los elementos:");
		
		app.filtrar();
		System.out.println("\nOrdenar los elementos:");
		
		app.ordenar();
		System.out.println("\nTransformar los elementos:");
		
		app.transformar();
		System.out.println("\nLimitar los elementos:");
		
		app.limitar();
		System.out.println("\nContar los elementos:");
		
		app.contar();
		System.out.println();
		
	}

}
