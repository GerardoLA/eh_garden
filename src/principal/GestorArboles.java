package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class GestorArboles {
	private static final String HOST = "localhost";
	private static final String BBDD = "eh_garden";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// INSERT INTO tablaprueba (nombre) VALUES ('gato')

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/eh_garden", "root", "");

			Statement st = con.createStatement();

			final int INSERTAR_ARBOL = 1;
			final int ELIMINAR_ARBOL = 2;
			final int MODIFICAR = 3;
			final int VISUALIZAR = 4;
			final int SALIR = 0;

			int opcion_menu;

			do {
				System.out.println("------MENU-------");
				System.out.println(INSERTAR_ARBOL + ". primera opcion");
				System.out.println(ELIMINAR_ARBOL + ". segunda opcion");
				System.out.println(MODIFICAR + ". tercera opcion");
				System.out.println(VISUALIZAR + ". cuarta opcion");
				System.out.println(SALIR + ". AGUR");
				System.out.println("Elije una de las opciones");
				opcion_menu = Integer.parseInt(scan.nextLine());

				switch (opcion_menu) {
				case INSERTAR_ARBOL:
					System.out.println("insertando arbol...");
					System.out.println("introduce el nombre");
					String nombreComun = scan.nextLine();
					st.execute("INSERT INTO arboles (nombre_comun) VALUES ('" + nombreComun+ "')");
					
					System.out.println("introduce el nombre cientifico");
					String nombreCientifico = scan.nextLine();
					st.execute("INSERT INTO arboles (nombre_cientifico) VALUES ('" + nombreCientifico+ "')");
					
					
					System.out.println("introduce el habitat");
					String habitat = scan.nextLine();
					st.execute("INSERT INTO arboles (habitat) VALUES ('" + habitat+ "')");
					

					System.out.println("introduce la altur");
					 int altura = scan.nextInt();
					st.execute("INSERT INTO arboles (altura) VALUES ('" + altura+ "')");
					
					

					System.out.println("introduce el origen");
					String origen = scan.nextLine();
					st.execute("INSERT INTO arboles (origen) VALUES ('" + origen+ "')");
					
					break;
				case ELIMINAR_ARBOL:
					System.out.println("Eliminando un arbol...");
					System.out.println("Introducel la id del arbol a eliminar");
					int idEliminar= scan.nextInt();
					st.execute("DELETE FROM arboles WHERE id = '"+ idEliminar +"'");
					
					break;
				case MODIFICAR:
					System.out.println("Modicifanco arbol...");
					System.out.println("introduce la id del arbol a modificar :");
					int idModificar = scan.nextInt();
					
					System.out.println("introduce nombre nuevo");
					String nombreN = scan.nextLine();
					st.execute("UPDATE arboles SET nombre_comun ='"+ nombreN+"' WHERE id='"+ idModificar+"'");
					
					System.out.println("introduce nombre cientifico nuevo");
					String nombreC = scan.nextLine();
					st.execute("UPDATE arboles SET nombre_cientifico ='"+ nombreC+"' WHERE id='"+ idModificar+"'");
					
					System.out.println("introduce habitat nuevo");
					String habitatN = scan.nextLine();
					st.execute("UPDATE arboles SET nombre_cientifico ='"+ habitatN+"' WHERE id='"+ idModificar+"'");
					
					System.out.println("introduce altura");
					String alturaN = scan.nextLine();
					st.execute("UPDATE arboles SET nombre_cientifico ='"+ alturaN+"' WHERE id='"+ idModificar+"'");
					
					
					System.out.println("introduce origen nuevo");
					String origenN = scan.nextLine();
					st.execute("UPDATE arboles SET nombre_cientifico ='"+ origenN+"' WHERE id='"+ idModificar+"'");
					
					
					
					
					break;
				case VISUALIZAR:
					System.out.println("cuarta opcion seleccionada\n");
					
					String sentenciaSelect = "SELECT *FROM arboles";
					ResultSet resultado = st.executeQuery(sentenciaSelect);
					while(resultado.next()) {
						System.out.println(resultado.getInt(1)+"  -  "+resultado.getString(2));
					}
					break;
				case SALIR:
					System.out.println("ADIOS");
					break;
				default:
					System.out.println("Opcion incorrecta!");
				}

			} while (opcion_menu != SALIR);
			scan.close();

			String sentenciaSelect = "SELECT *FROM arbol";
			ResultSet resultado = st.executeQuery(sentenciaSelect);
			while (resultado.next()) {
				System.out.println(resultado.getInt(1) + "  -  " + resultado.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
	}
}
