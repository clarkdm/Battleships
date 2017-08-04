package Battleships;

import java.util.ArrayList;

/**
 * Created by dmclark on 23/07/17.
 */


public class Board {

    int size;
    char[][] board;

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
    ArrayList<Character> shi = new ArrayList<Character>();
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
        board = new char[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                board[x][y] = 'w';
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
        for (int i = 0; i < patrol_boat; i++) {
            shi.add('p');
        }
        for (int i = 0; i < battleships; i++) {
            shi.add('b');
        }
        for (int i = 0; i < submarine; i++) {
            shi.add('s');
        }
        for (int i = 0; i < destroyer; i++) {
            shi.add('d');
        }
        for (int i = 0; i < carrier; i++) {
            shi.add('c');
        }
        board = new char[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                board[x][y] = 'w';
            }
        }
    }

    public boolean take_Shot(int x, int y) {


        if (board[x][y] == 'w' || board[x][y] == 'x') {
            board[x][y] = 'x';
            return false;
        } else {
            boolean f = false;
            int i = 0;
            while (!f) {
                f = ships.get(i).hit(x, y);
                if (f) {
                    board[x][y] = Character.toUpperCase(ships.get(i).getType());
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
                ships.remove(i);
                return shi.get(i);
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

    public boolean add_next_Boat(int x, int y, int d) {
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
        System.out.println(can_plas(l, x, y, xd, yd));
        if (can_plas(l, x, y, xd, yd)) {
            if (type == 'p') {
                n_patrol_boat--;
                System.out.println("#########");
            } else if (type == 'b') {
                n_battleships--;

            } else if (type == 's') {
                n_submarine--;

            } else if (type == 'd') {
                n_destroyer--;

            } else if (type == 'c') {
                n_carrier--;
            }

            return plas(type, l, x, y, xd, yd);
        }
        return false;
    }


    public boolean if_empte(int x, int y) {
        if (board[x][y] == 'w') {
            return true;
        }
        return false;
    }

    public boolean can_plas(int l, int x, int y, int xd, int yd) {

        for (int i = 0; i < l; i++) {
            if (!if_empte(x, y)) {
                return false;
            } else {
                y = y + yd;
                x = x + xd;
            }
        }
        return true;
    }

    public boolean plas(char type, int l, int x, int y, int xd, int yd) {
        Ship s = new Ship(type);
        for (int i = 0; i < l; i++) {
            board[x][y] = type;
            s.add(x, y);
            y = y + yd;
            x = x + xd;
        }
        ships.add(s);
        return true;
    }

    public boolean add_boat(char type, int x, int y, int d) {
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

        if (can_plas(l, x, y, xd, yd)) {
            plas(type, l, x, y, xd, yd);
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
        for (int y = size - 1; y >= 0; y--) {
            if (y > 9) {
                temp = temp + y + " ";
            } else {
                temp = temp + y + "  ";
            }

            for (int x = 0; x < size; x++) {
                temp = temp + "  " + board[x][y];
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
                if (board[x][y] == 'w') {
                    temp = temp + " " + 'w';
                } else if (board[x][y] == 'x') {
                    temp = temp + " " + 'x';
                } else if (board[x][y] == 'p') {
                    temp = temp + " " + 'w';
                } else if (board[x][y] == 'b') {
                    temp = temp + " " + 'w';
                } else if (board[x][y] == 's') {
                    temp = temp + " " + 'w';
                } else if (board[x][y] == 'd') {
                    temp = temp + " " + 'w';
                } else if (board[x][y] == 'c') {
                    temp = temp + " " + 'w';
                } else if (board[x][y] == 'P') {
                    temp = temp + " " + 'x';
                } else if (board[x][y] == 'B') {
                    temp = temp + " " + 'x';
                } else if (board[x][y] == 'S') {
                    temp = temp + " " + 'x';
                } else if (board[x][y] == 'D') {
                    temp = temp + " " + 'x';
                } else if (board[x][y] == 'C') {
                    temp = temp + " " + 'x';
                }

            }
            temp = temp + "\n";
        }
        return temp;
    }

}
