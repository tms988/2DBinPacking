
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by ben on 28/12/2014.
 */
public class Main {

    private long elapsedTime;
    int containerWidth;
    int containerHeight;
    List<Box> boxesDescription = new ArrayList<Box>();

    int nb_containers = 0;
    private int nb_rows = 0;

    private List<BoxPanel> containersFull = new ArrayList<BoxPanel>();

    public static void main(String[] args) throws IOException {
        new Main();
        //Randomizer.printRandomBoxes(26000, 10, 300);
    }

    public void init() throws FileNotFoundException {
                /*
        JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(true);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.showOpenDialog(null);
        if(fc.getSelectedFile() == null) return;
        */

        //found.Parser.parse(fc.getSelectedFile().getAbsolutePath());
        Parser.defineFilePath("C:\\Users\\ben\\Documents\\GitHub\\2DBinPacking\\src\\main\\resources\\f1.txt");

        Container containerDescription =  Parser.getContainer();
        containerWidth = containerDescription.width;
        containerHeight = containerDescription.height;

        boxesDescription = Parser.getBoxes();

       // System.err.println("Container : " + containerDescription);
        Collections.sort(boxesDescription);
       // System.err.println("List of boxes : " + boxesDescription);
    }

    public Main() throws IOException {
        init();

        long dateD = System.nanoTime();

        while(boxesDescription.size() != 0) {
            List<Box> boxesList = new ArrayList<Box>();

            Slot container = new Slot(0, 0, containerWidth, containerHeight, 0);

            for (int i = 0 ; i < boxesDescription.size() ; i ++) {
                Box b = boxesDescription.get(i);
                if (b.width <= containerWidth && b.height <= containerHeight) {
                    Slot s = container.insert(b);

                    //  Pas pu insere
                    if (s == null) {
                        boxesList.add(b);
                    }
                }
            }
            //System.in.read();

            boxesDescription.clear();
            for(Box b : boxesList) {
                boxesDescription.add(b.clone());
            }

            List<BoxDraw> boxDraws = container.addBoxDraw(new ArrayList<BoxDraw>());
            this.containersFull.add(new BoxPanel(boxDraws));
        }
        this.elapsedTime = TimeUnit.MILLISECONDS.convert(System.nanoTime() - dateD, TimeUnit.NANOSECONDS);
        drawContainers();


    }
    public void drawContainers() {

        JPanel mainPanel = new JPanel();
        int nb_container_width = (int) ((double) Toolkit.getDefaultToolkit().getScreenSize().width / (double)containerWidth);
        int nb_container_height =(int)((double)this.containersFull.size() / (double) nb_container_width) +1;
        System.out.println("WxH : " + nb_container_width + "x" + nb_container_height);
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new GridLayout(nb_container_height+1, nb_container_width));

        for(BoxPanel b : this.containersFull) {
            b.setPreferredSize(new Dimension(this.containerWidth + 2, this.containerHeight + 2));
            b.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            JPanel innerPanel = new JPanel();
            innerPanel.setBackground(Color.white);
            innerPanel.add(b);

            mainPanel.add(innerPanel);
        }


        JFrame mainFrame = new JFrame();
        Dimension screenComputerSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize(screenComputerSize);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLocation(0, 0);
        mainFrame.setTitle("Container : " + this.containerWidth + "x" + this.containerHeight + " - " + this.containersFull.size() + " boites utilisées - Temps de calcul : " + elapsedTime + "ms");
        mainFrame.getContentPane().add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

}
