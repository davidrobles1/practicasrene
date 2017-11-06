<!DOCTYPE html>
<html>
	<title>Datatable Demo1 | CoderExample</title>
	<head>
		<link rel="stylesheet" type="text/css" href="css/jquery.dataTables.css">
		<script type="text/javascript" language="javascript" src="js/jquery.js"></script>
		<script type="text/javascript" language="javascript" src="js/jquery.dataTables.js"></script>
		<script type="text/javascript" language="javascript" >
			$(document).ready(function() {
				var dataTable = $('#employee-grid').DataTable( {
					"processing": true,
					"serverSide": true,
					"ajax":{
						url :"employee-grid-data.php", // json datasource
						type: "post",  // method  , by default get
						error: function(){  // error handling
							$(".employee-grid-error").html("");
							$("#employee-grid").append('<tbody class="employee-grid-error"><tr><th colspan="3">No data found in the server</th></tr></tbody>');
							$("#employee-grid_processing").css("display","none");
							
						}
					}
				} );
				var dataTable = $('#employee-grid1').DataTable( {
					"processing": true,
					"serverSide": true,
					"ajax":{
						url :"employee-grid1-data.php", // json datasource
						type: "post",  // method  , by default get
						error: function(){  // error handling
							$(".employee-grid1-error").html("");
							$("#employee-grid1").append('<tbody class="employee-grid1-error"><tr><th colspan="3">No data found in the server</th></tr></tbody>');
							$("#employee-grid1_processing").css("display","none");
							
						}
					}
				} );
				var dataTable = $('#employee-grid2').DataTable( {
					"processing": true,
					"serverSide": true,
					"ajax":{
						url :"employee-grid2-data.php", // json datasource
						type: "post",  // method  , by default get
						error: function(){  // error handling
							$(".employee-grid2-error").html("");
							$("#employee-grid2").append('<tbody class="employee-grid2-error"><tr><th colspan="3">No data found in the server</th></tr></tbody>');
							$("#employee-grid2_processing").css("display","none");
							
						}
					}
				} );
				var dataTable = $('#employee-grid3').DataTable( {
					"processing": true,
					"serverSide": true,
					"ajax":{
						url :"employee-grid3-data.php", // json datasource
						type: "post",  // method  , by default get
						error: function(){  // error handling
							$(".employee-grid3-error").html("");
							$("#employee-grid3").append('<tbody class="employee-grid3-error"><tr><th colspan="3">No data found in the server</th></tr></tbody>');
							$("#employee-grid3_processing").css("display","none");
							
						}
					}
				} );
			} );
		</script>
		<style>
			div.container {
			    margin: 0 auto;
			    max-width:760px;
			}
			div.header {
			    margin: 100px auto;
			    line-height:30px;
			    max-width:760px;
			}
			body {
			    background: #f7f7f7;
			    color: #333;
			    font: 90%/1.45em "Helvetica Neue",HelveticaNeue,Verdana,Arial,Helvetica,sans-serif;
			}
		</style>
	</head>
	<body>
		<div class="header"><h1>DATATABLE MECANICA</h1></div>
		<div class="container">
			<table id="employee-grid"  cellpadding="0" cellspacing="0" border="0" class="display" width="100%">
					<thead>
						<tr>
							<th>marca</th>
							<th>modelo</th>
							<th>cliente</th>
							<th>tipo_transmicion</th>
						</tr>
					</thead>
			</table>
			<div class="header"><h1>DATATABLERELACION MECANICA</h1></div>
			<table id="employee-grid1"  cellpadding="0" cellspacing="0" border="0" class="display" width="100%">
					<thead>
						<tr>
							<th>fecha</th>
							<th>mecanico</th>
							<th>descripcion</th>
							<th>costo</th>
						</tr>
					</thead>
			</table>
			<div class="header"><h1>DATATABLERELACION MECANICA</h1></div>
			<table id="employee-grid2"  cellpadding="0" cellspacing="0" border="0" class="display" width="100%">
					<thead>
						<tr>
							<th>nombre</th>
							<th>apellidos</th>
							<th>numero</th>
							<th>domicilio</th>
						</tr>
					</thead>
			</table>
			<div class="header"><h1>DATATABLERELACION MECANICA</h1></div>
			<table id="employee-grid1"  cellpadding="0" cellspacing="0" border="0" class="display" width="100%">
					<thead>
						<tr>
							<th>fecha</th>
							<th>mecanico</th>
							<th>descripcion</th>
							<th>costo</th>
						</tr>
					</thead>
			</table>
		</div>
	</body>
</html>
