const nameOrLastnameErrorMessage = "Questo campo deve iniziare con una lettera maiuscola e non deve contenere numeri."
const emailErrorMessage = "Questo campo deve essere nella seguente forma: username@dominio.ext"
const passwordErrorMessage = "Questo campo deve contenere una lettera maiuscola, una minuscola e un carattere speciale tra i seguenti : !,@,#,$,%,^,&,* . "
const emptyFieldErrorMessage = "Questo campo non può essere vuoto."

function validaElem(currentElement, errorElement, errorMessage) {
	if (currentElement.checkValidity()) {
		errorElement.innerText = "";
		errorElement.classList.remove("visible");
		return true;
	}
	
	errorElement.classList.add("visible");
	if (currentElement.validity.valueMissing) {
		errorElement.innerText = emptyFieldErrorMessage;
	} else {
		errorElement.innerText = errorMessage;
	}
	return false;
}

/**
 * Valida il form di login prima dell'invio.
 */
function validateLogin() {
	let valid = true;	
	
	let emailInput = document.getElementById("Femail");
	let errorMail = document.getElementById("errorEmail");
	if (!validaElem(emailInput, errorMail, emailErrorMessage)) {
		valid = false;
	}
	
	let pwInput = document.getElementById("FPassword");
	let errorPw = document.getElementById("errorPw");
	if (!validaElem(pwInput, errorPw, passwordErrorMessage)) {
	    valid = false;
	}
	
	return valid;
}

/**
 * Valida il form di registrazione prima dell'invio.
 */
function validateRegistration() {
	let valid = true;	
	
	let nameInput = document.getElementById("Fnome");
	let errorName = document.getElementById("errorName");
	if(!validaElem(nameInput, errorName, nameOrLastnameErrorMessage)) {
		valid = false;
	} 
	
	let surnameInput = document.getElementById("Fcognome");
	let errorSur = document.getElementById("errorSur");
	if (!validaElem(surnameInput, errorSur, nameOrLastnameErrorMessage)) {
		valid = false;
	}
	
	let emailInput = document.getElementById("Femail");
	let errorMail = document.getElementById("errorEmail");
	if (!validaElem(emailInput, errorMail, emailErrorMessage)) {
		valid = false;
	}
	
	let pwInput = document.getElementById("FPassword");
	let errorPw = document.getElementById("errorPw");
	if (!validaElem(pwInput, errorPw, passwordErrorMessage)) {
	    valid = false;
	}
	
	
	let dateInput = document.getElementById("FdataNascita");
	let errorDate = document.getElementById("errorDate");
	if (!validateAge(dateInput, errorDate)) {
		valid = false;
	}

	return valid;
}

/**
 * Funzione specifica per validare che l'utente sia maggiorenne.
 */
function validateAge(dateElement, errorElement) {
    if (dateElement.value === "") {
        errorElement.innerText = emptyFieldErrorMessage;
        errorElement.classList.add("visible");
        return false;
    }
    
    const birthDate = new Date(dateElement.value);
    const today = new Date();
    let age = today.getFullYear() - birthDate.getFullYear();
    const m = today.getMonth() - birthDate.getMonth();
    
    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }
    
    if (age >= 18) {
        errorElement.innerText = "";
        errorElement.classList.remove("visible");
        return true;
    } else {
        errorElement.innerText = dateErrorMessage;
        errorElement.classList.add("visible");
        return false;
    }
}
