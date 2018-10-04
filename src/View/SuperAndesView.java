package View;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import main.java.model.Productos;
import main.java.model.Sucursal;
import main.java.model.TipoPromocion;
import main.java.persistence.PersistenciaSuperAndes;

public class SuperAndesView {

	private static PersistenciaSuperAndes persistencia;

	private static long idSucursal;
	Sucursal sucursal;
	public static void main(String[] args) 
	{
		//		persistencia=new PersistenciaSuperAndes();
		//		try
		//		{
		//		Sucursal sucursal=persistencia.agregarSucursal("Pepe Sierra", "Bogota", "Calle 116 #15-66");
		//		if(sucursal!=null)
		//			System.out.println("id: "+sucursal.getId()+" ,nombre: "+sucursal.getNombre());
		//		}
		//		catch(Exception e)
		//		{
		//			System.out.println("Se totio");
		//
		//		}

		borrarPromocionesVencidas();
		idSucursal=-1l;
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		while(!fin)
		{
			if(idSucursal==-1l)
			{
				printMenu();

				int option = sc.nextInt();


				switch(option)
				{
				case 1:
					System.out.println("----------------------------------------------------------------");
					System.out.println("Para registrar una nueva Sucursal debe proporcionar los siguientes datos:\n");

					registrarSucursal(sc);						


					break;
				case 2:
					System.out.println("----------------------------------------------------------------");
					System.out.println("Para ingresar a su sucursal debe proporcionar los siguientes datos:\n");

					ingresarASucursal(sc);

					break;

				case 0:	
					fin=true;
					sc.close();
					break;
				}

			}
			else
			{
				printMenuSucursal();

				int option = sc.nextInt();

				switch(option)
				{
				case 1:
					System.out.println("----------------------------------------------------------------");
					System.out.println("Para registrar un nuevo proveedor debe proporcionar los siguientes datos:\n");

					registrarProveedor(sc);



					break;
				case 2:
					System.out.println("----------------------------------------------------------------");
					System.out.println("Para registrar un nuevo producto debe proporcionar los siguientes datos:\n");

					registrarProducto(sc);

					break;
				case 3:
					System.out.println("----------------------------------------------------------------");
					System.out.println("Para registrar un nuevo cliente debe proporcionar los siguientes datos:\n");

					registrarCliente(sc);
					break;
				case 4:
					System.out.println("----------------------------------------------------------------");
					System.out.println("Para registrar una nueva sucursal debe proporcionar los siguientes datos:\n");

					registrarSucursal(sc);

					break;
				case 5:
					System.out.println("----------------------------------------------------------------");
					System.out.println("Para registrar una nueva Bodega a una Sucursal debe proporcionar los siguientes datos:\n");

					registrarBodegaASucursal(sc);
					break;
				case 6:
					System.out.println("----------------------------------------------------------------");
					System.out.println("Para registrar un Estante a una Sucursal debe proporcionar los siguientes datos:\n");

					registrarEstanteASucursal(sc);
					break;
				case 7:
					System.out.println("----------------------------------------------------------------");
					System.out.println("Para registrar una nueva Promoción debe proporcionar los siguientes datos:\n");

					registrarPromocion(sc);
					break;
				case 8:
					System.out.println("----------------------------------------------------------------");
					System.out.println("Esta acción se realiza sola cada vez que se abre la aplicación");

					break;
				case 9:
					System.out.println("----------------------------------------------------------------");
					System.out.println("Para registrar un nuevo  Pedido de un Producto a un Proveedor para una Sucursal debe proporcionar los siguientes datos:\n");

					break;
				case 10:
					System.out.println("----------------------------------------------------------------");
					System.out.println("Para registrar la llegada de un pedido de un producto a una sucursal debe proporcionar los siguientes datos:\n");

					break;
				case 11:
					System.out.println("----------------------------------------------------------------");
					System.out.println("Para registrar la venta de un producto en una sucursal debe proporcionar los siguientes datos:\n");

					break;
				case 12:
					System.out.println("----------------------------------------------------------------");


					break;
				case 13:
					System.out.println("----------------------------------------------------------------");

					break;
				case 14:
					System.out.println("----------------------------------------------------------------");

					break;
				case 15:
					System.out.println("----------------------------------------------------------------");

					break;
				case 16:
					System.out.println("----------------------------------------------------------------");

					break;
				case 17:
					System.out.println("----------------------------------------------------------------");

					break;
				case 0:	
					fin=true;
					sc.close();
					break;
				}
			}
		}
	}

	public static void borrarPromocionesVencidas()
	{
		//TODO BORRAR TODAS LAS PROMOCIONES CUYA FECHA<FECHAACTUAL
	}


