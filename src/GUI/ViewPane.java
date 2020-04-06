package GUI;


import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewPane extends GridPane{

	public ViewPane(Stage window) {
		Label lbl = new Label("This is the viewPane");
		this.add(lbl, 1, 1);
	}

}
