<?php
require "conn.php";
//sample input
////email=jhooper@yahoo.com
$rating_star = $_POST['rating_star'];
$rating_description = $_POST['rating_description'];
$worker_email = $_POST['worker_email'];
$owner_email = $_POST['owner_email'];

$statement=mysqli_prepare($conn, "INSERT INTO workerrating (rating_star, rating_description, worker_email, owner_email) VALUES(?,?,?,?)");
mysqli_stmt_bind_param($statement,"dsss",$rating_star,$rating_description,$worker_email,$owner_email);
if(mysqli_stmt_execute($statement)) {
    $response=array();
    $response["success"]=true;
    }
else{
    $response=array();
    $response["success"]=false;
}




//returns json string
//response["success"] is true or false
//if it is successful, which it should always be if there is data in the databse
//response[i] is a row of data, each attribute accessible by it's name ("street" for street address, etc.)
echo json_encode($response);
//SAMPLE RESPONSE
//{"success":true,"description":"I am a hard worker with 8 years of experience in the lawn care industry. Visit my website to view my credentials and read testimonials","website":"juliushooperlawncare.com","daysAvailable":"UMTWRFS","startTime":"12:00:00","endTime":"19:00:00","workOffered":["Fertilization","Mowing"]}


mysqli_close($conn);
?>
