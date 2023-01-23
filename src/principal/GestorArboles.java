package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

			PreparedStatement pst = con.prepareStatement("INSERT INTO arboles VALUES (null,?,?,?,?,?)");
			
			PreparedStatement pstElim = con.prepareStatement("DELETE FROM arboles WHERE nombre_comun=?");
			
			;
				//"UPDATE arboles SET ID=10 WHERE ID=1"
			final int INSERTAR_ARBOL = 1;
			final int ELIMINAR_ARBOL = 2;
			final int MODIFICAR = 3;
			final int VISUALIZAR = 4;
			final int SALIR = 0;
//eneko petardo
			int opcion_menu;

			
			do {
				System.out.println("------MENU-------");
				System.out.println(INSERTAR_ARBOL + ".INSERTAR_ARBOL primera opcion");
				System.out.println(ELIMINAR_ARBOL + ".ELIMINAR_ARBOL segunda opcion");
				System.out.println(MODIFICAR + ".MODIFICAR tercera opcion");
				System.out.println(VISUALIZAR + ". VISUALIZAR cuarta opcion");
				System.out.println(SALIR + ". AGUR");
				System.out.println("Elije una de las opciones");
				opcion_menu = Integer.parseInt(scan.nextLine());

				switch (opcion_menu) {
				
				case INSERTAR_ARBOL:
					
					System.out.println("insertando arbol...");
					
					System.out.println("introduce el nombre");
					String nombreComun = scan.nextLine();	
					pst.setString(1, nombreComun);
					//para el ejercicio de arbol pst.setString(1, arbol1.getNombreComun());
					
					System.out.println("introduce el nombre cientifico");
					String nombreCientifico = scan.nextLine();
					pst.setString(2, nombreCientifico);
					
				
					System.out.println("introduce el habitat");
					String habitat = scan.nextLine();
					pst.setString(3, habitat);
				
					System.out.println("introduce la altura, numero entero");
					 int altura = Integer.parseInt(scan.nextLine());
					 pst.setInt(4,altura);
					 
					
					System.out.println("introduce el origen");
					String origen = scan.nextLine();
					pst.setString(5, origen);
					
					
					pst.executeUpdate();
					//pst.execute("INSERT INTO arboles (nombre_comun, nombre_cientifico, habitat, altura, origen) "
						//	+ "VALUES("+nombreComun+","+" "+nombreCientifico+","+""+habitat+","+" "+altura+","+" "+origen+")");
					
					System.out.println("Yes!! Árbol introducido correctamente"); 
					
					
					break; 
				case ELIMINAR_ARBOL:
					System.out.println("Eliminando un arbol...");
					System.out.println("Introduce el nombre del arbol a eliminar");
					String nombreEliminar= scan.nextLine();
					pstElim.setString(1, nombreEliminar);
					pstElim.executeUpdate();
					System.out.println(nombreEliminar + "eliminado");
					
					break;
				case MODIFICAR:
					System.out.println("Modicifando arbol...");
					System.out.println("introduce id del arbol a modificar :");
					int idmod = Integer.parseInt(scan.nextLine());
					
					
					PreparedStatement pstModif = con.prepareStatement("UPDATE arboles set nombre_comun=(?) WHERE id=?");
					System.out.println("introduce nombre nuevo");
					String nombreN = scan.nextLine();
					
					//pstModif.execute("UPDATE arboles SET nombre_comun ='"+ nombreN+"' WHERE id='"+ idModificar+"'");
					pstModif.setString(1,nombreN);
					pstModif.setInt(2, idmod);
					pstModif.executeUpdate();
					
					PreparedStatement pstModif2 = con.prepareStatement("UPDATE arboles set nombre_cientifico=(?) WHERE id=?");
					System.out.println("introduce nombre cientifico nuevo");
					String nombreC = scan.nextLine();
					//pstModif.execute("UPDATE arboles SET nombre_cientifico ='"+ nombreC+"' WHERE id='"+ idModificar+"'");
					pstModif2.setString(1,nombreC);
					pstModif2.setInt(2, idmod);
					pstModif2.executeUpdate();
					
					PreparedStatement pstModif3 = con.prepareStatement("UPDATE arboles set habitat=(?) WHERE id=?");
					System.out.println("introduce habitat nuevo");
					String habitatN = scan.nextLine();
					//pstModif.execute("UPDATE arboles SET habitat ='"+ habitatN+"' WHERE id='"+ idModificar+"'");
					pstModif3.setString(1,habitatN);
					pstModif3.setInt(2, idmod);
					pstModif3.executeUpdate();
					
					PreparedStatement pstModif4 = con.prepareStatement("UPDATE arboles set altura=(?) WHERE id=?");
					System.out.println("introduce altura");
					int alturaN = Integer.parseInt(scan.nextLine());
					//pstModif.execute("UPDATE arboles SET altura ='"+ alturaN+"' WHERE id='"+ idModificar+"'");
					pstModif4.setInt(1,alturaN);
					pstModif4.setInt(2, idmod);
					pstModif4.executeUpdate();
					
					PreparedStatement pstModif5 = con.prepareStatement("UPDATE arboles set origen=(?) WHERE id=?");
					System.out.println("introduce origen nuevo");
					String origenN = scan.nextLine();
					//pst.execute("UPDATE arboles SET origen ='"+ origenN+"' WHERE id='"+ idModificar+"'");
					pstModif5.setString(1,origenN);
					pstModif5.setInt(2, idmod);
					pstModif5.executeUpdate();
					
					//pstModif.executeUpdate();
					
					System.out.println("modificado ok!");
					
					break;
				case VISUALIZAR:
					System.out.println("cuarta opcion seleccionada\n");
					
					String sentenciaSelect = "SELECT *FROM arboles";
					ResultSet resultado = pst.executeQuery(sentenciaSelect);
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
			/*ResultSet resultado = st.executeQuery(sentenciaSelect);
			while (resultado.next()) {
				System.out.println(resultado.getInt(1) + "  -  " + resultado.getString(2));
			}*/

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
	}
}
