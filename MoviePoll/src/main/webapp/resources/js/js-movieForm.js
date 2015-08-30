function voteMovie(){	
	document.getElementById("vote").submit();	
}

$(document).ready(
		function initializeDatePicker() {
			
			$(".datepicker").datepicker({dateFormat : "yy-mm-dd",
				changeMonth : false,
				changeYear : false,
				showButtonPanel : true}).datepicker("setDate", new Date());
});