console.log("js file reached");

$("#toggleBtn").click(function(event) {
    event.preventDefault();
    $.enableEdit();
    // $("input").toggle();
    // $("#patientUpdateBtn").toggle();
})

// $("#patientDeleteBtn").click(function(event) {
//     // event.preventDefault();
//     console.log("delete button pressed");
// })

$.enableEdit = function() {
    $("#patientForm input").toggle();
    $("#patientUpdateBtn").toggle();
    $("select").toggle();
}

$(document).ready($.enableEdit());