"use strict"

const toggle = document.querySelector('.toggle');
const links = document.querySelectorAll('.link-nav, .busqueda-form, .user-nav, .logout-nav, nav, .toggle, .add-admin');

toggle.addEventListener('click', () => {
  links.forEach(link => {
    link.classList.toggle('show');
  });
}); 