	public static void registrarProveedor(Scanner sc)
	{
		sc.nextLine();
		String nit ="";
		while(true)
		{
			System.out.println("Ingrese el NIT del proveedor:");
			nit = sc.nextLine();
			if(nit.trim().equals(""))
				System.out.println("¡El nit no puede ser vacio!");
			else 
				break;

		}

		String nombre ="";
		while(true)
		{
			System.out.println("Ingrese el nombre del proveedor:");
			nombre = sc.nextLine();
			if(nombre.trim().equals(""))
				System.out.println("¡El nombre no puede ser vacio!");
			else 
				break;
		}


		String correoElectronico="";
		while(true)
		{	
			System.out.println("Ingrese el correo electronico del proveedor:");
			correoElectronico = sc.nextLine();
			if(correoElectronico.contains("@")&&correoElectronico.substring(correoElectronico.indexOf("@")).contains("."))
				break;
			else
				System.out.println("¡El correo electronico no tiene el formato esperado!");

		}

		String telefono = "";
		while(true) {
			System.out.println("Ingrese el telefono del proveedor (sin incluir +57):");
			telefono = sc.nextLine();
			if(telefono.length()!=10)
				System.out.println("¡La longitud del telefono no es correcta!");
			else {
				try
				{
					Integer.parseInt(telefono.substring(0,6));
					Integer.parseInt(telefono.substring(6));
					break;
				}
				catch(Exception e)
				{
					System.out.println("¡El telefono contiene caracteres no permitidos!");
				}
			}

		}

		//TODO GENERAR PROVEEDOR CON LOS DATOS
		persistencia.requerimiento1(nit, nombre, correoElectronico, Long.valueOf(telefono));

		System.out.println("nit: "+nit+" ,nombre: "+nombre+" ,correoElectronico: "+correoElectronico+" ,telefono: (+57)"+telefono);

	}

