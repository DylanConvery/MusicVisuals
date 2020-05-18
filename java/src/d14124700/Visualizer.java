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
        //size(1000, 1000);
        fullScreen();
    }

    public void setup() {
        circle_x = width / 2;
        circle_y = height / 2;
        circle_width = width /5;
        circle_radius = circle_width / 2f;
        startMinim();
        loadAudio("heroplanet.mp3");
        getAudioPlayer().play();
        println(getSmoothedBands().length);
    }

    public void keyPressed() {

    }

    public void draw() {
        background(0);
        colorMode(HSB);
        try {
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }

        float theta = TWO_PI / (float) spines;
        strokeWeight(5);
        calculateFrequencyBands();
        float[] bands = getSmoothedBands();
        // println("x1 : " + x1 + "\ny1 : " + y1 + "\nx2 : " + x2 + "\ny2 : " + y2);

        for (int i = 0; i < spines; i++) {
            
            stroke(map(i, 0, spines, 0, 255), 255, 255);
            float band = bands[i % bands.length];

            x1 = circle_x + (circle_radius * sin(i * theta));
            y1 = circle_y - (circle_radius * cos(i * theta));

            x2 = circle_x + ((circle_radius + band * 0.2f) * sin(i * theta));
            y2 = circle_y - ((circle_radius + band * 0.2f) * cos(i * theta));

            line(x1, y1, x2, y2);
        }
    }
}