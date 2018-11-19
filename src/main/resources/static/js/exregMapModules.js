$(document).ready(function () {
    //TODO
});

var semesterCounter = 1;
var listOfUnmappedModules = [];
var delBtnSemesterId = {};//object which maps the delete button ids to semester id
var listOfSemester = {};//object which maps the semester id to the semesterCounter

/**
 * Creates a new semester panel and adds it to the list.
 */
function addNewSemester() {
    if (semesterCounter < 1) {
        semesterCounter = 1;
    }
    var semesterId = "semester" + semesterCounter;
    var delBtnId = "deleteButton" + semesterCounter;
    var spanId = "semesterSpan" + semesterCounter;
    var semesterBodyId = "semesterbody" + semesterCounter;
    delBtnSemesterId[delBtnId] = semesterId;
    listOfSemester[semesterId] = {
        "semesterNr": semesterCounter,
        "delBtn": delBtnId
    };

    var semester = $('<div></div>', {
        draggable: true,
        "class": "card",
        "id": semesterId
    });

    var semesterBody = $('<div></div>', {
        id: semesterBodyId,
        "class": "card-body card-deck modulesArea",
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
            },
            drag: function (event) { //child has the drag function because parent has no clickable area
            }
        }
    });

    var semesterTitle = $('<div></div>', {
        "class": "card-header",
        html: "<span id=" + spanId + ">" + semesterCounter + ". Semester</span>",
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

    $('<button></button>', {
        "class": "btn btn-danger",
        "id": delBtnId,
        on: {
            click: function (obj) {
                var idToDelete = delBtnSemesterId[obj.target.id];
                var semesterToDelete = listOfSemester[idToDelete];
                var lastSemester = $("#semesterContainer").children(":last");
                var modules = {};
                var modulesLast = null;

                for (var i = semesterCounter - 1; i >= semesterToDelete.semesterNr; i--) {
                    var nextSemester = 1;
                    var idThis = "#semester" + i;

                    modules = $(idThis).find("div.module");
                    if (i > semesterToDelete.semesterNr) {
                        if (modulesLast == null) {
                            $(idThis).find("div.modulesArea").empty();
                            modulesLast = modules;
                        } else {
                            $(idThis).find("div.modulesArea").empty().append(modulesLast);
                            modulesLast = modules;
                        }
                    }
                    else if (i == semesterToDelete.semesterNr) {
                        $("#unmappedModulesList").append(modules);
                        $(idThis).find("div.modulesArea").append(modulesLast);
                    }
                }

                lastSemester.remove();
                semesterCounter--;
            }
        }
    }).append($('<i></i>', {
            "class": "fa fa-trash"
        })
    ).appendTo(semesterTitle);

    semester.append(semesterTitle);
    semester.append(semesterBody);
    $("#semesterContainer").append(semester);
    semesterCounter++;
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
