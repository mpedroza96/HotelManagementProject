package hotel.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hotel.negocio.Gimnasio;
import hotel.negocio.PlanConsumo;
import hotel.negocio.VOGimnasio;

public class SQLGimnasio {
	
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
	public SQLGimnasio (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Gimnasio a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idGimnasio - El identificador del Gimnasio
	 * @param nombre - El nombre del Gimnasio
	 * @param ciudad - La ciudad del Gimnasio
	 * @param presupuesto - El presupuesto del Gimnasio (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del Gimnasio
	 * @return El número de tuplas insertadas
	 */
	public long adicionarGimnasio (PersistenceManager pm, long id, String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, int maquinas) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaGimnasio () + "(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, maquinas) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, maquinas);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Gimnasio de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idGimnasio - El identificador del Gimnasio
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarGimnasioPorId (PersistenceManager pm, long idGimnasio)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaGimnasio () + " WHERE id = ?");
        q.setParameters(idGimnasio);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Gimnasio de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idGimnasio - El identificador del Gimnasio
	 * @return El objeto Gimnasio que tiene el identificador dado
	 */
	public VOGimnasio darGimnasioPorId (PersistenceManager pm, long idGimnasio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaGimnasio () + " WHERE id = ?");
		q.setResultClass(Gimnasio.class);
		q.setParameters(idGimnasio);
		return (VOGimnasio) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS GimnasioES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreGimnasio - El nombre de Gimnasio buscado
	 * @return Una lista de objetos Gimnasio que tienen el nombre dado
	 */
	public List<Gimnasio> darGimnasiosPorNombre (PersistenceManager pm, String nombreGimnasio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaGimnasio () + " WHERE nombre = ?");
		q.setResultClass(Gimnasio.class);
		q.setParameters(nombreGimnasio);
		return (List<Gimnasio>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Gimnasios de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Gimnasio
	 */
	public List<Gimnasio> darGimnasios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaGimnasio ());
		q.setResultClass(Gimnasio.class);
		return (List<Gimnasio>) q.executeList();
	}

}
