package hotel.negocio;


public class Servicio implements VOServicio
{
	private int duracion;
	private int costo;
	private String tipo;
	private long id;
	private String nombre;
	private char ofrece;

	public Servicio(){
		super();
		this.duracion = 0;
		this.costo = 0;
		this.tipo = "";
		this.id = 0;
		this.nombre = "";
		this.ofrece = 0;
	}

	public Servicio(long id, String nombre, int duracion, int costo, String tipo, char ofrece) {
		super();
		this.duracion = duracion;
		this.costo = costo;
		this.tipo = tipo;
		this.id = id;
		this.nombre = nombre;
		this.ofrece = ofrece;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char getOfrece() {
		return ofrece;
	}

	public void setOfrece(char ofrece) {
		this.ofrece = ofrece;
	}

	@Override
	public String toString() {
		return "Servicio [duracion=" + duracion + ", costo=" + costo + ", tipo=" + tipo + ", id=" + id + ", nombre="
				+ nombre + ", ofrece=" + ofrece + "]";
	}

}

