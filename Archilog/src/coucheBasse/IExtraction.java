package coucheBasse;
import java.nio.file.Path;
import java.util.ArrayList;

public interface IExtraction {
	public ArrayList<String> getNomClasse();

	public ArrayList<Path> getCheminClasse();

	public ArrayList<String> getBinaryName();
}
