function generateTable(columnNames, rowData) {
    let tableHTML = '<table id="myTable">\n<thead>\n<tr>\n';

    // Ajouter les noms de colonnes
    columnNames.forEach(column => {
        tableHTML += `<th>${column}</th>\n`;
    });

    tableHTML += '</tr>\n</thead>\n<tbody>\n';

    // Ajouter les données des lignes
    rowData.forEach(row => {
        tableHTML += '<tr>\n';
        row.forEach(cell => {
            tableHTML += `<td>${cell}</td>\n`;
        });
        tableHTML += '</tr>\n';
    });

    tableHTML += '</tbody>\n</table>';

    return tableHTML;
}

/*function searchTable() {
    // Récupérer la chaîne de recherche depuis l'élément d'entrée
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");

    // Parcourir toutes les lignes du tableau et masquer celles qui ne correspondent pas à la recherche
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0]; // Choisir la colonne que vous souhaitez rechercher (dans cet exemple, la première colonne)
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}*/


function createMediaForm() {
    refreshView();
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
    document.getElementById('vueCreateMedia').appendChild(form);

}

function createUserAccountForm() {
    if (document.getElementById('vueCreateMedia').children[0]) {
        if (document.getElementById('vueCreateMedia').children[0].id === 'formContainer') {
            document.getElementById('vueCreateMedia').innerHTML = '';
        }
    }

    if (!document.getElementById('formMakeUserAccount')) {
        // Création du formulaire
        var form = document.createElement("form");
        form.setAttribute("action", "/submit");
        form.setAttribute("method", "post");
        form.id = 'formMakeUserAccount';

        // Création et ajout des éléments du formulaire
        //var labelIdUtilisateur = createLabel("idUtilisateur", "ID Utilisateur (Clé primaire):");
        //var inputIdUtilisateur = createInput("text", "idUtilisateur", "idUtilisateur", true);

        var labelNom = createLabel("nom", "Nom:");
        var inputNom = createInput("text", "nom", "nom", true);

        var labelPrenom = createLabel("prenom", "Prénom:");
        var inputPrenom = createInput("text", "prenom", "prenom", true);

        var labelAdresse = createLabel("adresse", "Adresse:");
        var inputAdresse = createInput("text", "adresse", "adresse", true);

        var labelTelephone = createLabel("telephone", "Numéro de téléphone:");
        var inputTelephone = createInput("tel", "telephone", "telephone", true);

        var labelEmail = createLabel("email", "Adresse e-mail:");
        var inputEmail = createInput("email", "email", "email", true);

        var labelMotDePasse = createLabel("motDePasse", "Mot de passe:");
        var inputMotDePasse = createInput("password", "motDePasse", "motDePasse", true);

        var labelTypeProfil = createLabel("typeProfil", "Type de profil:");
        var selectTypeProfil = createSelect("typeProfil", "typeProfil", true);
        addOption(selectTypeProfil, "responsable", "Responsable de médiathèque");
        addOption(selectTypeProfil, "usager", "Usager");

        var inputSubmit = document.createElement("input");
        inputSubmit.setAttribute("type", "submit");
        inputSubmit.setAttribute("value", "Ajouter Utilisateur");
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
        //form.appendChild(labelIdUtilisateur);
        //form.appendChild(inputIdUtilisateur);
        //form.appendChild(document.createElement("br"));

        form.appendChild(labelNom);
        form.appendChild(inputNom);
        form.appendChild(document.createElement("br"));

        form.appendChild(labelPrenom);
        form.appendChild(inputPrenom);
        form.appendChild(document.createElement("br"));

        form.appendChild(labelAdresse);
        form.appendChild(inputAdresse);
        form.appendChild(document.createElement("br"));

        form.appendChild(labelTelephone);
        form.appendChild(inputTelephone);
        form.appendChild(document.createElement("br"));

        form.appendChild(labelEmail);
        form.appendChild(inputEmail);
        form.appendChild(document.createElement("br"));

        form.appendChild(labelMotDePasse);
        form.appendChild(inputMotDePasse);
        form.appendChild(document.createElement("br"));

        form.appendChild(labelTypeProfil);
        form.appendChild(selectTypeProfil);
        form.appendChild(document.createElement("br"));

        form.appendChild(inputSubmit);


        document.getElementById('vueCreateMedia').appendChild(form);



    }
}

