package hotel.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hotel.negocio.PlanConsumo;
import hotel.negocio.Producto;
import hotel.negocio.Tienda;
import hotel.negocio.VOTienda;

public class SQLTienda {
	
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
	public SQLTienda (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Tienda a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idTienda - El identificador del Tienda
	 * @param nombre - El nombre del Tienda
	 * @param ciudad - La ciudad del Tienda
	 * @param presupuesto - El presupuesto del Tienda (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del Tienda
	 * @return El número de tuplas insertadas
	 */
	public long adicionarTienda (PersistenceManager pm, long id, String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, List<Producto> productos) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTienda () + "(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, productos) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, productos);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Tienda de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idTienda - El identificador del Tienda
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarTiendaPorId (PersistenceManager pm, long idTienda)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTienda () + " WHERE id = ?");
        q.setParameters(idTienda);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Tienda de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idTienda - El identificador del Tienda
	 * @return El objeto Tienda que tiene el identificador dado
	 */
	public VOTienda darTiendaPorId (PersistenceManager pm, long idTienda) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTienda () + " WHERE id = ?");
		q.setResultClass(Tienda.class);
		q.setParameters(idTienda);
		return (VOTienda) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS TiendaES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param numeroTienda - El nombre de Tienda buscado
	 * @return Una lista de objetos Tienda que tienen el nombre dado
	 */
	public List<Tienda> darTiendasPorNombre (PersistenceManager pm, String nombreTienda) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTienda () + " WHERE numero = ?");
		q.setResultClass(Tienda.class);
		q.setParameters(nombreTienda);
		return (List<Tienda>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Tiendas de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Tienda
	 */
	public List<Tienda> darTiendas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTienda ());
		q.setResultClass(Tienda.class);
		return (List<Tienda>) q.executeList();
	}


}
