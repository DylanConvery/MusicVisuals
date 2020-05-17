package d14124700;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class Visualizer extends Visual {
    float circle_x;
    float circle_y;
    float circle_width = 500;
    float circle_radius = circle_width / 2f;

    float angle = 0;
    int numSamples = 100;
    float[] lerpedSamples = new float[numSamples];
    float offs = 0;

    public void settings() {
        size(1000, 1000);
    }

    public void setup() {
        setFrameSize(1024);
        circle_x = width / 2;
        circle_y = height / 2;
        startMinim();
        loadAudio("heroplanet.mp3");
        getAudioPlayer().play();
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

        float theta = TWO_PI / (float) numSamples;
        float average = 0;

        strokeWeight(5);
        stroke(100, 255, 255);
        for (int i = 0; i < numSamples; i++) {
            lerpedSamples[i] = lerp(lerpedSamples[i], fft.getBand(i), 0.2f);


            stroke(map(i, 0, numSamples, 0, 255), 255, 255);

            float x1 = circle_x + (circle_radius * sin(i * theta));
            float y1 = circle_y - (circle_radius * cos(i * theta));
            float x2 = circle_x + ((lerpedSamples[i] * circle_radius) * sin(i * theta));
            float y2 = circle_y - ((lerpedSamples[i] * circle_radius) * cos(i * theta));
            println("x2 : " + x2 + "\ny2 : " + y2);
            line(x1, y1, x2, y2);
        }


        strokeWeight(20);
        stroke(255, 255, 255);
        noFill();
        // ellipse(circle_y, circle_y, circle_width, circle_width);

    }
}

// x = rsin(θ), y = rcos(θ).

// float x1 = (circle_width) / 2 * sin(angle * i);
// float y1 = (circle_width) / 2 * cos(angle * i);
// float x2 = (circle_width + 100) / 2 * sin(angle * i);
// float y2 = (circle_width + 100) / 2 * cos(angle * i);
