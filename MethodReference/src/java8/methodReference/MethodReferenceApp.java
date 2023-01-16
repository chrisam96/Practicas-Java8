package java8.methodReference;

import java.util.Arrays;
import java.util.Comparator;

public class MethodReferenceApp {

	/*
	 * METHOD REREFENCE
	 * >Una forma de tener codigo "Más legible"
	 * >Tiene una nomenclatura especial siendo:
	 * 		[NombreClase]::[metodoReferenciar()]
	 * >Se apoyan de las interfaces funcionales
	 * >NOTA: LAS REFERENCIAS A METODOS NO RECIBEN PARAMETROS
	 *	(No implmeneta la currificación) 
	 * 
	 * 
	 * >Los Method References trabajan con los métodos de las clases
	 * (de un objeto), no con el objeto en sí.
	 */
	public static void referenciarMetodoStatic() {
		System.out.println("Metodo tipo Static Referido");
	}

	/*
	 * OBJETO ARBITRARIO
	 * Variable cuya referencia a un objeto es ambigua,
	 * ya que cambia constantemente de objeto.
	 * 
	 * Es decir, la (referencia de la) variable apunta a un objeto u otro 
	 * según la funcionalidad de un segemento de código, por lo que 
	 * no es certero que esa variable apunte a un objeto en especifico.
	 * 
	 * Ejemplos: 
	 * > Una variable que se usa para recorrer una colección
	 * > Una variable que se usa en la devolución de un método nulleable
	 * 
	 */
	public <T> void referenciarMetodoInstanciaArbitrario() {
		//Dado un arreglo
		String[] nombres = {"Abeja","Bear","Carro"};
		
		//Ordenandolo con Comparator
		/*
		Arrays.sort(nombres, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		*/
		
		//Ordenandolo con Lambdas
		Arrays.sort(nombres, (s1, s2) -> s1.compareTo(s2) );
		
		
		//Ordenandolo con Method Reference
		/*
		 * El objeto arbitrario de este caso es de tipo String,
		 * ya que lo que se esta recorriendo es un Array de Strings.
		 * 
		 * >NOTA: Los Method References trabajan con los métodos de las clases
		 * (de un objeto), no con el propio objeto en sí.
		 */
		Arrays.sort(nombres, String::compareTo);
		
		//Impresión para pruebas
				System.out.println(Arrays.toString(nombres));
	}

	/*
	 * OBJETO PARTICULAR
	 * Variable cuya referencia a un objeto es fija y constante,
	 * es decir, apunta siempre al mismo objeto.
	 */
	public void referenciarMetodoInstanciaParticular() {
		System.out.println("Método Referido de un Objeto en Particular");
	}

	public void referenciarMetodoConstructor(){
		//De manera tradicional
		IPersona iper = new IPersona() {
			
			@Override
			public Persona crearPersona(int id, String nombre) {
				return new Persona(id, nombre);
			}
		};
		
		Persona p1 = iper.crearPersona(1, "Persona 1");
		
		//Aplicando Lambdas
		//IPersona iper2 = (id, nombre) -> {return  new Persona(id, nombre); };
				IPersona iper2 = (id, nombre) -> (new Persona(id, nombre));
		Persona p2 = iper2.crearPersona(2, "Persona 2");
		
	
		/*
		 * Aplicando lo mismo pero con Methood Reference
		 */
		IPersona iper3 = Persona::new;
		Persona p3 = iper3.crearPersona(3, "Persona 3");
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		
	}

	public static void operar() {
		//Llamando un método a tráves de Lambdas
		Operacion op = () -> MethodReferenceApp.referenciarMetodoStatic();
		op.saludar();
		
		//Lamando un método statico a tráves de Method Reference
		Operacion op2 = MethodReferenceApp::referenciarMetodoStatic;
		op2.saludar();
	}
	
	public static void main(String[] args) {
		MethodReferenceApp app =  new MethodReferenceApp();
		app.operar();
		app.referenciarMetodoInstanciaArbitrario();
		
		/*
		 * Al ser un objeto fijo, se puede hacer uso de las Method Reference 
		 * a tráves del objeto directamente
		 * 
		 * Si NO es un objeto arbitrario o el método NO es estático, entonces
		 * solo se puede usar las Method Reference a tráves de OBJETOS INSTANCIADOS
		 */
		Operacion op = app::referenciarMetodoInstanciaParticular;
		op.saludar();
		

		/* //Ejemplo de error
		 * Operacion op2 = MethodReferenceApp::referenciarMetodoInstanciaParticular;*/
		
		app.referenciarMetodoConstructor();
	}

}
