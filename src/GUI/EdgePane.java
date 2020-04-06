package GUI;

import java.util.ArrayList;

import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import FileHandling.IOHandling;
import Object.Building;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EdgePane extends GridPane{

	private TableView<Edge<Building>> table = new TableView<Edge<Building>>();
	private ObservableList<Edge<Building>> data;
	
	public EdgePane() {
		tableLoad();
		setupGUI();
	}
	
	public void setupGUI()
	{
		ColumnConstraints column1 = new ColumnConstraints();
	     column1.setPercentWidth(10);
	     ColumnConstraints column2 = new ColumnConstraints();
	     column2.setPercentWidth(40);
	     ColumnConstraints column3 = new ColumnConstraints();
	     column3.setPercentWidth(40);
	     ColumnConstraints column4 = new ColumnConstraints();
	     column4.setPercentWidth(10);
	     
	     this.getColumnConstraints().addAll(column1, column2, column3, column4);
	     this.setAlignment(Pos.CENTER);
		if(this.getChildren().contains(table))
		{
			table.refresh();
		}else
		{
			add(table, 1, 0, 2, 1);
		}
	}
	@SuppressWarnings("unchecked")
	public void tableLoad()
	{

		ArrayList<Edge<Building>> eTest=new ArrayList<Edge<Building>>();
		eTest = IOHandling.readEdge();
		for(Edge<Building> v: eTest)
			{
			System.out.println("************************ THIS IS VERTEX "+v.getCost()+ " ************************");
			//System.out.println(b.getValue().getxPosition());
			//System.out.println(b.getValue().getyPosition());
			//System.out.println(((SolarBuilding) b.getValue()).getPowerGenerated());
			System.out.println("************************\n"+v+ "\n************************");
			}
	
		data = FXCollections.observableList(eTest);
		table.setItems(data);

		//ObservableList<Barcode> data = FXCollections.observableArrayList(Barcode.get(0));
		TableColumn<Edge<Building>, String> valueCol = new TableColumn<Edge<Building>, String>("COST");
		valueCol.setCellValueFactory(new PropertyValueFactory<Edge<Building>, String>("cost"));
		
		TableColumn<Edge<Building>, String> weightCol = new TableColumn<Edge<Building>, String>("FROM VERTEX");
		weightCol.setCellValueFactory(new PropertyValueFactory<Edge<Building>, String>("fromVertex"));

		TableColumn<Edge<Building>, String> edgeCol = new TableColumn<Edge<Building>, String>("TO VERTEX");
		edgeCol.setCellValueFactory(new PropertyValueFactory<Edge<Building>, String>("toVertex"));

		table.getColumns().setAll(valueCol,weightCol, edgeCol);
		table.setPrefWidth(450);
		table.setPrefHeight(600);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

}
