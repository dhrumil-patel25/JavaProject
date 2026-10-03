import java.awt.Frame;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MousePressPosition extends Frame {
    private int mouseX = -1;
    private int mouseY = -1;

    public MousePressPosition() {
        setTitle("Mouse Press Position Viewer");
        setSize(500, 400);
        setLayout(null);

        // Add Mouse Listener to track mouse presses
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                // Request a repaint to update the displayed coordinates
                repaint();
            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }
    public void paint(Graphics g) {
        super.paint(g);
        
        g.setFont(new Font("SansSerif", Font.BOLD, 16));

        if (mouseX != -1 && mouseY != -1) {
            String text = "Mouse Pressed at: X = " + mouseX + ", Y = " + mouseY;
            
            // Draw a small dot at the clicked location
            g.fillOval(mouseX - 4, mouseY - 4, 8, 8);
            
            // Draw text output at the top of the window
            g.drawString(text, 30, 70);
        } else {
            g.drawString("Click anywhere inside the window...", 30, 70);
        }
    }

    public static void main(String[] args) {
        MousePressPosition frame = new MousePressPosition();
        frame.setVisible(true);
    }
}