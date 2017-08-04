package Battleships;

import java.util.Arrays;

/**
 * Created by dmclark on 24/07/17.
 */
public class Ship {

    int health = 0;
    int positions_x[] = {-1, -1, -1, -1, -1};
    int positions_y[] = {-1, -1, -1, -1, -1};
    char type;

    int patrol_boat = 2;
    int battleships = 3;
    int submarine = 3;
    int destroyer = 4;
    int carrier = 5;

    public Ship(char type) {


        this.type = type;


    }

    public boolean add(int x, int y) {
        positions_x[health] = x;
        positions_y[health] = y;
        health = health + 1;
        return true;
    }

    public boolean is_Alive_() {
        if (health > 0) {
            return true;
        }
        return false;
    }

    public char getType() {
        return type;
    }

    public boolean hit(int x, int y) {
        for (int i = 0; i < positions_x.length; i++) {
            if (positions_x[i] == x) {
                if (positions_y[i] == y) {
                    positions_y[i] = -1;
                    positions_x[i] = -1;
                    health = health - 1;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Battleships.Ship{" +
                "health=" + health +
                ", positions_x=" + Arrays.toString(positions_x) +
                ", positions_y=" + Arrays.toString(positions_y) +
                ", type=" + type +
                '}';
    }
}
