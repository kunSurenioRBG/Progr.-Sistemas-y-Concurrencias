package practicas.practicaGUI.Practica_Entregable;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class Panel3 extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int iBubble=0, iSelect=0;

	private JLabel label = new JLabel("List size (1--60000 elems):");

	private JTextField size = new JTextField(8);
	private JButton createButton = new JButton("Create");
	private JButton sortButton = new JButton("Sort");
	private JButton cancelButton = new JButton("Cancel");
	
	private JTextArea area = new JTextArea(20,35);
	private JTextArea areaSelection = new JTextArea(20,35);
	private JTextArea areaBubble = new JTextArea(20,35);
	private JProgressBar progress = new JProgressBar(0,100);
	//esta barra de progreso no se utiliza, solo aparece por estética en el panel
	private JProgressBar progressSelection = new JProgressBar(0,100);
	private JProgressBar progressBubble = new JProgressBar(0,100);

	private JLabel messageArea = new JLabel("");
	private JLabel messageAreaSelection = new JLabel("");
	private JLabel messageAreaBubble = new JLabel("");
	private JLabel comment = new JLabel("GUI created");
	
	public Panel3(){
		this.setLayout(new BorderLayout());
		comment.setForeground(Color.RED);
		setCreateButton();
		setSortButton();
		setCancelButton();
		disableSortButton();
		disableCancelButton();
		JPanel topRow=new JPanel();
		topRow.add(label);
		topRow.add(size);
		topRow.add(createButton);
		topRow.add(sortButton);
		topRow.add(cancelButton);
		
		
		this.add(topRow,BorderLayout.NORTH);
		
		JPanel center = new JPanel();
		JPanel c1=new JPanel();
		c1.setLayout(new BorderLayout());
		JScrollPane scrol1 = new JScrollPane(area);
		scrol1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrol1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		c1.add(scrol1,BorderLayout.CENTER);
		JPanel c11 = new JPanel();
		c11.setLayout(new GridLayout(3,1));
		c11.add(new JLabel("Array to be sorted"));
		c11.add(progress);
		c11.add(messageArea);
		c1.add(c11,BorderLayout.SOUTH);
		
		JPanel c2=new JPanel();
		c2.setLayout(new BorderLayout());
		JScrollPane scrol2 = new JScrollPane(this.areaSelection);
		scrol2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrol2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		c2.add(scrol2,BorderLayout.NORTH);
		JPanel c21 = new JPanel();
		c21.setLayout(new GridLayout(3,1) );
		c21.add((new JLabel("Selection sort")));
		c21.add(progressSelection);
		c21.add(messageAreaSelection);
		c2.add(c21,BorderLayout.SOUTH);
		
		
		JPanel c3=new JPanel();
		c3.setLayout(new BorderLayout());
		JScrollPane scrol3 = new JScrollPane(this.areaBubble);
		scrol3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrol3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		c3.add(scrol3,BorderLayout.NORTH);
		JPanel c31 = new JPanel();
		c31.setLayout(new GridLayout(3,1) );
		c31.add((new JLabel("Bubble sort")));
		c31.add(progressBubble);
		c31.add(messageAreaBubble);
		c3.add(c31,BorderLayout.SOUTH);

		
		center.add(c1);
		center.add(c2);
		center.add(c3);
		this.add(center,BorderLayout.CENTER);
		this.add(comment,BorderLayout.SOUTH);
	
	
	}


	public void disableCreateButton() {
		createButton.setEnabled(false);
	}
	public void disableSortButton() {
		sortButton.setEnabled(false);
	}
	public void disableCancelButton() {
		cancelButton.setEnabled(false);
	}
	public void enableCreateButton() {
		createButton.setEnabled(true);
	}
	public void enableSortButton() {
		sortButton.setEnabled(true);
	}
	public void enableCancelButton() {
		cancelButton.setEnabled(true);
	}
	public void setCreateButton() {
		
		createButton.setActionCommand("BOTON");
		
	}

	public void setSortButton() {
		
		sortButton.setActionCommand("BOTONO");
		
	}
	
	public void setCancelButton() {
		
		cancelButton.setActionCommand("BOTONC");
	}
	
	public void setController(ActionListener ctr){
	
		createButton.addActionListener(ctr);
		sortButton.addActionListener(ctr);
		cancelButton.addActionListener(ctr);
	}
	
	public int getTam(){
		String tam = size.getText();
		int num;
		
		try {
			num = Integer.parseInt(tam);
			if(num > 60000 || num == 0) throw new NumberFormatException();
			
		} catch (NumberFormatException e) { 
			
				this.comment("enter a number between 1 and 60000");
				size.setText("");
				num=0;
		}
		
		
		return num;
	}
	
	
	
	public void clearArea(){
		area.setText("");
	
	}
	public void clearAreaSelection(){
		areaSelection.setText("");
	
	}
	public void clearAreaBubble(){
		this.areaBubble.setText("");
	
	}

	public void writeTextArea(java.util.List<Integer> lista) {
		this.clearArea();
		int i=0;
		for (Integer e:lista) {
			i++;
			area.append(Integer.toString(e)+"  ");
			if (i%8==0) area.append("\n");
		}
	}
	public void writeTextAreaSelection(java.util.List<Integer> lista) {
		for (Integer e:lista) {
			this.iSelect++;
			areaSelection.append(Integer.toString(e)+"  ");
			if (iSelect%8==0) areaSelection.append("\n");
		}
		
	}
	public void writeTextAreaBubble(java.util.List<Integer> lista) {
		for (Integer e:lista) {
			this.iBubble++;
			areaBubble.append(Integer.toString(e)+"  ");
			if (iBubble%8==0) areaBubble.append("\n");
		}
		
	}
	
	public void messageArea(String texto) {
		this.messageArea.setText(texto);
	}
	public void messageAreaSelection(String texto) {
		this.messageAreaSelection.setText(texto);
	}
	public void messageAreaBubble(String texto) {
		this.messageAreaBubble.setText(texto);
	}

	public void comment(String texto) {
		comment.setText(texto);
	}
	
	//esta función se utiliza para reinicializar los índices,
	//cada vez que se comienza a escribir una nueva lista ordenada en
	//las correspondientes áreas de texto.
	public void initIndexes() {
		this.iSelect=0;
		this.iBubble=0;
	}

	
}