package java8.Colecciones;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ColeccionesApp {

	/**
	 * NUEVOS METODOS PARA COLECCIONES
	 */
	private List<String> lista;
	
	public void llenarLista() {
		lista = new ArrayList<>();
		
		lista.add("Uno");
		lista.add("Dos");
		lista.add("Tres");
	}
	
	/*IMPLEMENTANDO FOREACH
	 * Nueva forma de recorrer una lista
	*/
	public void usarForEach() {
		System.out.println("A través de JDK < 1.8 :::");
		
		//Recorriendo una colección a tráves de JDK < 1.8
		for (String elemento : lista) {
			System.out.println(elemento);
		}
		
		System.out.println("\nA través de Lambdas :::");
		
		//Usando lambdas (y meétodos de JDK 1.8)
		lista.forEach((elemento)-> System.out.println(elemento));
		
		System.out.println("\nUsando MethodReference :::");
		
		//Usando MethodReference
		lista.forEach(System.out::println);
		
	}
	
	/*
	 * IMPLEMENTANDO REMOVE-IF
	 * Quita algún elemento cuando exista alguna lógica*/
	public void usarRemoveIf() {
		//Esto ocasiona un ConcurrentModificationException
		/*for (String x : lista) {
			if(x.equalsIgnoreCase("UNO")){
				lista.remove(x);
			}
		}*/
		
		//Se tiene que hacer con un Iterator
		/*Iterator<String> it = lista.iterator();
		while (it.hasNext()) {
			if(it.next().equalsIgnoreCase("UNO")){
				it.remove();
			}
		}*/
		
		//Aplicando Lambdas y un método de Collections
		lista.removeIf(x -> x.equalsIgnoreCase("UNO"));
	}
	
	/*
	 * IMPLEMENTANDO
	 * Ordena una colección sin necesidad de agregar 
	 * algún algoritmo*/
	public void usarSort() {
		lista.sort((x, y) -> x.compareToIgnoreCase(y));
	}
	
	public static void main(String[] args) {
		ColeccionesApp app =  new ColeccionesApp();
		app.llenarLista();
		app.usarForEach();
		
		System.out.println("----------");
		app.usarSort();
		app.usarForEach();
		
		System.out.println("----------");
		app.usarRemoveIf();
		app.usarForEach();		
	}

}
