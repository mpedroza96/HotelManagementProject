package hotel.negocio;

public class Empleado implements VOEmpleado {

	private String tipo;
	private long id;
	
	public Empleado() {
		super();
		this.tipo = "";
		this.id = 0;
	}
	public Empleado(long id, String tipo ) {
		super();
		this.tipo = tipo;
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Empleado [tipo=" + tipo + ", id=" + id + "]";
	}	
}
