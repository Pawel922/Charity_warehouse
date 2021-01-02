document.addEventListener("DOMContentLoaded", function() {

	// Get the modal
	let modal = document.getElementById("myModal");
	// Get the button that opens the modal
	let typeBtn = document.querySelector("[data-name='typeBtn']");
	// Get the <span> element that closes the modal
	let span = document.getElementsByClassName("close")[0];
	//Get value of pick up date
	let pickUpDateStr = document.querySelectorAll("table td")[2].innerText.split(", ")[0];
	
	//Get submit button from form
	let submitBtn = document.querySelectorAll(".modal-body input")[2]
	
	// When the user clicks on the button, open the modal to type receive date
	typeBtn.addEventListener("click", function(e){
		e.preventDefault();
		modal.style.display = "block";
	})
	
	// When the user clicks on <span> (x), close the modal
	span.addEventListener("click", function(){
		modal.style.display = "none";
	})
	
	//When the user click submit button check if receive date is correct
	submitBtn.addEventListener("click", function(e){
		e.preventDefault();
		var paragraph = document.querySelector(".modal-body p")
		
		//Get value of receive date
		var receiveDateStr = document.querySelectorAll(".modal-body input")[0].value;
		
		var pickUpDate = Date.parse(pickUpDateStr);
		var receiveDate = Date.parse(receiveDateStr);
		
		if(receiveDateStr === "") {
			paragraph.innerHTML = "Wprowadz date";
		} else if (receiveDate < pickUpDate) {
			paragraph.innerHTML = "Zła data";
		} else {
			paragraph.innerHTML = "Data poprawna";
		}
	})

})