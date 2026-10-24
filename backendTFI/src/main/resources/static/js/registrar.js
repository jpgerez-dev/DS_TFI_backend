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
                'Accept': 'application/Json',
                'Content-type': 'application/Json'
            },
            body: JSON.stringify(datos)
        });

        alert('Cuenta creada con Exito!');
        window.location.href='login.html';



    })();
}

