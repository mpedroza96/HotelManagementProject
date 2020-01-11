package hotel.negocio;

import java.util.List;

public interface VOTienda {

	List<Producto> getProductos();

	void setProductos(List<Producto> productos);

	String toString();

}