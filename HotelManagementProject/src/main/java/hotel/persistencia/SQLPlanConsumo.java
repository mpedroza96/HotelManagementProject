package hotel.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hotel.negocio.PlanConsumo;

public class SQLPlanConsumo {


	
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
	public SQLPlanConsumo (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PlanConsumo a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idPlanConsumo - El identificador del PlanConsumo
	 * @param nombre - El nombre del PlanConsumo
	 * @param ciudad - La ciudad del PlanConsumo
	 * @param presupuesto - El presupuesto del PlanConsumo (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del PlanConsumo
	 * @return El número de tuplas insertadas
	 */
	public long adicionarPlanConsumo (PersistenceManager pm, long id, String nombre, String tipo, int costo, Date fecha) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPlanConsumo () + "(id, nombre, tipo, costo, fecha) values (?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, tipo, costo, fecha);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN PlanConsumo de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPlanConsumo - El identificador del PlanConsumo
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarPlanConsumoPorId (PersistenceManager pm, long idPlanConsumo)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPlanConsumo () + " WHERE id = ?");
        q.setParameters(idPlanConsumo);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN PlanConsumo de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPlanConsumo - El identificador del PlanConsumo
	 * @return El objeto PlanConsumo que tiene el identificador dado
	 */
	public PlanConsumo darPlanConsumoPorId (PersistenceManager pm, long idPlanConsumo) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPlanConsumo () + " WHERE id = ?");
		q.setResultClass(PlanConsumo.class);
		q.setParameters(idPlanConsumo);
		return (PlanConsumo) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PlanConsumoES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param numeroPlanConsumo - El nombre de PlanConsumo buscado
	 * @return Una lista de objetos PlanConsumo que tienen el nombre dado
	 */
	public List<PlanConsumo> darPlanConsumosPorNombre (PersistenceManager pm, String nombrePlanConsumo) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPlanConsumo () + " WHERE numero = ?");
		q.setResultClass(PlanConsumo.class);
		q.setParameters(nombrePlanConsumo);
		return (List<PlanConsumo>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PlanConsumos de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PlanConsumo
	 */
	public List<PlanConsumo> darPlanConsumos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPlanConsumo ());
		q.setResultClass(PlanConsumo.class);
		return (List<PlanConsumo>) q.executeList();
	}

}
