package coucheBasse;

import java.util.ArrayList;

public interface RepresentationProjet {
	public Class[] getClasses();
	public int getExtends(int i);// Retourn l'index j de la superclass de la classe i
	public ArrayList<Integer> getImplements(int i);//Retourn les indexs des interfaces implémentés par la classe i
	
}
