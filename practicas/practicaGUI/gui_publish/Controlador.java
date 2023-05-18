package practicas.practiaGUI.gui_publish;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;



public class Controlador implements ActionListener,PropertyChangeListener{

	private Panel panel;
	private Worker w=null;
	public Controlador(Panel panel) {
		this.panel = panel;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getActionCommand().equals("SI")) {
			panel.mensaje("sí pulsado");
		} else if (e.getActionCommand().equals("NO")) {
			panel.mensaje("no pulsado");
		} else if (e.getActionCommand().equals("NPRIMOS")) {
			try {
				int n = panel.nPrimos();
				panel.limpiar();
				panel.cambiaProgreso(0);
				
				w = new Worker(n,panel);
				w.addPropertyChangeListener(this);
				w.execute();
				
			//	panel.listaPrimos(this.listaPrimos(n));
				
			}catch(IllegalArgumentException exc) {
				panel.mensaje1("número inválido");
			}catch (Exception exc) {
				panel.mensaje1("tarea cancelada");
			}
		} else if (e.getActionCommand().equals("CANCELAR")) {
			if (w!=null) {
				w.cancel(true);
			}
		}
			
		
	}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getPropertyName().equals("progress")) {
			panel.cambiaProgreso((Integer)(evt.getNewValue()));
		}
		
	}


	

}
