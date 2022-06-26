// Event listener for Enable Editing button
$("#toggleBtn").click(function(event) {
    event.preventDefault();
    $.enableEdit();
});

// Method for enabling fields for updating a Patient
$.enableEdit = function() {
    $("#patientFormFieldset").prop("disabled", false);
    $("#patientUpdateBtn").prop("disabled", false);
    $("#patientDeleteBtn").prop("disabled", false);
    $("#toggleBtn").prop("disabled", true);
}