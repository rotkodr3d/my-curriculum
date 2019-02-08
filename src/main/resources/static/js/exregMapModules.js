/**
 * List of modules that already exist in the DB.
 */
let existingModulesMap = [];
/**
 * List of module stubs that are dynamically created by the user.
 */
let moduleStubs = [];
/**
 * Id Counter for the created module Stubs.
 */
let nextStubId = 1;
/**
 * Counter which keeps the number of the next semester.
 * It's also used to create the semester IDs.
 */
let semesterCounter = 1;
let delBtnSemesterId = {};//object which maps the delete button ids to semester id
let listOfSemester = {};//object which maps the semester id to the semesterCounter

/**
 * "Dummy Module" that acts as a placeholder to show the user where to drop the module.
 */
let dragAndDropModulePlaceholder = $('<div></div>', {
    "class": "card text-muted dragAndDropPlaceholder",
    on: {
        dragover: function (event) {
            event.preventDefault();
        },
        drop: function (event) {
            event.preventDefault();
            //because jQuery only passes the jQuery event object instead of the browser event object
            event.dataTransfer = event.originalEvent.dataTransfer;
            let draggedElementId = event.dataTransfer.getData("text");
            testAndSetPlaceholder(draggedElementId);
            $('#' + draggedElementId).detach().appendTo(this.parent());
            this.remove();
        }
    }
}).append($('<div></div>', {
    "class": "card-header bg-white text-truncate placeholderText",
    text: dropYourModuleHere
}));

/**
 * Creates a new semester panel and adds it to the list.
 */
