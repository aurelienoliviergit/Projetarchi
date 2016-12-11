package coucheBasse;
import java.util.ArrayList;

public abstract class ListeClasses {

	private ArrayList<String> binaryName;

	/**
	 * @return the binaryName
	 */
	public ArrayList<String> getBinaryName() {
		return binaryName;
	}

	/**
	 * @param binaryName the binaryName to set
	 */
	public void setBinaryName(ArrayList<String> binaryName) {
		this.binaryName = binaryName;
	}
}
