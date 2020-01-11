package hotel.negocio;

import java.util.Date;

public interface VOServicioAdicional {

	char getOfrece();

	void setOfrece(char ofrece);

	long getId();

	void setId(long id);

	String getNombre();

	void setNombre(String nombre);

	Date getReserva();

	void setReserva(Date reserva);

	int getCapacidad();

	void setCapacidad(int capacidad);

	PlanConsumo getIncluyePlanConsumo();

	void setIncluyePlanConsumo(PlanConsumo incluyePlanConsumo);

	int getCosto();

	void setCosto(int costo);

	char getCargaConsumo();

	void setCargaConsumo(char cargaConsumo);

	char getCostoIncluido();
	
	void setInicioMantenimiento(Date mantenimiento);

	Date getInicioMantenimiento();
	
	void setFinMantenimiento(Date mantenimiento);

	Date getFinMantenimiento();

	void setCostoIncluido(char costoIncluido);

	Date getHorario();

	void setHorario(Date horario);

	String toString();

}