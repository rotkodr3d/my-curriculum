/**
 * 
 */
function showModuleDetails (event) {
    var id = (event.target.id == "") ? $(event.target).parents(".module").attr("id") : event.target.id;
    
    $.ajax({
        url: "/details/"+id,
        success: function(response) {
            if ($("#showModuleDetails").length) {
                $("#showModuleDetails").remove();
            }
                $("body").append(response);
                $("#showModuleDetails").modal();
                $("#showModuleDetails").modal("show");
            }
    });
}

/*
 * function closeModal(event) { $("#showModuleDetails").modal("hide");
 * $('body').removeClass('modal-open'); $('.modal-backdrop').remove(); }
 */