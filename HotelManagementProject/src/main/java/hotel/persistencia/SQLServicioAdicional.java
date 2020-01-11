package hotel.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hotel.negocio.PlanConsumo;
import hotel.negocio.ServicioAdicional;
import hotel.negocio.VOServicioAdicional;

public class SQLServicioAdicional {

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
	public SQLServicioAdicional (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un ServicioAdicional a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idServicioAdicional - El identificador del ServicioAdicional
	 * @param nombre - El nombre del ServicioAdicional
	 * @param ciudad - La ciudad del ServicioAdicional
	 * @param presupuesto - El presupuesto del ServicioAdicional (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del ServicioAdicional
	 * @return El número de tuplas insertadas
	 */
	public long adicionarServicioAdicional (PersistenceManager pm, long id, String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicioAdicional () + "(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo,	costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo,	costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN ServicioAdicional de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idServicioAdicional - El identificador del ServicioAdicional
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarServicioAdicionalPorId (PersistenceManager pm, long idServicioAdicional)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicioAdicional () + " WHERE id = ?");
        q.setParameters(idServicioAdicional);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN ServicioAdicional de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idServicioAdicional - El identificador del ServicioAdicional
	 * @return El objeto ServicioAdicional que tiene el identificador dado
	 */
	public VOServicioAdicional darServicioAdicionalPorId (PersistenceManager pm, long idServicioAdicional) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioAdicional () + " WHERE id = ?");
		q.setResultClass(ServicioAdicional.class);
		q.setParameters(idServicioAdicional);
		return (VOServicioAdicional) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS ServicioAdicionalES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param numeroServicioAdicional - El nombre de ServicioAdicional buscado
	 * @return Una lista de objetos ServicioAdicional que tienen el nombre dado
	 */
	public List<ServicioAdicional> darServicioAdicionalsPorNombre (PersistenceManager pm, String nombreServicioAdicional) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioAdicional () + " WHERE numero = ?");
		q.setResultClass(ServicioAdicional.class);
		q.setParameters(nombreServicioAdicional);
		return (List<ServicioAdicional>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS ServicioAdicionals de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos ServicioAdicional
	 */
	public List<ServicioAdicional> darServicioAdicionales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioAdicional ());
		q.setResultClass(ServicioAdicional.class);
		return (List<ServicioAdicional>) q.executeList();
	}

	public int costoConvencion(PersistenceManager pm)
	{
		int costo=0;
		List<ServicioAdicional> lista = darServicioAdicionales(pm);
		for(ServicioAdicional ser:lista)
		{
			if(ser.getId()== 100)
			{
				costo += ser.getCosto();
			}
		}
		return costo;
	}
}
