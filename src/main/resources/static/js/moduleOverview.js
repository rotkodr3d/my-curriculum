/**
 * 
 */

function showModuleDetails (event) {
    console.log(event);
    var clickedModuleId = (event.target.id == "") ? $(event.target).parents('.module').attr('id') : event.target.id;
    console.log(clickedModuleId);
    
    exRegModules.forEach(function (module) {
        if (module.id == clickedModuleId) {
            console.log(module);
        }
    });
}