package coucheHaute;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

import org.jfree.graphics2d.svg.SVGGraphics2D;



import coucheBasse.Diagramme;
import coucheBasse.RepresentationProjet;

public class DessinSVG implements DessinClass, DessinLien{
	
	private SVGGraphics2D graph; 		//Dessin 2D ou svg du diagramme
	private ArrayList<Integer> x;		//tableau des abcisses  de tout les rectangles des classes
	private ArrayList<Integer> y;		//tableau des ordonnés  de tout les rectangles des classes 	
	private ArrayList<Integer> dimX;	//tableau des dimension en abcisse de tout les rectangles des classes
	private ArrayList<Integer> dimY;	//tableau des dimension en ordonné de tout les rectangles des classes
	private boolean couleur;
	private boolean epaisseur;
	
	
	public SVGGraphics2D getGraph() {
		return graph;
	}

	public void setGraph(SVGGraphics2D graph) {
		this.graph = graph;
	}

	public ArrayList<Integer> getX() {
		return x;
	}

	public void setX(ArrayList<Integer> x) {
		this.x = x;
	}

	public ArrayList<Integer> getY() {
		return y;
	}

	public void setY(ArrayList<Integer> y) {
		this.y = y;
	}

	public ArrayList<Integer> getDimX() {
		return dimX;
	}

	public void setDimX(ArrayList<Integer> dimX) {
		this.dimX = dimX;
	}

	public ArrayList<Integer> getDimY() {
		return dimY;
	}

	public void setDimY(ArrayList<Integer> dimY) {
		this.dimY = dimY;
	}

	// Dessine le cadre du dessin du Diagramme 
	public DessinSVG(int x, int y){
		this.graph = new SVGGraphics2D(x,y);
		this.x = new ArrayList<Integer>();
		this.y = new ArrayList<Integer>();
		this.dimX = new ArrayList<Integer>();
		this.dimY = new ArrayList<Integer>();
		
	}
	
	
	
	// Dessine l'ensemble des classes	
	public void dessinerClass(RepresentationProjet b){
        Scanner scan = new Scanner(System.in); //scanner pour demander les dimensions de la classe
        
        for(int i = 0; i< b.getClasses().length; i++){
        	System.out.println("entré la position voulue de la classe" + b.getClasses()[i].getName());
        	int k = scan.nextInt(); //position de l'abcisse du point en haut à gauche du rectangle representant la classe b[i]
        	this.getX().add(k);
        	
        	int l = scan.nextInt(); //position de l'ordonné du point en haut à gauche du rectangle representant la classe b[i]
        	this.getY().add(l);
        	
        	this.dessinerClass(b.getClasses()[i],k,l); // Dessine la classe b[i] 
        	
        	
        }
        
	}

	
	// Dessine une classe 
	public void dessinerClass(Class b,int x, int y){
		
		int nbreAttribut = b.getDeclaredFields().length; // nombre d'attribut dans la classe b
		int nbreMethod = RepresentationProjet.getCleanMethods(b).length; 	 // nombre de méthodes dans la classe b
		
		int dimX = DessinSVG.pluslongmot(b)*7+20 ;		  // dimension en abcisse du rectangle de la classe b, on a ajouté 20 par soucis de clarté
		this.getDimX().add(dimX);
		
		int dimY= nbreAttribut*12+nbreMethod*12+4*12; // dimension en ordonné du rectangle de la classe b, 12 étant taille du texte
		this.getDimY().add(dimY);
		
		this.getGraph().setColor(Color.red);
		this.getGraph().draw(new RoundRectangle2D.Double(x, y, dimX , dimY,10,10)); // dessin du rectangle
		
		int nbrElement = 1; //nbrElement est un repère pour savoir combien d'element on deja été dessiné dans le rectangle
		this.getGraph().drawString(b.getName(), x+10, y +12*(nbrElement)); // écrire le nom de la méthode dans le cadre
		nbrElement++;
		this.getGraph().drawLine(x,y +12*(nbrElement),x + dimX ,y +12*(nbrElement));
		nbrElement++;
		// ecrire dans le cadre de la classe b les attributs
		for(int i = 0; i<nbreAttribut; i++){
			this.getGraph().drawString(b.getDeclaredFields()[i].getName() + ": " + b.getDeclaredFields()[i].getType().getName(), x+10, y+12*(nbrElement));
			nbrElement++;
			
			if(i == nbreAttribut -1){
				this.getGraph().drawLine(x,y +12*(nbrElement),x + dimX ,y +12*(nbrElement));
				nbrElement++;
			}
		}
		
		// ecrire dans le cadre de la classe b les methodes
		for(int i = 0; i<nbreMethod; i++){
			this.getGraph().drawString(RepresentationProjet.getCleanMethods(b)[i].getName() + ": " + RepresentationProjet.getCleanMethods(b)[i].getReturnType().getName(), x+10, y+12*(nbrElement));
			nbrElement++;
		}
	}

