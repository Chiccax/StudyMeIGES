package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import control.util.JSONResponse;
import model.manager.RecensioneManager;
import utility.Strings;

/**
 * Gestisce l'inserimento di una recensione
 **/
@WebServlet("/RecensioneServlet")
public class RecensioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public RecensioneServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();

		String new_old_recensione = request.getParameter("new_old_recensione");
		String nomeUtente = request.getParameter("utente");
		String codicePacchetto = request.getParameter("codicePacchetto");
		String titoloRecensione = request.getParameter("titoloRecensione");
		String testoRecensione = request.getParameter("testoRecensione");

		if(nomeUtente == null || codicePacchetto == null || titoloRecensione == null || testoRecensione == null) {
			JSONResponse jsonResponse = new JSONResponse(false, Strings.NO_ARGUMENT);
			out.print(gson.toJson(jsonResponse));
			return;
		}
		if(titoloRecensione.equals("")  || testoRecensione.equals("")) {
			JSONResponse jsonResponse = new JSONResponse(false, Strings.NO_ARGUMENT);
			out.print(gson.toJson(jsonResponse));
			return;
		}
		if(titoloRecensione.length() < 5 || titoloRecensione.length() > 30 ) {
			JSONResponse jsonResponse = new JSONResponse(false, Strings.INVALID_TITLE);
			out.print(gson.toJson(jsonResponse));
			return;
		}
		if(testoRecensione.length() < 10 || testoRecensione.length() > 30 ) {
			JSONResponse jsonResponse = new JSONResponse(false, Strings.INVALID_TEXT);
			out.print(gson.toJson(jsonResponse));
			return;
		}

		RecensioneManager recensioneManager= new RecensioneManager();
		if(new_old_recensione.equalsIgnoreCase("new")){
			recensioneManager.aggiungiRecensione(nomeUtente, codicePacchetto, titoloRecensione, testoRecensione);
			JSONResponse jsonResponse = new JSONResponse(true);
			out.print(gson.toJson(jsonResponse));
		} else if(new_old_recensione.equalsIgnoreCase("old")){
			try {
				recensioneManager.modificaRecensione(nomeUtente,codicePacchetto,titoloRecensione,testoRecensione);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			JSONResponse jsonResponse = new JSONResponse(true);
			out.print(gson.toJson(jsonResponse));
		}
    }


}