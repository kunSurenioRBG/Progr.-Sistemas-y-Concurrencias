package practicas.practicaGUI.Practica_Entregable;

import java.util.List;
import javax.swing.SwingWorker;

public class WorkerSelection extends SwingWorker<Void, Integer> {
    private Panel panel;
    private int n;
    private List<Integer> lista;
    private List<Integer> listaSelection;

    public WorkerSelection(int n, Panel panel, List<Integer> lista) {
        this.n = n;
        this.panel = panel;
        this.lista = lista;
    }

    private void ordenarSelection(int n) {
        listaSelection.addAll(lista);

        for (int i = 0; i < n - 1; i++) {
            // buscamos el menor elemento del array a[i..N-1]
            // y lo intercambiamos con a[i]
            int menor = i;
            for (int j = i + 1; j < n; j++)
                if (listaSelection.get(j) < listaSelection.get(menor))
                    menor = j;
            int pos1 = listaSelection.get(i);
            int pos2 = listaSelection.get(menor);
            int aux = pos1;
            pos1 = pos2;
            pos2 = aux;
            // Inv: a[0..i] tiene los i+1 menores elementos de a[0..N-1]
            // Inv: a[0..i] está ordenado
        }
    }

    @Override
    protected Void doInBackground() throws Exception {
        // TODO Auto-generated method stub
        ordenarSelection(n);

        return null;

    }

    public void process(List<Integer> lista) {
        panel.writeTextAreaSelection(lista);
    }
}