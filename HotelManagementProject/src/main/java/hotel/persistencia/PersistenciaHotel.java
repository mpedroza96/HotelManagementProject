package hotel.persistencia;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import hotel.negocio.Bar;
import hotel.negocio.Cliente;
import hotel.negocio.Empleado;
import hotel.negocio.Gimnasio;
import hotel.negocio.Habitacion;
import hotel.negocio.Hotel;
import hotel.negocio.Internet;
import hotel.negocio.LavadoPlanchado;
import hotel.negocio.Piscina;
import hotel.negocio.PlanConsumo;
import hotel.negocio.PrestamoUtensilios;
import hotel.negocio.Producto;
import hotel.negocio.Restaurante;
import hotel.negocio.SalonConferencia;
import hotel.negocio.SalonReunion;
import hotel.negocio.Servicio;
import hotel.negocio.ServicioAdicional;
import hotel.negocio.Spa;
import hotel.negocio.Supermercado;
import hotel.negocio.Tienda;
import hotel.negocio.VOBar;
import hotel.negocio.VOGimnasio;
import hotel.negocio.VOHabitacion;
import hotel.negocio.VOInternet;
import hotel.negocio.VOLavadoPlanchado;
import hotel.negocio.VOPiscina;
import hotel.negocio.VOPrestamoUtensilios;
import hotel.negocio.VORestaurante;
import hotel.negocio.VOSalonConferencia;
import hotel.negocio.VOSalonReunion;
import hotel.negocio.VOServicio;
import hotel.negocio.VOServicioAdicional;
import hotel.negocio.VOSpa;
import hotel.negocio.VOSupermercado;
import hotel.negocio.VOTienda;


public class PersistenciaHotel {

	private static Logger log = Logger.getLogger(PersistenciaHotel.class.getName());
	
	public static final String SQL = "javax.jdo.query.SQL";
	
	private static PersistenciaHotel instance;
	private PersistenceManagerFactory pmf;
	private List<String> tablas;
	private SQLUtil sqlUtil;
	private SQLBar sqlBar;
	private SQLCliente sqlCliente;
	private SQLEmpleado sqlEmpleado;
	private SQLGimnasio sqlGimnasio;
	private SQLHabitacion sqlHabitacion;
	private SQLHotel sqlHotel;
	private SQLInternet sqlInternet;
	private SQLLavadoPlanchado sqlLavadoPlachado;
	private SQLPiscina sqlPiscina;
	private SQLPlanConsumo sqlPlanConsumo;
	private SQLPrestamoUtensilios sqlPrestamoUtensilios;
	private SQLProducto sqlProducto;
	private SQLRestaurante sqlRestaurante;
	private SQLSalonConferencia sqlSalonConferencia;
	private SQLSalonReunion sqlSalonReunion;
	private SQLServicio sqlServicio;
	private SQLServicioAdicional sqlServicioAdicional;
	private SQLSpa sqlSpa;
	private SQLSupermercado sqlSupermercado;
	private SQLTienda sqlTienda;

	private PersistenciaHotel()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Hoteleria");
		crearClasesSQL();

