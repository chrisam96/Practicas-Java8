package java8.DateApi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateAPIApp {

	public void verificar (boolean version) {
		//Version 1.7 JDK
		if(version == true) {
			//Se obtiene una instancia de la fecha actual
			Calendar fecha1 = Calendar.getInstance();
			Calendar fecha2 = Calendar.getInstance();
			
			//Los meses van del 0 - 11 en JDK < 1.8
			fecha1.set(1997, 0, 21);
			System.out.println("La fecha1 es despues (After) "
					+ "de fecha2:" + fecha1.after(fecha2));
		}
		//Version 1.8 JDK
		else {
			//Los meses son ya naturales, van del 1-12 
			LocalDate fecha1 = LocalDate.of(1997, 1, 21);
			LocalDate fecha2 = LocalDate.now();
			
			//Los métodos para las FECHAS son más precisos
			System.out.println("La fecha1 es DESPUES (isAfter) "
					+ "de fecha2: " + fecha1.isAfter(fecha2));
			System.out.println("La fecha1 es ANTES (isBefore) "
					+ "de fecha2: " + fecha1.isBefore(fecha2));
			System.out.println("La fecha1 es IGUAL (isEqual) "
					+ "de fecha2: " + fecha1.isEqual(fecha2));
			
			espacio();
			
			//Es más naturales manejar las horas con la nueva API 
			LocalTime hora1 = LocalTime.of(20, 36, 21);
			LocalTime hora2 = LocalTime.now();
			
			//Los métodos para las FECHAS son más precisos
			System.out.println("La hora1 es DESPUES (isAfter) "
					+ "de hora2: " + hora1.isAfter(hora2));
			System.out.println("La hora1 es ANTES (isBefore) "
					+ "de hora2: " + hora1.isBefore(hora2));
			
			espacio();
			
			/*Ya no es complejo manejar Fechas y horas en un solo objeto.
			 * Los primeros 3 digitos son para la FECHA.
			 * Los ultimos 3 digitos son para la HORA.*/
			LocalDateTime fecha_y_hora1 = LocalDateTime.of(1998, 4, 21, 10, 14, 26);
			LocalDateTime fecha_y_hora2 = LocalDateTime.now();
			
			//Los métodos para las FECHAS son más precisos
			System.out.println("La fecha_y_hora1 es DESPUES (isAfter) "
					+ "de fecha_y_hora2: " 
					+ fecha_y_hora1.isAfter(fecha_y_hora2));
			System.out.println("La fecha_y_hora1 es ANTES (isBefore) "
					+ "de fecha_y_hora2: " 
					+ fecha_y_hora1.isBefore(fecha_y_hora2));
			System.out.println("La fecha_y_hora1 es IGUAL (isEqual) "
					+ "de fecha_y_hora2: " 
					+ fecha_y_hora1.isEqual(fecha_y_hora2));
			
		}
	}
	
	//Uso de CurrentTimeMillis
	public void medirTiempo(boolean version) throws InterruptedException {
		//Version 1.7 JDK
		if (version == true) {
			/**
			 * La única forma de medir el tiempo es
			 * a tráves de Milisegundos
			 */
			long inicio = System.currentTimeMillis();
			Thread.sleep(1000);
			long fin = System.currentTimeMillis();
			System.out.println("La diferencia es: "
					+ (fin - inicio));
		} 
		//Version 1.8 JDK
		else {
			/*
			 * En JDK 1.8 se puede hacer a tráves de la clase INSTANT
			 * para medir el tiempo (se obtiene un punto en el tiempo) 
			 * 
			 * Para obtener la diferencia entre ellos se apoya
			 * de la clase DURATION */
			Instant	inicio = Instant.now();
			Thread.sleep(1000);
			Instant fin = Instant.now();
			System.out.println("La diferencia es:" 
					+ Duration.between(inicio, fin));
			
		}
	}
	
	//Uso de DateFormat
	public void convertir(boolean version) throws ParseException {
		//Version 1.7 JDK
		if (version) {
			String fecha = "21/01/1991";
			DateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaConvertida = formateador.parse(fecha);
			System.out.println(fechaConvertida);
			
			Date fechaActual = Calendar.getInstance().getTime();
			formateador =  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
			String fechaCadena = formateador.format(fechaActual);
			System.out.println(fechaCadena);
		}
		//Version 1.8 JDK
		else {
			String fecha = "20/04/2009";
			DateTimeFormatter formateador 
				= DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate fechaLocal = LocalDate.parse(fecha, formateador);
			System.out.println("Imprimiendo LocalDate" + fechaLocal);
			
			formateador = DateTimeFormatter.ofPattern("dd:MM:yyyy");
			System.out.println("Imprimiendo DateTimeFormatter: " 
					+ formateador.format(fechaLocal));
		}
	}
	
	//Uso de Calendar VS Period
	public void periodoEntrefechas(boolean version) {
		//Version 1.7 JDK
		if (version) {
			/*
			 * Forma burda de contar la diferencia de años
			 * */
			Calendar nacimiento = Calendar.getInstance();
			Calendar actual = Calendar.getInstance();
			
			nacimiento.set(1997, 0, 24);
			actual.set(2006, 10, 4);
			
			int anios = 0;
			
			while(nacimiento.before(actual)) {
				nacimiento.add(Calendar.YEAR, 1);
				if (nacimiento.before(actual)) {
					anios++;
				}
			}
			
			System.out.println("Diferencia de años:  "+anios);
		}
		//Version 1.8 JDK
		else {
			/*
			 * Gracias a la nueva API podemos tener 
			 * con exactitud la diferencia entre dos fechas
			 */
			LocalDate nacimiento =  LocalDate.of(1997, 1, 24);
			LocalDate actual = LocalDate.now();
			
			Period periodo = Period.between(nacimiento, actual);
			System.out.println("Han transcurrido " 
				+ periodo.getYears() + " años, " 
				+ periodo.getMonths() + " meses y "
				+ periodo.getDays() + " días"
				+ " desde " + nacimiento + " hasta " + actual);
		}
	}
	
	public void espacio() {
		System.out.println("\n==================================\n");
	}
	
	public static void main(String[] args) {
		DateAPIApp app =  new DateAPIApp();
		
		/*
		 * Para pruebas
		 * //Version 1.7 JDK = true
		 * //Version 1.8 JDK = false
		 */
		app.verificar(false);
		try {
			app.medirTiempo(false);
			app.periodoEntrefechas(false);
			app.convertir(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
