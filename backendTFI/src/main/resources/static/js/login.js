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
            headers: getHeader(),
            body: JSON.stringify(datos)
        });

        const response = await request.text();


        if(response != "Fail"){
            localStorage.token = response;
            localStorage.email = datos.email;
            window.location.href='usuarios.html';
        }else {
            alert("Credenciales incorrectas, ingrese nuevamente");
        }



    })();
}




function getHeader(){
    return {
        'Accept': 'application/Json',
        'Content-type': 'application/Json',
        'Authorization': localStorage.token
    }

}
