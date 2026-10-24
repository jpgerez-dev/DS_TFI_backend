
// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#usuarios').DataTable();
    cargarUsuarios();
    actualizarUsuario();
});


function actualizarUsuario(){
    document.getElementById('txt-email-usuario').outerHTML= localStorage.email;
}




function cargarUsuarios(){

  (async ()=>{
    const request = await fetch("api/usuarios",{
      method: 'GET',
      headers:getHeader()
    });





    const usuarios = await request.json();

    let listadohtml= '';

    for (let usuario of usuarios) {
        const telefono = usuario.telefono == null ? '-' : usuario.telefono;

        const btnEliminar = '<a href=\"#\" class=\"btn btn-danger btn-circle btn-sm\" onclick="eliminarUsuario(' + usuario.id + ')">\n<i class=\"fas fa-trash\"></i>\n</a>';
        let btnModificar = '<a href="#" class="btn btn-success btn-circle btn-sm" onclick="modificarUsuario('+usuario.id + usuario.nombre+')">\n<i class="fa-solid fa-pen-to-square"></i>\n</a>';
        let usuarioHtml = `<tr><td>${usuario.id}</td><td>${usuario.nombre} ${usuario.apellido}</td><td>${usuario.email}</td><td>${telefono}</td>
<td>
${btnEliminar}
${btnModificar}
</td>
</tr>`

        listadohtml += usuarioHtml;

    }
    console.log(usuarios);
    document.querySelector('#usuarios tbody').outerHTML = listadohtml;

  })();
}





function getHeader(){
    return {
        'Accept': 'application/Json',
        'Content-type': 'application/Json',
        'Authorization': localStorage.token
    }

}



function  eliminarUsuario (id){

    if(!confirm('Desea Borrar?')){
        return
    }

    (async ()=>{
        const request = await fetch("api/usuarios/eliminar/" +id,{
            method: 'DELETE',
            headers: getHeader()
        });

    })();
    console.log('entramos en eliminar usuario');

    window.location.reload(false);
}


function modificarUsuario(id,nombre){

    console.log(nombre);

    // window.location.href ="./modificarUsuario.html";
    //modificarUsuarioActual(id);

}