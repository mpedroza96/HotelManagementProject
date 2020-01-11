package hotel.negocio;
import java.util.List;


public class Supermercado extends ServicioAdicional implements VOSupermercado
{

	public List<Producto> productos;

	public Supermercado(){
		super();
		this.productos = null;
	}

	public Supermercado(List<Producto> productos) {
		super();
		this.productos = productos;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "Supermercado [productos=" + productos + ", getOfrece()=" + getOfrece() + ", getId()=" + getId()
				+ ", getNombre()=" + getNombre() + ", getReserva()=" + getReserva() + ", getCapacidad()="
				+ getCapacidad() + ", getIncluyePlanConsumo()=" + getIncluyePlanConsumo() + ", getCosto()=" + getCosto()
				+ ", getConsumo()=" + ", getCargaConsumo()=" + getCargaConsumo()
				+ ", getCostoIncluido()=" + getCostoIncluido() + ", getHorario()=" + getHorario() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}

