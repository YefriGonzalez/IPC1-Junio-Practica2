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
	int[][] idClienteTelefono=new int[100][2];
	boolean[] tienePeliculaPrestada=new boolean[2];
	String nombreCliente;
	int idCliente;
	int telefono;
	int contadorIngresarCliente=0;

	// Variables globales Peliculas
	int[][] id_Año=new int[100][2];
	String[][] nombrePelicula_categoria=new String[100][2];
	boolean[] disponible=new boolean[100];
	int[] diasPrestamo=new int[100];
	int contadorIngresarPelicula=0;
	String nombrePelicula;
	String categoriaPelicula;
	int idPelicula;
	int añoPelicula;
	boolean peliculaDisponible;


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
		int contador=0;
		String espacio="|----------|";
		int j=0; 
		if (nombrePelicula_categoria[0][0]==null) {
			System.err.println("\n-----No hay Peliculas registradas-----");
		} else {
			System.out.println("\nPeliculas: ");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
			while(nombrePelicula_categoria[contador][0]!=null && nombrePelicula_categoria[contador][1]!=null && id_Año[contador][0]!=0 && id_Año[contador][1]!=0 && contador<nombrePelicula_categoria.length){
				
				System.out.print("| Id: "+id_Año[contador][0]);
				System.out.print(espacio);
				System.out.print("Nombre: "+nombrePelicula_categoria[contador][0]);
				System.out.print(espacio);
				System.out.print("Año: "+id_Año[contador][1]);
				System.out.print(espacio);
				System.out.print("Categoria: "+nombrePelicula_categoria[contador][1]);
				System.out.print(espacio);
				System.out.print("Disponiblidad: "+disponible[contador]+"|");
				System.out.println("\n---------------------------------------------------------------------------------------------------------------------------");
				contador++;
				j++;
			}			
		}
	}

	public void ingresarPeliculas(){
		int opcionCategoria;
		int opcionDisponibilidad;
		int ingresarPelicula=0;
		boolean repetido;
		while(ingresarPelicula!=2){
			repetido=false;
			opcionCategoria=0;
			opcionDisponibilidad=0;
			System.out.println("----------Ingresar nueva Pelicula--------------");
			System.out.print("Nombre de la Pelicula: ");
			scanner.nextLine();
			nombrePelicula=scanner.nextLine();
			System.out.print("Id de la Pelicula: ");
			idPelicula=scanner.nextInt();
			System.out.print("Año de pelicula: ");
			añoPelicula=scanner.nextInt();
			System.out.print("Categorias: 1.Accion 2.Terror 3.Comedia 4.Drama 5.Ciencia Ficcion. Ingrese el numero de categoria: ");
			opcionCategoria=scanner.nextInt();
			categorias(opcionCategoria);
			System.out.print("Disponiblidad: 1.Disponible 2. No disponible . Ingrese el numero de Disponibilidad: ");
			opcionDisponibilidad=scanner.nextInt();
			disponibilidadPelicula(opcionDisponibilidad);
			for (int i=0;i<nombrePelicula_categoria.length && !repetido;i++ ) {
				if (id_Año[i][0]==idPelicula) {
					repetido=true;
				} else {
					repetido=false;
				}
			}
			if (nombrePelicula!=null && idPelicula!=0 && añoPelicula>1000 && opcionCategoria>=1 &&opcionCategoria<=5 && opcionDisponibilidad>=1 &&opcionDisponibilidad<=2 && nombrePelicula_categoria[contadorIngresarPelicula][0]==null) {
				if (repetido==false) {
					nombrePelicula_categoria[contadorIngresarPelicula][0]=nombrePelicula;
					nombrePelicula_categoria[contadorIngresarPelicula][1]=categoriaPelicula;
					id_Año[contadorIngresarPelicula][0]=idPelicula;
					id_Año[contadorIngresarPelicula][1]=añoPelicula;
					disponible[contadorIngresarPelicula]=peliculaDisponible;
					contadorIngresarPelicula++;
					System.out.println("!!!!!Pelicula Registrada!!!!!!!");
					System.out.println("\n¿Desea ingresar otra Pelicula?");
					System.out.print("1.Si, 2.No, Ingrese la opcion: ");
					ingresarPelicula=scanner.nextInt();
				} else {
					System.out.println("!!!!!!!!!!!Error Id de pelicula ya existente!!!!!!!!!!!");
					System.out.println("\n¿Desea ingresar otra Pelicula?");
					System.out.print("1.Si, 2.No, Ingrese la opcion: ");
					ingresarPelicula=scanner.nextInt();
				}
			} else {
				System.out.println("Error, ingreso un dato incorrecto");
			}
		}
	}


	public void categorias(int opcion){
		if (opcion==1) {
			categoriaPelicula="Accion";
		} else if(opcion==2){
			categoriaPelicula="Terror";
		} else if (opcion==3) {
			categoriaPelicula="Comedia";
		} else if (opcion==4){
			categoriaPelicula="Drama";
		} else if (opcion==5) {
			categoriaPelicula="Ciencia Ficcion";
		} else {
			categoriaPelicula=null;
		}
	}

	public boolean disponibilidadPelicula(int opcion){
		if (opcion==1) {
			peliculaDisponible=true;
		} else {
			peliculaDisponible=false;
		}
		return peliculaDisponible;
	}

	public void ingresarClienteNuevo(){
		int ingresarCliente=0;
		boolean repetido;
		while(ingresarCliente!=2){
			repetido=false;
			System.out.println("----------Ingresar nuevo cliente--------------");
			System.out.print("Nombre de cliente: ");
			scanner.nextLine();
			nombreCliente=scanner.nextLine();
			System.out.print("Id de cliente: ");
			idCliente=scanner.nextInt();
			System.out.print("Telefono del cliente: ");
			telefono=scanner.nextInt();
			for (int i=0;i<idClienteTelefono.length && !repetido ;i++ ) {
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
					System.out.println("!!!!!!!!!!!Error Id de cliente ya existente!!!!!!!!!!!");
					System.out.println("\n¿Desea ingresar otro cliente?");
					System.out.print("1.Si, 2.No, Ingrese la opcion: ");
					ingresarCliente=scanner.nextInt();
				}
			} else {
				System.out.println("Ingreso un dato incorrecto, intentelo de nuevo");
			}		
		}
	}

	public void mostrarClientes(){
		int contador=0;
		String espacio="|-----------|"; 
		if (nombresCliente[0]==null) {
			System.err.println("\n-----No hay clientes registrados-----");
		} else {
			System.out.println("\nClientes: ");
			System.out.println("-------------------------------------------------------------------------");
			while(nombresCliente[contador]!=null && idClienteTelefono[contador][0]!=0 && idClienteTelefono[contador][1]!=0 && contador<nombresCliente.length){
				System.out.print("| Id: "+idClienteTelefono[contador][0]);
				System.out.print(espacio);
				System.out.print("Nombre: "+nombresCliente[contador]);
				System.out.print(espacio);
				System.out.print("Telefono: "+idClienteTelefono[contador][1]+"|");
				System.out.println("\n-------------------------------------------------------------------------");
				contador++;
			}			
		}
	}

	public void reportes(){

	}

}