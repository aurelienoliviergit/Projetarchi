import java.awt.Color;
import java.awt.Rectangle;
import java.lang.reflect.Method;

import org.jfree.graphics2d.svg.SVGGraphics2D;

public class DessinSVG implements Dessin{
	
	protected int nombreElement=0;
	
	protected SVGGraphics2D graph;
	protected int dimensionX ; //dimension du cadre ordonné
	protected int dimensionY ; //dimension du cadre abcisse
	
	
	
	public DessinSVG(int x, int y){
		this.dimensionX = x;
		this.dimensionY = y; // 1+nbreAttribut*12px+nbreMethod*12px+3*12px
		this.graph = new SVGGraphics2D(this.dimensionX, this.dimensionY);
		this.graph.setPaint(Color.magenta);
		this.graph.draw(new Rectangle(0, 0, this.dimensionX, this.dimensionY));
	}
	
	
	public void dessinerNomClass(String n){
		this.nombreElement++;
		this.graph.drawString(n, this.dimensionX/10,12*(this.nombreElement+1));
		
	}

	public void dessinerAttribut(String f) {
		this.nombreElement++;
		this.graph.drawString(f, this.dimensionX/10,12*(this.nombreElement+1));
	}

	public void dessinerMethode(Method m) {
		this.nombreElement++;
		this.graph.drawString(m.getName(), this.dimensionX/10,12*(this.nombreElement+1));
	}

	@Override
	public void placercadre() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lier(Class a, int[][] t, int i) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		SVGGraphics2D g2 = new SVGGraphics2D(300, 200);
		 g2.setPaint(Color.RED);
		 g2.draw(new Rectangle(10, 10, 280, 180));
		 g2.drawString("string",15 , 15);
		 String svgElement = g2.getSVGElement();
		
		 
		 DessinSVG d = new DessinSVG(500, 500);
		 d.dessinerNomClass("nom");
		 d.dessinerAttribut("A1");
		 d.dessinerAttribut("A2");
		 String svg = d.graph.getSVGElement();
		 System.out.println( svg);
	}

}
