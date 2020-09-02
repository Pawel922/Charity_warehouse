document.addEventListener("DOMContentLoaded", function() {
	let passInput = document.querySelector("[name='password']");
	let pass2Input = document.querySelector("[name='password2']");
	let subBtn = document.querySelector("[type='submit']");
	let paragraphElement = pass2Input.nextElementSibling;
	let form = document.querySelector("form");
	
	
	subBtn.addEventListener("click", function (e){
		e.preventDefault();
		if(passInput.value !== pass2Input.value) {
			paragraphElement.innerText = "Hasło nie jest identyczne";
			return false;
		} else {
			paragraphElement.innerText = "";
			form.submit();
		}
	})
})