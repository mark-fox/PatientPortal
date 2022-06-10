console.log("js file reached");

$("#toggleBtn").click(function(event) {
    event.preventDefault();
    $("input").toggle();
})