package practicas.practicaGUI.Practica_Entregable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller implements ActionListener {
    private Panel panel;
    private WorkerSelection wS = null;
    private WorkerBubble wB = null;
    private List<Integer> lista;
    private int n;
    private Random r = new Random();

    public Controller(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("BOTON")) {
            if (panel.getTam() == 0) {
                // error me lo devuelve getTam con su "try-catch"
            } else {
                n = panel.getTam();

                lista = new ArrayList<>(n);
                for (int i = 0; i < n; i++) {
                    int valorDado = r.nextInt(100000); // 1-99999 (excluye el 100000)
                    lista.add(i, valorDado);
                }
                // limpiamos los cuadro de texto de "Selection" y "Bubble"
                panel.clearAreaBubble();
                panel.clearAreaSelection();
                panel.writeTextArea(lista);

                panel.messageArea("List Created");
                panel.comment("Number correct");
                panel.enableSortButton();
                // panel.disableCreateButton();
            }

        } else if (e.getActionCommand().equals("BOTONO")) {
            // pasamos la lista a los "workers" para que las ordenen, con sus respectivos
            // algoritmos
            panel.initIndexes();
            panel.messageAreaSelection("Sorting the list");
            panel.messageAreaBubble("Sorting the list");

            // tiempo de WorkerSelection
            long startTime1 = System.currentTimeMillis();
            wS = new WorkerSelection(lista);
            panel.writeTextAreaSelection(wS.getLista());
            long total1 = (System.currentTimeMillis() - startTime1);
            panel.messageAreaSelection("List sorted in " + total1 + " ms.");

            // tiempo de WorkerBubble
            long startTime2 = System.currentTimeMillis();
            wB = new WorkerBubble(lista);
            panel.writeTextAreaBubble(wB.getLista());
            long total2 = (System.currentTimeMillis() - startTime2);
            panel.messageAreaBubble("List sorted in " + total2 + " ms.");
        }
    }
}