package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;

import control.util.JSONResponse;
import model.bean.UtenteBean;
import model.manager.UtenteManager;
import utility.Strings;

/**
 * Gestisce l'operazione di login
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		response.setContentType("text/html");

		String nomeUtente = request.getParameter("NomeUtente");
		String password = request.getParameter("Password");
		boolean isChecked = Boolean.parseBoolean(request.getParameter("Ricordami"));

		if(nomeUtente == null || password == null) {
			JSONResponse jsonResponse = new JSONResponse(false, Strings.NO_ARGUMENT);
			out.print(gson.toJson(jsonResponse));
			return;
		}
		if(nomeUtente == "" || password == "") {
			JSONResponse jsonResponse = new JSONResponse(false, Strings.NO_ARGUMENT);
			out.print(gson.toJson(jsonResponse));
			return;
		}


		String passwordBase64format  = Base64.getEncoder().encodeToString(password.getBytes()); 
		UtenteManager utenteManager= new UtenteManager(); 
		UtenteBean user = utenteManager.getUtente(nomeUtente, passwordBase64format);
				
		if(user == null) {
			JSONResponse jsonResponse = new JSONResponse(false, Strings.NO_USER_PASSWORD);
			out.print(gson.toJson(jsonResponse));
			return;	
		}
		else {
			HttpSession session = request.getSession();
			if(isChecked) {
				Cookie c = new Cookie("userName", user.getNomeUtente());
				Cookie c2  = new Cookie("password", user.getPassword());

				c.setMaxAge(1800);
				c2.setMaxAge(1800);

				c.setSecure(false);
				c2.setSecure(false);

				response.addCookie(c);
				response.addCookie(c2);
				// session.setMaxInactiveInterval(360 * 60 * 30);
			}
			session.setAttribute("User", user);
			JSONResponse jsonResponse = new JSONResponse(true, "OK", user.getNomeUtente());
			out.print(gson.toJson(jsonResponse));
		}
	}
}
