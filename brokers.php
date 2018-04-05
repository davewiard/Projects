<?php

/*
header("Access-Control-Allow-Orgin: *");
header("Access-Control-Allow-Methods: *");
header("Content-Type: application/json");
*/

require_once('php/functions.php');

$db = openDatabaseConnection();
$brokers = getBrokerData($db);
echo json_encode($brokers);

?>