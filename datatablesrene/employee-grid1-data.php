<?php
/* Database connection start */
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mecanica";

$conn = mysqli_connect($servername, $username, $password, $dbname);

/* Database connection end */


// storing  request (ie, get/post) global array to a variable  
$requestData= $_REQUEST;


$columns = array( 
// datatable column index  => database column name
	0 =>'fecha', 
	1 => 'mecanico',
	2=> 'descripcion',
	3=> 'costo'
);

// getting total number records without any search
$sql = "SELECT fecha,mecanico,descripcion,costo FROM mantenimiento ";
$query=mysqli_query($conn, $sql);
$totalData = mysqli_num_rows($query);
$totalFiltered = $totalData;  // when there is no search parameter then total number rows = total number filtered rows.


$sql = "SELECT fecha,mecanico,descripcion,costo FROM mantenimiento ";
if( !empty($requestData['search']['value']) ) {   // if there is a search parameter, $requestData['search']['value'] contains search parameter
	$sql.=" AND ( fecha LIKE '".$requestData['search']['value']."%' ";    
	$sql.=" OR mecanico LIKE '".$requestData['search']['value']."%' ";
	$sql.=" OR costo LIKE '".$requestData['search']['value']."%' )";
}
$query=mysqli_query($conn, $sql);
$totalFiltered = mysqli_num_rows($query); // when there is a search parameter then we have to modify total number filtered rows as per search result. 
$sql.=" ORDER BY ". $columns[$requestData['order'][0]['column']]."   ".$requestData['order'][0]['dir']."  LIMIT ".$requestData['start']." ,".$requestData['length']."   ";
/* $requestData['order'][0]['column'] contains colmun index, $requestData['order'][0]['dir'] contains order such as asc/desc  */	
$query=mysqli_query($conn, $sql) or die("employee-grid1-data.php: get mecanico");

$data = array();
while( $row=mysqli_fetch_array($query) ) {  // preparing an array
	$nestedData=array(); 

	$nestedData[] = $row["fecha"];
	$nestedData[] = $row["mecanico"];
	$nestedData[] = $row["descripcion"];
	$nestedData[] = $row["costo"];
	
	$data[] = $nestedData;
}



$json_data = array(
			"draw"            => intval( $requestData['draw'] ),   // for every request/draw by clientside , they send a number as a parameter, when they recieve a response/data they first check the draw number, so we are sending same number in draw. 
			"recordsTotal"    => intval( $totalData ),  // total number of records
			"recordsFiltered" => intval( $totalFiltered ), // total number of records after searching, if there is no searching then totalFiltered = totalData
			"data"            => $data   // total data array
			);

echo json_encode($json_data);  // send data as json format

?>
