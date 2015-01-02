import java.io.*;
import java.util.*;

/**
 * Created by ben on 28/12/2014.
 */
public class Parser {

    static String filePath = "";
    static BufferedReader in;
    /*
        The file to parse has two lines :
        the first line contains the dimensions of containers :
            WIDTHxHEIGHT

        The second line contains the dimensions of each boxes as a list
        where each box description is separated by a ',' and SPACE
            W1xH1, W2xH2, ...
     */

    /**
     * This method will parse the file specified by the given name
     * @return
     *          A list of boxes. The first element is a container and describe
     *          the containers dimensions
     * @throws java.io.FileNotFoundException
     */
    public static List<Box> getBoxes() {
        List<Box> result = new ArrayList<Box>();
        try {
            //  Get the boxes description
            String boxesDimensions = in.readLine();

            //  Build a list which describe each box
            List<String> listBoxesDimensions = new ArrayList<String>(Arrays.asList(boxesDimensions.split(",")));

            // System.out.println("Boxes : "  + listBoxesDimensions);

            //  For each box description, build a box
            for(String currentBoxeDimensions : listBoxesDimensions) {
                //  Delete whitespaces
                currentBoxeDimensions = currentBoxeDimensions.trim();
                String[] split = currentBoxeDimensions.split("x");
                if(split.length == 2)
                result.add(new Box(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    static void defineFilePath(String filePath) throws FileNotFoundException {
        Parser.filePath = filePath;
        in = new BufferedReader(new FileReader(filePath));
    }

    static List<Box> convert(List<BoxDraw> boxDraws) {
        List<Box> boxes = new ArrayList<Box>();

        for(BoxDraw b : boxDraws) {
            boxes.add(new Box(b.width, b.height));
        }
        Collections.sort(boxes);
        return boxes;
    }

    static List<Box> diff(List<Box> actual, List<Box> expected) {
        Set<Box> setA = new HashSet<Box>(actual);
        Set<Box> setB = new HashSet<Box>(expected);

        setA.retainAll(setB);
        System.err.println("setA " + setA);

        List<Box> res = new ArrayList<Box>();

        List<Box> cloneA = new ArrayList<Box>(actual);
        int size = (actual.size() > expected.size() ? actual.size() :expected.size());



        actual.removeAll(expected);
        return actual;
    }

    static Container getContainer() {
        Container c = null;
        try {
            //  Get the container dimensions <=> the first line
            String containerDimensions = in.readLine();

            String[] split = containerDimensions.split("x");

            c = new Container(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return c;
    }

}
