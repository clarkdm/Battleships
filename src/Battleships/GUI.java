package Battleships;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/**
 * Created by dmclark on 24/07/17.
 */
public class GUI extends Frame {

    HashMap<Xy, Button> b1 = new HashMap<Xy, Button>();
    HashMap<Xy, Button> b2 = new HashMap<Xy, Button>();

    private GUI_I listeners;

    // Constructor to setup Battleships.GUI components and event handlers
    public GUI(int size, GUI_I a) {
        listeners = a;
        JFrame frame = new JFrame("p1");
        frame.setLayout(new GridLayout(size, size, size, size));
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
        // "super" Frame sets layout to 3x2 GridLayout, horizontal and verical gaps of 3 pixels

        // The components are added from left-to-right, top-to-bottom
        for (int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < size; x++) {
                Xy xy = new Xy(x, y);
                b1.put(xy, (new Button((x + "," + y + ", W"))));
                b1.get(xy).setBackground(new Color(0xB8B3FF));
                b1.get(xy).addActionListener(new buttonListener());
                frame.add(b1.get(xy));

            }
        }


        //frame.setTitle("GridLayout Demo"); // "super" Frame sets title
        frame.setSize(280, 150);           // "super" Frame sets initial size
        frame.setVisible(true);            // "super" Frame shows


        JFrame frame_2 = new JFrame("Battleships.AI");
        frame_2.setLayout(new GridLayout(size, size, size, size));

        frame_2.setLocationRelativeTo(null);

        frame_2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame_2.setVisible(true);
        // "super" Frame sets layout to 3x2 GridLayout, horizontal and verical gaps of 3 pixels

        // The components are added from left-to-right, top-to-bottom
        for (int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < size; x++) {
                Xy xy = new Xy(x, y);
                b2.put(xy, (new Button((x + "," + y + ", W"))));
                b2.get(xy).setBackground(new Color(0xB8B3FF));
                b2.get(xy).addActionListener(new buttonListener());
                frame_2.add(b2.get(xy));

            }
        }


        //frame_2.setTitle("GridLayout Demo"); // "super" Frame sets title
        frame_2.setSize(400, 400);
        frame.setSize(750, 650);// "super" Frame sets initial size
        frame_2.setVisible(true);            // "super" Frame shows
    }

    public void Shot(Xy xy) {
        listeners.someoneSaid_Shot(xy);
    }

    public void edit(Xy xy, char c) {


        if (c == 'X') {
            b1.get(xy).setLabel(xy.out() + ", " + "XXXXX" + "");
            // b1.get(xy).setForeground(new Color(0xFF0000));
            b1.get(xy).setBackground(new Color(0xFF1E21));
        } else {
            b1.get(xy).setLabel(xy.out() + ", " + "MMMM" + "");
            //b1.get(xy).setForeground(new Color(0x0000FF));
            b1.get(xy).setBackground(new Color(0x5C60FF));
        }
    }

    public void edit_2(Xy xy, char c) {

        if (c == 'X') {
            b2.get(xy).setLabel(xy.out() + ", " + "XXXXX" + "");
            //b2.get(xy).setForeground(new Color(0xFF0000));
            b2.get(xy).setBackground(new Color(0xFF1E21));
        } else {
            b2.get(xy).setLabel(xy.out() + ", " + "MMMM" + "");
            //b2.get(xy).setForeground(new Color(0x0000FF));
            b2.get(xy).setBackground(new Color(0x5C60FF));
        }
    }


    public class buttonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            Button buttonClicked = (Button) e.getSource(); //get the particular button that was clicked

            String[] l = buttonClicked.getLabel().split(",");
            // System.out.println(l[0]);
            int x = Integer.parseInt(l[0]);
            int y = Integer.parseInt(l[1]);
            //System.out.println(x+"hit"+y);
            Shot(new Xy(x, y));
            //JOptionPane.showConfirmDialog(null, (x + "Battleships.Game Over." + y));
        }
    }

}