package Stoichiometry.Snow;

/**
 * Write a description of class Snowman here.
 *
 * @author Khoi Bui
 * @version July 27, 2023
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

public class Snowman extends JFrame {
    private JPanel panel;
    private JComponent component;

    public Snowman() {
        panel = new JPanel();
        component = new SnowmanComponent();
        component.setPreferredSize(new Dimension(500, 500));
        panel.add(component);
        add(panel);

        setTitle("Snowman");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    class SnowmanComponent extends JComponent {
        public void paintComponent(Graphics g) {
            // Background
            g.setColor(new Color(204, 204, 255));
            g.fillRect(0, 0, getWidth(), getHeight());

            // Ground
            g.setColor(new Color(176, 245, 255));
            g.fillRect(0, 400, 500, 100);

            // Sun
            for (int i = 0; i < 100; i++) {
                g.setColor(Color.white);
                g.fillOval((int)(Math.random() * 500), (int)(Math.random() * 500), 10, 10);
            }

            // Snowman
            // Body
            g.setColor(Color.white);
            g.fillOval(200, 120, 120, 120);

            g.setColor(Color.white);
            g.fillOval(180, 180, 160, 160);

            g.setColor(Color.white);
            g.fillOval(160, 250, 200, 200);

            // Eyes
            g.setColor(Color.black);
            g.fillOval(230, 145, 20, 20);

            g.setColor(Color.black);
            g.fillOval(270, 145, 20, 20);

            g.drawLine(250, 155, 270, 155);

            // Hands
            // Left
            g.setColor(Color.white);
            g.fillOval(125, 210, 140, 30);

            // Right
            g.setColor(Color.white);
            g.fillOval(255, 210, 140, 30);

            // Sticker
            g.setColor(Color.black);
            g.drawOval(280, 220, 20, 20);
            g.drawLine(280, 230, 285, 230);
            g.drawLine(285, 230, 288, 225);
            g.drawLine(288, 225, 292, 225);
            g.drawLine(292, 225, 295, 230);
            g.drawLine(295, 230, 300, 230);

        }
    }

    public static void main(String[] args) {
        Snowman guiWindow = new Snowman();
        guiWindow.setVisible(true);
    }
}
