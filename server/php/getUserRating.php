<?php
require "conn.php";
//sample input
////email=jhooper@yahoo.com
$email=$_POST["email"];
$statement=mysqli_prepare($conn, "select rating_star, rating_description, owner_email FROM workerrating where worker_email = ?");
mysqli_stmt_bind_param($statement,"s",$email);
if(mysqli_stmt_execute($statement)){
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement,$rating_star,$rating_description,$owner_email);

    $response["success"]=true;

    while(mysqli_stmt_fetch($statement)){
      $result = array();
      $result["rating_star"]=$rating_star;
      $result["rating_description"]=$rating_description;

        $stmt = mysqli_prepare($conn, "SELECT firstName,lastName FROM lawncare.accounts where email = ?");

        mysqli_stmt_bind_param($stmt,"s",$owner_email);
        if (mysqli_stmt_execute($stmt)) {
          mysqli_stmt_store_result($stmt);
          mysqli_stmt_bind_result($stmt,$firstName, $lastName);
          while(mysqli_stmt_fetch($stmt)) {
            $result["firstName"] = $firstName;
            $result["lastName"] = $lastName;
          }
        }
        $response['result'][] = $result;
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
