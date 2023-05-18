package practicas.practiaGUI.gui_publish;

import java.util.*;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

public class Worker extends SwingWorker<Void,Integer> {
	
	private int n;
	private Panel panel;
	public Worker(int n,Panel panel) {
		this.n = n;
		this.panel = panel;
	}

	private boolean esPrimo(int n) {
		int i = 2;
		boolean primo = true;
		while (i*i<=n && primo) {
			if (n%i==0) primo =false;
			i++;
		}
		return primo;
	}
	
	public void listaPrimos(int n){
		int i=0;
		int num = 1;
		while (i<n && !this.isCancelled()) {
			if (esPrimo(num)) {
				publish(num);//publico el número primo			
				i++;
				this.setProgress(i*100/n);
			}
			num++;
		}
	}
	@Override
	protected  Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		
	    listaPrimos(n);
	    
		return null;
	}
	
	public void process(List<Integer> lista) {
		panel.listaPrimos(lista);
	}
	

}
