package hotel.negocio;

import java.util.Date;

public interface VOHabitacion {
	
	long getId();

	void setId(long id);
	
	String getTipo();

	void setTipo(String tipo);

	int getCapacidad();

	void setCapacidad(int capacidad);

	char getComedorCocina();

	void setComedorCocina(char comedorCocina);

	char getJacuzzi();

	void setJacuzzi(char jacuzzi);

	char getTelevision();

	void setTelevision(char television);

	char getMinibar();

	void setMinibar(char minibar);

	char getCafetera();

	void setCafetera(char cafetera);

	int getConsumo();

	void setConsumo(int consumo);

	char getOcupado();

	void setOcupado(char ocupado);

	Date getReserva();

	void setReserva(Date reserva);
	
	void setInicioMantenimiento(Date mantenimiento);

	Date getInicioMantenimiento();
	
	void setFinMantenimiento(Date mantenimiento);

	Date getFinMantenimiento();

	String toString();

}