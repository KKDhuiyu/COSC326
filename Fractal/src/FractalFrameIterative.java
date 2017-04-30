
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FractalFrameIterative extends JFrame {

    private final JPanel panel;
    private final int x = 243; // width
    private static int n = 4; // command line args

    public static void main(String[] args) {
        FractalFrameIterative frame = new FractalFrameIterative();

        try {
            n = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        frame.setTitle(n + " Fractal");
        n = n - 1;
        frame.setVisible(true);
    }

    public FractalFrameIterative() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 900);
        panel = new JPanel();
        panel.setBackground(Color.BLUE);
        getContentPane().add(panel);

    }

    @Override
    public void paint(Graphics g) {
        // When it repaints, things drawn before are cleared.
        super.paint(g);
        g.setColor(Color.WHITE); // draw in white
        int w = (getWidth() / 2) - (x / 2);
        int h = (getHeight() / 2) - (x / 2);
        g.fillRect(w, h, x, x);
        drawTop(g, w, h, x);
        //drawTopLeft(g, w, h, x);
//drawBot(g, w, h, x);
    }

    private void drawTop(Graphics g, int wi, int he, int size) {
        ArrayList<Integer> widths = new ArrayList<>();
        ArrayList<Integer> heights = new ArrayList<>();
        ArrayList<Integer> sizes = new ArrayList<>();
         int si = size;
        int width = wi+ si / 3;
        int height = he- 2 * si / 3;
        int width2 = wi+ si / 3;
        int height2 = he- 2 * si / 3;
            si = si / 3;
            widths.add(width);
            heights.add(height);
            sizes.add(si);
        for (int i = n; i > 0; i--) {
            width = width + si / 3;
            height = height - 2 * si / 3;
            
            widths.add(width);
            heights.add(height);
            
            
            width2 = width2- 2 * si / 3;
            height2 = height2 - 2 * si / 3;
            widths.add(width2);
            heights.add(height2);
            si = si / 3;
            sizes.add(si);
            sizes.add(si);
        }

        for (int i = 0; i < widths.size(); i++) {
            int w = widths.get(i);
            int h = heights.get(i);
            int s = sizes.get(i);
            g.fillRect(w, h, s, s);
            g.fillRect(w - s * ((int) Math.pow(3, i + 1)), h, s, s);
            g.fillRect(w + s * ((int) Math.pow(3, i + 1)), h, s, s);
            g.fillRect(w - s * ((int) Math.pow(3, i + 1)), h + s * ((int) Math.pow(3, i + 1)), s, s);
            g.fillRect(w + s * ((int) Math.pow(3, i + 1)), h + s * ((int) Math.pow(3, i + 1)), s, s);
            g.fillRect(w, h + 2 * s * ((int) Math.pow(3, i + 1)), s, s);
            g.fillRect(w - s * ((int) Math.pow(3, i + 1)), h + 2 * s * ((int) Math.pow(3, i + 1)), s, s);
            g.fillRect(w + s * ((int) Math.pow(3, i + 1)), h + 2 * s * ((int) Math.pow(3, i + 1)), s, s);

        }
    }
     private void drawBot(Graphics g, int wi, int he, int size) {
        ArrayList<Integer> widths = new ArrayList<>();
        ArrayList<Integer> heights = new ArrayList<>();
        ArrayList<Integer> sizes = new ArrayList<>();
        int width = wi;
        int height = he;
        int si = size;
        width = width + si / 3;
            height = height - 2 * si / 3;
            si = si / 3;
            widths.add(width);
            heights.add(height);
            sizes.add(si);
        for (int i = n; i > 1; i--) {
            width = width- 2 * si / 3;
            height = height - 2 * si / 3;
            si = si / 3;
            widths.add(width);
            heights.add(height);
            sizes.add(si);
        }

        for (int i = 0; i < widths.size(); i++) {
            int w = widths.get(i);
            int h = heights.get(i);
            int s = sizes.get(i);
            g.fillRect(w, h, s, s);
            g.fillRect(w - s * ((int) Math.pow(3, i + 1)), h, s, s);
            g.fillRect(w + s * ((int) Math.pow(3, i + 1)), h, s, s);
            g.fillRect(w - s * ((int) Math.pow(3, i + 1)), h + s * ((int) Math.pow(3, i + 1)), s, s);
            g.fillRect(w + s * ((int) Math.pow(3, i + 1)), h + s * ((int) Math.pow(3, i + 1)), s, s);
            g.fillRect(w, h + 2 * s * ((int) Math.pow(3, i + 1)), s, s);
            g.fillRect(w - s * ((int) Math.pow(3, i + 1)), h + 2 * s * ((int) Math.pow(3, i + 1)), s, s);
            g.fillRect(w + s * ((int) Math.pow(3, i + 1)), h + 2 * s * ((int) Math.pow(3, i + 1)), s, s);

        }
    }

    private void drawTopLeft(Graphics g, int wi, int he, int size) {
        ArrayList<Integer> widths = new ArrayList<>();
        ArrayList<Integer> heights = new ArrayList<>();
        ArrayList<Integer> sizes = new ArrayList<>();
        wi = wi + size / 3;
        he = he - 2 * size / 3;
        size = size / 3;
        for (int i = n; i > 1; i--) {
            wi = wi + size / 3;
            he = he - 2 * size / 3;
            size = size / 3;
            widths.add(wi);
            heights.add(he);
            sizes.add(size);
        }
        for (int i = 0; i < widths.size(); i++) {
            int w = widths.get(i);
            int h = heights.get(i);
            int s = sizes.get(i);
            g.fillRect(w, h, s, s);
            g.fillRect(w - s * ((int) Math.pow(3, i + 1)), h, s, s);
            g.fillRect(w + s * ((int) Math.pow(3, i + 1)), h, s, s);
            g.fillRect(w - s * ((int) Math.pow(3, i + 1)), h + s * ((int) Math.pow(3, i + 1)), s, s);
            g.fillRect(w + s * ((int) Math.pow(3, i + 1)), h + s * ((int) Math.pow(3, i + 1)), s, s);
            g.fillRect(w, h + 2 * s * ((int) Math.pow(3, i + 1)), s, s);
            g.fillRect(w - s * ((int) Math.pow(3, i + 1)), h + 2 * s * ((int) Math.pow(3, i + 1)), s, s);
            g.fillRect(w + s * ((int) Math.pow(3, i + 1)), h + 2 * s * ((int) Math.pow(3, i + 1)), s, s);
        }
    }
