function displayForm() {

    document.body.innerHTML =
        '<label for="name">Nome:</label><br>' +
        '<input type="text" id="name" name="name" value="${category.name}"/><br><br>' +

        '<label for="code">Código:</label><br>' +
        '<input type="text" id="code" name="code" value="${category.code}"/><br><br>' +

        '<label for="description">Descrição:</label><br>' +
        '<input type="textarea" id="description" name="description" value=${category.description}"/><br><br>' +

        '<label for="explanatoryGuide">Guia:</label><br>' +
        '<input type="text" id="explanatoryGuide" name="explanatoryGuide" value="${category.explanatoryGuide}"/><br><br>' +

        '<label>Categoria Ativa: </label><br>' +
        '<input type="radio" name="active" value="true"> Sim ' +
        '<input type="radio" name="active" value="false"> Não <br><br>' +

        '<label for="order">Ordem de visualização:</label><br>' +
        '<input type="number" id="order" name="order" min="1" onInput="validity.valid||(value="${category.order}")"><br><br>' +

        '<label for="imagePath">Link do icone:</label><br>' +
        '<input type="text" id="imagePath" name="imagePath" value="${category.imagePath}"/><br><br>' +

        '<label for="colorCode">Cor (Código): </label><br>' +
        '<input type="text" id="colorCode" name="colorCode" value="${category.colorCode}"/><br><br>' +

        '<input type="submit">'

}


