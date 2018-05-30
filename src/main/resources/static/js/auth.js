function getToken(){

    var email = document.getElementById('email').value;
    var pass = document.getElementById('password').value;

    if (email == '' || pass == ''){
        alert("Fields must be filled!!!");
        return false;
    }

    if(sendUser(email, pass)){
        return true;
    }

    return false;
}

function sendUser(email, password){

    var xhr = new XMLHttpRequest();

    var json = JSON.stringify({
      "username": email,
      "password": password
    });

    xhr.open('POST', 'http://localhost:8080/login', false);

    xhr.setRequestHeader('Content-type', 'application/json');

    xhr.send(json);

    if (xhr.status != 200) {
      // обработать ошибку
      alert("Нет такого пользователя");
//      alert( xhr.status + ': ' + xhr.statusText ); // пример вывода: 404: Not Found

      return false;
    } else {
      // вывести результат
//      alert( xhr.responseText ); // responseText -- текст ответа.

      var token = xhr.getResponseHeader("Authorization");
      localStorage.setItem('token', token);

      document.location.href="http://localhost:8080/home";

//      document.cookie = "Authorization=" + token + "; path=/";

//    $.ajax({
//        url: "http://localhost:8080",
//        type: "GET",
//        beforeSend: function(xhr) {
//            xhr.setRequestHeader('Authorization', localStorage.getItem('token'));
//        },
//        success: function() {
//            window.location.href = '/home';
//        }
//    });

      return true;
    }

}