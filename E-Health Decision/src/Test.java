import java.sql.SQLException;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

        
        // creation d instance de Diabetique bean
        ArrayList<String> listebean = new ArrayList<String>();
        listebean.add("homme");
        listebean.add("20-30");
        listebean.add("3");
        listebean.add("chirurgien");
        listebean.add("5");
        listebean.add("0");
        listebean.add("1");
        ArrayList<String> colonnes = DButil.colonnes("ehealth","diabete");
        double produito = 1;
        double produitn = 1;

        int i = 0;
        for(String element : listebean) {
        	produito = produito * DButil.Naif("ehealth","diabete",colonnes.get(i),element,"Diabetique","oui");
        	System.out.println(colonnes.get(i));
        	i++;
        }
        i=0;
        for(String element : listebean) {
        	produitn = produitn * DButil.Naif("ehealth","diabete",colonnes.get(i),element,"Diabetique","non");
        	System.out.println(colonnes.get(i));
        	i++;
        }
        System.out.println("Oui = "+produito);
        System.out.println("Non = "+produitn);


	}

}
