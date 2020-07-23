
$(document).ready(function () {
    if (sessionStorage.getItem('access_token') != null){
        window.location.href = "./index.html";
        return;
    }
    document.getElementById('btn-login').addEventListener('click', () => {

        let usuario = document.getElementById("usuario").value;
        let clave = document.getElementById("password").value;

        let encoded = window.btoa("mito:mitosecret");

        let body = {
            "grant_type": "password",
            "client_id": "mito",
            "username": usuario,
            "password": clave
        }

        $.ajax({
            type: "POST",
            url: `${API_AUTH}/oauth/token`,
            contentType: "application/x-www-form-urlencoded",
            dataType: "json",									
            headers: {
                "Authorization": "Basic " + encoded					
            },
            data: body,
            success: function (data) {
                let token = data.access_token;
                sessionStorage.setItem("access_token", token);
                window.location.href = "./index.html";	
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                toastr.error("Ocurrio un error al iniciar session.");
            }
        });
    });
});


