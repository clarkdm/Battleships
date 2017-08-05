package Battleships;

import java.util.Objects;

/**
 * Created by dmclark on 05/08/17.
 */
public class Xy {
    private int x;
    private int y;

    public Xy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void add_XY(int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String out() {
        return x + "," + y;
    }

    @Override
    public String toString() {
        return "Xy{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        //if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Xy xy = (Xy) o;
        return getX() == xy.getX() &&
                getY() == xy.getY();
    }

    public boolean equals_2(Xy o) {
        if (x == o.getX() && y == o.getY()) {
            return true;

        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
