package control;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;

import control.util.JSONResponse;
import model.bean.UtenteBean;
import model.manager.UtenteManager;
import utility.EmailSender;
import utility.Strings;

/** 
 * Gestisce la registrazione di un nuovo utente
 **/

@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistrazioneServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter(); 
		Gson gson = new Gson();
		response.setContentType("text/html");	
		
		String nomeUtente = request.getParameter("NomeUtente");
		String email = request.getParameter("Email");
		String password = request.getParameter("Password");
		String confPassword = request.getParameter("ConfermaPassword");
		
		String pattern = "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
		String patternPassword = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!()?.])(?=\\S+$).{8,}$";
		
		if (nomeUtente == null || email == null ||password == null || confPassword == null) {
			JSONResponse jsonResponse = new JSONResponse(false, Strings.NO_ARGUMENT);
			out.print(gson.toJson(jsonResponse));
			return;
		}
		
		if(nomeUtente.length() < 4) {
			JSONResponse jsonResponse = new JSONResponse(false, Strings.INVALID_USER);
			out.print(gson.toJson(jsonResponse));
			return;
		}
		
		if(!password.matches(patternPassword)) {
			JSONResponse jsonResponse = new JSONResponse(false, Strings.INVALID_PASSWORD);
			out.print(gson.toJson(jsonResponse));
			return;
		}
		
		if(!password.equals(confPassword)) {
			JSONResponse jsonResponse = new JSONResponse(false, Strings.NO_PASSWORD);
			out.print(gson.toJson(jsonResponse));
			return;	
		}
		
		if(!email.matches(pattern)){
			JSONResponse jsonResponse = new JSONResponse(false, Strings.WRONG_EMAIL_FORMAT);
			out.print(gson.toJson(jsonResponse));
			return;	
		}else {
			String passwordBase64format  = Base64.getEncoder().encodeToString(password.getBytes());

			UtenteManager utenteManager= new UtenteManager();
			boolean res =utenteManager.registrazione(email, nomeUtente, passwordBase64format);
	
			if(!res) {
				JSONResponse jsonResponse = new JSONResponse(false, Strings.NO_USER);
				out.print(gson.toJson(jsonResponse));
				return;			
			}else {
				UtenteBean user =utenteManager.login(nomeUtente, passwordBase64format);
				HttpSession session = request.getSession();
				session.setAttribute("User", user);
				JSONResponse jsonResponse = new JSONResponse(true, "OK");
				out.print(gson.toJson(jsonResponse));
			}
		}
	}

}