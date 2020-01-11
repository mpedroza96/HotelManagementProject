package hotel.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hotel.negocio.PlanConsumo;
import hotel.negocio.SalonConferencia;
import hotel.negocio.VOSalonConferencia;

public class SQLSalonConferencia {
	
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
	public SQLSalonConferencia (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un SalonConferencia a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idSalonConferencia - El identificador del SalonConferencia
	 * @param nombre - El nombre del SalonConferencia
	 * @param ciudad - La ciudad del SalonConferencia
	 * @param presupuesto - El presupuesto del SalonConferencia (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del SalonConferencia
	 * @return El número de tuplas insertadas
	 */
	public long adicionarSalonConferencia (PersistenceManager pm, long id, String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, int duracion, int costoAdicional) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSalonConferencia () + "(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, duracion, costoAdicional) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, duracion, costoAdicional);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN SalonConferencia de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idSalonConferencia - El identificador del SalonConferencia
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarSalonConferenciaPorId (PersistenceManager pm, long idSalonConferencia)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSalonConferencia () + " WHERE id = ?");
        q.setParameters(idSalonConferencia);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN SalonConferencia de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idSalonConferencia - El identificador del SalonConferencia
	 * @return El objeto SalonConferencia que tiene el identificador dado
	 */
	public VOSalonConferencia darSalonConferenciaPorId (PersistenceManager pm, long idSalonConferencia) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSalonConferencia () + " WHERE id = ?");
		q.setResultClass(SalonConferencia.class);
		q.setParameters(idSalonConferencia);
		return (VOSalonConferencia) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS SalonConferenciaES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param numeroSalonConferencia - El nombre de SalonConferencia buscado
	 * @return Una lista de objetos SalonConferencia que tienen el nombre dado
	 */
	public List<SalonConferencia> darSalonConferenciasPorNombre (PersistenceManager pm, String nombreSalonConferencia) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSalonConferencia () + " WHERE numero = ?");
		q.setResultClass(SalonConferencia.class);
		q.setParameters(nombreSalonConferencia);
		return (List<SalonConferencia>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS SalonConferencias de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos SalonConferencia
	 */
	public List<SalonConferencia> darSalonConferencias (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSalonConferencia ());
		q.setResultClass(SalonConferencia.class);
		return (List<SalonConferencia>) q.executeList();
	}


}
