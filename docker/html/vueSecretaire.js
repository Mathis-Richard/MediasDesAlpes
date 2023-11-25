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

    var labelAuteur = createLabel("genre", "Genre:");
    var selectAuteur = createSelect("idGenre", "idGenre", true);
    addOption(selectAuteur, "ADMINISTRATEUR", "ADMINISTRATEUR");
    addOption(selectAuteur, "UTILISATEUR", "UTILISATEUR");

    var labelAnnee = createLabel("anneePublicationSortie", "Année de publication/Sortie:");
    var inputAnnee = createInput("text", "anneePublicationSortie", "anneePublicationSortie", true);

    var labelTypeMedia = createLabel("typeMedia", "Type de média:");
    var selectTypeMedia = createSelect("idType", "idType", true);
    addOption(selectTypeMedia, "ADMINISTRATEUR", "ADMINISTRATEUR");
    addOption(selectTypeMedia, "UTILISATEUR", "UTILISATEUR");

    var labelQuantite = createLabel("img", "Lien image:");
    var inputQuantite = createInput("text", "img", "img", true);

    var labelStatut = createLabel("note", "Note:");
    var selectStatut = createInput("number", "note", "note", true);


    var inputSubmit = document.createElement("input");
    inputSubmit.setAttribute("type", "submit");
    inputSubmit.setAttribute("value", "Ajouter Média");
    inputSubmit.addEventListener('click', (e) => {
        e.preventDefault();
        var formElements = form.elements;
        var formData = {};
        for (var i = 0; i < formElements.length; i++) {
            var element = formElements[i];
            if (element.name && element.type !== "submit") formData[element.name] = element.value;
        }
        formData.id = '';

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
    form.appendChild(selectAuteur);
    form.appendChild(document.createElement("br"));


    form.appendChild(labelTypeMedia);
    form.appendChild(selectTypeMedia);
    form.appendChild(document.createElement("br"));

    form.appendChild(labelAnnee);
    form.appendChild(inputAnnee);
    form.appendChild(document.createElement("br"));

    form.appendChild(labelQuantite);
    form.appendChild(inputQuantite);
    form.appendChild(document.createElement("br"));

    form.appendChild(labelStatut);
    form.appendChild(selectStatut)
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

        var labelNom = createLabel("nom", "Nom:");
        var inputNom = createInput("text", "nomUtilisateur", "nomUtilisateur", true);

        var labelPrenom = createLabel("prenom", "Prénom:");
        var inputPrenom = createInput("text", "prenomUtilisateur", "prenomUtilisateur", true);

        var labelAdresse = createLabel("adresse", "Adresse:");
        var inputAdresse = createInput("text", "adresseUtilisateur", "adresseUtilisateur", true);

        var labelTelephone = createLabel("telephone", "Numéro de téléphone:");
        var inputTelephone = createInput("tel", "numTelephoneUtilisateur", "numTelephoneUtilisateur", true);

        var labelEmail = createLabel("email", "Adresse e-mail:");
        var inputEmail = createInput("email", "emailUtilisateur", "emailUtilisateur", true);


        // Adding date of birth field
        var labelDateNaissance = createLabel("dateNaissance", "Date de naissance:");
        var inputDateNaissance = createInput("date", "dateNaissanceUtilisateur", "dateNaissanceUtilisateur", true);

        var labelTypeProfil = createLabel("typeProfilUtilisateur", "Type de profil:");
        var selectTypeProfil = createSelect("typeProfilUtilisateur", "typeProfilUtilisateur", true);
        addOption(selectTypeProfil, "ADMINISTRATEUR", "ADMINISTRATEUR");
        addOption(selectTypeProfil, "UTILISATEUR", "UTILISATEUR");

        var inputSubmit = document.createElement("input");
        inputSubmit.setAttribute("type", "submit");
        inputSubmit.setAttribute("value", "Ajouter Utilisateur");
        inputSubmit.addEventListener('click', (e) => {
            e.preventDefault();
            var formElements = form.elements;
            var formData = {};
            for (var i = 0; i < formElements.length; i++) {
                var element = formElements[i];
                if (element.name && element.type !== "submit") formData[element.name] = element.value;
            }
            formData.id = '';
            fetch('http://localhost:9191/utilisateurs/new', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    // Vous pouvez ajouter d'autres en-têtes si nécessaire
                },
                body: JSON.stringify(formData) // Convertir les données en format JSON
            })
                .then(response => {
                    // Vérifier si la requête a réussi (statut 2xx)
                    if (!response.ok) {
                        throw new Error('La requête a échoué avec le statut ' + response.status);
                    }
                    // Convertir la réponse en JSON
                    return response.json();
                })
        })

        // Ajout des éléments au formulaire
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

        form.appendChild(labelDateNaissance);
        form.appendChild(inputDateNaissance);
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
        let count = 0;
        tableHTML += '<tr>\n';
        row.forEach(cell => {
            count++;
            if (count === 3) {
                tableHTML += `<td><input type="text" disabled=true value="${cell}"></td>\n`;
            } else {
                tableHTML += `<td><input type="text" value="${cell}"></td>\n`;
            }

        });
        tableHTML += '</tr>\n';
    });

    tableHTML += '</tbody>\n</table>';

    return tableHTML;
}

