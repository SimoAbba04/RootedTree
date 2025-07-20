DROP DATABASE IF EXISTS ROOTED_TREE;
CREATE DATABASE ROOTED_TREE;
USE ROOTED_TREE;

-- Account con ruolo
CREATE TABLE Account (
    IdAccount INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(20) NOT NULL,
    Cognome VARCHAR(20) NOT NULL,
    Email VARCHAR(50) NOT NULL UNIQUE,
    Pw VARCHAR(255) NOT NULL,
    DataNascita DATE NOT NULL,
    Ruolo ENUM('utente', 'admin') NOT NULL DEFAULT 'utente'
);

-- Indirizzo (legato a un utente)
CREATE TABLE Indirizzo (
    IdIndirizzo INT AUTO_INCREMENT PRIMARY KEY,
    Stato VARCHAR(30) NOT NULL,
    Provincia VARCHAR(30) NOT NULL,
    Citta VARCHAR(30) NOT NULL,
    Via VARCHAR(30) NOT NULL,
    CAP CHAR(5) NOT NULL,
    Descrizione VARCHAR(100),
    NumeroTelefono VARCHAR(11),
    IdAccount INT NOT NULL,
    FOREIGN KEY (IdAccount) REFERENCES Account(IdAccount) ON DELETE CASCADE
);

-- Metodo di pagamento
CREATE TABLE MetodoPagamento (
    IdPagamento INT AUTO_INCREMENT PRIMARY KEY,
    NumeroCarta VARCHAR(16) NOT NULL,
    DataScadenza DATE NOT NULL,
    CodiceSicurezza CHAR(3) NOT NULL,
    Titolare VARCHAR(50),
    IdAccount INT NOT NULL,
    FOREIGN KEY (IdAccount) REFERENCES Account(IdAccount) ON DELETE CASCADE
);

CREATE TABLE Prodotto (
    IdProdotto INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(50) NOT NULL,
    Descrizione TEXT,
    Prezzo DECIMAL(10,2) NOT NULL,
    Stock INT NOT NULL,
    Categoria ENUM("bonsai","vaso","cura") NOT NULL,
    Immagine mediumblob DEFAULT NULL
);

CREATE TABLE Ordine (
    IdOrdine INT AUTO_INCREMENT PRIMARY KEY,
    IdAccount INT NOT NULL,
    DataOrdine DATETIME DEFAULT CURRENT_TIMESTAMP,
    Stato ENUM('lavorazione', 'spedito', 'consegnato') DEFAULT 'lavorazione',
    Totale DECIMAL(10,2),
    FOREIGN KEY (IdAccount) REFERENCES Account(IdAccount) ON DELETE CASCADE
);

CREATE TABLE DettaglioOrdine (
    IdDettaglio INT AUTO_INCREMENT PRIMARY KEY,
    IdOrdine INT NOT NULL,
    IdProdotto INT,
    Quantita INT NOT NULL,
    PrezzoUnitario DECIMAL(10,2) NOT NULL,
    NomeProdotto VARCHAR(50) NOT NULL,
    FOREIGN KEY (IdOrdine) REFERENCES Ordine(IdOrdine) ON DELETE CASCADE ,
    FOREIGN KEY (IdProdotto) REFERENCES Prodotto(IdProdotto) ON DELETE SET NULL
);

