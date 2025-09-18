import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPane extends Application {

	Home home = new Home();
	Trailer trailer = new Trailer();
	ScreenShot screenShot = new ScreenShot();
	
	Tab homeTab = new Tab("Home", home);
	Tab trailerTab = new Tab("Trailer", trailer);
	Tab screenShotTab = new Tab("Screen Shot", screenShot);
	
	TabPane tabPane = new TabPane();
	
	MenuBar menuBar = new MenuBar();
	
	Menu fileMenu = new Menu("File");
	MenuItem about = new MenuItem("About Us");
	MenuItem exit = new MenuItem("Exit");
	
	Menu viewMenu = new Menu("View");
	CheckMenuItem homeItem = new CheckMenuItem("Home");
	CheckMenuItem trailerItem = new CheckMenuItem("Trailer");
	CheckMenuItem screenShotItem = new CheckMenuItem("Screen Shot");
	
	public void start (Stage stage) {
		
		homeTab.setClosable(false);
		trailerTab.setClosable(false);
		screenShotTab.setClosable(false);
		
		about.setOnAction(e -> {
			Alert a = new Alert(AlertType.NONE, "This is the app to introduce God Of War Ragnarok. \nComing Soon in 2022.", ButtonType.OK);
			a.show();
		});
		exit.setOnAction(e -> {
			stage.close();
		});
		fileMenu.getItems().addAll(about, new SeparatorMenuItem(), exit);
		
		homeItem.setSelected(true);
		trailerItem.setSelected(true);
		screenShotItem.setSelected(true);
		
		homeItem.setOnAction(e -> {
			if (homeItem.isSelected() == false) {
				tabPane.getTabs().remove(homeTab);
			} else if (homeItem.isSelected() == true){
				tabPane.getTabs().add(homeTab);
			}
		});
		
		trailerItem.setOnAction(e -> {
			if (trailerItem.isSelected() == false) {
				tabPane.getTabs().remove(trailerTab);
			} else if (trailerItem.isSelected() == true){
				tabPane.getTabs().add(trailerTab);
			}
		});
		
		screenShotItem.setOnAction(e -> {
			if (screenShotItem.isSelected() == false) {
				tabPane.getTabs().remove(screenShotTab);
			} else if (screenShotItem.isSelected() == true){
				tabPane.getTabs().add(screenShotTab);
			}
		});
	
		viewMenu.getItems().addAll(homeItem, trailerItem, screenShotItem);	
		tabPane.getTabs().addAll(homeTab, trailerTab, screenShotTab);
		menuBar.getMenus().addAll(fileMenu, viewMenu);
		
		BorderPane homePane = new  BorderPane();
		homePane.setTop(menuBar);
		homePane.setCenter(tabPane);
		
		Scene scene = new Scene(homePane, 900, 600);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("God of War Ragnarok Introduction");
		stage.show();
	}
	
	public static void main (String[]args) {
		launch(args);
	}
}
