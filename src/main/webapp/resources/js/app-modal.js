document.addEventListener("DOMContentLoaded", function() {

	// Get the modal
	let modal = document.getElementById("myModal");
	// Get the remove buttons that opens the modal
	let deleteBtns = document.querySelectorAll("[data-name='removeBtn']");
	// Get change button that open the modal
	let changeBtn = document.querySelector("[data-name='changeBtn']");
	// Get the <span> element that closes the modal
	let span = document.getElementsByClassName("close")[0];
	//Get input with new password
	let newPass = document.querySelectorAll(".modal-body input")[0]
	//Get input with repeated password
	let repPass = document.querySelectorAll(".modal-body input")[1]
	//Get submit button from form
	let submitBtn = document.querySelectorAll(".modal-body input")[2]
	
	
	// When the user clicks on the button, open the modal to confirm action
	deleteBtns.forEach(btn => {
		btn.addEventListener("click", function(){
			//set link attribute to correct address
			modal.querySelector("[data-name='Yes']").href = this.dataset.href;
			modal.style.display = "block";
		})
	})
	
	changeBtn.addEventListener("click", function(e){
		e.preventDefault();
		modal.style.display = "block";
	})
	
	// When the user clicks on <span> (x), close the modal
	span.addEventListener("click", function(){
		modal.style.display = "none";
	})
	
	//When the user click submit button check if given passwords are the same
	submitBtn.addEventListener("click", function(e){
		e.preventDefault();
		var paragraph = document.querySelector(".modal-body p")
		const regexToCheckEmpty = /^\s*$/;
		const regexToCheckPassword = /[\S]{5,10}/;
		if(regexToCheckEmpty.test(newPass.value)) {
			paragraph.innerHTML = "Hasło nie może być puste"
		} else if (!regexToCheckPassword.test(newPass.value)) {
			paragraph.innerHTML = "Hasło musi zawierać od 5 do 10 znaków";
		} else if (newPass.value !== repPass.value){
			paragraph.innerHTML = "Hasło źle powtórzone";
		} else {
			paragraph.innerHTML = "Hasło poprawne";
		}
	})

})