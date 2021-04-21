<%@page import="java.util.ArrayList" %>
<%@page import="model.bean.*" %>
<%@page import="model.dao.*" %>

<!DOCTYPE html>
<html>
<head>
    <link rel = "stylesheet" href = "css/Lezione.css">
	<meta charset="ISO-8859-1">
	<link rel="shortcut icon" type = "image/ico" href="img/utility/Logo.ico"/>
	<title>Lezioni</title>
</head>
	<%@ include file = "header.jsp" %>
<body>
	<div class ="containerLezioni">
	 	<%@ include file="BarraNavigazione.jsp"%>  
	 	<%@ include file="BarraCategoria.jsp"%> 	
	 
	 	<%
			ArrayList<LezioniBean> result = (ArrayList<LezioniBean>)request.getAttribute("lezioni");
			PacchettoBean pacchetto = (PacchettoBean)request.getAttribute("pacchetto");
			String titoloMod = null;
			String commentoMod = null;
			ArrayList<RecensioneBean> recensioni = (ArrayList<RecensioneBean>)request.getAttribute("recensioni");
			boolean comprato = (boolean) request.getAttribute("comprato");
			boolean nelCarrello = (boolean) request.getAttribute("nelCarrello");
			boolean recensito = (boolean) request.getAttribute("recensito");
			if(recensito){
				for (RecensioneBean recensione : recensioni){
					if (recensione.getCliente().equals(loggedUser.getNomeUtente())){
						titoloMod = recensione.getTitolo();
						commentoMod = recensione.getCommento();
					}
				}
			}
			String tipo= (String) request.getAttribute("tipo");

			if(result == null || result.size() == 0){%>
	 		<img src = "img/utility/no-lesson.svg" id = "immagineNoLezione" alt = "Lezione non trovata">	
	 		<h1 id = "noLesson"> Nessuna lezione trovata</h1>
   		<%}else{%>			
   				<div id = "pacchetto">
					<!--Conferma rimozione pacchetto -->
					<div id="confermaRimozione">
						<p> Sei sicuro di voler rimuovere il seguente pacchetto dal carrello?</p>
						<div id = "pulsantiConferma">
							<div id="conferma" onClick = "rimuoviDalCarrello()">Si</div>
							<div id="noConferma" onClick = "annullaRimozione()">No</div>
						</div>
					</div>
   					<h1 id = "titoloPacchetto"><%=pacchetto.getTitolo()%>*</h1>
   					<p id = "descrizione"><%=pacchetto.getDescrizione()%>.</p>
   					<p id  ="prezzo"><strong>Prezzo:</strong> <%=pacchetto.getPrezzo()%>&euro;</p>
   					<div id="bottoni">
 
   						<%if(comprato) {%>
   							<span id="videoIntroduzione" onClick="redirectTo('LibreriaServlet')"> Vai al corso <i class="far fa-play-circle"></i></span>
   							<%if(recensito){ %>
								<div id="recensione" onClick = "modificaUnaRecensione('<%=loggedUser.getNomeUtente()%>')"> Modifica la tua recensione <i class="fas fa-pen-alt"></i></div>
							<%} else {%>
   								<div id="recensione" onClick = "lasciaUnaRecensione('<%=loggedUser.getNomeUtente()%>')"> Lascia una recensione <i class="fas fa-pen-alt"></i></div>
   							<%}%>
						<%	} else {
						%>
							<div id="videoIntroduzione" onClick = "mostraLezioneGratis('<%=result.get(0).getUrl()%>')"> Guarda prima lezione gratis <i class="far fa-play-circle"></i></div>
							<%
							if(!tipo.equals("insegnante") && !tipo.equals("gestorecatalogo")){
								if(nelCarrello) { %>
								<div id ="aggiungiAlCarrello" action="false" data="<%=pacchetto.getCodicePacchetto()%>" onClick = "aggiungiAlCarrello()" style="background: red;">Rimuovi dal carrello <i class="fas fa-trash-alt"></i></div>
   							<%} else { %>
	   							<div id ="aggiungiAlCarrello" action="false" data="<%=pacchetto.getCodicePacchetto()%>" onClick = "aggiungiAlCarrello()">Aggiungi al carrello <i class="fas fa-cart-plus"></i></div>
								<div id ="acquistaOra" action="false" data="<%=pacchetto.getCodicePacchetto()%>" onClick = "acquistaOra()">Acquista ora<i class="fas fa-cart-plus"></i></div>
						<%}}%>
						<%} %>
   						</div>
   				</div>

				<div id = "recensioni">
					<i class="fas fa-quote-left" id = "topIcon"></i>
					<h1 id = "titoloRecensioni">Recensioni</h1>
					<%if(recensioni == null || recensioni.size() == 0) {%>
						<p id ="commento">Non ci sono recensioni per questo pacchetto</p>
					<%} else{ for(RecensioneBean recensione : recensioni){%>
						<p id ="commento">''<%= recensione.getTitolo()%>''<br> <%=recensione.getCommento()%></p>
						<p id = "recensore"> <strong><%=recensione.getCliente()%></strong> </p>
					<%}
					}%>
					<i class="fas fa-quote-left" id = "bottomIcon"></i>
				</div>
				<p id="avviso">*Acquistando questo pacchetto avrai accesso a tutte le sue lezioni direttamente dalla tua libreria</p>	
				
				<!-- Video -->
			    <div id = "sfondoVideo">
			    	<div id = "close-video" onClick = "nascondiLezioneGratis()">
						<i class="far fa-times-circle"></i>
					</div>
				    <div id ="video">
				    </div>
			    </div>		
			    
		    <!-- Aggiungi/modifica recensione -->
			<div id="sfondoRecensione">
				<div id="close-icon" onClick="nascondiAggiuntaRecensione()">
					<i class="far fa-times-circle"></i>
				</div>
				
				<div id = "containerRecensione">
					<div id="aggiungiRecensione">
						<%if(recensito){ %>
							<h2>Modifica la tua recensione</h2>
						<%} else {%>
							<h2>Lascia una nuova recensione</h2>
						<%} %>
						<div id="insuccess"></div>
						<input type="hidden" value= "<%=pacchetto.getCodicePacchetto()%>" id="pacchettoDaRecensire" required readonly>
						<input type="hidden" value= "" id="nomeUtenteRecensore" required readonly>
						<div id="add">
							<div id="titoloRecensione">
								<%--@declare id="uname"--%><label for="uname"><b>Titolo recensione: </b></label>
									<%if(recensito){ %>
										<input id = "titoloR" value = "<%=titoloMod%>" type="text" required>
									<%} else {%>
										<input id = "titoloR" placeholder = "Inserire titolo recensione" type="text" required>
									<%} %>
							</div>
							<div id="testoRecensione">
								<label for="uname"><b>Recensione: </b></label>
								<%if(recensito){ %>
								<textarea rows="3" cols="55" placeholder = "Inserire recensione" id="txtRecensione"><%=commentoMod%></textarea>
								<%} else {%>
								<textarea rows="3" cols="55" placeholder = "Inserire recensione" id="txtRecensione"></textarea>
								<%} %>

							</div>
							<%if(recensito){ %>
								<button class="bottoneDefault" onClick="updateReview()">Modifica la recensione</button>
							<%} else {%>
								<button class="bottoneDefault" onClick="addReview()">Aggiungi recensione</button>
							<%} %>

						</div>
					</div>
				</div>
			</div>

		<%}%>
 	<%@ include file="Footer.jsp"%> 
 	</div>
 	<script type="text/javascript" src="./js/catalogo.js"></script>
 </body>
</html>