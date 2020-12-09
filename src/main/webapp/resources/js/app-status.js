document.addEventListener("DOMContentLoaded", function() {
	let disableBtns = document.querySelectorAll("[data-status='disable']");
	let enableBtns = document.querySelectorAll("[data-status='enable']");
	const urlServer = "http://localhost:8080";
	
	disableBtns.forEach(btn => {
		btn.addEventListener("click", function(){	
			changeStatus(this.dataset.href,this, "red","Odblokuj");
		})
	})
	
	enableBtns.forEach(btn => {
		btn.addEventListener("click", function(){
			changeStatus(this.dataset.href,this, "green","Blokuj");
		})
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