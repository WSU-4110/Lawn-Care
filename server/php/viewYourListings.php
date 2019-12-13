<?php
require "conn.php";
//sample input
//email=johnsmith@gmail.com
$email=$_POST["email"];

//getting each property info based on owner email
$property=mysqli_prepare($conn, "select propertyNumber,street,city,state,zip,lawnSize,equipmentAvailable from propertyInfo where ownerEmail=?");
mysqli_stmt_bind_param($property,"s",$email);
if(mysqli_stmt_execute($property)){
    mysqli_stmt_store_result($property);
    mysqli_stmt_bind_result($property,$propertyNumber,$street,$city,$state,$zip,$lawnSize,$equipmentAvailable);
    
    $response=array();
    $response["success"]=true;
    $i=0;
    while(mysqli_stmt_fetch($property)){
        $response[$i]["propertyNumber"]=$propertyNumber;
        $response[$i]["street"]=$street;
        $response[$i]["city"]=$city;
        $response[$i]["state"]=$state;
        $response[$i]["zip"]=$zip;
        $response[$i]["lawnSize"]=$lawnSize;
        $response[$i]["equipmentAvailable"]=$equipmentAvailable;
        
        //getting work categories
        $response[$i]["workNeeded"]=array();
        $workNeeded=mysqli_prepare($conn, "select workNeeded from propertyWork where propertyNumber=?");
        mysqli_stmt_bind_param($workNeeded,"i",$propertyNumber);
        mysqli_stmt_execute($workNeeded);
        mysqli_stmt_store_result($workNeeded);
        mysqli_stmt_bind_result($workNeeded, $workType);
        
        while(mysqli_stmt_fetch($workNeeded)){
            //print($workType);
            $response[$i]["workNeeded"][]=$workType;
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
//response[i] is a row of data, each attribute accessible by it's name ("street" for street address, etc.)
echo json_encode($response);
//SAMPLE RESPONSE
//{"success":true,"0":{"propertyNumber":1,"street":"23 Green Street","city":"Grover","state":"Michigan","zip":"48391","lawnSize":8000,"equipmentAvailable":1,"workNeeded":["Mowing"]},"1":{"propertyNumber":2,"street":"25 Green Street","city":"Grover","state":"Michigan","zip":"48391","lawnSize":7600,"equipmentAvailable":0,"workNeeded":["Mowing","Pest Control"]}}

mysqli_close($conn);
?>