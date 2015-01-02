import java.io.IOException;
import java.util.List;

/**
 * Created by ben on 29/12/2014.
 */
public class Slot {
    public static boolean verbose = false;

    Slot leftSon;
    Slot rightSon;

    BoxDraw item;

    int width;
    int height;
    int x;
    int y;
    int depth;

    public Slot(int depth) {this.depth = depth;}

    /**
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param depth
     */
    public Slot(int x, int y, int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.depth = depth;
    }

    int surface() {
        return width * height;
    }

    int usedSurface() {
        int surface = 0;
        if(item != null)
            surface = surface();

        if(leftSon != null)
            surface += leftSon.usedSurface();

        if(rightSon != null)
            surface += rightSon.usedSurface();

        return surface;
    }

    boolean canFit(Box b) {
        return width >= b.width && height >= b.height;
    }

    boolean isOccupied() {
        return item != null;
    }

    Slot insert(Box b) {
        if(verbose) System.err.println("SLOT " + this + " WITH BOX : " + b);
        if (leftSon != null && rightSon != null) {
            Slot newSlot = leftSon.insert(b);
            if (newSlot != null) {
                if(verbose) System.err.println("LEFT DISPO");
                return newSlot;
            }
            if(verbose) System.err.println("RIGHT DISPO");
            return rightSon.insert(b);
        } else {
            if (isOccupied()) {
                if(verbose) System.err.println("SLOT FULL");
                return null;
            }
            if (!canFit(b)) {
                if(verbose) System.err.println(b + " CAN NOT FIT IN " + this);
                return null;
            }
            if (perfectlyFit(b)) {
                if(verbose) System.err.println(b + " PERFECTLY FIT IN " + this);
                item = new BoxDraw(x, y ,b.width, b.height);
                return this;
            }
            double dw = width - b.width;
            double dh = height - b.height;

            double dwdh = dw-dh;
            if (dw <= dh) {
                if(verbose) System.err.println("SPLIT IS HORIZONTAL");
                leftSon = new Slot(x, y, width, b.height, ++depth);
                rightSon = new Slot(x, y + b.height, width, (int)dh, ++depth);

            } else {
                if(verbose) System.err.println("SPLIT IS VERTICAL");
                leftSon = new Slot(x, y,  b.width, height, ++depth);
                rightSon = new Slot( x+b.width, y, width - b.width, height,++depth);
            }
            if(verbose) {
                System.err.println("LEFT SON : " + leftSon);
                System.err.println("RIGHT SON : " + rightSon);
            }

            return leftSon.insert(b);
        }

    }

        /*
        if(canFit(b)) {
            BoxDraw newBox = new BoxDraw(b.width, b.height, x, y);
            leftSon = new Slot(width, b.height, x, y, depth++);
            rightSon = new Slot(width, height - b.height, x, y + b.height, depth ++);

            leftSon.insert(b);
        }
        else if(leftSon.canFit(b)) {

        }
        else if(rightSon.canFit(b)) {

        }
        else {

        }
        */


    public List<BoxDraw> addBoxDraw(List<BoxDraw> l) {
        if(item != null)
        l.add(this.item);

        if(leftSon != null)
        l = leftSon.addBoxDraw(l);

        if(rightSon != null)
        l = rightSon.addBoxDraw(l);

        return l;
    }

    boolean perfectlyFit(Box b) {
        return width == b.width && height == b.height;
    }

    @Override
    public String toString() {
        return " {(" + x + ", " + y + "),(" + width + ", " + height + ")} ";
    }
}
