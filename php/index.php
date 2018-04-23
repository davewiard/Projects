<?php

require_once('./blog.php');

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
    $blog = new Blog();
    $allPosts = $blog->GetAllPosts();
  } catch (Exception $e) {
    print 'Exception : ' . $e->getMessage();
  }

  return $allPosts;
} elseif (filter_input(INPUT_SERVER, 'REQUEST_METHOD') === 'PUT ') {
  try {

  } catch (Exception $e) {
    print 'Exception : ' . $e->getMessage();
  }
}

?>
