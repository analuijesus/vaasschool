function deactivateCategory(id) {
    $.post("/admin/deactivateCategory", {'id': id}, function () {
        $("#category_" + id).html("Inativa");
    });
}

function deactivateSubcategory(id) {
    $.post("/admin/deactivateSubcategory", {'id': id}, function () {
        $("#category_" + id).html("Inativa");
    });
}