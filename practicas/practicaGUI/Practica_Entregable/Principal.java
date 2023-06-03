package practicas.practicaGUI.Practica_Entregable;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Principal {
    public static void crearGui(JFrame ventana) {
        Panel panel = new Panel();
        Controller ctr = new Controller(panel);
        panel.setController(ctr);
        ventana.setContentPane(panel);
        ventana.pack();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Gui worker");

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                crearGui(ventana);
            }
        });

    }
}
