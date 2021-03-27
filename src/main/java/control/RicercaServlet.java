package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.PacchettoBean;
import model.manager.RicercaManager;
/** 
 * Gestisce la ricerca di un pacchetto
 **/

@WebServlet("/RicercaServlet")
public class RicercaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RicercaServlet() {
        super();
         }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String argument = request.getParameter("argument");
		
		if(argument == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		argument = argument.trim();
		RicercaManager ricercaManager= new RicercaManager(); 
		ArrayList<PacchettoBean> pacchettiRicercati= ricercaManager.ricercaPacchetto(argument);
		
		request.setAttribute("pacchetti", pacchettiRicercati);
		request.setAttribute("argomento", argument);

		RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/Ricerca.jsp");
		dispatcher.forward(request, response);	
	}

	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
