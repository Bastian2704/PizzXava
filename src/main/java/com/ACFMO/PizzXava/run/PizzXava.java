package com.ACFMO.PizzXava.run;

import org.openxava.util.*;

/**
 * Ejecuta esta clase para arrancar la aplicación.
 */

public class PizzXava {

	public static void main(String[] args) throws Exception {
		DBServer.start("PizzXava-db"); // Para usar tu propia base de datos comenta esta línea y configura src/main/webapp/META-INF/context.xml
		AppServer.run("PizzXava"); // Usa AppServer.run("") para funcionar en el contexto raíz
	}

}
