package example;

import ddf.minim.AudioOutput;
import ddf.minim.Minim;
import ddf.minim.ugens.Oscil;
import ddf.minim.ugens.Waves;
import processing.core.PApplet;

public class SoundSynthesis extends PApplet
{

    float[] frequencies = {293.66f, 329.63f, 369.99f, 392.00f, 440.00f, 493.88f, 554.37f, 587.33f
        , 659.25f, 739.99f, 783.99f, 880.00f, 987.77f, 1108.73f, 1174.66f};
    Minim minim;
    AudioOutput out;
    Oscil wave;
    Oscil wave1;

    public void settings()
    {
        size(500, 500);
        
    }

    public void setup()
    {
        minim = new Minim(this);

        out = minim.getLineOut();
        wave = new Oscil( 440, 0.5f, Waves.SQUARE );
        wave.patch(out);
        wave1 = new Oscil( 440, 0.5f, Waves.SQUARE );
        wave1.patch(out);
    }

    public void keyPressed() {
        if (key == ' ')
        {
            wave.setFrequency(wave.frequency.getLastValue() + 100);
        }
        println(wave.frequency.getLastValue());
    }

    float offset = 0;

    public void draw()
    {
        float n = noise(offset);

        /*
        wave.setFrequency(
            frequencies[(int) map(n, 0, 1, 0, frequencies.length - 5)]
        );

        wave1.setFrequency(
            frequencies[(int) map(n, 0, 1, 4, frequencies.length-1)]
        );
        */
        
        
        
        wave.setFrequency(
            map(n, 0, 1, 200, 600)
        );

        wave1.setFrequency(
            map(n, 0, 1, 400, 800)
        );
        
        offset += 0.01f;

        float cx = width / 2;
        float cy = height / 2;

        float w = 60;
        background(0);
        stroke(255);
        noFill();
        ellipse(cx, cy, 300, 300);
        ellipse(cx, cy, 20, 20);
        ellipse(cx - 100, cy - 50, map(n, 0, 1, 0, w), w);
        ellipse(cx + 100, cy - 50, w, map(n, 0, 1, 0, w));

        ellipse(cx, cy + 100, 100,
            map(n, 0, 1, 0, 100)
        );
    }
}