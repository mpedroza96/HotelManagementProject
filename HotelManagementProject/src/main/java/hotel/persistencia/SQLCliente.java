package hotel.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hotel.negocio.Cliente;
import hotel.negocio.PlanConsumo;

public class SQLCliente {
	
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
	public SQLCliente (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Cliente a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idCliente - El identificador del Cliente
	 * @param nombre - El nombre del Cliente
	 * @param ciudad - La ciudad del Cliente
	 * @param presupuesto - El presupuesto del Cliente (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del Cliente
	 * @return El número de tuplas insertadas
	 */
	public long adicionarCliente (PersistenceManager pm, long id, String nombre, Date fechaEntrada, Date fechaSalida, String pago, PlanConsumo planDeConsumo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCliente () + "(id, nombre, fechaEntrada, fechaSalida, pago, consumo, planDeConsumo) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, fechaEntrada, fechaSalida, pago, planDeConsumo);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Cliente de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idCliente - El identificador del Cliente
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarClientePorId (PersistenceManager pm, long idCliente)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente () + " WHERE id = ?");
        q.setParameters(idCliente);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Cliente de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idCliente - El identificador del Cliente
	 * @return El objeto Cliente que tiene el identificador dado
	 */
	public Cliente darClientePorId (PersistenceManager pm, long idCliente) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente () + " WHERE id = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(idCliente);
		return (Cliente) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS ClienteES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombreCliente - El nombre de Cliente buscado
	 * @return Una lista de objetos Cliente que tienen el nombre dado
	 */
	public List<Cliente> darClientesPorNombre (PersistenceManager pm, String nombreCliente) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente () + " WHERE nombre = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(nombreCliente);
		return (List<Cliente>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Clientes de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Cliente
	 */
	public List<Cliente> darClientes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente ());
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}

	public void checkIn(PersistenceManager pm, long idC, long idE) {
		// TODO Auto-generated method stub
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCliente () + " SET ID_EMPLEADO = " + idE + " WHERE ID = " + idC );
		q.executeUnique();	
	}

	public void reservarHabitacion(PersistenceManager pm, long idCliente, long idHabitacion) {
		// TODO Auto-generated method stub
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCliente () + " SET ID_HABITACION ="+idHabitacion+" WHERE ID = " + idCliente );
		q.executeUnique();
	}

	public void checkOut(PersistenceManager pm, long idC) {
		// TODO Auto-generated method stub
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCliente () + " SET ID_EMPLEADO = null"  + " WHERE ID = " + idC );
		q.executeUnique();	
	}

	public void dinero(PersistenceManager pm) {
		// TODO Auto-generated method stub
		
	}

}
