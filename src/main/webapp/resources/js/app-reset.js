document.addEventListener("DOMContentLoaded", function() {

	// Get the form
	let form = document.querySelector("#resetPass")
	//Get input with new password
	let newPass = document.querySelectorAll("#resetPass input")[0]
	//Get input with repeated password
	let repPass = document.querySelectorAll("#resetPass input")[1]
	//Get submit button from form
	let submitBtn = document.querySelectorAll("#resetPass input")[2]
	//Get all paragraps with warnings
	let warnings = document.querySelectorAll("#resetPass p")
	
	//When the user click submit button check if given passwords are the same
	submitBtn.addEventListener("click", function(e){
		e.preventDefault();
		clearWarnings();
		const regexToCheckEmpty = /^\s*$/;
		const regexToCheckPassword = /[\S]{5,10}/;
		if(regexToCheckEmpty.test(newPass.value)) {
			newPass.nextElementSibling.innerHTML = "Hasło nie może być puste"
		} else if (!regexToCheckPassword.test(newPass.value)) {
			newPass.nextElementSibling.innerHTML = "Hasło musi zawierać od 5 do 10 znaków";
		} else if (newPass.value !== repPass.value){
			repPass.nextElementSibling.innerHTML = "Hasło źle powtórzone";
		}
	})
	
	//clear all warnings
	function clearWarnings(){
		warnings.forEach(warning => {
			warning.innerHTML="";
		})
	}
	

})