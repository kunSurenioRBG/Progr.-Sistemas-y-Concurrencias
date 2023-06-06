package practicas.practicaGUI.Practica_Entregable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.SwingWorker;

public class WorkerSelection extends SwingWorker<Void, List<Integer>> {
    private Panel panel;
    private List<Integer> lista;
    private List<Integer> listaSelection;

    public WorkerSelection(List<Integer> lista, Panel panel) {
        this.lista = new ArrayList<>(lista);
        listaSelection = ordenarSelection();
        this.panel = panel;
    }

    private List<Integer> ordenarSelection() {
        List<Integer> listaSolucion = new ArrayList<>(lista);

        for (int i = 0; i < listaSolucion.size() - 1; i++) {
            // buscamos el menor elemento del array a[i..N-1]
            // y lo intercambiamos con a[i]
            int menor = i;
            for (int j = i + 1; j < listaSolucion.size(); j++) {
                if (listaSolucion.get(j) < listaSolucion.get(menor)) {
                    menor = j;
                }
            }
            firePropertyChange("progressSelection", null, i * 100 / listaSolucion.size());
            publish(listaSolucion.subList(0, i));
            Collections.swap(listaSolucion, i, menor);

            // Inv: a[0..i] tiene los i+1 menores elementos de a[0..N-1]
            // Inv: a[0..i] está ordenado
        }
        firePropertyChange("progressSelection", null, 100);
        publish(listaSolucion);
        return listaSolucion;
    }

    public List<Integer> getLista() {
        return listaSelection;
    }

    @Override
    protected Void doInBackground() throws Exception {
        ordenarSelection();

        return null;
    }

    @Override
    public void process(List<List<Integer>> lista) {
        panel.clearAreaSelection();
        panel.writeTextAreaSelection(lista.get(lista.size() - 1));
    }
}