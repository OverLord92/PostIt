$(document).ready(function(){
	
	alert('radi');
	
	// for dinamically created postits
	$('#postitsTable').on('click', '.edit', function(event){
		$(this).closest('tr').next().toggle();
	});
});