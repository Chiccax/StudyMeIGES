package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import control.util.JSONResponse;
import model.bean.UtenteBean;
import model.manager.UtenteManager;

/**
 * Gestisce la modifica dei dati personali
 */
@WebServlet("/ModificaAreaUtenteServlet")
public class ModificaAreaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ModificaAreaUtenteServlet() {
        super();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		Gson gson = new Gson();
		UtenteManager utenteManager= new UtenteManager();
		String nuovaEmailUtente = request.getParameter("NuovaEmailUtente");
		String nuovaPasswordUtente = request.getParameter("NuovaPasswordUtente");
		String confermaNuovaPasswordUtente = request.getParameter("ConfermaNuovaPasswordUtente");
		UtenteBean loggedUser = (UtenteBean) request.getSession().getAttribute("User");
		if(loggedUser == null) {
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/HomePage.jsp");
			dispatcher.forward(request, response);
		}else {
			String emailUtente = loggedUser.getEmail();
			if(nuovaEmailUtente != null && nuovaPasswordUtente == null) {
				if(nuovaEmailUtente.length() <= 0) {
					JSONResponse jsonResponse = new JSONResponse(false, NO_EMAIL);
					out.print(gson.toJson(jsonResponse));
					return;	
				}
		
				String pattern = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
				
				if(!nuovaEmailUtente.matches(pattern)){
					JSONResponse jsonResponse = new JSONResponse(false, WRONG_EMAIL_FORMAT);
					out.print(gson.toJson(jsonResponse));
					return;	
				}
				
				boolean res = utenteManager.setEmail(emailUtente, nuovaEmailUtente);
				if(res == false) {
					JSONResponse jsonResponse = new JSONResponse(false, EMAIL_ESISTENTE);
					out.print(gson.toJson(jsonResponse));
					return;	
				}else {
					loggedUser.setEmail(nuovaEmailUtente);
					JSONResponse jsonResponse = new JSONResponse(true, "OK");
					out.print(gson.toJson(jsonResponse));
				}
			} else if(nuovaEmailUtente == null && nuovaPasswordUtente != null) {
				String patternPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!()?.])(?=\\S+$).{8,}$";
				if(nuovaPasswordUtente.equals(confermaNuovaPasswordUtente)) {
					if(!nuovaPasswordUtente.matches(patternPassword)) {
						JSONResponse jsonResponse = new JSONResponse(false, NO_VALIDEPASSWORD);
						out.print(gson.toJson(jsonResponse));
						return;	
					}
					if(nuovaPasswordUtente.length() <= 0){
						JSONResponse jsonResponse = new JSONResponse(false, EMPTY_PASSWORD);
						out.print(gson.toJson(jsonResponse));
						return;	
					}
					String passwordBase64format  = Base64.getEncoder().encodeToString(nuovaPasswordUtente.getBytes()); 
					boolean res = utenteManager.setPassword(emailUtente, passwordBase64format);
					if(res == false) {
						JSONResponse jsonResponse = new JSONResponse(false, NO_USER);
						out.print(gson.toJson(jsonResponse));
						return;	
					}else {
						JSONResponse jsonResponse = new JSONResponse(true, "OK");
						out.print(gson.toJson(jsonResponse));
					}
				} else {
					JSONResponse jsonResponse = new JSONResponse(false, NO_PASSWORD);
					out.print(gson.toJson(jsonResponse));
					return;	
				}
			}
		}
	}
	
	private static final String NO_EMAIL = "Campo email vuoto";
	private static final String WRONG_EMAIL_FORMAT=  "Formato email non valido";
	private static final String NO_USER = "Utente non esistente";
	private static final String EMAIL_ESISTENTE= "L'email � gi� presente nel sistema";
	private static final String NO_VALIDEPASSWORD = "La password deve contenere almeno un carattere numerico, una maiuscola, una minuscola, un carattere speciale e almeno 8 caratteri.";
	private static final String NO_PASSWORD = "Le password non coincidono";
	private static final String EMPTY_PASSWORD = "Campo password vuoto";
}