<?php
require "conn.php";
//sample input
//email=jhooper@yahoo.com
$email=$_POST["email"];
$statement=mysqli_prepare($conn, "select description,website,daysAvailable,startTime,endTime from workerProfile where workerEmail=?");
mysqli_stmt_bind_param($statement,"s",$email);
if(mysqli_stmt_execute($statement)){
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement,$description,$website,$daysAvailable,$startTime,$endTime);

    $response=array();
    $response["success"]=true;
    while(mysqli_stmt_fetch($statement)){
        $response["description"]=$description;
        $response["website"]=$website;
        $response["daysAvailable"]=$daysAvailable;
        $response["startTime"]=$startTime;
        $response["endTime"]=$endTime;
        
        //getting work categories
        $response["workOffered"]=array();
        $workOffered=mysqli_prepare($conn, "select workOffered from workerWork where workerEmail=?");
        mysqli_stmt_bind_param($workOffered,"s",$email);
        mysqli_stmt_execute($workOffered);
        mysqli_stmt_store_result($workOffered);
        mysqli_stmt_bind_result($workOffered, $workType);
        
        while(mysqli_stmt_fetch($workOffered)){
            //print($workType);
            $response["workOffered"][]=$workType;
        }
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
echo json_encode($response);
//SAMPLE RESPONSE
//{"success":true,"description":"I am a hard worker with 8 years of experience in the lawn care industry. Visit my website to view my credentials and read testimonials","website":"juliushooperlawncare.com","daysAvailable":"UMTWRFS","startTime":"12:00:00","endTime":"19:00:00","workOffered":["Fertilization","Mowing"]}


mysqli_close($conn);
?>