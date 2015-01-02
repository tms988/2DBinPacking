/**
 * Created by ben on 29/12/2014.
 */
public class Container {
    int width;
    int height;

    public Container(int width, int height) {
        this.width = width;
        this.height = height;
    }
    @Override
    public String toString() {
        return "(" + width + ", " + height + ")";
    }
}
