console.log("js file reached");

$("#toggleBtn").click(function(event) {
    event.preventDefault();
    $.enableEdit();
    // $("input").toggle();
    // $("#patientUpdateBtn").toggle();
})

$.enableEdit = function() {
    $("input").toggle();
    $("#patientUpdateBtn").toggle();
}

$(document).ready($.enableEdit());