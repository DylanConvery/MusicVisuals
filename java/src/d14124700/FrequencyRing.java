package d14124700;

import processing.core.*;

public class FrequencyRing {

    Visualizer visualizer;

    float circle_x;
    float circle_y;
    float circle_width;
    float circle_radius;

    int spines = 200;
    float[] lerpedSamples = new float[spines];

    float rotation_angle = 0;

    float x1;
    float y1;
    float x2;
    float y2;

    public FrequencyRing(Visualizer visualizer){
        this.visualizer = visualizer;
        circle_x = visualizer.width / 2;
        circle_y = visualizer.height / 2;
        circle_width = visualizer.width / 5;
        circle_radius = circle_width / 2f;
    }

    public void render(){
        visualizer.strokeWeight(5);
        float theta = PApplet.TWO_PI / (float) spines;
        float[] bands = visualizer.getSmoothedBands();

        visualizer.translate(circle_x, circle_y);
        visualizer.rotate(rotation_angle);
        visualizer.pushMatrix();

        for (int i = 0; i < spines; i++) {
            visualizer.stroke(PApplet.map(i, 0, spines, 0, 255), 255, 255);
            float band = bands[i % bands.length];

            x1 = (circle_radius * PApplet.sin(i * theta));
            y1 = (circle_radius * PApplet.cos(i * theta));

            x2 = ((circle_radius + band * 0.2f) * PApplet.sin(i * theta));
            y2 = ((circle_radius + band * 0.2f) * PApplet.cos(i * theta));

            visualizer.line(x1, y1, x2, y2);
        }
        
        visualizer.popMatrix();

        

        rotation_angle += visualizer.getSmoothedAmplitude() / 10.0f;
    }
}