package d14124700;

import ie.tudublin.Visual;

public class Visualizer extends Visual {
    public void settings() {
        size(1000, 1000);
    }

    public void setup() {

    }

    public void keyPressed() {

    }


    float ang;

    public void draw() {
        background(0);
        colorMode(HSB);

        float circle_x = width / 2;
        float circle_y = height / 2;
        float circle_width = 500;

        noFill();
        stroke(255, 255, 255);
        ellipse(circle_y, circle_y, circle_width, circle_width);
        stroke(100, 255, 255);

        translate(circle_x, circle_y);
        rotate(ang);

        int lines = 100;
        float angle = TWO_PI / (float) lines;
        for (int i = 0; i < lines; i++) {
            float x1 = (circle_width) / 2 * sin(angle * i);
            float y1 = (circle_width) / 2 * cos(angle * i);
            float x2 = (circle_width + 100) / 2 * sin(angle * i);
            float y2 = (circle_width + 100) / 2 * cos(angle * i);
            line(x1,y1,x2,y2);
        }

        ang += PI / 500;
    }
}
