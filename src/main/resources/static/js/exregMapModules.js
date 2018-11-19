/**
 * List of modules that already exist in the DB.
 */
var existingModulesMap = [];
/**
 * List of module stubs that are dynamically created by the user.
 */
var moduleStubs = [];
/**
 * Id Counter for the created module Stubs.
 */
var nextStubId = 1;
var semesterCounter = 1;
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
    //in case the event is a jQuery object and not the Browser object
    if (event.dataTransfer === undefined) {
        event = event.originalEvent;
    }
    event.dataTransfer.setData("text", event.target.id);
}

function createStub() {
    var stub = {
        code: $('#stub_code').val(),
        title: $('#stub_title').val(),
        lecturers: $('#stub_lecturers').val(),
        stubId: nextStubId
    };
    moduleStubs[nextStubId] = stub;
    nextStubId++;

    $('#createModuleStub').modal('hide');
    $('#stubForm').trigger('reset');
    addStub(stub);
}

/**
 * Creates the card for the new stub.
 */
function addStub(stub) {
    $('<div></div>', {
        "class": "card bg-primary text-white module",
        id: "modulelist_stub" + stub.stubId,
        draggable: true,
        on: {
            dragstart: drag
        }
    }).append(
        $('<div></div>', {
            "class": "card-header text-truncate",
            html: stub.code + ' ' + stub.title
        })
    ).append(
        $('<div></div>', {
            "class": "card-body",
            html: stub.lecturers
        })
    ).appendTo($('#unmappedModulesList'));
}

$(document).ready(function () {
    listOfUnmappedModules.forEach(function (module) {
        existingModulesMap[module.id] = module;
    });
});
