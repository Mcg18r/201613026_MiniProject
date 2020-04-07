package GUI;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

import FileHandling.IOHandling;
import FileHandling.StringHandling;
import Object.Building;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import styles.button;

public class EdgePane extends GridPane{

	ArrayList<Edge<Building>> eTest=new ArrayList<Edge<Building>>();				//This is to store the edges into an arraylist
	private TableView<Edge<Building>> table = new TableView<Edge<Building>>();
	private ObservableList<Edge<Building>> data;
	
	Stage windowChart;
	
	Button btnLineChart;
	
	
	public EdgePane() {
		tableLoad();
		setupGUI();
		
		btnLineChart.setOnAction(e->{
			lineChart();
		});
	}
	
	public void setupGUI()
	{
		button btn = new button();
		
		btnLineChart = btn.btnSet(btnLineChart,"Line Chart","");
		
		setHgap(10);
		setVgap(10);
		setAlignment(Pos.CENTER);
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
		add(btnLineChart,1,1,2,1);
	}
	@SuppressWarnings("unchecked")
	public void tableLoad()
	{

		
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void lineChart ()
	{
		int cost=0;
		int count=1;
		windowChart=new Stage();
		windowChart.close();
		windowChart.setTitle("Chart");
		
		
		
		final NumberAxis xAxis = new NumberAxis();
	    final NumberAxis yAxis = new NumberAxis();
	    xAxis.setLabel("Number of Month");
	    //creating the chart
	    final LineChart<Number,Number> lineChart = 
	            new LineChart<Number,Number>(xAxis,yAxis);
	            
	    lineChart.setTitle("Stock Monitoring, 2010");
	    //defining a series
	    XYChart.Series series = new XYChart.Series();
	    series.setName("My portfolio");
	    //populating the series with data
	    for (Edge<Building> e: eTest)
		{
	    	String edge = e.toString();
	    	
	    	String edgeFromName = StringHandling.strFind(edge,"getName\\(\\)=(\\d|\\w|\\s)+(]|,|\\()");
	    	//String []line = edgeFromName.split("^(^getName\\(\\)=|^]|^\\s)");
	    	String []line=edgeFromName.split("getName\\(\\)=");
	    	//String other = line[0];
	    	String from = line[1];
	    	String to = line[2];
	    	//System.out.println("other: "+other);
	    	System.out.println("From: "+from);
	    	System.out.println("To: "+to);
	    	//System.out.println(From +"<--------------");
	    	//StringHandling.getDetail(str, strDelimeter)
	    	cost=e.getCost();
		    series.getData().add(new XYChart.Data(count, cost));
		    //count++;
		}
	    lineChart.getData().add(series);
	    
	    Scene scene  = new Scene(lineChart,800,600);
	    windowChart.setScene(scene);
	    windowChart.show();
	}
	

}
