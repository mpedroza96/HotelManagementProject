package hotel.negocio;

import java.util.Date;

public interface VOPlanConsumo {

	String getTipo();

	void setTipo(String tipo);

	int getCosto();

	void setCosto(int costo);

	Date getFecha();

	void setFecha(Date fecha);

	String toString();

}