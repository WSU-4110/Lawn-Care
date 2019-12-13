<?php
require "conn.php";


$email=$_POST["email"];
$password=$_POST["password"];
$firstName=$_POST["firstName"];
$lastName=$_POST["lastName"];
$phoneNumber=$_POST["phoneNumber"];
$userType=$_POST["userType"];

$userTypeWorker = 'worker';
//get results that match user and password from database
$statement=mysqli_prepare($conn, "INSERT INTO accounts (email,password,firstName,lastName,phone,userType) VALUES(?,?,?,?,?,?)");
$password = md5($password);
mysqli_stmt_bind_param($statement,"ssssss",$email,$password,$firstName,$lastName,$phoneNumber,$userType);

$response=array();
$response["success"]=false;

if(mysqli_stmt_execute($statement)){
    $response["success"]=true;
    if($userType=$userTypeWorker){
        $qury = mysqli_prepare($conn, "INSERT INTO workerProfile (workerEmail) VALUES(?)");
        mysqli_stmt_bind_param($qury,"s",$email);
        mysqli_stmt_execute($qury);
    }
    else
        echo "diff";
}
echo json_encode($response);

//mysqli_free_result($statement);

mysqli_close($conn);
?>