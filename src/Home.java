import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class Home extends BorderPane{

	ImageView logo = new ImageView(new Image("file:///C:/Users/wan%20wai%20hoe/eclipse-workspace/Assignment%203/godOfWar(new).jpg"));
	
	Home() {
		
		this.setCenter(logo);
		
	}
}
