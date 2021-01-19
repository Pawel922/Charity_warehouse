document.addEventListener("DOMContentLoaded", function() {
	
	//Get the submit button
	let subBtn = document.querySelector(".form--contact button");
	//Get all input from the form
	let inputs = document.querySelectorAll(".form--contact input")
	//Get text area
	let textArea = document.querySelector(".form--contact textarea");
	
	subBtn.addEventListener("click", function(e) {
		e.preventDefault();
		let allValuesCorrect = true;
		const regexToCheckEmpty = /^\s*$/;
		inputs.forEach(input => {
			if(regexToCheckEmpty.test(input.value)) {
				input.nextElementSibling.innerText = "Nie może być puste"
			}
		});
	})
	
	

})