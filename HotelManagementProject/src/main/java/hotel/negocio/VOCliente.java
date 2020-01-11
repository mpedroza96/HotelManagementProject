package hotel.negocio;

import java.util.Date;

public interface VOCliente {

	String getNombre();

	void setNombre(String nombre);

	Date getFechaEntrada();

	void setFechaEntrada(Date fechaEntrada);

	Date getFechaSalida();

	void setFechaSalida(Date fechaSalida);

	String getPago();

	void setPago(String pago);

	VOPlanConsumo getPlanDeConsumo();

	void setPlanDeConsumo(VOPlanConsumo planDeConsumo);

	String toString();

}