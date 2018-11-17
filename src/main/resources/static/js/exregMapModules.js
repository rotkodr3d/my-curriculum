$(document).ready(function () {
    //TODO
});

var semesterCounter = 0;
var listOfUnmappedModules = [];

/**
 * Creates a new semester panel and adds it to the list.
 */
function addNewSemester() {
    semesterCounter++;

    var semester = $('<div></div>', {
        draggable: true,
        "class": "card"
    });

    var semesterBody = $('<div></div>', {
        id: "semesterbody" + semesterCounter,
        "class": "card-body card-deck",
        on: {
            dragover: function (event) {
                event.preventDefault();
            },
            drop: function (event) {
                event.preventDefault();
                //wonky hack because jQuery only passes the jQuery event object instead of the browser event object
                event.dataTransfer = event.originalEvent.dataTransfer;
                var draggedElementId = event.dataTransfer.getData("text");
                $('#' + draggedElementId).detach().appendTo(this);
            }
        }
    });

    var semesterTitle = $('<div></div>', {
        "class": "card-header",
        text: semesterCounter + ". Semester",
        on: {
            dragover: function (event) {
                event.preventDefault();
            },
            drop: function (event) {
                event.preventDefault();
                //wonky hack because jQuery only passes the jQuery event object instead of the browser event object
                event.dataTransfer = event.originalEvent.dataTransfer;
                var draggedElementId = event.dataTransfer.getData("text");
                $('#' + draggedElementId).detach().appendTo(semesterBody);
            }
        }
    });

    semester.append(semesterTitle);
    semester.append(semesterBody);
    $("#semesterContainer").append(semester);
}

function allowDrag(event) {
    event.preventDefault();
}

function moduleListDrop(event) {
    event.preventDefault();
    var moduleId = event.dataTransfer.getData("text");
    $('#' + moduleId).detach().appendTo($('#unmappedModulesList'));
}

function drag(event) {
    event.dataTransfer.setData("text", event.target.id);
}

function drop(event) {
    event.preventDefault();
    var data = event.dataTransfer.getData("text");
    var element = document.getElementById(data);

    if (event.target === document.getElementById("div1")) {
        event.target.appendChild(element);
    } else if (event.target === document.getElementById("list")) {
        event.target.appendChild(element);
    } else {
        return;
    }
}
