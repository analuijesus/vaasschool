function disableNow(id) {
    let httpRequest = new XMLHttpRequest();

    httpRequest.open("POST", "/vaasschool/desativaCategoria", true);

    httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    httpRequest.onreadystatechange = function () {
        if (httpRequest.status == 200 && httpRequest.readyState == 4) {
            document.getElementById("category_" + id).innerText = 'Inativa!';
        }
    }

    httpRequest.send("id=" + id);
}

function finalizaAgora(id) {
    $.post("desativaCategoria", {'id': id}, function () {
        $("#category_" + id).html("Inativa");
    });
}