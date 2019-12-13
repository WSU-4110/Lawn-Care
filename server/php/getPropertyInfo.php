<?php
require "conn.php";

$propertyNumber=$_POST["propertyNumber"];
//get results that match user and password from database
$statement=mysqli_prepare($conn, "select firstName,lastName, phone, propertyNumber, ownerEmail, street, city, state, zip, lawnSize, equipmentAvailable from accounts,propertyInfo where propertyNumber = ? AND accounts.email=propertyInfo.ownerEmail");
mysqli_stmt_bind_param($statement,"s",$propertyNumber);
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement,$firstName,$lastName,$phone,$propertyNumber, $ownerEmail, $street, $city, $state, $zip, $lawnSize, $equipmentAvailable);

$response=array();
$response["success"]=false;

while(mysqli_stmt_fetch($statement)){
    $response["success"]=true;
    $response["firstName"]=$firstName;
    $response["lastName"]=$lastName;
    $response["phone"]=$phone;
    $response["propertyNumber"]=$propertyNumber;
    $response["ownerEmail"]=$ownerEmail;
    $response["street"]=$street;
    $response["city"]=$city;
    $response["state"]=$state;
    $response["zip"]=$zip;
    $response["lawnSize"]=$lawnSize;
    $response["equipmentAvailable"]=$equipmentAvailable;
    
    //getting work categories
    $response["workNeeded"]=array();
    $workNeeded=mysqli_prepare($conn, "select workNeeded from propertyWork where propertyNumber=?");
    mysqli_stmt_bind_param($workNeeded,"s",$propertyNumber);
    mysqli_stmt_execute($workNeeded);
    mysqli_stmt_store_result($workNeeded);
    mysqli_stmt_bind_result($workNeeded, $workType);
    
    while(mysqli_stmt_fetch($workNeeded)){
        $response["workNeeded"][]=$workType;
    }
    
}

echo json_encode($response);
mysqli_close($conn);
?>