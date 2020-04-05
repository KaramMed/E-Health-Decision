import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import com.mysql.jdbc.ResultSet;

public class Entropie {
 
	public static double calculEntropie(String bd,String table,String attribut,String predictive) throws ClassNotFoundException, SQLException {
		// on commence par recuperer les valeurs distincs d attribut predictive
		ArrayList<String> pred = DButil.distincts(bd,table,predictive);
		// ensuite les valeurs distincs de l attribut souhaité
		ArrayList<String> att = DButil.distincts(bd,table,attribut);
		
		// pour chaque valeur predictive on ajoute cette somme
		double gain = 0;
		// pour chaque valeur endrogene on soustrait cette somme
		double ent = 0;
		double c1,c2,p1;
				
		for( String p : att ) {
			for(String a : pred ) {
                c1 = DButil.conditionnelle(bd,table,attribut,predictive,p,a);
                c2 = DButil.cond_seul(bd, table, attribut,p);
                // en cas meme classe
                if(c1==0) ent = 0;
                else ent = ent - ( (c1/c2)*( Math.log10(c1/c2)) );
			}   

			p1 = DButil.prob(bd, table, attribut,p);
			ent = ent * p1;
			gain = gain + ( ent );
			ent = 0 ; // compteur doit reintialiser
		}
		return gain;
	}
	
	// compteur pour eviter une boucle infinie
	public static int compteur = 0;
	public static Noeud n = new Noeud();
	public static Noeud arbre = new Noeud();
	
	// liste qui va contenir les classe
	public static HashMap<String,String> classee = new HashMap<String,String>();
	// liste qui va contenir les noeuds
	public static HashMap<String,Noeud> noeudss = new HashMap<String,Noeud>();
	public static ArrayList<String> name = new ArrayList<String>();
	
	
	public static ArrayList<String> regles = new ArrayList<String>();
	
	
	// methode qui construit l'arbre
	public static Noeud arbre(String bd,String table,String predictive) throws ClassNotFoundException, SQLException {

		// creer temp si se trouve pas
		DButil.temp(bd,table);
		// recup liste de tout les colonnes
		ArrayList<String> attribut = DButil.colonnes(bd,"temp");
		// calcul leurs entropies
		HashMap<String,Double> entropies = new HashMap<String,Double>();
		for( String att : attribut ) {
			entropies.put(att,calculEntropie(bd,"temp",att,predictive));
		}
		
		// recup colonne avec max entropie
		String att_dec = null ; // attribut avec entropie max
		Double maxentropie = Collections.max(entropies.values());
		for(String key : entropies.keySet()) {
			if(maxentropie==entropies.get(key)) {
				att_dec = key;
			}
		}
		// apres avoir entropie max
		// on recupere les valeurs d'attribut avec entropie max et savoir si meme classe
		ArrayList<String> valeurs = DButil.valeurs(bd, "temp", att_dec);
		//System.out.println("Max entropie : "+att_dec);
		for( String val : valeurs ) {
			//System.out.print(val);
			String classe = DButil.classe(bd, "temp", att_dec, val,predictive);
			if(classe!=null) {
				// on ajoute a la liste des classes
				classee.put(val,classe);
				arbre.setClasse(classee);
				name.add(att_dec);
				arbre.setNom(name);
				//System.out.println(att_dec +" : "+val+" a comme classe : "+classe);
				if(!att_dec.equals("Diabetique"))
					regles.add("Si "+att_dec+" est "+val+" alors la decision est "+classe);	
			}
			else {
				// on ajoute a la liste noeuds
				noeudss.put(val,n);
				arbre.setNoeud(noeudss);
				name.add(att_dec);
				arbre.setNom(name);
				//arbre.nom.add(att_dec);
				//System.out.println(att_dec +" : "+val+" n a pas meme classe ");
				
			}
			
		}

		// pour arreter la recursivité
		int nn = DButil.supptemp(bd, "temp",att_dec);
		if(nn>1) n = arbre(bd,"temp",predictive);

		return n;
	}
	
	// methode qui va contenir les deux liste dans le noeuds
	public static Noeud Arbre() {
	     Noeud arbree = new Noeud();
	     arbree = arbre;
	     return arbree;
	}
	
	public static ArrayList<String> afficheArbre() {
		ArrayList<String> l = new ArrayList<String>();
		return regles;
	}

			
	
		
		
		
	
	
	

	
	
public static void main(String[] args) throws ClassNotFoundException, SQLException {
	//System.out.println(DButil.selectiontable("ehealth","diabete", "Jours_Hospitalises"));
	Noeud n = new Noeud();
    n = arbre("ehealth","diabete","Diabetique");
    
    Noeud arbre = Arbre(); // c celui qui va contenir le tout
    



    
    //parcourArbre(arbre);
    
    // donc pour parcourir
    // pour chaque colonne on va voir qui appartient a quelle liste
    
    
    
   
    
    
    

	
	
}
}
