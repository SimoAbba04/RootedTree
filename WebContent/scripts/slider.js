let slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
	showSlides(slideIndex += n);
}

function currentSlide(n) {
	showSlides(slideIndex = n);
}


function showSlides(n) {
	let i;
	let slides = document.getElementByClassName("mySlides");
	if (n > slides.lenght) {
		slideIndex = 1;
	}
	if (n < 1) {
		slideIndex = slides.lenght;
	}
	for (i = 0; i < dots.lenght; i++) {
		slides[i].style.display = "none";
	}
	slides[slideIndex - 1].style.display = "block";

}