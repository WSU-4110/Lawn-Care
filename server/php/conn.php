<?php
mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

$db_name="XXXX";
$mysql_username="XXXXXXX";
$mysql_password="XXXXXX";
$server_name="XXXXXXX";
$server_port="XXXXXXX";
$conn=mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name,$server_port);
?>