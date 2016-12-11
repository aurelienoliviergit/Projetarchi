package coucheBasse;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Extraction extends ListeClasses implements IExtraction{

	private ArrayList<String> nomClasse;
	private ArrayList<Path> cheminClasse;
	

	public Extraction(String path) {
		this(new File(path).toPath());
	}
	public Extraction(Path path) {

		// La methode classesAlire doit retourner une Liste(ou arbre) contenant
		// les noms des
		// classes et interfaces du projet
		this.cheminClasse = new ArrayList<>();
		this.nomClasse = new ArrayList<>();
		this.setBinaryName(new ArrayList<>());
		try {
			this.classesAlire(path);
		} catch (ScanException e) {
			e.printStackTrace();
		}
		String s;
		int t = path.toString().length();
		for (Path p : this.cheminClasse) {
			s = p.toString();
			s = s.substring(t + 1, s.length() - 6);
			s = s.replace('\\', '.');
			this.getBinaryName().add(s);

		}

		
	}
	// inspire de :
		// https://openclassrooms.com/courses/apprenez-a-programmer-en-java/executer-des-taches-simultanement
		public class ScanException extends Exception {

			public ScanException(String message) {
				super(message);
			}

		}

		// inspire de :
		// https://openclassrooms.com/courses/apprenez-a-programmer-en-java/executer-des-taches-simultanement
		public void classesAlire(Path path) throws ScanException {
			String filter = "*.class";
			String s;
			// Si le chemin n'est pas valide, on leve une exception
			if (path == null || path.equals(""))
				throw new ScanException("Chemin � scanner non valide (vide ou null) !");

			// On liste maintenant le contenu du repertoire pour traiter les
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
		
		public ArrayList<String> getNomClasse() {
			return nomClasse;
		}
		public void setNomClasse(ArrayList<String> nomClasse) {
			this.nomClasse = nomClasse;
		}
		public ArrayList<Path> getCheminClasse() {
			return cheminClasse;
		}
		public void setCheminClasse(ArrayList<Path> cheminClasse) {
			this.cheminClasse = cheminClasse;
		}

}