//private void drawTopLeft(Graphics g, int wi, int he, int size) {
//   ArrayList<Integer> widths = new ArrayList<>();
//   ArrayList<Integer> heights = new ArrayList<>();
//   ArrayList<Integer> sizes = new ArrayList<>();
//   for (int i = n; i > 0; i--) {
//       he = he - (2 * size / 3);
//       wi = wi - 2 * size / 3;
//       size = size / 3;
//       widths.add(wi);
//       heights.add(he);
//       sizes.add(size);
//   }
//   for (int i = 0; i < widths.size(); i++) {
//       int w=widths.get(i);
//       int h=heights.get(i);
//       int s =sizes.get(i);
//       g.fillRect(w,h,s,s);
//       
//       g.fillRect(w, h+ s*((int)Math.pow(3, i+1)), s,s);
//       g.fillRect(w, h+ 2*s*((int)Math.pow(3, i+1)), s,s);
//       
//       g.fillRect(w + s*((int)Math.pow(3, i+1)), h, s,s);
//       g.fillRect(w + s*((int)Math.pow(3, i+1)), h+ 2*s*((int)Math.pow(3, i+1)), s,s); 
//       
//       g.fillRect(w + 2*s*((int)Math.pow(3, i+1)), h, s,s);
//       g.fillRect(w + 2*s*((int)Math.pow(3, i+1)), h+ s*((int)Math.pow(3, i+1)), s,s); 
//       g.fillRect(w + 2*s*((int)Math.pow(3, i+1)), h+ 2*s*((int)Math.pow(3, i+1)), s,s); 
//      
//      
//   }
//}

    private void draw(Graphics g, int w, int h, int size) {
        int s = size;
        int width = w;
        int hight = h;

        for (int j = n; j > 0; j--) {
            g.fillRect(w + (size / 3), h - (2 * size / 3), size / 3, size / 3);
            g.fillRect(w - (2 * size / 3), h - (2 * size / 3), size / 3, size / 3);
            g.fillRect(w + (4 * size / 3), h - (2 * size / 3), size / 3, size / 3);
            g.fillRect(w - (2 * size / 3), h + (size / 3), size / 3, size / 3);
            g.fillRect(w + size + (size / 3), h + (size / 3), size / 3, size / 3);
            g.fillRect(w + (size / 3), h + (4 * size / 3), size / 3, size / 3);
            g.fillRect(w - (2 * size / 3), h + (4 * size / 3), size / 3, size / 3);
            g.fillRect(w + size + (size / 3), h + (4 * size / 3), size / 3, size / 3);

            h = h - 2 * size / 3;
            w = w + size / 3;
            size = size / 3;
        }
        size = s;
        w = width;
        h = hight;
        for (int k = n; k > 0; k--) {
            g.fillRect(w + (size / 3), h - (2 * size / 3), size / 3, size / 3);
            g.fillRect(w - (2 * size / 3), h - (2 * size / 3), size / 3, size / 3);
            g.fillRect(w + (4 * size / 3), h - (2 * size / 3), size / 3, size / 3);
            g.fillRect(w - (2 * size / 3), h + (size / 3), size / 3, size / 3);
            g.fillRect(w + size + (size / 3), h + (size / 3), size / 3, size / 3);
            g.fillRect(w + (size / 3), h + (4 * size / 3), size / 3, size / 3);
            g.fillRect(w - (2 * size / 3), h + (4 * size / 3), size / 3, size / 3);
            g.fillRect(w + size + (size / 3), h + (4 * size / 3), size / 3, size / 3);

            h = h - (2 * size / 3);
            w = w - 2 * size / 3;
            size = size / 3;
        }

    }
}
