<?php
require "conn.php";
//sample input
//
$properties=mysqli_prepare($conn, "select street,city,state,zip from propertyInfo");
if(mysqli_stmt_execute($properties)){
    mysqli_stmt_store_result($properties);
    mysqli_stmt_bind_result($properties,$street,$city,$state,$zip);
    
    $response=array();
    $response["success"]=true;
    $i=0;
    while(mysqli_stmt_fetch($properties)){
        $response[$i]["street"]=$street;
        $response[$i]["city"]=$city;
        $response[$i]["state"]=$state;
        $response[$i]["zip"]=$zip;
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
// {"success":true,"0":{"street":"23 Jump Street","city":"Los Angeles","state":"LA","zip":"67651"},"1":{"street":"83 Lima
// Avenue","city":"Kalamazoo","state":"MI","zip":"49001"},"2":{"street":"99
// Mo","city":"Polo","state":"AR","zip":"88876"},"3":{"street":"123 Wayne
// ","city":"Detroit","state":"AR","zip":"12345"},"4":{"street":"123
// wayne","city":"Detroit","state":"AK","zip":"12345"},"5":{"street":"67 Moonlight
// Drive","city":"Detroit","state":"KY","zip":"67093"},"6":{"street":"42 Warren
// Avenue","city":"Detroit","state":"KY","zip":"33333"},"7":{"street":"3750
// cass","city":"detroit","state":"MI","zip":"48102"},"8":{"street":"58
// warren","city":"Detroit","state":"MI","zip":"48111"},"9":{"street":"53
// fdff","city":"dddd","state":"AR","zip":"55555"},"10":{"street":"88 Warren","city":"Detroit","state":"MI","zip":"48999"}}
mysqli_close($conn);
?>