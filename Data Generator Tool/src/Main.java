import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Main {

	private static String [] nombreCliente;
	private static String [] nombreServicio;
	private static String [] tipoPago;
	private static int dia;
	private static int mes;
	private static int ano;
	private static String pago;
	private static char ofrece;
	private static int costo;
	private static Date horario;
	private static int duracion;
	private static Integer reserva;

	public static void ini(){
		nombreCliente = new String[20];
		nombreServicio = new String[33];
		tipoPago = new String[2];
		nombreCliente[0] = "'Mateo'";
		nombreCliente[1] = "'David'";
		nombreCliente[2] = "'Juan'";
		nombreCliente[3] = "'Jose'";
		nombreCliente[4] = "'Ana'";
		nombreCliente[5] = "'Juliana'";
		nombreCliente[6] = "'Mariana'";
		nombreCliente[7] = "'Diego'";
		nombreCliente[8] = "'Arturo'";
		nombreCliente[9] = "'Esperanza'";
		nombreCliente[10] = "'Magdalena'";
		nombreCliente[11] = "'Santiago'";
		nombreCliente[12] = "'Felipe'";
		nombreCliente[13] = "'Maria'";
		nombreCliente[14] = "'Jorge'";
		nombreCliente[15] = "'Miranda'";
		nombreCliente[16] = "'Ronaldo'";
		nombreCliente[17] = "'Lionel'";
		nombreCliente[18] = "'Karen'";
		nombreCliente[19] = "'Jesus'";
		nombreServicio[0] = "'Stark'";
		nombreServicio[1] = "'SmartFit'";
		nombreServicio[2] = "'BodyTech'";
		nombreServicio[3] = "'Lio'";
		nombreServicio[4] = "'Teatron'";
		nombreServicio[5] = "'Baum'";
		nombreServicio[6] = "'Internet 3G'";
		nombreServicio[7] = "'Internet 4G'";
		nombreServicio[8] = "'Lavado y Planchado'";
		nombreServicio[9] = "'Piscina 1'";
		nombreServicio[10] = "'Piscina 2'";
		nombreServicio[11] = "'Piscina 3'";
		nombreServicio[12] = "'Piscina 4'";
		nombreServicio[13] = "'Prestamo Utencilios'";
		nombreServicio[14] = "'McDonalds'";
		nombreServicio[15] = "'Wok'";
		nombreServicio[16] = "'Subway'";
		nombreServicio[17] = "'La Central'";
		nombreServicio[18] = "'Salon Conferencia 1'";
		nombreServicio[19] = "'Salon Conferencia 2'";
		nombreServicio[20] = "'Salon Reunion 1'";
		nombreServicio[21] = "'Salon Reunion 2'";
		nombreServicio[22] = "'Salon Reunion 3'";
		nombreServicio[23] = "'Salon Reunion 4'";
		nombreServicio[24] = "'Salon Reunion 5'";
		nombreServicio[25] = "'SPA 1'";
		nombreServicio[26] = "'SPA 2'";
		nombreServicio[27] = "'SPA 3'";
		nombreServicio[28] = "'Carulla'";
		nombreServicio[29] = "'Exito'";
		nombreServicio[30] = "'Tienda de Accesorios'";
		nombreServicio[31] = "'Tienda de Comestibles'";
		nombreServicio[32] = "'Tienda de Souvenir'";
		tipoPago[0] = "'Efectivo'";
		tipoPago[1] = "'Tarjeta'";
	}

	public static void Cliente() {
		try {
			FileWriter c = new FileWriter("./clientes.sql");
			PrintWriter p = new PrintWriter(c);
			for(int i = 0; i<300000; i++) {
				dia=((int)(Math.random()*23)+1);
				mes =((int)(Math.random()*12)+1);
				ano= ((int)(Math.random()*3)+2016);
				p.println("INSERT INTO CLIENTE values("+
						(i+800001) + ", " +
						nombreCliente[(int)(Math.random()*20)]+ ", '" +
						dia + "/" +
						mes + "/" +
						ano + "', '"+		
						(dia+((int)(Math.random()*5))+1) + "/" +
						mes + "/" +
						ano + "', " +
						tipoPago[(int)(Math.random()*2)] +
						" , 1, "+((int)(Math.random()*300)+1) + "," +((int)(Math.random()*500)+1) + ");"
						);	
			}
			p.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void ServicioAdicional() {
		try {
			FileWriter c = new FileWriter("./servicioAdicional.sql");
			PrintWriter p = new PrintWriter(c);
			
		for(int i = 0; i<200000; i++) {
			dia=((int)(Math.random()*28)+1);
			mes =((int)(Math.random()*12)+1);
			ano= ((int)(Math.random()*3)+2016);
			if(Math.random()<0.8) {
				reserva = null;
				costo = ((int)(Math.random()*90000)+10000);
			}
			else {
				reserva = ((int)(Math.random()*10)+1);
				costo = ((int)(Math.random()*300000)+200000);
			}
			p.println("INSERT INTO SERVICIOADICIONAL values("+
					(i+1) + ", " +
					nombreServicio[(int)(Math.random()*33)]+ ", 1, '" + 
					dia + "/" +
					mes + "/" +
					ano + "', "+ ((int)(Math.random()*1000000)+1) +	", " + costo + ", " + reserva + ", " + (((int)(Math.random()*5))+1) + ");"
					);	
		}
		p.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	public static void main (String[] args) {
		ini();
		Cliente();
		//ServicioAdicional();
	}

}
