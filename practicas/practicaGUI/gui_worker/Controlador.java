package practicas.practicaGUI.gui_worker;

import java.awt.event.*;

public class Controlador implements ActionListener{

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
				w = new Worker(n,panel);
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

}
