/**
 * This is a class that holds all the relevant data for an instance.
 * 
 * Ref.: Lozano, L. and Medaglia, A. L. (2013). 
 * On an exact method for the constrained shortest path problem. Computers & Operations Research. 40 (1):378-384.
 * DOI: http://dx.doi.org/10.1016/j.cor.2012.07.008 
 * 
 * 
 * @author L. Lozano & D. Duque
 * @affiliation Universidad de los Andes - Centro para la Optimizaci�n y Probabilidad Aplicada (COPA)
 * @url http://copa.uniandes.edu.co/
 * 
 */
package Pulse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class DataHandler {
	

	/**
	 * Number of arcs
	 */
	int NumArcs;
	/**
	 * Number of nodes
	 */
	static int NumNodes;
	/**
	 * Destination node
	 */
	int LastNode;
	/**
	 * Source node
	 */
	int Source;
	/**
	 * All the arcs in the network stored in a vector where Arcs[i][0]= Tail for arc i and Arcs[i][1]= Head for arc i 
	 */
	static int[][] Arcs;
	/**
	 * The distance attribute for any arc i
	 */
	static int[] Distance;
	/**
	 * The time attribute for any arc i
	 */
	static int[] Time;
	/**
	 * Data structure for storing the graph
	 */
	private PulseGraph Gd;
	
	/**
	 * Network ID
	 */
	private int networkId;
	
	/**
	 * Acronymum used for the network, example NY
	 */
	private String acro;
	
	/**
	 * Number of labels for the pruning by dominance strategy
	 */
	static int numLabels;
	
	/**
	 * Random number generator
	 */
	static Random r = new Random(0);

	/**
	 * Read data from an instance
	 * @param numNodes
	 * @param numArcs
	 * @param sourceNode
	 * @param lastNode
	 * @param netId
	 */
	public DataHandler(int numNodes, int numArcs, int sourceNode, int lastNode, int netId, String acronym) {
		
		//Retrieves the information of the instance
		NumArcs = numArcs;
		NumNodes = numNodes;
		LastNode = lastNode;
		Source = sourceNode;
		setNetworkId(netId);
		acro = acronym;
		
		//Creates the list of arcs. A list of distances and a list of times   --- Serian independientes del sentido de la red ! 
		Arcs = new int[numArcs][2];
		Distance = new int[numArcs];
		Time = new int[numArcs];
		
		//Creates the graph
		Gd = new PulseGraph(NumNodes);
	}

		
	/**
	 * This procedure creates the nodes for the graph
	 */
	public void upLoadNodes(){
		// All nodes are VertexPulse except the final node
		for (int i = 0; i < NumNodes; i++) {
			if(i!=(LastNode-1)){
				Gd.addVertex(new VertexPulse(i) ); //Primero lo creo, y luego lo meto. El id corresponde al n�mero del nodo
			}
		}
		// The final node is a FinalVertexPulse 
		FinalVertexPulse vv = new FinalVertexPulse(LastNode-1);
		Gd.addFinalVertex(vv);
	}
	
	/**
	 * This procedure returns a graph
	 * @return the graph
	 */
	public PulseGraph getGd()
	{
		return Gd;
	}

	/**
	 * This procedure reads data from a data file in DIMACS format (forward direction)
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void ReadDimacsF() throws NumberFormatException, IOException {
		File file2 = null;
		file2 = new File("./networks/"+acro);
		@SuppressWarnings("resource")
		BufferedReader bufRdr2 = new BufferedReader(new FileReader(file2));
		String line2 = null;
		int row2 = 0;
		while ((line2 = bufRdr2.readLine()) != null && row2 < NumArcs) {
			String[] Actual = line2.split(" ");
			Arcs[row2][0] = Integer.parseInt(Actual[0])-1;
			Arcs[row2][1] =  Integer.parseInt(Actual[1])-1;
			Distance[row2] = Integer.parseInt(Actual[2]);
			Time[row2] = Integer.parseInt(Actual[3]);
			row2++;
		}
	}
	
	/**
	 * This procedure reads data from a data file in DIMACS format (backward direction)
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void ReadDimacsB() throws NumberFormatException, IOException {
		File file2 = null;
		file2 = new File("./networks/"+acro);
		@SuppressWarnings("resource")
		BufferedReader bufRdr2 = new BufferedReader(new FileReader(file2));
		String line2 = null;
		int row2 = 0;
		while ((line2 = bufRdr2.readLine()) != null && row2 < NumArcs) {
			String[] Actual = line2.split(" ");
			Arcs[row2][1] = Integer.parseInt(Actual[0])-1;
			Arcs[row2][0] =  Integer.parseInt(Actual[1])-1;
			Distance[row2] = Integer.parseInt(Actual[2]);
			Time[row2] = Integer.parseInt(Actual[3]);
			row2++;
		}
	}


	public int getNetworkId() {
		return networkId;
	}


	public void setNetworkId(int networkId) {
		this.networkId = networkId;
	}
}

