

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;


public class DButil {
	// connection au BD
    public static Connection dbConnect(String dbName, String dbUserName, String dbUserPassword){
        Connection con = null;
        try{
            StringBuilder dbURL1 = new StringBuilder("jdbc:mysql://localhost/");
            dbURL1.append(dbName);
            String dbURL = dbURL1.toString();
          //  System.out.println(dbURL);
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(dbURL, dbUserName, dbUserPassword);
        }catch(ClassNotFoundException e){
            System.out.println("Driver non trouve : " + e.getMessage());
        }
        catch(SQLException e){  
            System.out.println("Connection a la base impossible : " + e.getMessage());
        }
        return con;
    }
    
    // autre connection
    public static Connection ConnectionBD(String nombd) throws SQLException, ClassNotFoundException{
		 Class.forName("com.mysql.jdbc.Driver");
		 String dbURL = "jdbc:mysql://localhost:3306/"+nombd; 
		 Connection conn = (Connection) DriverManager.getConnection(dbURL,"root","");
		 return conn;
	}
    
    // prendre l age et nombre
    public static HashMap<String, Integer> RecupAgeNb(String nombd) throws SQLException, ClassNotFoundException{
    Class.forName("com.mysql.jdbc.Driver");
    String dbURL = "jdbc:mysql://localhost:3306/"+"ehealth"; 
    Connection conn = (Connection) DriverManager.getConnection(dbURL,"root","");
    String requete="SELECT Age ,count(*) as Nombre FROM diabete where Diabetique='Oui' GROUP by Age";
    Statement sta=(Statement) conn.createStatement();
    ResultSet rs=(ResultSet) sta.executeQuery(requete);

    ArrayList<String> age = new ArrayList<String>();
    ArrayList<Integer> diab = new ArrayList<Integer>();
    HashMap<String,Integer> an = new HashMap<String,Integer>();

    while(rs.next())
    {
        an.put(rs.getString("Age"),rs.getInt("Nombre"));
        
    }
    conn.close();
    return an;
   
    }

    
    
    public static void InsererDiabetique(Diabetique_bean d) throws ClassNotFoundException, SQLException {
    	Connection conn=ConnectionBD("ehealth");
    	String requete="Insert into diabete values('"+d.getGenre()+"','"+d.getAge()+"','"+
    	d.getJours_hospi()+"','"+
    			d.getType_medcin()+"','"+d.getAnalyse()+"','"+d.getUrgence()+"','"+
    	d.getNb_hospi()+"','"+d.getDiab()+"')";
	    PreparedStatement sta=conn.prepareStatement(requete);
	    sta.executeUpdate();
	    conn.close();
        
    }
    
    
    
    // fermeture connection
    public static void closeStatement(Statement s) {
        try {
            if (s != null) {   s.close();   }
        } catch (SQLException e) {  System.out.println(e);     }
    }

    public static void closePreparedStatement(PreparedStatement ps) {
        try {  if (ps != null) {
                ps.close();            }
        } catch (SQLException e) {   System.out.println(e);         }
    }

