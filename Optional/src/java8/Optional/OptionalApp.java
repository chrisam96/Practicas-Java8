package java8.Optional;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalApp {

	/*
	 * OPTIONAL
	 * Gestiona (la obtención de) objetos de manera adecuada 
	 * para evitar los errores de tipo NullPointerException
	 */
	
	public void probar(String valor) {
		/* >>Los Optional deben ser inicializados, ya que
		 * sino arrojarían la excepción NoSuchElementException.
		 * 
		 * >>Hay dos formas de inicializarlo:
		 * 		>Optional.empty()
		 * 		>Optional.of()
		 * 
		 * La diferencia entre ellos es que empty() crea
		 * el objeto Optional y of() solo se creará a partir
		 * de obtener un objeto
		 * */
		if (valor == null) { valor = "nulo"; }
		
		try {
			Optional op = Optional.empty();
			op = op.of(valor);
			op.get();
		}catch(NoSuchElementException e) {
			System.out.println("No hay elementos en el Optional");
		}
		
		//Con un NULL arrojaría un error NullPointerException
		System.out.println(valor.contains("prueba"));
	}
	
	public void orElse(String valor) {
		/*
		 * Si entra un valor NULL como parametro en of(),
		 * entonces de todos modos se arrojará un
		 * NoSuchElementException
		 */
		//Optional<String> op = Optional.of(valor);
		//String x = op.get();
		
		/*
		 * Si el parametro/objeto del Optional se acepta como
		 * un NULLeable, entonces se debe cambiar de método a:
		 * 		Optional.ofNulleable()
		 * 
		 * Para settear un valor por defecto, 
		 * en el caso de que el Optional contenga un null,
		 * se debe usar el método orElse()
		 */
		Optional<String> op = Optional.ofNullable(valor);
		String x = op.orElse("Valor predeterminado");
		
		System.out.println(x);
	}
	
	public void orElseGet(String valor) {
		/*
		 * Si el parametro/objeto del Optional se acepta como
		 * un NULLeable, entonces se debe cambiar de método a:
		 * 		Optional.ofNulleable()
		 * 
		 * Para settear un valor por defecto, 
		 * en el caso de que el Optional devuelva un null,
		 * también se puede apoyar de una función que se 
		 * pasa como parametro en el método orElseGet()
		 */
		Optional<String> op = Optional.ofNullable(valor);
		String x = op.orElseGet(() -> "Valor predeterminado"
				+ "a tráves de una Lambda (expresión)");
		
		System.out.println(x);
	}
	
	public void orElseThrow(String valor) {
		/*
		 * Si el parametro/objeto del Optional se acepta como
		 * un NULLeable, entonces se debe cambiar de método a:
		 * 		Optional.ofNulleable()
		 * 
		 * Si el Optional contiene un NULL como valor
		 * tendremos la facultad de arrojar una Exception
		 * a tráves del método orElseThrow()
		 */
		Optional<String> op = Optional.ofNullable(valor);
		String x = op.orElseThrow(NumberFormatException::new);
		String y = op.orElseThrow(() -> new NumberFormatException());
		
		System.out.println(x);
	}
	
	public void isPresent(String valor) {
		/**
		 * isPresent() nos permite saber si lo que contiene
		 * el Optional es un NULL o un Objeto
		 */
		Optional<String> op = Optional.ofNullable(valor);	
		System.out.println("Contiene objeto?: " + op.isPresent());
	}
	
	
	public static void main(String[] args) {
		OptionalApp app = new OptionalApp();
		//app.probar(null);
		//app.probar("Esto es una prueba");
		//app.orElse("Esto es una prueba");
		app.orElseGet(null);
		//app.orElseThrow("Esto es una prueba");
		app.isPresent(null);
	}

}
