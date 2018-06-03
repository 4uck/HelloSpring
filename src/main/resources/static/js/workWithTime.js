function getTime(){

    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/timestamps/STOP', true);
    xhr.responseType = 'json';
    xhr.setRequestHeader('Authorization', localStorage.getItem('token'));
    xhr.send();

    xhr.onreadystatechange = function() {
        if (xhr.readyState != 4) return;

        var body = xhr.response;

        var sumTime = document.getElementById('sumTime').value =
            "Твое время: " + body.hours + "ч " + body.minutes + "м " + body.seconds + "с";
        var divTime = document.getElementById('divTime').style = "visibility: visible";
    }
}

function startTime(){

    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/timestamps/START', false);

    xhr.setRequestHeader('Authorization', localStorage.getItem('token'));

    xhr.send();

    if(xhr.status != 200){
        alert(xhr.status);
        alert("Что-то пошло не так");
    }
}

function pauseTime(){

    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8080/timestamps/PAUSE', false);

    xhr.setRequestHeader('Authorization', localStorage.getItem('token'));

    xhr.send();

    if(xhr.status != 200){
        alert(xhr.status);
        alert("Что-то пошло не так");
    }
}

