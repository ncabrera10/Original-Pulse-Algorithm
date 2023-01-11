/**
 * This is the main class for the pulse algorithm without acceleration strategies. 
 * 
 * Ref.: Lozano, L. and Medaglia, A. L. (2013). 
 * On an exact method for the constrained shortest path problem. Computers & Operations Research. 40 (1):378-384.
 * DOI: http://dx.doi.org/10.1016/j.cor.2012.07.008 
 * 
 * 
 * @author L. Lozano & D. Duque & N. Cabrera
 * @affiliation Universidad de los Andes - Centro para la Optimizaci�n y Probabilidad Aplicada (COPA)
 * @url http://copa.uniandes.edu.co/
 * 
 */

package Pulse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PulseMain {

	/**
	 * This method runs the pulse
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		
		//Create the test instance:
		
			File testFile = new File("./networks/Config.txt"); // Go to the file to modify the instance TODO
			
			@SuppressWarnings("resource")
			BufferedReader bufRedr = new BufferedReader(new FileReader(testFile));
			
			String actLine = null;
			
			String [] information = new String [6];
			
			int rowA = 0;
		
			while((actLine = bufRedr.readLine()) != null && rowA < 6){	
				String [] info = actLine.split(":");
				information[rowA] = info[1];
				rowA++;
			}

		//Choose the direction TODO
			
			String dir = "f";
			
		//Load the network and initialize
			
			DataHandler dataA = null;
			PulseGraph network = null;
					
			if(dir.equals("b")) {
				
				//Backward direction: 
	
				dataA = new DataHandler(Integer.parseInt(information[2]),Integer.parseInt(information[1]),Integer.parseInt(information[5]),Integer.parseInt(information[4]),1,information[0]);	
				dataA.ReadDimacsB();
				network = createGraph(dataA);	
				network.SetConstraint(Integer.parseInt(information[3]));
			
			}else if(dir.equals("f")) {
				
				//Forward direction: 
				
				dataA = new DataHandler(Integer.parseInt(information[2]),Integer.parseInt(information[1]),Integer.parseInt(information[4]),Integer.parseInt(information[5]),1,information[0]);
				dataA.ReadDimacsF();
				network = createGraph(dataA);	
				network.SetConstraint(Integer.parseInt(information[3]));
			}
			
		
		//Initial bounds
		
			SP(dataA,network);

			
		//Pulse algorithm parameters:
		
			//First primal bound
		
			int MD=network.getVertexByID(dataA.Source-1).getMaxDist();
			network.setPrimalBound(MD);
			PulseGraph.TimeStar = network.getVertexByID(dataA.Source-1).getMinTime();
			
			//Size of Q TODO
			
			DataHandler.numLabels = 10;

			//Initial weights
			
			int[] initialWeights = new int[2];
			initialWeights[0] = 0;
			initialWeights[1] = 0;
		
		//Run the pulse and recover the path:
		
			//Starts the clock
			
			double ITime = System.nanoTime(); 
		
			ArrayList<Integer> finalPath = new ArrayList<Integer>();
			network.getVertexByID(dataA.Source-1).pulse(initialWeights,finalPath);
		
			//Ends the clock
			
			double FTime = (System.nanoTime());
		
		//Print results
		
			System.out.println("-----------Main results------------");		
			System.out.println("Network: "+information[0]);
			System.out.println("Time limit: "+PulseGraph.TimeC);
			System.out.println("Time star: "+PulseGraph.TimeStar);
			System.out.println("Initial Primal Bound: "+MD);
			System.out.println("Final Primal Bound: "+PulseGraph.PrimalBound);
			System.out.println("Final path: "+PulseGraph.Path.toString());
			System.out.println("Computational time: "+(FTime-ITime)/1000000000);
			System.out.println("------------------------------------");

	}
	
	/**
	 * This method creates a network
	 * @param data
	 * @return the final graph
	 */
	private static PulseGraph createGraph(DataHandler data) {
		int numNodes = DataHandler.NumNodes;
		PulseGraph Gd = new PulseGraph(numNodes);
		for (int i = 0; i < numNodes; i++) {
			if(i!=(data.LastNode-1)){
				Gd.addVertex(new VertexPulse(i) ); //Primero lo creo, y luego lo meto. El id corresponde al n�mero del nodo
			}
		}
		FinalVertexPulse vv = new FinalVertexPulse(data.LastNode-1);
		Gd.addFinalVertex(vv);
		for(int i = 0; i <data.NumArcs; i ++){
			Gd.addWeightedEdge( Gd.getVertexByID(DataHandler.Arcs[i][0]), Gd.getVertexByID(DataHandler.Arcs[i][1]),DataHandler.Distance[i],DataHandler.Time[i], i);			
		}
		return Gd;
	}
	
	/**
	 * This method runs a shortest path for cost and times
	 * @param data
	 * @param network
	 * @throws InterruptedException
	 */
	private static void SP(DataHandler data, PulseGraph network) throws InterruptedException {
		// Create two threads and run parallel SP for the initialization		
		Thread tTime = new Thread();
		Thread tDist = new Thread();
		// Reverse the network and run SP for distance and time 
		DukqstraDist spDist = new DukqstraDist(network, data.LastNode-1);
		DukqstraTime spTime = new DukqstraTime(network, data.LastNode-1);
		tDist = new Thread(new ShortestPathTask(1, spDist, null));
		tTime = new Thread(new ShortestPathTask(0, null,  spTime));
		tDist.start();
		tTime.start();
		tDist.join();
		tTime.join();
	}
	
	
	

	
}
