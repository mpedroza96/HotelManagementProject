package hotel.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hotel.negocio.PlanConsumo;
import hotel.negocio.SalonReunion;
import hotel.negocio.VOSalonReunion;

public class SQLSalonReunion {
	
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
	public SQLSalonReunion (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un SalonReunion a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idSalonReunion - El identificador del SalonReunion
	 * @param nombre - El nombre del SalonReunion
	 * @param ciudad - La ciudad del SalonReunion
	 * @param presupuesto - El presupuesto del SalonReunion (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del SalonReunion
	 * @return El número de tuplas insertadas
	 */
	public long adicionarSalonReunion (PersistenceManager pm, long id, String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, int duracion, int costoAdicional) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSalonReunion () + "(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, duracion, costoAdicional) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, duracion, costoAdicional);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN SalonReunion de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idSalonReunion - El identificador del SalonReunion
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarSalonReunionPorId (PersistenceManager pm, long idSalonReunion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSalonReunion () + " WHERE id = ?");
        q.setParameters(idSalonReunion);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN SalonReunion de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idSalonReunion - El identificador del SalonReunion
	 * @return El objeto SalonReunion que tiene el identificador dado
	 */
	public VOSalonReunion darSalonReunionPorId (PersistenceManager pm, long idSalonReunion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSalonReunion () + " WHERE id = ?");
		q.setResultClass(SalonReunion.class);
		q.setParameters(idSalonReunion);
		return (VOSalonReunion) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS SalonReunionES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param numeroSalonReunion - El nombre de SalonReunion buscado
	 * @return Una lista de objetos SalonReunion que tienen el nombre dado
	 */
	public List<SalonReunion> darSalonReunionsPorNombre (PersistenceManager pm, String nombreSalonReunion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSalonReunion () + " WHERE numero = ?");
		q.setResultClass(SalonReunion.class);
		q.setParameters(nombreSalonReunion);
		return (List<SalonReunion>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS SalonReunions de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos SalonReunion
	 */
	public List<SalonReunion> darSalonReunions (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSalonReunion ());
		q.setResultClass(SalonReunion.class);
		return (List<SalonReunion>) q.executeList();
	}

	public void reservarSalon(PersistenceManager pm, long id) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaSalonReunion()+ " SET RESERVA = 'N', ID_CLIENTE = 2 WHERE ID = " + id );
		q.executeUnique();
	}


}
