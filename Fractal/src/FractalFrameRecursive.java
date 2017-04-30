
import javax.swing.*;
import java.awt.*;


public class FractalFrameRecursive extends JFrame {

    private final JPanel panel;
    private final int x = 250; // width
    private static int n = 3; // command line args
 public static void main(String[] args) {
        FractalFrameRecursive frame = new FractalFrameRecursive();
        
        try {
            n = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        frame.setTitle(n + " Fractal");
        frame.setVisible(true);
    }
    public FractalFrameRecursive() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 800);

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
        int count = 1;
        g.fillRect(w, h, x, x);
        if (n > 0) {
            draw(g, w, h, x, count);
        }
    }

    private void draw (Graphics g, int w, int h, int size, int n) {

        if (n == this.n) {
            return;
        }
        n++;
//top
        g.fillRect(w + (size / 3), h - (2 * size / 3), size / 3, size / 3);
        draw(g, w + (size / 3), h - (2 * size / 3), size / 3, n);
//top-left

        g.fillRect(w - (2 * size / 3), h - (2 * size / 3), size / 3, size / 3);
        draw(g, w - (2 * size / 3), h - (2 * size / 3), size / 3,  n);
        //top-right 
        g.fillRect(w + (4 * size / 3), h - (2 * size / 3), size / 3, size / 3);
        draw(g, w + (4 * size / 3), h - (2 * size / 3), size / 3,  n);
//left
        g.fillRect(w - (2 * size / 3), h + (size / 3), size / 3, size / 3);
        draw(g, w - (2 * size / 3), h + (size / 3), size / 3,  n);
//right
        g.fillRect(w + size + (size / 3), h + (size / 3), size / 3, size / 3);
        draw(g, w + size + (size / 3), h + (size / 3), size / 3,  n);
        //bottom
        g.fillRect(w + (size / 3), h + (4 * size / 3), size / 3, size / 3);
        draw(g, w + (size / 3), h + (4 * size / 3), size / 3,  n);
        //left bot
        g.fillRect(w - (2 * size / 3), h + (4 * size / 3), size / 3, size / 3);
        draw(g, w - (2 * size / 3), h + (4 * size / 3), size / 3,  n);
        //right bot
        g.fillRect(w + size + (size / 3), h + (4 * size / 3), size / 3, size / 3);
        draw(g, w + size + (size / 3), h + (4 * size / 3), size / 3, n);

    }
}
