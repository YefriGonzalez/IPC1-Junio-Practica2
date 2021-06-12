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
	String[] nombresCliente=new String[100];
	int[][] idClienteTelefono=new int[100][100];
	boolean[] tienePeliculaPrestada=new boolean[100];
	String nombreCliente;
	int idCliente;
	int telefono;

	// Variables globales Peliculas
	int[][] id_Año=new int[100][100];
	String[][] nombrePelicula_categoria=new String[100][100];
	boolean[] disponible=new boolean[100];
	int[] diasPrestamo=new int[100];

	// Variables globlales 
	int opcion=0;
	int contadorIngresarCliente=0;

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
			if (opcion==1) {
				
			} else if (opcion==2) {
				
			} else if (opcion==3) {
				mostrarPeliculas();
			} else if (opcion==4) {
				ingresarPeliculas();
			} else if (opcion==5) {
				
			} else if (opcion==6) {
				ingresarClienteNuevo();
			} else if (opcion==7) {
				mostrarClientes();
			} else if (opcion==8) {

			}
		}
		System.out.println("\n----------------------Adios-----------------");
	}

	public void prestamoPeliculas(){

	}

	public void devolucionPeliculas(){

	}

	public void mostrarPeliculas(){

	}

	public void ingresarPeliculas(){

	}

	public void ingresarClienteNuevo(){
		int ingresarCliente=0;
		boolean repetido;
		while(ingresarCliente!=2){
			repetido=false;
			System.out.println("----------Ingresar nuevo cliente--------------");
			System.out.print("Nombre de cliente: ");
			nombreCliente=scanner.next();
			System.out.print("Id de cliente: ");
			idCliente=scanner.nextInt();
			System.out.print("Telefono del cliente: ");
			telefono=scanner.nextInt();
			for (int i=0;i<idClienteTelefono.length &&!repetido ;i++ ) {
				if (idClienteTelefono[i][0]==idCliente) {
					repetido= true;
				} else {
					repetido =false;
				}
			}
			if ((nombreCliente != null) && (idCliente != 0) && (telefono != 0) && nombresCliente[contadorIngresarCliente]==null) {
				if(repetido==false){
					nombresCliente[contadorIngresarCliente]=nombreCliente;
					idClienteTelefono[contadorIngresarCliente][0]=idCliente;
					idClienteTelefono[contadorIngresarCliente][1]=telefono;
					contadorIngresarCliente++;
					System.out.println("!!!!!Usuario Registrado!!!!!!!");
					System.out.println("\n¿Desea ingresar otro cliente?");
					System.out.print("1.Si, 2.No, Ingrese la opcion: ");
					ingresarCliente=scanner.nextInt();
				} else {
					System.out.println("!!!!!!!!!!!Error Id ya existente!!!!!!!!!!!");
				}
			} else {
				System.out.println("Ingreso un dato incorrecto, intentelo de nuevo");
			}		
		}
	}

	public void mostrarClientes(){
		int contador=0;
		String espacio="     ";
		int j=0; 
		if (nombresCliente[0]==null) {
			System.err.println("\n-----No hay clientes registrados-----");
		} else {
			System.out.println("\nClientes: ");
			while(nombresCliente[contador]!=null && idClienteTelefono[contador][0]!=0 && idClienteTelefono[contador][1]!=0 && contador<nombresCliente.length){
				System.out.print("Nombre: "+nombresCliente[j]);
				System.out.print(espacio);
				System.out.print("Id: "+idClienteTelefono[j][0]);
				System.out.print(espacio);
				System.out.print("Telefono: "+idClienteTelefono[j][1]);
				System.out.println("\n");
				contador++;
				j++;
			}			
		}
	}

	public void reportes(){

	}

}