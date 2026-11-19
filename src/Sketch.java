import processing.core.PApplet;

public class Sketch extends PApplet {
    private static final int LIGHT_X = 510;
    private static final int RED_LIGHT_Y = 55;
    private static final int LIGHT_SPACING = 60;
    private static final int YELLOW_LIGHT_Y = RED_LIGHT_Y + LIGHT_SPACING;
    private static final int GREEN_LIGHT_Y = YELLOW_LIGHT_Y + LIGHT_SPACING;
    private static final int LIGHT_DIAMETER = 50;
    private static final int LIGHT_RADIUS = LIGHT_DIAMETER / 2;
    private static final int LIGHT_BOX_TOP = 20;
    private static final int LIGHT_BOX_WIDTH = 70;
    private static final int LIGHT_BOX_HEIGHT = 190;
    private static final int LIGHT_BOX_CORNER_RADIUS = 10;
    private static final int LIGHT_POLE_WIDTH = 10;
    private static final int LIGHT_POLE_TOP = LIGHT_BOX_TOP + LIGHT_BOX_HEIGHT;
    private static final int LIGHT_POLE_HEIGHT = 90;

    private static final String RED = "red";
    private static final String YELLOW = "yellow";
    private static final String GREEN = "green";

    // Initial state variables of the traffic light
    String state; // 
    float carX; // X-coordinate of the car
    int skyBlue; // Color variable for the sky background

    // The main method is the entry point of the program
    public void settings() {
        size(600, 400);
    }

    // The setup method is called once when the program starts
    public void setup() {
        state = GREEN;
        carX = 40;
        skyBlue = color(185, 220, 250);
    }

    // The draw method is called repeatedly to render the scene
    public void draw() {
        background(skyBlue);
        drawRoad();
        drawLight();
        drawCar(carX, 305);
    }

    public void drawRoad() {
        noStroke();
        fill(70);
        rect(0, 300, 600, 100);
        fill(255, 210, 0);
        for (int x = 0; x < 600; x += 60) {
            rect(x, 357, 30, 6);
        }
    }

    public void drawLight() {
        stroke(0);
        fill(60);
        rect(LIGHT_X - LIGHT_POLE_WIDTH / 2, LIGHT_POLE_TOP,
                LIGHT_POLE_WIDTH, LIGHT_POLE_HEIGHT); // pole
        rect(LIGHT_X - LIGHT_BOX_WIDTH / 2, LIGHT_BOX_TOP,
                LIGHT_BOX_WIDTH, LIGHT_BOX_HEIGHT, LIGHT_BOX_CORNER_RADIUS); // box

        // red
        if (state.equals(RED)) {
            fill(230, 40, 40); 
        } else { 
            fill(90, 30, 30); 
        }
        ellipse(LIGHT_X, RED_LIGHT_Y, LIGHT_DIAMETER, LIGHT_DIAMETER);

        // yellow
        if (state.equals(YELLOW)) {
            fill(250, 210, 40); 
        } else { 
            fill(90, 80, 20); 
        }
        ellipse(LIGHT_X, YELLOW_LIGHT_Y, LIGHT_DIAMETER, LIGHT_DIAMETER);

        // green
        if (state.equals(GREEN)) {
            fill(60, 200, 80); 
        } else { 
            fill(25, 75, 35); 
        }
        ellipse(LIGHT_X, GREEN_LIGHT_Y, LIGHT_DIAMETER, LIGHT_DIAMETER);
    }

    public void drawCar(float x, float y) {
        stroke(0);
        fill(30, 90, 200);
        rect(x + 25, y - 25, 65, 30, 8);   // roof
        rect(x, y, 120, 35, 8);            // body
        fill(20);
        ellipse(x + 25, y + 35, 28, 28);   // back wheel
        ellipse(x + 95, y + 35, 28, 28);   // front wheel
    }

    public void mousePressed() {
        // Check if the mouse was pressed on the red light
        double d = calculateDistance(mouseX, mouseY, LIGHT_X, RED_LIGHT_Y);
        if (d <= LIGHT_RADIUS) {
            state = RED;
        }
        // Check if the mouse was pressed on the yellow light
        d = calculateDistance(mouseX, mouseY, LIGHT_X, YELLOW_LIGHT_Y);
        if (d <= LIGHT_RADIUS) {
            state = YELLOW;
        }
        // Check if the mouse was pressed on the green light
        d = calculateDistance(mouseX, mouseY, LIGHT_X, GREEN_LIGHT_Y);
        if (d <= LIGHT_RADIUS) {
            state = GREEN;
        }

    }

    public double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}