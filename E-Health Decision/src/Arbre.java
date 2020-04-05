

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Arbre
 */
@WebServlet("/Arbre")
public class Arbre extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Arbre() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void procedure(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
    	// creation d'une liste des champs et leurs entropies
    	
    	DecimalFormat df = new DecimalFormat(".##");
    	
    	HashMap<String,Double> liste = new HashMap<String,Double>();
    	liste.put("Entropie (Genre) ",Entropie.calculEntropie("ehealth","diabete","Genre","Diabetique"));
    	liste.put("Entropie (Age) ",Entropie.calculEntropie("ehealth","diabete","Age","Diabetique"));
    	liste.put("Entropie (Jours Hospitalisés) ",Entropie.calculEntropie("ehealth","diabete","Jours_Hospitalises","Diabetique"));
    	liste.put("Entropie (Medcins Consultés) ",Entropie.calculEntropie("ehealth","diabete","Types_Medcins_Consultes","Diabetique"));
    	liste.put("Entropie (Nombre Analyses) ",Entropie.calculEntropie("ehealth","diabete","Nombre_Analyses","Diabetique"));
    	liste.put("Entropie (Hospitalisations d'urgence) ",Entropie.calculEntropie("ehealth","diabete","Nombre_hospitalisations_urgentes","Diabetique"));
    	liste.put("Entropie (Nombre Hospitalisations)",Entropie.calculEntropie("ehealth","diabete","Nombre_hospitalisations","Diabetique"));
    	
    	request.setAttribute("liste",liste);
    	
    	ArrayList<String> liste2 = Entropie.afficheArbre();
    	
    	request.setAttribute("liste2",liste2);
    	
    	
    	getServletContext().getRequestDispatcher("/Arbre.jsp").forward(request,response);
	}
    
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
