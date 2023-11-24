function mediaCardMaker(bookDataArray) {

    // Création du conteneur principal
    const mainContainer = document.createElement("div");
    let nbBook = 0;
    let bookContainer3 = document.createElement('div');
    bookContainer3.id = "bookContainer3";
    // Boucle à travers chaque objet dans le tableau
    bookDataArray.forEach(bookData => {
        // Création des éléments HTML pour chaque livre
        const bookContainer = document.createElement("div");
        bookContainer.classList.add("book-card");

        const imageElement = document.createElement("img");
        imageElement.src = bookData.url;
        imageElement.alt = bookData.title;
        imageElement.classList.add("book-image");

        const titleElement = document.createElement("h2");
        titleElement.textContent = bookData.title;

        const descriptionElement = document.createElement("p");
        descriptionElement.textContent = bookData.description;

        const authorElement = document.createElement("p");
        authorElement.textContent = "Auteur: " + bookData.author;

        const genreElement = document.createElement("p");
        genreElement.textContent = "Genre: " + bookData.genre;

        const releaseDateElement = document.createElement("p");
        releaseDateElement.textContent = "Date de sortie: " + bookData.releaseDate;

        const ratingElement = document.createElement("p");
        ratingElement.textContent = "Note moyenne: " + bookData.averageRating;

        const detailButton = document.createElement("button");
        detailButton.textContent = "Voir les détails";
        detailButton.addEventListener("click", function () {
            const dataToSend = {
                title: bookData.title,
                description: bookData.description,
                author: bookData.author,
                genre: bookData.genre,
                releaseDate: bookData.releaseDate,
                averageRating: bookData.averageRating,
            };

            // Convertir l'objet en chaîne de requête JSON et l'encoder
            const dataString = encodeURIComponent(JSON.stringify(dataToSend));
            window.location.href = 'detail.html?data=' + dataString;
        });

        // Ajout des éléments à la carte du livre
        bookContainer.appendChild(imageElement);
        bookContainer.appendChild(titleElement);
        bookContainer.appendChild(descriptionElement);
        bookContainer.appendChild(authorElement);
        bookContainer.appendChild(genreElement);
        bookContainer.appendChild(releaseDateElement);
        bookContainer.appendChild(ratingElement);
        bookContainer.appendChild(detailButton);

        bookContainer3.appendChild(bookContainer);

        nbBook++;
        if (nbBook === 3) {
            nbBook = 0;
            mainContainer.appendChild(bookContainer3);
            bookContainer3 = document.createElement('div'); // Créer un nouveau conteneur après l'ajout
            bookContainer3.id = "bookContainer3";

        }
        if (bookContainer3.children.length > 0) {
            mainContainer.appendChild(bookContainer3);
        }
        // Ajout de la carte du livre au conteneur principal
        //mainContainer.appendChild(bookContainer);
    });

    // Retourne le conteneur principal avec toutes les cartes de livres
    return mainContainer;
}

function genererHTML(titre, urlImage, maHashMap) {
    var container = document.createElement('div');
    container.style.textAlign = "center"; // Centrer le contenu

    // Créer un élément h1 pour le titre
    var titreElement = document.createElement('h1');
    titreElement.textContent = titre;

    // Créer un élément img pour l'image
    var imageElement = document.createElement('img');
    imageElement.src = urlImage;
    imageElement.alt = "Image";
    imageElement.style.maxWidth = "100%"; // Assurer que l'image ne dépasse pas la largeur du conteneur

    // Ajouter le titre et l'image au conteneur
    container.appendChild(titreElement);
    container.appendChild(imageElement);

    // Parcourir la hashmap et créer un élément div pour chaque paire clé-valeur
    for (var cle in maHashMap) {
        var phraseElement = document.createElement('div');
        phraseElement.textContent = cle + ': ' + maHashMap[cle];
        phraseElement.style.border = "1px solid #000";
        phraseElement.style.padding = "10px";
        phraseElement.style.marginTop = "10px"; // Ajouter un espacement entre les éléments

        // Ajouter l'élément div au conteneur
        container.appendChild(phraseElement);
    }

    var bouton = document.createElement('button');
    bouton.textContent = "Réserver/Emprunter";
    bouton.style.marginTop = "20px"; // Ajouter un espacement entre le bouton et les éléments précédents

    // Ajouter le bouton au conteneur
    container.appendChild(bouton);

    // Appliquer des styles au conteneur principal
    container.style.maxWidth = "600px"; // Limiter la largeur du conteneur principal
    container.style.margin = "auto"; // Centrer le conteneur sur la page
   document.body.appendChild(container);
}

