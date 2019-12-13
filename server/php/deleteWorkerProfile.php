<?php
require "conn.php";
//sample input
//email=jhooper@yahoo.com
$email=$_POST["email"];

$statement=mysqli_prepare($conn, "DELETE FROM workerWork WHERE workerEmail = ?");
mysqli_stmt_bind_param($statement,"s",$email);
if(mysqli_stmt_execute($statement)){
    $qury = mysqli_prepare($conn, "DELETE FROM workerProfile WHERE workerEmail = ?");
    mysqli_stmt_bind_param($qury,"s",$email);
    if (mysqli_stmt_execute($qury)){
        $stmt = mysqli_prepare($conn, "DELETE FROM accounts WHERE email = ?");
        mysqli_stmt_bind_param($stmt,"s",$email);
        if (mysqli_stmt_execute($stmt))
            echo "Profile has been deleted";
    }
}
else{
    $response=array();
    $response["success"]=false;
}




//returns json string
//response["success"] is true or false
//if it is successful, which it should always be if there is data in the databse
//response[i] is a row of data, each attribute accessible by it's name ("street" for street address, etc.)
//SAMPLE RESPONSE
//{"success":true,"description":"I am a hard worker with 8 years of experience in the lawn care industry. Visit my website to view my credentials and read testimonials","website":"juliushooperlawncare.com","daysAvailable":"UMTWRFS","startTime":"12:00:00","endTime":"19:00:00","workOffered":["Fertilization","Mowing"]}


mysqli_close($conn);
?>