package hotel.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hotel.negocio.Piscina;
import hotel.negocio.PlanConsumo;
import hotel.negocio.VOPiscina;

public class SQLPiscina {

	
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
	public SQLPiscina (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Piscina a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idPiscina - El identificador del Piscina
	 * @param nombre - El nombre del Piscina
	 * @param ciudad - La ciudad del Piscina
	 * @param presupuesto - El presupuesto del Piscina (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del Piscina
	 * @return El número de tuplas insertadas
	 */
	public long adicionarPiscina (PersistenceManager pm, long id, String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, int profundidad) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPiscina () + "(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, profundidad) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, profundidad);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Piscina de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPiscina - El identificador del Piscina
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarPiscinaPorId (PersistenceManager pm, long idPiscina)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPiscina () + " WHERE id = ?");
        q.setParameters(idPiscina);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Piscina de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPiscina - El identificador del Piscina
	 * @return El objeto Piscina que tiene el identificador dado
	 */
	public VOPiscina darPiscinaPorId (PersistenceManager pm, long idPiscina) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPiscina () + " WHERE id = ?");
		q.setResultClass(Piscina.class);
		q.setParameters(idPiscina);
		return (VOPiscina) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PiscinaES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param numeroPiscina - El nombre de Piscina buscado
	 * @return Una lista de objetos Piscina que tienen el nombre dado
	 */
	public List<Piscina> darPiscinasPorNombre (PersistenceManager pm, String nombrePiscina) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPiscina () + " WHERE numero = ?");
		q.setResultClass(Piscina.class);
		q.setParameters(nombrePiscina);
		return (List<Piscina>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Piscinas de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Piscina
	 */
	public List<Piscina> darPiscinas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPiscina ());
		q.setResultClass(Piscina.class);
		return (List<Piscina>) q.executeList();
	}

}
