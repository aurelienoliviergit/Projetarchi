package coucheBasse;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;

public interface RepresentationProjet {
	public Class[] getClasses();

	public ArrayList<Integer> getExtends(int i);// Retourn les indexs des
	// classes implementes
	// par la classe i

	public ArrayList<Integer> getImplements(int i);// Retourn les indexs des
													// interfaces implementes
													// par la classe i

	public ArrayList<Integer> getExtendedBy(int i);// Retourn les indexs des
	// classes qui extends
	// la classe i

	public ArrayList<Integer> getImplementsBy(int i);// Retourn les indexs des
	// classes qui implement
	// la classe i

	public default Method[] getCleanMethods(int i) {
		Method[] tab = this.getClasses()[i].getDeclaredMethods().clone();

		return RepresentationProjet.process(tab).clone();

	}

	public static Method[] getCleanMethods(Class c) {
		Method[] tab = c.getDeclaredMethods().clone();
		return RepresentationProjet.process(tab).clone();

	}

	public static Method[] process(Method[] tab) {
		ArrayList<String> methodsToRemove = new ArrayList<>();
		methodsToRemove.add("wait");
		methodsToRemove.add("notifyAll");
		methodsToRemove.add("equals");
		methodsToRemove.add("hashCode");
		methodsToRemove.add("toString");
		methodsToRemove.add("notify");
		methodsToRemove.add("getClass");

		int size = 0;
		for (int p = 0; p < tab.length; p++) {
			if (!methodsToRemove.contains(tab[p].getName())) {
				size++;
			}
		}
		int k = 0;
		Method[] tabClean = new Method[size];
		for (int p = 0; p < tab.length; p++) {
			if (!methodsToRemove.contains(tab[p].getName())) {
				tabClean[k] = tab[p];
				k++;
			}
		}
		return tabClean;

	}

	public default int getPlusLongueChaineDeExtends() {
		int a = 0, max = 0, j;
		HashSet<Integer> tabou = new HashSet<>();
		for (int i = 0; i < this.getClasses().length; i++) {
			j = i;
			a = 0;
			tabou.clear();

			while (!this.getExtends(j).isEmpty() && !tabou.contains(j)) {
				tabou.add(j);
				a++;
				j = this.getExtends(j).get(0);
			}
			if (a > max) {
				max = a;
			}
		}
		return max;
	}

}
