const nameOrLastnameErrorMessage = "Questo campo deve iniziare con una lettera maiuscola e non deve contenere numeri."
const emailErrorMessage = "Questo campo deve essere nella seguente forma: username@dominio.ext"
const passwordErrorMessage = "Questo campo deve contenere una lettera maiuscola, una minuscola e un carattere speciale tra i seguenti : !,@,#,$,%,^,&,* . "
const emptyFieldErrorMessage = "Questo campo non può essere vuoto."

function validaElem(currentElement, errorElement, errorMessage) {
	if (currentElement.checkValidity()) {
		errorElement.innerText="";
		errorElement.classList.remove("visible");
		return true;
	}
	errorElement.classList.add("visible");
	if(currentElement.validity.valueMissing){
		errorElement.innerText = emptyFieldErrorMessage;
	}else{
		errorElement.innerText = errorMessage;
	}
	return false;
}

function validateLogin() {
	let valid = true;	
	let form = document.getElementById("formAccesso");
	
	let errorMail = document.getElementById("errorEmail");
	if (!validaElem(form.email, errorMail, emailErrorMessage)){
		valid = false;
	}
	let errorPw = document.getElementById("errorPw");
	if (!validaElem(form.password, errorPw, passwordErrorMessage)) {
	       valid = false;
	   }
	return valid;
}

function validateRegistration() {
	let valid = true;	
	let form = document.getElementById("formRegistrazione");
	
	let divName = document.getElementById("errorName");
	if(!validaElem(form.nome, divName, nameOrLastnameErrorMessage)){
		valid = false;
	} 
	let divLastName = document.getElementById("errorSur");
	if (!validaElem(form.cognome, divLastName, nameOrLastnameErrorMessage)){
		valid = false;
	}
	let errorMail = document.getElementById("errorEmail");
	if (!validaElem(form.email, errorMail, emailErrorMessage)){
		valid = false;
	}
	let errorPw = document.getElementById("errorPw");
	if (!validaElem(form.password, errorPw, passwordErrorMessage)) {
	       valid = false;
	   }
	   //da validare la data
	return valid;
}