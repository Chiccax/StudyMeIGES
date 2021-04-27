/*
    Funzioni estetiche
*/

function ValidateEmail(inputText)
{
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	if(inputText.value.match(mailformat))
	{
		document.form1.text1.focus();
			return true;
	}
	else
	{
		alert("You have entered an invalid email address!");
		document.form1.text1.focus();
		return false;
	}
}

function allLetter(inputtxt)
{ 
	//Solo lettere e/o numeri ed abbia una lunghezza minima di 6 e massima di 12 caratteri
	//SONO STATE AGGIUNTE DELLE PARENTESI TONDE CHE NON CI VOLEVANO ALTRIMENTI NON FUNGE
	var letters = /^([a-z0-9]{6,12})+$/;
	
	if(inputtxt.value.match(letters))
	{
		alert('Your name have accepted : you can try another');
		return true;
	}
		else
	{
			alert('Please input alphabet characters only');
			return false;
	}
}

function togglePassword(event) {
	var showClass = "fa-eye-slash";
	var hideClass = "fa-eye";
	
	var x = event.target; /*Memorizza chi ha chiamato l'evento*/
	var input = x.parentElement.querySelector("input");
	
	if (input.type === "password") {
		input.type = "text";
		x.classList.remove(showClass);
		x.classList.add(hideClass);
	} else {
		input.type = "password";
		x.classList.remove(hideClass);
		x.classList.add(showClass);
	}
}

function mostraLogin() { 
	var x = document.getElementsByClassName("sfondo-login");
	var y = document.getElementsByClassName("container-login");
	var i, j;
	
	console.log("Mostra Login");
	
	x[0].style.display = "block";
	
	y[0].style.display = "block";
	
	x[1].style.display = "none";
	
	y[1].style.display = "none";
	
	x[2].style.display = "none";
	
	y[2].style.display = "none";

	
}

function mostraRegistrazione(){
	var x = document.getElementsByClassName("sfondo-login");
	var y = document.getElementsByClassName("container-login");
	
	console.log("Mostra registrazione");
	
	x[0].style.display = "none";
	
	y[0].style.display = "none";
	
	x[1].style.display = "block";
	
	y[1].style.display = "block";
	
	x[2].style.display = "none";
	
	y[2].style.display = "none";
}

function ValidatePassword() {
	var rules = [{
		Pattern: "[A-Z]",
		Target: "UpperCase"
	},
		{
			Pattern: "[a-z]",
			Target: "LowerCase"
		},
		{
			Pattern: "[0-9]",
			Target: "Numbers"
		},
		{
			Pattern: "[-_.;:!@/=()£§+{}?^#$%^&*]",
			Target: "Symbols"
		}
	];
	var password = $(this).val();
	$("#Length").removeClass(password.length > 7 ? "format-pass-red" : "format-pass-green");
	$("#Length").addClass(password.length > 7 ? "format-pass-green" : "format-pass-red");
	for (var i = 0; i < rules.length; i++) {
		$("#" + rules[i].Target).removeClass(new RegExp(rules[i].Pattern).test(password) ? "format-pass-red" : "format-pass-green");
		$("#" + rules[i].Target).addClass(new RegExp(rules[i].Pattern).test(password) ? "format-pass-green" : "format-pass-red");
	}
}

$(document).ready(function() {
	$("#NewPassword").on('keyup', ValidatePassword)
});
function mostraBubble(){
	document.getElementById("mostraBubble").style.display="block";
}
function nascondiBuble(){
	document.getElementById("mostraBubble").style.display="none";
}

function mostraRecuperoPassword(){
	var x = document.getElementsByClassName("sfondo-login");
	var y = document.getElementsByClassName("container-login");
	
	console.log("Mostra Recupero");
	
	x[0].style.display = "none";
	
	y[0].style.display = "none";
	
	x[1].style.display = "none";
	
	y[1].style.display = "none";
	
	x[2].style.display = "block";
	
	y[2].style.display = "block";
	
	document.getElementById("formRecovery").style.display="block";
	document.getElementById("emailF").value = null;
	document.getElementById("recovery").innerHTML = "Recupero password";
	$("#recovery").css("text-align", "left");
}

function nascondi() {
	nascondiBuble();
	var x = document.getElementsByClassName("sfondo-login");
	var y = document.getElementsByClassName("container-login");
	
	var i, j;
	
	x[0].style.display = "none";
	
	x[1].style.display = "none";
	
	x[2].style.display = "none";
	
	y[0].style.display = "none";
	
	y[1].style.display = "none";
	
	y[2].style.display = "none";	
	
	document.getElementById('messageError').style.opacity= "0";
	document.getElementById('signUpMessageError').style.opacity= "0";
	document.getElementById('myName').style.border= "1px solid #ccc";
	document.getElementById('myPass').style.border= "1px solid #ccc";
	document.getElementById('myName').style.border= "1px solid #ccc";
	document.getElementById('regPass').style.border= "1px solid #ccc";
	document.getElementById('regPassRepeat').style.border= "1px solid #ccc";
	document.getElementById('emailF').style.border= "1px solid #ccc";
	document.getElementById("newName").style.border= "1px solid #ccc";
	document.getElementById("newEmail").style.border= "1px solid #ccc";
	document.getElementById("regPass").style.border= "1px solid #ccc";
	document.getElementById("regPassRepeat").style.border= "1px solid #ccc";
	$(".registrazioneEffettuata").css("border", "1px solid #ccc");
}

