$(document).ready(function() {

	// for dinamically created postits
	$('#postitsTable').on('click', 'button', function(event) {

		var id = event.target.id;
		alert(id);

		// / ajax poziv za brisanje
		$.ajax({
			url : 'deletePostit',
			method : 'GET',
			data : {
				postitId : id
			},
		}).done(function(response) {

			var $rowToBeDeleted = $('#' + id).closest('tr');
			$rowToBeDeleted.remove();
			alert(response);
		});
	});
});
