document.addEventListener("DOMContentLoaded", function() {
	let form = document.querySelector("[name='login']");
	let subBtn = document.querySelector("[name='login'] [type='submit']");
	let inputs = document.querySelectorAll("[name='login'] input");
	let emailInput = document.querySelector("input[name='email']");
	
	const urlServer = "http://localhost:8080";
	
	subBtn.addEventListener("click", function(e) {
		e.preventDefault();
		let allValuesCorrect = true;
		const regexToCheckEmpty = /^\s*$/;
		inputs.forEach(input => {
			if(input.name === 'email') {
				const regexToCheckEmail = /[^0-9-_\.](\S)*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.([a-zA-Z]{2,}){1}/;
				if(regexToCheckEmpty.test(input.value)) {
					input.nextElementSibling.innerText = "Nie może być puste";
					allValuesCorrect = false;
				} else if(!regexToCheckEmail.test(input.value)) {
					input.nextElementSibling.innerText = "Niewłaściwy adres email";
					allValuesCorrect = false;
				} else {
					input.nextElementSibling.innerText = "";
				}
			} else if (input.name === 'password') {
				if(regexToCheckEmpty.test(input.value)) {
					input.nextElementSibling.innerText = "Nie może być puste";
					allValuesCorrect = false;
				} else {
					input.nextElementSibling.innerText = "";
				} 
			}
			
		})
		
		if(allValuesCorrect) {
			checkStatus(emailInput.value)
			//form.submit();
		}
	})
	
	function checkStatus(email) {
		const xhr = new XMLHttpRequest();
			xhr.open("GET", urlServer + "/user/check/" + email, true);
			xhr.send();
			xhr.addEventListener("load", e => {
    		if (xhr.status === 200) {
				console.log("Status OK");
    		} else if (xhr.status === 404) {
				console.log("User not found");
			}
		});
	}
})