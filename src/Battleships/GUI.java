package Battleships;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dmclark on 24/07/17.
 */
public class GUI extends Frame {
    public Button[][] b = new Button[12][12];
    public Button[][] b2 = new Button[12][12];
    private GUI_I listeners;

    // Constructor to setup Battleships.GUI components and event handlers
    public GUI(int size, GUI_I a) {
        listeners = a;
        JFrame frame = new JFrame("p1");
        frame.setLayout(new GridLayout(size, size, size, size));
        frame.setSize(600, 600);
        // "super" Frame sets layout to 3x2 GridLayout, horizontal and verical gaps of 3 pixels

        // The components are added from left-to-right, top-to-bottom
        for (int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < size; x++) {

                b[x][y] = (new Button((x + "," + y + ", W")));

                b[x][y].addActionListener(new buttonListener());
                frame.add(b[x][y]);

            }
        }


        //frame.setTitle("GridLayout Demo"); // "super" Frame sets title
        frame.setSize(280, 150);           // "super" Frame sets initial size
        frame.setVisible(true);            // "super" Frame shows


        JFrame frame_2 = new JFrame("Battleships.AI");
        frame_2.setLayout(new GridLayout(size, size, size, size));
        frame_2.setSize(600, 600);
        // "super" Frame sets layout to 3x2 GridLayout, horizontal and verical gaps of 3 pixels

        // The components are added from left-to-right, top-to-bottom
        for (int y = size - 1; y >= 0; y--) {
            for (int x = 0; x < size; x++) {

                b2[x][y] = (new Button((x + "," + y + ", W")));

                b2[x][y].setSize(30,30);

                b2[x][y].addActionListener(new buttonListener());
                frame_2.add(b2[x][y]);

            }
        }


        //frame_2.setTitle("GridLayout Demo"); // "super" Frame sets title
        frame_2.setSize(280, 150);           // "super" Frame sets initial size
        frame_2.setVisible(true);            // "super" Frame shows
    }

    public void Shot(int x, int y) {
        listeners.someoneSaid_Shot(x, y);
    }

    public void edit(int x, int y, char c) {
        if (c=='X') {
            b[x][y].setLabel(x + "," + y + ", " + "XXXXX" + "");
            b[x][y].setForeground(new Color(0xFF0000));
        }else {
            b[x][y].setLabel(x + "," + y + ", " + "MMMM" + "");
            b[x][y].setForeground(new Color(0x0000FF));
        }
    }
    public void edit_2(int x, int y, char c) {
        if (c=='X'){
            b2[x][y].setLabel(x + "," + y + ", " + "XXXXX" + "");
            b2[x][y].setForeground(new Color(0xFF0000));
        }else {
            b2[x][y].setLabel(x + "," + y + ", " + "MMMM" + "");
            b2[x][y].setForeground(new Color(0x0000FF));
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
            Shot(x, y);
            //JOptionPane.showConfirmDialog(null, (x + "Battleships.Game Over." + y));
        }
    }

}