document.addEventListener("DOMContentLoaded", function() {
	let passInput = document.querySelector("[name='password']");
	let pass2Input = document.querySelector("[name='password2']");
	let subBtn = document.querySelector("[type='submit']");
	let form = document.querySelector("form");
	
	
	subBtn.addEventListener("click", function (event){
		event.preventDefault();
		if(passInput.value !== pass2Input.value) {
			console.log("Popraw hasło");
			return false;
		} else {
			form.submit();
		}
	})
})