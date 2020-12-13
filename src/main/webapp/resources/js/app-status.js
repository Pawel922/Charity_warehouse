document.addEventListener("DOMContentLoaded", function() {
	let disableBtns = document.querySelectorAll("[data-status='disable']");
	let enableBtns = document.querySelectorAll("[data-status='enable']");
	
	// Get the modal
	let modal = document.getElementById("confirmModal");
	// Get the buttons that opens the modal
	let deleteBtns = document.querySelectorAll("[data-name='removeBtn']");
	// Get the <span> element that closes the modal
	let span = document.getElementsByClassName("close")[0];
	
	const urlServer = "http://localhost:8080";
	
	// When the user clicks on the button, change user status from enable to disable
	disableBtns.forEach(btn => {
		btn.addEventListener("click", function(){	
			changeStatus(this.dataset.href,this, "red","Odblokuj");
		})
	})
	
	// When the user clicks on the button, change user status from disable to enable
	enableBtns.forEach(btn => {
		btn.addEventListener("click", function(){
			changeStatus(this.dataset.href,this, "green","Blokuj");
		})
	})
	
	// When the user clicks on the button, open the modal to confirm action
	deleteBtns.forEach(btn => {
		btn.addEventListener("click", function(){
			//set link attribute to correct address
			modal.querySelector("[data-name='Yes']").href = this.dataset.href;
			modal.style.display = "block";
		})
	})
	
	// When the user clicks on <span> (x), close the modal
	span.addEventListener("click", function(){
			modal.style.display = "none";
	})
  
	
	function changeStatus(href, elem, color, text) {
		const xhr = new XMLHttpRequest();
			xhr.open("GET", urlServer + href, true);
			xhr.send();
			xhr.addEventListener("load", e => {
    		if (xhr.status === 200) {
				elem.style.background = color;
				elem.innerText = text;
				window.location.reload();
    			}
			});
	}
})