function submitForm() {
    const table = $('#myTable').DataTable();
    const inputValues = [];
    let userTabObject = [];
    // Parcourir toutes les lignes du tableau
    table.rows().every(function () {
        const rowData = [];
        const cells = $(this.node()).find('input');

        let user = {
            "id": "",
            "emailUtilisateur": "",
            "mdpUtilisateur": "",
            "nomUtilisateur": "",
            "prenomUtilisateur": "",
            "adresseUtilisateur": "",
            "numTelephoneUtilisateur": "",
            "dateNaissanceUtilisateur": "",
            "typeProfilUtilisateur": "",
        };


        // Parcourir tous les inputs dans la ligne
        let i = 0;
        cells.each(function () {
            user[Object.keys(user)[i]] = $(this).val();
            i++;
            if (i > 8) {
                i = 0;
                userTabObject.push(user);
            }
        });

    });
    console.log(userTabObject);
    for (let keyObject in userTabObject) {
        const data = userTabObject[keyObject];
        console.log("oui", userTabObject[keyObject]);
        let itemId = userTabObject[keyObject].emailUtilisateur;
        itemId = encodeURIComponent(itemId);
        fetch(`http://localhost:9191/utilisateurs/${itemId}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('La requête a échoué avec le statut ' + response.status);
                }
                // Convertir la réponse en JSON
                return response.json();
            })
            .then(updatedData => {
                // Traiter les données mises à jour ici
                console.log(updatedData);
            })
            .catch(error => {
                // Gérer les erreurs ici
                console.error('Erreur lors de la requête PATCH:', error);
            });
    }
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
        const columnNames = ["ID", "email", "Mot de passe", "Nom", "Prenom", "Adresse", "Telephone", "Date de naissance", "Type de profil"];
        fetch('http://localhost:9191/utilisateurs', {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        })
            .then(response => {
                // Vérifier si la requête a réussi (statut 200 OK)
                if (!response.ok) {
                    throw new Error('La requête a échoué avec le statut ' + response.status);
                }
                // Convertir la réponse en JSON
                return response.json();
            })
            .then(data => {
                // Traiter les données ici
                let dataConverted = convertirTableau(data);
                const generatedTable = generateTableModifyUser(columnNames, dataConverted);

                myTableContainer.innerHTML = generatedTable;


                // Initialiser DataTables
                $(document).ready(function () {
                    $('#myTable').DataTable({
                        "columnDefs": [
                            {"type": "html-input", "targets": "_all"} // Définir le type de colonne comme "html-input"
                        ]
                    });
                });
                document.getElementById('vueCreateMedia').appendChild(myFormContainer);
            })
            .catch(error => {
                // Gérer les erreurs ici
                console.error('Erreur lors de la requête:', error);
            });
    }


}

function supprimerElement(tableau, champ) {
    // Filtrer chaque objet dans le tableau
    const nouveauTableau = tableau.map(objet => {
        // Vérifier si le champ existe dans l'objet
        if (champ in objet) {
            // Supprimer le champ de l'objet
            delete objet[champ];
        }
        return objet;
    });

    return nouveauTableau;
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

function convertirTableau(objets) {
    const resultats = [];

    for (const objet of objets) {
        const ligne = Object.values(objet).map((valeur) => (valeur !== null && valeur !== undefined) ? valeur.toString() : "");
        resultats.push(ligne);
    }

    return resultats;
}

function updateDataWithMappings(data, typeMap, genreMap) {
    return data.map(item => {
        const updatedItem = { ...item };

        // Mise à jour de idType
        const typeMapping = typeMap.find(type => type.id === item.idType);
        if (typeMapping) {
            updatedItem.idType = typeMapping.designationType;
        }

        // Mise à jour de idGenre
        const genreMapping = genreMap.find(genre => genre.id === item.idGenre);
        if (genreMapping) {
            updatedItem.idGenre = genreMapping.designation;
        }

        return updatedItem;
    });
}

function refreshView() {
    const topButtons = document.getElementById('topButtonsContainer');
    while (topButtons.children.length > 0) topButtons.children[0].remove();
    if (document.getElementById('vueCreateMedia').children.length) document.getElementById('vueCreateMedia').innerHTML = '';
}

