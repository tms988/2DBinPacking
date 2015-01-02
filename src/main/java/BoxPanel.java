import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ben on 29/12/2014.
 */
public class BoxPanel extends JPanel {
    List<BoxDraw> boxDrawList = new ArrayList<BoxDraw>();

    public BoxPanel(List<BoxDraw> list) {
        super();
        this.boxDrawList = list;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);     // paint parent's background
        for(BoxDraw b : this.boxDrawList) {
            b.paint(g);
        }
    }

}
