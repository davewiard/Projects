<?php

ini_set('display_errors', 'On');
error_reporting(E_ALL);

//
// This is a monolithic script structure that should really be chnaged into
// a class-based structure. That would make it more readable, more maintainable
// and better all around.
//

// check if this is GET or POST request
if (filter_input(INPUT_SERVER, 'REQUEST_METHOD') === 'GET') {
  try {
    # connect to blog.db database
    $db = new PDO('sqlite:../blog.db');

    // perform selection
    // This is notably poor structure for large data sets but works well for
    // the data in the blog.db. Larger data sets should send blocks of results
    // back to the browser instead of all at once.
    $sql = "SELECT * FROM posts";
    $stmt = $db->query($sql);
    $result = $stmt->fetchAll(PDO::FETCH_ASSOC);
    $json = json_encode($result);
    print $json;

    $db = null;
  } catch (\Exception $e) {
    print 'Exception : ' . $e->getMessage();
  }
}

?>
