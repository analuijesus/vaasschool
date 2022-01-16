function disableNow(id) {
    let httpRequest = new XMLHttpRequest();


    httpRequest.onreadystatechange = function () {
        if (httpRequest.status == 200 && httpRequest.readyState == 4) {
            document.getElementById("category_" + id);
        }
    }
    console.log(httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded"));
    httpRequest.open("POST", "/vaasschool/desativaCategoria", true);
    httpRequest.send("id=" +id);
}


// function disableNow(id) {
//     $.post("desativaCategoria", {'id': id}, function () {
//         $("#category_" + id);
//     });
//
//     console.log("#category_" + id);
// }





