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
    // Constants for the traffic light colors
    private static final String RED = "red";
    private static final String YELLOW = "yellow";
    private static final String GREEN = "green";

    // Initial state variables of the traffic light
    String state; // The current state of the traffic light
    float carX; // X-coordinate of the car
    int skyBlue; // Color variable for the sky background
    int redOn, redOff, yellowOn, yellowOff, greenOn, greenOff; // Traffic light colors

    // The main method is the entry point of the program
    public void settings() {
        size(600, 400);
    }

    // The setup method is called once when the program starts
    public void setup() {
        carX = 40;
        skyBlue = color(185, 220, 250);
        state = GREEN;
        
        redOn = color(230, 40, 40);
        redOff = color(90, 30, 30);
        yellowOn = color(250, 210, 40);
        yellowOff = color(90, 80, 20);
        greenOn = color(60, 200, 80);
        greenOff = color(25, 75, 35);
    }

    // The draw method is called repeatedly to render the scene
    public void draw() {
        background(skyBlue);
        drawRoad();
        drawLight();
        // Put the light on a timer to change automatically after a certain period
        // This is a simple implementation and can be improved with more complex logic
        // For example, you could use a counter to track the time and change the state accordingly
        // For now, we'll just change the state every 3 seconds
        if (frameCount % 180 == 0) {
            if (state.equals(GREEN)) {
                state = YELLOW;
            } else if (state.equals(YELLOW)) {
                state = RED;
            } else if (state.equals(RED)) {
                state = GREEN;
            }
        }
        // Update the car's position based on the traffic light state
        // when carX exceeds the width of the canvas, reset it to the starting position
        if (carX > width) {
            carX = -120;
        }
        if (state.equals(GREEN)) {
            carX += 2; // Move the car to the right
        } else if (state.equals(YELLOW)) {
            carX += 1; // Move the car slowly
        } else if (state.equals(RED)) {
            carX = carX; // Stop the car
        }
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
            fill(redOn); 
        } else { 
            fill(redOff); 
        }
        ellipse(LIGHT_X, RED_LIGHT_Y, LIGHT_DIAMETER, LIGHT_DIAMETER);

        // yellow
        if (state.equals(YELLOW)) {
            fill(yellowOn); 
        } else { 
            fill(yellowOff); 
        }
        ellipse(LIGHT_X, YELLOW_LIGHT_Y, LIGHT_DIAMETER, LIGHT_DIAMETER);

        // green
        if (state.equals(GREEN)) {
            fill(greenOn); 
        } else { 
            fill(greenOff); 
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

    /*
     * Custom functions below 
     */

    // Custom method to calculate the distance between two points
    public double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    // set lights on
    public void setLight(String color) {
        state = color;
    }

}