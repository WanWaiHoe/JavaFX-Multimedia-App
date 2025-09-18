import java.io.File;
import java.util.concurrent.Callable;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class Trailer extends BorderPane {
	
	private static final String Media_URL = "C:\\Users\\wan wai hoe\\Desktop\\SEMD019\\God Of War Ragnarok.mp4";
	File file = new File(Media_URL);
	Media media = new Media(file.toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(media);
	MediaView mediaView = new MediaView(mediaPlayer);
	
	Button playBtn = new Button("Play");
	Button stopBtn = new Button("Stop");
	Button muteBtn = new Button("Mute");
	
	Label lblCurrentTime = new Label();
	Label lblTotalTime = new Label();
	
	Boolean atEndOfVideo = false;
	
	Slider volSlider = new Slider();
	Slider videoSlider = new Slider();
	
	Trailer(){
		mediaView.setFitHeight(500);
		mediaView.setFitWidth(800);
		
		playBtn.setOnAction(e -> {
			if (playBtn.getText().equals("Play")) {
				mediaPlayer.play();
				playBtn.setText("Pause");
			}else if (playBtn.getText().equals("Pause")) {
				mediaPlayer.pause();
				playBtn.setText("Play");
			}
		});
		
		stopBtn.setOnAction(e -> {
			mediaPlayer.seek(Duration.ZERO);
			mediaPlayer.pause();
			playBtn.setText("Play");
		});
		
		muteBtn.setOnAction(e -> {
			if (muteBtn.getText().equals("Mute")) {
				mediaPlayer.setMute(true);
				muteBtn.setText("Unmute");
			}else if (muteBtn.getText().equals("Unmute")) {
				mediaPlayer.setMute(false);
				muteBtn.setText("Mute");
			}
		});
		
		volSlider.setPrefWidth(150);
		volSlider.setValue(50);
		mediaPlayer.volumeProperty().bind(volSlider.valueProperty().divide(100));
		
		lblCurrentTime.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return getTime(mediaPlayer.getCurrentTime()) + " / ";
			}
		}, mediaPlayer.currentTimeProperty()));
		
		mediaPlayer.totalDurationProperty().addListener(new ChangeListener<Duration>() {
			@Override
			public void changed(ObservableValue<? extends Duration> observableValue, Duration oldDuration, Duration newDuration) {
				videoSlider.setMax(newDuration.toSeconds());
				lblTotalTime.setText(getTime(newDuration));
			}
		});
		
		videoSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanging, Boolean isChanging) {
				if (!isChanging) {
					mediaPlayer.seek(Duration.seconds(videoSlider.getValue()));
				}
			}
		});
		
		videoSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
				double currentTime = mediaPlayer.getCurrentTime().toSeconds();
				if (Math.abs(currentTime - newValue.doubleValue()) > 0.5) {
					mediaPlayer.seek(Duration.seconds(newValue.doubleValue()));
				}
			}
		});
		
		mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
			@Override
			public void changed(ObservableValue<? extends Duration> observableValue, Duration oldTime, Duration newTime) {
				if (!videoSlider.isValueChanging()) {
					videoSlider.setValue(newTime.toSeconds());
				}
			}
		});
		
		VBox vbox = new VBox(5);
		HBox hbox = new HBox(5);
		vbox.getChildren().addAll(videoSlider, hbox);
		hbox.getChildren().addAll(playBtn, stopBtn, muteBtn, new Label("Volume"), volSlider, lblCurrentTime, lblTotalTime);
		
		this.setMargin(vbox, new Insets(5, 5, 5, 5));
		this.setBottom(vbox);
		this.setCenter(mediaView);
	}
	
	public String getTime(Duration time) {
		
		int hours = (int) time.toHours();
		int minutes = (int) time.toMinutes();
		int seconds = (int) time.toSeconds();
		
		if (seconds > 59) seconds = seconds % 60;
		if (minutes > 59) minutes = minutes % 60;
		if (hours > 59)  hours = hours % 60;
		
		if (hours > 0) return String.format("%d : %02d : %02d", hours, minutes, seconds);
		else return String.format("%02d : %02d", minutes, seconds);
	}
}
