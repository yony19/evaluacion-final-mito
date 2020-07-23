var image_temp = "";
$(document).ready(function () {
    if (sessionStorage.getItem('access_token') == null){
        window.location.href = "./login.html";
        return;
    }
    obtenerMedicamentos();

    document.getElementById('txtMedicamento').addEventListener('keyup', (event)=> {
        if (event.srcElement.value.length >= 4) {
            let text = event.srcElement.value;
            let myHeaders = new Headers({
                'Accept': 'application/json'
            });
            let token = sessionStorage.getItem('access_token');
            fetch(`${API_MEDICAMENTO}/medicamento/buscar?nombre=${text}&access_token=${token}`, {
                headers: myHeaders
            })
            .then(resp => {
                if (resp.ok) {
                   return resp.json();
                }
                if (resp.status == 401) {
                    sessionStorage.removeItem('access_token');
                    window.location.href = "./login.html";	
                }
            })
            .then(reponse => {
                document.getElementById('listMedicamento').innerHTML = "";
                if (reponse.length > 0) {
                    createLiElement(reponse);
                } else {
                    document.getElementById('listMedicamento').innerHTML = "";
                }
            })
            .catch((error) => {
                toastr.error("Error: " + error);
            });
        }
    });

    $("#btn-registrar").click(function () {
			
	//debe generar una estrucutra json, puede guiarse de SpringBootNota del método POST callAjax del js
	//en este caso no es necesario los token csrf;
	//var json_input = .... 
	
	//Ejemplo en español de peticions ajax con jquery método post
	//https://es.stackoverflow.com/questions/24583/enviar-post-a-php-por-medio-de-ajax
	
	//Para crear el json_input recuerde que JSON es solo un string|cadena de texto, que tiene un formato especial
	//Documentacion JSON https://www.w3schools.com/js/js_json.asp
	
    //Recurde que siempre puede probar sus servicios con POSTMAN
    if (document.querySelector("input[name=optradio]:checked") == null) {
        toastr.error("Seleccionar un precio");
        return;
    }
        
        let idFarmacia = document.querySelector("input[name=optradio]:checked").value;
        let input = {
            farmacia:{
                idFarmacia:idFarmacia
            }
        };
        let token = sessionStorage.getItem('access_token');
        $.ajax({
            type: "POST",
            url: `${API_MEDICAMENTO}/compra?access_token=${token}`,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(input),
            success: function (data) {
                //SI EL SERVICIO RETORNA EXITO EVALUAR
                if (data == '1') {
                    toastr.info("Se registró");
                } else {
                    toastr.warning("Error al registrar " + data);
                }
            },

            error: function (XMLHttpRequest, textStatus, errorThrown) {
                toastr.error("Request: " + XMLHttpRequest.toString() + "\n\nStatus: " + textStatus + "\n\nError: " + errorThrown);
            }
        });
    });    
});

function getFarmacias(medicamentoId, urlIma) {
    if (typeof urlIma != "undefined") {
        image_temp = urlIma;
    }

    let myHeaders = new Headers({
        'Accept': 'application/json'
    });
    let token = sessionStorage.getItem('access_token');
    fetch(`${API_MEDICAMENTO}/farmacia/${medicamentoId}?access_token=${token}`, {
        headers: myHeaders
    })
    .then(resp => {
        if (resp.ok) {
            return resp.json();
        }
        if (resp.status == 401) {
            sessionStorage.removeItem('access_token');
            window.location.href = "./login.html";	
        }
    })
    .then(lista => {
        if (lista.length > 0) {
            createListaPrecios(lista);
        }
    })
    .catch((error) => {
        toastr.error("Error: " + error);
    });
}

function createTemplate(HTMLString) {
    const html = document.implementation.createHTMLDocument();
    html.body.innerHTML = HTMLString;
    return html.body.children[0];
}

function ulTemplate(element)
{
    return (`<li class="autocomplete-items" data-url="${element.urlImagen}" data-id="${element.idMedicamento}">
        <img src="${element.urlImagen}" data-url="${element.urlImagen}" data-id="${element.idMedicamento}" height="40" width="40" alt="Imagen icono" />
        ${element.nombreMedicamento}
            </li>`);
}
function createLiElement(lista) {
    lista.forEach((element) => {
        const HTMLString = ulTemplate(element);
        const liElement = createTemplate(HTMLString);
        document.getElementById('listMedicamento').append(liElement);
        const image = liElement.querySelector('img');
        image.addEventListener('load', (event)=>{
          event.srcElement.classList.add('fadeIn');
        });
        addEventClick(liElement);
      });
}

function addEventClick($element) {
    $element.addEventListener('click', (event) => {
        document.getElementById('listMedicamento').innerHTML = "";
        let medicamentId = event.srcElement.getAttribute('data-id');
        image_temp = event.srcElement.getAttribute('data-url');
        getFarmacias(medicamentId);
    });
}

function obtenerMedicamentos() {
    let myHeaders = new Headers({
        'Accept': 'application/json'
    });
    let token = sessionStorage.getItem('access_token');
    fetch(`${API_MEDICAMENTO}/medicamento?access_token=${token}`, {
        headers: myHeaders
    })
    .then(resp => {
        if (resp.ok) {
            return resp.json();
        }
        if (resp.status == 401) {
            sessionStorage.removeItem('access_token');
            window.location.href = "./login.html";	
        }
    })
    .then(lista => {
        if (lista.length > 0) {
            createListaMedicamento(lista);
        }
    })
    .catch((error) => {
        toastr.error("Error: " + error);
    });
}

function createListaMedicamento(lista) {
    let html = '';
    lista.forEach((element) => {
        html += `<li>${element.nombreMedicamento}<button onclick="getFarmacias(${element.idMedicamento}, '${element.urlImagen}')" class="btn btn-xs btn-success" >Selecionar</button></li>`;
    });

    document.getElementById('listadoMedicamento').innerHTML = html;
}

function createListaPrecios(lista) {
    let html = '';
    lista.forEach((element) => {
        html += `<div class="col-xs-4">
        <img width="150px" height="150px" class="img-responsive" src="${image_temp}"
            alt="Paracetamol">
        <div class="radio">
            <label><input id="rdnPrecio1" type="radio" value="${element.idFarmacia}" name="optradio">${element.nombre_farmacia} S/. ${element.precio}</label>
        </div>
    </div>`;
    });

    document.getElementById('precioBody').innerHTML = html;
}