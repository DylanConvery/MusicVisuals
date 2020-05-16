package example;

import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.FFT;

public class Sound2 extends PApplet
{	
	Minim minim;
	AudioSample as;

	int frameSize = 1024;

	int sampleRate = 44100;

	FFT fft;

	float frameToSecond = sampleRate / (float) frameSize;

	public void settings()
	{
		size(1024, 500);
	}


	public void setup() 
	{
		minim = new Minim(this);
		as = minim.loadSample("scale.wav", frameSize);
		
		fft = new FFT(frameSize, sampleRate); 

		colorMode(HSB);
	}

	int countZeroCrossings()
	{
		int count = 0;

		for(int i = 1 ; i < as.bufferSize() ; i ++)
		{
			if (as.left.get(i-1) > 0 && as.left.get(i) <= 0)
			{
				count ++;
			}
		}
		return count;
	}

	float lerpedw = 0;
	float average = 0;

	public void keyPressed()
	{
		if (key == ' ')
		{
			as.stop();
			as.trigger();
		}

	}

	float offs = 0;
	
	public void draw()
	{	
		background(0);		
		stroke(255);
		float cy = height / 2;
		float sum = 0;
		for(int i = 0 ; i < as.bufferSize() ; i ++)
		{
			stroke(
				map(i, 0, as.bufferSize(), 0, 255)
				, 255
				, 255
			);
			line(i, cy, i, cy + as.left.get(i) * cy);
			sum += abs(as.left.get(i));
		}
		average = sum / as.bufferSize();

		float w  = average * 1000;
		lerpedw = lerp(lerpedw, w, 0.1f);
		noStroke();
		fill(
			map(average, 0, 1, 0, 255)
			, 255
			, 255
		);
		//ellipse(400 , cy,w, w);
		//ellipse(600 , cy,lerpedw, lerpedw);	
		
		int count = countZeroCrossings();

		float freq = count * frameToSecond;
		textSize(22);
		text("Zero crossings frequency: " + freq, 100, 50);

		fft.window(FFT.HAMMING);
		fft.forward(as.left);

		stroke(255);
		int highestBin = 0;
		for(int i = 0 ; i < fft.specSize() ; i ++)
		{
			line(i, 0, i, fft.getBand(i) * 100);
			if (fft.getBand(i) > fft.getBand(highestBin))
			{
				highestBin = i;
			}
		}

		float freq1 = fft.indexToFreq(highestBin);

		fill(255);
		text("FFT Freq: " + freq1, 100, 100);

	}
}
