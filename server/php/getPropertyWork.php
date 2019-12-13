<?php
//NEEDS TO BE UPDATED
//NEED TO REDO WORK TYPE STUFF

require "conn.php";

$stmt = $conn->prepare(
//    "SELECT distinct workOffered, isOffred FROM lawncare.workerwork");
    "SELECT distinct workNeeded FROM propertyWork");
    $stmt->execute();
//    $stmt->bind_result($workOffered, $isOffred);
    $stmt->bind_result($workOffered);
   
$response = array();    

$work = array(); 
    
while($stmt->fetch()){
    $theWork  = array();
    $theWork['workNeeded'] = $workOffered;
//    $theWork['isOffred'] = $isOffred;
    array_push($work, $theWork); 
}
$response['work'] = $work;

echo json_encode($response);
mysqli_close($conn);

?>