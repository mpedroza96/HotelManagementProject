package hotel.negocio;
import java.util.List;

public class Restaurante extends ServicioAdicional implements VORestaurante
{
	private String estilo;
	public List<Producto> productos;

	public Restaurante(){
		super();
		this.estilo = "";
		this.productos = null;
	}

	public Restaurante(String estilo, List<Producto> productos) {
		super();
		this.estilo = estilo;
		this.productos = productos;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "Restaurante [estilo=" + estilo + ", productos=" + productos + ", getOfrece()=" + getOfrece()
				+ ", getId()=" + getId() + ", getNombre()=" + getNombre() + ", getReserva()=" + getReserva()
				+ ", getCapacidad()=" + getCapacidad() + ", getIncluyePlanConsumo()=" + getIncluyePlanConsumo()
				+ ", getCosto()=" + getCosto() + ", getConsumo()=" + ", getCargaConsumo()="
				+ getCargaConsumo() + ", getCostoIncluido()=" + getCostoIncluido() + ", getHorario()=" + getHorario()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
}

