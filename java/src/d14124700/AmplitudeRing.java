package d14124700;

import processing.core.*;

public class AmplitudeRing {

    Visualizer visualizer;

    float circle_width;

    public AmplitudeRing(Visualizer visualizer){
        this.visualizer = visualizer;
        circle_width = visualizer.width / 6;
    }

    public void render(){
        visualizer.noFill();
        visualizer.stroke(PApplet.map(visualizer.getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);
        visualizer.strokeWeight(5);
        visualizer.ellipse(0,0, circle_width, circle_width);
    }
}