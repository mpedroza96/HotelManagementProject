package hotel.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hotel.negocio.Servicio;
import hotel.negocio.VOServicio;

public class SQLServicio {
	
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
	public SQLServicio (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Servicio a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idServicio - El identificador del Servicio
	 * @param nombre - El nombre del Servicio
	 * @param ciudad - La ciudad del Servicio
	 * @param presupuesto - El presupuesto del Servicio (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del Servicio
	 * @return El número de tuplas insertadas
	 */
	public long adicionarServicio (PersistenceManager pm, long id, String nombre, int duracion, int costo, String tipo, char ofrece) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicio () + "(id, nombre, duracion, costo, tipo, ofrece) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, duracion, costo, tipo, ofrece);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Servicio de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idServicio - El identificador del Servicio
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarServicioPorId (PersistenceManager pm, long idServicio)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicio () + " WHERE id = ?");
        q.setParameters(idServicio);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Servicio de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idServicio - El identificador del Servicio
	 * @return El objeto Servicio que tiene el identificador dado
	 */
	public VOServicio darServicioPorId (PersistenceManager pm, long idServicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicio () + " WHERE id = ?");
		q.setResultClass(Servicio.class);
		q.setParameters(idServicio);
		return (VOServicio) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS ServicioES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param numeroServicio - El nombre de Servicio buscado
	 * @return Una lista de objetos Servicio que tienen el nombre dado
	 */
	public List<Servicio> darServiciosPorNombre (PersistenceManager pm, String nombreServicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicio () + " WHERE numero = ?");
		q.setResultClass(Servicio.class);
		q.setParameters(nombreServicio);
		return (List<Servicio>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Servicios de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Servicio
	 */
	public List<Servicio> darServicios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicio ());
		q.setResultClass(Servicio.class);
		return (List<Servicio>) q.executeList();
	}


}
