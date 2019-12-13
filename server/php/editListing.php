<?php
require "conn.php";
//sample input
//email=johnsmith@gmail.com&street=27%20Grove%20Street&city=Grover&state=Michigan&zip=48182&lawnSize=3300&equipmentAvailable=0&workNeeded=Mowing
$propNum=$_POST["propNum"];
$editStreet=$_POST["street"];
$editCity=$_POST["city"];
$editState=$_POST["state"];
$editZip=$_POST["zip"];
$editLawnSize=$_POST["lawnSize"];
$editEquipmentAvailable=$_POST["equipmentAvailable"];
//INPUT NOTE: this input should be a string of work types delimited by commas (for example, "Fertilization, Mowing")
$editWorkNeeded=$_POST["workNeeded"];
$editWorkNeeded=explode(",",$editWorkNeeded);

$response=array();
$response["success"]=false;


//$statement=mysqli_prepare($conn, "select street,city,state,zip,lawnSize,equipmentAvailable from propertyinfo where propertyNumber=$propNum");
//mysqli_stmt_bind_param($statement);

//if(mysqli_stmt_execute($statement)){
    $response["success"]=true;

	$m  = mysqli_prepare($conn, "UPDATE propertyInfo SET street = ?, city = ?, state = ?, zip = ?, lawnSize = ?, equipmentAvailable = ?  WHERE (propertyNumber = ?)");
	mysqli_stmt_bind_param($m, "sssssss", $editStreet, $editCity, $editState, $editZip, $editLawnSize, $editEquipmentAvailable, $propNum);
	mysqli_stmt_execute($m);
	
	$d = mysqli_prepare($conn, "DELETE FROM propertyWork WHERE (propertyNumber = ?)");
	mysqli_stmt_bind_param($d, "s",$propNum);
	mysqli_stmt_execute($d);

	
	foreach ($editWorkNeeded as $editWorkType){
		$editWork=mysqli_prepare($conn, "INSERT INTO propertyWork (propertyNumber, workNeeded) VALUES(?,?)");
        mysqli_stmt_bind_param($editWork,"ss",$propNum,$editWorkType);
        mysqli_stmt_execute($editWork);
	}

//}
echo json_encode($response);
//sample output
//{"success":true}
mysqli_close($conn);






?>