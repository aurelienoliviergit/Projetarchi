import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class DessinScript implements Dessin {
private int epaisseurtrait;
private int couleur;
private int formesboites;
private int formefleches;
private Diagramme d;


public DessinScript(int ep, int c, int fb, int ff){
	
	this.epaisseurtrait=ep;
	this.couleur=c;
	this.formesboites=fb;
	this.formefleches=ff;

}

public void lire(Diagramme d){
	int[][] tab=d.getTab();
	ArrayList<Class> a=d.getNoeuds();
	for( int i=0; i<tab.length; i++){
		
		this.dessinercadre(a.get(i),tab,i);
		this.lier(a.get(i),tab,i);
	}
}






public void dessinercadre(Class a ){

   this.placercadre();
  Method[]m=a.getDeclaredMethods();
	for(int i=0;i<m.length ;i++){
		this.dessinerMethode(m[i]);
	}
	Field[]f=a.getFields();
	for(int i=0;i<m.length ;i++){
		this.dessinerAttribut(f[i].getName());
	}

	this.lier(a, null, couleur);
	
}

public void placercadre(){
	
	System.out.println("Dessiner cadre");
	
}
public void dessinerMethode(Method m){
	System.out.println("Ajouter methode:"+m.getName()+" de type "+m.getReturnType());
}
public void dessinerAttribut(String f){
	
	System.out.println("Ajouter attribut"+f);
}


public void lier(Class a,int[][] t, int i){
	for(int j=0;j<t.length;j++){
		if(t[i][j]==1){
	System.out.println("lien de"+i+" à "+j);	
		}
	}
		
}
public int getEpaisseurtrait() {
	return epaisseurtrait;
}

public void setEpaisseurtrait(int epaisseurtrait) {
	this.epaisseurtrait = epaisseurtrait;
}

public int getCouleur() {
	return couleur;
}

public void setCouleur(int couleur) {
	this.couleur = couleur;
}

public int getFormesboites() {
	return formesboites;
}

public void setFormesboites(int formesboites) {
	this.formesboites = formesboites;
}

public int getFormefleches() {
	return formefleches;
}
public void setDiagramme(Diagramme d){
	this.d=d;
}

public void setFormefleches(int formefleches) {
	this.formefleches = formefleches;
}





public static void main(String[] args) {
	
	int epaisseurtrait=1;
	int couleur=1;
	 int formeboites=1;
	 int formefleches=1;
		Dessin draw=new DessinScript(epaisseurtrait,couleur,formeboites,formefleches);

	Diagramme d = new Diagramme("c:\\Users\\guest\\workspace\\archiLogiciel\\bin\\");
	draw.setDiagramme(d);

	
	
}


}

