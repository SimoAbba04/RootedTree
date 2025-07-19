document.addEventListener("DOMContentLoaded", function() {

    const searchBar = document.querySelector(".search-bar");
    const searchButton = searchBar.querySelector("button");
    const searchInput = searchBar.querySelector("input[type='text']");

    // Apre la barra di ricerca al click sul bottone
    searchButton.addEventListener("click", function(event) {
        if (!searchBar.classList.contains("active")) {
            event.preventDefault(); // Previene l'invio del form al primo click
            searchBar.classList.add("active");
            searchInput.focus();
        }
        
        else if (searchInput.value.trim() === "") {
             event.preventDefault();
             searchBar.classList.remove("active");
        }
        
    });

    // Chiude la barra di ricerca se si clicca fuori
    document.addEventListener("click", function(event) {
        if (!searchBar.contains(event.target)) {
            searchBar.classList.remove("active");
        }
    });
});



function openNav() {
  document.getElementById("mobileNav").style.width = "100%";
}

function closeNav() {
  document.getElementById("mobileNav").style.width = "0%";
}