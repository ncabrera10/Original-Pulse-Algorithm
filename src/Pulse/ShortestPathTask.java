/**
 * This class is used for parallelizing the SP algorithm.
 * 
 * Ref.: Lozano, L. and Medaglia, A. L. (2013). 
 * On an exact method for the constrained shortest path problem. Computers & Operations Research. 40 (1):378-384.
 * DOI: http://dx.doi.org/10.1016/j.cor.2012.07.008 
 * 
 * 
 * @author L. Lozano & D. Duque
 * @affiliation Universidad de los Andes - Centro para la Optimización y Probabilidad Aplicada (COPA)
 * @url http://copa.uniandes.edu.co/
 * 
 */
package Pulse;

public class ShortestPathTask implements Runnable {

	/**
	 * The dijkstra for distance
	 */
	private DukqstraDist spDist; 
	/**
	 * The dijkstra for time
	 */
	private DukqstraTime spTime;
	/**
	 * A boolean indicator to know if it is for time or for distance
	 */
	private int algoRuning;
	/**
	 * The main method. Creates a task
	 * @param quienEs if it is for time or for distance
	 * @param dD
	 * @param dT
	 */
	public ShortestPathTask(int quienEs, DukqstraDist dD, DukqstraTime dT) {
		//quienES 1 Dist, 0 Time;
		algoRuning = quienEs;
		if(quienEs==1){
			spDist = dD;
		}else{
			spTime = dT;
		}
	}
	/**
	 * This method runs the desired dijkstra
	 */
	public void run() {
		// TODO Auto-generated method stub
		if(algoRuning==1){
			spDist.runAlgDist();
		}else{
			spTime.runAlgTime();
		}
	}

	
	
}
