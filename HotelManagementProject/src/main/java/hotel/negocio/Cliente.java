package hotel.negocio;
import java.util.Date;


public class Cliente implements VOCliente
{

	private long id;
	private String nombre;
	private Date fechaEntrada;
	private Date fechaSalida;
	private String pago;
	public VOPlanConsumo planDeConsumo;

	
	public Cliente(){
		super();
		this.id = 0;
		this.nombre = "";
		this.fechaEntrada = null;
		this.fechaSalida = null;
		this.pago = "";
		this.planDeConsumo = null;
	}

	

	public Cliente(long id, String nombre, Date fechaEntrada, Date fechaSalida, String pago, VOPlanConsumo planDeConsumo) {
		super();
		this.nombre = nombre;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.pago = pago;
		this.planDeConsumo = planDeConsumo;
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Date getFechaEntrada() {
		return fechaEntrada;
	}


	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}


	public Date getFechaSalida() {
		return fechaSalida;
	}


	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}


	public String getPago() {
		return pago;
	}


	public void setPago(String pago) {
		this.pago = pago;
	}


	public VOPlanConsumo getPlanDeConsumo() {
		return planDeConsumo;
	}


	public void setPlanDeConsumo(VOPlanConsumo planDeConsumo) {
		this.planDeConsumo = planDeConsumo;
	}



	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida
				+ ", pago=" + pago + ", consumo=" + ", planDeConsumo=" + planDeConsumo + "]";
	}
}

