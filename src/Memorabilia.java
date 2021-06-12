package src;

import java.util.Scanner;
public class Memorabilia{
	Scanner scanner=new Scanner(System.in);
	
	public static void main(String[] args) {
		Memorabilia m=new Memorabilia();
	}

	// Constructor
	public Memorabilia(){
		menu();
	}

	// Variables globales Clientes
	String nombreCliente;
	int idCliente;
	int telefono;
	boolean tienePeliculaPrestada;

	// Variables globales Peliculas
	int id;
	String nombre;
	int año;
	String categoria;
	boolean disponible;
	int diasPrestamo;

	// Variables globlales 
	int opcion=0;

	public void menu(){
		while(opcion!=9){
			System.out.println("\n-----------------Menu----------------\n");
			System.out.println("Opciones del menu: \n1.Prestamo de peliculas");
			System.out.println("2.Devolucion de Peliculas \n3.Mostrar Peliculas");
			System.out.println("4.Ingreso de Peliculas \n5.Ordenar Peliculas en forma Ascendente");
			System.out.println("6.Ingresar cliente nuevo \n7.Mostrar Clientes");
			System.out.println("8.Reportes \n9.Salir");
			System.out.print("Ingrese la opcion: ");
			opcion=scanner.nextInt();
		}
		System.out.println("\n----------------------Adios-----------------");
	}
}