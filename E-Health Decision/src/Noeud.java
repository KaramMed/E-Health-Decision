import java.util.ArrayList;
import java.util.HashMap;

public class Noeud {
HashMap<String,String> classes;
HashMap<String,Noeud> noeuds;
ArrayList<String> nom;




public ArrayList<String> getNom() {
	return nom;
}

public void setNom(ArrayList<String> nom) {
	this.nom = nom;
}

// constructeurs des feuilles avec classe direct
public Noeud(String valeur,String classe) {
	classes.put(valeur,classe);
}

// constructeur pour noeud suivant
public Noeud(String valeur,Noeud suivant) {
	noeuds.put(valeur,suivant);
}


public Noeud() {
	// TODO Auto-generated constructor stub
}

public void clearNoeud() {
	// efface le contenu 
	noeuds.clear();
	classes.clear();
}

public HashMap<String, String> getClasses() {
	return classes;
}
public void setClasses(String valeur,String classe) {
	classes.put(valeur, classe);
}

public void setClasse(HashMap<String,String> classes) {
	this.classes = classes;
}

public void setNoeud(HashMap<String,Noeud> noeuds) {
	this.noeuds = noeuds;
}

public HashMap<String, Noeud> getNoeuds() {
	return noeuds;
}
public void setNoeuds(String valeur,Noeud n) {
	noeuds.put(valeur, n);
}
}
