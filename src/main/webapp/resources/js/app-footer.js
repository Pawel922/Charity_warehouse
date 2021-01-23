document.addEventListener("DOMContentLoaded", function() {
	
	//Get the submit button
	let subBtn = document.querySelector(".form--contact button");
	//Get all input from the form
	let inputs = document.querySelectorAll(".form--contact input")
	//Get text area
	let textArea = document.querySelector(".form--contact textarea");
	//Get form
	let form = document.querySelector(".form--contact");
	
	subBtn.addEventListener("click", function(e) {
		e.preventDefault();
		let allValuesCorrect = true;
		const regexToCheckEmpty = /^\s*$/;
		const regexToCheck = /^[A-Za-z]+$/;
		inputs.forEach(input => {
			if(regexToCheckEmpty.test(input.value)) {
				input.nextElementSibling.innerText = "Nie może być puste";
				allValuesCorrect = false;
			} else if (!regexToCheck.test(input.value)) {
				input.nextElementSibling.innerText = "Niepoprawna wartość";
				allValuesCorrect = false;
			} else {
				input.nextElementSibling.innerText = "";
			}
		});
		
		if(regexToCheckEmpty.test(textArea.value)) {
			textArea.nextElementSibling.innerText = "Pusta wiadomość";
			allValuesCorrect = false;
		} else {
			textArea.nextElementSibling.innerText = "";
		}
		
		if(allValuesCorrect) {
			form.submit();
		};
	})
})