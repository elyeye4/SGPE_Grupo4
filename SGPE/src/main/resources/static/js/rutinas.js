/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


// Rutinas generales del proyecto SGPE.

// Pide confirmación antes de enviar cualquier formulario con el atributo
// data-confirmar="texto de la pregunta".
document.addEventListener('submit', function (evento) {
    var formulario = evento.target;
    var pregunta = formulario.getAttribute('data-confirmar');

    if (pregunta && !window.confirm(pregunta)) {
        evento.preventDefault();
    }
});
