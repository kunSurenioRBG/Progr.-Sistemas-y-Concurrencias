package practicas.practiaGUI.gui_simple;

import java.awt.event.*;

public class Controlador implements ActionListener{

	private Panel panel;
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
		}
			
		
	}

}
