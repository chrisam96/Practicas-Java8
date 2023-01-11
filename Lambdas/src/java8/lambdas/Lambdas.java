package java8.lambdas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Lambdas {

	/* PARADIGMA FUNCIONAL
	 * Es un tipo del paradigma de la programación declarativa
	 * basada en el uso de funciones matematicas
	 * 
	 * PROGRAMACION DECLARATIVA vs FUNCIONAL
	 * Prog. Declarativa: 
	 * Se indican todas las sentencias 
	 * a un determinado problema. 
	 * 
	 * En el paradigma declarativa, yo estoy diciendo al 
	 * lenguaje de programación que es lo que quiero, 
	 * mas no digo cómo implementarlo internamente 
	 * para lograr ese objetivo.
	 * 
	 * Por ejemplo, En el lenguaje SQL
	 * si se quiere todos los usuarios que cuyo nombre 
	 * empiece con la letra "a", se hace 
	 * "select * from usuarios where nombre like 'a%'
	 * 
	 * Prog. Imperativa: 
	 * Se dan/indican las instrucciones paso a paso.
	 * 
	 * Por ejemplo, Si pidieran el promedio,
	 * yo tendría que colocar un número tras otro número, 
	 * sumarlos todos y luego dividir entre la cant. de números,
	 * eso es una programación imperativa porque estoy indicando
	 * todos los pasos para lograr el objetivo
	 * 
	 * EJEMPLOS DE LENG. DECLARATIVA
	 * 1.SQL
	 * 2.SWI Prolog
	 * 3.LISP
	 * 4.Haskell
	 * 
	 * EJEMPLOS DE LENG. IMPERATIVA
	 * 1.Java
	 * 2.GO
	 * 3.PHP
	 * 4.Python
	 * 
	 * 
	 * LAMBDAS
	 * Son funciones anónimas. Presenta la siguiente estructura:
	 * (parametros) -> { expresion o cuerpo de método }
	 * 
	 * 
	 * 
	 */
	
	/* LAMBDAS
	 * Son funciones anónimas. Presenta la siguiente estructura:
	 * (parametros) -> { expresion o cuerpo de método } */
	public void ordenar() {
		/*Dada una lista de objetos, se pueden ordenar apoyandosé
		 * de las lambdas
		 * */
		List<String> lista = new ArrayList<>();
		lista.add("Uno");
		lista.add("Dos");
		lista.add("Tres");
		
		//--> Ordenar Listas con
		
		//Antes con JDK < 1.8
		Collections.sort(lista, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				//Comparación interna a través de la clase interna
				return 0;
			}
			
		});
		
		//Usando lambdas, JDK 1.8
		Collections.sort(lista, (String s1, String s2) -> s1.compareTo(s2));
		
		for (String elemento : lista) {
			System.out.println(elemento);
		}
		
	}
	
	/*	LAMBDAS Y LAS INTERFACES
	 *  Las Lambdas pueden sustituir Interfaces, siempre y
	 * cuando sean <<Interfaces Funcionales>>, es decir,
	 * de una sola método/función
	 */
	public void calcular() {
		
		//Implementado la interfaz de la forma JDK < 1.8
		Operacion op = new Operacion() {
			
			@Override
			public double calcular(double n1, double n2) {
				return (n1 + n2) / 2;
			}
		};
		
		//Implementando la forma del JDK 1.8
		Operacion op1 = (double n1, double n2) -> (n1+n2) / 2;
		
		System.out.println(op1.calcular(12, 13));
	}
	
	/* DIFERENTES SINTAXIS
	 * Existen diferentes formas de escribir el s
	 */
	//
	public double probandoSintaxis(){
		/*Las lambdas se componen de
		 * (parametros)-> { expresion (funciones) }*/
		Operacion op  = (double x, double y) -> (x + y)/2;
		//Pueden contener "RETURN" y llaves
		Operacion op2  = (double x, double y) -> {return (x + y)/2;};
		/*Las llaves son útiles para expresiones multilinea*/
		Operacion op3  = (double x, double y) -> {
			double a = 2.0;
			System.out.println(a);
			return (x + y) / 2 + a;
		};
		/*No es necesario escribir el tipo de dato, ya que 
		 * implicitamente Java sabe el tipo*/
		Operacion op4  = (x, y) -> (x + y) / 2;

		/*NOTA: A partir de aquí, descomentar la otra intefaz
		 * y comentar la anterior usada
		 * */
		/*
		//El tipo de retorno puede ser una constante
		Operacion op5  = () -> 2;
		/*Cuando no se desea enviar parametros en las
		 * Lambdas, se dejan los parentesis vacíos *
		Operacion op6  = () -> {
			int x = 2;
			int y = 3;
			return x + y;
		};*/
		return op3.calcular(12, 13);
	}
	
	/*	SCOPES DE LAS VARIABLES EN LAMBDAS
	 *  Pueden existir variables externas dentro de
	 *  las Lambdas siempre y cuando estas sean:
	 *  > Variables ESTATICAS
	 *  > Variables DE CLASE
	 *  
	 *  Variables locales pueden existir sí y solo sí
	 *  son de tipo FINAL
	 */
	private double varDeClase;
	private static double varEstatica;
	
	public double probandoScopeDeLambdas() {
		
		//Variable local
		double n3 = 3;
		
		//Comparando con funciones de clases anonimas		
		Operacion op = new Operacion() {
			
			@Override
			public double calcular(double n1, double n2) {
				/*No es posible por el scope 
				 * (no es variable de tipo FINAL)*/
				//n3 = n1 + n2;
				
				return n1 + n2 + n3;			
			}
		};
		
		Operacion op2 = (x, y) -> {
			/*No es posible por el scope 
			 * (no es variable de tipo FINAL)*/
			//return n3 = x + y;
			
			return x + y + n3;
		};
		
		//Usando variable estatica
		Operacion op3 = (x, y) -> {
			varEstatica = x + y;
			return varEstatica;
		};
		
		//Usando variable de Clase
		Operacion op4 = (x, y) -> {
			varDeClase = x + y + 1;
			return varDeClase;
		};
		
		return op.calcular(12, 13);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Lambdas app =  new Lambdas();
		//app.ordenar();
		//app.calcular();
		//System.out.println(app.probandoSintaxis());
		System.out.println(app.probandoScopeDeLambdas());
	}

}
