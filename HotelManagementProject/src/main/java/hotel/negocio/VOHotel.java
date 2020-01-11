package hotel.negocio;

import java.util.List;

public interface VOHotel {

	String getNombre();

	void setNombre(String nombre);

	int getCalificacion();

	void setCalificacion(int calificacion);

	List<Habitacion> getHabitaciones();

	void setHabitaciones(List<Habitacion> habitaciones);

	List<ServicioAdicional> getServicios();

	void setServicios(List<ServicioAdicional> servicios);

	List<Cliente> getClientes();

	void setClientes(List<Cliente> clientes);

	List<PlanConsumo> getPlanesConsumo();

	void setPlanesConsumo(List<PlanConsumo> planesConsumo);

	String toString();

}