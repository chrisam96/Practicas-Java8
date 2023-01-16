package java8.StreamAPI;

import java.util.ArrayList;
import java.util.List;

public class ParallelStreamApp {
	
	/*
	 * API STREAM - PARALLEL STREAM
	 * A partir de JDK 1.8 y bajo el paradigma declarativo,
	 * se ha implementado una API para la manipulación de 
	 * las Colecciones.
	 * 
	 * Se invoca de la siguiente forma:
	 * [coleccion].parallelStream().[nombreMetodo()]
	 * 
	 * Utiliza el API de forJoin o forkJoin de la JDK 1.7
	 * para su implementación
	 */
	
	private List<Integer> numeros;
	
	private void llenar() {
		
		numeros =  new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			numeros.add(i);
		}
	}
	
	//Trabaja de forma secuencial (0.. 9)
	public void probarStream() {
		numeros.stream().forEach(System.out::println);
	}
	
	//Trabaja en un procesamiento con hilos (Thread)
	public void probarParalelo() {
		numeros.parallelStream().
			forEach(x -> System.out.println(x));
	}

	public static void main(String[] args) {
		ParallelStreamApp app = new ParallelStreamApp();
		
		app.llenar();
		System.out.println("De forma secuencial");
		app.probarStream();
		System.out.println("\nCon Hilos:");
		app.probarParalelo();
	}

}
