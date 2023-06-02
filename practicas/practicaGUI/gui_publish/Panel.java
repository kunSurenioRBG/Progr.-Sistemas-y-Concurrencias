package practicas.practicaGUI.gui_publish;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.List;

public class Panel extends JPanel{
	private JTextArea area = new JTextArea(10,20);
	private JScrollPane scroll = new JScrollPane(area);
	private JLabel primos = new JLabel("primos?");
	private JTextField  nprimos = new JTextField(10);
	private JButton cancelar = new JButton("Cancelar");
	
	private JLabel verdad = new JLabel("Verdad?");
	private JButton si = new JButton("sí");
	private JButton no = new JButton("no");
	
	private JLabel ms = new JLabel("Gui creada");
	private JProgressBar progreso = new JProgressBar();
	public Panel() {
		progreso.setStringPainted(true);
		this.setLayout(new BorderLayout());
		JPanel panelNorte = new JPanel();
		panelNorte.add(primos);panelNorte.add(nprimos);
		panelNorte.add(cancelar);
		this.add(panelNorte, BorderLayout.NORTH);
		this.add(scroll,BorderLayout.CENTER);
		JPanel panelSur1 = new JPanel();
		
		panelSur1.add(verdad); panelSur1.add(si); panelSur1.add(no);
		JPanel panelSur2 = new JPanel();
		panelSur2.add(ms);
		JPanel panelSur = new JPanel();
		panelSur.setLayout(new GridLayout(3,1));
		panelSur.add(panelSur1);panelSur.add(panelSur2);panelSur.add(progreso);
		this.add(panelSur,BorderLayout.SOUTH);
	}
	
	public void controlador(ActionListener ctr) {
		si.addActionListener(ctr);
		si.setActionCommand("SI");
		no.addActionListener(ctr);
		no.setActionCommand("NO");
		nprimos.addActionListener(ctr);
		nprimos.setActionCommand("NPRIMOS");
		cancelar.addActionListener(ctr);
		cancelar.setActionCommand("CANCELAR");
	}
	
	public void mensaje(String str) {
		verdad.setText(str);
	}
	
	public void mensaje1(String str) {
		ms.setText(str);
	}
	
	public int nPrimos() {
		return Integer.parseInt(nprimos.getText());
	}
	
	public void listaPrimos(List<Integer> lista) {
		int i = 0;
		for (int e:lista) {
			area.append("["+i+"]:"+e+"  ");
			if ((i+1)%10==0) area.append("\n");
			i++;
		}
	}

	public void limpiar() {
		area.setText("");
	}
	
	public void cambiaProgreso(int n) {
		progreso.setValue(n);
	}
}
