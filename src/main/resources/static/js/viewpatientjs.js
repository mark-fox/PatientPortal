console.log("js file reached");
const allInputs = document.querySelectorAll("input");
const editBtn = document.querySelector("#toggleBtn");

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

    // if (editBtn.value === "true") {
    //     allInputs.readOnly = false;
    //     editBtn.value = false;
    // } else {
    //     allInputs.readOnly = true;
    //     editBtn.value = true;
    // }

     // document.getElementsByTagName("input");
    // console.log(document.querySelector("#firstNameInput").readOnly);
    // console.log($("#patientForm input").readOnly);
    // const x = $("#firstNameInput");
    // if (x.readOnly == true) {
    //     x.readOnly = false;
    // } else {
    //     x.readOnly = false;
    // }
    $("#patientUpdateBtn").toggle();
    $("select").toggle();
}

// $(document).ready($.enableEdit());