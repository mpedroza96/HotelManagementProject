package hotel.negocio;


public class Producto implements VOProducto
{
	private int costo;
	private String nombre;

	public Producto(){
		super();
		this.nombre = "";
		this.costo = 0;
	}

	
	public Producto(int costo, String nombre) {
		super();
		this.costo = costo;
		this.nombre = nombre;
	}


	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Producto [costo=" + costo + ", nombre=" + nombre + "]";
	}
}

