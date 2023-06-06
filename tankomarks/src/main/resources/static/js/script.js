"use strict"

// show edit username form
$(".boton-perfil").click(function(){
    $(".form-perfil").show(1000);
});

$(".boton-perfil").click(function(){
    $(".boton-perfil").hide(1000);
});

// confirmacion eliminar
function adminEliminar(id_manga) {
    swal({
        title: "¿Estás seguro?",
        text: "Una vez eliminado, no podrás recuperar el manga.",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
    .then((OK) => {
        if (OK) {
            $.ajax({
                url:"/adminEliminar/" + id_manga,
                success: function(res) {
                    console.log(res);
                }
            })
            swal("El manga se ha eliminado correctamente.", {
                icon: "success",
            }).then((ok) => {
                if (ok) {
                    location.href="/administracion";
                }
            });
        } else {
            swal("El manga no se eliminará.");
        }
    });
}