// Fonction utilitaire pour créer un label
function createLabel(forAttribute, textContent) {
    var label = document.createElement("label");
    label.setAttribute("for", forAttribute);
    label.textContent = textContent;
    return label;
}

// Fonction utilitaire pour créer un input
function createInput(type, id, name, required) {
    var input = document.createElement("input");
    input.setAttribute("type", type);
    input.setAttribute("id", id);
    input.setAttribute("name", name);
    if (required) {
        input.setAttribute("required", "");
    }
    return input;
}

// Fonction utilitaire pour créer un select
function createSelect(id, name, required) {
    var select = document.createElement("select");
    select.setAttribute("id", id);
    select.setAttribute("name", name);
    if (required) {
        select.setAttribute("required", "");
    }
    return select;
}

// Fonction utilitaire pour ajouter une option à un select
function addOption(select, value, text) {
    var option = document.createElement("option");
    option.setAttribute("value", value);
    option.textContent = text;
    select.appendChild(option);
}

function createReadTable(columnNames, rowData) {
    refreshView();
    const generatedTable = generateTable(columnNames, rowData);
    const ok = document.createElement('div');
    ok.innerHTML = generatedTable;
    ok.id = 'mediaRankTable';
    document.getElementById('vueCreateMedia').appendChild(ok);

    // Fonction pour effectuer la recherche

    // Ajouter un écouteur d'événements pour déclencher la recherche lors de la frappe
    //document.getElementById("searchInput").addEventListener("input", searchTable);

    // Initialiser DataTables
    $(document).ready(function () {
        $('#myTable').DataTable();
    });
}


function generateTableModifyUser(columnNames, rowData) {
    let tableHTML = '<table id="myTable">\n<thead>\n<tr>\n';

    // Ajouter les noms de colonnes
    columnNames.forEach(column => {
        tableHTML += `<th>${column}</th>\n`;
    });

    tableHTML += '</tr>\n</thead>\n<tbody>\n';

    // Ajouter les données des lignes
    rowData.forEach(row => {
        tableHTML += '<tr>\n';
        row.forEach(cell => {
            tableHTML += `<td><input type="text" value="${cell}"></td>\n`;
        });
        tableHTML += '</tr>\n';
    });

    tableHTML += '</tbody>\n</table>';

    return tableHTML;
}

function submitForm() {
    const table = $('#myTable').DataTable();
    const inputValues = [];

    // Parcourir toutes les lignes du tableau
    table.rows().every(function () {
        const rowData = [];
        const cells = $(this.node()).find('input');

        // Parcourir tous les inputs dans la ligne
        cells.each(function () {
            const inputValue = $(this).val();
            rowData.push(inputValue);
        });

        inputValues.push(rowData);
    });

    // Afficher les valeurs dans la console ou effectuer d'autres actions
    console.log(inputValues);
}

