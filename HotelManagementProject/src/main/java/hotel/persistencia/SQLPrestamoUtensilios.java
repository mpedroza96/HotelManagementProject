package hotel.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import hotel.negocio.PlanConsumo;
import hotel.negocio.PrestamoUtensilios;
import hotel.negocio.VOPrestamoUtensilios;

public class SQLPrestamoUtensilios {
	
	
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
	public SQLPrestamoUtensilios (PersistenciaHotel pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PrestamoUtensilios a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idPrestamoUtensilios - El identificador del PrestamoUtensilios
	 * @param nombre - El nombre del PrestamoUtensilios
	 * @param ciudad - La ciudad del PrestamoUtensilios
	 * @param presupuesto - El presupuesto del PrestamoUtensilios (ALTO, MEDIO, BAJO)
	 * @param sedes - El número de sedes del PrestamoUtensilios
	 * @return El número de tuplas insertadas
	 */
	public long adicionarPrestamoUtensilios (PersistenceManager pm, long id, String nombre, char ofrece, Date reserva, int capacidad, PlanConsumo incluyePlanConsumo,
			int costo, char cargaConsumo, char costoIncluido, Date horario, Date inicioMantenimiento, Date finMantenimiento, char estado, String tipo, char retorno) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPrestamoUtensilios () + "(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, estado, tipo, retorno) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, ofrece, reserva, capacidad, incluyePlanConsumo, costo, cargaConsumo, costoIncluido, horario, inicioMantenimiento, finMantenimiento, estado, tipo, retorno);
        return (long) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN PrestamoUtensilios de la base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPrestamoUtensilios - El identificador del PrestamoUtensilios
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarPrestamoUtensiliosPorId (PersistenceManager pm, long idPrestamoUtensilios)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPrestamoUtensilios () + " WHERE id = ?");
        q.setParameters(idPrestamoUtensilios);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN PrestamoUtensilios de la 
	 * base de datos de Parranderos, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idPrestamoUtensilios - El identificador del PrestamoUtensilios
	 * @return El objeto PrestamoUtensilios que tiene el identificador dado
	 */
	public VOPrestamoUtensilios darPrestamoUtensiliosPorId (PersistenceManager pm, long idPrestamoUtensilios) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPrestamoUtensilios () + " WHERE id = ?");
		q.setResultClass(PrestamoUtensilios.class);
		q.setParameters(idPrestamoUtensilios);
		return (VOPrestamoUtensilios) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PrestamoUtensiliosES de la 
	 * base de datos de Parranderos, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param numeroPrestamoUtensilios - El nombre de PrestamoUtensilios buscado
	 * @return Una lista de objetos PrestamoUtensilios que tienen el nombre dado
	 */
	public List<PrestamoUtensilios> darPrestamoUtensiliossPorNombre (PersistenceManager pm, String nombrePrestamoUtensilios) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPrestamoUtensilios () + " WHERE numero = ?");
		q.setResultClass(PrestamoUtensilios.class);
		q.setParameters(nombrePrestamoUtensilios);
		return (List<PrestamoUtensilios>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS PrestamoUtensilioss de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PrestamoUtensilios
	 */
	public List<PrestamoUtensilios> darPrestamoUtensilioss (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPrestamoUtensilios ());
		q.setResultClass(PrestamoUtensilios.class);
		return (List<PrestamoUtensilios>) q.executeList();
	}


}
