import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application{

	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setResizable(false);
		
		Label label1 = new Label("Depth: 2.3 feet");
		Label label2 = new Label("Pressure: 0.3 atm");
		Label label3 = new Label("Horizontal speed: 3 knots");
		Label label4 = new Label("Vertical speed: 0 knots");
		Label label5 = new Label("Moisture level: dry");
		VBox vbox = new VBox();
		vbox.getChildren().addAll(label1,label2,label3,label4,label5);
		vbox.setPadding(new Insets(10,10,10,10));
		vbox.setSpacing(10);
		
		BorderPane pane2 = new BorderPane();
		Button button1 = new Button("motor on");
		Button button2 = new Button("motor on");
		Button button3 = new Button("motor off");
		Button button4 = new Button("motor off");
		pane2.setPrefSize(200, 200);
		pane2.setLeft(button3);
		pane2.setRight(button4);
		pane2.setTop(button1);
		pane2.setBottom(button2);
		
		
		BorderPane rootPane = new BorderPane();
		Label title =  new Label("Control Panel");
		title.setFont(new Font("Avenir", 60));
		Scene scene = new Scene(rootPane, 1200, 720);
		StackPane videoPane = new StackPane();
		rootPane.setLeft(videoPane); 
		rootPane.setBottom(pane2);
		rootPane.setTop(title);
		rootPane.setRight(vbox);
		
		MediaPlayer player = new MediaPlayer( new Media(getClass().getResource("mobile.mp4").toExternalForm()));
        MediaView mediaView = new MediaView(player);

        videoPane.getChildren().add(mediaView);

        primaryStage.setScene(scene);
        primaryStage.show();
        
        player.play();
	
	}
}