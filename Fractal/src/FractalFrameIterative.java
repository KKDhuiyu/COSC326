
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FractalFrameIterative extends JFrame {

    private final JPanel panel;
    private final int x = 243; // width
    private static int n = 2; // command line args
    private static ArrayList<Integer> widths = new ArrayList<>();
    private static ArrayList<Integer> heights = new ArrayList<>();
    private static ArrayList<Integer> sizes = new ArrayList<>();
    private static ArrayList<Integer> widths2 = new ArrayList<>();
    private static ArrayList<Integer> heights2 = new ArrayList<>();
    private static ArrayList<Integer> sizes2 = new ArrayList<>();

    public static void main(String[] args) {
        FractalFrameIterative frame = new FractalFrameIterative();

        try {
            n = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        frame.setTitle(n + " Fractal");
        frame.setVisible(true);
    }

    public FractalFrameIterative() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        panel = new JPanel();
        panel.setBackground(Color.BLUE);
        getContentPane().add(panel);

    }
/**
 * this method will be called 3 times for some unknown reasons.
 * @param g 
 */
    @Override
    public void paint(Graphics g) {
        // When it repaints, things drawn before are cleared.
        super.paint(g);
        g.setColor(Color.WHITE); // draw in white
        int w = (getWidth() / 2) - (x / 2);
        int h = (getHeight() / 2) - (x / 2);
        int nFractal = n;
        g.fillRect(w, h, x, x);
        while (nFractal > 0) {
            widths.clear();
            heights.clear();
            sizes.clear();
            for (int i = 0; i < widths2.size(); i++) {
                widths.add(widths2.get(i));
                heights.add(heights2.get(i));
                sizes.add(sizes2.get(i));
            }
            widths2.clear();
            heights2.clear();
            sizes2.clear(); // prevent infinent loop. 
            if (widths.isEmpty()) { // when draw the first 8 surroundings
                drawPoints(g, w, h, x);
                addPoints(w, h, x);
            } else {
                for (int i = 0; i < widths.size(); i++) {
                    addPoints(widths.get(i), heights.get(i), sizes.get(i));
                    drawPoints(g, widths.get(i), heights.get(i), sizes.get(i));
                }
            }
            nFractal--;
        }
        widths.clear();
        heights.clear();
        sizes.clear();
        widths2.clear();
        heights2.clear();
        sizes2.clear();
    }
/**
 * Add the coordinators of the 8 surrounding squares to the list.
 * @param w
 * @param h
 * @param size 
 */
    private void addPoints(int w, int h, int size) {

        widths2.add(w + (size / 3));
        heights2.add(h - (2 * size / 3));
        sizes2.add(size / 3);
        widths2.add(w - (2 * size / 3));
        heights2.add(h - (2 * size / 3));
        sizes2.add(size / 3);
        widths2.add(w + (4 * size / 3));
        heights2.add(h - (2 * size / 3));
        sizes2.add(size / 3);
        widths2.add(w - (2 * size / 3));
        heights2.add(h + (size / 3));
        sizes2.add(size / 3);
        widths2.add(w + size + (size / 3));
        heights2.add(h + (size / 3));
        sizes2.add(size / 3);
        widths2.add(w + (size / 3));
        heights2.add(h + (4 * size / 3));
        sizes2.add(size / 3);
        widths2.add(w - (2 * size / 3));
        heights2.add(h + (4 * size / 3));
        sizes2.add(size / 3);
        widths2.add(w + size + (size / 3));
        heights2.add(h + (4 * size / 3));
        sizes2.add(size / 3);

    }

    /**
     * draw a square and 8 surrounding according to width height and size of the
     * middle square.
     *
     * @param g graph
     * @param w width
     * @param h height
     * @param size size of the middle square.
     */
    private void drawPoints(Graphics g, int w, int h, int size) {
        g.fillRect(w, h, size, size);
        g.fillRect(w + (size / 3), h - (2 * size / 3), size / 3, size / 3);
        g.fillRect(w - (2 * size / 3), h - (2 * size / 3), size / 3, size / 3);
        g.fillRect(w + (4 * size / 3), h - (2 * size / 3), size / 3, size / 3);
        g.fillRect(w - (2 * size / 3), h + (size / 3), size / 3, size / 3);
        g.fillRect(w + size + (size / 3), h + (size / 3), size / 3, size / 3);
        g.fillRect(w + (size / 3), h + (4 * size / 3), size / 3, size / 3);
        g.fillRect(w - (2 * size / 3), h + (4 * size / 3), size / 3, size / 3);
        g.fillRect(w + size + (size / 3),
                h + (4 * size / 3), size / 3, size / 3);

    }

}
