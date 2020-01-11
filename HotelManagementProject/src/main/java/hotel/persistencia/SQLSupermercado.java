package hotel.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hotel.negocio.PlanConsumo;
import hotel.negocio.Producto;
import hotel.negocio.Supermercado;
import hotel.negocio.VOSupermercado;

public class SQLSupermercado {
	
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
	public SQLSupermercado (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Supermercado a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idSupermercado - El identificador del Supermercado
	 * @param nombre - El nombre del Supermercado
	 * @param ciudad - La ciudad del Supermercado
	 * @param presupuesto - El presupuesto del Supermercado (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del Supermercado
	 * @return El número de tuplas insertadas
	 */
	public long adicionarSupermercado (PersistenceManager pm, long id, String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, List<Producto> productos) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSupermercado () + "(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, productos) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, productos);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Supermercado de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idSupermercado - El identificador del Supermercado
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarSupermercadoPorId (PersistenceManager pm, long idSupermercado)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSupermercado () + " WHERE id = ?");
        q.setParameters(idSupermercado);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Supermercado de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idSupermercado - El identificador del Supermercado
	 * @return El objeto Supermercado que tiene el identificador dado
	 */
	public VOSupermercado darSupermercadoPorId (PersistenceManager pm, long idSupermercado) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSupermercado () + " WHERE id = ?");
		q.setResultClass(Supermercado.class);
		q.setParameters(idSupermercado);
		return (VOSupermercado) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS SupermercadoES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param numeroSupermercado - El nombre de Supermercado buscado
	 * @return Una lista de objetos Supermercado que tienen el nombre dado
	 */
	public List<Supermercado> darSupermercadosPorNombre (PersistenceManager pm, String nombreSupermercado) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSupermercado () + " WHERE numero = ?");
		q.setResultClass(Supermercado.class);
		q.setParameters(nombreSupermercado);
		return (List<Supermercado>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Supermercados de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Supermercado
	 */
	public List<Supermercado> darSupermercados (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSupermercado ());
		q.setResultClass(Supermercado.class);
		return (List<Supermercado>) q.executeList();
	}


}
