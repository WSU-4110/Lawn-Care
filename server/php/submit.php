<?php
require "conn.php";

$email=$_POST["email"];
$workOffered=$_POST["workOffered"];
$workOffered=explode(",",$workOffered);


$statement=mysqli_prepare($conn, "DELETE FROM workerWork WHERE workerEmail = ?");
mysqli_stmt_bind_param($statement,"s",$email);
mysqli_stmt_execute($statement);



//DELETE FROM workerwork WHERE workerEmail = ?;

foreach ($workOffered as $workType){
    $workerWork=mysqli_prepare($conn, "INSERT INTO workerWork (workerEmail, workOffered) VALUES(?,?)");
    mysqli_stmt_bind_param($workerWork,"ss",$email,$workType);
    mysqli_stmt_execute($workerWork);
}