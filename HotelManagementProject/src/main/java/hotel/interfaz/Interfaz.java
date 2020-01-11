package hotel.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.sql.Date;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import hotel.negocio.Hoteleria;
import hotel.negocio.VOHotel;

public class Interfaz extends JFrame implements ActionListener{


	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 

	private static Logger log = Logger.getLogger(Interfaz.class.getName());
	
	private Hoteleria hoteleria;
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Menú de la aplicación
	 */
	private JMenuBar menuBar;

	public Interfaz( )
	{
		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gráfica
		configurarFrame ( );
		if (guiConfig != null) 	   
		{
			crearMenu( guiConfig.getAsJsonArray("menuBar") );
		}

		hoteleria = new Hoteleria();
		panelDatos = new PanelDatos ( );

		setLayout (new BorderLayout());        
		add( panelDatos, BorderLayout.CENTER );        
	}

	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
		} 
		catch (Exception e)
		{
			//				e.printStackTrace ();	
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{	
			titulo = "Hotel APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation (50,50);
		setResizable( true );
		setBackground( Color.WHITE );

		setTitle( titulo );
		setSize ( ancho, alto);        
	}

	private void crearMenu(  JsonArray jsonMenu )
	{    	
		// Creación de la barra de menús
		menuBar = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	
				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject(); 
				String lb =   jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem( lb );
				mItem.addActionListener( this );
				mItem.setActionCommand(event);

				menu.add(mItem);
			}       
			menuBar.add( menu );
		}        
		setJMenuBar ( menuBar );	
	}
	

    public void adicionarHotel( )
    {
    	try 
    	{
    		String nombreTipo = JOptionPane.showInputDialog (this, "Nombre del tipo de bedida?", "Adicionar tipo de bebida", JOptionPane.QUESTION_MESSAGE);
    		if (nombreTipo != null)
    		{
        		System.out.println("metodo1");
        		VOHotel tb = hoteleria.adicionarHotel(nombreTipo, 10);
        		System.out.println("metodo");
        		if (tb == null)
        		{
            		System.out.println("nulo");
        			throw new Exception ("No se pudo crear un tipo de bebida con nombre: " + nombreTipo);
        		}
        		String resultado = "En adicionarTipoBebida\n\n";
        		resultado += "Tipo de bebida adicionado exitosamente: " + tb;
        		System.out.println("aqui va");
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void reservarHabitacion()
    {
    	String idCliente = JOptionPane.showInputDialog (this, "Digite el id del cliente a reservar habitacion: ", "Id de la Habitación", JOptionPane.QUESTION_MESSAGE);
    	String idHabitacion = JOptionPane.showInputDialog (this, "Digite el id de la habitacion a reservar: ", "Id del cliente Habitación", JOptionPane.QUESTION_MESSAGE);
    	if(idCliente != null)
    	{
    		long idClienteL = Long.parseLong(idCliente);
    		long idHabitacionL = Long.parseLong(idHabitacion);
    		hoteleria.reservarHabitacion(idClienteL, idHabitacionL);
    	}
    }
    
    public void reservarServicio()
    {
    	String respuesta = JOptionPane.showInputDialog (this, "Digite su id y el nombre del servicio, separados por una coma: ", "Reservar Habitación", JOptionPane.QUESTION_MESSAGE);
    	if(respuesta != null)
    	{
    		long id = Long.parseLong(respuesta);
    		hoteleria.reservarServicio(id);
    	}
    }
    
    public void checkIn()
    {
    	String respuesta = JOptionPane.showInputDialog (this, "Digite su id y el del cliente, separados por una coma: ", "CheckIn", JOptionPane.QUESTION_MESSAGE);
    	if(respuesta != null)
    	{
    		
    		long idE = Long.parseLong(respuesta.split(",")[0]);
    		long idC = Long.parseLong(respuesta.split(",")[1]);
    		hoteleria.checkIn(idE, idC);
    	}
    }
    
    public void registrarConsumo()
    {
    	String respuesta = JOptionPane.showInputDialog (this, "Digite el id del producto y el del cliente, separados por una coma: ", "RegistrarConsumo", JOptionPane.QUESTION_MESSAGE);
    	if(respuesta != null)
    	{	
    		long idP = Long.parseLong(respuesta.split(",")[0]);
    		long idC = Long.parseLong(respuesta.split(",")[1]);
    		hoteleria.registrarConsumo(idP, idC);
    	}
    }
    
    public void checkOut()
    {
    	String respuesta = JOptionPane.showInputDialog (this, "Digite el id del cliente", "CheckOut", JOptionPane.QUESTION_MESSAGE);
    	if(respuesta != null)
    	{
    		
    		long idC = Long.parseLong(respuesta);
    		hoteleria.checkOut(idC);
    	}
    }
    
    public void dineroHabitaciones()
    {
    	hoteleria.dineroHabitaciones();
    }
    
    public void reservar()
    {
    	String respuesta = JOptionPane.showInputDialog (this, "Digite el numero de habitaciones suites, dobles e individuales, separadas por una coma \n Adicionalmente, digite 1 en cada campo que desee, o 0 de lo contrario, separados por comas: \n SalonC, SalonR, Bar, R", "Reserva", JOptionPane.QUESTION_MESSAGE);
    	if(respuesta != null)
    	{
    		String[] aux = respuesta.split(",");
    		int suites = Integer.parseInt(aux[0]);
    		int dobles = Integer.parseInt(aux[1]);
    		int indivi = Integer.parseInt(aux[2]);
    		int salonC = Integer.parseInt(aux[3]);
    		int salonR = Integer.parseInt(aux[4]);
    		int bar = Integer.parseInt(aux[5]);
    		int restaurante = Integer.parseInt(aux[6]);
    		hoteleria.reservas(suites,dobles,indivi,salonC,salonR,bar,restaurante);
    	}
    }
    
    public void cancelarReserva()
    {
    	hoteleria.cancelarReservas();
    }
    
    public void finConvencion()
    {
    	hoteleria.finConvencion();
    }
    public void registrarIniMan()
    {
    	String respuesta = JOptionPane.showInputDialog (this, "Digite la fecha de inicio separada por / y la fecha de fin igual. Ambas separadas por una coma \n Ej: 2019/4/22,2019/4/23", "CheckOut", JOptionPane.QUESTION_MESSAGE);
    	String[] aux = respuesta.split(",");
    	Integer ano = Integer.parseInt(aux[0].split("/")[0]);
    	Integer mes = Integer.parseInt(aux[0].split("/")[1]);
    	Integer dia = Integer.parseInt(aux[0].split("/")[2]);
    	Date ini = new Date(ano, mes, dia);
    	ano = Integer.parseInt(aux[1].split("/")[0]);
    	mes = Integer.parseInt(aux[1].split("/")[1]);
    	dia = Integer.parseInt(aux[1].split("/")[2]);
    	Date fin = new Date(ano, mes, dia);
    	hoteleria.inicioMantenimiento(ini, fin);
    }
    
    public void registrarFinMan()
    {
    	hoteleria.finMantenimiento();
    }
	public static void main( String[] args )
	{
		try
		{

			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			Interfaz interfaz = new Interfaz( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
	
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
	
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
		return resultado;
	}

	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
		try 
		{
			Method req = Interfaz.class.getMethod ( evento );			
			req.invoke ( this );
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}
}
