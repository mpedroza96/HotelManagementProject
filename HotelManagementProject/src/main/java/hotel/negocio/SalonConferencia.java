package hotel.negocio;

public class SalonConferencia extends ServicioAdicional implements VOSalonConferencia
{
	//Costo por hora
	private int duracion;
	private int costoAdicional;

	public SalonConferencia(){
		super();
		this.duracion = 0;
		this.costoAdicional = 0;
	}

	public SalonConferencia(int duracion, int costoAdicional) {
		super();
		this.duracion = duracion;
		this.costoAdicional = costoAdicional;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getCostoAdicional() {
		return costoAdicional;
	}

	public void setCostoAdicional(int costoAdicional) {
		this.costoAdicional = costoAdicional;
	}

	@Override
	public String toString() {
		return "SalonConferencia [duracion=" + duracion + ", costoAdicional=" + costoAdicional + ", getOfrece()="
				+ getOfrece() + ", getId()=" + getId() + ", getNombre()=" + getNombre() + ", getReserva()="
				+ getReserva() + ", getCapacidad()=" + getCapacidad() + ", getIncluyePlanConsumo()="
				+ getIncluyePlanConsumo() + ", getCosto()=" + getCosto() + ", getConsumo()=" + ", getCargaConsumo()=" + getCargaConsumo() + ", getCostoIncluido()=" + getCostoIncluido()
				+ ", getHorario()=" + getHorario() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
}

