<?php
require "conn.php";
//sample input

$propertyNumber=$_POST["propertyNumber"];

$statement=mysqli_prepare($conn, "DELETE FROM propertyInfo WHERE propertyNumber = ?");
mysqli_stmt_bind_param($statement,"i",$propertyNumber);

$response=array();
$response["success"]=false;

if(mysqli_stmt_execute($statement)){
    $response["success"]=true;
}
echo json_encode($response);

//mysqli_free_result($statement);

mysqli_close($conn);
?>