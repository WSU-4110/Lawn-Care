<?php
require "conn.php";
//sample input
//email=johnsmith@gmail.com&description=I%20am%20not%20actually%20a%20worker&website=&daysAvailable=MWRF&startTime=09:00&endTime=12:00&workOffered=Mowing,Fertilization
$email=$_POST["email"];
$description=$_POST["description"];
$website=$_POST["website"];
$daysAvailable=$_POST["daysAvailable"];
$startTime=$_POST["startTime"];
$endTime=$_POST["endTime"];
//INPUT NOTE: this input should be a string of work types delimited by commas (for example, "Fertilization, Mowing")
$workOffered=$_POST["workOffered"];
$workOffered=explode(",",$workOffered);

$statement=mysqli_prepare($conn, "INSERT INTO workerProfile (workerEmail,description,website,daysAvailable,startTime,endTime) VALUES(?,?,?,?,?,?)");
mysqli_stmt_bind_param($statement,"ssssss",$email,$description,$website,$daysAvailable,$startTime,$endTime);

$response=array();
$response["success"]=false;

if(mysqli_stmt_execute($statement)){
    $response["success"]=true;
    foreach ($workOffered as $workType){
        $workerWork=mysqli_prepare($conn, "INSERT INTO workerWork (workerEmail, workOffered) VALUES(?,?)");
        mysqli_stmt_bind_param($workerWork,"ss",$email,$workType);
        mysqli_stmt_execute($workerWork);
    }

    
}
echo json_encode($response);
//sample output
//{"success":true}
mysqli_close($conn);






?>