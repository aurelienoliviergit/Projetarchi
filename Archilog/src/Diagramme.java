import java.util.ArrayList;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;



public class Diagramme {

	private int[][] tab;
	private ArrayList<Class> noeuds;
	public static final int Implement = 2;
	public static final int Extends = 1;
	
	public Diagramme(String path) {
		this(new File(path));
	}

	
	
	public Diagramme(File path) {

		
		ClassLoader urlcl=Lecture(path);
		
		
		// La méthode classesAlire doit retourner une Liste(ou arbre) contenant les noms des 
		// classes et interfaces du projet
		ArrayList<String> Listestring= this.classesAlire();
		
		
		
		// Cette boucle ajoute toutes les classe du projets dans l'ArrayList noeuds
		for ( int i=0; i<Listestring.size();i++){
			this.noeuds.add(ajoutType0(urlcl,Listestring.get(i)));
           this.ajoutTypetab(this.noeuds.get(i));
		}
	
		
		
		// Creer le tableau contenant les classes et leurs relations
  this.tab=new int[Listestring.size()][Listestring.size()];
		
	
	
	
	}
	
	
	public void ajoutType(){
		
		
for ( int i=1; i<this.noeuds.size();i++){

	
	
}	
	


	}
	
	
	
	public Class ajoutType0(ClassLoader urlcl,String st){
		
		
		
		Class c=null;
		try {
			c = urlcl.loadClass(st);

			//c = urlcl.loadClass("session1.demo.NatDecimal");

			// c=urlcl.loadClass(listefichiers[i].substring(0,
			// listefichiers[i].length() - 5));
			for(int i=0;i<c.getMethods().length;i++){
				System.out.println(c.getMethods()[i].getName());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	
	
	public ClassLoader Lecture (File path){
		URL[] cp = new URL[1];
		try {
			cp[0] = path.toURL();
			System.out.println(cp[0]);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ClassLoader urlcl = new URLClassLoader(cp);
		return urlcl;
	}
	
	public ArrayList<String> classesAlire(){
		
    return null;
		
		
	}
	public void ajoutTypetab(Class a) {
	// ajoute la class au diagramme en indiquant ses relations aux autres class
	}
	
	
	
	public void decrireType(){
	
	}
		
	public void insererDiagramme(){
		
	}
	public void etiquetterDiagramme(){
		
	}
	
	
	
	public int[][] getTab() {
		return tab;
	}

	public void setTab(int[][] tab) {
		this.tab = tab;
	}

	public ArrayList<Class> getNoeuds() {
		return noeuds;
	}

	public void setNoeuds(ArrayList<Class> noeuds) {
		this.noeuds = noeuds;
	}
	

public void insererDiagramme(Diagramme d){
}


	
	
	
	
	public static void main(String[] args) {
		Diagramme d = new Diagramme("c:\\Users\\guest\\workspace\\archiLogiciel\\bin\\");
		// System.out.println(d.getTab());
		
		
		URL[] cp = new URL[1];
		File path=new File("c:\\Users\\guest\\workspace\\archiLogiciel\\bin\\");
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

}