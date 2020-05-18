package d14124700;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class Visualizer extends Visual {
    
    FrequencyRing bands_ring;
    AmplitudeRing amps_ring;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        startMinim();
        loadAudio("heroplanet.mp3");
        getAudioPlayer().play();
        bands_ring = new FrequencyRing(this);
        amps_ring = new AmplitudeRing(this);
    }

    public void keyPressed() {
    }

    public void draw() {
        background(0);
        colorMode(HSB);
        calculateAverageAmplitude();
        try {
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }
        calculateFrequencyBands();
        bands_ring.render();
        amps_ring.render();
    }
}