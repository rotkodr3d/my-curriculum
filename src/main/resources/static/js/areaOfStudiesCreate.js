/**
 * 
 *

function submitData(event) {
    console.log($("#colorInput").val());
    checkInputData();
    if (checkInputData()) {
        $("#areaOfStudiesForm").submit();
    } else {
        $("#colorInput").addClass("is-invalid");    
    }
}

function checkInputData() {
    var colorInputVal = $("#colorInput").val();
    colorInputVal = colorInputVal.replace("#","");
    colorInputVal = parseInt(colorInputVal,16);
    
    existingAreaOfStudiesList.forEach(function (existingAreaOfStudies) {
        var existingColor = existingAreaOfStudies.color;
        
        if (colorInputVal > (existingColor + 50) || colorInputVal < (existingColor - 50)) {
            return colorInputVal;
        } else {
            return -1;
        }
    });
}*/

