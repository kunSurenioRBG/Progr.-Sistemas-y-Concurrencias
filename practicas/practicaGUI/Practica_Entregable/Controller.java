package practicas.practicaGUI.Practica_Entregable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller implements ActionListener, PropertyChangeListener {
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
                panel.disableCreateButton();
            }

        } else if (e.getActionCommand().equals("BOTONO")) {
            // pasamos la lista a los "workers" para que las ordenen, con sus respectivos
            // algoritmos
            wS = new WorkerSelection(n, panel, lista);
            wB = new WorkerBubble(n, panel, lista);

            // wS.addPropertyChangeListener(this);
            wS.execute();
            // wB.addPropertyChangeListener(this);
            wB.execute();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }
}