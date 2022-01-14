// function loadDoc(id) {
//     const xhttp = new XMLHttpRequest();
//     xhttp.onload = function() {
//         document.getElementById("demo").innerHTML = this.responseText;
//     }
//     xhttp.open("GET", "/vaassschool/desativaCategoria", true);
//     xhttp.send();
// }

function disableNow(id) {
    let httpRequest = new XMLHttpRequest();
    httpRequest.open("POST", "/vaasschool/desativaCategoria",true);

    httpRequest.onreadystatechange = function (){
        if(httpRequest.status==200 && httpRequest.readyState==4){
            let activeId = document.getElementById("category_${category.id}").value;
            console.log(activeId);
            // activeId.innerHTML = "Inativa";
        }
    }

    httpRequest.send(id);
}




// function disableNow(id) {
//     $.post("desativaCategoria", {'id': id}, function () {
//         $("#category_" + id).html("Inativa");
//     });
// }





