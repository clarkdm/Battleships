package Battleships;

import java.util.Scanner;

/**
 * Created by dmclark on 23/07/17.
 */
public class Game implements GUI_I {

    private final AI ai;
    private GUI gui;
    Board b1;
    Board b2;
    int size;
    //   public int patrol_boat = 2;
//    int battleships = 2;
//    int submarine = 1;
//    int destroyer = 1;
//    int carrier = 1;
    public int patrol_boat = 2;
    int battleships = 2;
    int submarine = 2;
    int destroyer = 1;
    int carrier = 1;
    boolean t;
    boolean start;
    int x = 99;
    int y = 99;

    public Game(int size) {
        this.size = size;
        b1 = new Board(size, patrol_boat, battleships, submarine, destroyer, carrier);
        b2 = new Board(size, patrol_boat, battleships, submarine, destroyer, carrier);
        ai = new AI(size);
        gui = new GUI(size, this);
        start = true;
        t = true;
        ai.add_Boats(b2);
        System.out.println("P1#################################");
        ai.add_Boats(b1);
//        add_Boats(b1);
//
//        while (start) {
//            if (t) {
//                System.out.println("P1");
//                take_Shot(b2);
//
//                start = b2.is_Alive();
//
//                t = !t;
//            } else {
//                System.out.println("P2");
//                //ai.take_Shot(b1);
//
//                start = b1.is_Alive();
//                t = !t;
//            }
////
//
//        }
//        System.out.println("GAME OVER");
//        if (t) {
//            System.out.println("P2 win");
//        } else {
//            System.out.println("P1 win");
//        }


//        while (start) {
//            if (t) {
//                System.out.println("P1");
//                take_Shot(b2);
//
//                start = b2.is_Alive();
//
//                t = !t;
//            } else {
//                System.out.println("P2");
//                take_Shot(b1);
//
//                start = b1.is_Alive();
//                t = !t;
//            }
//
//
//        }
//        System.out.println("GAME OVER");
//        if (t) {
//            System.out.println("P2 win");
//        } else {
//            System.out.println("P1 win");
//        }


    }


    private boolean add_Boats(Board b) {
        String temp = b.get_next_Boat();
        while (temp != null) {
            System.out.println(b.toString());
            System.out.println(temp + " size " + b.get_next_Boat_size());
            Scanner sc = new Scanner(System.in);
            System.out.println("X");
            int x = sc.nextInt();
            System.out.println("y");
            int y = sc.nextInt();
            System.out.println("D");
            int d = sc.nextInt();
            boolean i = b.add_next_Boat(x, y, d);


            if (!i) {
                System.out.println("invaled");
            }
            System.out.println(b.toString());
            temp = b.get_next_Boat();
        }

        return true;
    }

    //
    public boolean take_Shot(Board b) {
        String temp = b.get_next_Boat();
        boolean i = true;
        while (i) {
            System.out.println(b.toString_2());
            System.out.println("Take Shot");
            Scanner sc = new Scanner(System.in);
            System.out.println("X");
            int x = sc.nextInt();
            System.out.println("y");
            int y = sc.nextInt();

            i = b.take_Shot(x, y);

            if (!i) {
                System.out.println("miss");
                return false;
            }
            System.out.println("hit");
            System.out.println(b.toString());
            if (!b.is_Alive()) {
                i = false;
            }
        }
        return true;
    }

    private boolean set_Board(Board b) {
        return true;
    }


    public static void main(String[] args) {
        Game g = new Game(12);
    }

    @Override
    public void someoneSaid_Shot(int x, int y) {
        if (t) {
            Board b;
//            if (t) {
                b = b1;
//            } else {
//                b = b2;
//            }

            boolean i = b.take_Shot(x, y);

            if (!i) {
                System.out.println("miss");
                gui.edit(x, y, 'M');

            } else {
                System.out.println("hit");
                // System.out.println(b.toString());
                gui.edit(x, y, 'X');
                if (!b.is_Alive()) {
                    i = false;
                }
            }
            boolean i_2 = ai.take_New_Shot(b2);
            if (!i_2) {
                System.out.println("miss");
                gui.edit_2(ai.getX(), ai.getY(), 'M');

            } else {
                System.out.println("hit");
                // System.out.println(b.toString());
                gui.edit_2(ai.getX(), ai.getY(), 'X');
                if (!b.is_Alive()) {
                    i_2 = false;
                }
            }
            System.out.println(b1);
            System.out.println(b2);
        }


    }
}
