/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Hotel Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package hotel.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Hotel
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLUtil
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaHotel.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaHotel pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLUtil (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
		System.out.println("xd");
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqHotel () + ".nextval FROM DUAL");
        System.out.println("xd2");
        q.setResultClass(Long.class);
        System.out.println("xd3");
        long resp = (long) q.executeUnique();
        System.out.println("xd4");
        return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarHotel (PersistenceManager pm)
	{
        Query qCliente = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente());          
        Query qEmpleado = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpleado());
        Query qGimnasio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaGimnasio());
        Query qHabitacion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacion());
        Query qHotel = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel());
        Query qInternet = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaInternet());
        Query qBar = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBar());
        Query qPlanConsumo = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPlanConsumo());
        Query qPrestamoUtensilios = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPrestamoUtensilios());
        Query qProducto = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProducto());
        Query qRestaurante = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRestaurante());
        Query qSalonConferencia = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSalonConferencia());
        Query qSalonReunion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSalonReunion());
        Query qServicio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicio ());
        Query qServicioAdicional = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicioAdicional());
        Query qSpa = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSpa());
        Query qSupermercado = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSupermercado());
        Query qTienda = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTienda());

        long barEliminados = (long) qBar.executeUnique ();
        long clientesEliminados = (long) qBar.executeUnique ();
        long empleadoEliminados = (long) qBar.executeUnique ();
        long gimnasioEliminados = (long) qBar.executeUnique ();
        long habitacionEliminados = (long) qBar.executeUnique ();
        long hotelEliminados = (long) qBar.executeUnique ();
        long internetEliminados = (long) qBar.executeUnique ();
        long lavadoPlanchadoEliminados = (long) qBar.executeUnique ();
        long piscinaEliminados = (long) qBar.executeUnique ();
        long planConsumoEliminados = (long) qBar.executeUnique ();
        long prestamoUtensiliosEliminados = (long) qBar.executeUnique ();
        long productoEliminados = (long) qBar.executeUnique ();
        long restauranteEliminados = (long) qBar.executeUnique ();
        long salonConferenciaEliminados = (long) qBar.executeUnique ();
        long salonReunionEliminados = (long) qBar.executeUnique ();
        long servicioEliminados = (long) qBar.executeUnique ();
        long servicioAdicionalEliminados = (long) qBar.executeUnique ();
        long spaEliminados = (long) qBar.executeUnique ();
        long supermercadoEliminados = (long) qBar.executeUnique ();
        long tiendaEliminados = (long) qBar.executeUnique ();
        
        return new long[] {barEliminados, clientesEliminados,empleadoEliminados,gimnasioEliminados,habitacionEliminados,
        		hotelEliminados,internetEliminados,lavadoPlanchadoEliminados,piscinaEliminados,planConsumoEliminados,prestamoUtensiliosEliminados,
        		productoEliminados,restauranteEliminados,salonConferenciaEliminados,salonReunionEliminados, servicioEliminados,
        		servicioAdicionalEliminados, spaEliminados, supermercadoEliminados,tiendaEliminados};
	}

}
