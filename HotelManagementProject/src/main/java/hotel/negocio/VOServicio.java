package hotel.negocio;

public interface VOServicio {

	int getDuracion();

	void setDuracion(int duracion);

	int getCosto();

	void setCosto(int costo);

	String getTipo();

	void setTipo(String tipo);

	long getId();

	void setId(long id);

	String getNombre();

	void setNombre(String nombre);

	char getOfrece();

	void setOfrece(char ofrece);

	String toString();

}