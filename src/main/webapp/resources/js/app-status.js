document.addEventListener("DOMContentLoaded", function() {
	let disableBtn = document.querySelector("[data-status='disable']");
	let enableBtn = document.querySelector("[data-status='enable']");
	
	disableBtn.addEventListener("click", function(evt){
		const xhr = new XMLHttpRequest();

		xhr.addEventListener("load", e => {
    	if (xhr.status === 200) {
        	console.log("Wynik połączenia:");
        	console.log(xhr.response);
    		}
		});

		xhr.addEventListener("error", e => {
    		alert("Niestety nie udało się nawiązać połączenia");
		});

		xhr.open("GET", "http://localhost:8080/user/disable/1", true);
		xhr.send();
	})
	
	enableBtn.addEventListener("click", function(evt){
		const xhr = new XMLHttpRequest();

		xhr.addEventListener("load", e => {
    	if (xhr.status === 200) {
        	console.log("Wynik połączenia:");
        	console.log(xhr.response);
    		}
		});

		xhr.addEventListener("error", e => {
    		alert("Niestety nie udało się nawiązać połączenia");
		});

		xhr.open("GET", "http://localhost:8080/user/enable/1", true);
		xhr.send();
	})
})