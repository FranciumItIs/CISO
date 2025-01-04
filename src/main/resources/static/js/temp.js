function clearFunction() {
	location.reload();
}

function validateSearch() {
	var wepe = document.querySelector('input[name="wepe"]:checked');
	clearErrors();
	if (wepe === null) {
		displayError('wepeError', 'Please Select Type Of Document');
		isValid = false;
	}
}

function validateForm() {
	debugger
	var wepe = document.querySelector('input[name="wepe"]:checked');
	var uploadedwepe = document.getElementById('uploadedwepe').value;
	var wepeNo = document.getElementById('wepeNo').value;
	var newWePe = document.querySelector('input[name="newWePe"]:checked');
	var tableTitle = document.getElementById('tableTitle').value;
	var efffrmdate = document.getElementById('efffrmdate').value;
	var efftodate = document.getElementById('efftodate').value;
	var doctype = document.getElementById('doctype').value;
	var arm = document.getElementById('arm').value;
	var sponsordire = document.getElementById('sponsordire').value;

	clearErrors();

	var isValid = true;

	//	if (wepe === null) {
	//		displayError('wepeError', 'Please Select Type Of Document');
	//		isValid = false;
	//	}

	if (uploadedwepe.trim() == '' || uploadedwepe.trim() == null) {
		displayError('uploadedwepe', 'Please Select Approved Upload WE/PE No.');
		isValid = false;
	}

	if (wepeNo.trim() == '') {
		displayError('wepeNo', 'Please Select Miso WE/PE No.');
		isValid = false;
	}

	if (tableTitle.trim() == '') {
		displayError('tableTitle', 'Please Enter WE/PE Title');
		isValid = false;
	}

	if (efffrmdate.trim() == '') {
		displayError('efffrmdate', 'Please Select Effective From');
		isValid = false;
	}

	if (efftodate.trim() == '') {
		displayError('efftodate', 'Please Select Effective To');
		isValid = false;
	}

	if (doctype.trim() == '') {
		displayError('doctype', 'Please Select Security Classification');
		isValid = false;
	}

	if (arm.trim() == '') {
		displayError('arm', 'Please Select Arm/Service');
		isValid = false;
	}

	if (sponsordire.trim() == '') {
		displayError('sponsordire', 'Please Select Sponsor Directorate');
		isValid = false;
	}

	return isValid;
}


document.addEventListener('DOMContentLoaded', function() {
	var Search = document.getElementById('SerachID');
	if (Search) {
		Search.onclick = function() {
			mockjax2('capWePeTitleTable');
			t1 = dataTableBind2('capWePeTitleTable');
			$("div#tablediv").show();
		}
	}
});

$('#capWePeTitleTable').on('click', '.deleteBtn', function(event) {
	var itemId = $(this).data('id');
	var r = $('input:radio[name=wepe]:checked').val();
	if (confirm("Are You Sure You Want To Delete This Data?")) {
		$("#deleteid").val(itemId);
		$("#wepe12").val(r);
		$("#deleteForm").submit();
	}
});

$('#capWePeTitleTable').on('click', '.editBtn', function(event) {
	var itemId = $(this).data('id');
	if (confirm("Are You Sure You Want To Edit this data?")) {
		$("#editid").val(itemId);
		$("#editForm").submit();
	}
});

var weapId = document.getElementById('weapId');
function data2(tableName) {
	var table = $('#' + tableName).DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();

	var orderColunm = table.order()[0][0];
	var orderType = order[0][1];

	var srch_radios = document.querySelector('input[name="wepe"]:checked');

	var wepe = srch_radios ? srch_radios.value : '';
	var uploadedwepe = document.getElementById("uploadedwepe").value;
	var wepeNo = document.getElementById("wepeNo").value;
	var arm = document.getElementById("arm").value;
	var sponsordire = document.getElementById("sponsordire").value;
	var doctype = document.getElementById("doctype").value;
	var tableTitle = document.getElementById("tableTitle").value;
	var setTypeOnclick = document.getElementById("setTypeOnclick").value;

	FilteredRecords = 0;

	var formData = {

		currentPage: currentPage.toString(),
		Search: Search,
		wepe: wepe,
		uploadedwepe: uploadedwepe,
		wepeNo: wepeNo,
		arm: arm,
		sponsordire: sponsordire,
		doctype: doctype,
		setTypeOnclick: setTypeOnclick,
		startPage: startPage.toString(),
		pageLength: pageLength.toString(),
		orderColunm: orderColunm.toString(),
		order: order.toString(),
		//code: code,
		pageno: table.page.info().page,
		orderDir: table.order()[0][1]
	};

	jsonData = JSON.stringify(formData);
	$.ajax({ url: '/searchCaptureWePe', type: "POST", data: jsonData, contentType: 'application/json', dataType: 'json', })
		.done(function(data) {
			jsondata = [];
			for (var i = 0; i < data.data.length; i++) {
				var statusData = data.data[i];
				console.log(statusData);
				jsondata.push([i + 1, statusData.wepeNo, statusData.uploadedwepe, statusData.suprcddwepeNo, statusData.tabletitle,
				statusData.linedtename, statusData.armdesc, statusData.doctype, statusData.rejectLetterNo, statusData.rejectRemarks,
				statusData.Action]);
			}
			FilteredRecords = data.total;
		})
		.fail(function(jqXHR, textStatus) { });
}

