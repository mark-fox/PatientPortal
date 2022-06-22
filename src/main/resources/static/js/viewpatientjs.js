$("#toggleBtn").click(function(event) {
    event.preventDefault();
    $.enableEdit();
});

$.enableEdit = function() {
    $("#patientFormFieldset").prop("disabled", false);
    $("#patientUpdateBtn").prop("disabled", false);
    $("#patientDeleteBtn").prop("disabled", false);
    $("#toggleBtn").prop("disabled", true);
}