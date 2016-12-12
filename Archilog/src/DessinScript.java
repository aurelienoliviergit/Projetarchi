import java.io.FileWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import coucheBasse.Arbre;
import coucheBasse.Diagramme;
import coucheBasse.RepresentationProjet;

public class DessinScript implements DessinClass, DessinLien {

	private int epaisseurtrait; // choix de l'Ã©paisseur du trait
	private int couleur; // choix de la couleur du trait
	private int formesboites; // choix de la forme des boites
	private int formefleches; // choix de la forme des flÃ¨ches
	private RepresentationProjet d; // reprÃ©sentation du projet
	private ArrayList<String> script; // Array contenant les instructions du
										// script

	public DessinScript(int ep, int c, int fb, int ff, RepresentationProjet d) {

		this.epaisseurtrait = ep;
		this.couleur = c;
		this.formesboites = fb;
		this.formefleches = ff;
		this.d = d;
		this.script = new ArrayList<String>();
	}

	public DessinScript(RepresentationProjet d) {
		this.d = d;
		this.script = new ArrayList<String>();

	}

	// Lit la reprÃ©sentation du projet afin de dessiner les cadres des classes,
	// leurs mÃ©thodes et leurs attributs
	// et lie les cadre entre eux
	public void lire(RepresentationProjet d) {

		Class[] a = d.getClasses();

		this.dessinerClass(d);

		this.lier(d);
		System.out.println("write");
		try {

			// creation d'un writer (un écrivain)
			final FileWriter writer = new FileWriter("script.txt", true);
			try {
				writer.write(script.toString());
				writer.write("");
				System.out.println(script);

				writer.write("fin du script");
			} finally {
				// quoiqu'il arrive, on ferme le fichier
				writer.close();
			}
		} catch (Exception e) {
			System.out.println("Impossible de creer le fichier");
		}

	}

	// La fonction dessinerClass(
	public void dessinerClass(RepresentationProjet d) {
		Class[] a = d.getClasses();

		this.choisirBandes();
		this.placercadre();

		for (int i = 0; i < a.length; i++) {

			Method[] m = RepresentationProjet.getCleanMethods(a[i]);
			for (int j = 0; j < m.length; j++) {
				this.dessinerMethode(m[j], a[i].getName());
			}
			Field[] f;
			try {
				f = a[i].getDeclaredFields();
			} catch(NoClassDefFoundError e1) {
				f = new Field[0];
			}
			for (int j = 0; j < f.length; j++) {
				this.dessinerAttribut(f[j].getName(), a[i].getName());
			}

		}

	}

	public void placercadre() {

		this.script.add("Dessiner le cadre de la Classe");
		String ligne = System.getProperty("line.separator");
		this.script.add(ligne);

		System.out.println("Dessiner le cadre de la Classe");

	}

	public void dessinerMethode(Method m, String a) {
		this.script.add("Ajouter la methode:" + m.getName() + " de type " + m.getReturnType() + " a  la classe " + a);
		String ligne = System.getProperty("line.separator");
		this.script.add(ligne);
		System.out
				.println("Ajouter la methode:" + m.getName() + " de type " + m.getReturnType() + " a  la classe " + a);
	}

	public void dessinerAttribut(String f, String a) {
		this.script.add("Ajouter l'attribut" + f + " a  la classe " + a);
		String ligne = System.getProperty("line.separator");
		this.script.add(ligne);
		System.out.println("Ajouter l'attribut" + f + " a  la classe " + a);
	}

	public void lier(RepresentationProjet d) {

		Class[] a = d.getClasses();
		for (int i = 0; i < a.length; i++) {
			ArrayList<Integer> listeextends = d.getExtends(i);

			for (int j = 0; j < listeextends.size(); j++) {

				this.script
						.add("La classe " + a[i].getName() + " extends la classe" + a[listeextends.get(j)].getName());
				System.out.println(
						"La classe " + a[i].getName() + " extends la classe" + a[listeextends.get(j)].getName());

			}
			ArrayList<Integer> L = d.getImplements(i);
			for (int j = 0; j < L.size(); j++) {
				this.script.add("La classe " + a[i].getName() + " implemente la classe" + a[L.get(j)].getName());

				System.out.println("La classe " + a[i].getName() + " implemente la classe" + a[L.get(j)].getName());
			}
		}
	}

	public void choisirBandes() {

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

	public void setRepresentation(RepresentationProjet d) {
		this.d = d;
	}

	public void setFormefleches(int formefleches) {
		this.formefleches = formefleches;
	}

	public static void main(String[] args) {
		Diagramme D = new Diagramme("/home/armand/workspace/OptimNonLinTP4/bin/");
		DessinScript d = new DessinScript(D);
		d.lire(D);
		int epaisseurtrait = 1;
		int couleur = 1;
		int formeboites = 1;
		int formefleches = 1;

	}

}