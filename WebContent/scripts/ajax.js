function showSuggestions(str, contextPath) {
    const suggestionsBox = document.getElementById("suggestions-box");
    
    if (str.length < 2) {
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
                    const product = suggestions[i];
                    
                    // la riga corretta che "spacchetta" l'oggetto
                    html += '<a href="' + contextPath + '/ProductDetailServlet?id=' + product.id + '">' + product.name + '</a>';
                }
                suggestionsBox.innerHTML = html;
                suggestionsBox.style.display = "block";
            } else {
                suggestionsBox.innerHTML = "";
                suggestionsBox.style.display = "none";
            }
        }
    };
    
    xhr.open("GET", contextPath + "/SearchSuggestServlet?query=" + str, true);
    xhr.send();
}

document.addEventListener("click", function (e) {
    const suggestionsBox = document.getElementById("suggestions-box");
    const searchInput = document.getElementById("search-input");
    if (e.target !== searchInput) {
        suggestionsBox.style.display = "none";
    }
});
