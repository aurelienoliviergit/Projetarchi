import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Diagramme {

	private int[][] tab;
	private Class[] noeuds;
	private ArrayList<Path> cheminClasse;
	private ArrayList<String> binaryName;
	private ArrayList<String> nomClasse;
	public static final int Implement = 2;
	public static final int Extends = 1;
	public static final int PasDeLien = 0;
	public Diagramme(String path) throws ScanException {
		this(new File(path).toPath());
	}

	public Diagramme(Path path) throws ScanException {

		ClassLoader urlcl = Lecture(path);

		// La méthode classesAlire doit retourner une Liste(ou arbre) contenant
		// les noms des
		// classes et interfaces du projet
		this.cheminClasse = new ArrayList<>();
		this.nomClasse = new ArrayList<>();
		this.binaryName = new ArrayList<>();
		this.classesAlire(path);
		String s;
		int t = path.toString().length();
		for (Path p : this.cheminClasse) {
			s = p.toString();
			s = s.substring(t + 1, s.length() - 6);
			s = s.replace('\\', '.');
			this.binaryName.add(s);

		}

		this.noeuds = new Class[this.nomClasse.size()];
		System.out.println(this.cheminClasse.toString());
		System.out.println(this.nomClasse.toString());
		System.out.println(this.binaryName.toString());
		this.tab = new int[nomClasse.size()][nomClasse.size()];
		int i = 0;
		for (String a : this.binaryName) {
			this.noeuds[i] = this.ajoutType0(urlcl, a);
			// remplissage de la matrice avec des zéros
			for (int j = 0; j < this.binaryName.size(); j++) {
				this.tab[i][j]=PasDeLien;
			}
			i++;
		}
		this.generateTab();
	}

	private void generateTab() {
		String superClass = "";
		Class[] implemented;
		int i,j;
		for (Class c : this.noeuds) {
			if (c.getGenericSuperclass() != null) {
				superClass = c.getGenericSuperclass().getTypeName();
				if(this.binaryName.contains(superClass)){
					i = this.binaryName.indexOf(c.getName());
					j= this.binaryName.indexOf(superClass);
					this.tab[i][j]=Extends;
				}
				implemented=c.getInterfaces();
				for(Class interf:implemented){
					if(this.binaryName.contains(interf.getName())){
						i = this.binaryName.indexOf(c.getName());
						j= this.binaryName.indexOf(interf.getName());
						this.tab[i][j]=Implement;
					}
				}
			}
		}
	}

	public Class ajoutType0(ClassLoader urlcl, String st) {

		Class c = null;
		try {
			c = urlcl.loadClass(st);
		} catch (ClassNotFoundException e) {
			System.out.println(st);
		}
		return c;
	}

	public ClassLoader Lecture(Path path) {
		URL[] cp = new URL[1];
		try {
			cp[0] = path.toUri().toURL();
			System.out.println(cp[0]);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ClassLoader urlcl = new URLClassLoader(cp);
		return urlcl;
	}

	// inspiré de :
	// https://openclassrooms.com/courses/apprenez-a-programmer-en-java/executer-des-taches-simultanement
	public class ScanException extends Exception {

		public ScanException(String message) {
			super(message);
		}

	}

	// inspiré de :
	// https://openclassrooms.com/courses/apprenez-a-programmer-en-java/executer-des-taches-simultanement
	public void classesAlire(Path path) throws ScanException {
		String filter = "*.class";
		String s;
		// Si le chemin n'est pas valide, on lève une exception
		if (path == null || path.equals(""))
			throw new ScanException("Chemin à scanner non valide (vide ou null) !");

		// On liste maintenant le contenu du répertoire pour traiter les
		// sous-dossiers
		try (DirectoryStream<Path> listing = Files.newDirectoryStream(path)) {
			for (Path nom : listing) {
				if (Files.isDirectory(nom.toAbsolutePath())) {
					classesAlire(nom.toAbsolutePath());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (DirectoryStream<Path> listing = Files.newDirectoryStream(path, filter)) {
			for (Path nom : listing) {
				if (!Files.isDirectory(nom.toAbsolutePath())) {
					this.cheminClasse.add(nom);
					this.nomClasse.add(nom.getFileName().toString().replaceAll(".class", ""));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int[][] getTab() {
		return tab;
	}

	public void setTab(int[][] tab) {
		this.tab = tab;
	}

	public Class[] getNoeuds() {
		return noeuds;
	}

	public void setNoeuds(Class[] noeuds) {
		this.noeuds = noeuds;
	}

	public static void main(String[] args) throws ScanException {
		Diagramme d = new Diagramme("c:\\Users\\guest\\workspace\\PkB\\bin\\");
		String s="";
		for(int i=0;i<d.getTab().length;i++){
			for(int j=0;j<d.getTab().length;j++){
				s+=" "+d.getTab()[i][j];
			}
			s+="\n";
		}
		System.out.println(s);
	}

}