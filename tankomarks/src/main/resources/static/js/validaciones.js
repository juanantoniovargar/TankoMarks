"use strict"

var checkboxes = document.querySelectorAll(".check-cap-input");

for (var i = 0; i < checkboxes.length; i++) {
  checkboxes[i].addEventListener("change", function() {
    this.closest("form").submit();
  });
}
