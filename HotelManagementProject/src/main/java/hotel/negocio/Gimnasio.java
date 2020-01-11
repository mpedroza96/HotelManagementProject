package hotel.negocio;

public class Gimnasio extends ServicioAdicional implements VOGimnasio
{

	private int maquinas;

	
	public Gimnasio(){
		super();
		this.maquinas = 0;
	}
	
	public Gimnasio(int maquinas) {
		super();
		this.maquinas = maquinas;
	}

	public int getMaquinas() {
		return maquinas;
	}


	public void setMaquinas(int maquinas) {
		this.maquinas = maquinas;
	}


	@Override
	public String toString() {
		return "Gimnasio [maquinas=" + maquinas + ", getOfrece()=" + getOfrece() + ", getId()=" + getId()
				+ ", getNombre()=" + getNombre() + ", getReserva()=" + getReserva() + ", getCapacidad()="
				+ getCapacidad() + ", getIncluyePlanConsumo()=" + getIncluyePlanConsumo() + ", getCosto()=" + getCosto()
				+ ", getConsumo()=" + ", getCargaConsumo()=" + getCargaConsumo()
				+ ", getCostoIncluido()=" + getCostoIncluido() + ", getHorario()=" + getHorario() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
}

