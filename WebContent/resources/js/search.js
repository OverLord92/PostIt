$(document).ready(function() {

	$('#date').datepicker({
		dateFormat : 'dd-mm-yy'
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
	
	$('#submitForm').click(function(event){
		$('#searchForm').validate({
			onfocusout: function(element) {
				this.element(element);
			},
			rules : {
				date: {
					required: true,
				}
			},
			onkeyup : false,
			messages : {
				date: {
					required: 'Date is required',
				}
			},
			submitHandler: function (form) {
				alert('validiralo');
				ajaxSearch();
	        }
		});
	});
	
	
	function ajaxSearch(){
		alert('ajax');
		
		var $date = $('#date').val();
		var $searchType = $('input[name=searchType]:checked').val();
		
		$.ajax({
			type: 'POST',
			url: 'searchPostits',
			data: JSON.stringify({
				'date': $date,
				'searchType': $searchType
			}),
			accept: 'application/json',
			dataType: 'json',
			success: success,
			error: error,
			contentType: 'application/json',
			dataType: 'json'
		});
				
	};
	
	function success(data) {
		$("#postitsTable").find("tr:gt(0)").remove();
		
		var postits = data.filteredPostits;
		
		
		for(var i = 0; i < postits.length; i++){
				
			$('#postitsTable').append(
				'<tr>'
				+ '<td>' + postits[i].subject +'</td>'
				+ '<td>' + postits[i].text +'</td>'
				+ '<td>' + postits[i].date +'</td>'
				+ '<td></td>'
				+'</tr>'
				
			);
		}
		
		
		alert(data.filteredPostits.length);
	}
	
	function error(data) {
		alert('Something went wrong');
	}
});