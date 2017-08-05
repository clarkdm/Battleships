package Battleships;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by dmclark on 24/07/17.
 */
public class Ship {

    int health = 0;
    ArrayList<Xy> positions = new ArrayList<Xy>();

    char type;

    int patrol_boat = 2;
    int battleships = 3;
    int submarine = 3;
    int destroyer = 4;
    int carrier = 5;

    public Ship(char type) {


        this.type = type;


    }

    public boolean add(Xy xy) {
        positions.add(new Xy(xy.getX(), xy.getY()));
        return true;
    }

    public boolean is_Alive_() {
        if (!positions.isEmpty()) {
            return true;
        }
        return false;
    }

    public char getType() {
        return type;
    }

    public boolean hit(Xy xy) {
        for (int i = 0; i < positions.size(); i++) {
            if (positions.get(i).equals(xy)) {
                positions.remove(i);

                return true;

            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Battleships.Ship{" +
                "health=" + health +
                ", positions_x=" + positions +
                ", type=" + type +
                '}';
    }
}
