package practicas.practicaGUI.Practica_Entregable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkerBubble {
    private List<Integer> lista;
    private List<Integer> listaBubble;

    public WorkerBubble(List<Integer> lista) {
        this.lista = new ArrayList<>(lista);
        listaBubble = ordenarBubble();
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
            // Inv: a[0..i] tiene los i+1 menores elementos de a[0..N-1]
            // Inv: a[0..i] está ordenado
        }
        return listaSolucion;
    }

    public List<Integer> getLista() {
        return listaBubble;
    }
}
