package coucheBasse;

import java.util.ArrayList;

public class Arbre extends ListeClasses implements RepresentationArbre {
	private boolean estRacine;
	private Arbre(boolean b){
		this.estRacine=b;
	}
	public Arbre() {
		super();
		this.estRacine=true;
	}
	private void addFils(String s){
		Arbre fils= new Arbre(false);
		//TODO
	}
	@Override
	public Class[] getClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Arbre getArbre() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public int getExtends(int i) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public ArrayList<Integer> getImplements(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
