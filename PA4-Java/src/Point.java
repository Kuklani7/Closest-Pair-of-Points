import java.io.Serializable;

public class Point implements Serializable {
    public Integer _x;  // x coordinate
    public Integer _y;  // y coordinate

    public Point(Integer x, Integer y) {
        _x = x;
        _y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point compare = (Point) obj;
        return _x.equals(compare._x) && _y.equals(compare._y);
    }

    @Override
    public int hashCode() {
        return 31 * _x.hashCode() + _y.hashCode();
    }
}
