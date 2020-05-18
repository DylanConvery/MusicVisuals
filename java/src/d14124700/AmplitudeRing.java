package d14124700;

import processing.core.*;

public class AmplitudeRing {

    Visualizer visualizer;

    float x;
    float y;
    float width;
    float intensity; // how intense our ring will react to amplitude
    float smoothed_size;

    public AmplitudeRing(Visualizer visualizer, float x, float y, float width, float intensity) {
        this.visualizer = visualizer;
        this.x = x;
        this.y = y;
        this.width = width;
        this.intensity = intensity;
    }

    public void render() {
        visualizer.colorMode(PApplet.HSB);
        visualizer.noFill();
        visualizer.stroke(PApplet.map(visualizer.getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        visualizer.strokeWeight(10);
        //grow and contract our ring according to the amplitude
        float size = width + (visualizer.getSmoothedAmplitude() * intensity);
        smoothed_size = PApplet.lerp(smoothed_size, size, 0.5f);
        visualizer.ellipse(x, y, smoothed_size, smoothed_size);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getIntensity() {
        return intensity;
    }
}
