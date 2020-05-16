package example;

import processing.core.PApplet;
import processing.core.PVector;

public class ThreeD extends PApplet {

    PVector cp = new PVector(0, 0, 1);

    public float speed = 5;

    void moveCamera()
    {
        float speed = this.speed;
        if (checkKey(SHIFT))
        {
            speed *= 5.0f;
        }

        if (checkKey('w'))
        {
            cp.z -= speed;
        }

        if (checkKey('s'))
        {
            cp.z += speed;
        }
        if (checkKey('a'))
        {
            cp.x -= speed;
        }
        if (checkKey('d'))
        {
            cp.x += speed;
        }
    }

    boolean checkKey(int k) {
        if (keys.length >= k) {
            return keys[k] || keys[Character.toUpperCase(k)];
        }
        return false;
    }

    public void keyPressed() {
        keys[keyCode] = true;
    }

    public void keyReleased() {
        keys[keyCode] = false;
    }

    boolean[] keys = new boolean[2048];

    public void settings() {
        size(500, 500, P3D);
    }

    public void setup() {

    }

    float rot = 0;

    public void draw() {
        background(0);
        stroke(255);
        lights();
        camera(cp.x, cp.y, cp.z, cp.x, cp.y, cp.z - 1, 0, 1, 0);
        pushMatrix();
        translate(0, 0, -250);
        rotateX(rot);
        rotateY(rot);
        noStroke();
        fill(255);
        box(100);
        popMatrix();
        rot += 0.01f;
        moveCamera();
    }
}