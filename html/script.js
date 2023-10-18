let xhr = new XMLHttpRequest();
xhr.withCredentials = true;
xhr.open('GET', 'http://localhost:8081/api/version');
xhr.setRequestHeader('accept', '*/*');

xhr.onload = function() {
    document.getElementById('test').innerHTML = xhr.response;
    console.log(xhr.response);
};

xhr.send();