import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

import org.jfree.graphics2d.svg.SVGGraphics2D;

public class DessinSVG {
	
	private SVGGraphics2D graph; // Dessin 2D ou svg du diagramme
	private ArrayList<Integer> x; 
	private ArrayList<Integer> y; 
	private ArrayList<Integer> dimX; 
	private ArrayList<Integer> dimY; 
	
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
		
		int dimX = b.tailleDuPlusLongMot()+20 ;		  // dimension en abcisse du rectangle de la classe b, on a ajouté 20 par soucis de clarté
		this.getDimX().add(dimX);
		
		int dimY= nbreAttribut*12+nbreMethod*12+3*12; // dimension en ordonné du rectangle de la classe b, 12 étant taille du texte
		this.getDimY().add(dimY);
		
		this.getGraph().draw(new Rectangle(x, y, dimX , dimY)); // dessin du rectangle
		
		int nbrElement = 1; //nbrElement est un repere pour savoir combien d'element on deja ete dessine dans le rectangle
		this.getGraph().drawString(b.getName(), x+10, y +12*(nbrElement)); // ecrire le nom de la methode dans le cadre
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
	
	
	public void dessinerFleche(Diagramme d){
		
		for(int i = 0; i < d.getTab().length; i++){
			for(int j = 0; j < d.getTab()[0].length; j++){
				if(d.getTab()[i][j]==1){
					int [] t= this.pointLier(i, j);
					this.getGraph().drawLine(t[0], t[1], t[2], t[3]);
				}
				if(d.getTab()[i][j]==1){
					
				}
			}
		}
	
		
		
	}
	
	
	public int[] pointLier(int i, int j){
		int deltaX = this.getX().get(i)-this.getX().get(i);
		int deltaY = this.getY().get(i)-this.getY().get(i);
		
		return null;
		
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
		d.dessinerClass(null); //on dessine dans d l'ensemble des classes représentées par b qui est du type Bean[]
		 
		 
	}

}
