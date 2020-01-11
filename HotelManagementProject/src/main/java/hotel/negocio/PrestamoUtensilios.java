package hotel.negocio;


public class PrestamoUtensilios extends ServicioAdicional implements VOPrestamoUtensilios
{

	private char estado;
	private String tipo;
	private char retorno;

	public PrestamoUtensilios(){
		super();
		this.estado = 0;
		this.tipo = "";
		this.retorno = 0;
	}
	

	public PrestamoUtensilios(char estado, String tipo, char retorno) {
		super();
		this.estado = estado;
		this.tipo = tipo;
		this.retorno = retorno;
	}

	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public char getRetorno() {
		return retorno;
	}

	public void setRetorno(char retorno) {
		this.retorno = retorno;
	}

	@Override
	public String toString() {
		return "PrestamoUtensilios [estado=" + estado + ", tipo=" + tipo + ", retorno=" + retorno + ", getOfrece()="
				+ getOfrece() + ", getId()=" + getId() + ", getNombre()=" + getNombre() + ", getReserva()="
				+ getReserva() + ", getCapacidad()=" + getCapacidad() + ", getIncluyePlanConsumo()="
				+ getIncluyePlanConsumo() + ", getCosto()=" + getCosto() + ", getConsumo()=" + ", getCargaConsumo()=" + getCargaConsumo() + ", getCostoIncluido()=" + getCostoIncluido()
				+ ", getHorario()=" + getHorario() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}

