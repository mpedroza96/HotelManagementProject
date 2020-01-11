package hotel.negocio;
import java.util.List;


public class Tienda extends ServicioAdicional implements VOTienda
{
	
	public List<Producto> productos;

	public Tienda(){
		super();
		this.productos = null;
	}

	public Tienda(List<Producto> productos) {
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
		return "Tienda [productos=" + productos + ", getOfrece()=" + getOfrece() + ", getId()=" + getId()
				+ ", getNombre()=" + getNombre() + ", getReserva()=" + getReserva() + ", getCapacidad()="
				+ getCapacidad() + ", getIncluyePlanConsumo()=" + getIncluyePlanConsumo() + ", getCosto()=" + getCosto()
				+ ", getConsumo()=" + ", getCargaConsumo()=" + getCargaConsumo()
				+ ", getCostoIncluido()=" + getCostoIncluido() + ", getHorario()=" + getHorario() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
}

