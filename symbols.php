<?php

/*
header("Access-Control-Allow-Orgin: *");
header("Access-Control-Allow-Methods: *");
header("Content-Type: application/json");
*/

require_once('php/functions.php');

$db = openDatabaseConnection();
$symbols = getSymbolData($db);
echo json_encode($symbols);

?>
