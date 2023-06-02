package practicas.practicaGUI.gui_simple;

import javax.swing.*;

public class Principal {
	
	public static void crearGui(JFrame ventana) {
		Panel panel = new Panel();
		Controlador ctr = new Controlador(panel);
		panel.controlador(ctr);
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame ventana = new JFrame("Gui simple");
		
		crearGui(ventana);

	}

}
