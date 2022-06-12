console.log("js file reached");

$("#toggleBtn").click(function(event) {
    event.preventDefault();
    $.enableEdit();
    // $("input").toggle();
    // $("#patientUpdateBtn").toggle();
})

$.enableEdit = function() {
    $("#patientForm input").toggle();
    $("#patientUpdateBtn").toggle();
    $("select").toggle();
}

$(document).ready($.enableEdit());