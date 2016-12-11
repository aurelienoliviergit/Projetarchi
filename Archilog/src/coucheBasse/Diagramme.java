package coucheBasse;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;

public class Diagramme extends ListeClasses implements RepresentationGraph{

	private int[][] tab;
	private Class[] noeuds;
	public static final int Implement = 2;
	public static final int Extends = 1;
	public static final int PasDeLien = 0;

	public Diagramme(String path) {
		this(new File(path).toPath());
	}

	public Diagramme(Path path) {
		Extraction e = new Extraction(path);
		this.setBinaryName(e.getBinaryName());
		ClassLoader urlcl = Lecture(path);
		this.noeuds = new Class[e.getNomClasse().size()];
		System.out.println(e.getBinaryName());
		System.out.println(e.getCheminClasse());
		System.out.println(e.getNomClasse());
		this.tab = new int[e.getNomClasse().size()][e.getNomClasse().size()];
		int i = 0;
		for (String a : e.getBinaryName()) {
			this.noeuds[i] = this.ajoutType0(urlcl, a);
			// remplissage de la matrice avec des zeros
			for (int j = 0; j < e.getBinaryName().size(); j++) {
				this.tab[i][j] = PasDeLien;
			}
			i++;
		}
		this.generateTab();
	}

	private void generateTab() {
		String superClass = "";
		Class[] implemented;
		int i, j;
		for (Class c : this.noeuds) {
			if (c.getGenericSuperclass() != null) {
				superClass = c.getGenericSuperclass().getTypeName();
				if ( this.getBinaryName().contains(superClass)) {
					i =  this.getBinaryName().indexOf(c.getName());
					j =  this.getBinaryName().indexOf(superClass);
					this.tab[i][j] = Extends;
				}
				implemented = c.getInterfaces();
				for (Class interf : implemented) {
					if ( this.getBinaryName().contains(interf.getName())) {
						i =  this.getBinaryName().indexOf(c.getName());
						j =  this.getBinaryName().indexOf(interf.getName());
						this.tab[i][j] = Implement;
					}
				}
			}
		}
	}

	public ClassLoader Lecture(Path path) {
		URL[] cp = new URL[1];
		try {
			cp[0] = path.toUri().toURL();
			System.out.println(cp[0]);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		ClassLoader urlcl = new URLClassLoader(cp);
		return urlcl;
	}
	public Class ajoutType0(ClassLoader urlcl, String st) {
		System.out.println(st);
		Class c = null;
		try {
			c = urlcl.loadClass(st);
		} catch (ClassNotFoundException e) {
			System.out.println(st);
		}
		return c;
	}
	public int[][] getTab() {
		return tab;
	}

	public void setTab(int[][] tab) {
		this.tab = tab;
	}
	@Override
	public Class[] getClasses() {
		return noeuds;
	}

	public void setClassess(Class[] noeuds) {
		this.noeuds = noeuds;
	}

	public static void main(String[] args) {
		Diagramme d = new Diagramme("/home/armand/workspace/OptimNonLinTP4/bin/");
		String s = "";
		for (int i = 0; i < d.getTab().length; i++) {
			for (int j = 0; j < d.getTab().length; j++) {
				s += " " + d.getTab()[i][j];
			}
			s += "\n";
		}
		System.out.println(s);
		for(int i=0;i<d.getClasses()[0].getMethods().length;i++){
			System.out.println(d.getClasses()[0].getMethods()[i].getName());
		}
		///*
		System.out.println("--"+ d.getCleanMethods(0).length);
		for(int i=0;i<d.getCleanMethods(0).length;i++){
			System.out.println(d.getCleanMethods(0)[i].getName());
		}//*/
	}

	@Override
	public int getExtends(int i) {
		int toReturn=-1;
		for(int p:this.getTab()[i]){
			if(p==1){
				toReturn=p;
			}
		}
		return toReturn;
	}

	@Override
	public ArrayList<Integer> getImplements(int i) {
		 ArrayList<Integer> toReturn=new ArrayList<>();
		for(int p:this.getTab()[i]){
			if(p==1){
				toReturn.add(p);
			}
		}
		return toReturn;
	}

	

}