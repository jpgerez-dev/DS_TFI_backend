// Call the dataTables jQuery plugin
$(document).ready(function() {
   //on ready
});


function registarUsuarios(){
    let datos = {}
        datos.nombre = document.getElementById('txtNombre').value;
        datos.apellido= document.getElementById('txtApellido').value;
        datos.email= document.getElementById('txtEmail').value;
        datos.password= document.getElementById('txtPassword').value;
        let passwordRepeat= document.getElementById('txtRepeatPassword').value;

        if(passwordRepeat != datos.password){
            alert('La contraseñas no coinciden');
            return

        }


    (async ()=>{
        const request = await fetch("api/usuarios",{
            method: 'POST',
            headers:{
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            body: JSON.stringify(datos)
        });

        if (request.ok) {
            alert('Cuenta creada con Exito!');
            window.location.href='login.html';
        } else if (request.status === 409) {
            alert('Ese email ya está registrado');
        } else {
            alert('No se pudo crear la cuenta, revise los datos e intente nuevamente');
        }
    })();
}
