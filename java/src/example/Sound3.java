package example;

import processing.core.PApplet;
import ddf.minim.*;

public class Sound3 extends PApplet
{	
	Minim minim;
	AudioSample as;

	int frameSize = 1024;

	float frameToSecond = 44100 / (float) frameSize;

	public void settings()
	{
		size(1024, 500);
	}


	public void setup() 
	{
		minim = new Minim(this);
		as = minim.loadSample("heroplanet.mp3", frameSize);
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

	public void circleVisual()
	{
		strokeWeight(2);
		float cx = width / 2;
		float cy = height / 2;

		for(int i = 0 ; i < as.bufferSize() ; i ++)
		{
			float theta = map(i, 0, as.bufferSize(), 0, TWO_PI);
			float x = cx + sin(theta) * cx * abs(as.left.get(i));
			float y = cy + cos(theta) * cx * abs(as.left.get(i));
			stroke(
				map(i + offs, 0, as.bufferSize(), 0, 255) % 255
				,255
				,255
			);
			line(cx, cy, x, y);
		}
		offs += average * 100f;		
	}
	
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
			//line(i, cy, i, cy + ai.left.get(i) * cy);
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
		text(freq, 100, 50);

		circleVisual();
	}
}
