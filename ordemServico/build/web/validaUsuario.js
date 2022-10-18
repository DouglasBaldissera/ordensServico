function validarSenha() {

    var senha1 = document.getElementById("senha").value;


    var senha2 = document.getElementById("confirmaSenha").value;

    if (senha1 !== senha2)
    {

        document.getElementById("senha").setCustomValidity("As senhas não são iguais!! ");


        return false;
    } else {
        document.getElementById("senha").setCustomValidity("");
    }
    return true;

}


function validarSetorUsuario() {
    var dis = document.getElementById("idS").value;

    if (dis === "Selecione") {

        document.getElementById("idS").setCustomValidity("Selecione um setor.");

        return false;
    } else {
        document.getElementById("idS").setCustomValidity("");


    }
    return true;

}

function validarTipoUsuario() {
    var dis = document.getElementById("tipoUsuario").value;

    if (dis === "0") {
        document.getElementById("tipoUsuario").setCustomValidity("Selecione um tipo de usuário!");
        return false;
    } else {
        document.getElementById("tipoUsuario").setCustomValidity("");
    }

    return true;
}


