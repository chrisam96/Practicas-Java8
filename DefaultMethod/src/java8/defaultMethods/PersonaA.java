package java8.defaultMethods;

public interface PersonaA {
	public void caminar();
	default public void hablar() {
		System.out.println("Saludos desde PersonaA.hablar()");
	}
	default public void respirar() {
		System.out.println("Saludos desde un método no "
				+ "sobreescrito - PersonaA.respirar()");
	}
	default public void observar(){
		System.out.println("Saludos desde un método "
				+ "por default NO SOBREESCRITO "
				+ "- PersonaA.observar()");
	}
}
