package lib;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.logistica.gui.frmPrincipal;

public class MostraHora extends Thread {

	@Override
	public void run() {
		
		do {
			
			int hora,minuto,segundo;
			
			Calendar calendar=GregorianCalendar.getInstance();
			Date date=Calendar.getInstance().getTime();
			
			SimpleDateFormat sdf= new SimpleDateFormat("E dd/MM/yyyy");
			SimpleDateFormat sdf2= new SimpleDateFormat("hh:mm:ss a");
			/*
			hora=calendar.get(Calendar.HOUR_OF_DAY);
			minuto=calendar.get(Calendar.MINUTE);
			segundo=calendar.get(Calendar.SECOND);
			*/
			frmPrincipal.lblHora.setText(sdf2.format(date));
			frmPrincipal.lblFecha2.setText(sdf.format(date));
			
		} while (true);
		
		
	}

}