	//Dessine les flèches entre les classes ayant des liens d'extends et d'implements	
	public void lier(RepresentationProjet d){
		for(int i = 0; i< d.getClasses().length;i++){
			
			ArrayList<Integer> A = d.getImplements(i);
			while(!A.isEmpty()){
				int [] t= this.pointLier(i, A.get(0));
				this.getGraph().setPaint(Color.darkGray);
				this.getGraph().drawLine(t[0], t[1], t[2], t[3]);
				this.getGraph().draw(new Rectangle(t[2], t[3], 10,10));
				A.remove(0);
			}
			ArrayList<Integer> B = d.getExtends(i);
			while(!B.isEmpty()){
				int [] t= this.pointLier(i, B.get(0));
				this.getGraph().setPaint(Color.blue);
				this.getGraph().drawLine(t[0], t[1], t[2], t[3]);
				this.getGraph().draw(new Ellipse2D.Double(t[2], t[3], 10,10));
				B.remove(0);
			}
		}	
	}
	
	// donne les coordonnées des 2 points à lier 
	public int[] pointLier(int i, int j){
		int[] t = new int[4]; // tableau des coordonnées à lier
		int deltaX = this.getX().get(i)-this.getX().get(j);
		int deltaY = this.getY().get(i)-this.getY().get(j);
		
		int x1 = this.getX().get(i);
		int y1 = this.getY().get(i);
		int dimx1 = this.getDimX().get(i);
		int dimy1 = this.getDimY().get(i);
		int x2 = this.getX().get(j);
		int y2 = this.getY().get(j);
		int dimx2 = this.getDimX().get(j);
		int dimy2 = this.getDimY().get(j);
		
		if(Math.abs(deltaY) >= Math.abs(deltaX)){
			if(deltaY>=0){
				t[0]=x1+dimx1/2;
				t[1]=y1;
				t[2]=x2+dimx2/2;
				t[3]=y2+dimy2;
			}
			else{
				t[0]=x1+dimx1/2;
				t[1]=y1+dimy1;
				t[2]=x2+dimx2/2;
				t[3]=y2;
			}
		}
		else{
			if(deltaX<0){
				t[0]=x1+dimx1;
				t[1]=y1+dimy1/2;
				t[2]=x2;
				t[3]=y2+dimy2/2;
			}
			else{
				t[0]=x1;
				t[1]=y1+dimy1/2;
				t[2]=x2+dimx2;
				t[3]=y2+dimy2/2;
			}
		}
		return t;
	}

	

	//retourne la taille du mot le plus long à écrire de la classe b
	public static int pluslongmot(Class b){
		int tailleDuPlusLongMot = b.getName().length();
		for(int i = 0; i<b.getFields().length; i++){
			if((b.getFields()[i].getName() + "" + b.getFields()[i].getType().getName()).length() > tailleDuPlusLongMot){
				tailleDuPlusLongMot = b.getFields()[i].getName().length() + 1 + b.getFields()[i].getType().getName().length();
			}
		}
		for(int i = 0; i<b.getMethods().length; i++){
			if((b.getMethods()[i].getName() + "" + b.getMethods()[i].getReturnType().getName()).length() > tailleDuPlusLongMot){
				tailleDuPlusLongMot = b.getMethods()[i].getName().length() + 1 + b.getMethods()[i].getReturnType().getName().length();
			}
		}
		return tailleDuPlusLongMot;
	}
	
	public static void main(String[] args) {
		
        Scanner scan = new Scanner(System.in); //scanner pour demander les dimensions de la classe
        System.out.println("Choisir des dimensions cohèrentes avec le diagramme");
        System.out.println("taille en abcisse du cadre?");
        int i = scan.nextInt();
        System.out.println("taille en ordonné du cadre?");
        int j = scan.nextInt();
      	DessinSVG d = new DessinSVG(i,j); //Dessine le cadre de taille (i,j)
		Diagramme D = new Diagramme("/home/abel/workspace/fibonacci/bin/");
		d.dessinerClass(D); //on dessine dans d l'ensemble des classes représentées par un objet de type Diagramme
		d.lier(D);
		System.out.println(d.getGraph().getSVGElement());
		
	}

}
