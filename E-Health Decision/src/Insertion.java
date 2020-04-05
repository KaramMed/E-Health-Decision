

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Insertion
 */
@WebServlet("/Insertion")
public class Insertion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insertion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
    
    public void procedure(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
    	HttpSession session=request.getSession();
        PrintWriter out = response.getWriter();
        // on commencer par recuprer et creer un bean
        String genre = request.getParameter("genre");
        String age = request.getParameter("age");
        String jours_hospi = request.getParameter("jourshospi");
        String type_medcin = request.getParameter("typemedcin");
        String analyse = request.getParameter("nbanalyse");
        String urgence = request.getParameter("urgence");
        String nb_hospi = request.getParameter("nbhospi");
        String diab = request.getParameter("diab");
        
        Diabetique_bean d = 
        new Diabetique_bean(genre, age,jours_hospi,type_medcin,analyse,urgence,nb_hospi,diab);
        
        // envoie
        
        DButil.InsererDiabetique(d);
        
        // retour a la page
        getServletContext().getRequestDispatcher("/Ameliorer.jsp").forward(request,response);;
        
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
