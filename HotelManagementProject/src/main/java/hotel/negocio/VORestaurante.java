package hotel.negocio;

import java.util.List;

public interface VORestaurante {

	String getEstilo();

	void setEstilo(String estilo);

	List<Producto> getProductos();

	void setProductos(List<Producto> productos);

	String toString();

}