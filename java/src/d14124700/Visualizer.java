package d14124700;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class Visualizer extends Visual {
    float offs = 0;
    float circle_x;
    float circle_y;
    float circle_width = 500;
    float ang = 0;

    public void settings() {
        size(1000, 1000);
    }

    public void setup() {
        circle_x = width / 2;
        circle_y = height / 2;
        startMinim();
        loadAudio("heroplanet.mp3");
        getAudioPlayer().play();
    }

    public void keyPressed() {

    }

    public void draw() {
        calculateAverageAmplitude();
        try {
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }

        float cgap = 255 / 100 + 1;

        background(0);
        colorMode(HSB);
        strokeWeight(5);

        pushMatrix();

        stroke(100, 255, 255);
        translate(circle_x, circle_y);
        rotate(ang);

        int lines = 100;
        float angle = TWO_PI / (float) lines;
        ang += getSmoothedAmplitude() / 8.0f;

        for (int i = 0; i < lines; i++) {
            float c = ((i + offs) * cgap) % 255;
            stroke(c, 255, 255);

            float x1 = (circle_width) / 2 * sin(angle * i);
            float y1 = (circle_width) / 2 * cos(angle * i);
            float x2 = (circle_width + 100) / 2 * sin(angle * i);
            float y2 = (circle_width + 100) / 2 * cos(angle * i);
            line(x1, y1, x2, y2);
        }

        popMatrix();

        strokeWeight(20);
        noFill();
        stroke(255, 255, 255);
        ellipse(circle_y, circle_y, circle_width, circle_width);

    }
}