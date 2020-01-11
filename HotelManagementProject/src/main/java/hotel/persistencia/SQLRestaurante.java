package hotel.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hotel.negocio.PlanConsumo;
import hotel.negocio.Producto;
import hotel.negocio.Restaurante;
import hotel.negocio.VORestaurante;

public class SQLRestaurante {
	
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
	public SQLRestaurante (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Restaurante a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idRestaurante - El identificador del Restaurante
	 * @param nombre - El nombre del Restaurante
	 * @param ciudad - La ciudad del Restaurante
	 * @param presupuesto - El presupuesto del Restaurante (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del Restaurante
	 * @return El número de tuplas insertadas
	 */
	public long adicionarRestaurante (PersistenceManager pm, long id, String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, String estilo, List<Producto> productos) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaRestaurante () + "(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, estilo, productos) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, estilo, productos);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Restaurante de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idRestaurante - El identificador del Restaurante
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarRestaurantePorId (PersistenceManager pm, long idRestaurante)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRestaurante () + " WHERE id = ?");
        q.setParameters(idRestaurante);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Restaurante de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idRestaurante - El identificador del Restaurante
	 * @return El objeto Restaurante que tiene el identificador dado
	 */
	public VORestaurante darRestaurantePorId (PersistenceManager pm, long idRestaurante) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRestaurante () + " WHERE id = ?");
		q.setResultClass(Restaurante.class);
		q.setParameters(idRestaurante);
		return (VORestaurante) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS RestauranteES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param numeroRestaurante - El nombre de Restaurante buscado
	 * @return Una lista de objetos Restaurante que tienen el nombre dado
	 */
	public List<Restaurante> darRestaurantesPorNombre (PersistenceManager pm, String nombreRestaurante) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRestaurante () + " WHERE numero = ?");
		q.setResultClass(Restaurante.class);
		q.setParameters(nombreRestaurante);
		return (List<Restaurante>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Restaurantes de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Restaurante
	 */
	public List<Restaurante> darRestaurantes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRestaurante ());
		q.setResultClass(Restaurante.class);
		return (List<Restaurante>) q.executeList();
	}


}
