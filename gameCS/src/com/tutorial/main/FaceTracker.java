package com.tutorial.main;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class FaceTracker {

	static JFrame frame;
	static JLabel lbl;
	static ImageIcon icon;
	
	private long startTime = 0;
	
	private int playerXCord;
	private int playerYCord;
	
	private VideoCapture videoDevice;
	private CascadeClassifier cascadeFaceClassifier; 
	private CascadeClassifier cascadeEyeClassifier;
	
	private Mat backgroundRect;
	
	
	public FaceTracker(){
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		videoDevice = new VideoCapture();
		cascadeFaceClassifier = new CascadeClassifier("ressources/haarcascade_frontalface_default.xml");
		cascadeEyeClassifier = new CascadeClassifier("ressources/haarcascade_eye.xml");
	
	}
	
	public boolean beginTrack() {
		
		//Open Video Device
		videoDevice.open(0);
		if (videoDevice.isOpened()) {
			return true;
		}
		else {
			System.out.println("Video cannot be opened.");
			return false;
		}
	}
	
	public void tick() {
		//Refresh video every 100 frames
		if(System.currentTimeMillis() - startTime >= 100) {
			startTime = System.currentTimeMillis();
			refreshVideo();
		}
		
	}
	
	public void refreshVideo() {
		//Redraw image (update)
		Mat frameCapture = new Mat();
		//read image
		videoDevice.read(frameCapture);
		//Resize image
		Imgproc.resize(frameCapture, frameCapture, new Size(320, 240));
		//Invert image so that its like a mirror
		Core.flip(frameCapture, frameCapture, 1);
		//Redraw shown image with black background for easier time rendering
		backgroundRect = new Mat();
		backgroundRect = Imgcodecs.imread("ressources/BlackSquare.jpg");
		//Resize background image
		Imgproc.resize(backgroundRect, backgroundRect, new Size(320, 240));
		
		// Face Tracking...
		MatOfRect faces = new MatOfRect();
		cascadeFaceClassifier.detectMultiScale(frameCapture, faces);								

		for (Rect rect : faces.toArray()) {

			Imgproc.putText(backgroundRect, "Face", new Point(rect.x,rect.y-5), 1, 2, new Scalar(0,0,255));								
			Imgproc.rectangle(backgroundRect, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),new Scalar(0, 100, 0),3);
			playerXCord = rect.x + rect.width/2;
			playerYCord = rect.y + rect.height/2;
		}
		
		//EyeTracking...
		
		/*MatOfRect eyes = new MatOfRect();
		cascadeEyeClassifier.detectMultiScale(frameCapture, eyes);
		for (Rect rect : eyes.toArray()) {

			Imgproc.putText(backgroundRect, "Eye", new Point(rect.x,rect.y-5), 1, 2, new Scalar(0,0,255));				

			Imgproc.rectangle(backgroundRect, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
					new Scalar(200, 200, 100),2);
		}*/
		
		PushImage(ConvertMat2Image(backgroundRect));
		//PushImage(ConvertMat2Image(frameCapture));
		//System.out.println(String.format("%s FACES %s EYE detected.", faces.toArray().length,eyes.toArray().length));
		System.out.println(String.format("%s FACES", faces.toArray().length));
	}
	
	private static BufferedImage ConvertMat2Image(Mat camerArray) {
		//in order to display image using jFrame
		
		MatOfByte byteMatArray = new MatOfByte();

		Imgcodecs.imencode(".jpg", camerArray, byteMatArray);

		byte[] byteArray = byteMatArray.toArray();
		BufferedImage recievedImage = null;
		try {
			InputStream in = new ByteArrayInputStream(byteArray);
			recievedImage = ImageIO.read(in);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return recievedImage;
	}
  	
	public static void newWindow() {
		//Second jFrame window
	
		
		frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(330, 250);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void PushImage(Image img2) {
		//Display image onto frame
		
		if (frame == null)
			newWindow();
		if (lbl != null)
			frame.remove(lbl);
		icon = new ImageIcon(img2);
		lbl = new JLabel();
		lbl.setIcon(icon);
		frame.add(lbl);
		frame.revalidate();
	}
	
	public int getX() {
		return playerXCord;
	}
	
	public int getY() {
		return playerYCord;
	}
}
