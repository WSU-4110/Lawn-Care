<?php
require "conn.php";
//sample input
$profiles;
if(!isset($_POST["query"]) or empty($_POST["query"]) or $_POST["query"]==""){
$profiles=mysqli_prepare($conn, "select workerEmail,description, website, daysAvailable,startTime,endTime,firstName,lastName, phone from workerProfile,accounts where workerProfile.workerEmail=accounts.email");
}
else{
    $query=$_POST["query"].'%';
    $profiles=mysqli_prepare($conn, "SELECT DISTINCT workerProfile.workerEmail, workerProfile.description, workerProfile.website, workerProfile.daysAvailable, workerProfile.startTime, workerProfile.endTime, accounts.firstName, accounts.lastName, accounts.phone FROM workerProfile, accounts, workerWork WHERE (workerProfile.workerEmail=accounts.email AND workerProfile.workerEmail=workerWork.workerEmail) AND (workerProfile.workerEmail LIKE ? OR workerProfile.description LIKE ? OR workerProfile.website LIKE ? OR accounts.firstName LIKE ? OR accounts.lastName LIKE ? OR workerWork.workOffered LIKE ?)");
    //$profiles=mysqli_prepare($conn, "SELECT workerEmail,description, website, daysAvailable,startTime,endTime,firstName,lastName FROM workerProfile,accounts WHERE workerProfile.workerEmail=accounts.email AND (workerEmail LIKE ? or description LIKE ? or website LIKE ? or firstName LIKE ? or lastName LIKE ?)");
    mysqli_stmt_bind_param($profiles,"ssssss",$query,$query,$query,$query,$query,$query);
}
if(mysqli_stmt_execute($profiles)){
    mysqli_stmt_store_result($profiles);
    mysqli_stmt_bind_result($profiles,$workerEmail,$description,$website,$daysAvailable,$startTime,$endTime,$firstName,$lastName, $phone);
    
    $response=array();
    $response["success"]=true;
    $i=0;
    while(mysqli_stmt_fetch($profiles)){
        $response[$i]["workerEmail"]=$workerEmail;
        $response[$i]["description"]=$description;
        $response[$i]["website"]=$website;
        $response[$i]["daysAvailable"]=$daysAvailable;
        $response[$i]["startTime"]=$startTime;
        $response[$i]["endTime"]=$endTime;
        $response[$i]["firstName"]=$firstName;
        $response[$i]["lastName"]=$lastName;
        $response[$i]["phone"]=$phone;
        
        
        //getting work categories
        $response[$i]["workOffered"]=array();
        $workNeeded=mysqli_prepare($conn, "select workOffered from workerWork where workerEmail=?");
        mysqli_stmt_bind_param($workNeeded,"s",$workerEmail);
        mysqli_stmt_execute($workNeeded);
        mysqli_stmt_store_result($workNeeded);
        mysqli_stmt_bind_result($workNeeded, $workType);
        
        while(mysqli_stmt_fetch($workNeeded)){
            //print($workType);
            $response[$i]["workOffered"][]=$workType;
        }
        $i=$i+1;
    }
}
else{
    $response=array();
    $response["success"]=false;
}




//returns json string
//response["success"] is true or false
//if it is successful, which it should always be if there is data in the databse
echo json_encode($response);
//SAMPLE RESPONSE

mysqli_close($conn);
?>