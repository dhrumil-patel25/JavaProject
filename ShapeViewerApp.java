import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ShapeViewerApp extends Frame implements ItemListener {
    private CheckboxGroup shapeGroup;
    private Checkbox chkRectangle, chkOval, chkLine, chkFilledRect;
    private ShapeCanvas canvas;

    public ShapeViewerApp() {
        setTitle("Shape Viewer on Canvas");
        setSize(500, 450);
        setLayout(new BorderLayout());

        // Create CheckboxGroup (radio buttons) for mutually exclusive options
        shapeGroup = new CheckboxGroup();
        chkRectangle = new Checkbox("Rectangle", shapeGroup, true); // Selected by default
        chkOval = new Checkbox("Oval", shapeGroup, false);
        chkLine = new Checkbox("Line", shapeGroup, false);
        chkFilledRect = new Checkbox("Filled Rectangle", shapeGroup, false);

        // Attach ItemListeners to respond immediately on selection change
        chkRectangle.addItemListener(this);
        chkOval.addItemListener(this);
        chkLine.addItemListener(this);
        chkFilledRect.addItemListener(this);

        // Top Control Panel
        Panel controlPanel = new Panel();
        controlPanel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        controlPanel.add(chkRectangle);
        controlPanel.add(chkOval);
        controlPanel.add(chkLine);
        controlPanel.add(chkFilledRect);

        // Initialize custom Canvas
        canvas = new ShapeCanvas();

        // Add components to Frame layout
        add(controlPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);

        // Clean window exit
        addWindowListener(new WindowAdapter() {
         
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    
    public void itemStateChanged(ItemEvent e) {
        // Retrieve label of the currently selected checkbox
        String selectedShape = shapeGroup.getSelectedCheckbox().getLabel();
        
        // Pass selected shape to the canvas and request redrawing
        canvas.setSelectedShape(selectedShape);
    }

    public static void main(String[] args) {
        ShapeViewerApp app = new ShapeViewerApp();
        app.setVisible(true);
    }
}

// Custom Canvas class responsible for drawing shapes
class ShapeCanvas extends Canvas {
    private String selectedShape = "Rectangle"; // Default shape

    public ShapeCanvas() {
        setBackground(Color.WHITE);
    }

    public void setSelectedShape(String shape) {
        this.selectedShape = shape;
        repaint(); // Re-render the canvas when shape changes
    }

 
    public void paint(Graphics g) {
        super.paint(g);

        // Use custom color and thickness/position logic
        g.setColor(new Color(40, 90, 180));

        int width = getWidth();
        int height = getHeight();
        int shapeWidth = 200;
        int shapeHeight = 150;
        int x = (width - shapeWidth) / 2;
        int y = (height - shapeHeight) / 2;

        switch (selectedShape) {
            case "Rectangle":
                g.drawRect(x, y, shapeWidth, shapeHeight);
                break;

            case "Oval":
                g.drawOval(x, y, shapeWidth, shapeHeight);
                break;

            case "Line":
                g.drawLine(x, y + shapeHeight, x + shapeWidth, y);
                break;

            case "Filled Rectangle":
                g.fillRect(x, y, shapeWidth, shapeHeight);
                break;
        }
    }
}