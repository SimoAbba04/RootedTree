function showSuggestions(str) {
    const suggestionsBox = document.getElementById("suggestions-box");
    
    if (str.length < 2) { // Mostra suggerimenti solo dopo 2 caratteri
        suggestionsBox.innerHTML = "";
        suggestionsBox.style.display = "none";
        return;
    }
    
    const xhr = new XMLHttpRequest();
    
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            const suggestions = JSON.parse(this.responseText);
            let html = "";
            
            if (suggestions.length > 0) {
                for (let i = 0; i < suggestions.length; i++) {
                    html += '<a href="SearchServlet?searchQuery=' + suggestions[i] + '">' + suggestions[i] + '</a>';
                }
                suggestionsBox.innerHTML = html;
                suggestionsBox.style.display = "block";
            } else {
                suggestionsBox.innerHTML = "";
                suggestionsBox.style.display = "none";
            }
        }
    };
    
    xhr.open("GET", "SearchSuggestServlet?query=" + str, true);
    xhr.send();
}

// Chiudi i suggerimenti se l'utente clicca altrove
//document.addEventListener("click", function (e) {
 //   const suggestionsBox = document.getElementById("suggestions-box");
  //  const searchInput = document.getElementById("search-input");
  //  if (e.target !== searchInput) {
   //     suggestionsBox.style.display = "none";
    //}
//}
//);