	public static void registrarProducto(Scanner sc)
	{
		sc.nextLine();


		String codigoProducto ="";
		String resp ="0";
		//		Productos producto=new Productos("x","x","x","x","x",5,"x",5,"x");
		Productos producto=null;

		while(true)
		{
			System.out.println("Ingrese el codigoBarras del producto:");
			codigoProducto = sc.nextLine();
			if(codigoProducto.trim().equals(""))
				System.out.println("¡El codigoBarras no puede ser vacio!");
			else
			{
				//TODO traer porducto con ese codigo barras
				//				producto=


				if(producto==null)
				{
					while(true)
					{
						System.out.println("NO existe un producto con ese codigoBarras, ¿Desea Crearlo?:");
						System.out.println("1) SI");
						System.out.println("2) NO");
						resp = sc.nextLine();
						if(resp.equals("1"))
							break;
						if(resp.equals("2"))
							break;
						System.out.println("¡Debe escoger una opción valida!");

					}				
				}

				break;
			}

		}
		if(resp.equals("1"))
		{
			String nombre ="";
			while(true)
			{
				System.out.println("Ingrese el nombre del producto:");
				nombre = sc.nextLine();
				if(nombre.trim().equals(""))
					System.out.println("¡El nombre no puede ser vacio!");
				else 
					break;
			}

			String marca ="";
			while(true)
			{
				System.out.println("Ingrese la marca del producto:");
				marca = sc.nextLine();
				if(marca.trim().equals(""))
					System.out.println("¡La marca no puede ser vacia!");
				else 
					break;
			}

			String presentacion ="";
			while(true)
			{
				System.out.println("Ingrese la presentacion del producto:");
				presentacion = sc.nextLine();
				if(presentacion.trim().equals(""))
					System.out.println("¡La presentacion no puede ser vacia!");
				else 
					break;
			}

			String unidadPeso ="";
			while(true)
			{
				System.out.println("Escoja la unidadPeso del producto escogiendo el numero de opción:");
				System.out.println("1) g");
				System.out.println("2) kg");
				unidadPeso = sc.nextLine();
				if(unidadPeso.equals("1"))
				{
					unidadPeso = "g";
					break;
				}
				if(unidadPeso.equals("2"))
				{
					unidadPeso = "kg";
					break;
				}	
				System.out.println("¡Debe escoger una opción valida!");

			}

			String unidadVolumen ="";
			while(true)
			{
				System.out.println("Escoja la unidadVolumen del producto escogiendo el numero de opción:");
				System.out.println("1) l");
				System.out.println("2) ml");
				unidadVolumen = sc.nextLine();
				if(unidadVolumen.equals("1"))
				{
					unidadVolumen = "l";
					break;
				}
				if(unidadVolumen.equals("2"))
				{
					unidadVolumen = "ml";
					break;
				}	
				System.out.println("¡Debe escoger una opción valida!");

			}


			double cantidadPeso=0;
			while(true)
			{
				System.out.println("Ingrese la cantidadPeso del producto");
				String cant= sc.nextLine();
				try
				{
					cantidadPeso=Double.parseDouble(cant);
					if(cantidadPeso<=0)
						System.out.println("¡Debe ingresar un numero mayor a cero!");
					else 
						break;
				}
				catch(Exception e)
				{
					System.out.println("¡Debe ingresar un numero!");
				}

			}

			double cantidadVolumen=0;
			while(true)
			{
				System.out.println("Ingrese la cantidadVolumen del producto");
				String cant= sc.nextLine();
				try
				{
					cantidadVolumen=Double.parseDouble(cant);
					if(cantidadVolumen<=0)
						System.out.println("¡Debe ingresar un numero mayor a cero!");
					else 
						break;
				}
				catch(Exception e)
				{
					System.out.println("¡Debe ingresar un numero!");
				}

			}


			String tipoProducto="";
			while(true)
			{	
				System.out.println("Ingrese el tipoProducto del producto:");
				tipoProducto = sc.nextLine();
				if(tipoProducto.trim().equals(""))
					System.out.println("¡El tipoProducto no puede ser vacio!");
				else 
					break;
			}

			//TODO GENERAR PRODUCTO CON LOS DATOS
			producto=new Productos("x","x","x","x","x",5,"x",5,"x");

			System.out.println("codigoBarras: "+codigoProducto+" ,nombre: "+nombre+" ,presentacion: "+presentacion+" ,unidadPeso: "+unidadPeso
					+" unidadVolumen: "+unidadVolumen+" ,cantidadPeso: "+cantidadPeso+" ,cantidadVolumen: "+cantidadVolumen+" ,tipoProducto: "+tipoProducto);


		}
		if(producto!=null)
		{
			double precio=0;
			while(true)
			{
				System.out.println("Ingrese el precio que el producto tendra en su sucursal");
				String cant= sc.nextLine();
				try
				{
					precio=Double.parseDouble(cant);
					if(precio<=0)
						System.out.println("¡Debe ingresar un numero mayor a cero!");
					else 
						break;
				}
				catch(Exception e)
				{
					System.out.println("¡Debe ingresar un numero!");
				}


			}
			String idProductoSucursal=idSucursal+"-"+codigoProducto;

			//TODO GEENERAR PRODUCTO SUCURSAL CON VALORES
			try {
				persistencia.agregarProductosSucursal(idSucursal, precio, codigoProducto, Long.MAX_VALUE, idProductoSucursal);
				System.out.println("idProductoSucursal: "+idProductoSucursal+" ,idSucursal: "+idSucursal+" ,precio: "+precio+" ,codigoProducto: "+codigoProducto);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}





	}


	public static void registrarCliente(Scanner sc)
	{

		sc.nextLine();

		boolean registrarPersona =true;
		while(true)
		{
			System.out.println("Escoja el tipo de cliente que va a registrar:");
			System.out.println("1) Persona");
			System.out.println("2) Empresa");
			String tipoCliente = sc.nextLine();
			if(tipoCliente.equals("1"))
				break;
			if(tipoCliente.equals("2"))
			{
				registrarPersona = false;
				break;
			}	
			System.out.println("¡Debe escoger una opción valida!");

		}
		String identificador="";
		String cedula ="";
		String nit ="";

		if(registrarPersona)
		{
			while(true)
			{
				System.out.println("Ingrese la cedula del cliente:");
				cedula = sc.nextLine();
				if(cedula.length()<6)
					System.out.println("¡La cedula debe tener más de 6 digitos!");
				else 
					try
				{
						Integer.parseInt(cedula.substring(0,5));
						Integer.parseInt(cedula.substring(5));
						identificador=cedula;
						break;
				}
				catch(Exception e)
				{
					System.out.println("¡La cedula debe estar compuesta unicamente de numeros!");

				}			
			}
		}
		else
			while(true)
			{
				System.out.println("Ingrese el NIT del cliente:");
				nit = sc.nextLine();
				if(nit.trim().equals(""))
					System.out.println("¡El nit no puede ser vacio!");
				else 
				{
					identificador=nit;
					break;
				}

			}

		String nombre ="";
		while(true)
		{
			System.out.println("Ingrese el nombre del cliente:");
			nombre = sc.nextLine();
			if(nombre.trim().equals(""))
				System.out.println("¡El nombre no puede ser vacio!");
			else 
				break;
		}

		String correoElectronico="";
		while(true)
		{	
			System.out.println("Ingrese el correo electronico del cliente:");
			correoElectronico = sc.nextLine();
			if(correoElectronico.contains("@")&&correoElectronico.substring(correoElectronico.indexOf("@")).contains("."))
				break;
			else
				System.out.println("¡El correo electronico no tiene el formato esperado!");

		}

		double puntos=0;


		if(registrarPersona)
		{
			System.out.println("cedula: "+cedula+" ,nombre: "+nombre+" ,correoElectronico: "+correoElectronico+" ,puntos: "+puntos
					+" identificador: "+identificador);

		}
		else
		{
			System.out.println("NIT: "+nit+" ,nombre: "+nombre+" ,correoElectronico: "+correoElectronico+" ,puntos: "+puntos
					+" identificador: "+identificador);

		}

	}



	public static void registrarSucursal(Scanner sc)
	{

		sc.nextLine();

		String nombre ="";
		while(true)
		{
			System.out.println("Ingrese el nombre de la Sucursal:");
			nombre = sc.nextLine();
			if(nombre.trim().equals(""))
				System.out.println("¡El nombre no puede ser vacio!");
			else 
				break;
		}

		String ciudad ="";
		while(true)
		{
			System.out.println("Ingrese el nombre de la Ciudad de la Sucursal:");
			ciudad = sc.nextLine();
			if(ciudad.trim().equals(""))
				System.out.println("¡La ciudad no puede ser vacia!");
			else 
				break;
		}

		String direccion ="";
		while(true)
		{
			System.out.println("Ingrese la dirección de la Sucursal:");
			direccion = sc.nextLine();
			if(direccion.trim().equals(""))
				System.out.println("¡La dirección no puede ser vacia!");
			else 
				break;
		}

		//TODO PONER EL ID QUE GENERO EL SISTEMA
		idSucursal=0l;
		persistencia.requerimiento4(nombre, ciudad, direccion);
		System.out.println("idSucursal: "+idSucursal+" ,nombre: "+nombre+" ,ciudad: "+ciudad+" ,direccion: "+direccion);

	}


	public static void registrarBodegaASucursal(Scanner sc)
	{

		sc.nextLine();



		String unidadPeso ="";
		while(true)
		{
			System.out.println("Escoja la unidadPeso con la que se medira la capacidad de la Bodega escogiendo el numero de opción:");
			System.out.println("1) g");
			System.out.println("2) kg");
			unidadPeso = sc.nextLine();
			if(unidadPeso.equals("1"))
			{
				unidadPeso = "g";
				break;
			}
			if(unidadPeso.equals("2"))
			{
				unidadPeso = "kg";
				break;
			}	
			System.out.println("¡Debe escoger una opción valida!");

		}

		String unidadVolumen ="";
		while(true)
		{
			System.out.println("Escoja la unidadVolumen con la que se medira la capacidad de la Bodega escogiendo el numero de opción:");
			System.out.println("1) l");
			System.out.println("2) ml");
			unidadVolumen = sc.nextLine();
			if(unidadVolumen.equals("1"))
			{
				unidadVolumen = "l";
				break;
			}
			if(unidadVolumen.equals("2"))
			{
				unidadVolumen = "ml";
				break;
			}	
			System.out.println("¡Debe escoger una opción valida!");

		}


		double capacidadPeso=0;
		while(true)
		{
			System.out.println("Ingrese la capacidadPeso de la Bodega");
			String cant= sc.nextLine();
			try
			{
				capacidadPeso=Double.parseDouble(cant);
				if(capacidadPeso<=0)
					System.out.println("¡Debe ingresar un numero mayor a cero!");
				else 
					break;
			}
			catch(Exception e)
			{
				System.out.println("¡Debe ingresar un numero!");
			}

		}

		double capacidadVolumen=0;
		while(true)
		{
			System.out.println("Ingrese la capacidadVolumen de la Bodega");
			String cant= sc.nextLine();
			try
			{
				capacidadVolumen=Double.parseDouble(cant);
				if(capacidadVolumen<=0)
					System.out.println("¡Debe ingresar un numero mayor a cero!");
				else 
					break;
			}
			catch(Exception e)
			{
				System.out.println("¡Debe ingresar un numero!");
			}

		}

		double nivelReOrden=0;
		while(true)
		{
			System.out.println("Ingrese el nivel de Re Ordenamiento de la Bodega");
			String cant= sc.nextLine();
			try
			{
				nivelReOrden=Double.parseDouble(cant);
				if(nivelReOrden<=0)
					System.out.println("¡Debe ingresar un numero mayor a cero!");
				else 
					break;
			}
			catch(Exception e)
			{
				System.out.println("¡Debe ingresar un numero!");
			}

		}

		String tipoProducto="";
		while(true)
		{	
			System.out.println("Ingrese el tipoProducto de la Bodega:");
			tipoProducto = sc.nextLine();
			if(tipoProducto.trim().equals(""))
				System.out.println("¡El tipoProducto no puede ser vacio!");
			else 
				break;
		}


		//TODO CREAR BOGEGA CON IDSUCURSAL=idSucursal

		//PONER EL ID QUE GENERO EL SISTEMA
		Long idBodega=0l;

		try {
			persistencia.requerimiento5(capacidadVolumen, capacidadPeso, unidadPeso, unidadVolumen, idSucursal, nivelReOrden, tipoProducto);
			System.out.println("idBodega: "+idBodega+" ,idSucursal: "+idSucursal+" ,unidadPeso: "+unidadPeso
					+" unidadVolumen: "+unidadVolumen+" ,capacidadPeso: "+capacidadPeso+" ,capacidadVolumen: "+capacidadVolumen
					+" ,tipoProducto: "+tipoProducto+" ,nivelReOrden: "+nivelReOrden);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}


	public static void registrarEstanteASucursal(Scanner sc)
	{

		sc.nextLine();

		String unidadPeso ="";
		while(true)
		{
			System.out.println("Escoja la unidadPeso con la que se medira la capacidad del Estante escogiendo el numero de opción:");
			System.out.println("1) g");
			System.out.println("2) kg");
			unidadPeso = sc.nextLine();
			if(unidadPeso.equals("1"))
			{
				unidadPeso = "g";
				break;
			}
			if(unidadPeso.equals("2"))
			{
				unidadPeso = "kg";
				break;
			}	
			System.out.println("¡Debe escoger una opción valida!");

		}

		String unidadVolumen ="";
		while(true)
		{
			System.out.println("Escoja la unidadVolumen con la que se medira la capacidad del Estante escogiendo el numero de opción:");
			System.out.println("1) l");
			System.out.println("2) ml");
			unidadVolumen = sc.nextLine();
			if(unidadVolumen.equals("1"))
			{
				unidadVolumen = "l";
				break;
			}
			if(unidadVolumen.equals("2"))
			{
				unidadVolumen = "ml";
				break;
			}	
			System.out.println("¡Debe escoger una opción valida!");

		}


		double capacidadPeso=0;
		while(true)
		{
			System.out.println("Ingrese la capacidadPeso del Estante");
			String cant= sc.nextLine();
			try
			{
				capacidadPeso=Double.parseDouble(cant);
				if(capacidadPeso<=0)
					System.out.println("¡Debe ingresar un numero mayor a cero!");
				else 
					break;
			}
			catch(Exception e)
			{
				System.out.println("¡Debe ingresar un numero!");
			}

		}

		double capacidadVolumen=0;
		while(true)
		{
			System.out.println("Ingrese la capacidadVolumen del Estante");
			String cant= sc.nextLine();
			try
			{
				capacidadVolumen=Double.parseDouble(cant);
				if(capacidadVolumen<=0)
					System.out.println("¡Debe ingresar un numero mayor a cero!");
				else 
					break;
			}
			catch(Exception e)
			{
				System.out.println("¡Debe ingresar un numero!");
			}

		}

		double nivelReOrden=0;
		while(true)
		{
			System.out.println("Ingrese el nivel de Re Ordenamiento del Estante");
			String cant= sc.nextLine();
			try
			{
				nivelReOrden=Double.parseDouble(cant);
				if(nivelReOrden<=0)
					System.out.println("¡Debe ingresar un numero mayor a cero!");
				else 
					break;
			}
			catch(Exception e)
			{
				System.out.println("¡Debe ingresar un numero!");
			}

		}

		String tipoProducto="";
		while(true)
		{	
			System.out.println("Ingrese el tipoProducto del Estante:");
			tipoProducto = sc.nextLine();
			if(tipoProducto.trim().equals(""))
				System.out.println("¡El tipoProducto no puede ser vacio!");
			else 
				break;
		}

		//TODO CREAR ESTANTE CON IDSUCURSAL=idSucursal
		//TODO PONER EL ID QUE GENERO EL SISTEMA
		Long idEstante=0l;
		try {
			persistencia.requerimiento6(capacidadVolumen, capacidadPeso, unidadPeso, unidadVolumen, idSucursal, nivelReOrden, tipoProducto);
			System.out.println("idEstante: "+idEstante+" ,idSucursal: "+idSucursal+" ,unidadPeso: "+unidadPeso
					+" unidadVolumen: "+unidadVolumen+" ,capacidadPeso: "+capacidadPeso+" ,capacidadVolumen: "+capacidadVolumen
					+" ,tipoProducto: "+tipoProducto+" ,nivelReOrden: "+nivelReOrden);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




	}

	public static void registrarPromocion(Scanner sc)
	{
		sc.nextLine();

		TipoPromocion tipoPromocion;
		while(true)
		{
			System.out.println("Escoja el tipo de promoción que desea agregar escogiendo el numero de opción:");
			System.out.println("1) Pague \"x\" unidades lleve \"y\" unidades (x<y)");
			System.out.println("2) \"x\" Porcentaje de Descuento");
			System.out.println("3) Pague \"x\" cantidad lleve \"y\" cantidad (x<y)");
			System.out.println("4) Pague \"x\" productos y lleve el siguiente con \"y\" PorcentajeDescuento");
			System.out.println("5) Pague \"x\" por productos promocion");

			String tipo = sc.nextLine();
			if(tipo.equals("1"))
			{
				tipoPromocion = TipoPromocion.PAGUE_X_LLEVE_Y_UNIDADES;
				break;
			}
			if(tipo.equals("2"))
			{
				tipoPromocion = TipoPromocion.X_PORCENTAJE_DESCUENTO;
				break;
			}
			if(tipo.equals("3"))
			{
				tipoPromocion = TipoPromocion.PAGUE_X_LLEVE_Y_CANTIDAD;
				break;
			}	
			if(tipo.equals("4"))
			{
				tipoPromocion = TipoPromocion.PAGUE_X_LLEVE_SIGUIENTE_CON_Y_PORCENTAJE_DESCUENTO;
				break;
			}	
			if(tipo.equals("5"))
			{
				tipoPromocion = TipoPromocion.PAGUE_X_POR_PRODUCTOS_PROMOCION;
				break;
			}	
			System.out.println("¡Debe escoger una opción valida!");

		}
		String fechaInicial="";
		while(fechaInicial.equals(""))
		{
			int anio=0;
			while(true)
			{
				System.out.println("Ingrese el año de inicio de la promocion "+tipoPromocion.toString());
				String cant= sc.nextLine();
				try
				{
					anio=Integer.parseInt(cant);
					if(anio<2018)
						System.out.println("¡Debe ingresar un año valido!");
					else 
						break;
				}
				catch(Exception e)
				{
					System.out.println("¡Debe ingresar un numero!");
				}
			}

			int mes=0;
			while(true)
			{
				System.out.println("Ingrese el mes de inicio de la promocion "+tipoPromocion.toString());
				String cant= sc.nextLine();
				try
				{
					mes=Integer.parseInt(cant);
					if(mes<=0||mes>12)
						System.out.println("¡Debe ingresar un mes valido!");
					else 
						break;
				}
				catch(Exception e)
				{
					System.out.println("¡Debe ingresar un numero!");
				}
			}

			int dia=0;
			while(true)
			{
				System.out.println("Ingrese el dia de inicio de la promocion "+tipoPromocion.toString());
				String cant= sc.nextLine();
				try
				{
					dia=Integer.parseInt(cant);
					if(dia<=0||dia>31)
						System.out.println("¡Debe ingresar un dia valido!");
					else 
					{
						System.out.println(getFechaActual());
						break;
					}
				}
				catch(Exception e)
				{
					System.out.println("¡Debe ingresar un numero!");
				}
			}
			if(compararFechas(anio+"-"+mes+"-"+dia, getFechaActual())>=0)
				fechaInicial=anio+"-"+mes+"-"+dia;
			else
				System.out.println("¡Debe ingresar una fecha posterior a la fecha actual!   fecha actual: "+getFechaActual());


		}
		String fechaFinal="";
		while(fechaFinal.equals(""))
		{
			int anio=0;
			while(true)
			{
				System.out.println("Ingrese el año de fin de la promocion "+tipoPromocion.toString());
				String cant= sc.nextLine();
				try
				{
					anio=Integer.parseInt(cant);
					if(anio<2018)
						System.out.println("¡Debe ingresar un año valido!");
					else 
						break;
				}
				catch(Exception e)
				{
					System.out.println("¡Debe ingresar un numero!");
				}
			}

			int mes=0;
			while(true)
			{
				System.out.println("Ingrese el ultimo mes de la promocion "+tipoPromocion.toString());
				String cant= sc.nextLine();
				try
				{
					mes=Integer.parseInt(cant);
					if(mes<=0||mes>12)
						System.out.println("¡Debe ingresar un mes valido!");
					else 
						break;
				}
				catch(Exception e)
				{
					System.out.println("¡Debe ingresar un numero!");
				}
			}

			int dia=0;
			while(true)
			{
				System.out.println("Ingrese el ultimo dia de de la promocion "+tipoPromocion.toString());
				String cant= sc.nextLine();
				try
				{
					dia=Integer.parseInt(cant);
					if(dia<=0||dia>31)
						System.out.println("¡Debe ingresar un dia valido!");
					else 
						break;
				}
				catch(Exception e)
				{
					System.out.println("¡Debe ingresar un numero!");
				}
			}
			if(compararFechas(anio+"-"+mes+"-"+dia, fechaInicial)>=0)
				fechaFinal=anio+"-"+mes+"-"+dia;
			else
				System.out.println("¡Debe ingresar una fecha posterior a la fecha inicial!   fecha inicial: "+fechaInicial);


		}


		double y=0;
		double x=0;
		while(true)
		{
			System.out.println("Ingrese x, teniendo en cuenta que este representara el valor de \"x\" en la promocion "+tipoPromocion.toString());
			String cant= sc.nextLine();
			try
			{
				x=Double.parseDouble(cant);
				if(x<=0)
					System.out.println("¡Debe ingresar un numero mayor a cero!");
				else 
					break;
			}
			catch(Exception e)
			{
				System.out.println("¡Debe ingresar un numero!");
			}

		}
		if(tipoPromocion!=TipoPromocion.X_PORCENTAJE_DESCUENTO)
		{
			while(true)
			{
				System.out.println("Ingrese y, teniendo en cuenta que este representara el valor de \"y\" en la promocion "+tipoPromocion.toString());
				String cant= sc.nextLine();
				try
				{
					y=Double.parseDouble(cant);
					if(y<=0)
						System.out.println("¡Debe ingresar un numero mayor a cero!");
					else if(y<=x)
						System.out.println("¡y debe ser mayor a x!");		
					else 
						break;
				}
				catch(Exception e)
				{
					System.out.println("¡Debe ingresar un numero!");
				}

			}

		}
		boolean termine=false;
		ArrayList<String> idsProductos=new ArrayList<String>(); 
		while(!termine)
		{
			System.out.println("Ingrese el codigo de barras de los productos a los que desea aplicarle la promoción");
			System.out.println("Para indicar que termino envie la palabra \"termine\"");
			while(true)
			{
				System.out.println("Ingrese el codigo de barras del producto al que desea agregarle una promoción");
				System.out.println("Para indicar que termino envie la palabra \"termine\"");
				String cod= sc.nextLine();

				if(cod.equals("termine"))
				{
					termine=true;
					break;
				}
				if(!cod.trim().equals(""))
				{
					//TODO REVISAR SI ID EXISTEEEEEEE
					idsProductos.add(cod);
					System.out.println("agregado");
				}
				else
					System.out.println("¡El codigo no puede ser vacio!");


			}
		}

		String descripcion="";
		while(true)
		{	
			System.out.println("Ingrese la descripcion de la promocion:");
			descripcion = sc.nextLine();
			if(descripcion.trim().equals(""))
				System.out.println("¡El tipo promocion no puede ser vacia!");
			else 
				break;
		}


		//TODO GENERAR PROMOCION CON INFO Y DAR A IDPROMOCION VALOR GENERADO
		long idPromocion=0l;
		
		System.out.println("idPromocion: "+idPromocion+" ,x: "+x+" ,y: "+y
				+" fechaInicial: "+fechaInicial+" ,fechaFinal: "+fechaFinal+" ,tipoPromocion: "+tipoPromocion.toString()
				+" ,descripcion: "+descripcion);


		for(String codigoProducto:idsProductos)
		{
			//TODO REGISTRAR PROMOCION A PRODUCTO SUCURSAL CON idProductoSucursal
			String idProductoSucursal=idSucursal+"-"+codigoProducto;
			System.out.println("idProductoSucursal: "+idProductoSucursal+" ,idPromocion: "+idPromocion);

		}

try {
	persistencia.requerimiento7(descripcion, Timestamp.valueOf(fechaInicial), Timestamp.valueOf(fechaFinal),(int) x, (int) y, tipoPromocion);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}




	}

	public static void registrarPedidoProductoAUnProveedor(Scanner sc)
	{
		sc.nextLine();

	}


	public static void ingresarASucursal(Scanner sc)
	{
		sc.nextLine();

		while(true)
		{
			System.out.println("Ingrese el id de la Sucursal a la que desea ingresar");
			String cant= sc.nextLine();
			try
			{
				Integer.parseInt(cant);
				idSucursal=Long.valueOf(cant);

				//TODO ENCONTRAR SUCURSAL Y GUARDARLA EN
				//				sucursal=;

				break;
			}
			catch(Exception e)
			{
				idSucursal=-1l;
				System.out.println("¡Debe ingresar un numero!");
			}

		}
	}

	public static String getFechaActual()
	{
		Calendar c = Calendar.getInstance();

		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH)+1);
		String annio = Integer.toString(c.get(Calendar.YEAR));
		return(annio+"-"+mes+"-"+dia);	
	}

	public static int compararFechas(String fech,String fech2)
	{
		String[]a=fech.split("-");
		String[]b=fech.split("-");
		if(Integer.parseInt(a[1])>Integer.parseInt(a[1]))
			return 1;
		if(Integer.parseInt(a[1])<Integer.parseInt(a[1]))
			return -1;
		if(Integer.parseInt(a[2])>Integer.parseInt(a[2]))
			return 1;
		if(Integer.parseInt(a[2])<Integer.parseInt(a[2]))
			return -1;
		if(Integer.parseInt(a[3])>Integer.parseInt(a[3]))
			return 1;
		if(Integer.parseInt(a[3])<Integer.parseInt(a[3]))
			return -1;
		return 0;
	}

	private static void printMenuSucursal() {

		System.out.println("---------ISIS - Sistemas Transaccionales----------");
		System.out.println("---------------------Iteración 1----------------------");
		System.out.println("---------------------Super Andes----------------------");
		System.out.println("---------------------Sucursal "+ idSucursal+"----------------------");
		System.out.println("1) RF1. REGISTRAR PROVEEDORES ");
		System.out.println("2) RF2. REGISTRAR PRODUCTOS");		
		System.out.println("3) RF3. REGISTRAR CLIENTES");		
		System.out.println("4) RF4. REGISTRAR UNA SUCURSAL");		
		System.out.println("5) RF5. REGISTRAR UNA BODEGA A UNA SUCURSAL");		
		System.out.println("6) RF6. REGISTRAR UN ESTANTE EN UNA SUCURSAL");		
		System.out.println("7) RF7. REGISTRAR UNA PROMOCIÓN");		
		System.out.println("8) RF8. FINALIZAR UNA PROMOCIÓN");		
		System.out.println("9) RF9. REGISTRAR UN PEDIDO DE UN PRODUCTO A UN PROVEEDOR PARA UNA SUCURSAL");		
		System.out.println("10) RF10. REGISTRAR LA LLEGADA DE UN PEDIDO DE UN PRODUCTO A UNA SUCURSAL");		
		System.out.println("11) RF11. REGISTRAR UNA VENTA DE UN PRODUCTO EN UNA SUCURSAL");		
		System.out.println("12) RFC1. MOSTRAR EL DINERO RECOLECTADO POR VENTAS EN CADA SUCURSAL DURANTE UN PERIODO DE TIEMPO Y EN EL AÑO CORRIDO");		
		System.out.println("13) RFC2. MOSTRAR LAS 20 PROMOCIONES MÁS POPULARES.");		
		System.out.println("14) RFC3. MOSTRAR EL ÍNDICE DE OCUPACIÓN DE CADA UNA DE BODEGAS Y ESTANTES DE UNA SUCURSAL");		
		System.out.println("15) RFC4. MOSTRAR LOS PRODUCTOS QUE CUMPLEN CON CIERTA CARACTERÍSTICA");		
		System.out.println("16) RFC5. MOSTRAR LAS COMPRAS HECHAS POR SUPERANDES A LOS PROVEEDORES");		
		System.out.println("17) RFC6. MOSTRAR LAS VENTAS DE SUPERANDES A UN USUARIO DADO, EN UN RANGO DE FECHAS INDICADO");		
		System.out.println("0)  Salir");
		System.out.println("Digite el número de opción para ejecutar la tarea, luego presione enter: (Ej., 1):");

	}

	private static void printMenu() {

		System.out.println("---------ISIS - Sistemas Transaccionales----------");
		System.out.println("---------------------Iteración 1----------------------");
		System.out.println("---------------------Super Andes----------------------");
		System.out.println("1) RF4. REGISTRAR UNA SUCURSAL");		
		System.out.println("2) INGRESAR A SU SUCURSAL SUCURSAL");		
		//		System.out.println("6) RF6. REGISTRAR UN ESTANTE EN UNA SUCURSAL");		
		//		System.out.println("7) RF7. REGISTRAR UNA PROMOCIÓN");		
		//		System.out.println("8) RF8. FINALIZAR UNA PROMOCIÓN");		
		//		System.out.println("9) RF9. REGISTRAR UN PEDIDO DE UN PRODUCTO A UN PROVEEDOR PARA UNA SUCURSAL");		
		//		System.out.println("10) RF10. REGISTRAR LA LLEGADA DE UN PEDIDO DE UN PRODUCTO A UNA SUCURSAL");		
		//		System.out.println("11) RF11. REGISTRAR UNA VENTA DE UN PRODUCTO EN UNA SUCURSAL");		
		//		System.out.println("12) RFC1. MOSTRAR EL DINERO RECOLECTADO POR VENTAS EN CADA SUCURSAL DURANTE UN PERIODO DE TIEMPO Y EN EL AÑO CORRIDO");		
		//		System.out.println("13) RFC2. MOSTRAR LAS 20 PROMOCIONES MÁS POPULARES.");		
		//		System.out.println("14) RFC3. MOSTRAR EL ÍNDICE DE OCUPACIÓN DE CADA UNA DE BODEGAS Y ESTANTES DE UNA SUCURSAL");		
		//		System.out.println("15) RFC4. MOSTRAR LOS PRODUCTOS QUE CUMPLEN CON CIERTA CARACTERÍSTICA");		
		//		System.out.println("16) RFC5. MOSTRAR LAS COMPRAS HECHAS POR SUPERANDES A LOS PROVEEDORES");		
		//		System.out.println("17) RFC6. MOSTRAR LAS VENTAS DE SUPERANDES A UN USUARIO DADO, EN UN RANGO DE FECHAS INDICADO");		
		//		System.out.println("0)  Salir");
		//		System.out.println("Digite el número de opción para ejecutar la tarea, luego presione enter: (Ej., 1):");

	}
}
