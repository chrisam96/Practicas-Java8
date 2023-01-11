package java8.defaultMethods;

public class DefaultMethods implements PersonaA, PersonaB{

	/*
	 * DEFAULT METHODS
	 * Es un método que está implementado en una interfaz 
	 * para que cualquier clase que implemente esta interfaz 
	 * ya tenga acceso al método definidos
	 */ 
	 

	@Override
	public void caminar() {
		System.out.println("Hola desde la implementacion");
	}
	
	/* > Al haber N interfaces con m-metodos llamados iguales,
	 * Java no es capaz de saber cual implementar, por lo que,
	 * se sobreescribe implementandolo a tráves de la siguiente
	 * estructra:
	 * 
	 * "NombreClase.super.nombreMetodo();"
	 * 
	 */	
	@Override
	public void hablar() {
		/*Si se desea implementar la otra interfaz, 
		 * basta con solo cambiar de interfaz*/
		PersonaB.super.hablar();
	}
	
	/*Si un metodo es sobrescrio, reemplaza la funcionalidad
	 * de la interfaz*/
	@Override
	public void observar() {
		System.out.println("Metodo sobreescrito de "
				+ "-> PersonaA.observar()");
	}

	public static void main(String[] args) {
		DefaultMethods app = new DefaultMethods();
		
		/*Implememntando el método por default de la Interfaz 
		 * */
		app.hablar();
		app.respirar();
		/*
		 * NOTA: Para usar el <<Método por Default>> 
		 * se debe descomentar su sobreescritura
		 */
		app.observar();
	}

}
