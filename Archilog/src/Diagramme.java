import java.util.ArrayList;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;



public class Diagramme {

	private int[][] tab;
	private ArrayList<Bean> noeuds;
	public static final int Implement = 2;
	public static final int Extends = 1;
	
	public Diagramme(String path) {
		this(new File(path));
	}

	public Diagramme(File path) {

		String[] listefichiers;
		listefichiers = path.list();

		URL[] cp = new URL[1];
		try {
			cp[0] = path.toURL();
			System.out.println(cp[0]);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ClassLoader urlcl = new URLClassLoader(cp);
		Class c;
		try {
			c = urlcl.loadClass("session1.demo.NatDecimal");

			// c=urlcl.loadClass(listefichiers[i].substring(0,
			// listefichiers[i].length() - 5));
			for(int i=0;i<c.getMethods().length;i++){
				System.out.println(c.getMethods()[i].getName());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ajoutType(Bean a) {
		this.noeuds.add(a);
	}
	public void decrireType(){
	
	}
		
	
	
	
	
	public int[][] getTab() {
		return tab;
	}

	public void setTab(int[][] tab) {
		this.tab = tab;
	}

	public ArrayList<Bean> getNoeuds() {
		return noeuds;
	}

	public void setNoeuds(ArrayList<Bean> noeuds) {
		this.noeuds = noeuds;
	}
	public static void main(String[] args) {
		Diagramme d = new Diagramme("c:\\Users\\guest\\workspace\\archiLogiciel\\bin\\");
		// System.out.println(d.getTab());
	}

}

