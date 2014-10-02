import processing.core.PApplet
import gab.opencv._
import processing.video.Capture
import java.awt.Rectangle


class Sketch extends PApplet {

  val video = new Capture(this, 640 / 2, 480 / 2)
  val opencv = new OpenCV(this, 640 / 2, 480 / 2)

  override def setup() {
    size(640, 480)
    opencv.loadCascade(OpenCV.CASCADE_FRONTALFACE)
    video.start()
  }

  override def draw() {
    scale(2)
    opencv.loadImage(video)
    image(video, 0, 0)
    noFill()
    stroke(0, 255, 0)
    strokeWeight(3)
    val faces: Array[Rectangle] = opencv.detect
//    println(faces.length)
    for (face <- faces) {
//      println(face.x + "," + face.y)
      rect(face.x, face.y, face.width, face.height)
    }
  }

  def captureEvent(c: Capture) = c.read()

  override def sketchFullScreen: Boolean = true
}
