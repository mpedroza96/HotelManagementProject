package hotel.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hotel.negocio.LavadoPlanchado;
import hotel.negocio.PlanConsumo;
import hotel.negocio.VOLavadoPlanchado;

public class SQLLavadoPlanchado {

	
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
	public SQLLavadoPlanchado (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un LavadoPlanchado a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idLavadoPlanchado - El identificador del LavadoPlanchado
	 * @param nombre - El nombre del LavadoPlanchado
	 * @param ciudad - La ciudad del LavadoPlanchado
	 * @param presupuesto - El presupuesto del LavadoPlanchado (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del LavadoPlanchado
	 * @return El número de tuplas insertadas
	 */
	public long adicionarLavadoPlanchado (PersistenceManager pm, long id, String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, String tipo, int numPrendas) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaLavadoPlanchado () + "(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, tipo, numPrendas) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, tipo, numPrendas);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN LavadoPlanchado de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idLavadoPlanchado - El identificador del LavadoPlanchado
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarLavadoPlanchadoPorId (PersistenceManager pm, long idLavadoPlanchado)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaLavadoPlanchado () + " WHERE id = ?");
        q.setParameters(idLavadoPlanchado);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN LavadoPlanchado de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idLavadoPlanchado - El identificador del LavadoPlanchado
	 * @return El objeto LavadoPlanchado que tiene el identificador dado
	 */
	public VOLavadoPlanchado darLavadoPlanchadoPorId (PersistenceManager pm, long idLavadoPlanchado) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLavadoPlanchado () + " WHERE id = ?");
		q.setResultClass(LavadoPlanchado.class);
		q.setParameters(idLavadoPlanchado);
		return (VOLavadoPlanchado) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS LavadoPlanchadoES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param numeroLavadoPlanchado - El nombre de LavadoPlanchado buscado
	 * @return Una lista de objetos LavadoPlanchado que tienen el nombre dado
	 */
	public List<LavadoPlanchado> darLavadoPlanchadosPorNombre (PersistenceManager pm, String nombreLavadoPlanchado) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLavadoPlanchado () + " WHERE numero = ?");
		q.setResultClass(LavadoPlanchado.class);
		q.setParameters(nombreLavadoPlanchado);
		return (List<LavadoPlanchado>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS LavadoPlanchados de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos LavadoPlanchado
	 */
	public List<LavadoPlanchado> darLavadoPlanchados (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaLavadoPlanchado ());
		q.setResultClass(LavadoPlanchado.class);
		return (List<LavadoPlanchado>) q.executeList();
	}

	
}
