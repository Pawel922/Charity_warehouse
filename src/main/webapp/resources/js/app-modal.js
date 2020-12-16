document.addEventListener("DOMContentLoaded", function() {

	// Get the modal
	let modal = document.getElementById("myModal");
	// Get the remove buttons that opens the modal
	let deleteBtns = document.querySelectorAll("[data-name='removeBtn']");
	// Get change button that open the modal
	let changeBtn = document.querySelector("[data-name='changeBtn']");
	// Get the <span> element that closes the modal
	let span = document.getElementsByClassName("close")[0];
	
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
})