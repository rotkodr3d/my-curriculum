$(document).ready(function() {
	//TODO
});

var semesterCounter = 0;
var listOfUnmappedModules = [];

function addNewSemester() {
	var semesterContainer = document.getElementById("semesterContainer");
	var semester = document.createElement("div");
	var semesterTitle = document.createElement("div");
	var semesterBody = document.createElement("div"); //Body of semester which contains the modules 
	semester.draggable = "true";
	semesterCounter++;
	semesterTitle.className="panel-heading";
	semesterTitle.innerText = semesterCounter + ". Semester";
	semesterBody.id = "semesterbody" + semesterCounter;
	semesterBody.className = "semesterBody";
	semesterBody.ondragover = function(event) {
		event.preventDefault();
	};
	semesterBody.ondrop = function(event) {
		event.preventDefault();
		var data = event.dataTransfer.getData("text");
		var element = document.getElementById(data);
		alert(data);
		event.target.appendChild(element);
	} ;
	semester.appendChild(semesterTitle);
	semester.appendChild(semesterBody);
	semesterContainer.appendChild(semester);
}

function allowDrag(event) {
	event.preventDefault();
}

function moduleListDrop(event) {
	event.preventDefault();
	var data = event.dataTransfer.getData("text");
	var element = document.getElementById(data);
	event.target.appendChild(element);
}

function dragModuleEnd(element) {
	element.style.opacity = 1.0;	
}

function dragModule(event,element) {
	event.dataTransfer.setData("text", event.target.id);
	element.style.opacity = 0.4;
}

function drag(ev) {
	ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
	ev.preventDefault();
	var data = ev.dataTransfer.getData("text");
	var element = document.getElementById(data);

	//element.style.opacity = 1.0;

	if (ev.target === document.getElementById("div1")) {
		ev.target.appendChild(element);
	} else if (ev.target === document.getElementById("list")) {
		ev.target.appendChild(element);
	} else {
		return;
	}
}