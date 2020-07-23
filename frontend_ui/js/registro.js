
$(document).ready(function () {
    if (sessionStorage.getItem('access_token') == null){
        window.location.href = "./login.html";
        return;
    }
    //Se ejecutará apenas cargue la página
    //alert("CARGUE");
	
    let token = sessionStorage.getItem('access_token');
    $.ajax({
        type: "GET",
        url: `${API_MEDICAMENTO}/compra?access_token=${token}`,
        contentType: "application/json; charset=utf-8",
		//data : JSON.stringify(json_input),
        dataType: "json",
        success: function (data) {
            let html = '';
            data.forEach(element => {
                html += `<li><button>#${element.idCompra}, ${element.farmacia.medicamento.nombreMedicamento} - S/. ${element.farmacia.precio}</button></li>`;
            });
            document.getElementById('registrosList').innerHTML = html;
            //HACER ALGO CON LA DATA
            //IMPRIMIR EN ul-li o como guste
        },

        error: function (XMLHttpRequest, textStatus, errorThrown) {
            toastr.error("Request: " + XMLHttpRequest.toString() + "\n\nStatus: " + textStatus + "\n\nError: " + errorThrown);
        }
    });

});