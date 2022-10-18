function validarDispositivoOS() {

    var dispositivo = document.getElementById("idD").value;


    if (dispositivo === "Selecione")
    {

        document.getElementById("idD").setCustomValidity("Selecione um dispositivo!!");

        return false;
    } else {
        document.getElementById("idD").setCustomValidity("");
    }
    return true;

}

function validarTipoOS() {

    var tipoOS = document.getElementById("idT").value;


    if (tipoOS === "Selecione")
    {

        document.getElementById("idT").setCustomValidity("Selecione um tipo de serviço!");

        return false;
    } else {
        document.getElementById("idT").setCustomValidity("");
    }
    return true;

}



function validarProdutos() {

    var produto = document.getElementById("idP").value;
    var qtd = document.getElementById("quantidade").value;

    if (produto !== "Selecione")
    {
        if (qtd === "0") {
            document.getElementById("quantidade").setCustomValidity("Selecione a quantidade de produtos!");
            return false;
        } else {
            document.getElementById("quantidade").setCustomValidity("");
        }
    }
    return true;

}



