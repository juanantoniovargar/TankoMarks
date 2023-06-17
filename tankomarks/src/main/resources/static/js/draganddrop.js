"use strict"

let elementoArrastrado;

let imagenes = document.getElementsByClassName("busqueda-imagen");
let zonas = document.getElementsByClassName("zonaParaSoltar");

for (let imagen of imagenes) {
    imagen.addEventListener("dragstart", iniciandoArrastre);
    imagen.addEventListener("dragend", terminando);
}

for (let zona of zonas) {
    zona.addEventListener("dragenter", entrandoZona);
    zona.addEventListener("dragover", habilitaSoltar); 
    zona.addEventListener("drop", soltando);
}

function iniciandoArrastre(e) { 
    console.log(`Inicio el arrastre del elemento: ${e.target.id}`);
    elementoArrastrado = e.target; 
}

function entrandoZona(e) { 
    console.log(`Entrando en el elemento: ${e.target.id}`);
}

function habilitaSoltar(e) { 
    console.log(`Habilito soltar en el elemento: ${e.target.id}`);
    e.preventDefault();
}

function soltando(e) {
    console.log(`Soltando en el elemento: ${e.target.id}`);
    e.preventDefault(); 
    e.target.appendChild(elementoArrastrado);
}

function terminando(e) {
    console.log(`Terminando con el elemento: ${e.target.id}`);
}
