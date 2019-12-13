<?php
require "conn.php";
//sample input
//email=johnsmith@gmail.com&street=27%20Grove%20Street&city=Grover&state=Michigan&zip=48182&lawnSize=3300&equipmentAvailable=0&workNeeded=Mowing
$email=$_POST["email"];
$street=$_POST["street"];
$city=$_POST["city"];
$state=$_POST["state"];
$zip=$_POST["zip"];
$lawnSize=$_POST["lawnSize"];
$equipmentAvailable=$_POST["equipmentAvailable"];
//INPUT NOTE: this input should be a string of work types delimited by commas (for example, "Fertilization, Mowing")
$workNeeded=$_POST["workNeeded"];
$workNeeded=explode(",",$workNeeded);

$statement=mysqli_prepare($conn, "INSERT INTO propertyInfo (ownerEmail,street,city,state,zip,lawnSize,equipmentAvailable) VALUES(?,?,?,?,?,?,?)");
mysqli_stmt_bind_param($statement,"ssssiii",$email,$street,$city,$state,$zip,$lawnSize,$equipmentAvailable);

$response=array();
$response["success"]=false;

if(mysqli_stmt_execute($statement)){
    $response["success"]=true;
    //get property number
    $propNum=mysqli_prepare($conn, "select propertyNumber from propertyInfo where street=? and city=? and state=?");
    mysqli_stmt_bind_param($propNum,"sss",$street,$city,$state);
    mysqli_stmt_execute($propNum);
    mysqli_stmt_store_result($propNum);
    mysqli_stmt_bind_result($propNum,$propertyNumber);
    mysqli_stmt_fetch($propNum);
    
    foreach ($workNeeded as $workType){
        $workerWork=mysqli_prepare($conn, "INSERT INTO propertyWork (propertyNumber, workNeeded) VALUES(?,?)");
        mysqli_stmt_bind_param($workerWork,"ss",$propertyNumber,$workType);
        mysqli_stmt_execute($workerWork);
    }

    
}
echo json_encode($response);
//sample output
//{"success":true}
mysqli_close($conn);






?>