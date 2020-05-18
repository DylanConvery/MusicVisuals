package d14124700;

import ie.tudublin.Visual;
import ie.tudublin.VisualException;

public class Visualizer extends Visual {

    FrequencyRing freq_ring;
    AmplitudeRing amps_ring;

    public void settings() {
        fullScreen();
    }

    public void setup() {
        startMinim();
        loadAudio("heroplanet.mp3");
        getAudioPlayer().play();
        freq_ring = new FrequencyRing(this, width / 2, height / 2, width / 4, 50, 10);
        amps_ring = new AmplitudeRing(this, width / 2, height / 2, width / 10, 400f);
    }

    public void keyPressed() {
        if (key == ' ') {
            // allows for pausing and playing
            if (getAudioPlayer().isPlaying()) {
                getAudioPlayer().pause();
            } else {
                getAudioPlayer().play();
            }
        }
    }

    public void draw() {
        background(0);
        colorMode(HSB);
        // calculates our average amplitude and is used by our objects.
        calculateAverageAmplitude();
        try {
            calculateFFT();
        } catch (VisualException e) {
            e.printStackTrace();
        }
        calculateFrequencyBands();
        freq_ring.render();
        amps_ring.render();
    }
}