document.addEventListener('htmx:afterSwap', function() {
	const radioButtons = document.getElementsByName('answer01');
	const supersededDocumentNoDiv = document.getElementById('supersededDoc');

	if (event.detail.target.id === "wepenoList") {
		const suggestionItems = document.querySelectorAll('#wepenoList li');
		suggestionItems.forEach(item => {
			item.addEventListener('click', function() {
				document.getElementById('uploadedwepe').value = this.textContent;
				document.getElementById('wepenoList').innerHTML = '';

			});
		});
	}

	if (event.detail.target.id === "wepenomisoList") {
		const suggestionItems = document.querySelectorAll('#wepenomisoList li');
		suggestionItems.forEach(item => {
			item.addEventListener('click', function() {
				document.getElementById('wepeNo').value = this.textContent;
				document.getElementById('wepenomisoList').innerHTML = '';

			});
		});
	}

});


document.body.addEventListener('htmx:afterRequest', function(evt) {
	if (evt.detail.elt.id === 'wepenoList') {
		var values = JSON.parse(evt.detail.xhr.response);
		console.log(values);
		document.getElementById('arm').value = values[0];
		document.getElementById('efffrmdate').value = values[1];
		document.getElementById('efftodate').value = values[2];
		document.getElementById('doctype').value = values[3];
		document.getElementById('tableTitle').value = values[4];
		document.getElementById('sponsordire').value = values[5];
	}

});


$('#capWePeTitleTable').on('click', '.viewBtn', function(event) {
	var itemId = $(this).data('id');
	$.ajax({
		url: '/getViewData',
		type: 'POST',
		data: { id: itemId },
		success: function(data) {
			debugger
			var jsonData = JSON.parse(data);
			$('#viewWepe').val(jsonData.wepe);
			$('#viewWepeNo').val(jsonData.wepeNo);
			$('#viewTablTitle').val(jsonData.tabletitle);
			// Set values for other fields

			// Initialize your DataTables here using itemId to fetch data
			// Example:
			//            $('#getStdTransSearchReport').DataTable({
			//                ajax: {
			//                    url: '/getDataForTable1',
			//                    type: 'POST',
			//                    data: { id: itemId }
			//                }
			//                // ... other DataTable options
			//            });
			//            
			//            $('#getStdTransModeSearchReport').DataTable({
			//                ajax: {
			//                    url: '/getDataForTable2',
			//                    type: 'POST',
			//                    data: { id: itemId }
			//                }
			//                // ... other DataTable options
			//            });
			//            
			//            $('#getStdTransfotnoteSearchReport').DataTable({
			//                ajax: {
			//                    url: '/getDataForTable3',
			//                    type: 'POST',
			//                    data: { id: itemId }
			//                }
			//                // ... other DataTable options
			//            });
			//            
			//            $('#getStdWaeponSearchReport').DataTable({
			//                ajax: {
			//                    url: '/getDataForTable3',
			//                    type: 'POST',
			//                    data: { id: itemId }
			//                }
			//                // ... other DataTable options
			//            });

			$('#getStdWaeponModeSearchReport').DataTable({
				ajax: {
					url: '/list6View',
					type: 'POST',
					data: { id: itemId }
				}
				// ... other DataTable options
			});

			//            $('#getStdWaeponfotnoteSearchReport').DataTable({
			//                ajax: {
			//                    url: '/getDataForTable3',
			//                    type: 'POST',
			//                    data: { id: itemId }
			//                }
			//                // ... other DataTable options
			//            });
			//            
			//            $('#getStdPersonSearchReport').DataTable({
			//                ajax: {
			//                    url: '/getDataForTable3',
			//                    type: 'POST',
			//                    data: { id: itemId }
			//                }
			//                // ... other DataTable options
			//            });
			//            
			//            $('#getStdPersonModeSearchReport').DataTable({
			//                ajax: {
			//                    url: '/getDataForTable3',
			//                    type: 'POST',
			//                    data: { id: itemId }
			//                }
			//                // ... other DataTable options
			//            });
			//            
			//            $('#getStdPersonfotnoteSearchReport').DataTable({
			//                ajax: {
			//                    url: '/getDataForTable3',
			//                    type: 'POST',
			//                    data: { id: itemId }
			//                }
			//                // ... other DataTable options
			//            });

			// Repeat for dataTable2 and dataTable3

			$('#viewDataModal').modal('show');
		}
	});
});


function closeReject() {
	var id = document.getElementById('rejectid_model').value;
	$('#rrr' + id).attr('data-target', '#');
	$('#rejectModal').modal('hide'); // This line closes the modal when the rejection is canceled
	$("#rejid").val(''); // This line nullifies the ID
}
