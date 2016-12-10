import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

import org.jfree.graphics2d.svg.SVGGraphics2D;

public class DessinSVG implements Dessin{
	
	private SVGGraphics2D graph; 		// Dessin 2D ou svg du diagramme
	private ArrayList<Integer> x;		//tableau des abcisses  de tout les rectangles des classes
	private ArrayList<Integer> y;		//tableau des ordonnés  de tout les rectangles des classes 	
	private ArrayList<Integer> dimX;	//tableau des dimension en abcisse de tout les rectangles des classes
	private ArrayList<Integer> dimY;	//tableau des dimension en ordonné de tout les rectangles des classes
	
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
	}
	
	
	
// Dessine l'ensemble des classes	
	public void dessinerClass(Diagramme b){
        Scanner scan2 = new Scanner(System.in); //scanner pour demander les dimensions de la classe
        
        for(int i = 0; i< b.getNoeuds().length; i++){
        	System.out.println("entré la position voulue de la classe" + b.getNoeuds()[i].getName());
        	int k = scan2.nextInt(); //position de l'abcisse du point en haut à gauche du rectangle representant la classe b[i]
        	this.getX().add(k);
        	
        	int l = scan2.nextInt(); //position de l'ordonné du point en haut à gauche du rectangle representant la classe b[i]
        	this.getY().add(l);
        	
        	this.dessinerClass(b.getNoeuds()[i],k,l); // Dessine la classe b[i] 
        	
        	scan2.close();
        }
	}

	
// Dessine une classe 
	public void dessinerClass(Class b,int x, int y){
		
		int nbreAttribut = b.getFields().length; // nombre d'attribut dans la classe b
		int nbreMethod = b.getMethods().length; 	 // nombre de méthodes dans la classe b
		
		int dimX = DessinSVG.pluslongmot(b)+20 ;		  // dimension en abcisse du rectangle de la classe b, on a ajouté 20 par soucis de clarté
		this.getDimX().add(dimX);
		
		int dimY= nbreAttribut*12+nbreMethod*12+3*12; // dimension en ordonné du rectangle de la classe b, 12 étant taille du texte
		this.getDimY().add(dimY);
		
		this.getGraph().draw(new Rectangle(x, y, dimX , dimY)); // dessin du rectangle
		
		int nbrElement = 1; //nbrElement est un repère pour savoir combien d'element on deja été dessiné dans le rectangle
		this.getGraph().drawString(b.getName(), x+10, y +12*(nbrElement)); // écrire le nom de la méthode dans le cadre
		nbrElement++;
		
		// ecrire dans le cadre de la classe b les attributs
		for(int i = 0; i<nbreAttribut; i++){
			this.getGraph().drawString(b.getFields()[i].getName() + "" + b.getFields()[i].getType().getName(), x+10, y+12*(nbrElement));
			nbrElement++;
			//b.getFields()[i].getType().getName()
		}
		
		
		// ecrire dans le cadre de la classe b les methodes
		for(int i = 0; i<nbreMethod; i++){
			this.getGraph().drawString(b.getMethods()[i].getName() + "" + b.getMethods()[i].getReturnType().getName(), x+10, y+12*(nbrElement));
			nbrElement++;
		}
	}

//Dessine les flèches entre les classes ayant des liens d'extends et d'implements	
	public void lier(Diagramme d){
		
		for(int i = 0; i < d.getTab().length; i++){
			for(int j = 0; j < d.getTab()[0].length; j++){ // parcour du tableau des relations du diagramme
				if(d.getTab()[i][j]==1){ //si i extends j
					int [] t= this.pointLier(i, j);
					this.getGraph().drawLine(t[0], t[1], t[2], t[3]);
				}
				if(d.getTab()[i][j]==2){ // si i implements j
					int [] t= this.pointLier(i, j);
					this.getGraph().drawLine(t[0], t[1], t[2], t[3]);
				}
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
			if(deltaY<=0){
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
			if(deltaX<=0){
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
				tailleDuPlusLongMot = (b.getFields()[i].getName() + "" + b.getFields()[i].getType().getName()).length();
			}
		}
		for(int i = 0; i<b.getMethods().length; i++){
			if((b.getMethods()[i].getName() + "" + b.getMethods()[i].getReturnType().getName()).length() > tailleDuPlusLongMot){
				tailleDuPlusLongMot = (b.getMethods()[i].getName() + "" + b.getMethods()[i].getReturnType().getName()).length();
			}
		}
		return tailleDuPlusLongMot;
	}
	
	public static void main(String[] args) {
		/*SVGGraphics2D g2 = new SVGGraphics2D(300, 200);
		 g2.setPaint(Color.RED);
		 g2.draw(new Rectangle(10, 10, 280, 180));
		 g2.drawString("string",15 , 15);
		 String svgElement = g2.getSVGElement();
		
		 
		 DessinSVG d = new DessinSVG(500, 500, Color.BLUE);
		 d.dessinerNomClass("nom");
		 d.dessinerAttribut("A1");
		 d.dessinerAttribut("A2");
		 
		 String svg = d.graph.getSVGElement();
		 System.out.println( svg);
		 */
		
        Scanner scan = new Scanner(System.in); //scanner pour demander les dimensions de la classe
        System.out.println("Choisir des dimensions cohèrentes avec le diagramme");
        System.out.println("taille en abcisse du cadre?");
        int i = scan.nextInt();
        System.out.println("taille en ordonné du cadre?");
        int j = scan.nextInt();
		 
		DessinSVG d = new DessinSVG(i,j); //Dessine le cadre de taille (i,j)
		d.dessinerClass(null); //on dessine dans d l'ensemble des classes représentées par un objet de type Diagramme
		 
		 
	}

}
