package Battleships;

import java.util.ArrayList;

/**
 * Created by dmclark on 26/07/17.
 */
public class AI {

    ArrayList<ArrayList> l_x = new ArrayList<>();
    ArrayList<Xy> next = new ArrayList<Xy>();
    int b_s = 0;
    private int y;
    private int x;
    Xy last = null;

    public AI(int size) {
        b_s = size;
        for (int x = 0; x < b_s; x++) {
            ArrayList<Boolean> l_y = new ArrayList<>();
            for (int y = 0; y < b_s; y++) {
                l_y.add(true);
            }

            l_x.add(l_y);

        }

    }

    public Xy getLast() {
        return last;
    }

    public void add_Boats(Board b2) {

        String b = b2.get_next_Boat();

        while (b != null) {
            int s = b2.get_next_Boat_size();
            int d = (int) (1 + Math.random() * 2);
            int x = 0;
            int y = 0;
            if (d == 1) {
                x = (int) (Math.random() * b_s);
                y = (int) (Math.random() * b_s) - s;
                if (y < 0) {
                    y = 1;
                }
            } else {
                x = (int) (Math.random() * b_s - s);
                y = (int) (Math.random() * b_s);
                if (x < 0) {
                    x = 1;
                }

            }
            System.out.println(x + " " + y + " " + d + " ~ " + b2.get_next_Boat());
            System.out.println(b2.add_next_Boat(new Xy(x, y), d));
            b = b2.get_next_Boat();
        }
        System.out.println(b2);

    }


    //public boolean take_Next_Shot(Battleships.Board b1, int x, int y) {

//        ArrayList<int[]> stuf = new ArrayList<>();
//        //stuf.add()
//
//        while (true) {
//        int d = (int) (1 + Math.random() * index);
//        int xd=0;
//        int yd=0;
//        if (d == 1) {
//            xd = 0;
//            yd = 1;
//            if ((Boolean) l_x.get(x+xd).get(y+yd)) {
//                return b1.take_Shot(x, y);
//            }
//        } else if (d == 2) {
//            xd = 1;
//            yd = 0;
//        } else if (d == 3) {
//            xd = 0;
//            yd = -1;
//        } else if (d == 4) {
//            xd = -1;
//            yd = 0;
//        }
//
//            if ((Boolean) l_x.get(x+xd).get(y+yd)) {
//                return b1.take_Shot(x, y);
//            }
//        }
    // }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void jeijdcjew() {

    }

    public boolean take_New_Shot(Board b1) {
        boolean r;
        if (next.isEmpty()) {
            while (true) {
                x = (int) (Math.random() * b_s);
                y = (int) (Math.random() * b_s);
                //System.out.println(l_x.get(x).get(y));
                if ((Boolean) l_x.get(x).get(y)) {
                    l_x.get(x).set(y, false);
                    last = new Xy(x, y);

                    r = b1.take_Shot(last);
                    return r;
                }
            }
        } else {


            r = b1.take_Shot(new Xy(x, y));
        }
        return r;

    }
}
