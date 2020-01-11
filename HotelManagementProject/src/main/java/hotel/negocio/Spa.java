package hotel.negocio;
import java.util.List;



public class Spa extends ServicioAdicional implements VOSpa
{

	public List<Servicio> servicios;

	public Spa(){
		super();
		this.servicios = null;
	}

	public Spa(List<Servicio> servicios) {
		super();
		this.servicios = servicios;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	@Override
	public String toString() {
		return "Spa [servicios=" + servicios + ", getOfrece()=" + getOfrece() + ", getId()=" + getId()
				+ ", getNombre()=" + getNombre() + ", getReserva()=" + getReserva() + ", getCapacidad()="
				+ getCapacidad() + ", getIncluyePlanConsumo()=" + getIncluyePlanConsumo() + ", getCosto()=" + getCosto()
				+ ", getConsumo()=" + ", getCargaConsumo()=" + getCargaConsumo()
				+ ", getCostoIncluido()=" + getCostoIncluido() + ", getHorario()=" + getHorario() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
}

