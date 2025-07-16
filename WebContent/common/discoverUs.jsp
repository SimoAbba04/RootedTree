<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi Siamo - RootedTree</title>
    <link rel="stylesheet" href="./styles/index.css">
    <link rel="stylesheet" href="./styles/navbar.css">
    <link rel="stylesheet" href="./styles/footer.css">
    <link rel="stylesheet" href="./styles/discover-us.css"> 
</head>
<body>
    <%@ include file="navbar.jsp" %>

    <main class="main-content">
        <div class="about-container">
            <h1 class="page-title">Le Radici della Nostra Passione</h1>

            <!-- Sezione 1: Immagine a Sinistra, Testo a Destra -->
            <section class="about-section">
                <div class="about-image">
                    <img src="https://placehold.co/600x400/A3B18A/344E41?text=Mani+che+curano+un+bonsai" alt="Mani esperte che curano un bonsai">
                </div>
                <div class="about-text">
                    <h2>Un'Arte che Cresce con Pazienza</h2>
                    <p>
                        RootedTree nasce non come un semplice negozio, ma come un santuario dedicato all'antica arte del bonsai. La nostra storia è iniziata con un singolo, piccolo albero e una grande fascinazione per l'equilibrio tra natura e intervento umano. Crediamo che ogni bonsai sia un'opera d'arte vivente, una miniatura di paesaggi maestosi che richiede dedizione, cura e una profonda connessione con il mondo naturale.
                    </p>
                    <p>
                        La nostra missione è condividere questa passione, offrendo non solo piante, ma anche la conoscenza e gli strumenti per coltivarle. Ogni albero nel nostro vivaio è un testimone di pazienza e amore.
                    </p>
                </div>
            </section>

            <!-- Sezione 2: Testo a Sinistra, Immagine a Destra -->
            <section class="about-section reverse">
                <div class="about-image">
                    <img src="https://placehold.co/600x400/588157/FFFFFF?text=Serra+con+molti+bonsai" alt="Serra luminosa con una vasta selezione di bonsai">
                </div>
                <div class="about-text">
                    <h2>Qualità e Comunità al Vostro Servizio</h2>
                    <p>
                        Selezioniamo personalmente ogni bonsai, vaso e strumento per garantire la massima qualità. Collaboriamo con coltivatori esperti e artigiani per portare a casa vostra solo il meglio. Che siate neofiti curiosi o bonsaisti esperti, il nostro team è qui per guidarvi in ogni fase del vostro percorso.
                    </p>
                    <p>
                        In RootedTree, non vendiamo solo prodotti: costruiamo una comunità. Organizziamo workshop, forniamo guide dettagliate e offriamo un'assistenza post-vendita che vi accompagnerà nella crescita del vostro piccolo, grande albero. Benvenuti nella nostra famiglia.
                    </p>
                </div>
            </section>

        </div>
    </main>

    <%@ include file="footer.jsp" %>
</body>
</html>