    public static void closeResultSet(ResultSet rs) {
        try {  if (rs != null) {
                rs.close();             }
        } catch (SQLException e) { System.out.println(e);  }  
    }
    

    

    
  
    
  
    
   
	
	public static boolean Existe(String nombd,String e,String table,String att) throws ClassNotFoundException, SQLException {
		Connection conn=ConnectionBD(nombd);
		String requete="select * from "+table+" where "+att+"='"+e+"'";
	    Statement sta=(Statement) conn.createStatement();
	    ResultSet rs=(ResultSet) sta.executeQuery(requete);
	    if(rs.next()) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
	
	// prendre de la base e-health decision
	// methode qui recupere les valeurs distincs
	public static ArrayList<String> distincts(String bd,String table,String attribut) throws ClassNotFoundException, SQLException {
		Connection conn=ConnectionBD(bd);
		String requete = "select distinct "+attribut+" from "+table;
		Statement sta=(Statement) conn.createStatement();
	    ResultSet rs=(ResultSet) sta.executeQuery(requete);
	    // tableau qui va contenir les valeurs
	    ArrayList<String> A = new ArrayList<String>();
	    while(rs.next()) {
	    	A.add(rs.getString(attribut));
	    }
	    conn.close();
	    return A;
		
	}
	
	// methode qui cherche le nombre dont la valeur d attribut se trouve avec valeur critique donnée
	public static double conditionnelle(String bd,String table,String attribut,String attributdec,String end,String pred) throws SQLException, ClassNotFoundException {
		Connection conn=ConnectionBD(bd);
		String requete = "select count(*) as nb from "+table+" where "+attribut+" = '"+end+"' and "+attributdec
				+" = '"+pred+"'";
		Statement sta=(Statement) conn.createStatement();
	    ResultSet rs=(ResultSet) sta.executeQuery(requete);
	    int n;
	    if(rs.next()) {
	    	n=rs.getInt("nb");
	    	conn.close();	
	    	return n;
	    	
	    }
	    
		return 0;
	}
	
	// methode qui cherche le nombre dont la valeur d attribut se trouve une valeur
		public static double cond_seul(String bd,String table,String attribut,String end) throws SQLException, ClassNotFoundException {
			Connection conn=ConnectionBD(bd);
			String requete = "select count(*) as nb from "+table+" where "+attribut+" = '"+end+"'";
			Statement sta=(Statement) conn.createStatement();
		    ResultSet rs=(ResultSet) sta.executeQuery(requete);
		    int n;
		    if(rs.next()) {
		    	n= rs.getInt("nb");
		    	conn.close();
		    	return n;
		    	}
		    conn.close();
			return 0;
		}
		
	 // methode qui renvoie le nombre d attributs
		public static double nombre(String bd,String table,String attribut) throws ClassNotFoundException, SQLException {
			Connection conn=ConnectionBD(bd);
			String requete = "select count(*) as nb from "+table;
			Statement sta=(Statement) conn.createStatement();
		    ResultSet rs=(ResultSet) sta.executeQuery(requete);
		    int n;
		    if(rs.next()) {  n= rs.getInt("nb"); conn.close(); return n;}
		    conn.close();
			return 0;
		}
	
		
	  // methode qui calcule probablité d une valeur
		public static double prob(String bd,String table,String attribut,String end) throws ClassNotFoundException, SQLException {
			double a = cond_seul(bd,table,attribut,end);
			double b = nombre(bd,table,attribut);
			double resultat = (a/b);
			return resultat;
		}
		
		
		// methode qui calcule probablité d une valeur
		public static double probnaif(String bd,String table,String attribut,String end,String pred,String p) throws ClassNotFoundException, SQLException {
			double a = (cond_seul(bd,table,attribut,end) * cond_seul(bd,table,pred,p));
			double b = nombre(bd,table,attribut);
			double resultat = (prob(bd,table,attribut,end) * prob(bd,table,pred,p));
			return resultat;
			}
		
		public static double Naif(String bd,String table,String attribut,String end,String pred,String p) throws ClassNotFoundException, SQLException {
			Connection conn=ConnectionBD(bd);
			String requete = "select count(*) as nb from "+table+" where "+attribut+"='"+end+"' and "
					+pred+"='"+p+"'";
			Statement sta=(Statement) conn.createStatement();
		    ResultSet rs=(ResultSet) sta.executeQuery(requete);
		    double n = 0;
		    if(rs.next()) {  n= rs.getInt("nb"); conn.close();}
		    conn.close();
		    conn=ConnectionBD(bd);
			requete = "select count(*) as nb from "+table+" where "+pred+" = '"+p+"'";
			sta=(Statement) conn.createStatement();
		    rs=(ResultSet) sta.executeQuery(requete);
		    double l = 0;
		    if(rs.next()) {  l= rs.getInt("nb"); conn.close();}
			return (n/l);
		}
		
		// methode qui supprime la table temporaire
		public static void supp(String bd,String table) throws ClassNotFoundException, SQLException {
			// au debut faut savoir si la table existe nom : temp
			Connection conn=ConnectionBD(bd);
			String requete = "drop table "+table+"";
			Statement sta=(Statement) conn.createStatement();
		    sta.executeUpdate(requete);
		    conn.close();
		}
		
		// methode qui cree table temporaire et supprime la colonne exlus
		public static String table_exlus(String bd,String table,String exlus) throws ClassNotFoundException, SQLException {
			// au debut faut savoir si la table existe nom : temp
			Connection conn=ConnectionBD(bd);
			String requete = "SHOW TABLES LIKE '%temp%'";
			Statement sta=(Statement) conn.createStatement();
		    ResultSet rs=(ResultSet) sta.executeQuery(requete);
		   
		    if(rs.next()) {
		    	//existe , on doit supprimer la colonne exlus
		    	String requete3 = "alter table temp drop column "+exlus+" ";
				Statement sta3=(Statement) conn.createStatement();
			    sta.executeUpdate(requete3);
		    	
		    }
		    else {
		    	// on le cree ici
		    	// on doit au debut creer sans la colonnes exlus
				String requete2 = "create table temp as select "+selectiontable(bd,table,exlus)+" from diabete";
				Statement sta2=(Statement) conn.createStatement();
			    sta2.executeUpdate(requete2);
		    }
		    conn.close();
		    return "temp";
			
		}
		
		// methode qui supprime la colonne de temporaire
		public static int supptemp(String bd,String table,String exlus) throws ClassNotFoundException, SQLException {
			Connection conn=ConnectionBD(bd);
			// doit teste s 'il reste une seule colonne
			String requete = "SELECT count(*) as nb FROM INFORMATION_SCHEMA.COLUMNS "
					+ "WHERE TABLE_NAME = '"+table+"'";
			Statement sta=(Statement) conn.createStatement();
			ResultSet rs=(ResultSet) sta.executeQuery(requete);
			rs.next();
			int r = rs.getInt("nb");
			
			if(rs.getInt("nb")==1) {
				String requete2 = "drop table temp ";
				Statement sta2=(Statement) conn.createStatement();
			    sta2.executeUpdate(requete2);
			    conn.close();
			}
			
			else {
			String requete2 = "alter table temp drop column "+exlus+" ";
			Statement sta2=(Statement) conn.createStatement();
		    sta2.executeUpdate(requete2);
		    conn.close();
			}
			return r;
		}
		
		// methode qui cree table temporaire
		public static void temp(String bd,String table) throws ClassNotFoundException, SQLException {
			Connection conn=ConnectionBD(bd);
			String requete = "SHOW TABLES LIKE '%temp%'";
			Statement sta=(Statement) conn.createStatement();
		    ResultSet rs=(ResultSet) sta.executeQuery(requete);
		    if(!rs.next()) { // ajoute seulement si ca existe
			String requete2 = "create table temp as select * from diabete";
			Statement sta2=(Statement) conn.createStatement();
		    sta2.executeUpdate(requete2); }
		    conn.close();
		}
		
		
	 // methode qui exlus colonne et retounre la liste des colonnes pr etre selecté
		public static String selectiontable(String bd,String table,String exlus) throws ClassNotFoundException, SQLException {
			Connection conn=ConnectionBD(bd);
			String requete = "select * from "+table;
			Statement sta=(Statement) conn.createStatement();
		    ResultSet rs=(ResultSet) sta.executeQuery(requete);
			
		    ArrayList<String> colonnes = new ArrayList<String>();
		    
		    ResultSetMetaData rsmd = rs.getMetaData();
		    
		    int i=1;
		    int premier = 0;
		    
		    StringBuilder select = new StringBuilder();
		    
		    // on extrait les colonnes
		    while(rs.next() && i<=rsmd.getColumnCount()) {
		    	colonnes.add(rsmd.getColumnName(i));
		    	i++;
		    
		    }
		    
		    // pour chaque colonne voir si ca va pas etre egale au exlus
		    for ( String col : colonnes ) {
		    	if( !col.equals(exlus) ) {
		    		premier++;
		    		if(premier==1) select.append(col);
		    		else select.append(" , "+col);
		    	}
		    }
		    
		    conn.close();
		    
		    // on va extraire le string builder de la table
		    //String req = "select "+select.toString()+" from "+table;
		    
		    return select.toString();
		    
		}
		
		// methode qui donne liste des colonnes
		public static ArrayList<String> colonnes(String nombd,String table) throws SQLException, ClassNotFoundException{
			// on extrait les noms de colonnes
			Connection conn=ConnectionBD(nombd);
			String requete = "SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS "
					+ "WHERE TABLE_NAME = '"+table+"'";
			Statement sta=(Statement) conn.createStatement();
		    ResultSet rs=(ResultSet) sta.executeQuery(requete);
			// liste qui va recupere les colonnes
		    ArrayList<String> liste = new ArrayList<String>();
		    while(rs.next()) {
				liste.add(rs.getString("column_name"));
			}
		    conn.close();
		    return liste;
		}
		
		// method qui donne liste des valeurs d'une colonne
		public static ArrayList<String> valeurs(String nombd,String table,String attribut) throws SQLException, ClassNotFoundException{
			// on extrait les noms de colonnes
			Connection conn=ConnectionBD(nombd);
			String requete = "SELECT distinct "+attribut+" from "+table ;
			Statement sta=(Statement) conn.createStatement();
		    ResultSet rs=(ResultSet) sta.executeQuery(requete);
			// liste qui va recupere les colonnes
		    ArrayList<String> liste = new ArrayList<String>();
		    while(rs.next()) {
				liste.add(rs.getString(attribut));
			}
		    conn.close();
		    return liste;
		}
		
		
		// methode qui calcule si la valeur a la meme classe ou pas
		public static String classe(String nombd,String table,String att,String v,String pred) throws ClassNotFoundException, SQLException {
			Connection conn=ConnectionBD(nombd);
			String requete="select DISTINCT "+pred+" from "+table+" where "+att+" = '"+v+"'";
		    Statement sta=(Statement) conn.createStatement();
		    ResultSet rs=(ResultSet) sta.executeQuery(requete);
		    //
		    // on va voir si meme classe ou pas
		    int cmp=0;
		    String classe = null;
		    while(rs.next()) {
		    	cmp++;
		    	classe = rs.getString(pred);
		    }
		    conn.close();
		    // si egale null n est pas meme classe
		    if(cmp==1) return classe;
		    return null;
		    
		}

	
	public static String[] Afficher(String nombd,String table,String att) throws ClassNotFoundException, SQLException {
		Connection conn=ConnectionBD(nombd);
		String requete="select * from "+table+" ";
	    Statement sta=(Statement) conn.createStatement();
	    ResultSet rs=(ResultSet) sta.executeQuery(requete);
	    int i=0;
	    int n=0;
	    String[] s = new String[5];
	    while(rs.next()) {
	    	s[i]=rs.getString(att);
	    	i++;
	    }
	    conn.close();
	    return s;
	}
	
    
    
    
    
}


