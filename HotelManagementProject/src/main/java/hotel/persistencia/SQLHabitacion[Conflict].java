package hotel.persistencia;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hotel.negocio.Habitacion;
import hotel.negocio.VOHabitacion;

public class SQLHabitacion {
	
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
	public SQLHabitacion (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Habitacion a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idHabitacion - El identificador del Habitacion
	 * @param nombre - El nombre del Habitacion
	 * @param ciudad - La ciudad del Habitacion
	 * @param presupuesto - El presupuesto del Habitacion (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del Habitacion
	 * @return El número de tuplas insertadas
	 */
	public long adicionarHabitacion (PersistenceManager pm, long id, String tipo, int capacidad, char comedorCocina, char jacuzzi, char television, char minibar,
			char cafetera, int consumo, char ocupado, Date reserva, Date inicioMantenimiento, Date finMantenimiento) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHabitacion () + "(id, tipo, capacidad, comedorCocina, jacuzzi, television, minibar, cafetera, consumo, ocupado, reserva, inicioMantenimiento, finMantenimiento) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, tipo, capacidad, comedorCocina, jacuzzi, television, minibar, cafetera, consumo, ocupado, reserva, inicioMantenimiento, finMantenimiento);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Habitacion de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idHabitacion - El identificador del Habitacion
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarHabitacionPorId (PersistenceManager pm, long idHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacion () + " WHERE id = ?");
        q.setParameters(idHabitacion);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Habitacion de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idHabitacion - El identificador del Habitacion
	 * @return El objeto Habitacion que tiene el identificador dado
	 */
	public VOHabitacion darHabitacionPorId (PersistenceManager pm, long idHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion () + " WHERE id = ?");
		q.setResultClass(Habitacion.class);
		q.setParameters(idHabitacion);
		return (VOHabitacion) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS HabitacionES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param numeroHabitacion - El nombre de Habitacion buscado
	 * @return Una lista de objetos Habitacion que tienen el nombre dado
	 */
	public List<Habitacion> darHabitacionsPorNumero (PersistenceManager pm, int numeroHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion () + " WHERE numero = ?");
		q.setResultClass(Habitacion.class);
		q.setParameters(numeroHabitacion);
		return (List<Habitacion>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Habitacions de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Habitacion
	 */
	public List<Habitacion> darHabitaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion ());
		System.out.println(q.executeList().iterator());
		Iterator ite = q.executeList().iterator();
		Habitacion habitacion = new Habitacion();
		
		while (ite.hasNext()) {
			ite.next();
			System.out.println(ite.getClass());
			System.out.println(ite.toString());
		}
		q.setResultClass(Habitacion.class);
		System.out.println(q.executeList());
		List<Habitacion> lista = (List<Habitacion>) q.executeResultList();
		System.out.println(lista);
		return (List<Habitacion>) q.execute();
	}

	public void cancelarReservas(PersistenceManager pm) {
		// TODO Auto-generated method stub
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaHabitacion () + " SET ID_RESERVADO = null AND RESERVADO = null WHERE ID = 1" );
		q.executeUnique();
	}

	public void finMatenimiento(PersistenceManager pm) {
		// TODO Auto-generated method stub
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaHabitacion () + " SET INICIOMANTENIMIENTO = null  AND FINMANTENIMIENTO = null WHERE ID = 2" );
		q.executeUnique();
	}

}
