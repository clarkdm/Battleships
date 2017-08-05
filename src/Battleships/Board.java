package Battleships;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dmclark on 23/07/17.
 */


public class Board {

    int size;
    //char[][] board;
    HashMap<Xy, Character> board = new HashMap<Xy, Character>();
    int n_patrol_boat;
    int n_battleships;
    int n_submarine;
    int n_destroyer;
    int n_carrier;

    int patrol_boat = 2;
    int battleships = 3;
    int submarine = 3;
    int destroyer = 4;
    int carrier = 5;
    int num_ships = 0;
    ArrayList<Ship> ships = new ArrayList<Ship>();

    //
    // empte w
    // miss x
    // patrol boat  pp      PP
    // battleships  bbb     BBB
    // submarine    sss     SSS
    // destroyer    dddd    DDDD
    // carrier      ccccc   CCCCC


    public Board(int size) {
        this.size = size;

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                board.put(new Xy(x, y), new Character('w'));
            }
        }

    }

    public Board(int size, int patrol_boat, int battleships, int submarine, int destroyer, int carrier) {
        this.size = size;
        this.n_patrol_boat = patrol_boat;
        this.n_battleships = battleships;
        this.n_submarine = submarine;
        this.n_destroyer = destroyer;
        this.n_carrier = carrier;


        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                board.put(new Xy(x, y), new Character('w'));
            }
        }
    }

    public boolean take_Shot(Xy xy) {


        if (board.get(xy) == 'w' || board.get(xy) == 'x') {
            board.replace(xy, 'x');
            return false;
        } else {
            boolean f = false;
            int i = 0;
            while (!f) {
                if (i == ships.size()) {
                    return false;
                }
                f = ships.get(i).hit(xy);
                //System.out.println(ships.size()+" : "+i+f);
                if (f) {
                    board.replace(xy, Character.toUpperCase(ships.get(i).getType()));
                    System.out.println(ships.size() + " : " + i + f);
                }
                i++;


            }


        }
        return true;
    }


    public boolean is_Alive() {
        int l = 0;


        if (ships.size() == 0) {
            l++;
        }


        if (l > 0) {
            return true;
        }
        return false;
    }

    public Character is_S_Alive() {
        int l = 0;

        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).is_Alive_()) {
                l++;
            } else {
                char car = ships.get(i).getType();
                ships.remove(i);
                return car;
            }

        }
        if (l > 0) {
            return 'n';
        }
        return 'n';
    }


    public int getSize() {
        return size;
    }

    public String get_next_Boat() {

        if (n_patrol_boat > 0) {
            return "patrol_boat";
        } else if (n_battleships > 0) {
            return "battleship";
        } else if (n_submarine > 0) {
            return "submarine";
        } else if (n_destroyer > 0) {
            return "estroyer";
        } else if (n_carrier > 0) {
            return "carrier";
        }

        return null;
    }

    public int get_next_Boat_size() {

        if (n_patrol_boat > 0) {
            return 2;
        } else if (n_battleships > 0) {
            return 3;
        } else if (n_submarine > 0) {
            return 3;
        } else if (n_destroyer > 0) {
            return 4;
        } else if (n_carrier > 0) {
            return 5;
        }

        return 0;
    }

    public boolean add_next_Boat(Xy xy, int d) {
        int x = xy.getX();
        int y = xy.getY();
        int l = 2;
        char type = 'w';
        int xd = 0;
        int yd = 0;

        if (d == 1) {
            xd = 0;
            yd = 1;
        } else if (d == 2) {
            xd = 1;
            yd = 0;
        } else if (d == 3) {
            xd = 0;
            yd = -1;
        } else if (d == 4) {
            xd = -1;
            yd = 0;
        }

        if (n_patrol_boat > 0) {
            l = patrol_boat;
            type = 'p';
        } else if (n_battleships > 0) {
            l = battleships;
            type = 'b';
        } else if (n_submarine > 0) {
            l = submarine;
            type = 's';
        } else if (n_destroyer > 0) {
            l = destroyer;
            type = 'd';
        } else if (n_carrier > 0) {
            l = carrier;
            type = 'c';
        }
        //System.out.println(can_plas(l, xy, xd, yd));
        if (can_plas(l, xy, xd, yd)) {
            if (type == 'p') {
                n_patrol_boat--;
                //System.out.println("#########");
            } else if (type == 'b') {
                n_battleships--;

            } else if (type == 's') {
                n_submarine--;

            } else if (type == 'd') {
                n_destroyer--;

            } else if (type == 'c') {
                n_carrier--;
            }

            return plas(type, l, xy, xd, yd);
        }
        return false;
    }


    public boolean if_empte(Xy xy) {

        if (board.get(xy) == 'w') {
            return true;
        }
        return false;
    }

    public boolean can_plas(int l, Xy xy, int xd, int yd) {
        int x = xy.getX();
        int y = xy.getY();
        for (int i = 0; i < l; i++) {
            if (!if_empte(new Xy(x, y))) {
                return false;
            } else {
                y = y + yd;
                x = x + xd;
            }
        }
        return true;
    }

    public boolean plas(char type, int l, Xy xy, int xd, int yd) {


        Ship s = new Ship(type);
        for (int i = 0; i < l; i++) {
            board.replace(xy, type);
            s.add(xy);
            xy.add_XY(xd, yd);


        }
        ships.add(s);
        return true;
    }

    public boolean add_boat(char type, Xy xy, int d) {
        int xd = 0;
        int yd = 0;
        int l = 1;
        if (d == 1) {
            xd = 0;
            yd = 1;
        } else if (d == 2) {
            xd = 1;
            yd = 0;
        } else if (d == 3) {
            xd = 0;
            yd = -1;
        } else if (d == 4) {
            xd = -1;
            yd = 0;
        }

        if (type == 'p') {
            l = patrol_boat;

        } else if (type == 'b') {
            l = battleships;

        } else if (type == 's') {
            l = submarine;

        } else if (type == 'd') {
            l = destroyer;

        } else if (type == 'c') {
            l = carrier;
        }

        if (can_plas(l, xy, xd, yd)) {
            plas(type, l, xy, xd, yd);
            return true;
        }
        return false;

    }


    public String toString() {
        String temp = "";
        if (size > 9) {
            temp = "    ";
        } else {
            temp = "  ";
        }

        for (int x = 0; x < size; x++) {
            temp = temp + x + "  ";
        }
        temp = temp + "\n";
        Xy xy = new Xy(0, 0);
        for (int y = size - 1; y >= 0; y--) {
            if (y > 9) {
                temp = temp + y + " ";
            } else {
                temp = temp + y + "  ";
            }

            for (int x = 0; x < size; x++) {
                xy.set(x, y);
                temp = temp + "  " + board.get(xy);
            }
            temp = temp + "\n";
        }
        return temp;
    }

    public String toString_2() {
        String temp = "";
        if (size > 9) {
            temp = "    ";
        } else {
            temp = "  ";
        }
        Xy xy = new Xy(0, 0);
        Character car;
        for (int x = 0; x < size; x++) {
            temp = temp + x + "  ";
        }
        temp = temp + "\n";
        for (int y = size - 1; y >= 0; y--) {

            if (y > 9) {
                temp = temp + y + " ";
            } else {
                temp = temp + y + "  ";
            }

            for (int x = 0; x < size; x++) {
                xy.set(x, y);
                car = board.get(xy);
                if (car == 'w') {
                    temp = temp + " " + 'w';
                } else if (car == 'x') {
                    temp = temp + " " + 'x';
                } else if (car == 'p') {
                    temp = temp + " " + 'w';
                } else if (car == 'b') {
                    temp = temp + " " + 'w';
                } else if (car == 's') {
                    temp = temp + " " + 'w';
                } else if (car == 'd') {
                    temp = temp + " " + 'w';
                } else if (car == 'c') {
                    temp = temp + " " + 'w';
                } else if (car == 'P') {
                    temp = temp + " " + 'x';
                } else if (car == 'B') {
                    temp = temp + " " + 'x';
                } else if (car == 'S') {
                    temp = temp + " " + 'x';
                } else if (car == 'D') {
                    temp = temp + " " + 'x';
                } else if (car == 'C') {
                    temp = temp + " " + 'x';
                }

            }
            temp = temp + "\n";
        }
        return temp;
    }

}
