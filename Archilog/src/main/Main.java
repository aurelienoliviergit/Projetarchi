package main;

import java.util.Scanner;

import coucheBasse.Diagramme;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in); // scanner pour demander les
												// dimensions de la classe
		System.out.println("Choisir des dimensions cohèrentes avec le diagramme");
		System.out.println("taille en abcisse du cadre?");
		int i = scan.nextInt();
		System.out.println("taille en ordonné du cadre?");
		int j = scan.nextInt();
		//DessinSVG d = new DessinSVG(i, j); // Dessine le cadre de taille (i,j)
		Diagramme D = new Diagramme("/home/abel/workspace/fibonacci/bin/");
		//d.dessinerClass(D); // on dessine dans d l'ensemble des classes
							// représentées par un objet de type Diagramme
		//d.lier(D);
		//System.out.println(d.getGraph().getSVGElement());

	}

}
