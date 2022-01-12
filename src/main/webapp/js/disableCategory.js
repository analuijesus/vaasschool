function disableNow(id) {
    $.post("desativaCategoria", {'id': id}, function () {
        $("#category_" + id).html("Inativa");
    });
}





