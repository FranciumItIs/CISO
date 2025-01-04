$(document).ready(function() {
	$('#carsTable').DataTable({
		"pageLength": 10,
		"lengthChange": false,
		"searching": true,
		"ordering": true,
		"info": true,
		"autoWidth": false,
		"responsive": true,
		"language": {
			"search": "Search:",
			"paginate": {
				"first": "First",
				"last": "Last",
				"next": "Next",
				"previous": "Previous"
			}
		}
	});
});