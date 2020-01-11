package hotel.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hotel.negocio.Empleado;
import hotel.negocio.VOEmpleado;

public class SQLEmpleado {

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
	public SQLEmpleado (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Empleado a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idEmpleado - El identificador del Empleado
	 * @param nombre - El nombre del Empleado
	 * @param ciudad - La ciudad del Empleado
	 * @param presupuesto - El presupuesto del Empleado (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del Empleado
	 * @return El número de tuplas insertadas
	 */
	public long adicionarEmpleado (PersistenceManager pm, long id, String tipo, long id_hotel) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEmpleado () + "(id, tipo, id_hotel) values (?, ?, ?)");
        q.setParameters(id, tipo, id_hotel);
        return (long) q.executeUnique();
	}

	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Empleado de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEmpleado - El identificador del Empleado
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarEmpleadoPorId (PersistenceManager pm, long idEmpleado)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpleado () + " WHERE id = ?");
        q.setParameters(idEmpleado);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Empleado de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idEmpleado - El identificador del Empleado
	 * @return El objeto Empleado que tiene el identificador dado
	 */
	public VOEmpleado darEmpleadoPorId (PersistenceManager pm, long idEmpleado) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpleado () + " WHERE id = ?");
		q.setResultClass(Empleado.class);
		q.setParameters(idEmpleado);
		return (VOEmpleado) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS EmpleadoES de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Empleado
	 */
	public List<Empleado> darEmpleadoes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpleado ());
		q.setResultClass(Empleado.class);
		return (List<Empleado>) q.executeList();
	}

}
