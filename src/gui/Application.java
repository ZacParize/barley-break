package gui;

import interfaces.IApp;
import javax.swing.*;
import java.awt.*;

/**
 * Main class
 */
public class Application extends JFrame implements IApp {

    public Application() {
        super();
        initUI();
    }

    private void initUI() {
        this.add(new Board());
        this.setSize(700, 700);
        this.setBackground(Color.blue);
        this.setPreferredSize(new Dimension(700,700));
        this.setResizable(false);
        this.setTitle("Barley-break");
        //close operation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set form to screen centre
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        //EventQueue.invokeLater(new Runnable() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Application ex = new Application();
                ex.setVisible(true);
            }
        });
    }
}
