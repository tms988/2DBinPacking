/**
 * Created by ben on 29/12/2014.
 */
public class Box implements Comparable {

    int width;
    int height;

    public Box(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int surface() {
        return width * height;
    }

    @Override
    public int compareTo(Object o) {
        Box b = (Box) o;
        if(surface() > b.surface()) {
            return -1;
        }
        else if(surface() < b.surface()) {
            return 1;
        }
        else {
            return 0;
        }

        //IMPORTANCE DE CE CRITERE !!
        //  return width > b.width ? -1 : 1;
    }

    @Override
    public boolean equals(Object o) {
        Box b = (Box) o;
        return width == b.width && height == b.height;
    }


    @Override
    public Box clone() {
        return new Box(width, height);
    }
    @Override
    public String toString() {
        return "(" + width + ", " + height + ")";
    }
}
