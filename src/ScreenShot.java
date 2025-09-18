import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ScreenShot extends BorderPane{

	List<Image> imgList = new ArrayList<>();
	Button btnNext = new Button(">");
	Button btnBack = new Button("<");
	
	Label lblImageCount = new Label("1/7");
	
	ImageView imageView = new ImageView();
	
	int a = 0;
	
	ScreenShot() {
		
		imgList.add(new Image("file:///C:/Users/wan%20wai%20hoe/eclipse-workspace/Assignment%203/Ragnarok1.jpg"));
		imgList.add(new Image("file:///C:/Users/wan%20wai%20hoe/eclipse-workspace/Assignment%203/Raknarok2.jpg"));
		imgList.add(new Image("file:///C:/Users/wan%20wai%20hoe/eclipse-workspace/Assignment%203/Raknarok3.jpg"));
		imgList.add(new Image("file:///C:/Users/wan%20wai%20hoe/eclipse-workspace/Assignment%203/Raknarok4.jpg"));
		imgList.add(new Image("file:///C:/Users/wan%20wai%20hoe/eclipse-workspace/Assignment%203/Raknarok5.jpg"));
		imgList.add(new Image("file:///C:/Users/wan%20wai%20hoe/eclipse-workspace/Assignment%203/Raknarok6.jpg"));
		imgList.add(new Image("file:///C:/Users/wan%20wai%20hoe/eclipse-workspace/Assignment%203/Raknarok7.jpg"));
		
		imageView.setImage(imgList.get(a));
		imageView.setFitHeight(500);
		imageView.setFitWidth(800);
		
		btnNext.setPrefHeight(510);
		btnBack.setPrefHeight(510);
		
		lblImageCount.setStyle("-fx-font-weight: bold");
		
		btnNext.setOnAction(e -> {	
			if (a < 6) {
				a++;
				lblImageCount.setText(Integer.toString(a+1)+"/7");
			} else {
				a = 0;
				lblImageCount.setText(Integer.toString(a+1)+"/7");
			}
			imageView.setImage(imgList.get(a));
		});
		
		btnBack.setOnAction(e -> {
			if (a == 0) {
				a = 6;
				lblImageCount.setText(Integer.toString(a+1)+"/7");
			} else {
				a--;
				lblImageCount.setText(Integer.toString(a+1)+"/7");
			}
			imageView.setImage(imgList.get(a));
		});

		this.setBottom(lblImageCount);
		this.setAlignment(lblImageCount, Pos.TOP_CENTER);
		this.setCenter(imageView);
		this.setRight(btnNext);
		this.setLeft(btnBack);
	}
}
