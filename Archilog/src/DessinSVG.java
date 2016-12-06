import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.lang.reflect.Method;
import java.util.Scanner;

import org.jfree.graphics2d.svg.SVGGraphics2D;

public class DessinSVG {
	
	private SVGGraphics2D graph; // Dessin 2D ou svg du diagramme
	
	public SVGGraphics2D getGraph() {
		return graph;
	}

	public void setGraph(SVGGraphics2D graph) {
		this.graph = graph;
	}

// Dessine le cadre du dessin du Diagramme 
	public DessinSVG(int x, int y){
		this.graph = new SVGGraphics2D(x,y);
	}
	
	
	
// Dessine l'ensemble des classes	
	public void dessinerClass(Bean[] b){
        Scanner scan2 = new Scanner(System.in); //scanner pour demander les dimensions de la classe
        
        for(int i = 0; i< b.length; i++){
        	System.out.println("entré la position voulue de la classe" + b[i].toString());
        	int x = scan.nextInt(); //position de l'abcisse du point en haut à gauche du rectangle representant la classe b[i]
        	int y = scan.nextInt(); //position de l'ordonné du point en haut à gauche du rectangle representant la classe b[i]
        	this.dessinerClass(b[i],x,y); // Dessine la classe b[i] 
        	
        	scan2.close();
        }
	}

	
// Dessine une classe 
	public void dessinerClass(Bean b,int x, int y){
		
		int nbreAttribut = b.nbreAttribut(); // nombre d'attribut dans la classe b
		int nbreMethod = b.nbreMethod(); 	 // nombre de méthodes dans la classe b
		
		int dimX = b.tailleDuPlusLongMot()+20 ;		  // dimension en abcisse du rectangle de la classe b, on a ajouté 20 par soucis de clarté
		int dimY= nbreAttribut*12+nbreMethod*12+3*12; // dimension en ordonné du rectangle de la classe b, 12 étant taille du texte
		
		this.getGraph().draw(new Rectangle(x, y, dimX , dimY)); // dessin du rectangle
		
		int nbrElement = 1; //nbrElement est un repere pour savoir combien d'element on deja ete dessine dans le rectangle
		this.getGraph().drawString(b.name(), x+10, y +12*(nbrElement)); // ecrire le nom de la methode dans le cadre
		nbrElement++;
		
		// ecrire dans le cadre de la classe b les attributs
		for(int i = 0; i<nbreAttribut; i++){
			this.getGraph().drawString(b.getAttribut()[i], x+10, y+12*(nbrElement));
			nbrElement++;
		}
		
		// ecrire dans le cadre de la classe b les methodes
		for(int i = 0; i<nbreMethod; i++){
			this.getGraph().drawString(b.getMethod()[i], x+10, y+12*(nbrElement));
			nbrElement++;
		}
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
		d.dessinerClass(); //on dessine dans d l'ensemble des classes représentées par b qui est du type Bean[]
		 
		 
	}

}
