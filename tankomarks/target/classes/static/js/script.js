"use strict"

// show edit username form
$(".boton-perfil").click(function(){
    $(".form-perfil").show(1000);
});

$(".boton-perfil").click(function(){
    $(".boton-perfil").hide(1000);
});

// confirmacion eliminar manga administracion
function adminEliminar(id_manga) {

    let currentPageURL = window.location.href;

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
                    location.href = currentPageURL;
                }
            });
        } else {
            swal("El manga no se eliminará.");
        }
    });
}

// confirmacion eliminar tomo administracion
function adminEliminarTomo(id_tomo) {

    let currentPageURL = window.location.href;

    swal({
        title: "¿Estás seguro?",
        text: "Una vez eliminado, no podrás recuperar el tomo.",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
    .then((OK) => {
        if (OK) {
            $.ajax({
                url:"/adminEliminarTomo/" + id_tomo,
                success: function(res) {
                    console.log(res);
                }
            })
            swal("El tomo se ha eliminado correctamente.", {
                icon: "success",
            }).then((ok) => {
                if (ok) {
                    location.href = currentPageURL; // "/adminTomos/" + id_manga
                }
            });
        } else {
            swal("El tomo no se eliminará.");
        }
    });
}

// confirmacion eliminar capitulo administracion
function adminEliminarCapitulo(id_capitulo) {

    let currentPageURL = window.location.href;

    swal({
        title: "¿Estás seguro?",
        text: "Una vez eliminado, no podrás recuperar el capítulo.",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
    .then((OK) => {
        if (OK) {
            $.ajax({
                url:"/adminEliminarCapitulo/" + id_capitulo,
                success: function(res) {
                    console.log(res);
                }
            })
            swal("El capítulo se ha eliminado correctamente.", {
                icon: "success",
            }).then((ok) => {
                if (ok) {
                    location.href = currentPageURL; 
                }
            });
        } else {
            swal("El capítulo no se eliminará.");
        }
    });
}
