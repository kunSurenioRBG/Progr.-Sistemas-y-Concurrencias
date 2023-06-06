package practicas.practicaGUI.Practica_Entregable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.SwingWorker;

public class WorkerBubble extends SwingWorker<Void, List<Integer>> {
    private Panel panel;
    private int n;
    private List<Integer> lista;
    private List<Integer> listaBubble;

    public WorkerBubble(List<Integer> lista, int n, Panel panel) {
        this.lista = new ArrayList<>(lista);
        listaBubble = ordenarBubble();
        this.n = n;
        this.panel = panel;
    }

    private List<Integer> ordenarBubble() {
        List<Integer> listaSolucion = new ArrayList<>(lista);

        for (int i = 0; i < lista.size() - 1; i++) {
            // recorremos el array a[i..N-1] desde la posición N-1 a la i.
            // Comparamos los elementos dos a dos y los intercambiamos si
            // están desordenados
            for (int j = lista.size() - 1; j > i; j--) {
                if (listaSolucion.get(j) < listaSolucion.get(j - 1)) {
                    Collections.swap(listaSolucion, j, j - 1);
                }
            }
            firePropertyChange("progressBubble", null, i * 100 / listaSolucion.size());
            publish(listaSolucion.subList(0, i));
            // Inv: a[0..i] tiene los i+1 menores elementos de a[0..N-1]
            // Inv: a[0..i] está ordenado
        }
        firePropertyChange("progressBubble", null, 100);
        publish(listaSolucion);
        return listaSolucion;
    }

    public List<Integer> getLista() {
        return listaBubble;
    }

    @Override
    protected Void doInBackground() throws Exception {
        ordenarBubble();

        return null;
    }

    @Override
    public void process(List<List<Integer>> lista) {
        panel.clearAreaBubble();
        panel.writeTextAreaBubble(lista.get(lista.size() - 1));
    }
}
