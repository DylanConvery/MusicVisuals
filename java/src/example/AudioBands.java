package example;

import processing.core.PApplet;
import ddf.minim.*;

import ddf.minim.analysis.*;

public class AudioBands extends PApplet {
	Minim minim;
	AudioSample as;

	int frameSize = 1024;
	int sampleRate = 44100;

	float[] bands;
	float[] lerpedBands;

	FFT fft;

	float log2(float f) {
		return log(f) / log(2.0f);
	}

	void getFrequencyBands() {
		for (int i = 0; i < bands.length; i++) {
			int start = (int) pow(2, i) - 1;
			int w = (int) pow(2, i);
			int end = start + w;
			float average = 0;
			for (int j = start; j < end; j++) {
				average += fft.getBand(j) * (j + 1);
			}
			average /= (float) w;
			bands[i] = average * 5.0f;
			lerpedBands[i] = lerp(lerpedBands[i], bands[i], 0.05f);
		}
	}

	public void settings() {
		size(1024, 500);
	}

	public void setup() {
		minim = new Minim(this);
		as = minim.loadSample("heroplanet.mp3", frameSize);
		colorMode(HSB);

		fft = new FFT(frameSize, sampleRate);

		bands = new float[(int) log2(frameSize)];
  		lerpedBands = new float[bands.length];
	}

	float lerpedw = 0;
	float average = 0;

	public void keyPressed() {
		if (key == ' ') {
			as.stop();
			as.trigger();
		}

	}

	float offs = 0;

	public void draw() {
		background(0);
		fft.window(FFT.HAMMING);
		fft.forward(as.left);

		getFrequencyBands();

		float gap = width / (float) bands.length;
		noStroke();
		float colorGap = 255 / (float) bands.length;
		for (int i = 0; i < bands.length; i++) {
			fill(i * colorGap, 255, 255);
			rect(i * gap, height, gap, -lerpedBands[i]);
		}
	}
}
