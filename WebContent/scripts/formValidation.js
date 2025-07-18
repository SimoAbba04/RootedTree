const nameOrLastnameErrorMessage = "Deve iniziare con una maiuscola e contenere solo lettere.";
const emailErrorMessage = "L'email non è in un formato valido (es. nome@dominio.it).";
const passwordErrorMessage = "Minimo 8 caratteri, con maiuscola, minuscola, numero e simbolo speciale (!@#$%^&*).";
const emptyFieldErrorMessage = "Questo campo non può essere vuoto.";
const dateErrorMessage = "Devi essere maggiorenne per registrarti.";
const stateErrorMessage = "Il nome dello stato non è valido.";
const streetErrorMessage = "L'indirizzo contiene caratteri non validi.";
const cityErrorMessage = "Il nome della città non è valido.";
const provinceErrorMessage = "La provincia deve contenere 2 lettere maiuscole (es. SA).";
const capErrorMessage = "Il CAP deve contenere 5 cifre numeriche.";
const phoneErrorMessage = "Il numero di telefono deve contenere da 9 a 15 cifre.";
const cardHolderErrorMessage = "Il nome del titolare non è valido.";
const cardNumberErrorMessage = "Il numero della carta deve contenere 16 cifre.";
const expiryDateErrorMessage = "La data di scadenza deve essere nel formato MM/AA.";
const expiredCardErrorMessage = "La carta di pagamento è scaduta.";
const cvvErrorMessage = "Il CVV deve contenere 3 cifre.";

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


function validateRegistration() {
	let valid = true;

	let nameInput = document.getElementById("Fnome");
	let errorName = document.getElementById("errorName");
	if (!validaElem(nameInput, errorName, nameOrLastnameErrorMessage)) {
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
	if (!validateAge(dateInput, errorDate, dateErrorMessage)) {
		valid = false;
	}

	return valid;
}


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


function validateExpiryDateField(inputElement) {
    const errorElement = inputElement.nextElementSibling;
    const today = new Date();
    const inputDate = new Date(inputElement.value);

    // Imposta le ore a zero per confrontare solo le date
    today.setHours(0, 0, 0, 0);

    if (inputElement.value === "") {
        errorElement.innerText = emptyFieldErrorMessage;
        errorElement.classList.add("visible");
        return false;
    }

    if (inputDate < today) {
        errorElement.innerText = expiryDateErrorMessage;
        errorElement.classList.add("visible");
        return false;
    }

    errorElement.innerText = "";
    errorElement.classList.remove("visible");
    return true;
}




function validateProfileForm() {
	let isValid = true;
	if (!validaElem(document.getElementById('Fnome'), document.getElementById('Fnome').nextElementSibling, nameOrLastnameErrorMessage)) isValid = false;
	if (!validaElem(document.getElementById('Fcognome'), document.getElementById('Fcognome').nextElementSibling, nameOrLastnameErrorMessage)) isValid = false;
	if (!validaElem(document.getElementById('Femail'), document.getElementById('Femail').nextElementSibling, emailErrorMessage)) isValid = false;
	if (!validateAge(document.getElementById('FdataNascita'), document.getElementById('FdataNascita').nextElementSibling)) isValid = false;

	const newPass = document.getElementById('FnewPassword');
	if (newPass.value.trim() !== "") {
		const oldPass = document.getElementById('FoldPassword');
		if (oldPass.value.trim() === "") {
			oldPass.nextElementSibling.innerText = "Inserire la vecchia password per cambiarla.";
			oldPass.nextElementSibling.classList.add("visible");
			isValid = false;
		} else {
			oldPass.nextElementSibling.classList.remove("visible");
		}
		if (!validaElem(newPass, newPass.nextElementSibling, passwordErrorMessage)) isValid = false;
	}
	return isValid;
}

function validateAddressForm() {
	let isValid = true;
	if (!validaElem(document.getElementById('stato'), document.getElementById('stato').nextElementSibling, stateErrorMessage)) isValid = false;
	if (!validaElem(document.getElementById('via'), document.getElementById('via').nextElementSibling, streetErrorMessage)) isValid = false;
	if (!validaElem(document.getElementById('citta'), document.getElementById('citta').nextElementSibling, cityErrorMessage)) isValid = false;
	if (!validaElem(document.getElementById('provincia'), document.getElementById('provincia').nextElementSibling, provinceErrorMessage)) isValid = false;
	if (!validaElem(document.getElementById('cap'), document.getElementById('cap').nextElementSibling, capErrorMessage)) isValid = false;

	const phoneInput = document.getElementById('numeroTelefono');
	if (phoneInput.value.trim() !== "") {
		if (!validaElem(phoneInput, phoneInput.nextElementSibling, phoneErrorMessage)) isValid = false;
	} else {
		phoneInput.nextElementSibling.classList.remove("visible");
	}
	return isValid;
}

function validatePaymentForm() {
	let isValid = true;
	if (!validaElem(document.getElementById('titolare'), document.getElementById('titolare').nextElementSibling, cardHolderErrorMessage)) isValid = false;
	if (!validaElem(document.getElementById('numeroCarta'), document.getElementById('numeroCarta').nextElementSibling, cardNumberErrorMessage)) isValid = false;
	if (!validateExpiryDateField(document.getElementById('dataScadenza'))) isValid = false;
	if (!validaElem(document.getElementById('cvv'), document.getElementById('cvv').nextElementSibling, cvvErrorMessage)) isValid = false;
	return isValid;
}


