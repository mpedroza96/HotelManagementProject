package hotel.negocio;
import java.util.ArrayList;
import java.util.List;


public class Bar extends ServicioAdicional implements VOBar
{
	
	private String estilo;
	public List<Producto> productos;


	public Bar(){
		super();
		this.estilo = "";
		this.productos = new ArrayList<Producto>();
	}
	
	
	public Bar(String estilo, List<Producto> productos) {
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
		return "Bar [estilo=" + estilo + ", productos=" + productos + ", getOfrece()=" + getOfrece() + ", getId()="
				+ getId() + ", getNombre()=" + getNombre() + ", getReserva()=" + getReserva() + ", getCapacidad()="
				+ getCapacidad() + ", getIncluyePlanConsumo()=" + getIncluyePlanConsumo() + ", getCosto()=" + getCosto()
				+ ", getConsumo()=" + ", getCargaConsumo()=" + getCargaConsumo()
				+ ", getCostoIncluido()=" + getCostoIncluido() + ", getHorario()=" + getHorario() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
}

