$(document).ready(function() {
	
	$('#date').datepicker({
		dateFormat : 'dd-mm-yy'
	});
	
	// for dinamically created postits
	$('#postitsTable').on('click', '.done', function(event){
		
		var id = event.target.id;
		
		// / ajax poziv za brisanje
		$.ajax({
			url : 'markAsDone',
			method : 'GET',
			data : {
				postitId : id
			},
		}).done(function(response) {

			var $rowToBeDeleted = $('#' + id).closest('tr');
			$rowToBeDeleted.remove();
		});
		
	});
	

	$.validator.setDefaults({
		errorClass: 'help-block',
		highlight: function(element) {
			$(element)
				.closest('.form-group')
				.addClass('has-error');
		},
		unhighlight: function(element) {
			$(element)
				.closest('.form-group')
				.removeClass('has-error');
		}
	});
	
	$('input').blur(function(event){
		event.preventDefault();
		
		$('#addForm').validate({
			onfocusout: function(element) {
				this.element(element);
			},
			rules: {
				subject: {
					required: true,
					minlength: 10
				},
				text: {
					required: true,
					minlength: 10
				},
				dateString: {
					required: true
				}
			},
			onkeyup : false,
			messages: {
				subject: {
					required: 'Required jQuery',
					minlength: 'Minimal 10 characters long'
				},
				text: {
					required: 'Required jQuery',
					minlength: 'Minimal 10 characters long'
				},
				dateString: {
					required: 'Required jQuery'
				}
			},
			submitHandler: function (form) {
				dodajPostitAjaxom();
	        }
		})
	});
	
	
	function dodajPostitAjaxom(){
		
		var $subject = $('#subject').val();
		var $text = $('#text').val();
		var $date = $('#date').val();
		
		$.ajax({
			type: 'POST',
			url: 'addPostit',
			data: JSON.stringify({"subject": $subject,
				"text": $text,
				"date": $date
			}),
			accept: 'application/json',
			dataType: 'json',
			success: success,
			error: error,
			contentType: 'application/json',
			dataType: 'json'
		});
		
		alert('definisana metoda');
		
	};
	
	function success(data) {
		$('#postitsTable').append(
				'<tr>'
				+ '<td class="col-xs-2">' + data.subject +'</td>'
				+ '<td class="col-xs-7">' + data.text +'</td>'
				+ '<td class="col-xs-2">' + data.date +'</td>'
				+ '<td class="col-xs-1"><button class="btn btn-default done" id="' + data.id + '" >Done</button></td>'
				+'</tr>'
				
			);
		alert('<td class="col-xs-1"><button class="btn btn-default done" id="' + data.id + '" >Done</button></td>');
	}
	
	function error(data) {
		alert('Something went wrong');
	}
});
















