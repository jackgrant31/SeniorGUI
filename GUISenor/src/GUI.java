import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
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

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {	
		System.load(System.getProperty("user.dir")+"/src/libopencv_java343.dylib");
		primaryStage.setResizable(false);
		stage=primaryStage;
		
		initOpenCv();
		canvas = new Canvas(1000, 565);
        g2d = canvas.getGraphicsContext2D();
        g2d.setStroke(Color.GREEN);

        Group group = new Group(canvas);
		
		Label label1 = new Label("Depth: 2.3 feet");
		Label label2 = new Label("Orientation: ");
		Label label3 = new Label("Horizontal speed: 3 knots");
		Label label4 = new Label("Vertical speed: 0 knots");
		Label label5 = new Label("Moisture level: dry");
		VBox vbox = new VBox();
//		vbox.getChildren().addAll(label1,label2,label3,label4,label5);
		vbox.setPadding(new Insets(10,10,10,10));
		vbox.setSpacing(10);
		
		BorderPane pane2 = new BorderPane();
		Button button1 = new Button("motor on");
		Button button2 = new Button("motor on");
		Button button3 = new Button("motor off");
		Button button4 = new Button("motor off");
//		button1.setId("record-sales");
//		button2.setId("record-sales");
//		button3.setId("record-sales");
//		button4.setId("record-sales");
		pane2.setPrefSize(200, 200);
		pane2.setLeft(button3);
		pane2.setRight(button4);
		pane2.setTop(button1);
		pane2.setBottom(button2);
		vbox.getChildren().addAll(label1,label2,label3,label4,label5, button1, button2, button3,button4);
		
		BorderPane rootPane = new BorderPane();
		Text title =  new Text("Sea Perch II Control Panel");
		title.setId("title");
		title.setFont(new Font("Avenir", 60));
		Scene scene = new Scene(rootPane, 1200, 720);
		//StackPane videoPane = new StackPane();
		rootPane.setLeft(group); 
		//rootPane.setBottom(pane2);
		rootPane.setTop(title);
		rootPane.setRight(vbox);
		
		//MediaPlayer player = new MediaPlayer( new Media(getClass().getResource("mobile.mp4").toExternalForm()));
        //MediaView mediaView = new MediaView(player);

        //videoPane.getChildren().add(mediaView);

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

                Image image = mat2Image(mat);

                g2d.drawImage(image, 0, 0);

            }
        };
        timer.start();
        //player.play();
	
	}
	
	private void initOpenCv() {

        setLibraryPath();
        
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        videoCapture = new VideoCapture();
        videoCapture.open(0);

        System.out.println("Camera open: " + videoCapture.isOpened());

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {

                timer.stop();
                videoCapture.release();

                System.out.println("Camera released");

            }
        });

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