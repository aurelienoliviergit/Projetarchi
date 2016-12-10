import java.lang.reflect.Field;
import java.lang.reflect.Method;


public interface Dessin {

	int couleur = 0;
	/*
	 public void dessinerAttribut(String f);
	 
	public default void dessinercadre(Class a){

		   this.placercadre();
		  Method[]m=a.getDeclaredMethods();
			for(int i=0;i<m.length ;i++){
				this.dessinerMethode(m[i]);
			}
			Field[]f=a.getFields();
			for(int i=0;i<m.length ;i++){
				this.dessinerAttribut(f[i].getName());
			}

			this.lier(a, null, couleur);
			
	}
	public void dessinerMethode(Method m);
	public void placercadre();
	*/
	public void dessinerClass(Diagramme d);
	public void lier(Diagramme d);
	
}