function addNewSemester() {
    if (semesterCounter < 1) {
        semesterCounter = 1;
    }
    let semesterId = "semester" + semesterCounter;
    let delBtnId = "deleteButton" + semesterCounter;
    let spanId = "semesterSpan" + semesterCounter;
    let semesterBodyId = "semesterbody" + semesterCounter;
    delBtnSemesterId[delBtnId] = semesterId;
    listOfSemester[semesterId] = {
        "semesterNr": semesterCounter,
        "delBtn": delBtnId
    };

    var semester = $('<div></div>', {
        draggable: true,
        "class": "card semester",
        "id": semesterId,
        draggable: true,
        on: {
            dragover: function (event) {
                event.preventDefault();
            },
            drop: function (event) {
                event.preventDefault();
                //because jQuery only passes the jQuery event object instead of the browser event object
                event.dataTransfer = event.originalEvent.dataTransfer;
                let draggedElementId = '#' + event.dataTransfer.getData("text");

                if ($(draggedElementId).hasClass("semester")) {
                    let targetElementId = '#' + $(event.target).parents("div.semester").attr("id");

                    if (draggedElementId != event.target.id) {
                        let modulesAreaDragged = $(draggedElementId).find("div.modulesArea");
                        let modulesAreaTarget = $(targetElementId).find("div.modulesArea");
                        let modulesDragged = modulesAreaDragged.find("div.module");
                        let modulesTarget = modulesAreaTarget.find("div.module");
                        modulesDragged.detach().appendTo(modulesAreaTarget);
                        modulesTarget.detach().appendTo(modulesAreaDragged);
                        checkPlaceholder(modulesAreaDragged);
                        checkPlaceholder(modulesAreaTarget);
                    }
                }
            },
            dragstart: drag
        }
    });

    /**
     * Declaration of the semester body, here the modules
     * will be appended when the user drops modules on the semester.
     */
    let semesterBody = $('<div></div>', {
        id: semesterBodyId,
        "class": "card-body modulesArea px-2 pt-2 pb-0",
        on: {
            dragover: function (event) {
                event.preventDefault();
            },
            drop: function (event) {
                event.preventDefault();
                //because jQuery only passes the jQuery event object instead of the browser event object
                event.dataTransfer = event.originalEvent.dataTransfer;
                let draggedElementId = event.dataTransfer.getData("text");

                if ($('#' + draggedElementId).hasClass("semester")) {
                    return;
                }

                testAndSetPlaceholder(draggedElementId);
                //semesterBody.find('.dragAndDropPlaceholder').remove();
                $(this).find('.dragAndDropPlaceholder').remove();
                $('#' + draggedElementId).detach().appendTo(this);
            },
            drag: function (event) { //child has the drag function because parent has no clickable area (stub for later use)
            }
        }
    }).append(dragAndDropModulePlaceholder.clone());

    /**
     * Construction of the semester title.
     * Also the drag and drop logic for the title gets added (see dragover and drop).
     */
    let semesterTitle = $('<div></div>', {
        "class": "card-header",
        html: "<span id=" + spanId + ">" + semesterCounter + ". Semester</span>",
        on: {
            dragover: function (event) {
                event.preventDefault();
            },
            /**
             * When the user drops modules on the title the module will be appended on its body
             */
            drop: function (event) {
                event.preventDefault();
                //because jQuery only passes the jQuery event object instead of the browser event object
                event.dataTransfer = event.originalEvent.dataTransfer;
                let draggedElementId = event.dataTransfer.getData("text");

                if ($('#' + draggedElementId).hasClass("semester")) {
                    return;
                }

                testAndSetPlaceholder(draggedElementId);
                semesterBody.find('.dragAndDropPlaceholder').remove();
                $('#' + draggedElementId).detach().appendTo(semesterBody);
            }
        }
    });

    $('<button></button>', {
        "class": "btn btn-danger py-0 px-2 float-right",
        "id": delBtnId,
        on: {
            /**
             * Delete functionality, moves all modules from the semester to delete to the unmapped modules list
             * and moves all modules from the higher semesters one semester down until the semester to delete has modules from its
             * following semester.
             *
             * E.g We have 4 semesters and want to delete the 2nd. The elements from the 2nd semester will be moved to the unmapped modules list
             * and the modules from the 4th semester will be move to the 3rd and the modules from the 3rd will be moved to the 2nd semester.
             * The 4th semester which is empty now, gets deleted.
             */
            click: function (event) {
                /*
                 * Browser issue: When the span character is clicked in Chrome: Event target is a span
                 * In Firefox it is the button.
                 */
                let idToDelete = delBtnSemesterId[event.target.id || $(event.target).parent().attr('id') || $(event.target).parent().parent().attr('id')];
                let semesterToDelete = listOfSemester[idToDelete];
                let lastSemester = $("#semesterContainer").children(":last");
                let modules = {};
                let modulesLast = null;

                for (let i = semesterCounter - 1; i >= semesterToDelete.semesterNr; i--) {
                    let idThis = "#semester" + i;
                    let modulesArea = $(idThis).find("div.modulesArea");

                    modules = $(idThis).find("div.module");
                    if (i > semesterToDelete.semesterNr) {
                        if (modulesLast == null) {
                            modulesArea.empty();
                            modulesLast = modules;
                        } else {
                            modulesArea.empty().append(modulesLast);
                            modulesLast = modules;
                        }
                    }
                    else if (i == semesterToDelete.semesterNr) {
                        $("#unmappedModulesList").append(modules);
                        modulesArea.empty();
                        modulesArea.append(modulesLast);
                    }
                    if (modulesArea.children().length === 0) {
                        modulesArea.append(dragAndDropModulePlaceholder.clone());
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

/**
 * Drop logic for the unmapped modules list.
 */
function moduleListDrop(event) {
    event.preventDefault();
    let idDataTransfer = '#' + (event.dataTransfer.getData("text"));
    if ($(idDataTransfer).hasClass("semester")) {
        let modulesArea = $(idDataTransfer).find("div.modulesArea");
        let modules = $(idDataTransfer).find("div.module");
        modules.detach().appendTo($("#unmappedModulesList"));
        checkPlaceholder(modulesArea);
        return;
    }
    let moduleId = event.dataTransfer.getData("text");
    testAndSetPlaceholder(moduleId);
    $('#' + moduleId).detach().appendTo($('#unmappedModulesList'));
}

/**
 * Generic drag event which enables the data transfer of the dragged object.
 */
function drag(event) {
    //in case the event is a jQuery object and not the Browser object
    if (event.dataTransfer === undefined) {
        event = event.originalEvent;
    }
    event.dataTransfer.setData("text", event.target.id);
}

/**
 * Checks whether the parent is a semester body. If it is and the dragged module was its last child, insert the drag
 * and drop placeholder.
 */
function testAndSetPlaceholder(draggedModuleId) {
    let modulesArea = $('#' + draggedModuleId).closest(".modulesArea");
    if (modulesArea !== undefined && modulesArea.children().length === 1) {
        modulesArea.append(dragAndDropModulePlaceholder.clone());
    }
}

/**
 * Collects the data from the create stub popup and creates a jquery object from the data.
 */
function createStub() {

    if ($('#stub_title').val()) {

        //reset title field if it was marked as invalid
        if ($('#stub_title').hasClass('is-invalid')) {
            $('#stub_title').removeClass('is-invalid');
        }

        let stub = {
            code: $('#stub_code').val(),
            title: $('#stub_title').val(),
            areaOfStudies: areaOfStudiesMap[$('#areaOfStudies').val().replace("#", "")],
            moduleCoordinator: $('#stub_coordinator').val(),
            stubId: nextStubId
        };
        moduleStubs[nextStubId] = stub;
        nextStubId++;

        $('#createModuleStub').modal('hide');
        $('#stubForm').trigger('reset');
        addStub(stub);

    } else if (!$('#stub_title').hasClass('is-invalid')) {

        //mark title field as invalid
        $('#stub_title').addClass("is-invalid");

    }

}

/**
 * Creates the card for the new stub.
 */
function addStub(stub) {
    $('<div></div>', {
        class: "card module",
        style: "background-color:rgb(" + areaOfStudiesMap[stub.areaOfStudies.id].colorRGB + ")",
        id: "modulelist_stub" + stub.stubId,
        "data-stub-id": stub.stubId,
        draggable: true,
        on: {
            dragstart: drag
        }
    }).append(
        $('<div></div>', {
            "class": "card-header text-truncate",
            html: stub.code + ' ' + stub.title
        })
    ).appendTo($('#unmappedModulesList'));
}

/**
 * Checks if a module area needs a placeholder or if an existing placeholder needs to be removed.
 *
 */
function checkPlaceholder(modulesArea) {
    if (modulesArea !== undefined && modulesArea.children().length > 1) {
        modulesArea.find(".dragAndDropPlaceholder").remove();
    } else if (modulesArea !== undefined && modulesArea.children().length === 0) {
        modulesArea.append(dragAndDropModulePlaceholder.clone());
    }
}

function refreshPage() {
    $("#refreshButtonIcon").addClass("fa-spin");
    $.ajax({
        url: '/exreg/refresh',
        type: 'POST',
        success: ({
                      moduleList: newUnmappedModules = [],
                      areaOfStudiesMap: newAreaOfStudiesMap = {},
                      existingModulesMap: newExistingModulesMap = {},
                      areaOfStudiesList = [], curriculumList = []
                  }) => {

            /*
             * update the curriculum Select List
             */
            let selectCurriculum = $("#exReg_curriculum");
            selectCurriculum.find("option:not(:first)").remove(); //delete all options but the first one

            curriculumList.forEach(curriculum => { //iterate over new curriculum options and append them
                $("<option></option>", {
                    text: `${curriculum.acronym} ${curriculum.name}`,
                    value: curriculum.id
                }).appendTo(selectCurriculum);
            });

            /*
             * update the areaOfStudies Select List in the add Module Stub
             */
            let selectAreaOfStudies = $("#areaOfStudies");
            selectAreaOfStudies.find("option").remove(); // empty the list

            areaOfStudiesList.forEach(areaOfStudies => { //iterate over new areaOfStudies options and append them
                $("<option></option>", {
                    text: `${areaOfStudies.name}`,
                    value: areaOfStudies.id
                }).appendTo(selectAreaOfStudies);
            });


            /*
             * remove previously unmapped modules that have been mapped in the meantime
             */
            listOfUnmappedModules.forEach(oldUnmappedModule => { //Iterate through the old and updated Module list and check if module is still unmapped

                let stillUnmapped = false;
                newUnmappedModules.forEach(newUnmappedModule => {
                    if (oldUnmappedModule.id === newUnmappedModule.id) {
                        stillUnmapped = true; //set unmapped flag to true, since the id is also found in the updated list
                    }
                });

                if (!stillUnmapped) { //if unmapped flag is false delete module from DOM
                    $(`.module[data-module-id=${oldUnmappedModule.id}]`).remove();
                }
            });

            /*
             * replace the text of the remaining unmapped modules
             * their titles or acronyms may have changed
             */
            newUnmappedModules.forEach(module => {
                let oldModule = $.find(`#modulelist_module${module.id}`);
                if (oldModule.length > 0) { //checks if module has html representation
                    $(oldModule).replaceWith(createModule(module)); //if so replace it with updated model
                } else {
                    $(createModule(module)).appendTo($('#unmappedModulesList'));    //if it has no representation generate one and append to unmapped modules list
                }
            });

            let allModulesArea = $.find("div.modulesArea");
            allModulesArea.forEach(modulesArea => { //check all modules area if they need a placeholder due to the removal of already mapped modules
                checkPlaceholder($(modulesArea));
            });

            listOfUnmappedModules = newUnmappedModules;
            existingModulesMap = newExistingModulesMap;
            areaOfStudiesMap = newAreaOfStudiesMap;
        },
        error: function (response) {
            console.log(response);
        }
    });
    window.setTimeout(function () {
        $("#refreshButtonIcon").removeClass("fa-spin");
    }, 1000);
}

/**
 *
 * Creates the "real" html module representation, not stubs!
 */
function createModule(module) {
    return $('<div></div>', {
        class: "card module",
        style: `background-color:rgb(${module.areaOfStudies.colorRGB})`,
        id: `modulelist_module${module.id}`,
        "data-module-id": module.id,
        draggable: true,
        on: {
            dragstart: drag
        }
    }).append(
        $('<div></div>', {
            class: "card-header text-truncate",
            html: `${module.code} ${module.title}`
        })
    );
}

/**
 * Saves all mapped Module stubs and Modules.
 * Sends an AJAX request to the REST controller which then saves the data to the DB.
 */
function save() {
    let stubsToBeMapped = [];
    let modulesToBeMapped = [];

    $('#semesterContainer').children('div').each(function (semesterIndex, semester) {
        $(semester).find("div.module").each(function (moduleIndex, module) {
            if (module.hasAttribute("data-module-id")) {

                //module is an existing module, not a stub
                let moduleId = module.getAttribute("data-module-id");
                module = existingModulesMap[moduleId];
                module.semester = semesterIndex + 1;
                modulesToBeMapped.push(module);

            } else if (module.hasAttribute("data-stub-id")) {

                //module is a stub
                let stubId = module.getAttribute("data-stub-id");
                let stub = moduleStubs[stubId];
                stub.semester = semesterIndex + 1;
                delete stub.stubId;
                stubsToBeMapped.push(stub);

            }
        });
    });

    let exregData = {
        exReg: {
            name: $('#exReg_name').val(),
            validFrom: $('#exReg_validFrom').val(),
            curriculum: {
                id: $('#exReg_curriculum').find(":selected").val()
            }
        },
        newModuleStubs: stubsToBeMapped,
        modulesToBeMapped: modulesToBeMapped
    };

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        url: '/exreg/save',
        dataType: 'json',
        data: JSON.stringify(exregData),
        success: ({data = null, messages = [], redirectTo = null}, textStatus, jqXHR) => {
            if (redirectTo != null) {
                // store messages
                sessionStorage.setItem('messages', JSON.stringify(messages));
                // redirect to wherever we're told to
                window.location.href = redirectTo;
            } else {
                // display messages here and now
                messages.forEach((message) => {
                    displayAlert(message);
                });
            }
        },
        error: (jqXHR, textStatus, errorThrown) => {
            let response = JSON.parse(jqXHR.responseText);
            displayAlert({type: 'ERROR', message: `${response.status} ${response.error}: ${response.message}`});
        }
    });
}

/**
 * Function which will be executed when the site has been loaded.
 * It will append all unmapped modules from the DB to the unmapped modules list.
 */
$(document).ready(function () {
    listOfUnmappedModules.forEach(module => {
        existingModulesMap[module.id] = module;
    });
    addNewSemester();
});
