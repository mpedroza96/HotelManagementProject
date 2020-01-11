package hotel.negocio;

public class LavadoPlanchado extends ServicioAdicional implements VOLavadoPlanchado
{

	private String tipo;
	private int numPrendas;
	

	public LavadoPlanchado(){
		super();
		this.tipo = "";
		this.numPrendas = 0;
	}

	public LavadoPlanchado(String tipo, int numPrendas) {
		super();
		this.tipo = tipo;
		this.numPrendas = numPrendas;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public int getNumPrendas() {
		return numPrendas;
	}


	public void setNumPrendas(int numPrendas) {
		this.numPrendas = numPrendas;
	}


	@Override
	public String toString() {
		return "LavadoPlanchado [tipo=" + tipo + ", numPrendas=" + numPrendas + ", getOfrece()=" + getOfrece()
				+ ", getId()=" + getId() + ", getNombre()=" + getNombre() + ", getReserva()=" + getReserva()
				+ ", getCapacidad()=" + getCapacidad() + ", getIncluyePlanConsumo()=" + getIncluyePlanConsumo()
				+ ", getCosto()=" + getCosto() + ", getConsumo()=" + ", getCargaConsumo()="
				+ getCargaConsumo() + ", getCostoIncluido()=" + getCostoIncluido() + ", getHorario()=" + getHorario()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
	
}

