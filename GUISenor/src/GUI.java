import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import javax.swing.JFrame;

import org.jcp.xml.dsig.internal.dom.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GUI extends Application{
	VideoCapture videoCapture;
    Canvas canvas;
    GraphicsContext g2d;
    Stage stage;
    AnimationTimer timer;
    String address;
    Slider lowR,highR,lowB,highB,lowG,highG;
    Mat maskmat, morphmat;
    Rectangle previewcolor1, previewcolor2;
    TextField add;
    Label label1, label2, label3, label4, label5;

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {	
		System.load(System.getProperty("user.dir")+"/src/libopencv_java343.dylib");
		primaryStage.setResizable(false);
		stage=primaryStage;
	
		canvas = new Canvas(640, 480);
        g2d = canvas.getGraphicsContext2D();
        g2d.setStroke(Color.GREEN);
        
        Canvas mask1 = new Canvas(320, 240);
        GraphicsContext g3d = mask1.getGraphicsContext2D();
        g3d.setStroke(Color.GREEN);
        
        Canvas morph1 = new Canvas(320, 240);
        GraphicsContext g4d = morph1.getGraphicsContext2D();
        g3d.setStroke(Color.GREEN);
        
        Group group1 = new Group(mask1);
        Group group2= new Group(morph1);
        
        Group group = new Group(canvas);
		
		label1 = new Label("Depth: 2.3 feet");
		label2 = new Label("Orientation: ");
		label3 = new Label("Horizontal speed: 3 knots");
		label4 = new Label("Vertical speed: 0 knots");
		label5 = new Label("Moisture level: dry");
		
		lowG = new Slider(0,255,0);
		lowG.setShowTickMarks(true);
		lowG.setShowTickLabels(true);
		highG = new Slider(0,255,255);
		highG.setShowTickMarks(true);
		highG.setShowTickLabels(true);
		lowB = new Slider(0,255,0);
		lowB.setShowTickMarks(true);
		lowB.setShowTickLabels(true);
		highB = new Slider(0,255,255);
		highB.setShowTickMarks(true);
		highB.setShowTickLabels(true);
		lowR = new Slider(0,180,0);
		lowR.setShowTickMarks(true);
		lowR.setShowTickLabels(true);
		highR = new Slider(0,180,180);
		highR.setShowTickMarks(true);
		highR.setShowTickLabels(true);
		VBox sliderbox = new VBox();
		sliderbox.getChildren().addAll(lowR,highR,lowB,highB,lowG,highG);
		
		Label lr = new Label("Low Hue:");
		Label lb = new Label("Low Saturation:");
		Label lg = new Label("Low Value:");
		Label hg = new Label("High Value:");
		Label hr = new Label("High Hue:");
		Label hb = new Label("High Saturation:");
		VBox sliderlabelbox = new VBox();
		sliderlabelbox.setSpacing(11);
		sliderlabelbox.getChildren().addAll(lr,hr,lb,hb,lg,hg);
		
		HBox combinedsliderbox = new HBox();
		combinedsliderbox.getChildren().addAll(sliderlabelbox,sliderbox);
		
		Color c1 = Color.hsb(0,0,0);
		Color c2 = Color.hsb(180,255/255,255/255);
		previewcolor1 = new Rectangle();
		previewcolor1.setWidth(200);
		previewcolor1.setHeight(100);
		previewcolor1.setFill(c1);
		previewcolor2 = new Rectangle();
		previewcolor2.setWidth(200);
		previewcolor2.setHeight(100);
		previewcolor2.setFill(c2);
		
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10,10,10,10));
		vbox.setSpacing(10);
		vbox.getChildren().addAll(label1,label2,label3,label4,label5, combinedsliderbox, previewcolor1, previewcolor2);
		
		HBox addressbox = new HBox();
		add = new TextField();
		Button sendButton = new Button("send");
		addressbox.getChildren().addAll(add,sendButton);
		sendButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent e) {
//		        videoCapture.release();
//				videoCapture.open(add.getText());
				initOpenCv();
		    }
		});
		
		BorderPane rootPane = new BorderPane();
		Text title =  new Text("Sea Perch II Control Panel");
		title.setId("title");
		title.setFont(new Font("Avenir", 60));
		
		JFrameWindow frame = new JFrameWindow();
		final JFXPanel mainJFXPanel = new JFXPanel();
		frame.getContentPane().add(mainJFXPanel);
		
		VBox leftrside = new VBox();
		leftrside.getChildren().addAll(group1,group2);
		
		HBox leftlside = new HBox();
		leftlside.getChildren().addAll(group,leftrside);
		
		VBox leftside = new VBox();
		leftside.getChildren().addAll(title, leftlside,addressbox);
		
		Scene scene = new Scene(rootPane, 1300, 720);
		rootPane.setLeft(leftside); 
		rootPane.setRight(vbox);

        primaryStage.setScene(scene);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(
        		GUI.class.getResource("file.css").toExternalForm());
        primaryStage.show();
     
        timer = new AnimationTimer() {

            Mat mat = new Mat();

            @Override
            public void handle(long now) {

                videoCapture.read(mat);
                
                grabFrame();

                Image image = mat2Image(mat);

                g2d.drawImage(image, 0, 0);
                
                Image maskimg = mat2Image(maskmat);
                
                g3d.drawImage(maskimg,0,0,640,480,0,0,320,240);
                
                Image morphimg = mat2Image(morphmat);
                
                g4d.drawImage(morphimg,0,0,640,480,0,0,320,240);

            }
        };
        timer.start();
	
	}
	
	private Mat grabFrame()
	{
		Mat frame = new Mat();
		
		videoCapture.read(frame);
		
		if(!frame.empty())
		{
		
	        Mat blurredImage = new Mat();
	        Mat hsvImage = new Mat();
	        maskmat = new Mat();
	        morphmat = new Mat();
	
	        // remove some noise
	        Imgproc.blur(frame, blurredImage, new Size(7, 7));
	
	        // convert the frame to HSV
	        Imgproc.cvtColor(blurredImage, hsvImage, Imgproc.COLOR_BGR2HSV);
	        
	        Scalar minValues = new Scalar(lowR.getValue(),lowB.getValue(),lowG.getValue());
	        Scalar maxValues = new Scalar(highR.getValue(),highB.getValue(),highG.getValue());
	        
	        Color c1 = Color.hsb(lowR.getValue(),lowB.getValue()/255,lowG.getValue()/255);
			Color c2 = Color.hsb(highR.getValue(),highB.getValue()/255,highG.getValue()/255);
			
			previewcolor1.setFill(c1);
			previewcolor2.setFill(c2);
	        
	        Core.inRange(hsvImage, minValues, maxValues, maskmat);
//			// show the partial output
			
			// morphological operators
			// dilate with large element, erode with small ones
			Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(24, 24));
			Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(12, 12));
			
			Imgproc.erode(maskmat, morphmat, erodeElement);
			Imgproc.erode(morphmat, morphmat, erodeElement);
			
			Imgproc.dilate(morphmat, morphmat, dilateElement);
			Imgproc.dilate(morphmat, morphmat, dilateElement);
			
			// show the partial output
		}
		return frame;
	}
	
	private void initOpenCv() {

        setLibraryPath();
        
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        videoCapture = new VideoCapture();
//        videoCapture.open("http://192.168.1.15:8081/"); if this fails uncomment this 
        // and add initOpenCv() to the first method
        if(add.getText().equals("0"))
        		videoCapture.open(0);
        else
        		videoCapture.open(add.getText());

        System.out.println("Camera open: " + videoCapture.isOpened());

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {

                timer.stop();
                videoCapture.release();

                System.out.println("Camera released");

            }
        });

    }
	
	private void editLabels(double varD, int varO, double varH, double varV, int varL)
	{
		label1.setText("Depth: "+varD+" meters");
		label2.setText("Orientation: "+varO);
		label3.setText("Horizontal speed: "+varH+" knots");
		label4.setText("Vertical speed: "+varV+" knots");
		if(varL==0)
			label5.setText("Moisture level: dry");
		else
			label5.setText("Moisture level: WATER IN THE SUB");
		
	}

    public static Image mat2Image(Mat mat) {
        MatOfByte buffer = new MatOfByte();
        Imgcodecs.imencode(".png", mat, buffer);
        return new Image(new ByteArrayInputStream(buffer.toArray()));
    }

    private static void setLibraryPath() {

        try {

            System.setProperty("java.library.path", "lib/x64");

            Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
            fieldSysPath.setAccessible(true);
            fieldSysPath.set(null, null);

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }

    }

    public static String getOpenCvResource(Class<?> clazz, String path) {
        try {
            return Paths.get( clazz.getResource(path).toURI()).toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}