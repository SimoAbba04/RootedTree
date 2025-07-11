document.addEventListener("DOMContentLoaded", function() {

    const searchBar = document.querySelector(".search-bar");
    const searchButton = searchBar.querySelector("button");
    const searchInput = searchBar.querySelector("input[type='text']");

    // Apre la barra di ricerca al click sul bottone
    searchButton.addEventListener("click", function(event) {
        // Se la barra non è attiva, aprila e impedisci l'invio del form
        if (!searchBar.classList.contains("active")) {
            event.preventDefault(); // Previene l'invio del form al primo click
            searchBar.classList.add("active");
            searchInput.focus(); // Mette il focus sull'input
        }
        // Se l'input è vuoto quando si clicca, non invia il form
        else if (searchInput.value.trim() === "") {
             event.preventDefault();
             searchBar.classList.remove("active");
        }
        // Altrimenti, il form verrà inviato normalmente
    });

    // Chiude la barra di ricerca se si clicca fuori
    document.addEventListener("click", function(event) {
        if (!searchBar.contains(event.target)) {
            searchBar.classList.remove("active");
        }
    });
});