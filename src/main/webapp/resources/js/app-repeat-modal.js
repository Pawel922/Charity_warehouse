document.addEventListener("DOMContentLoaded", function() {

	// Get the modal
	let modal = document.getElementById("myModal");
	// Get repeat button that open the modal
	let repeatBtn = document.querySelector("[data-name='repeatBtn']");
	// Get the <span> element that closes the modal
	let span = document.getElementsByClassName("close")[0];
	//Get input with email
	let emailInput = document.querySelectorAll(".modal-body input");
	
	
	// When the user clicks on the button, open the modal to repeat password
	repeatBtn.addEventListener("click", function(e){
		e.preventDefault();
		modal.style.display = "block";
	})
	
	// When the user clicks on <span> (x), close the modal
	span.addEventListener("click", function(){
		modal.style.display = "none";
	})
})