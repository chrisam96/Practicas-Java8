package java8.defaultMethods;

public interface PersonaB {
	default public void hablar() {
		System.out.println("Saludos desde PersonaB.hablar()");
	}
}
