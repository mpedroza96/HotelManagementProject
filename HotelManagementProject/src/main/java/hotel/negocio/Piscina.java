package hotel.negocio;
import java.util.Date;

public class Piscina extends ServicioAdicional implements VOPiscina
{

	private int profundidad;


	public Piscina(){
		super();
		this.profundidad = 0;
	}


	public Piscina(int profundidad) {
		super();
		this.profundidad = profundidad;
	}


	public int getProfundidad() {
		return profundidad;
	}


	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}


	@Override
	public String toString() {
		return "Piscina [profundidad=" + profundidad + ", getOfrece()=" + getOfrece() + ", getId()=" + getId()
				+ ", getNombre()=" + getNombre() + ", getReserva()=" + getReserva() + ", getCapacidad()="
				+ getCapacidad() + ", getIncluyePlanConsumo()=" + getIncluyePlanConsumo() + ", getCosto()=" + getCosto()
				+ ", getConsumo()=" + ", getCargaConsumo()=" + getCargaConsumo()
				+ ", getCostoIncluido()=" + getCostoIncluido() + ", getHorario()=" + getHorario() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}	
}

