package hotel.negocio;

import java.sql.Date;

import com.google.gson.JsonObject;

import hotel.persistencia.PersistenciaHotel;


public class Hoteleria {

	//private static Logger log = Logger.getLogger(Hoteleria.class.getName());
	private PersistenciaHotel pp;
	
	public Hoteleria()
	{
		pp = PersistenciaHotel.getInstance();
	}
	
	public Hoteleria (JsonObject tableConfig)
	{
		pp=PersistenciaHotel.getInstance(tableConfig);
	}
	
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}

	public Hotel adicionarHotel (String nombre, int calificacion)
	{
       // log.info ("Adicionando Hotel: " + nombre);
        Hotel tipoBebida = pp.adicionarHotel(nombre, calificacion);		
       // log.info ("Adicionando Hotel: " + tipoBebida);
        return tipoBebida;
	}
	
	public void reservarHabitacion(long idCliente, long idHabitacion)
	{
		pp.reservarHabitacion(idCliente, idHabitacion);
	}

	public void reservarServicio(long id) {
		// TODO Auto-generated method stub
		pp.reservarServicio(id);
	}

	public void checkIn(long idE, long idC) {
		// TODO Auto-generated method stub
		pp.checkIn(idE, idC);
	}

	public void registrarConsumo(long idP, long idC) {
		// TODO Auto-generated method stub
		pp.registrarConsumo(idP, idC);
	}

	public void checkOut(long idC) {
		// TODO Auto-generated method stub
		pp.checkOut(idC);
	}

	public void dineroHabitaciones() {
		// TODO Auto-generated method stub
		pp.dineroHabitaciones();
	}

	public void reservas(int suites, int dobles, int indivi, int salonC, int salonR, int bar, int restaurante) {
		// TODO Auto-generated method stub
		pp.reservas(suites,dobles,indivi,salonC,salonR,bar,restaurante);
	}

	public void inicioMantenimiento(Date ini, Date fin) {
		// TODO Auto-generated method stub
		pp.inicioMantenimiento(ini, fin);
	}

	public void cancelarReservas() {
		// TODO Auto-generated method stub
		pp.cancelarReservas();
	}

	public void finConvencion() {
		// TODO Auto-generated method stub
		pp.finConvencion();
	}

	public void finMantenimiento() {
		// TODO Auto-generated method stub
		pp.finMantenimiento();
	}
}
