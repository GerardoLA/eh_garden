package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestorArboles {
	
	private static final String HOST = "localhost";
	private static final String BBDD = "eh_garden";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);

		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			
			final int INSERTAR_ARBOL = 1;
			final int ELIMINAR_ARBOL = 2;
			final int MODIFICAR = 3;
			final int VISUALIZAR = 4;
			final int SALIR = 0;

			int opcion_menu;

			
			Arbol arbol=new Arbol();
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
					
					insertarArbol();
					System.out.println("Yes!! Árbol introducido correctamente"); 
					break; 
					
				case ELIMINAR_ARBOL:
					
					eliminarArbol();
					System.out.println(  "eliminado");
					break;
					
				case MODIFICAR:
					
					modificar();
					System.out.println("modificado ok!");
					break;
					
				case VISUALIZAR:
					visualizar();
					break;
					
				case SALIR:
					System.out.println("ADIOS");
					break;
					
				default:
					System.out.println("Opcion incorrecta!");
				}

			} while (opcion_menu != SALIR);
			scan.close();


		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
	}

	private static void insertarArbol() throws SQLException {
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/eh_garden", "root", "");
		Scanner scan = new Scanner(System.in);
		Arbol arbol =new Arbol();
		PreparedStatement pst = con.prepareStatement("INSERT INTO arboles VALUES (null,?,?,?,?,?)");
		
		System.out.println("insertando arbol...");
		System.out.println("introduce el nombre");
		arbol.setNombreComun(scan.nextLine());
		System.out.println("introduce el nombre cientifico");
		arbol.setNombrecientifico(scan.nextLine());
		System.out.println("introduce el habitat");
		arbol.setHabitat(scan.nextLine());
		System.out.println("introduce la altura, numero entero");
		arbol.setAltura(Integer.parseInt(scan.nextLine()));
		System.out.println("introduce el origen");
		arbol.setOrigen(scan.nextLine());

		
		pst.setString(1, arbol.getNombreComun());
		pst.setString(2, arbol.getNombrecientifico());
		pst.setString(3, arbol.getHabitat());
		pst.setInt   (4,arbol.getAltura());
		pst.setString(5, arbol.getOrigen());
		
		pst.execute();
		
	}
	
	
	private static void modificar() throws SQLException {
		
		Arbol arbol = new Arbol();
		Scanner scan =new Scanner(System.in);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/eh_garden", "root", "");
		PreparedStatement pstModif = con.prepareStatement("UPDATE arboles set nombre_comun=?,nombre_cientifico=?,habitat=?,altura=?,origen=? WHERE id=?");
		
		System.out.println("Modicifando arbol...");
		
		System.out.println("introduce id del arbol a modificar :");
		arbol.setId(Integer.parseInt(scan.nextLine()));
		System.out.println("introduce nombre nuevo");
		arbol.setNombreComun(scan.nextLine());
		System.out.println("Nombre cientifico nuevo :");
		arbol.setNombrecientifico(scan.nextLine());
		System.out.println("Habitat nuevo : ");
		arbol.setHabitat(scan.nextLine());
		System.out.println("Altura nueva : ");
		arbol.setAltura(Integer.parseInt(scan.nextLine()));
		System.out.println("Origen : ");
		arbol.setOrigen(scan.nextLine());
		
		pstModif.setString(1,arbol.getNombreComun());
		pstModif.setString(2,arbol.getNombrecientifico());
		pstModif.setString(3, arbol.getHabitat());
		pstModif.setInt(4, arbol.getAltura());
		pstModif.setString(5, arbol.getOrigen());
		pstModif.setInt(6, arbol.getId());
		
		pstModif.executeUpdate();
		
	}
	
	private static void eliminarArbol() throws SQLException {
		
		Arbol arbol=new Arbol();
		Scanner scan =new Scanner(System.in);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/eh_garden", "root", "");
		PreparedStatement pstElim = con.prepareStatement("DELETE FROM arboles WHERE id=?");
		
		System.out.println("Eliminando un arbol...");
		System.out.println("Introduce la id del arbol a eliminar");
		
		arbol.setId(Integer.parseInt(scan.nextLine()));
		pstElim.setInt(1, arbol.getId());
		pstElim.execute();
		
	}
	
	private static void visualizar() throws SQLException {
		
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/eh_garden", "root", "");
		PreparedStatement pst = con.prepareStatement("INSERT INTO arboles VALUES (null,?,?,?,?,?)");
		System.out.println("cuarta opcion seleccionada\n");
		String sentenciaSelect = "SELECT *FROM arboles";
		ResultSet resultado = pst.executeQuery(sentenciaSelect);
		while(resultado.next()) {
			System.out.println(resultado.getInt(1)+" - "+resultado.getString(2));
		}
		
	}






}
