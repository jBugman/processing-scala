import processing.core.PApplet;
import gab.opencv.*;
import processing.video.*;
import java.awt.*;


public class Sketch extends PApplet {

    Capture video;
    OpenCV opencv;

    @Override
    public void setup() {
        size(640, 480);
        video = new Capture(this, 640/2, 480/2);
        opencv = new OpenCV(this, 640/2, 480/2);
        opencv.loadCascade(OpenCV.CASCADE_FRONTALFACE);

        video.start();
    }

    @Override
    public void draw() {
        scale(2);
        opencv.loadImage(video);

        image(video, 0, 0 );

        noFill();
        stroke(0, 255, 0);
        strokeWeight(3);
        Rectangle[] faces = opencv.detect();
        println(faces.length);

        for (Rectangle face : faces) {
            println(face.x + "," + face.y);
            rect(face.x, face.y, face.width, face.height);
        }
    }

    public void captureEvent(Capture c) {
        c.read();
    }

    @Override
    public boolean sketchFullScreen() {
        return true;
    }
}
