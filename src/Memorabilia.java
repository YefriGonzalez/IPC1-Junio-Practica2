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
	String[] nombresCliente=new String[30];
	int[][] idClienteTelefono=new int[30][2];
	boolean estadoDeCliente;
	String nombreCliente;
	int idCliente;
	int telefono;
	int contadorIngresarCliente=0;
	boolean[] tienePeliculaPrestada=new boolean[30];

	// Variables globales Peliculas
	int[][] id_Año=new int[30][2];
	String[][] nombrePelicula_categoria=new String[30][2];
	boolean[] disponible=new boolean[30];
	int contadorIngresarPelicula=0;
	String nombrePelicula;
	String categoriaPelicula;
	int idPelicula;
	int añoPelicula;
	boolean peliculaDisponible;


	// Variables globlales 
	int opcion=0;
	String[][] seleccion=new String[30][2];
	String[][] seleccionCategoria=new String[30][2];
	int[][] seleccionId=new int[30][2];
	int[][] seleccionAño=new int[30][2];
	boolean[] seleccionDisponibilidad= new boolean[30];

	// variables globales para prestamos de peliculas
	int[] cantidadDias=new int[30];
	int cantidadVecesPeliculaPrestada=0;
	int peliAccionPrestada=0;
	int peliTerrorPrestada=0;
	int peliComediaPrestada=0;
	int peliDramaPrestada=0;
	int peliCienciaPrestada=0;
	
	public void menu(){
		while(opcion!=9){
			System.out.println("\n-----------------Menu----------------\n");
			System.out.println("Opciones del menu:");
			System.out.println("1.Prestamo de peliculas            2.Devolucion de Peliculas          3.Ingresar nueva Pelicula");
			System.out.println("4.Mostrar Peliculas                5.Ordenar Peliculas en forma Ascendente");
			System.out.println("6.Ingresar cliente nuevo           7.Mostrar Clientes");
			System.out.println("8.Reportes                         9.Salir");
			System.out.print("Ingrese la opcion: ");
			opcion=scanner.nextInt();
			if (opcion==1) {
				prestamoPeliculas();
			} else if (opcion==2) {
				devolverPeliculas();
			} else if (opcion==3) {
				ingresarPeliculas();
			} else if (opcion==4) {
				mostrarPeliculas();
			} else if (opcion==5) {
				ordenarPeliculasAscendentemente();
			} else if (opcion==6) {
				ingresarClienteNuevo();
			} else if (opcion==7) {
				mostrarClientes();
			} else if (opcion==8) {
				reportes();
			}
		}
		System.out.println("\n----------------------Adios-----------------");
	}

	public void prestamoPeliculas(){
		int contador=0;
		int eleccion;
		int idCliente;
		int idAlquilada;
		int diasAlquiler;
		boolean hayPeliculasDisponibles=false;
		imprimirPeliculasDisponibles(contador);
		for (int i=0;i<disponible.length && !hayPeliculasDisponibles;i++) {
			if (disponible[i]==true) {
				hayPeliculasDisponibles=true;
			} else {
				hayPeliculasDisponibles=false;
			}
		}
		if (hayPeliculasDisponibles==true) {
			System.out.println("!!!!!Unicamente se puede alquilar una pelicula por persona!!!!!");
			System.out.print("\nIngrese el Id de la pelicula que desea alquilar: ");
			idAlquilada=scanner.nextInt();
			System.out.print("Ingrese el Id de cliente: ");
			idCliente=scanner.nextInt();
			System.out.print("Ingrese cantidad de dias de prestamo: ");
			diasAlquiler=scanner.nextInt();
			if (diasAlquiler>0) {
				System.out.println("");
				System.out.println("¿Desea alquilar la pelicula Id: "+idAlquilada+",con Id de cliente:"+idCliente +" por "+diasAlquiler+" dias?");
				System.out.println("1.Si      2.No    ");
				System.out.print("Ingrese el numero de opcion: ");
				eleccion=scanner.nextInt();
				verificarDisponiblidadDeCompra(eleccion,idAlquilada,idCliente,diasAlquiler);	
			} else {
				System.out.println("Cantidad de dias incorrecto");
			}
			
		}
		
	}

	public void devolverPeliculas(){
		int contador=0;
		int idClienteDevolucion;
		int idAlquiladaDevolucion;
		int eleccion;
		boolean hayPeliculasParaDevolver=false;
		for (int i=0;i<cantidadDias.length && !hayPeliculasParaDevolver;i++) {
			if (tienePeliculaPrestada[i]==true) {
				hayPeliculasParaDevolver=true;
			} else{
				hayPeliculasParaDevolver=false;
			}
		}
		if (hayPeliculasParaDevolver==true) {
			System.out.println("--------------------Devolucion de peliculas----------------------------");
			imprimirPeliculasPrestadas();
			System.out.print("\nIngrese el Id de la pelicula que desea devolver: ");
			idAlquiladaDevolucion=scanner.nextInt();
			System.out.print("Ingrese el Id de cliente: ");
			idClienteDevolucion=scanner.nextInt();
			System.out.println("Desea devoler la pelicula con ID: "+idAlquiladaDevolucion);
			System.out.println("1.Si    2.No ");
			System.out.print("Ingrese el numero de opcion: ");
			eleccion=scanner.nextInt();
			verificarDatosParaDevolverPelicula(eleccion,idAlquiladaDevolucion,idClienteDevolucion);
		} else {
			System.out.println("\n-----------------No hay peliculas  para devolver---------------------------");
		}

	}

	public void verificarDatosParaDevolverPelicula(int eleccion,int idPeliParaDevolver,int idClienteParaDevolucion){
		boolean peliculaPrestada=false;
		boolean clienteTienePeliculaPrestad=false;
		for (int i=0;i<cantidadDias.length && !peliculaPrestada;i++ ) {
			if (disponible[i]==false && id_Año[i][0]==idPeliParaDevolver) {
				peliculaPrestada=true;
			} else {
				peliculaPrestada=false;
			}
		}
		for (int i=0;i<cantidadDias.length && !clienteTienePeliculaPrestad;i++) {
			if (tienePeliculaPrestada[i]==true && idClienteTelefono[i][0]==idClienteParaDevolucion) {
				clienteTienePeliculaPrestad=true;
			} else {
				clienteTienePeliculaPrestad=false;
			}
		}
		if (eleccion==1) {
			if (clienteTienePeliculaPrestad==true && peliculaPrestada==true) {
				for (int i=0;i<cantidadDias.length;i++) {
					if (id_Año[i][0]==idPeliParaDevolver && disponible[i]==false) {
						disponible[i]=true;
					}
				}
				for (int j=0;j<cantidadDias.length;j++) {
					if (idClienteTelefono[j][0]==idClienteParaDevolucion && tienePeliculaPrestada[j]==true) {
						tienePeliculaPrestada[j]=false;
					}
				}
			} imprimirPeliculasPrestadas();
		}
	}

	public void verificarDisponiblidadDeCompra(int eleccion,int idAlquilada, int idCliente, int diasAlquiler){
		boolean peliculaExistente=false;
		boolean clienteExistente=false;
		for (int i=0;i<disponible.length && !peliculaExistente;i++) {
			if (disponible[i]==true && id_Año[i][0]==idAlquilada) {
				peliculaExistente=true;
				cantidadDias[i]=diasAlquiler;
			} else {
				peliculaExistente=false;
			}
		}

		for (int i=0;i<disponible.length &&!clienteExistente;i++) {
			if (tienePeliculaPrestada[i]==false && idClienteTelefono[i][0]==idCliente) {
				clienteExistente=true;
			} else {
				clienteExistente=false;
			}
		}

		if (eleccion==1) {
			if (clienteExistente==true && peliculaExistente==true && contadorIngresarCliente<cantidadDias.length) {
				for (int i=0;i<disponible.length;i++ ) {
					if (id_Año[i][0]==idAlquilada && disponible[i]==true) {
						disponible[i]=false;
					}
				}
				for (int j=0;j<disponible.length;j++) {
					if (idClienteTelefono[j][0]==idCliente && tienePeliculaPrestada[j]==false) {
						tienePeliculaPrestada[j]=true;
						System.out.println("---PELICULA ALQUILADA---");
					}
				}
			} else {
				System.out.println("Los datos de pelicula o cliente son incorrectos");
			}
		} else if (eleccion==2) {
			System.out.println("-----Hay mas peliculas, quiza te guste otra-----");
			System.out.println("------------MEMORABILIA--------------------");
		} else {
			System.out.println("La opcion es incorrecta");
		}

	}
	
	public void compararPeliculas(String[][] nombrePelicula){

	}

	public void imprimirPeliculasPrestadas(){
		int contador=0;
		String espacio="|----------|";
		System.out.println("Peliculas Alquiladas: ");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");	
		while(nombrePelicula_categoria[contador][0]!=null && contador<cantidadDias.length){	
			if (disponible[contador]==false && cantidadDias[contador]!=0) {
				System.out.print("| Id Cliente: "+idClienteTelefono[contador][0]);
				System.out.print(espacio);
				System.out.print("Nombre Cliente: "+nombresCliente[contador]);
				System.out.print(espacio);
				System.out.print("Id pelicula: "+id_Año[contador][0]);
				System.out.print(espacio);
				System.out.print("Nombre Pelicula: "+nombrePelicula_categoria[contador][0]);
				System.out.print(espacio);
				System.out.print("Dias de prestamo: "+cantidadDias[contador]+" |");
				System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------");
			}
			contador++;	
		}
	}


	public void imprimirPeliculasDisponibles(int contador){
		contador=0;
		String espacio="|----------|"; 
		if (nombrePelicula_categoria[0][0]==null) {
			System.err.println("\n-----No hay Peliculas Disponibles-----");
		} else {
				System.out.println("\nPeliculas Disponibles: ");
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
				while(nombrePelicula_categoria[contador][0]!=null && contador<nombrePelicula_categoria.length){
					if (disponible[contador]==true) {
						System.out.print("| Id: "+id_Año[contador][0]);
						System.out.print(espacio);
						System.out.print("Nombre: "+nombrePelicula_categoria[contador][0]);
						System.out.print(espacio);
						System.out.print("Año: "+id_Año[contador][1]);
						System.out.print(espacio);
						System.out.print("Categoria: "+nombrePelicula_categoria[contador][1]);
						System.out.print(espacio);
						System.out.print("Esta Disponible: "+disponible[contador]+"|");
						System.out.println("\n---------------------------------------------------------------------------------------------------------------------------");
					}
				contador++;
			}			
		}

	}

	public void mostrarPeliculas(){
		int contador=0;
		String espacio="|----------|"; 
		if (nombrePelicula_categoria[0][0]==null) {
			System.err.println("\n-----No hay Peliculas registradas-----");
		} else {
			System.out.println("\nPeliculas: ");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
			while(nombrePelicula_categoria[contador][0]!=null && contador<nombrePelicula_categoria.length){
				System.out.print("| Id: "+id_Año[contador][0]);
				System.out.print(espacio);
				System.out.print("Nombre: "+nombrePelicula_categoria[contador][0]);
				System.out.print(espacio);
				System.out.print("Año: "+id_Año[contador][1]);
				System.out.print(espacio);
				System.out.print("Categoria: "+nombrePelicula_categoria[contador][1]);
				System.out.print(espacio);
				System.out.print("Esta Disponible: "+disponible[contador]+"|");
				System.out.println("\n---------------------------------------------------------------------------------------------------------------------------");
				contador++;
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
			scanner.nextLine();
			System.out.print("Nombre de la Pelicula: ");
			nombrePelicula=scanner.nextLine();
			System.out.print("Id de la Pelicula: ");
			idPelicula=scanner.nextInt();
			System.out.print("Año de pelicula: ");
			añoPelicula=scanner.nextInt();
			System.out.println("Categorias: 1.Accion   2.Terror    3.Comedia    4.Drama    5.Ciencia Ficcion");
			System.out.print("Ingrese el numero de categoria: ");
			opcionCategoria=scanner.nextInt();
			categorias(opcionCategoria);
			for (int i=0;i<nombrePelicula_categoria.length && !repetido;i++ ) {
				if (id_Año[i][0]==idPelicula) {
					repetido=true;
				} else {
					repetido=false;
				}
			}
			if (nombrePelicula!=null && idPelicula!=0 && añoPelicula>1990 && añoPelicula<2023 && opcionCategoria>=1 &&opcionCategoria<=5 && nombrePelicula_categoria[contadorIngresarPelicula][0]==null) {
				if (repetido==false) {
					nombrePelicula_categoria[contadorIngresarPelicula][0]=nombrePelicula;
					nombrePelicula_categoria[contadorIngresarPelicula][1]=categoriaPelicula;
					id_Año[contadorIngresarPelicula][0]=idPelicula;
					id_Año[contadorIngresarPelicula][1]=añoPelicula;
					disponible[contadorIngresarPelicula]=true;
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

	public void ordenarPeliculasAscendentemente(){
		int posicion=0;
		for (int i=0;i<nombrePelicula_categoria.length;i++) {
				seleccion[i][0]=nombrePelicula_categoria[i][0];
				seleccionCategoria[i][0]=nombrePelicula_categoria[i][1];
				seleccionId[i][0]=id_Año[i][0];
				seleccionAño[i][0]=id_Año[i][1];
				seleccionDisponibilidad[i]=disponible[i];
				posicion=i;
				if (nombrePelicula_categoria[i][0]!=null) {
					for (int j=i-1;j>=0 && nombrePelicula_categoria[j][0].compareToIgnoreCase(seleccion[i][0])>0;j--) {
						if (nombrePelicula_categoria[j][0]!=null) {
							nombrePelicula_categoria[j+1][0]=nombrePelicula_categoria[j][0];
							nombrePelicula_categoria[j][0]=seleccion[i][0];
							nombrePelicula_categoria[j+1][1]=nombrePelicula_categoria[j][1];
							nombrePelicula_categoria[j][1]=seleccionCategoria[i][0];
							id_Año[j+1][0]=id_Año[j][0];
							id_Año[j][0]=seleccionId[i][0];
							id_Año[j+1][1]=id_Año[j][1];
							id_Año[j][1]=seleccionAño[i][0];
							disponible[j+1]=disponible[j];
							disponible[j]=seleccionDisponibilidad[i];
						}
					} 
				}
		}
		System.out.println("No hay mas peliculas para ordenar ");
		mostrarPeliculas();	
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
					tienePeliculaPrestada[contadorIngresarCliente]=false;	
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
			System.out.println("---------------------------------------------------------------------------------------------------------------------------");
			while(nombresCliente[contador]!=null && idClienteTelefono[contador][0]!=0 && idClienteTelefono[contador][1]!=0 && contador<nombresCliente.length){
				System.out.print("| Id: "+idClienteTelefono[contador][0]);
				System.out.print(espacio);
				System.out.print("Nombre: "+nombresCliente[contador]);
				System.out.print(espacio);
				System.out.print("Telefono: "+idClienteTelefono[contador][1]);
				System.out.print(espacio);
				System.out.print("Tiene Pelicula Prestada: "+tienePeliculaPrestada[contador]+"|");
				System.out.println("\n------------------------------------------------------------------------------------------------------------------------");
				contador++;
			}			
		}
	}

	
	public void reportes(){
		int opcion=0;
		while(opcion!=6){
			System.out.println("---------REPORTES------------");
			System.out.println("Opciones de Reportes: ");
			System.out.println("1.Cantidad de peliculas por categoria                  2.Categoria Especifica");
			System.out.println("3.Cantidad de veces que se prestan las peliculas       4.Pelicula mas prestada");
			System.out.println("5. Pelicula Menos Prestada                             6.Regresar");
			System.out.print("Ingrese el numero de  opcion: ");
			opcion=scanner.nextInt();
			if (opcion==1) {
				reportesPorCategoria();
			} else if(opcion==2){
				reporteCategoriaEspecifica();
			} else if (opcion==3) {
				cantidadVecesQueSeAlquila();
			} else if (opcion==4) {
				peliculaMasPrestada();
			} else if (opcion==5) {
				peliculaMenosPrestada();
			}
		}
		System.out.println("-----------Adios----------");
	}

	public void reportesPorCategoria(){
		int cont1=0;
		int cont2=0;
		int cont3=0;
		int cont4=0;
		int cont5=0;
		String espacio="----------";
		System.out.println("Accion: ");
		while(cont1<cantidadDias.length && nombrePelicula_categoria[cont1][0]!=null){
			if (nombrePelicula_categoria[cont1][1].equals("Accion")) {
				System.out.print(cont1+".Nombre: "+nombrePelicula_categoria[cont1][0]);
				System.out.print(espacio);
				System.out.print("Id: "+id_Año[cont1][0]+"|");
				System.out.println("\n-------------------------------------------------------");
			}
			cont1++;
		}	
		System.out.println("\nTerror: ");
		while(cont2<cantidadDias.length && nombrePelicula_categoria[cont2][0]!=null){
			if (nombrePelicula_categoria[cont2][1].equals("Terror")) {
				System.out.print(cont2+".Nombre: "+nombrePelicula_categoria[cont2][0]);
				System.out.print(espacio);
				System.out.print("Id: "+id_Año[cont2][0]+"|");
				System.out.println("\n-------------------------------------------------------");
			}
			cont2++;
		}
		System.out.println("\nComedia: ");
		while(cont3<cantidadDias.length && nombrePelicula_categoria[cont3][0]!=null){
			if (nombrePelicula_categoria[cont3][1].equals("Comedia")) {
				System.out.print(cont3+".Nombre: "+nombrePelicula_categoria[cont3][0]);
				System.out.print(espacio);
				System.out.print("Id: "+id_Año[cont3][0]+"|");
				System.out.println("\n-------------------------------------------------------");
			}
			cont3++;
		}
		System.out.println("\nDrama: ");
		while(cont4<cantidadDias.length && nombrePelicula_categoria[cont4][0]!=null){
			if (nombrePelicula_categoria[cont4][1].equals("Comedia")) {
				System.out.print(cont4+".Nombre: "+nombrePelicula_categoria[cont4][0]);
				System.out.print(espacio);
				System.out.print("Id: "+id_Año[cont4][0]+"|");
				System.out.println("\n-------------------------------------------------------");
			}
			cont4++;
		}
		System.out.println("\nCiencia Ficcion: ");
		while(cont5<cantidadDias.length && nombrePelicula_categoria[cont5][0]!=null){
			if (nombrePelicula_categoria[cont5][1].equals("Ciencia Ficcion")) {
				System.out.print(cont5+".Nombre: "+nombrePelicula_categoria[cont5][0]);
				System.out.print(espacio);
				System.out.print("Id: "+id_Año[cont5][0]+"|");
				System.out.println("\n-------------------------------------------------------");
			}
			cont5++;
		}
	}

	public void reporteCategoriaEspecifica(){
		int opcion=0;
		int cont1=0;
		int cont2=0;
		int cont3=0;
		int cont4=0;
		int cont5=0;
		String espacio="----------";
		while(opcion!=6){
			System.out.println("Categorias: ");
			System.out.println("1.Accion           2.Terror            3.Comedia");
			System.out.println("4.Drama            5.Ciencia Ficcion   6.Regresar");
			opcion=scanner.nextInt();
			if (opcion==1) {
				while(cont1<cantidadDias.length && nombrePelicula_categoria[cont1][0]!=null){
					if (nombrePelicula_categoria[cont1][1].equals("Accion")) {
						System.out.print(cont1+".Nombre: "+nombrePelicula_categoria[cont1][0]);
						System.out.print(espacio);
						System.out.print("Id: "+id_Año[cont1][0]+"|");
						System.out.println("\n-------------------------------------------------------");
					}
					cont1++;
				}	
			}else if (opcion==2) {
				while(cont2<cantidadDias.length && nombrePelicula_categoria[cont2][0]!=null){
					if (nombrePelicula_categoria[cont2][1].equals("Terror")) {
						System.out.print(cont2+".Nombre: "+nombrePelicula_categoria[cont2][0]);
						System.out.print(espacio);
						System.out.print("Id: "+id_Año[cont2][0]+"|");
						System.out.println("\n-------------------------------------------------------");
					}
					cont2++;
				}
			} else if (opcion==3) {
				while(cont3<cantidadDias.length && nombrePelicula_categoria[cont3][0]!=null){
					if (nombrePelicula_categoria[cont3][1].equals("Comedia")) {
						System.out.print(cont3+".Nombre: "+nombrePelicula_categoria[cont3][0]);
						System.out.print(espacio);
						System.out.print("Id: "+id_Año[cont3][0]+"|");
						System.out.println("\n-------------------------------------------------------");
					}
					cont3++;
				}
			} else if (opcion==4) {
				while(cont4<cantidadDias.length && nombrePelicula_categoria[cont4][0]!=null){
					if (nombrePelicula_categoria[cont4][1].equals("Comedia")) {
						System.out.print(cont4+".Nombre: "+nombrePelicula_categoria[cont4][0]);
						System.out.print(espacio);
						System.out.print("Id: "+id_Año[cont4][0]+"|");
						System.out.println("\n-------------------------------------------------------");
					}
					cont4++;
				}
			} else if (opcion==5) {
				while(cont5<cantidadDias.length && nombrePelicula_categoria[cont5][0]!=null){
					if (nombrePelicula_categoria[cont5][1].equals("Ciencia Ficcion")) {
						System.out.print(cont5+".Nombre: "+nombrePelicula_categoria[cont5][0]);
						System.out.print(espacio);
						System.out.print("Id: "+id_Año[cont5][0]+"|");
						System.out.println("\n-------------------------------------------------------");
					}
					cont5++;
				}
			}
		}
	}

	public void cantidadVecesQueSeAlquila(){
		
	}

	public void peliculaMasPrestada(){

	}

	public void peliculaMenosPrestada(){

	}
}