function modifyUserAccountForm() {
    if (document.getElementById('vueCreateMedia').children[0]) {
        if (document.getElementById('vueCreateMedia').children[0].tagName.toLowerCase() === 'form') {
            document.getElementById('vueCreateMedia').innerHTML = '';
        }
    }
    if (!document.getElementById('formContainer')) {
        const myFormContainer = document.createElement('div');
        myFormContainer.id = 'formContainer';

        const myTableContainer = document.createElement('div');
        myTableContainer.id = 'tableContainer';

        const myButtonSubmit = document.createElement('button');
        myButtonSubmit.textContent = "Submit";
        myButtonSubmit.onclick = submitForm;

        myFormContainer.append(myTableContainer, myButtonSubmit);


        // Exemple d'utilisation
        const columnNames = ["ID", "Nom", "Prénom", "Adresse", "Numéro de téléphone", "Adresse e-mail", "Mot de passe", "Type de profil"];
        const rowData = [
            ["1", "Doe", "Joe", "average_basement", "0123456789", "doe@joe.com", "itsmefr", "averageJoe"],
            ["2", "Smith", "Alice", "wonderland", "9876543210", "alice.smith@example.com", "alice_wonder", "wonderAlice"],
            ["3", "Johnson", "Bob", "fantasy_land", "5551234567", "bob.johnson@example.com", "bobby", "fantasyBob"],
            ["4", "Williams", "Emily", "dreamville", "3339998888", "emily.williams@example.com", "emily_dream", "dreamEmily"],
            ["5", "Brown", "David", "secret_garden", "1112223333", "david.brown@example.com", "david_secret", "secretDavid"],
            ["6", "Miller", "Ella", "enchanted_forest", "6667778888", "ella.miller@example.com", "ella_enchanted", "enchantedElla"],
            ["7", "Davis", "Michael", "magic_kingdom", "4445556666", "michael.davis@example.com", "michael_magic", "magicMichael"],
            ["8", "Garcia", "Sophia", "fairy_tale", "8887776666", "sophia.garcia@example.com", "sophia_fairy", "fairySophia"],
            ["9", "Martinez", "Oliver", "storybook_land", "2223334444", "oliver.martinez@example.com", "oliver_story", "storybookOliver"],
            ["10", "Taylor", "Isabella", "wonderful_world", "9998887777", "isabella.taylor@example.com", "isabella_wonder", "wonderfulIsabella"],
            ["11", "Anderson", "Sophie", "enchanted_castle", "7778889999", "sophie.anderson@example.com", "sophie_enchanted", "enchantedSophie"],
            ["12", "White", "Jack", "magic_forest", "1234567890", "jack.white@example.com", "jack_magic", "magicJack"],
            ["13", "Clark", "Emma", "dreamy_meadow", "4567890123", "emma.clark@example.com", "emma_dreamy", "dreamyEmma"],
            ["14", "Thomas", "Liam", "fantastic_village", "7890123456", "liam.thomas@example.com", "liam_fantastic", "fantasticLiam"],
            ["15", "Hall", "Ava", "storybook_mansion", "2345678901", "ava.hall@example.com", "ava_storybook", "storybookAva"],
            ["16", "Moore", "Mia", "secret_valley", "5678901234", "mia.moore@example.com", "mia_secret", "secretMia"],
            ["17", "Adams", "Noah", "wonderland_grove", "8901234567", "noah.adams@example.com", "noah_wonder", "wonderNoah"],
            ["18", "Baker", "Grace", "enchanted_glade", "3456789012", "grace.baker@example.com", "grace_enchanted", "enchantedGrace"],
            ["19", "Ross", "Lily", "magic_haven", "6789012345", "lily.ross@example.com", "lily_magic", "magicLily"],
            ["20", "Ward", "Logan", "fantasy_sanctuary", "0123456789", "logan.ward@example.com", "logan_fantasy", "fantasyLogan"]
        ];


        const generatedTable = generateTableModifyUser(columnNames, rowData);

        myTableContainer.innerHTML = generatedTable;


        // Initialiser DataTables
        $(document).ready(function () {
            $('#myTable').DataTable({
                "columnDefs": [
                    { "type": "html-input", "targets": "_all" } // Définir le type de colonne comme "html-input"
                ]
            });
        });


        document.getElementById('vueCreateMedia').appendChild(myFormContainer);
    }


}

/*function createModifyUserButtons() {
    const createButton = document.createElement('button');
    const modifyButton = document.createElement('button');
    if (document.getElementById('vueCreateMedia').children.length > 0) document.getElementById('vueCreateMedia').innerHTML = '';
    if (!document.getElementById('isTopButtons')) {
        createButton.classList.add('topButton', 'button');
        createButton.id = 'isTopButtons';
        createButton.innerText = "CREER UN COMPTE UTILISATEUR";
        modifyButton.classList.add('topButton', 'button');
        modifyButton.id = 'isTopButtons';
        modifyButton.innerText = "MODIFIER UN COMPTE UTILISATEUR";
        document.getElementById('topButtonsContainer').append(createButton, modifyButton);
    }

    createButton.addEventListener('click', () => {
        const userForm = formMakeUserAccount();
        document.getElementById('vueCreateMedia').appendChild(userForm);
    })

    modifyButton.addEventListener('click', () => {
        const createdUserForm = createUserForm();
        document.getElementById('vueCreateMedia').appendChild(createdUserForm);
    })
}*/

function refreshView() {
    const topButtons = document.getElementById('topButtonsContainer');
    while (topButtons.children.length > 0) topButtons.children[0].remove();
    if (document.getElementById('vueCreateMedia').children.length) document.getElementById('vueCreateMedia').innerHTML = '';
}

