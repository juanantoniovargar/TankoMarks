"use strict"

const toggle = document.querySelector('.toggle');
const links = document.querySelectorAll('.link-nav, .busqueda-form, .user-nav, .logout-nav, nav, .toggle');

toggle.addEventListener('click', () => {
  links.forEach(link => {
    link.classList.toggle('show');
  });
}); 
