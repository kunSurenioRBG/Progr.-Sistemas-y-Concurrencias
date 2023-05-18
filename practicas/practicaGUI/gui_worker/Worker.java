package practicas.practicaGUI.gui_worker;

import java.util.*;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

public class Worker extends SwingWorker<List<Integer>,Void> {
	
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
	
	public List<Integer> listaPrimos(int n){
		List<Integer> lista = new ArrayList<Integer>();
		int i=0;
		int num = 1;
		while (i<n && !this.isCancelled()) {
			if (esPrimo(num)) {
				lista.add(num);
				i++;
			}
			num++;
		}
		return lista;
	}
	@Override
	protected List<Integer> doInBackground() throws Exception {
		// TODO Auto-generated method stub
		
		return listaPrimos(n);
	}
	
	public void done() {
		try {
			panel.listaPrimos(this.get());
			
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			panel.mensaje1("tarea cancelada");
		} catch (Exception exc) {
			panel.mensaje1("tarea cancelada");
		}
	}
	
	

}
