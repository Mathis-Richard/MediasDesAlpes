const separator = document.createElement("p")
separator.style.borderBottom = "3px solid black"
separator.style.margin = "2vw 0"

let xhr = new XMLHttpRequest();
xhr.withCredentials = true;
xhr.open('GET', 'http://localhost:9191/media');
xhr.setRequestHeader('accept', '*/*');
xhr.responseType = "json";

xhr.onload = function() {
    let resp = xhr.response;
    let root =  document.getElementById('medias');
    root.style.display="flex"
    root.append(separator.cloneNode())
    for(let i = 0; i<resp.length; i++){
        let link = document.createElement("a")
        let element = document.createElement("div")
        let elementT = document.createElement("div")
        let title = document.createElement("p")
        let image = document.createElement("img")
        element.style.display="flex"
        element.style.padding="1vw"
        elementT.style.margin="2vw"
        elementT.style.display="flex"
        elementT.style.flexDirection="column"
        elementT.style.justifyContent="space-between"
        title.innerText = resp[i].titre
        image.src = resp[i].img
        image.width = 150
        image.height = 150
        link.text = "Voir la fiche média"
        link.rel = "./media/"+resp[i].id
        title.classList.add("text-white")
        elementT.appendChild(title);
        elementT.appendChild(link);
        element.appendChild(image);
        element.appendChild(elementT)
        root.appendChild(element)
        root.append(separator.cloneNode())
    }
    console.log(xhr.response);
};

xhr.send();