INSERT INTO Prodotto (Nome, Descrizione, Prezzo, Stock, Categoria, Immagine) VALUES
('Bonsai di Acero Giapponese Rosso', 'Splendido esemplare di Acero Palmatum ''Deshojo'', celebre per le sue foglie di un rosso intenso in primavera e autunno. Un pezzo da collezione che incanta con il suo fogliame vibrante e la sua struttura elegante.', 189.90, 8, 'bonsai', NULL),
('Bonsai di Ciliegio in Fiore', 'Evoca la magia della primavera giapponese con questo magnifico bonsai di ciliegio (Sakura). Durante la fioritura, si copre di delicati fiori rosa, offrendo uno spettacolo di rara bellezza.', 220.00, 5, 'bonsai', NULL),
('Bonsai di Acero a Foglie Gialle', 'L''Acero ''Katsura'' è una varietà affascinante che regala foglie di un giallo-arancio brillante in primavera, che virano al verde in estate e di nuovo all''arancione in autunno.', 175.50, 10, 'bonsai', NULL),
('Bonsai di Ginepro Cinese', 'Un classico intramontabile dell''arte bonsai. Il Ginepro è apprezzato per la sua resistenza, il suo fogliame verde intenso e la facilità con cui può essere modellato.', 129.00, 15, 'bonsai', NULL),
('Bonsai di Acero Tridente', 'L''Acero Tridente (Acer buergerianum) è un''essenza classica, amata per le sue caratteristiche foglie a tre punte, la corteccia interessante che si sviluppa con l''età e la sua notevole vigoria. Affascinante in ogni stagione.', 185.00, 9, 'bonsai', NULL),
('Bonsai di Glicine', 'Famoso per le sue spettacolari cascate di fiori viola profumati, il bonsai di Glicine è un vero gioiello. La sua fioritura trasforma l''albero in un''opera d''arte vivente.', 280.00, 4, 'bonsai', NULL),
('Bonsai di Azalea Satsuki', 'L''Azalea Satsuki è regina dei bonsai da fiore. A fine primavera, produce una fioritura così abbondante da coprire quasi completamente il fogliame con i suoi fiori rosa.', 165.00, 12, 'bonsai', NULL),
('Bonsai di Pino Nero Giapponese', 'Il Pino Nero è l''emblema della forza e della perseveranza. Con la sua corteccia scura e rugosa e i suoi aghi di un verde profondo, esprime un carattere maestoso.', 255.00, 6, 'bonsai', NULL),
('Bonsai di Acero Giapponese ''Seigen''', 'Una varietà spettacolare, l''Acero ''Seigen'' stupisce in primavera con le sue nuove foglie di un incredibile colore rosso-rosato corallo, che poi maturano in verde durante l''estate. Un''esplosione di colore unica.', 195.00, 7, 'bonsai', NULL),
('Bonsai di Olivo', 'L''olivo in versione bonsai cattura l''essenza del Mediterraneo. Con il suo tronco antico e contorto e le sue foglie argentate, è un simbolo di pace e longevità.', 155.00, 11, 'bonsai', NULL),
('Bonsai di Acero Giapponese ''Kiyohime''', 'Varietà nana di Acero Palmatum, ''Kiyohime'' è perfetta per bonsai di piccole dimensioni (shohin). Ha una crescita naturalmente densa, con piccole foglie verdi che diventano gialle e arancioni in autunno.', 159.90, 11, 'bonsai', NULL),
('Boschetto di Aceri Giapponesi in Stile Foresta', 'Composizione in stile foresta (Yose-ue) che utilizza più esemplari di Acero per ricreare un paesaggio boschivo in miniatura. Un''opera d''arte dinamica che evoca la serenità di una passeggiata nella natura.', 350.00, 3, 'bonsai', NULL),
('Bonsai di Melo da Fiore', 'Un albero quattro stagioni: fiori in primavera, fogliame in estate, piccoli frutti decorativi in autunno e una silhouette affascinante in inverno.', 185.00, 8, 'bonsai', NULL),
('Bonsai di Melograno', 'Apprezzato per i suoi fiori arancioni brillanti e per i piccoli melograni che seguono la fioritura. La sua corteccia che si sfalda con l''età aggiunge ulteriore interesse.', 169.50, 10, 'bonsai', NULL),
('Vaso per Bonsai Blu Cobalto', 'Vaso rettangolare in ceramica di alta qualità con finitura smaltata blu cobalto. Il colore profondo esalta magnificamente il verde e i colori autunnali.', 35.00, 40, 'vaso', NULL),
('Vaso per Bonsai Bianco Crema', 'Elegante vaso ovale in ceramica con smaltatura bianco crema. Perfetto per bonsai da fiore, mettendo in risalto i loro colori.', 32.00, 35, 'vaso', NULL),
('Vaso per Bonsai Verde Muschio', 'Vaso quadrato in ceramica con finitura smaltata verde muschio. Si abbina splendidamente con conifere come pini e ginepri.', 29.90, 50, 'vaso', NULL),
('Vaso per Bonsai Terracotta', 'Classico vaso rettangolare in terracotta non smaltata. Il suo aspetto rustico è ideale per conifere. L''eccellente porosità garantisce un''ottima salute delle radici.', 25.00, 60, 'vaso', NULL),
('Vaso per Bonsai Nero Opaco', 'Vaso rotondo basso in grès non smaltato nero opaco. Il design minimalista e il colore scuro creano un forte contrasto con la pianta.', 28.50, 45, 'vaso', NULL),
('Pinza Concava per Potatura', 'Strumento essenziale per tagliare i rami in modo netto e preciso, lasciando una ferita concava che cicatrizza in modo ottimale. Acciaio al carbonio di alta qualità.', 45.90, 50, 'cura', NULL),
('Filo di Alluminio Ramato (2.5mm)', 'Filo di alluminio ramato indispensabile per avvolgere e modellare i rami. Morbido e flessibile, non danneggia la corteccia. Diametro 2.5mm.', 12.90, 100, 'cura', NULL),
('Terriccio Akadama - Grana Media', 'Il substrato per bonsai per eccellenza. Argilla vulcanica giapponese che garantisce drenaggio e aerazione ottimali. Grana media adatta alla maggior parte dei bonsai.', 22.00, 80, 'cura', NULL),
('Terriccio Kiryuzuna per Conifere', 'Substrato vulcanico ideale per pini, ginepri e altre conifere. Assicura un drenaggio perfetto, prevenendo i marciumi radicali.', 25.50, 60, 'cura', NULL),
('Terriccio Kanuma per Acidofile', 'Substrato acido di origine vulcanica, specifico per piante acidofile come azalee e rododendri. Mantiene l''umidità e il pH ideali per la loro salute.', 24.00, 65, 'cura', NULL);

INSERT INTO Account (Nome, Cognome, Email, Pw, DataNascita, Ruolo) VALUES ("Simone", "Abbatiello", "s.abbatiello@gmail.com", "39373ddb10652be31d8e4574c5c9890af3bcd72d5f2635aa594fde26a2ad0b8905fe841e9f5d75d5879910287e7a47605524cdacc64e12a98e7ef90bf176d304",'2004-03-02','admin');
