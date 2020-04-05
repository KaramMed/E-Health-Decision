

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Servlet implementation class AcceuilHttp
 */
@WebServlet("/AcceuilHttp")
public class AcceuilHttp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceuilHttp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws JSONException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    public static String calculNaif(ArrayList<String> bean) throws ClassNotFoundException, SQLException {
    	String bd = "ehealth";
    	String table = "diabete";
    	String pred = "Diabetique";
    	double o=1;
    	double n=1;
    	// noms des colonnes
    	ArrayList<String> colonnes = DButil.colonnes(bd, table);
    	int i=0;
    	for( String att : bean ) {
    		o = o * DButil.probnaif(bd, table , colonnes.get(i), att , "Diabetique" , "Oui");
    		i++;
    	}
    	
    	i=0;
    	for( String att : bean ) {
    		n = n * DButil.probnaif(bd, table,colonnes.get(i), att , "Diabetique" , "Non");
    		i++;
    	}
    	
        System.out.println("Oui :  "+o);
        System.out.println("Non :  "+n);

    	if(o>n) return "Oui";
    	else return "Non";

    }
    
    
    
	protected void procedure(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
	  // receuil du parametre
		String genre = request.getParameter("genre");
        String age = request.getParameter("age");
        String jours_hospi = request.getParameter("jours_hospi");
        String type = request.getParameter("type_medcin");
        String analyse = request.getParameter("analyse");
        String urgence = request.getParameter("urgence");
        String hosp = request.getParameter("hosp");
        
        // creation d instance de Diabetique bean
        ArrayList<String> listebean = new ArrayList<String>();
        listebean.add(genre);
        listebean.add(age);
        listebean.add(jours_hospi);
        listebean.add(type);
        listebean.add(analyse);
        listebean.add(urgence);
        listebean.add(hosp);
        
        Diabetique_bean e = new Diabetique_bean(genre,age,jours_hospi,type,analyse,urgence,hosp,"");
		
        

		// renvoie d'un message
		String reponse = calculNaif(listebean);
		System.out.println(reponse);
		PrintWriter p = response.getWriter();
		p.write(reponse);
	}
    
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// l application recupere ce qui etait print writed
		try {
			procedure(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
