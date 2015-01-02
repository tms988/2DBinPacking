import javax.swing.*;

import java.awt.*;
import java.util.Random;

/**
 * Created by ben on 29/12/2014.
 */
public class BoxDraw extends JComponent{
    int width;
    int height;
    int x;
    int y;
    Color color;

    public BoxDraw(int x, int y,int width, int height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;

        this.color = getRandomColor();
    }

    Color getRandomColor() {
        int red = new Random().nextInt(255);
        int green = new Random().nextInt(255);
        int blue = new Random().nextInt(255);

        return new Color(red, green, blue);
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);

        Color c = g.getColor();
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.black);


        String dim = "" + width + "x" + height;
        int stringLen = (int) g.getFontMetrics().getStringBounds(dim, g).getWidth();
        int start = width/2 - stringLen/2;
        g.drawString(dim, start + x,  y+height/2+4);
        g.drawRect(x, y, width, height);
        g.setColor(c);
    }

    @Override
    public void repaint() {

    }

    @Override
    public String toString() {
        return " {(" + x + ", " + y + "),(" + width + ", " + height + ")} ";
    }

}
