import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class Camera extends Application {

    private static final int SCENE_W = 640;
    private static final int SCENE_H = 480;

    VideoCapture videoCapture;

    Canvas canvas;
    GraphicsContext g2d;
    Stage stage;
    AnimationTimer timer;

    @Override
    public void start(Stage stage) {
    		System.load(System.getProperty("user.dir")+"/src/libopencv_java343.dylib");
    		System.out.println(System.getProperty("java.library.path"));
    		
        this.stage = stage;

        initOpenCv();

        canvas = new Canvas(SCENE_W, SCENE_H);
        g2d = canvas.getGraphicsContext2D();
        g2d.setStroke(Color.GREEN);

        Group group = new Group(canvas);

        Scene scene = new Scene(group, SCENE_W, SCENE_H);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

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

    public static void main(String[] args) {
        launch(args);
    }
}