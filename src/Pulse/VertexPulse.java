/**
 * This class represents a node, contains the pulse main logic.
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

public class VertexPulse {
	/**
	 * This array contains the indexes for all the outgoing arcs from this node
	 */
	ArrayList<Integer> magicIndex;
	
	/**
	 * Labels for dominance prunning
	 */
	
	ArrayList<Label> labels;
	
	/**
	 * Boolean that tells if the node is visited for first time
	 */
	boolean firstTime = true;
	/**
	 * Bounds to reach the end node
	 */
	int minDist;
	int maxTime;
	int minTime;
	int maxDist;
		
	/**
	 * SP stuff
	 */
	public static final int infinity = (int)Double.POSITIVE_INFINITY;
	/**
	 * The edge that is coming to the node
	 */
	private EdgePulse reverseEdges;
	/**
	 * The vertex id
	 */
	private int id;
	private VertexPulse leftDist;
	private VertexPulse rigthDist;
	private VertexPulse leftTime;
	private VertexPulse rigthTime;
	private boolean insertedDist;
	private boolean insertedTime;
	
	/**
	 * Creates a node
	 * @param i the id
	 */
	public VertexPulse(int i) {
		id = i;
		insertedDist = false;
		minDist = infinity;
		minTime = infinity;
		maxTime = 0;
		maxDist = 0;

		leftDist = this;
		rigthDist = this;
		leftTime = this;
		rigthTime = this;
		labels = new ArrayList<Label>();

		magicIndex = new ArrayList<Integer>();
	}
	
	/**
	 * Returns the node id
	 * @return
	 */
	public int  getID()
	{
		return id;
	}
	
	/**
	 * Adds an edge to the coming arcs list
	 * @param e the edge
	 */
	public void addReversedEdge(EdgePulse e)
	{
		if(reverseEdges!=null){
			reverseEdges.addNextCommonTailEdge(e);
		}else
			reverseEdges = e;
	}
	
	/**
	 * Returns the list of reversed edges
	 * @return
	 */
	public EdgePulse getReversedEdges() {
		if(reverseEdges!= null){
			return reverseEdges;
		}return new EdgePulse(1,1, this,this , -1);
	}
	
	/**
	 * Sets the minimum distance
	 * @param c
	 */
	public void setMinDist(int c){
		minDist = c;
	}
	
	/**
	 * Returns the minimum distance
	 * @return
	 */
	public int getMinDist(){
		return minDist;
	}
	
	/**
	 * Sets the maximum time
	 * @param mt
	 */
	public void setMaxTime(int mt){
		maxTime = mt;
	}
	/**
	 * Returns the maximum time
	 * @return
	 */
	public int getMaxTime(){
		return maxTime;
	}
	/**
	 * Sets the minimum time
	 * @param t
	 */
	public void setMinTime(int t){
		minTime = t;
	}
	/**
	 * Returns the minimum time
	 * @return
	 */
	public int getMinTime(){
		return minTime;
	}
	/**
	 * Sets the maximum distance
	 * @param md
	 */
	public void setMaxDist(int md){
		maxDist = md;
	}
	/**
	 * Returns the maximum distance
	 * @return
	 */
	public int getMaxDist(){
		return maxDist;
	}
	
	
	/**
	 * Unlink a vertex from the bucket
	 * @return true, if the buckets gets empty
	 */
	public boolean unLinkVertexDist(){
		if(rigthDist.getID() == id){
			leftDist=this;
			rigthDist=this;
			return true;
		}else{
			leftDist.setRigthDist(rigthDist);
			rigthDist.setLeftDist(leftDist);
			leftDist = this;
			rigthDist = this;
			return false;
		}
	}
	/**
	 * 
	 * @return
	 */
	public boolean unLinkVertexTime(){
		if(rigthTime.getID() == id){
			leftTime=this;
			rigthTime=this;
			return true;
		}else{
			leftTime.setRigthTime(rigthTime);
			rigthTime.setLeftTime(leftTime);
			leftTime = this;
			rigthTime = this;
			return false;
		}
	}

	public void fastUnlinkDist(){
		leftDist=this;
		rigthDist=this;
	}
	public void fastUnlinkTime(){
		leftTime = this;
		rigthTime = this;
	}
	public void unlinkRighBoundDist()
	{
		rigthDist = null;
	}
	public void unlinkRighBoundTime()
	{
		rigthTime = null;
	}
	/**
	 * Insert a vertex in a bucket. 
	 * New vertex is inserted on the left of the bucket entrance 
	 * @param v vertex in progress to be inserted
	 */
	public void insertVertexDist(VertexPulse v) {
		v.setLeftDist(leftDist);
		v.setRigthDist(this);
		leftDist.setRigthDist(v);
		leftDist = v;
	}
	
	/**
	 * 
	 * @param v
	 */
	public void insertVertexTime(VertexPulse v) {
		v.setLeftTime(leftTime);
		v.setRigthTime(this);
		leftTime.setRigthTime(v);
		leftTime = v;
	}
	
	/**
	 * Distance basic methods
	 */
	public void setLeftDist(VertexPulse v){
		leftDist= v;
	}
	public void setRigthDist(VertexPulse v){
		rigthDist= v;
	}
	public VertexPulse getBLeftDist(){
		return leftDist;
	}
	public VertexPulse getBRigthDist(){
		return rigthDist;
	}
	public void setInsertedDist(){
		insertedDist = true;
	}
	public boolean isInserteDist(){
		return insertedDist;
	}
	/**
	 * Time basic methods
	 */
	public void setLeftTime(VertexPulse v){
		leftTime= v;
	}
	public void setRigthTime(VertexPulse v){
		rigthTime= v;
	}
	public VertexPulse getBLeftTime(){
		return leftTime;
	}
	public VertexPulse getBRigthTime(){
		return rigthTime;
	}
	public void setInsertedTime(){
		insertedTime = true;
	}
	public boolean isInsertedTime(){
		return insertedTime;
	}
	
	
	
	
	/**
	 * 
	 */
	public void reset(){
		insertedDist = false;
	}
	
	// This is the pulse procedure
	/**
	 * This is the pulse procedure
	 * @param PTime
	 * @param PDist
	 * @param path
	 */
	public void pulse(int[]pulseWeights,ArrayList<Integer>path) 
	{
		// if a node is visited for first time, sort the arcs
		if (this.firstTime) {
			this.firstTime = false;
			this.Sort(this.magicIndex); 
			leftDist = null;
			rigthDist = null;
			reverseEdges = null;
		}
		
		// Label update
		changeLabels(pulseWeights); 
			
			// Check for cycles
			if (PulseGraph.Visited[id]==0) {  
				
				// Update the visit indicator
				PulseGraph.Visited[id]=1;
				path.add(id);
				
				// Pulse all the head nodes for the outgoing arcs
				for (int i = 0; i < magicIndex.size(); i++) { 
					
					// Update distance and time
					int e = (Integer) magicIndex.get(i);
					int[] newWeights = new int[2];
					newWeights[0] = (pulseWeights[0] + DataHandler.Time[e]); //Tiempo del camino al agregar el nodo
					newWeights[1] = (pulseWeights[1] + DataHandler.Distance[e]); //Distancia del camino al agregar el nodo			
					int a = DataHandler.Arcs[e][1];
	
					// Pruning strategies: infeasibility, bounds and labels
	
					if ( (newWeights[0] + PulseGraph.vertexes[a].getMinTime() <= PulseGraph.TimeC) && (newWeights[1] + PulseGraph.vertexes[a].getMinDist() < PulseGraph.PrimalBound) && !PulseGraph.vertexes[a].CheckLabels(newWeights,a)){
						
						// If not pruned the pulse travels to the next head node
						PulseGraph.vertexes[a].pulse(newWeights,path);
						
						
					}
				}
				PulseGraph.Visited[id]=0;
				path.remove(path.size()-1);
			}
		
	}
	
	
		
	/**
	 * This method sorts the arcs
	 * @param set
	 */
	private void Sort(ArrayList<Integer> set) 
	{
		QS(magicIndex, 0, magicIndex.size()-1);
	}
	
	/**
	 * 
	 * @param e
	 * @param b
	 * @param t
	 * @return
	 */
	public int colocar(ArrayList<Integer> e, int b, int t)
	{
	    int i;
	    int pivote, valor_pivote;
	    int temp;
	   
	    pivote = b;
	    valor_pivote = PulseGraph.vertexes[DataHandler.Arcs[e.get(pivote)][1]].getCompareCriteria();
	    for (i=b+1; i<=t; i++){
	        if (PulseGraph.vertexes[DataHandler.Arcs[e.get(i)][1]].getCompareCriteria() < valor_pivote){
	                pivote++;    
	                temp= e.get(i);
	                e.set(i, e.get(pivote));
	                e.set(pivote, temp);
	        }
	    }
	    temp=e.get(b);
	    e.set(b, e.get(pivote));
        e.set(pivote, temp);
	    return pivote;
	} 
	 
	 
	/**
	 * 
	 * @param e
	 * @param b
	 * @param t
	 */
	public void QS(ArrayList<Integer> e, int b, int t)
	{
		 int pivote;
	     if(b < t){
	        pivote=colocar(e,b,t);
	        QS(e,b,pivote-1);
	        QS(e,pivote+1,t);
	     }  
	}
	/**
	 * This method verifies dominance
	 * @param PTime
	 * @param PDist
	 * @return true if the label is dominated
	 */
	public boolean CheckLabels(int[] pulse_weights,int node)
	// Label pruning strategy
	{
		for (int i = 0; i < PulseGraph.vertexes[node].labels.size(); i++) {
			if (PulseGraph.vertexes[node].labels.get(i).dominateLabel(pulse_weights)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * This method modifies the labels.
	 * @param PTime the current pulse time
	 * @param PDist the current pulse distance
	 */
	private void changeLabels(int[] objs) {
		
		if (labels.size() == 0) {
			labels.add(new Label(objs));
		} else if (labels.size() == 1) {
			labels.add(objs[0] < labels.get(0).attributes[0] ? 0 : 1, new Label(objs));
		} else if (labels.size() == 2) {
			labels.add(objs[1] < labels.get(1).attributes[1] ? 0 : 1, new Label(objs));
		}else {
			if (labels.size() < DataHandler.numLabels) {
				insertLabel1(objs);
			} else {
				if(DataHandler.numLabels > 2) {
					int luck = DataHandler.r.nextInt(DataHandler.numLabels-2)+2;
					labels.remove(luck);
					insertLabel1(objs);
				}
			}
		}
		
	}

	/**
	 * This method inserts a label based on their time
	 * @param objs
	 */
	private void insertLabel1(int[] objs) {
		Label np = new Label(objs);
		double cScore = np.attributes[0];
		
		boolean cond = true;
		int l = 0;
		int r = labels.size();
		int m = (int) ((l + r) / 2);
		double mVal = 0;
		if (labels.size() == 0) {
			labels.add(np);
			return;
		} else if (labels.size() == 1) {
			mVal = labels.get(m).attributes[0];;
			labels.add(cScore < mVal ? 0 : 1, np);
			return;
		} else {
			mVal = labels.get(m).attributes[0];
		}

		while (cond) {
			if (r - l > 1) {
				if (cScore < mVal) {
					r = m;
					m = (int) ((l + r) / 2);
				} else if (cScore > mVal) {
					l = m;
					m = (int) ((l + r) / 2);
				} else {
					labels.set(m, np);
					return;
				}
				mVal = labels.get(m).attributes[0];;
			} else {
				cond = false;
				if (l == m) {
					labels.add(cScore < mVal ? l : l + 1, np);
				} else if (r == m) {
					System.out.println("esto no pasa ");
					labels.add(cScore < mVal ? r : Math.min(r + 1, labels.size()), np);
				} else {
					System.err.println(VertexPulse.class +  " insert label, error");
				}
				return;

			}
		}
		
	}

	
	/**
	 * 
	 * @return
	 */
	public int getCompareCriteria(){
		return getMinDist();
	}


}

