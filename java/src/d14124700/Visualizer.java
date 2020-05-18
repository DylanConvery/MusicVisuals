package d14124700;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class Visualizer extends Visual {
    float circle_x;
    float circle_y;
    float circle_width;
    float circle_radius;

    int spines = 200;
    float[] lerpedSamples = new float[spines];

    float x1;
    float y1;
    float x2;
    float y2;

    public void settings() {
        // size(1000, 1000);
        fullScreen();
    }

    public void setup() {
        circle_x = width / 2;
        circle_y = height / 2;
        circle_width = width / 5;
        circle_radius = circle_width / 2f;
        startMinim();
        loadAudio("heroplanet.mp3");
        getAudioPlayer().play();
        println(getSmoothedBands().length);
    }

    public void keyPressed() {

    }

    float angle = 0;

    public void draw() {
        background(0);
        colorMode(HSB);

        calculateAverageAmplitude();
        calculateFrequencyBands();
        try {
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }

        strokeWeight(5);
        float theta = TWO_PI / (float) spines;
        float[] bands = getSmoothedBands();
        // println("x1 : " + x1 + "\ny1 : " + y1 + "\nx2 : " + x2 + "\ny2 : " + y2);

        translate(circle_x, circle_y);
        rotate(angle);
        pushMatrix();

        for (int i = 0; i < spines; i++) {
            stroke(map(i, 0, spines, 0, 255), 255, 255);
            float band = bands[i % bands.length];

            x1 = (circle_radius * sin(i * theta));
            y1 = (circle_radius * cos(i * theta));

            x2 = ((circle_radius + band * 0.2f) * sin(i * theta));
            y2 = ((circle_radius + band * 0.2f) * cos(i * theta));


            line(x1, y1, x2, y2);

        }
        
        popMatrix();

        noFill();
        stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        strokeWeight(5);
        ellipse(0,0, circle_width - 50, circle_width - 50);

        angle +=getSmoothedAmplitude() / 10.0f;
    }
}

// calculateAverageAmplitude();
// try {
// calculateFFT();
// } catch (VisualException e) {
// e.printStackTrace();
// }

// float cgap = 255 / 100 + 1;

// background(0);
// colorMode(HSB);
// strokeWeight(5);

// pushMatrix();

// stroke(100, 255, 255);
// translate(circle_x, circle_y);
// rotate(ang);

// int lines = 100;
// float angle = TWO_PI / (float) lines;
// ang += getSmoothedAmplitude() / 8.0f;

// for (int i = 0; i < lines; i++) {
// float c = ((i + offs) * cgap) % 255;
// stroke(c, 255, 255);

// float x1 = (circle_width) / 2 * sin(angle * i);
// float y1 = (circle_width) / 2 * cos(angle * i);
// float x2 = (circle_width + 100) / 2 * sin(angle * i);
// float y2 = (circle_width + 100) / 2 * cos(angle * i);
// line(x1, y1, x2, y2);
// }

// popMatrix();

// strokeWeight(20);
// noFill();
// stroke(255, 255, 255);
// ellipse(circle_y, circle_y, circle_width, circle_width);
