package example;

import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.*;

public class Sound1 extends PApplet {
    Minim minim;
    AudioInput ai;

    FFT fft;

    int frameSize = 1024;
    int sampleRate = 44100;

    public void settings() {
        size(1024, 500);
    }

    public void setup() {
        minim = new Minim(this);
        ai = minim.getLineIn(Minim.MONO, width, 44100, 16);

        colorMode(HSB);
        fft = new FFT(frameSize, sampleRate);

    }

    public void draw() {
        background(0);
        // drawPie();
        drawSamples();
    }

    void drawSamples() {
        float cy = height / 2;
        for (int i = 0; i < ai.bufferSize(); i++) {
            stroke(map(i, 0, ai.bufferSize(), 0, 255), 255, 255);
            line(i, cy, i, cy + ai.left.get(i) * cy);
        }
    }

    void reactiveEllipse(){
		float cx = width / 2;
		float cy = height / 2;

		float average = 0;
		for (int i = 0; i < ai.bufferSize(); i++) {
			average += abs(ai.left.get(i));
		}
		average /= ai.bufferSize();
		float ellipse_width = average * 1000;


		noStroke();
		fill(
			map(average, 0, 1, 0, 255)
			, 255
			, 255
        );
    }
		//ellipse(400 , cy,w, w);
		//ellipse(600 , cy,lerpedw, lerpedw);

    void drawPie() {
        float cx = width / 2;
        float cy = height / 2;
        float w = width * 0.2f;

        for (int i = 0; i < ai.bufferSize(); i++) {
            stroke(map(i, 0, ai.bufferSize(), 0, 255), 255, 255);
            line(i, cy, i, cy + ai.left.get(i) * cy);
        }

        // ellipse(100, circy, 50, 50);
        // ellipse(200, lerpedcircley, 50, 50);

        fft.window(FFT.HAMMING);
        fft.forward(ai.left);

        stroke(255);
        for (int i = 0; i < fft.specSize(); i++) {
            line(i, 0, i, fft.getBand(i) * 100);
        }

    }

}
