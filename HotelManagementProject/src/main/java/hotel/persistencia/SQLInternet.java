package hotel.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hotel.negocio.Internet;
import hotel.negocio.PlanConsumo;
import hotel.negocio.VOInternet;

public class SQLInternet {
	
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
	public SQLInternet (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Internet a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idInternet - El identificador del Internet
	 * @param nombre - El nombre del Internet
	 * @param ciudad - La ciudad del Internet
	 * @param presupuesto - El presupuesto del Internet (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del Internet
	 * @return El número de tuplas insertadas
	 */
	public long adicionarInternet (PersistenceManager pm, long id, String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaInternet () + "(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Internet de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idInternet - El identificador del Internet
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarInternetPorId (PersistenceManager pm, long idInternet)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaInternet () + " WHERE id = ?");
        q.setParameters(idInternet);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Internet de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idInternet - El identificador del Internet
	 * @return El objeto Internet que tiene el identificador dado
	 */
	public VOInternet darInternetPorId (PersistenceManager pm, long idInternet) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaInternet () + " WHERE id = ?");
		q.setResultClass(Internet.class);
		q.setParameters(idInternet);
		return (VOInternet) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS InternetES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param numeroInternet - El nombre de Internet buscado
	 * @return Una lista de objetos Internet que tienen el nombre dado
	 */
	public List<Internet> darInternetsPorNombre (PersistenceManager pm, String nombreInternet) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaInternet () + " WHERE numero = ?");
		q.setResultClass(Internet.class);
		q.setParameters(nombreInternet);
		return (List<Internet>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Internets de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Internet
	 */
	public List<Internet> darInternets (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaInternet ());
		q.setResultClass(Internet.class);
		return (List<Internet>) q.executeList();
	}


}
