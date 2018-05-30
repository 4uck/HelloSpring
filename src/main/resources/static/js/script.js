function getAction(){

    var email = document.forms.data['email'].value;
    var pass = document.forms.data['password'].value;
    var confPass = document.forms.data['confPass'].value;

    if (email == '' || pass == '' || confPass == ''){
        alert("Fields must be filled!!!");
        return false;
    }
    if (pass != confPass){
        alert('Password and confirm must be the same!!');
        return false;
    }

    if(sendUser(email, pass)){
        alert("User with that login already exist");
        return false;
    }

    return true;
}

function sendUser(email, password){

    // 1. Создаём новый объект XMLHttpRequest
    var xhr = new XMLHttpRequest();

    var json = JSON.stringify({
      "login": email,
      "password": password
    });

    // 2. Конфигурируем его: GET-запрос на URL 'phones.json'
    xhr.open('POST', 'http://localhost:8080/checkUser', false);

    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');

    // 3. Отсылаем запрос
    xhr.send(json);

    // 4. Если код ответа сервера не 200, то это ошибка
    if (xhr.status != 200) {
      // обработать ошибку
      alert("Такой пользователь уже существует");
//      alert( xhr.status + ': ' + xhr.statusText ); // пример вывода: 404: Not Found
      return true;
    } else {
      // вывести результат
//      alert( xhr.responseText ); // responseText -- текст ответа.
      return false;
    }

}