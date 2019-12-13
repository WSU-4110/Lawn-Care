<?php
require "conn.php";

//sample input
//email=johnsmith@gmail.com&description=I%20am%20not%20actually%20a%20worker&website=&daysAvailable=MWRF&startTime=09:00&endTime=12:00&
//workOffered=Mowing,Fertilization

$email=$_POST["email"];
$description=$_POST["description"];
$website=$_POST["website"];
$daysAvailable=$_POST["daysAvailable"];
$startTime=$_POST["startTime"];
$endTime=$_POST["endTime"];

$statement=mysqli_prepare($conn, "UPDATE workerProfile SET
    description = ?,
    website = ?,
    daysAvailable = ?,
    startTime = ?,
    endTime = ?
WHERE workerEmail = ?");
mysqli_stmt_bind_param($statement,"ssssss",$description,$website,$daysAvailable,$startTime,
$endTime,$email);
mysqli_stmt_execute($statement);

$response=array();
$response["success"]=false;

echo json_encode($response);
//sample output
//{"success":true}
mysqli_close($conn);






?>