/*
    Interfaccia con il backend
*/

function getCookie(cname) {
	var name = cname + "=";
	var decodedCookie = decodeURIComponent(document.cookie);
	var ca = decodedCookie.split(';');
	for(var i = 0; i <ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}

$(document).ready(() => {
	if(document.cookie != null) {
		var nomeUtente = getCookie('userName');
		var password = getCookie('password');
		var passwordDecriptata = atob(password);
		var isChecked = true;

		$.ajax({
			url: "LoginServlet",
			method: 'POST',
			data: {
				NomeUtente: nomeUtente,
				Password: passwordDecriptata,
				Ricordami : isChecked
			}
		}).done(data => {
			const response = JSON.parse(data);
			if(response.ok){
				console.log("open");
				if(!window.location.hash) {
					window.location = window.location + '#loaded';
					window.location.reload();
				}
			}
		})
	}

    //Seleziono le due form: login e registrazione con jquery
    const formLogin = $("[name='login']");
    const formSignUP = $("[name='sign-up']");
    const passForgot = $("[name = 'passRecovery']");

    //Chiama l'evento onLoginSubmit o onSignUpSubmit quando viene fatto il submit della form
    formLogin.on('submit', onLoginSubmit);
    formSignUP.on('submit', onSignUpSubmit);
    passForgot.on('submit', recoveryPassword);
    
})

const onLoginSubmit = event => {
    //Evita di fare il submit
    event.preventDefault();
    
    let isChecked;
    if (document.getElementById('remember').checked) {
        isChecked = true;
    } else {
        isChecked = false;
    }
    
  //Selezioniamo gli elementi del login
    let userInput = document.getElementById("myName");
    let userPassword = document.getElementById("myPass");
 
    $.ajax({
        url: "LoginServlet",
        method: 'POST',
        data: {
            NomeUtente: userInput.value,
            Password: userPassword.value,
            Ricordami : isChecked
        }
    }).done(data => {
        const response = JSON.parse(data);

        if(response.ok == true){
        	window.location.reload();
        }else {
        	const messageError = $("#messageError");
        	messageError.text(response.message);
        	messageError.css("opacity", "1");
            userInput.style.border = "1px solid red";
            userPassword.style.border = "1px solid red";
            userInput.value = null;
            userPassword.value = null;       
        }         
    })
}

const onSignUpSubmit = event => {
	 event.preventDefault();
 
	 let userInput = document.getElementById("newName");
	 let userEmail = document.getElementById("newEmail");
	 let userPassword = document.getElementById("regPass");
	 let userConfPassword = document.getElementById("regPassRepeat");
	 let formRegEff = document.getElementsByClassName("registrazioneEffettuata");
	 
	 $.ajax({
	       url: "RegistrazioneServlet",
	       method: 'POST',
	       data: {
	           NomeUtente: userInput.value,
	           Email : userEmail.value,
	           Password: userPassword.value,
	           ConfermaPassword: userConfPassword.value        
	       }
	 }).done(data => {
	       const response = JSON.parse(data);      
	       if(response.ok == true){
	    	   window.location.reload();
	       }else {
		        const messageError = $("#signUpMessageError");
		        messageError.text(response.message);
		        messageError.css("opacity", "1");
		        userPassword.style.border = "1px solid red";
		        userInput.style.border = "1px solid red";
		        userEmail.style.border = "1px solid red";
		        userConfPassword.style.border = "1px solid red";
		        userPassword.value = null;
		        userConfPassword.value = null;
		        userNome.value=null;
		        userCognome.value=null
		        }         
	 })
}

/*Modificare form nel momento in cui si invia la mail*/
const recoveryPassword = event => {
	    event.preventDefault();
	    const form = $(event.target);
	    const button = form.find(".bottoneDefault");
	    const originalColor = button.css("background-color");
	    
	    button.attr("disabled", true);
	    button.css("background-color", "#829683");
	    
	    let userEmail = document.getElementById("emailF");

	    $.ajax({
	    	url: "RecuperoPasswordServlet",
	    	method: 'POST',
	    	data: {
	    		Email: userEmail.value
	    	}
	    }).done(data => {
		    const response = JSON.parse(data);
		    const messageError = $(".recuperoPassword #signUpMessageError");
		    
	    	 if(response.ok == true){
	    		messageError.css("opacity", "0");
	    		document.getElementById("recovery").innerHTML = "La tua nuova password ti &egrave stata inviata per email!";
	    		document.getElementById("formRecovery").style.display="none";
	    		$("#recovery").css("text-align", "center");
	  	   	}else {
		        messageError.text(response.message);
		        messageError.css("opacity", "1");
		        userEmail.style.border = "1px solid red";
		        userEmail.value = null;
	  	   	}     
	    }).always(()=>{
	    	button.attr("disabled", false);
	    	button.css("background-color", originalColor);
	    })
	}

function redirectUserArea(){
	window.location = "AreaUtente.jsp";
}