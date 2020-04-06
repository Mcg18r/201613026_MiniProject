package GUI;

import java.util.ArrayList;

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

public class BuildingPane extends GridPane{

	private TableView<Building> table = new TableView<Building>();
	private ObservableList<Building> data;
	
	public BuildingPane() {
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

		ArrayList<Building> building = new ArrayList<Building>();
		building = IOHandling.readBuilding();
		
		for(Building b: building)
			{
				System.out.println(b.toString());
			}
	
		data = FXCollections.observableList(building);
		table.setItems(data);

		//ObservableList<Barcode> data = FXCollections.observableArrayList(Barcode.get(0));
		TableColumn<Building, String> nameCol = new TableColumn<Building, String>("NAME");
		nameCol.setCellValueFactory(new PropertyValueFactory<Building, String>("name"));
		
		TableColumn<Building, String> type = new TableColumn<Building, String>("TYPE");
		type.setCellValueFactory(new PropertyValueFactory<Building, String>("type"));

		TableColumn<Building, String> xPos = new TableColumn<Building, String>("X POSTION");
		xPos.setCellValueFactory(new PropertyValueFactory<Building, String>("xPosition"));
		
		TableColumn<Building, String> yPos = new TableColumn<Building, String>("Y POSITION");
		yPos.setCellValueFactory(new PropertyValueFactory<Building, String>("yPosition"));
		
		TableColumn<Building, String> pow = new TableColumn<Building, String>("POWER");
		
		TableColumn<Building, String> powGen = new TableColumn<Building, String>("Generated");
		powGen.setCellValueFactory(new PropertyValueFactory<Building, String>("powerGenerated"));
		
		TableColumn<Building, String> powUsed = new TableColumn<Building, String>("Used");
		powUsed.setCellValueFactory(new PropertyValueFactory<Building, String>("powerUsed"));

		pow.getColumns().addAll(powGen, powUsed);

		table.getColumns().setAll(type,nameCol, xPos, yPos,pow);
		table.setPrefWidth(450);
		table.setPrefHeight(600);
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

}
