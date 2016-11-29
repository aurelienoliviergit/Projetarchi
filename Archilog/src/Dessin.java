import java.lang.reflect.Method;


public interface Dessin {

	public void dessinerAttribut(String f);
	public void dessinercadre(Class a);
	public void dessinerMethode(Method m);
	public void placercadre();
	public void lier(Class a,int[][] t, int i);
	
}
