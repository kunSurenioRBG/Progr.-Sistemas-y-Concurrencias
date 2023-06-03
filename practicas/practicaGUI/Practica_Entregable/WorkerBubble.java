package practicas.practicaGUI.Practica_Entregable;

import java.util.List;

import javax.swing.SwingWorker;

public class WorkerBubble extends SwingWorker<Void, Integer> {
    private Panel panel;
    private int n;
    private List<Integer> lista;
    private List<Integer> listaBubble;

    public WorkerBubble(int n, Panel panel, List<Integer> lista) {
        this.n = n;
        this.panel = panel;
        this.lista = lista;
    }

    private void ordenarBubble(int n) {
        listaBubble.addAll(lista);

        for (int i = 0; i < n - 1; i++) {
            // recorremos el array a[i..N-1] desde la posición N-1 a la i.
            // Comparamos los elementos dos a dos y los intercambiamos si
            // están desordenados
            for (int j = n - 1; j > i; j--) {
                if (listaBubble.get(j) < listaBubble.get(j - 1)) {
                    int pos1 = listaBubble.get(j);
                    int pos2 = listaBubble.get(j - 1);
                    int aux = pos1;
                    pos1 = pos2;
                    pos2 = aux;
                }
            }
            // Inv: a[0..i] tiene los i+1 menores elementos de a[0..N-1]
            // Inv: a[0..i] está ordenado
        }
    }

    @Override
    protected Void doInBackground() throws Exception {
        // TODO Auto-generated method stub
        ordenarBubble(n);

        return null;
    }

    public void process(List<Integer> lista) {
        panel.writeTextAreaBubble(lista);
    }

}
