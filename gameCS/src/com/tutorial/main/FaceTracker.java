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
		//MUST EDIT SO ILL ONLY SHOW BLACK SCREEN WITH SQUARES NOT THE FACE ITSELF!!!
		//DRAW BOXES IN CORRECT COORDINATES 
		// SET VELOCITIES FOR PLAYER X AND Y
		
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
		if(System.currentTimeMillis() - startTime >= 150) {
			startTime = System.currentTimeMillis();
			refreshVideo();
		}
		
	}
	
	public void refreshVideo() {
		Mat frameCapture = new Mat();
		videoDevice.read(frameCapture);
		Imgproc.resize(frameCapture, frameCapture, new Size(320, 240));
		Core.flip(frameCapture, frameCapture, 1);
		backgroundRect = new Mat();
		backgroundRect = Imgcodecs.imread("ressources/BlackSquare.jpg");
		Imgproc.resize(backgroundRect, backgroundRect, new Size(320, 240));
		
		MatOfRect faces = new MatOfRect();
		cascadeFaceClassifier.detectMultiScale(frameCapture, faces);								

		for (Rect rect : faces.toArray()) {

			Imgproc.putText(backgroundRect, "Face", new Point(rect.x,rect.y-5), 1, 2, new Scalar(0,0,255));								
			Imgproc.rectangle(backgroundRect, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),new Scalar(0, 100, 0),3);
			playerXCord = rect.x + rect.width/2;
			playerYCord = rect.y + rect.height/2;
		}
		

		MatOfRect eyes = new MatOfRect();
		cascadeEyeClassifier.detectMultiScale(frameCapture, eyes);
		for (Rect rect : eyes.toArray()) {

			Imgproc.putText(backgroundRect, "Eye", new Point(rect.x,rect.y-5), 1, 2, new Scalar(0,0,255));				

			Imgproc.rectangle(backgroundRect, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
					new Scalar(200, 200, 100),2);
		}
		PushImage(ConvertMat2Image(backgroundRect));
		//PushImage(ConvertMat2Image(frameCapture));
		System.out.println(String.format("%s FACES %s EYE detected.", faces.toArray().length,eyes.toArray().length));
	}
	
	private static BufferedImage ConvertMat2Image(Mat kameraVerisi) {
		MatOfByte byteMatVerisi = new MatOfByte();

		Imgcodecs.imencode(".jpg", kameraVerisi, byteMatVerisi);

		byte[] byteArray = byteMatVerisi.toArray();
		BufferedImage goruntu = null;
		try {
			InputStream in = new ByteArrayInputStream(byteArray);
			goruntu = ImageIO.read(in);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return goruntu;
	}
  	
	public static void PencereHazirla() {
		frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(330, 250);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void PushImage(Image img2) {
		if (frame == null)
			PencereHazirla();
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