		tablas= new LinkedList<String>();
		tablas.add("Hoteleria_sequence");
		tablas.add("BAR");
		tablas.add("CLIENTE");
		tablas.add("EMPLEADO");
		tablas.add("GIMNASIO");
		tablas.add("HABITACION");
		tablas.add("HOTEL");
		tablas.add("INTERNET");
		tablas.add("LAVADOPLANCHADO");
		tablas.add("PISCINA");
		tablas.add("PLANCONSUMO");
		tablas.add("PRESTAMOUTENSILIOS");
		tablas.add("PRODUCTO");
		tablas.add("RESTAURANTE");
		tablas.add("SALONCONFERENCIA");
		tablas.add("SALONREUNION");
		tablas.add("SERVICIO");
		tablas.add("SERVICIOADICIONAL");
		tablas.add("SPA");
		tablas.add("SUPERMERCADO");
		tablas.add("TIENDA");		
	}

	private PersistenciaHotel(JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas(tableConfig);

		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		//log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	public static PersistenciaHotel getInstance()
	{
		if(instance == null)
		{
			instance = new PersistenciaHotel();
		}
		return instance;
	}

	public static PersistenciaHotel getInstance(JsonObject tableConfig)
	{
		if(instance == null)
		{
			instance = new PersistenciaHotel(tableConfig);
		}
		return instance;
	}

	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}

	private List<String> leerNombresTablas(JsonObject tableConfig) 	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}

		return resp;
	}

	private void crearClasesSQL() {
	  sqlBar = new SQLBar(this);
	  sqlCliente = new SQLCliente(this);
	  sqlEmpleado = new SQLEmpleado(this);
	  sqlGimnasio = new SQLGimnasio(this);
	  sqlHabitacion = new SQLHabitacion(this);
	  sqlHotel = new SQLHotel(this);
	  sqlInternet = new SQLInternet(this);
	  sqlLavadoPlachado = new SQLLavadoPlanchado(this);
	  sqlPiscina = new SQLPiscina(this);
	  sqlPlanConsumo = new SQLPlanConsumo(this);
	  sqlPrestamoUtensilios = new SQLPrestamoUtensilios(this);
	  sqlProducto = new SQLProducto(this);
	  sqlRestaurante = new SQLRestaurante(this);
	  sqlSalonConferencia = new SQLSalonConferencia(this);
	  sqlSalonReunion = new SQLSalonReunion(this);
	  sqlServicio = new SQLServicio(this);
	  sqlSpa = new SQLSpa(this);
	  sqlSupermercado = new SQLSupermercado(this);
	  sqlTienda = new SQLTienda(this);
	  sqlUtil = new SQLUtil(this);

	}
	
	public String darSeqHotel()
	{
		return tablas.get (0);
	}
	public String darTablaBar() {
		// TODO Auto-generated method stub
		return tablas.get (1);
	}

	public String darTablaCliente() {
		// TODO Auto-generated method stub
		return tablas.get (2);
	}
	
	public String darTablaEmpleado() {
		// TODO Auto-generated method stub
		return tablas.get (3);
	}

	public String darTablaGimnasio() {
		// TODO Auto-generated method stub
		return tablas.get (4);
	}

	public String darTablaHabitacion() {
		// TODO Auto-generated method stub
		return tablas.get (5);
	}

	public String darTablaHotel() {
		// TODO Auto-generated method stub
		return tablas.get (6);
	}

	public String darTablaInternet() {
		// TODO Auto-generated method stub
		return tablas.get (7);
	}

	public String darTablaLavadoPlanchado() {
		// TODO Auto-generated method stub
		return tablas.get (8);
	}

	public String darTablaPiscina() {
		// TODO Auto-generated mthod stub
		return tablas.get (9);
	}

	public String darTablaPlanConsumo() {
		// TODO Auto-generated method stub
		return tablas.get (10);
	}

	public String darTablaPrestamoUtensilios() {
		// TODO Auto-generated method stub
		return tablas.get (11);
	}

	public String darTablaProducto() {
		// TODO Auto-generated method stub
		return tablas.get (12);
	}

	public String darTablaRestaurante() {
		// TODO Auto-generated method stub
		return tablas.get (13);
	}

	public String darTablaSalonConferencia() {
		// TODO Auto-generated method stub
		return tablas.get (14);
	}

	public String darTablaSalonReunion() {
		// TODO Auto-generated method stub
		return tablas.get (15);
	}

	public String darTablaServicio() {
		// TODO Auto-generated method stub
		return tablas.get (16);
	}

	public String darTablaServicioAdicional() {
		// TODO Auto-generated method stub
		return tablas.get (17);
	}

	public String darTablaSpa() {
		// TODO Auto-generated method stub
		return tablas.get (18);
	}

	public String darTablaSupermercado() {
		// TODO Auto-generated method stub
		return tablas.get (19);
	}

	public String darTablaTienda() {
		// TODO Auto-generated method stub
		return tablas.get (20);
	}

	private long nextval ()
	{
		System.out.println("val");
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		System.out.println("aqui");
        //log.trace ("Generando secuencia: " + resp);
        return resp;
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
	
	//BAR
	
	public VOBar adicionarBar(String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, String estilo, List<Producto> productos)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlBar.adicionarBar(pm, id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, estilo, productos);
            tx.commit();
            
            log.trace ("Inserción de Empleado: " + nombre+ ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Bar(estilo, productos);
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	public Empleado adicionarEmpleado(String tipo, Long id_hotel)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlEmpleado.adicionarEmpleado(pm, id, tipo, id_hotel);
            tx.commit();
            
            log.trace ("Inserción de Empleado: " + tipo+ ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Empleado(id, tipo);
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Cliente adicionarCliente(String nombre, Date fechaEntrada, Date fechaSalida, String pago, PlanConsumo planDeConsumo )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlCliente.adicionarCliente(pm, id, nombre, fechaEntrada, fechaSalida, pago, planDeConsumo);
            tx.commit();
            
            log.trace ("Inserción de Empleado: " + nombre+ ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Cliente(id, nombre, fechaEntrada, fechaSalida, pago, planDeConsumo);
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public VOGimnasio adicionarGimnasio(String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, int maquinas)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlGimnasio.adicionarGimnasio(pm, id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, maquinas);
            tx.commit();
            
            log.trace ("Inserción de Empleado: " + nombre+ ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Gimnasio(maquinas);
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	public VOHabitacion adicionarHabitacion (String tipo, int capacidad, char comedorCocina, char jacuzzi, char television, char minibar,
			char cafetera, int consumo, char ocupado, Date reserva, Date inicioMantenimiento, Date finMantenimiento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlHabitacion.adicionarHabitacion(pm, id, tipo, capacidad, comedorCocina, jacuzzi, television, minibar, cafetera, consumo, ocupado, reserva, inicioMantenimiento, finMantenimiento);
            tx.commit();
            
            log.trace ("Inserción de Empleado: " + id+ ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Habitacion();
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Hotel adicionarHotel(String nombre, int calificacion)
	{
		System.out.println("hot");
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
		System.out.println("normal");
        try
        {
    		System.out.println("intento");
            tx.begin();
    		System.out.println("begin");
            long id = nextval ();
    		System.out.println("netx");
            long tuplasInsertadas = sqlHotel.adicionarHotel(pm, id, nombre, calificacion);
    		System.out.println(tuplasInsertadas);
            tx.commit();
            
           // log.trace ("Inserción de Hotel: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Hotel(nombre, calificacion);
        }
        catch (Exception e)
        {
        	//log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
    		System.out.println("se metio");
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public VOInternet adicionarInternet(String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlInternet.adicionarInternet(pm, id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento);
            tx.commit();
            
            log.trace ("Inserción de : " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Internet();
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public VOLavadoPlanchado adicionarLavadoPlanchado(String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, String tipo, int numPrendas)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlLavadoPlachado.adicionarLavadoPlanchado(pm, id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, tipo, numPrendas);
            tx.commit();
            
            log.trace ("Inserción de : " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new LavadoPlanchado(tipo, numPrendas);
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public VOPiscina adicionarPiscina(String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, int profundidad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlPiscina.adicionarPiscina(pm, id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, profundidad);
            tx.commit();
            
            log.trace ("Inserción de : " +nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Piscina(profundidad);
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public PlanConsumo adicionarPlanConsumo(String nombre, String tipo, int costo, Date fecha)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlPlanConsumo.adicionarPlanConsumo(pm, id, nombre, tipo, costo, fecha);
            tx.commit();
            
            log.trace ("Inserción de : " +nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new PlanConsumo(tipo, costo, fecha);
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public VOPrestamoUtensilios adicionarPrestamoUtensilios(String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, char estado, String tipo, char retorno)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlPrestamoUtensilios.adicionarPrestamoUtensilios(pm, id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, estado, tipo, retorno);
            tx.commit();
            
            log.trace ("Inserción de : " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new PrestamoUtensilios(estado, tipo, retorno);
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Producto adicionarProducto(int costo, String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlProducto.adicionarProducto(pm, id, costo, nombre);
            tx.commit();
            
            log.trace ("Inserción de : " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Producto(costo, nombre);
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public VORestaurante adicionarRestaurante(String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, String estilo, List<Producto> productos)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlRestaurante.adicionarRestaurante(pm, id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, estilo, productos);
            tx.commit();
            
            log.trace ("Inserción de : " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Restaurante(estilo, productos);
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	//RF2
	
	public VOSalonConferencia adicionarSalonConferencia(String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, int duracion, int costoAdicional)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlSalonConferencia.adicionarSalonConferencia(pm, id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, duracion, costoAdicional);
            tx.commit();
            
            log.trace ("Inserción de : " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new SalonConferencia(duracion, costoAdicional);
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public VOSalonReunion adicionarSalonReunion(String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, int duracion, int costoAdicional)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlSalonReunion.adicionarSalonReunion(pm, id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, duracion, costoAdicional);
            tx.commit();
            
            log.trace ("Inserción de : " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new SalonReunion(duracion, costoAdicional);
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public VOServicio adicionarServicio(String nombre, int duracion, int costo, String tipo, char ofrece)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlServicio.adicionarServicio(pm, id, nombre, duracion, costo, tipo, ofrece);
            tx.commit();
            
            log.trace ("Inserción de : " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Servicio(id, nombre, duracion, costo, tipo, ofrece);
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public VOServicioAdicional adicionarServicioAdicional(String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlServicioAdicional.adicionarServicioAdicional(pm, id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo,
        			costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento);
            tx.commit();
            
            log.trace ("Inserción de : " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ServicioAdicional();
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public VOSpa adicionarSpa(String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, List<Servicio> servicios)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlSpa.adicionarSpa(pm, id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, servicios);
            tx.commit();
            
            log.trace ("Inserción de : " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Spa(servicios);
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public VOSupermercado adicionarSupermercado(String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, List<Producto> productos)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlSupermercado.adicionarSupermercado(pm, id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, productos);
            tx.commit();
            
            log.trace ("Inserción de : " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Supermercado(productos);
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public VOTienda adicionarTienda(String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, List<Producto> productos)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlTienda.adicionarTienda(pm, id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, productos);
            tx.commit();
            
            log.trace ("Inserción de : " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Tienda(productos);
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public void reservarHabitacion(long idCliente, long idHabitacion) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            sqlCliente.reservarHabitacion(pm,idCliente, idHabitacion);
            tx.commit();
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public void reservarServicio(long id) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            sqlSalonReunion.reservarSalon(pm,id);
            tx.commit();
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public void checkIn(long idC, long idE) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            sqlCliente.checkIn(pm,idC, idE);
            tx.commit();
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public void registrarConsumo(long idP, long idC) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            sqlProducto.registrarConsumo(pm,idC, idP);
            tx.commit();
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public void checkOut(long idC) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            sqlCliente.checkOut(pm,idC);
            tx.commit();
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public void dineroHabitaciones() {
		// TODO Auto-generated method stub
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            sqlCliente.dinero(pm);
            tx.commit();
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public void reservas(int suites, int dobles, int indivi, int salonC, int salonR, int bar, int restaurante) {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            System.out.println("lista habitaciones");
    		List<Habitacion> lista = sqlHabitacion.darHabitaciones(pm);
    		System.out.println("lista habitaciones"+lista.get(0).getTipo());
            tx.commit();
            List<Long> aux = new ArrayList();
            int contadorS =0;
            int contadorD =0;
            int contadorI =0;
    		for(int i = 0; i<lista.size(); i++)
    		{
    			if(lista.get(i).getTipo().equalsIgnoreCase("suite") && lista.get(i).getOcupado() == 'N')
    			{
    				aux.add(lista.get(i).getId());
    				contadorS++;
    			}
    			if(lista.get(i).getTipo().equalsIgnoreCase("doble") && lista.get(i).getOcupado() == 'N')
    			{
    				aux.add(lista.get(i).getId());
    				contadorD++;
    			}
    			if(lista.get(i).getTipo().equalsIgnoreCase("individual") && lista.get(i).getOcupado() == 'N')
    			{
    				aux.add(lista.get(i).getId());
    				contadorI++;
    			}
    		}
    		System.out.println("Contadores: "+contadorD+contadorI+contadorS);
    		if(contadorS>= suites && contadorD >=dobles && contadorI >= indivi)
    		{
    			for(int i = 0; i< aux.size();i++)
    			{
    				
    				Query q = pm.newQuery(SQL, "UPDATE " + darTablaHabitacion () + " SET RESERVADO = TO_DATE('22/Abril/2019 8:30:00AM','DD/MON/YY HH:MI:SSAM') AND ID_RESERVADO  = 100 WHERE ID = " + aux.get(i));
    				q.executeUnique();
    				
    			}
    			System.out.println("Reservo");
    			tx.commit();
    		}
    		
    		else {
    			System.out.println("No reservo");
    			tx.rollback();
    		}
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public void inicioMantenimiento(java.sql.Date ini, java.sql.Date fin) {
		// TODO Auto-generated method stub
		
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
    		Query q = pm.newQuery(SQL, "UPDATE " + darTablaHabitacion () + " SET INICIOMATENIMIENTO = " + ini+  " AND FINMANTENIMIENTO = " + fin+ " WHERE ID = 2" );
    		q.executeUnique();
            tx.commit();
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public void cancelarReservas() {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
    	    sqlHabitacion.cancelarReservas(pm);
            tx.commit();
    	
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public void finConvencion() {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
    	    sqlHabitacion.cancelarReservas(pm);
    	    int monto = sqlServicioAdicional.costoConvencion(pm);
			Query q = pm.newQuery(SQL, "UPDATE " + darTablaCliente () + " SET CONSUMO = "+ monto + " WHERE ID = 100" );
			q.executeUnique();	
    	    tx.commit();
    	
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	
	}

	public void finMantenimiento() {
		// TODO Auto-generated method stub
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
    	    sqlHabitacion.finMatenimiento(pm);
            tx.commit();
    	
        }
        catch (Exception e)
        {
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

}
