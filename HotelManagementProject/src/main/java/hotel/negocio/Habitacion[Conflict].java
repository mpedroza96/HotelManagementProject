package hotel.negocio;

import java.util.Date;

public class Habitacion implements VOHabitacion 
{
	private long id;
	private String tipo;
	private int capacidad;
	private char comedorCocina;
	private char jacuzzi;
	private char television;
	private char minibar;
	private char cafetera;
	private int consumo;
	private char ocupado;
	private long idReservado;
	private Date reserva;
	private Date inicioMantenimiento;
	private Date finMantenimiento;
	private long idHotel;
	
	public Habitacion(){
		this.id = 0;
		this.cafetera = 0;
		this.capacidad = 0;
		this.comedorCocina = 0; 
		this.consumo = 0;
		this.jacuzzi = 0;
		this.minibar = 0;
		this.television = 0;
		this.tipo = "";
		this.ocupado = 0;
		this.idReservado = 0;
		this.reserva = null;
		this.inicioMantenimiento = null;
		this.finMantenimiento = null;
		this.idHotel = 0;
	}

	public Habitacion(long id, String tipo, int capacidad, char comedorCocina, char jacuzzi, char minibar,
			char television, char cafetera, int consumo, long idHotel, Date reserva, Date inicioMantenimiento, Date finMantenimiento, char ocupado, long idReservado) {
		this.id = id;
		this.tipo = tipo;
		this.capacidad = capacidad;
		this.comedorCocina = comedorCocina;
		this.jacuzzi = jacuzzi;
		this.minibar = minibar;
		this.television = television;
		this.cafetera = cafetera;
		this.consumo = consumo;
		this.ocupado = ocupado;
		this.reserva = reserva;
		this.inicioMantenimiento = inicioMantenimiento;
		this.finMantenimiento = finMantenimiento;
		this.idReservado = idReservado;
		this.idHotel = idHotel;
	}

	public long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(long idHotel) {
		this.idHotel = idHotel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public char getComedorCocina() {
		return comedorCocina;
	}

	public void setComedorCocina(char comedorCocina) {
		this.comedorCocina = comedorCocina;
	}

	public char getJacuzzi() {
		return jacuzzi;
	}

	public void setJacuzzi(char jacuzzi) {
		this.jacuzzi = jacuzzi;
	}

	public char getTelevision() {
		return television;
	}

	public void setTelevision(char television) {
		this.television = television;
	}

	public char getMinibar() {
		return minibar;
	}

	public void setMinibar(char minibar) {
		this.minibar = minibar;
	}

	public char getCafetera() {
		return cafetera;
	}

	public void setCafetera(char cafetera) {
		this.cafetera = cafetera;
	}

	public int getConsumo() {
		return consumo;
	}

	public void setConsumo(int consumo) {
		this.consumo = consumo;
	}

	public char getOcupado() {
		return ocupado;
	}

	public void setOcupado(char ocupado) {
		this.ocupado = ocupado;
	}
	
	public long getIdReservado() {
		return ocupado;
	}

	public void setIdReservado(long idReservado) {
		this.idReservado = idReservado;
	}

	public Date getReserva() {
		return reserva;
	}

	public void setReserva(Date reserva) {
		this.reserva = reserva;
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
		return "Habitacion [id=" + id + ", tipo=" + tipo + ", capacidad=" + capacidad + ", comedorCocina="
				+ comedorCocina + ", jacuzzi=" + jacuzzi + ", television=" + television + ", minibar=" + minibar
				+ ", cafetera=" + cafetera + ", consumo=" + consumo + ", ocupado=" + ocupado + ", idReservado=" + idReservado + 
				", reserva=" + reserva + ", inicioMantenimiento=" + inicioMantenimiento + ", finMantenimiento=" + finMantenimiento + "]";
	}
}

