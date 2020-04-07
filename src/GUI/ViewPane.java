package GUI;


import java.util.ArrayList;
import java.util.List;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;
import com.sun.javafx.geom.Shape;

import FileHandling.IOHandling;
import Object.Building;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import styles.Arrow;

public class ViewPane extends GridPane{

	private Stage drawWindow = new Stage();
	private ScrollPane sp = new ScrollPane();
	private Group root = new Group();				//CREATED GROUP TO ADD NODES TO
	
	ArrayList<Vertex<Building>> vList;
	ArrayList<Edge<Building>> eList;
	ArrayList<Graph<Building>> gList;
	
	public ViewPane() {
		
		/*--------------------READING DATA TO ARRAY LIST---------------------------------*/
		vList = IOHandling.readVertex();
		eList = IOHandling.readEdge();
		gList = IOHandling.readGraph();
		/*-------------------------------------------------------------------------------*/
		
		drawGraph();			
		
		/*------------------------ADD VERTICES TO PANE-----------------------------------*/
//		for (Vertex<Building> v: vList)
//		{
//			Circle c = drawVertex(v);
//			root.getChildren().add(c);
//		}
		/*-------------------------------------------------------------------------------*/
		
		/*------------------------ADD EDGES TO PANE--------------------------------------*/
//		for (Edge<Building> e: eList)
//		{
//			Arrow arrow = drawArrowEdge(e);
//			root.getChildren().add(arrow);
//		}
		/*-------------------------------------------------------------------------------*/
		
		/*------------------------ADD ROOT TO SCENE--------------------------------------*/
		sp.setContent(root);					//add root to scroll bar first
		Scene scene = new Scene(sp,800,800);	//then add scroll bar to the scene
		drawWindow.setScene(scene);				
		drawWindow.show();							
		/*-------------------------------------------------------------------------------*/
		
		/*-----------------------ADD EDGES TO PANE WITH MOUSE----------------------------*/
//		root.setOnMouseClicked(evt -> {
//	        switch (evt.getButton()) {
//	            case PRIMARY:
//	                // set pos of end with arrow head
//	                arrow.setEndX(evt.getX());
//	                arrow.setEndY(evt.getY());
//	                break;
//	            case SECONDARY:
//	                // set pos of end without arrow head
//	                arrow.setStartX(evt.getX());
//	                arrow.setStartY(evt.getY());
//	                break;
//	        }
//	    });
		/*-------------------------------------------------------------------------------*/
	
	}
	
	public Circle drawVertex(Vertex<Building> vertex)
	{
		Circle circle = new Circle(vertex.getValue().getXPosition(), vertex.getValue().getYPosition(), 50);		
		return circle;
	}
	
	public Line drawLineEdge(Edge<Building> edge)
	{
		Line line = new Line(edge.getFromVertex().getValue().getXPosition(), edge.getFromVertex().getValue().getYPosition(), 
				edge.getToVertex().getValue().getXPosition(), edge.getToVertex().getValue().getYPosition());
		
		return line;
	}
	
	public Arrow drawArrowEdge(Edge<Building> edge)
	{
		Arrow arrow = new Arrow();
		double radius = 50;
		
		int startX = edge.getFromVertex().getValue().getXPosition();
		int startY = edge.getFromVertex().getValue().getYPosition();
		int endX = edge.getToVertex().getValue().getXPosition();
		int endY = edge.getToVertex().getValue().getYPosition();

		double angle = Math.atan2(endY - startY, endX - startX) * 180 / 3.14;

	    double height = endY - startY;
	    double width = endX - startX;
	    double length = Math.sqrt(Math.pow(height, 2) + Math.pow(width, 2));

	    double subtractWidth = radius * width / length;
	    double subtractHeight = radius * height / length;

	    setRotate(angle - 90);
	    
	    arrow.setStartX(startX+ subtractWidth);
	    arrow.setStartY(startY+ subtractHeight);
	    arrow.setEndX(endX - subtractWidth);
	    arrow.setEndY(endY - subtractHeight);
		
		return arrow;
	}

	public void drawGraph ()
	{
		for (Graph<Building> g: gList)
		{
			List<Vertex<Building>> vL=g.getVertices();
			List<Edge<Building>> eL=g.getEdges();
			
			for (Vertex<Building> v: vL)
			{
				
				int Xpos= v.getValue().getXPosition();
				int Ypos= v.getValue().getYPosition();
				String name= v.getValue().getName();
				String type= v.getValue().getType();
				String X= Integer.toString(Xpos);
				String Y = Integer.toString(Ypos);
				
				Circle c = drawVertex(v);
				
				if(type=="Solar Building")
				{
					c.setFill(Color.GREEN);
				}
				else if (type=="Standard Building")
				{
					c.setFill(Color.RED);
				}
				else
				{
					c.setFill(Color.CYAN);
				}
				
				root.getChildren().add(c);
			}
			
			for (Edge<Building> e: eL)
			{
				Arrow a = drawArrowEdge(e);
				root.getChildren().add(a);
			}
		}
	}
	
}
