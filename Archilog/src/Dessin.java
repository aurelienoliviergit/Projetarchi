import java.util.ArrayList;


public class Dessin {
private int epaisseurtrait;
private int couleur;
private int formesboites;
private int formefleches;
private ArrayList<Diagramme> Liste;

public Dessin(int ep, int c, int fb, int ff){
	
	this.epaisseurtrait=ep;
	this.couleur=c;
	this.formesboites=fb;
	this.formefleches=ff;
	this.Liste=new ArrayList<Diagramme>();
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

public void setFormefleches(int formefleches) {
	this.formefleches = formefleches;
}

public void addDiagramme(Diagramme d){
	this.Liste.add(d);
}


public static void main(String[] args) {
	
	int epaisseurtrait=1;
	int couleur=1;
	 int formeboites=1;
	 int formefleches=1;
	Dessin SVG=new Dessin(epaisseurtrait,couleur,formeboites,formefleches);
	Diagramme d=new Diagramme();
	d.lecture();
	
	
}


}

