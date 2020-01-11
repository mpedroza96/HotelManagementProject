package hotel.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hotel.negocio.PlanConsumo;
import hotel.negocio.Servicio;
import hotel.negocio.Spa;
import hotel.negocio.VOSpa;

public class SQLSpa {
	
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
	public SQLSpa (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Spa a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idSpa - El identificador del Spa
	 * @param nombre - El nombre del Spa
	 * @param ciudad - La ciudad del Spa
	 * @param presupuesto - El presupuesto del Spa (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del Spa
	 * @return El número de tuplas insertadas
	 */
	public long adicionarSpa (PersistenceManager pm, long id, String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, List<Servicio> servicios) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSpa () + "(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, servicios) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, servicios);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Spa de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idSpa - El identificador del Spa
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarSpaPorId (PersistenceManager pm, long idSpa)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSpa () + " WHERE id = ?");
        q.setParameters(idSpa);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Spa de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idSpa - El identificador del Spa
	 * @return El objeto Spa que tiene el identificador dado
	 */
	public VOSpa darSpaPorId (PersistenceManager pm, long idSpa) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSpa () + " WHERE id = ?");
		q.setResultClass(Spa.class);
		q.setParameters(idSpa);
		return (VOSpa) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS SpaES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param numeroSpa - El nombre de Spa buscado
	 * @return Una lista de objetos Spa que tienen el nombre dado
	 */
	public List<Spa> darSpasPorNombre (PersistenceManager pm, String nombreSpa) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSpa () + " WHERE numero = ?");
		q.setResultClass(Spa.class);
		q.setParameters(nombreSpa);
		return (List<Spa>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Spas de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Spa
	 */
	public List<Spa> darSpas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSpa ());
		q.setResultClass(Spa.class);
		return (List<Spa>) q.executeList();
	}


}
