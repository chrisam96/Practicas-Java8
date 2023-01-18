package java8.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapApp {

	/*
	 * MAP
	 * Se cambiaron varios métodos para la manipulación 
	 * de los Maps, apoyandosé de Lambdas
	 */
	private Map<Integer, String> map;
	
	public void poblar() {
		map =  new HashMap<>();
		map.put(1, "uno");
		map.put(2, "dos");
		map.put(3, "tres");
	}
	
	public void imprimir_V7() {
		for (Map.Entry<Integer, String> en : map.entrySet()) {
			Integer key = en.getKey();
			String val = en.getValue();
			System.out.println("Llave: " + en.getKey() +
					" Valor: " + en.getValue());
		}
	}
	
	public void imprimir_V8() {
		//Con ayuda de Lambdas y ForEach()
		map.forEach((key, value) -> 
			System.out.println("Llave: " + key +
			" Valor: " + value));
		
		System.out.println();
		
		//Con ayuda del framework Stream
		map.entrySet().stream().forEach(System.out::println);
	}
	
	public void recolectar() {
		/*
		 * Filtra los mapas en base a un Lambda (Function), 
		 * creando así un nuevo mapa
		 * 
		 * Se usa entrySet() para usar la API stream:
		 * 		= map.entrySet().stream()
		 * 
		 * Se usa para filter() con una operación lambda de 
		 * parametro para purgar la lista 
		 * 		.filter(entry -> entry.getValue().contains("o")) 
		 * 
		 * Con collect() obtenemos los datos para el nuevo mapa,
		 * para ello nos apoyamos del método estático de 
		 * "Collectors.toMap()" 
		 * 
		 * Cada parametro del método Collectors.toMap() son los 
		 * mismos valores que componen al objeto Entry, 
		 * es decir, su "Key" y "Value".
		 * Entry = {Key, Value}. 
		 * 		.collect(Collectors.toMap(
						objEntry -> objEntry.getKey(),
						objEntry -> objEntry.getValue()
					)
				);
		 */
		Map<Integer, String> mapRecolectado
			= map.entrySet().stream()
			.filter(entry -> entry.getValue().contains("o"))
			.collect(Collectors.toMap(
						objEntry -> objEntry.getKey(),
						objEntry -> objEntry.getValue()
					)
				);
		
		mapRecolectado.forEach(
			(llave, valor) -> 
			System.out.println("Llave: " + llave 
					+ " - valor: " + valor)
		);
	}
	
	//Agrega un valor si no existe dentro del Map
	public void insertarSiAusente() {
		//Agrega un valor si no se encuentra
		map.putIfAbsent(4, "cuatro");
		
		//No agregará 
		map.putIfAbsent(3, "otro tres");
	}
	
	public void operarSiPresente() {
		/*Si encuentra el valor dentro del Map,
		 * entonces se hará la operación lambda
		 * enviada
		 * */
		map.computeIfPresent(3, (k, v) -> k +v);
		System.out.println(map.get(3));
	}
	
	public void operarSiAusente() {
		/*
		 * Si NO SE HAYA el valor, lo agregará
		 * y manipulará bajó una Function envíada,
		 * o sea, la Lambda envíada como el segundo parametro 
		 */
		map.computeIfAbsent(6, (v) -> {
				System.out.println("v tiene " + v);
				return "v es: " + v;
			}
		);
		System.out.println(map.get(6));
	}
	
	public void obtenerOrPredeterminado() {
		/*
		 * En caso de no encontrar algún valor dentro del Map,
		 * devolverá un valor por defecto
		 */
		String valor = map.getOrDefault(8, "VALOR POR DEFECTO");
		System.out.println(valor);
	}
	
	public void espacioEntrePruebas(boolean impr) {
		System.out.println("-----------------------");
		if(impr) {imprimir();}
	}
	
	public void imprimir() {
		map.forEach((key, value) -> 
			System.out.println("Llave: " + key +
			" Valor: " + value)
		);
	}
	
	public static void main(String[] args) {
		MapApp app = new MapApp();
		app.poblar();
		
		app.imprimir_V7();
		app.espacioEntrePruebas(false);
		app.imprimir_V8();		
		
		app.insertarSiAusente();
		app.espacioEntrePruebas(true);
		
		app.operarSiPresente();
		app.espacioEntrePruebas(true);
		
		app.operarSiAusente();
		app.espacioEntrePruebas(false);
				
		app.recolectar();
	}

}
