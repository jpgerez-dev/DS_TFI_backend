
// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#usuarios').DataTable();
    cargarUsuarios();
    actualizarUsuario();
});


// Evita inyección de HTML (XSS) al mostrar datos cargados por los usuarios
function esc(valor){
    return String(valor ?? '').replace(/[&<>"']/g, c => ({
        '&':'&amp;', '<':'&lt;', '>':'&gt;', '"':'&quot;', "'":'&#39;'
    }[c]));
}


function actualizarUsuario(){
    document.getElementById('txt-email-usuario').textContent = localStorage.email || '';
}


function cargarUsuarios(){

  (async ()=>{
    const request = await fetch("api/usuarios",{
      method: 'GET',
      headers:getHeader()
    });

    // Sin sesión válida: volver al login
    if (request.status === 401) {
        localStorage.clear();
        window.location.href = 'login.html';
        return;
    }

    const usuarios = await request.json();

    let listadohtml= '';

    for (let usuario of usuarios) {
        const telefono = usuario.telefono == null ? '-' : usuario.telefono;

        const btnEliminar = '<a href="#" class="btn btn-danger btn-circle btn-sm" onclick="eliminarUsuario(' + Number(usuario.id) + ')">\n<i class="fas fa-trash"></i>\n</a>';
        const btnModificar = '<a href="#" class="btn btn-success btn-circle btn-sm" onclick="modificarUsuario(' + Number(usuario.id) + ')">\n<i class="fa-solid fa-pen-to-square"></i>\n</a>';
        let usuarioHtml = `<tr><td>${esc(usuario.id)}</td><td>${esc(usuario.nombre)} ${esc(usuario.apellido)}</td><td>${esc(usuario.email)}</td><td>${esc(telefono)}</td>
<td>
${btnEliminar}
${btnModificar}
</td>
</tr>`

        listadohtml += usuarioHtml;

    }
    document.querySelector('#usuarios tbody').outerHTML = listadohtml;

  })();
}


function getHeader(){
    return {
        'Accept': 'application/json',
        'Content-type': 'application/json',
        'Authorization': localStorage.token
    }

}


function eliminarUsuario(id){

    if(!confirm('Desea Borrar?')){
        return
    }

    (async ()=>{
        const request = await fetch("api/usuarios/eliminar/" + id,{
            method: 'DELETE',
            headers: getHeader()
        });

        if (request.status === 401) {
            localStorage.clear();
            window.location.href = 'login.html';
            return;
        }
        // Recargar recién cuando el servidor terminó de borrar
        window.location.reload(false);
    })();
}


function modificarUsuario(id){
    // Pendiente: la modificación de usuarios todavía no está implementada
    console.log('Modificar usuario', id);
}
