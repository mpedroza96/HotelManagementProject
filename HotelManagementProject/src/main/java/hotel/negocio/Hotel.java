package hotel.negocio;
import java.util.List;

public class Hotel implements VOHotel
{

	private String nombre;
	private int calificacion;
	public List<Habitacion> habitaciones;
	public List<ServicioAdicional> servicios;
	public List<Cliente> clientes;
	public List<PlanConsumo> planesConsumo;

	
	public Hotel(){
		super();
		this.nombre = null;
		this.calificacion = 0;
		this.habitaciones = null;
		this.servicios = null;
		this.clientes = null;
		this.planesConsumo = null;
	}


	public Hotel(String nombre, int calificacion, List<Habitacion> habitaciones, List<ServicioAdicional> servicios,
			List<Cliente> clientes, List<PlanConsumo> planesConsumo) {
		super();
		this.nombre = nombre;
		this.calificacion = calificacion;
		this.habitaciones = habitaciones;
		this.servicios = servicios;
		this.clientes = clientes;
		this.planesConsumo = planesConsumo;
	}
	

	public Hotel(String nombre, int calificacion) {
		super();
		this.nombre = nombre;
		this.calificacion = calificacion;
		this.habitaciones = null;
		this.servicios = null;
		this.clientes = null;
		this.planesConsumo = null;
	

	}
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public int getCalificacion() {
		return calificacion;
	}


	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}


	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}


	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}


	public List<ServicioAdicional> getServicios() {
		return servicios;
	}


	public void setServicios(List<ServicioAdicional> servicios) {
		this.servicios = servicios;
	}


	public List<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}


	public List<PlanConsumo> getPlanesConsumo() {
		return planesConsumo;
	}


	public void setPlanesConsumo(List<PlanConsumo> planesConsumo) {
		this.planesConsumo = planesConsumo;
	}


	@Override
	public String toString() {
		return "Hotel [nombre=" + nombre + ", calificacion=" + calificacion + ", habitaciones=" + habitaciones
				+ ", servicios=" + servicios + ", clientes=" + clientes + ", planesConsumo=" + planesConsumo + "]";
	}
}

