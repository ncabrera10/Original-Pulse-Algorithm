/**
 * This class represents a graph. It is used for the SP and the pulse!!!
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.jgrapht.EdgeFactory;
import org.jgrapht.Graph;

public class PulseGraph  implements Graph<VertexPulse, EdgePulse> {
	
	/**
	 * The nodes
	 */
	static VertexPulse[] vertexes;
	/**
	 * Number of nodes
	 */
	private int numNodes;
	/**
	 * SP stuff
	 */
	private int Cd;
	private int Ct;
	/**
	 * Time constraint
	 */
	static double TimeC;
	/**
	 * Primal bound
	 */
	static double PrimalBound;
	/**
	 * The best solution found is globally stored here 
	 */
	static ArrayList<Integer> Path;
	/**
	 * The time for the best solution found (the distance is stored in the primal bound)
	 */
	static double TimeStar;
	/**
	 * Binary indicator to know if visiting a node creates a cycle
	 */
	static int[] Visited;
	
	
	/**
	 * creates a pulse graph
	 * @param numNodes
	 */
	public PulseGraph( int numNodes) {
		super();
		this.numNodes = numNodes;
		Cd=0;
		Ct=0;
		vertexes = new VertexPulse[numNodes];
		Path= new ArrayList<Integer>();
		Visited = new int[numNodes];
	}
	

	/**
	 * Adds an edge
	 */
	public EdgePulse addEdge(VertexPulse sourceVertex, VertexPulse targetVertex) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Returns the number of nodes
	 * @return
	 */
	public  int getNumNodes()
	{
		return numNodes;
	}
	/**
	 * Returns the node searched by an id
	 * @param id
	 * @return
	 */
	public VertexPulse getVertexByID(int id){
		return vertexes[id];
	}
	
	/**
	 * Adds an edge with a weight
	 * @param sourceVertex arc tail
	 * @param targetVertex arc head
	 * @param d arc distance
	 * @param t arc time
	 * @param id arc id
	 * @return
	 */
	public EdgePulse addWeightedEdge(VertexPulse sourceVertex, VertexPulse targetVertex, int d, int t, int id) {
		//We keep track of the higher distance and the higher time in the network
		if(d>Cd){
			Cd=d;
		}
		if(t>Ct){
			Ct=t;
		}
		//To the head node we add an incoming arc
		vertexes[targetVertex.getID()].addReversedEdge(new EdgePulse(d, t, targetVertex , sourceVertex, id));
		//To the tail node we add an outgoing arc
		vertexes[sourceVertex.getID()].magicIndex.add(id);
		return null;
	}
	
	
	/**
	 * Adds and edge
	 */
	public boolean addEdge(VertexPulse sourceVertex, VertexPulse targetVertex, EdgePulse e) {
		return false;
	}

	/**
	 * Adds a vertex. The position in the list is the node id
	 */
	public boolean addVertex(VertexPulse v) {
		vertexes[v.getID()] = v;
		return true;
	}
	/**
	 * Adds a final vertex. The position in the list is the node id
	 * @param v
	 * @return
	 */
	public boolean addFinalVertex(FinalVertexPulse v) {
		vertexes[v.getID()] = v;
		return true;
	}

	/**
	 * Verifies if the edge is in
	 */
	public boolean containsEdge(VertexPulse sourceVertex,
			VertexPulse targetVertex) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Verifies if the edge is in
	 */
	public boolean containsEdge(EdgePulse e) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Verifies if the node is in
	 */
	public boolean containsVertex(VertexPulse v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<EdgePulse> edgeSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<EdgePulse> edgesOf(VertexPulse vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<EdgePulse> getAllEdges(VertexPulse sourceVertex,
			VertexPulse targetVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EdgePulse getEdge(VertexPulse sourceVertex, VertexPulse targetVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EdgeFactory<VertexPulse, EdgePulse> getEdgeFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VertexPulse getEdgeSource(EdgePulse e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VertexPulse getEdgeTarget(EdgePulse e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getEdgeWeight(EdgePulse e) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean removeAllEdges(Collection<? extends EdgePulse> edges) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<EdgePulse> removeAllEdges(VertexPulse sourceVertex,
			VertexPulse targetVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAllVertices(Collection<? extends VertexPulse> vertices) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EdgePulse removeEdge(VertexPulse sourceVertex,
			VertexPulse targetVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeEdge(EdgePulse e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeVertex(VertexPulse v) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<VertexPulse> vertexSet() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Returns the distance bound
	 * @return
	 */
	public int getCd()
	{
		return Cd;
	}
	/**
	 * Returns the time bound
	 * @return
	 */
	public int getCt()
	{
		return Ct;
	}
	
	/**
	 * Restarts the network
	 */
	public void resetNetwork(){
		for (int i = 0; i < numNodes ; i++) {
			vertexes[i].reset();
		}
	}
	
	/**
	 * Sets the constraint
	 * @param timeC
	 */
	public void SetConstraint(double timeC) {
		
		PulseGraph.TimeC=timeC;
		
	}
	/**
	 * Sets the primal bound
	 * @param bound
	 */
	public void setPrimalBound(int bound) {
		
		PulseGraph.PrimalBound=bound;
		
	}
	
	


}
