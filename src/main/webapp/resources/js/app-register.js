document.addEventListener("DOMContentLoaded", function() {
	let passInput = document.querySelector("[name='password']");
	let pass2Input = document.querySelector("[name='password2']");
	let subBtn = document.querySelector("[type='submit']");
	let paragraphElement = pass2Input.nextElementSibling;
	let form = document.querySelector("[name='registration']");
	let inputs = document.querySelectorAll("[name='registration'] input");
	
	
	subBtn.addEventListener("click", function (e){
		e.preventDefault();
		let allValuesCorrect = true;
		const regexToCheckEmpty = /^\s*$/;
		const regexToCheck = /[A-Za-z]+/gm;
		inputs.forEach(input => {
			if(input.name === 'name') {
				
				if(regexToCheckEmpty.test(input.value)) {
					input.nextElementSibling.innerText = "Nie może być puste";
					allValuesCorrect = false;
				} else if (!regexToCheck.test(input.value)) {
					input.nextElementSibling.innerText = "Niepoprawna wartość";
					allValuesCorrect = false;
				} else {
					input.nextElementSibling.innerText = "";
				}
			} else if (input.name === 'surname') {
				if(regexToCheckEmpty.test(input.value)) {
					input.nextElementSibling.innerText = "Nie może być puste";
					allValuesCorrect = false;
				} else if (!regexToCheck.test(input.value)) {
					input.nextElementSibling.innerText = "Niepoprawna wartość";
					allValuesCorrect = false;
				} else {
					input.nextElementSibling.innerText = "";
				}
			} else if (input.name === 'email') {
				const regexToCheckEmail = /[^0-9-_\.](\S)*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.([a-zA-Z]{2,}){1}/gm;
				if(regexToCheckEmpty.test(input.value)) {
					input.nextElementSibling.innerText = "Nie może być puste";
					allValuesCorrect = false;
				} else if (!regexToCheckEmail.test(input.value)) {
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
		});
		
		if(passInput.value !== pass2Input.value) {
			paragraphElement.innerText = "Hasło nie jest identyczne";
			return false;
		} else if (allValuesCorrect) {
			paragraphElement.innerText = "";
			form.submit();
		}
	});
})