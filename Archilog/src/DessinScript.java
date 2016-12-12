import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import coucheBasse.Arbre;
import coucheBasse.RepresentationProjet;


public class DessinScript implements DessinClass, DessinLien {
	
private int epaisseurtrait; // choix de l'épaisseur du trait
private int couleur; // choix de la couleur du trait
private int formesboites; // choix de la forme des boites
private int formefleches; // choix de la forme des flèches
private RepresentationProjet d; // représentation du projet


public DessinScript(int ep, int c, int fb, int ff,RepresentationProjet d){
	
	this.epaisseurtrait=ep;
	this.couleur=c;
	this.formesboites=fb;
	this.formefleches=ff;
    this.d=d;
}




// Lit la représentation du projet afin de dessiner les cadres des classes, leurs méthodes et leurs attributs
// et lie les cadre entre eux
public void lire(RepresentationProjet d){
	
	Class[] a=d.getClasses();
		
	this.dessinerClass(d);
	
	this.lier(d);
}





// La fonction dessinerClass(
public void dessinerClass(RepresentationProjet d ){
	Class[] a=d.getClasses();

	
	this.choisirBandes(); 
   this.placercadre();
   
   for(int i=0;i<a.length;i++){
	   
  Method[]m=d.getCleanMethods(a[i]);
	for(int j=0;j<m.length ;j++){
		this.dessinerMethode(m[j],a[i].getName());
	}
	Field[]f=a[i].getDeclaredFields();
	for(int j=0;j<m.length ;j++){
		this.dessinerAttribut(f[j].getName(),a[i].getName());
	}

   }
	
}

public void placercadre(){
	
	System.out.println("Dessiner le cadre de la Classe");
	
}
public void dessinerMethode(Method m,String a){
	System.out.println("Ajouter la methode:"+m.getName()+" de type "+m.getReturnType()+" à la classe "+a);
}
public void dessinerAttribut(String f,String a){
	
	System.out.println("Ajouter l'attribut"+f+" à la classe "+a);
}


public void lier(RepresentationProjet d){
	Class[] a=d.getClasses();
	for(int i=0;i<a.length;i++){
		
	
	for(int j=0;j<a.length;j++){
		if( -1!=d.getExtends(i)){
	System.out.println("La classe "+a[i].getName()+" étends la classe"+a[j].getName());	
		}
		
	}
	ArrayList<Integer> L =d.getImplements(i);
	for(int j=0;j<L.size();j++){
		
		System.out.println("La classe "+a[i].getName()+" implémente la classe"+ a[j].getName() ) ;
	}
	}
}


public void choisirBandes(){
	
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
public void setRepresentation(RepresentationProjet d){
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
	

	
	
}







}