function createMediaFormUserView() {
    // Création du formulaire
    var form = document.createElement("form");
    form.setAttribute("action", "/submit");
    form.setAttribute("method", "post");

    // Création et ajout des éléments du formulaire
    //var labelIdMedia = createLabel("idMedia", "ID Média (Clé primaire):");
    //var inputIdMedia = createInput("text", "idMedia", "idMedia", true);

    var labelTitre = createLabel("titre", "Titre:");
    var inputTitre = createInput("text", "titre", "titre", true);

    var labelAuteur = createLabel("auteur", "Auteur/Réalisateur:");
    var inputAuteur = createInput("text", "auteur", "auteur", true);

    var labelAnnee = createLabel("annee", "Année de publication/Sortie:");
    var inputAnnee = createInput("text", "annee", "annee", true);

    var labelTypeMedia = createLabel("typeMedia", "Type de média:");
    var selectTypeMedia = createSelect("typeMedia", "typeMedia", true);
    addOption(selectTypeMedia, "disponible", "Livre");
    addOption(selectTypeMedia, "emprunte", "CD");
    addOption(selectTypeMedia, "reserve", "DVD");

    var labelQuantite = createLabel("quantite", "Quantité disponible:");
    var inputQuantite = createInput("number", "quantite", "quantite", true);

    var labelStatut = createLabel("statut", "Statut:");
    var selectStatut = createSelect("statut", "statut", true);
    addOption(selectStatut, "disponible", "Disponible");
    addOption(selectStatut, "emprunte", "Emprunté");
    addOption(selectStatut, "reserve", "Réservé");

    var labelUrlImage = createLabel("urlImage", "URL Image:");
    var inputUrlImage = createInput("url", "urlImage", "urlImage");

    var inputSubmit = document.createElement("input");
    inputSubmit.setAttribute("type", "submit");
    inputSubmit.setAttribute("value", "Ajouter Média");
    inputSubmit.addEventListener('click', (e) => {
        e.preventDefault();
        var formElements = form.elements;
        var formData = {};
        for (var i = 0; i < formElements.length; i++) {
            var element = formElements[i];
            // Vérifier si l'élément a un nom et n'est pas le bouton de soumission
            if (element.name && element.type !== "submit") formData[element.name] = element.value;
        }
        console.log(formData);
    })

    // Ajout des éléments au formulaire
    //form.appendChild(labelIdMedia);
    //form.appendChild(inputIdMedia);
    //form.appendChild(document.createElement("br"));

    form.appendChild(labelTitre);
    form.appendChild(inputTitre);
    form.appendChild(document.createElement("br"));

    form.appendChild(labelAuteur);
    form.appendChild(inputAuteur);
    form.appendChild(document.createElement("br"));

    form.appendChild(labelAnnee);
    form.appendChild(inputAnnee);
    form.appendChild(document.createElement("br"));

    form.appendChild(labelTypeMedia);
    form.appendChild(selectTypeMedia);
    form.appendChild(document.createElement("br"));

    form.appendChild(labelQuantite);
    form.appendChild(inputQuantite);
    form.appendChild(document.createElement("br"));

    form.appendChild(labelStatut);
    form.appendChild(selectStatut);
    form.appendChild(document.createElement("br"));

    form.appendChild(labelUrlImage);
    form.appendChild(inputUrlImage);
    form.appendChild(document.createElement("br"));

    form.appendChild(inputSubmit);
    document.getElementById('viewContainerId').appendChild(form);

}

function createReadTableUserView(columnNames, rowData) {
    const generatedTable = generateTable(columnNames, rowData);
    const ok = document.createElement('div');
    ok.innerHTML = generatedTable;
    ok.id = 'mediaRankTable';
    document.getElementById('viewContainerId').appendChild(ok);

    // Fonction pour effectuer la recherche

    // Ajouter un écouteur d'événements pour déclencher la recherche lors de la frappe
    //document.getElementById("searchInput").addEventListener("input", searchTable);

    // Initialiser DataTables
    $(document).ready(function () {
        $('#myTable').DataTable();
    });
}



