function validarIp() {

    if (document.cadDispositivo.ip.value.length == 15) {
        return true;

    } else {
        alert("Ip invalido!!");
        return false;
    }
}
function validarIp2() {
    if (isNaN(document.cadDispositivo.ip.value)) {
        alert("Ip valido!!");
        return true;

    } else {
        alert("Ip invalido!!");
        return false;
    }

}


function validarJS() {
    var campo1 = document.getElementsByClassName('nomeRede');
    if (campo1.length == 1) {
        campo1.setCustomValidity('Preencha este campo corretamente.');
    }


}



function atualiza(){
    window.location.href = 'usuario.jsp';
}

