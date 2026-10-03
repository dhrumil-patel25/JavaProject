import java.awt.*;
import java.awt.event.*;

public class MultiplicationTableFrame extends Frame {

    private int number = 5; // Default number for the multiplication table
    private int limit = 10; // Table multiplier limit

    public MultiplicationTableFrame(int number) {
        this.number = number;

        // Window setup
        setTitle("Multiplication Table of " + number);
        setSize(350, 400);
        setBackground(Color.WHITE);

        // Window closing event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // Overridden paint method to print/draw text onto the Frame canvas
    
    public void paint(Graphics g) {
        super.paint(g);

        // Styling settings
        g.setFont(new Font("Monospaced", Font.BOLD, 16));
        g.setColor(Color.BLUE);

        // Header
        g.drawString("Multiplication Table of " + number, 50, 70);
        g.drawLine(50, 80, 280, 80);

        // Reset color for body
        g.setColor(Color.BLACK);

        // Render each row of the table
        int startY = 110;
        int rowSpacing = 25;

        for (int i = 1; i <= limit; i++) {
            String line = String.format("%2d  x  %2d  =  %3d", number, i, (number * i));
            g.drawString(line, 60, startY + (i * rowSpacing));
        }
    }

    public static void main(String[] args) {
        // Change number here to generate a different table
        new MultiplicationTableFrame(7);
    }
}