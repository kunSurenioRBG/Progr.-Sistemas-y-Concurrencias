package practicas.practicaGUI.gui_simple;

import java.awt.event.*;

import javax.swing.*;

public class Panel extends JPanel{
	private JLabel verdad = new JLabel("Verdad?");
	private JButton si = new JButton("sí");
	private JButton no = new JButton("no");
	
	public Panel() {
		this.add(verdad); this.add(si); this.add(no);
	}
	
	public void controlador(ActionListener ctr) {
		si.addActionListener(ctr);
		si.setActionCommand("SI");
		no.addActionListener(ctr);
		no.setActionCommand("NO");
	}
	
	public void mensaje(String str) {
		verdad.setText(str);
	}

}
