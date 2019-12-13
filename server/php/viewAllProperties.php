<?php
require "conn.php";
//sample input
$properties;
if(!isset($_POST["query"]) or empty($_POST["query"]) or $_POST["query"]==""){
$properties=mysqli_prepare($conn, "select propertyNumber,street,city,state,zip,lawnSize,equipmentAvailable from propertyInfo");
}
else{
    $query=$_POST["query"].'%';
    $properties=mysqli_prepare($conn, "select DISTINCT propertyInfo.propertyNumber,street,city,state,zip,lawnSize,equipmentAvailable from propertyInfo, propertyWork where (propertyInfo.propertyNumber=propertyWork.propertyNumber) AND (street LIKE ? or city LIKE ? or state LIKE ? or zip LIKE ? or lawnSize LIKE ? or workNeeded LIKE ?)");
    mysqli_stmt_bind_param($properties,"ssssss",$query,$query,$query,$query,$query,$query);
}
if(mysqli_stmt_execute($properties)){
    mysqli_stmt_store_result($properties);
    mysqli_stmt_bind_result($properties,$propertyNumber,$street,$city,$state,$zip,$lawnSize,$equipmentAvailable);
    
    $response=array();
    $response["success"]=true;
    $i=0;
    while(mysqli_stmt_fetch($properties)){
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
        mysqli_stmt_bind_param($workNeeded,"s",$propertyNumber);
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
echo json_encode($response);
//SAMPLE RESPONSE

mysqli_close($conn);
?>