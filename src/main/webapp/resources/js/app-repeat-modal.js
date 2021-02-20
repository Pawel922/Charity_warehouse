document.addEventListener("DOMContentLoaded", function() {

	// Get the modal
	let modal = document.getElementById("myModal");
	// Get repeat button that open the modal
	let repeatBtn = document.querySelector("[data-name='repeatBtn']");
	// Get the <span> element that closes the modal
	let span = document.getElementsByClassName("close")[0];
	//Get input with email
	let emailInput = document.querySelector(".modal-body input");
	//Get submit button
	let submitBtn = document.querySelector(".modal-body button")
	//Get paragraph with warnings
	let warning = document.querySelector(".modal-body .warning")
	//Get form from modal
	let form = document.querySelector(".formTable")
	
	const urlServer = "http://localhost:8080";
	
	
	// When the user clicks on the button, open the modal to repeat password
	repeatBtn.addEventListener("click", function(e){
		e.preventDefault();
		warning.innerText = "";
		modal.style.display = "block";
	})
	
	// When the user clicks on <span> (x), close the modal
	span.addEventListener("click", function(){
		modal.style.display = "none";
	})
	
	// When the user clicks on submit button, check value of email input
	submitBtn.addEventListener("click", function(e){
		e.preventDefault();
		
		const regexToCheckEmpty = /^\s*$/;
		const regexToCheckEmail = /[^0-9-_\.](\S)*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.([a-zA-Z]{2,}){1}/;
		let isValueCorrect = true;
		
		if(regexToCheckEmpty.test(emailInput.value)) {
			isValueCorrect = false;
			warning.innerText = "Nie może być puste";
		} else if (!regexToCheckEmail.test(emailInput.value)) {
			isValueCorrect = false;
			warning.innerText = "Nieprawidłowy email";
		}
		
		if(isValueCorrect) {
			checkEmail(emailInput.value);
		}
	})
	
	
	//function to check if email exist in database
	function checkEmail(email) {
		const xhr = new XMLHttpRequest();
			xhr.open("GET", urlServer + "/check/" + email, true);
			xhr.send();
			xhr.addEventListener("load", e => {
    		if (xhr.status === 200) {
				form.submit();
    		} else if (xhr.status === 404) {
				warning.innerText = "Użytkownik o podanym adresie e-mail nie istnieje";
			}
		});
	}
})