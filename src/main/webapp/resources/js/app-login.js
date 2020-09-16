document.addEventListener("DOMContentLoaded", function() {
	let form = document.querySelector("[name='login']");
	let subBtn = document.querySelector("[name='login'] [type='submit']");
	let inputs = document.querySelectorAll("[name='login'] input");
	
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
			form.submit();
		}
	} )
})