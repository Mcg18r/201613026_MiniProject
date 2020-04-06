package GUI;

import java.util.ArrayList;

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

public class VertexPane extends GridPane{

	private TableView<Vertex<Building>> table = new TableView<Vertex<Building>>();
	private ObservableList<Vertex<Building>> data;
	
	public VertexPane() {
		// TODO Auto-generated constructor stub() {
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

		ArrayList<Vertex<Building>> vTest=new ArrayList<Vertex<Building>>();
		vTest = IOHandling.readVertex();
		for(Vertex<Building> v: vTest)
			{
			System.out.println("************************ THIS IS VERTEX "+v.getValue().getName()+ " ************************");
			//System.out.println(b.getValue().getxPosition());
			//System.out.println(b.getValue().getyPosition());
			//System.out.println(((SolarBuilding) b.getValue()).getPowerGenerated());
			System.out.println("************************\n"+v+ "\n************************");
			}
	
		data = FXCollections.observableList(vTest);
		table.setItems(data);

		//ObservableList<Barcode> data = FXCollections.observableArrayList(Barcode.get(0));
		TableColumn<Vertex<Building>, String> valueCol = new TableColumn<Vertex<Building>, String>("VALUE");
		valueCol.setCellValueFactory(new PropertyValueFactory<Vertex<Building>, String>("value"));
		
		TableColumn<Vertex<Building>, String> weightCol = new TableColumn<Vertex<Building>, String>("WEIGHT");
		weightCol.setCellValueFactory(new PropertyValueFactory<Vertex<Building>, String>("weight"));

		TableColumn<Vertex<Building>, String> edgeCol = new TableColumn<Vertex<Building>, String>("EDGES");
		edgeCol.setCellValueFactory(new PropertyValueFactory<Vertex<Building>, String>("edges"));

		table.getColumns().setAll(valueCol,weightCol, edgeCol);
		table.setPrefWidth(450);
		table.setPrefHeight(600);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

}
