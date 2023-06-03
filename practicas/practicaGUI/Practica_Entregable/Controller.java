package practicas.practicaGUI.Practica_Entregable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Controller implements ActionListener, PropertyChangeListener {
    private Panel panel;
    private WorkerSelection wS = null;
    private WorkerBubble wB = null;
    private List<Integer> lista;
    private int n;

    public Controller(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("BOTON")) {
            n = panel.getTam();
            if (n < 1 || n > 60000) {
                panel.initIndexes();
                panel.comment("El numero introducido no es correcto");
            }
            lista = new ArrayList<>(n);
            // limpiamos los cuadro de texto de "Selection" y "Bubble"
            panel.clearAreaBubble();
            panel.clearAreaSelection();

            panel.writeTextArea(lista);
            panel.messageArea("List Created");
        } else if (e.getActionCommand().equals("BOTONO")) {
            // pasamos la lista a los "workers" para que las ordenen, con sus respectivos
            // algoritmos
            wS = new WorkerSelection(n, panel, lista);
            wB = new WorkerBubble(n, panel, lista);

            wS.addPropertyChangeListener(this);
            wS.execute();
            wB.addPropertyChangeListener(this);
            wB.execute();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'propertyChange'");
    }
}