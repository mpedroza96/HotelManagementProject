package hotel.negocio;

import java.util.Date;

public class ServicioAdicional implements VOServicioAdicional
{

	private char ofrece;
	private long id;
	private String nombre;
	private Date reserva;
	private int capacidad;
	private PlanConsumo incluyePlanConsumo;
	private int costo;
	private char cargaConsumo;
	private char costoIncluido;
	private Date horario;
	private Date inicioMantenimiento;
	private Date finMantenimiento;
	private int duracion;
	
	public ServicioAdicional() {
		super();
		this.ofrece = 0;
		this.nombre = "";
		this.id = 0;
		this.reserva = null;
		this.capacidad = 0;
		this.incluyePlanConsumo = null;
		this.costo = 0;
		this.cargaConsumo = 0;
		this.costoIncluido = 0;
		this.horario = null;
		this.inicioMantenimiento = null;
		this.finMantenimiento = null;
		this.duracion = 0;
	}

	public ServicioAdicional( long id, String nombre, char ofrece, Date reserva, int capacidad,
			PlanConsumo incluyePlanConsumo, int costo, char cargaConsumo, char costoIncluido, Date horario,
			Date inicioMantenimiento, Date finMantenimiento, int duracion) {
		super();
		this.ofrece = ofrece;
		this.id = id;
		this.nombre = nombre;
		this.reserva = reserva;
		this.capacidad = capacidad;
		this.incluyePlanConsumo = incluyePlanConsumo;
		this.costo = costo;
		this.cargaConsumo = cargaConsumo;
		this.costoIncluido = costoIncluido;
		this.horario = horario;
		this.inicioMantenimiento = inicioMantenimiento;
		this.finMantenimiento = finMantenimiento;
		this.duracion = duracion;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public char getOfrece() {
		return ofrece;
	}

	public void setOfrece(char ofrece) {
		this.ofrece = ofrece;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getReserva() {
		return reserva;
	}

	public void setReserva(Date reserva) {
		this.reserva = reserva;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public PlanConsumo getIncluyePlanConsumo() {
		return incluyePlanConsumo;
	}

	public void setIncluyePlanConsumo(PlanConsumo incluyePlanConsumo) {
		this.incluyePlanConsumo = incluyePlanConsumo;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public char getCargaConsumo() {
		return cargaConsumo;
	}

	public void setCargaConsumo(char cargaConsumo) {
		this.cargaConsumo = cargaConsumo;
	}

	public char getCostoIncluido() {
		return costoIncluido;
	}

	public void setCostoIncluido(char costoIncluido) {
		this.costoIncluido = costoIncluido;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public Date getInicioMantenimiento() {
		return inicioMantenimiento;
	}

	public void setInicioMantenimiento(Date inicioMantenimiento) {
		this.inicioMantenimiento = inicioMantenimiento;
	}

	public Date getFinMantenimiento() {
		return finMantenimiento;
	}

	public void setFinMantenimiento(Date finMantenimiento) {
		this.finMantenimiento = finMantenimiento;
	}

	@Override
	public String toString() {
		return "ServicioAdicional [ofrece=" + ofrece + ", id=" + id + ", nombre=" + nombre + ", reserva=" + reserva
				+ ", capacidad=" + capacidad + ", incluyePlanConsumo=" + incluyePlanConsumo + ", costo=" + costo
				+ ", cargaConsumo=" + cargaConsumo + ", costoIncluido=" + costoIncluido + ", horario=" + horario
				+ ", inicioMantenimiento=" + inicioMantenimiento + ", finMantenimiento=" + finMantenimiento + "]";
	}
}
