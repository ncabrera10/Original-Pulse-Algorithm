/**
 * This class defines an instance
 * 
 * Ref.: Lozano, L. and Medaglia, A. L. (2013). 
 * On an exact method for the constrained shortest path problem. Computers & Operations Research. 40 (1):378-384.
 * DOI: http://dx.doi.org/10.1016/j.cor.2012.07.008 
 * 
 * 
 * @author L. Lozano
 * @affiliation Universidad de los Andes - Centro para la Optimizaci�n y Probabilidad Aplicada (COPA)
 * @url http://copa.uniandes.edu.co/
 * 
 */

package Pulse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Settings {

	/**
	 * The data file txt with the nodes and arcs
	 */
	String DataFile;
	/**
	 * The total number of arcs
	 */
	int NumArcs;
	/**
	 * The total number of nodes
	 */
	int NumNodes;
	/**
	 * The id of the last node
	 */
	int LastNode;
	/**
	 * The source node
	 */
	int Source;
	/**
	 * The resource constraint
	 */
	double TimeC;
	
	/**
	 * This method creates the instance
	 * @param ConfigFile
	 * @throws IOException
	 */
	public Settings(String ConfigFile) throws IOException{
		
		File file = new File(ConfigFile);
		 
		@SuppressWarnings("resource")
		BufferedReader bufRdr  = new BufferedReader(new FileReader(file));
		String line = null;
		
		String [][] readed = new String [6][2];
		
		int row = 0;
		int col = 0;
	 
		//read each line of text file
		while((line = bufRdr.readLine()) != null && row < 6)
		{	
		StringTokenizer st = new StringTokenizer(line,":");
		while (st.hasMoreTokens())
		{
			//get next token and store it in the array
			readed[row][col] = st.nextToken();
			col++;
			
		}
		col = 0;
		row++;
		
		}
				
		DataFile=readed[0][1];						//Name of the txt
		NumArcs=Integer.parseInt(readed[1][1]);		//The total number of arcs
		NumNodes=Integer.parseInt(readed[2][1]);	//the total number of nodes
		TimeC=Double.parseDouble(readed[3][1]);		//The resource constraint
		Source=Integer.parseInt(readed[4][1]);		//The source id
		LastNode=Integer.parseInt(readed[5][1]);	//The last node id
		
		//Si quisiera correr la red al reves..simplemente tendria que cambiar el source y el last node.
		
	}
	
	
}
