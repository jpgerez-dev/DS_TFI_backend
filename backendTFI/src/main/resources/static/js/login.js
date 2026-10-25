// Call the dataTables jQuery plugin
$(document).ready(function() {
    //on ready
});


function iniciarSesion(){
    let datos = {}
    datos.email= document.getElementById('txtEmail').value;
    datos.password= document.getElementById('txtPassword').value;

    (async ()=>{
        const request = await fetch("api/login",{
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            body: JSON.stringify(datos)
        });

        if (request.ok) {
            localStorage.token = await request.text();
            localStorage.email = datos.email;
            window.location.href='usuarios.html';
        } else {
            alert("Credenciales incorrectas, ingrese nuevamente");
        }
    })();
}
