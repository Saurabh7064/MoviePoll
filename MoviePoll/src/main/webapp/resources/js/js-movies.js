$(document).ready(
		
function customDateTag() {
	
	$('#customDateTag').popover({		
        content: "Custom date tag",
        trigger: 'hover',
        placement:'left',
        delay: {show: 0, hide: 100}
    });		
});

function openModal() {
	$("#myModal").modal('show');
}