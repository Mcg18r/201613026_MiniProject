package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import styles.backg;
import styles.button;
	

/**
 * @author 201613026
 *
 */
public class NavPane extends GridPane 
{

	//BUTTONS USED FOR THE INTERFACE
	private Button btnView;
	private Button btnViewVertex;
	private Button btnViewEdge;
	private Button btnViewBuilding;
	
	private VBox gridBox= new VBox ();;
	
	Stage window;

	public NavPane(Stage window) {	
		this.window = window;
		setupGUI();
	}

	//A METHOD THAT HOLDS THE SETTING UP OF ALL THE BUTTONS
	private void buttonFunctions() {
		//shows the graph view
		btnView.setOnAction(e->{
			
			gridBox.getChildren().clear();
			ViewPane movPane = new ViewPane (window);
			gridBox.getChildren().add(movPane);
		});
		
		//shows the list of vertices in the graph
		btnViewVertex.setOnAction(e->{
			
			gridBox.getChildren().clear();
			VertexPane movPane = new VertexPane ();
			gridBox.getChildren().add(movPane);
		});
		
		//shows the list of edges in a graph
		btnViewEdge.setOnAction(e->{
			
			gridBox.getChildren().clear();
			EdgePane movPane = new EdgePane ();
			gridBox.getChildren().add(movPane);
		});
		
		btnViewBuilding.setOnAction(e->{
			
			gridBox.getChildren().clear();
			BuildingPane movPane = new BuildingPane ();
			gridBox.getChildren().add(movPane);
		});
	}

	private void setupGUI() 
	{
		button btn = new button();
		btnView = btn.btnSet(btnView,"VIEW","View all in graph");
		btnViewVertex = btn.btnSet(btnViewVertex,"VIEW VERTICES","View the vertices in graph");
		btnViewEdge = btn.btnSet(btnViewEdge,"VIEW EDGE'S","View the edge's in graph");
		btnViewBuilding = btn.btnSet(btnViewBuilding,"VIEW BUILDING'S","View the buildings");

		VBox btnBox = new VBox ();
		btnBox.getChildren().addAll(btnView, btnViewVertex, btnViewEdge,btnViewBuilding);
		gridBox.setAlignment(Pos.BOTTOM_CENTER);
		
		setHgap(10);
		setVgap(10);
		setAlignment(Pos.CENTER);
		
		Text lblMainUsage = new Text("Main Usage");
		lblMainUsage.setFont(Font.font ("Verdana", FontWeight.BOLD, 40));
		lblMainUsage.setFill(Color.WHITE);
		
		Text lblAdd = new Text("Add Information");
		lblAdd.setFont(Font.font ("Verdana", FontWeight.BOLD, 40));
		lblAdd.setFill(Color.WHITE);
		
		Text lblAddOther = new Text("Other");
		lblAddOther.setFont(Font.font ("Verdana", FontWeight.BOLD, 40));
		lblAddOther.setFill(Color.WHITE);
		
		ColumnConstraints column1 = new ColumnConstraints();
	     column1.setPercentWidth(5);
	     ColumnConstraints column2 = new ColumnConstraints();
	     column2.setPercentWidth(25);
	     ColumnConstraints column3 = new ColumnConstraints();
	     column3.setPercentWidth(65);
	     ColumnConstraints column4 = new ColumnConstraints();
	     column4.setPercentWidth(5);
	     
	     this.getColumnConstraints().addAll(column1, column2, column3, column4);
	     this.setAlignment(Pos.CENTER);
//	     
	     add(gridBox,2,1);
	     add(btnBox,1,1);
		
		buttonFunctions();
		
		Background b = backg.setBackground("file:src/Images/backgroundLogin.jpg");
		this.setBackground(b);		
	}

	
}
