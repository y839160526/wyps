
function openBox(){
	var msg="calculate";
chrome.tabs.query({active: true, currentWindow: true}, function(tabs){  
    chrome.tabs.sendMessage(tabs[0].id, {message:msg}, function(response) {  
      
    });    
});  
}

window.onload = function () {

$('#btn_submit').click(function () {
    openBox();});
}
