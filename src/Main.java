
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Edge;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;
import com.sun.corba.se.spi.ior.ObjectId;

import FileHandling.IOHandling;
import GUI.NavPane;
import GUI.ViewPane;
import Object.Building;
import Object.SolarBuilding;
import Object.StandardBuilding;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * @author 201613026
 *
 */
public class Main extends Application
{
    public static void main(String[] args)
    {
    	//launch the JavaFX Application
    	launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {	
		
		setUp();
		Stage window =primaryStage;
		window.setTitle("2016113026	MiniProject");

		NavPane homePane= new NavPane (window);
		Scene homeScene = new Scene (homePane,800,800);
		window.setScene(homeScene);
		window.setMaximized(true);
		window.show();
	}

	public void setUp ()
	{
		/*--------------------CREATION OF BUILDINGS---------------------------------*/
		SolarBuilding sb = new SolarBuilding ();
		sb.setXPosition(320);
		sb.setYPosition(480);
		sb.setPowerGenerated(2);
		sb.setName("39 Ferero Street");	
		IOHandling.writeBuildingToFile(sb);
		
		SolarBuilding sb1 = new SolarBuilding ();
		sb1.setXPosition(620);
		sb1.setYPosition(920);
		sb1.setPowerGenerated(12);
		sb1.setName("119 Kessel Street");
		IOHandling.writeBuildingToFile(sb1);
		
		SolarBuilding sb2 = new SolarBuilding ();
		sb2.setXPosition(120);
		sb2.setYPosition(100);
		sb2.setPowerGenerated(500);
		sb2.setName("UNICEF");	
		IOHandling.writeBuildingToFile(sb2);
		
		SolarBuilding sb3 = new SolarBuilding ();
		sb3.setXPosition(300);
		sb3.setYPosition(900);
		sb3.setPowerGenerated(1500);
		sb3.setName("134 Amber Street");
		IOHandling.writeBuildingToFile(sb3);
		
		StandardBuilding st = new StandardBuilding();
		st.setXPosition(500);
		st.setYPosition(500);
		st.setPowerUsed(1500);
		st.setName("ABSA");
		IOHandling.writeBuildingToFile(st);
		/*------------------------------------------------------------------------*/
		
		/*--------------------CREATION OF VERTICES---------------------------------*/
		Vertex<Building> v0 = new Vertex<Building>(sb,101);
		//IOHandling.writeVertex(v1); 						add the vertex to file after the edge creation else it adds without the edge
		Vertex<Building> v1 = new Vertex<Building>(sb1,200);
		//IOHandling.writeVertexToFile(v2);					add the vertex to file after the edge creation else it adds without the edge
		Vertex<Building> v2 = new Vertex<Building>(st,220);
		//IOHandling.writeVertexToFile(v2);					add the vertex to file after the edge creation else it adds without the edge
		Vertex<Building> v3 = new Vertex<Building>(sb2,150);
		//IOHandling.writeVertex(v1); 						add the vertex to file after the edge creation else it adds without the edge
		Vertex<Building> v4 = new Vertex<Building>(sb3,200);
		//IOHandling.writeVertexToFile(v2);					add the vertex to file after the edge creation else it adds without the edge
		/*------------------------------------------------------------------------*/
		
		/*--------------------CREATION OF EDGES-----------------------------------*/
		Edge<Building> e1 = new Edge<Building>(60,v1,v2);
		IOHandling.writeEdgeToFile(e1);
		Edge<Building> e2 = new Edge<Building>(120,v2,v1);
		IOHandling.writeEdgeToFile(e2);
		Edge<Building> e3 = new Edge<Building>(320,v3,v2);
		IOHandling.writeEdgeToFile(e3);
		Edge<Building> e4 = new Edge<Building>(220,v4,v2);
		IOHandling.writeEdgeToFile(e4);
		/*------------------------------------------------------------------------*/
		
		/*--------------------CREATING ARRAYLIST -----------------------------------*/
		ArrayList<Edge<Building>> eList = new ArrayList<Edge<Building>>();
		eList.add(e1);
		eList.add(e2);
		eList.add(e3);
		eList.add(e4);
		
		ArrayList<Vertex<Building>> vList = new ArrayList<Vertex<Building>>();
		vList.add(v1);
		vList.add(v2);
		vList.add(v3);
		vList.add(v4);
		/*--------------------------------------------------------------------------*/
	
		/*--------------------CREATING GRAPH ---------------------------------------*/
		System.out.println("_________________________________________________");
		Graph<Building> graph = new Graph<Building> (vList, eList);
		System.out.println(graph.toString());
		System.out.println("_________________________________________________");
		/*--------------------------------------------------------------------------*/
		
		/*--------------------CREATING GRAPH WITH IOHANDLING -----------------------*/
		
		IOHandling.writeVertexToFile(v0);													//added the vertices to file after the edges had been done else it didn't add properly
		IOHandling.writeVertexToFile(v1);											//added the vertices to file after the edges had been done else it didn't add properly
		IOHandling.writeVertexToFile(v2);
		IOHandling.writeVertexToFile(v3);
		IOHandling.writeVertexToFile(v4);
		
//		ArrayList<Edge<Building>> eeList = IOHandling.readEdge();
//		ArrayList<Vertex<Building>> vvList= IOHandling.readVertex();
//		System.out.println("--------------------this is the edges from file -----------------------");
//		System.out.println(eeList);
//		System.out.println("-----------------------------------------------------------------------");
//		System.out.println("--------------------this is the edges from system ---------------------");
//		System.out.println(eList);
//		System.out.println("-----------------------------------------------------------------------");
//		System.out.println("--------------------this is the vertices from file --------------------");
//		System.out.println(vvList);
//		System.out.println("-----------------------------------------------------------------------");
//		System.out.println("--------------------this is the vertices from system ------------------");
//		System.out.println(vList);
//		System.out.println("-----------------------------------------------------------------------");
//		//ArrayList<Graph<Building>> graph = IOHandling.readGraph();
//		Graph<Building> graph1 = new Graph<Building> (vvList, eeList);
//		//IOHandling.writeGraph(graph1);
//		System.out.println("--------------------this is the graph from system ---------------------");
//		System.out.println(graph1.toString());
//		System.out.println("-----------------------------------------------------------------------");
//		System.out.println("--------------------this is the graph from File -----------------------");
//		System.out.println(graph);
//		System.out.println("-----------------------------------------------------------------------");
		/*--------------------------------------------------------------------------*/
		
		/*--------------------ADD INDIVIDUAL TO GRAPH ------------------------------*/
		/*Graph <Building> b = new Graph <Building> ();
		b.getVertices().add(v1);
		System.out.println(b.getVertices().toString());*/
		/*--------------------------------------------------------------------------*/
	}
	
}