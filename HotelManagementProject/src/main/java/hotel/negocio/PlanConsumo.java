package hotel.negocio;
import java.util.Date;


public class PlanConsumo implements VOPlanConsumo
{
	private String tipo;
	private int costo;
	private Date fecha;

	public PlanConsumo(){
		super();
		this.tipo = "";
		this.costo = 0;
		this.fecha = null;
		
	}

	public PlanConsumo(String tipo, int costo, Date fecha) {
		super();
		this.tipo = tipo;
		this.costo = costo;
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "PlanConsumo [tipo=" + tipo + ", costo=" + costo + ", fecha=" + fecha + "]";
	}
}

