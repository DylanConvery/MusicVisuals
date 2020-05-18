package d14124700;

import processing.core.*;

public class FrequencyRing {

    Visualizer visualizer;

    float x;
    float y;
    float width;
    float radius;

    int spines;
    float rotation_angle;
    float smoothedBand;
    float border;

    public FrequencyRing(Visualizer visualizer, float x, float y, float width, int spines,
            float border) {
        this.visualizer = visualizer;
        this.x = x;
        this.y = y;
        this.width = width;
        this.spines = spines;
        radius = width / 2f;
        this.border = border;
    }

    public void render() {
        visualizer.colorMode(PApplet.HSB);
        visualizer.strokeWeight(5);
        float theta = PApplet.TWO_PI / (float) spines; // used to draw n spines in circle
        float[] bands = visualizer.getSmoothedBands(); // stores smoothed bands
        float band = 0;

        visualizer.pushMatrix(); // store current transformation matrix
        visualizer.translate(x, y); // set x,y as new 0,0
        visualizer.rotate(rotation_angle); // rotate by rotation_angle amount every frame

        for (int i = 0; i < spines; i++) {
            float offset = 0; // used to keep spines within border boundary
            visualizer.stroke(PApplet.map(i, 0, spines, 0, 255), 255, 255); // map i between between
                                                                            // 0 - spines to 0 - 255
                                                                            // to cover whole hsb
                                                                            // color space
            band = bands[i % bands.length]; // get band

            // checks whether the spine will be outside our border bounder
            if (radius + band > visualizer.width - x - border) {
                offset = (radius + band) - (visualizer.width - x - border);
            }

            if (radius + band > visualizer.height - y - border) {
                offset = (radius + band) - (visualizer.height - y - border);
            }

            // used for drawing spines.
            // a=r*sin(thetha)
            float x1 = (radius * PApplet.sin(i * theta));
            float y1 = (radius * PApplet.cos(i * theta));
            float x2 = ((radius + band - offset) * PApplet.sin(i * theta));
            float y2 = ((radius + band - offset) * PApplet.cos(i * theta));

            visualizer.line(x1, y1, x2, y2);
        }

        // rotate according to the amplitude
        rotation_angle += visualizer.getSmoothedAmplitude() / 10.0f;
        visualizer.popMatrix();
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
        radius = this.width / 2f;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        width = this.radius * 2f;
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

    public float getRadius() {
        return radius;
    }
}
