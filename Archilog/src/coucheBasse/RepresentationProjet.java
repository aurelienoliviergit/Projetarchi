package coucheBasse;

import java.lang.reflect.Method;
import java.util.ArrayList;

public interface RepresentationProjet {
	public Class[] getClasses();
	public int getExtends(int i);// Retourn l'index j de la superclass de la classe i
	public ArrayList<Integer> getImplements(int i);//Retourn les indexs des interfaces implémentés par la classe i
	public default Method[] getCleanMethods(int i){
		
		Method[] tab = this.getClasses()[i].getMethods().clone();
		ArrayList<String> methodsToRemove = new ArrayList<>();
		methodsToRemove.add("wait");
		methodsToRemove.add("notifyAll");
		methodsToRemove.add("equals");
		methodsToRemove.add("hashCode");
		methodsToRemove.add("toString");
		methodsToRemove.add("notify");
		methodsToRemove.add("getClass");
		
		int size=0;
		for(int p=0;p<tab.length;p++){
			if(!methodsToRemove.contains(tab[p].getName())){
				size++;
			}
		}
		int k=0;
		Method[] tabClean = new Method[size];
		for(int p=0;p<tab.length;p++){
			if(!methodsToRemove.contains(tab[p].getName())){
				tabClean[k]=tab[p];
				k++;
			}
		}
		return tabClean;
		
	}
	
}
