function validarSetorDispositivo() {

    var setor = document.getElementById("setor_id").value;


    if (setor === "Selecione")
    {

        document.getElementById("setor_id").setCustomValidity("Selecione um setor!!");

        return false;
    } else {
        document.getElementById("setor_id").setCustomValidity("");
    }
    return true;

}






