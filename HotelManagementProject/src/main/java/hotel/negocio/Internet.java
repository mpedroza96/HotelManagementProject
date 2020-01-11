package hotel.negocio;

public class Internet extends ServicioAdicional implements VOInternet
{
	
	public Internet(){
		super();
	}

	@Override
	public String toString() {
		return "Internet [getOfrece()=" + getOfrece() + ", getId()=" + getId() + ", getNombre()=" + getNombre()
				+ ", getReserva()=" + getReserva() + ", getCapacidad()=" + getCapacidad() + ", getIncluyePlanConsumo()="
				+ getIncluyePlanConsumo() + ", getCosto()=" + getCosto() + ", getConsumo()=" + ", getCargaConsumo()=" + getCargaConsumo() + ", getCostoIncluido()=" + getCostoIncluido()
				+ ", getHorario()=" + getHorario() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
}

