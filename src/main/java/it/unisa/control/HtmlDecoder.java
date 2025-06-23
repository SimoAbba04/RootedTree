package it.unisa.control;

public class HtmlDecoder {
	
	public static String encodeHtmlEntities(String input) {
        if (input == null) return null;

        return input.replace("&", "&amp;")
        			.replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&#39")
                    .replace(" ", "&nbsp;");
    }
}

