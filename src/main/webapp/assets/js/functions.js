
$(document).ready(function () {
    if ($('#tablax').length > 0) {
        $('#tablax').DataTable({
            language: {
                processing: "Tratamiento en curso...",
                search: "Buscar&nbsp;:",
                lengthMenu: "Agrupar de _MENU_ items",
                info: "Mostrando del item _START_ al _END_ de un total de _TOTAL_ items",
                infoEmpty: "No existen datos.",
                infoFiltered: "(filtrado de _MAX_ elementos en total)",
                infoPostFix: "",
                loadingRecords: "Cargando...",
                zeroRecords: "No se encontraron datos con tu busqueda",
                emptyTable: "No hay datos disponibles en la tabla.",
                paginate: {
                    first: "Primero",
                    previous: "Anterior",
                    next: "Siguiente",
                    last: "Ultimo"
                },
                aria: {
                    sortAscending: ": active para ordenar la columna en orden ascendente",
                    sortDescending: ": active para ordenar la columna en orden descendente"
                }
            },
            scrollY: 400,
            lengthMenu: [[5, 10, 25, -1], [5, 10, 25, "All"]],
        });
    }

});


function showConfirm(config, callback = null)
{
    swal({
        title: config.title,
        text: config.msg,
        type: config.type,
        showCancelButton: true,
        cancelButtonText: config.cancelText ? config.cancelText : "Cancelar",
        confirmButtonColor: "#DD6B55",
        confirmButtonText: config.confirmText ? config.confirmText : "Cancelar",
        closeOnConfirm: true
    },
            function () {
                if ($.isFunction(callback)) {
                    callback();
                }
            });

}


function showInfo(type, title, msg, callback = null)
{
    swal({
        title: title,
        text: msg,
        type: type,
        showCancelButton: false,
        cancelButtonText: "Cancelar",
//        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Aceptar",
        closeOnConfirm: true
    },
            function () {
                if ($.isFunction(callback)) {
                    callback();
                }
            });

}
/*jQuery(document).ready(function () {
 
 jQuery(".msg-basico").click(function () {
 swal("Texto del mensaje");
 });
 
 jQuery(".msg-basico-txt").click(function () {
 swal("Texto del título", "Texto del mensaje inferior");
 });
 
 jQuery(".msg-exito").click(function () {
 swal("¡Bien!", "Has hecho clic en el botón :)", "success");
 });
 
 jQuery(".msg-warning").click(function () {
 swal({
 title: "¿Seguro que deseas continuar?",
 text: "No podrás deshacer este paso...",
 type: "warning",
 showCancelButton: true,
 cancelButtonText: "Mmm... mejor no",
 confirmButtonColor: "#DD6B55",
 confirmButtonText: "¡Adelante!",
 closeOnConfirm: false},
 function () {
 swal("¡Hecho!",
 "Acabas de vender tu alma al diablo.",
 "success");
 });
 
 });
 
 jQuery(".msg-cond").click(function () {
 swal({
 title: "¿Deseas unirte al lado oscuro?",
 text: "Este paso marcará el resto de tu vida...",
 type: "warning",
 showCancelButton: true,
 confirmButtonColor: "#DD6B55",
 confirmButtonText: "¡Claro!",
 cancelButtonText: "No, jamás",
 closeOnConfirm: false,
 closeOnCancel: false},
 function (isConfirm) {
 if (isConfirm) {
 swal("¡Hecho!",
 "Ahora eres uno de los nuestros",
 "success");
 } else {
 swal("¡Gallina!",
 "Tu te lo pierdes...",
 "error");
 }
 });
 });
 
 jQuery(".msg-autoclose").click(function () {
 swal({
 title: "Mensaje con cierre automático",
 text: "Se cerrará en 3 segundos.",
 timer: 3000,
 showConfirmButton: false
 });
 
 });
 
 
 });
 */