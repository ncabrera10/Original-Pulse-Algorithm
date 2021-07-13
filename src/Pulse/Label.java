package Pulse;

public class Label {
	
public int[] attributes;
	
	public Label(int[] n_attris) {
		attributes = n_attris.clone();
	}
	/**
	 * True if this dominates param
	 * @param newLabel_attributes
	 * @return
	 */
	public boolean dominateLabel(int[] newLabel_attributes) {
		for (int i = 0; i < newLabel_attributes.length; i++) {
			if(attributes[i]>newLabel_attributes[i]){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "";
	}
}
