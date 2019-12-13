<?php
//NEEDS TO BE UPDATED
//NEED TO REDO WORK TYPE STUFF

require "conn.php";

$sql=mysqli_query($conn, "SELECT DISTINCT workOffered from workerWork where workOffered <> ''");
if($sql){
    $response=array();
    $response["success"]=true;
    while($row=mysqli_fetch_assoc($sql)){
        $response[]=$row;
    }
}
else{
    $response=array();
    $response["success"]=false;
}


//returns json string
//response["success"] is true or false
//if it is successful, which it should always be if there is data in the database
//response[i] is a row of data, each attribute accessible by it's name ("street" for street address, etc.)
echo json_encode($response);

//mysqli_free_result($statement);

mysqli_close($conn);
?>