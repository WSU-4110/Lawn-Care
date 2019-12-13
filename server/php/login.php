<?php
require "conn.php";

$email=$_POST["email"];
$password=$_POST["password"];
//get results that match user and password from database
$statement=mysqli_prepare($conn, "select phone,firstName,lastName,userType from accounts where email = ? and password = ?");
$password = md5($password);
mysqli_stmt_bind_param($statement,"ss",$email,$password);
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement,$phone,$firstName,$lastName,$userType);

$response=array();
$response["success"]=false;

while(mysqli_stmt_fetch($statement)){
    $response["success"]=true;
    $response["phone"]=$phone;
    $response["firstName"]=$firstName;
    $response["lastName"]=$lastName;
    $response["userType"]=$userType;
    
}

echo json_encode($response);

//mysqli_free_result($statement);

mysqli_close($conn);
?>