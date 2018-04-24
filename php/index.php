<?php

require_once('./blog.php');

ini_set('display_errors', 'On');
error_reporting(E_ALL);

// check if this is GET or POST request
if (filter_input(INPUT_SERVER, 'REQUEST_METHOD') === 'GET') {

  try {
    $blog = new Blog();
    $allPosts = $blog->getAllPosts();
  } catch (Exception $e) {
    print 'Exception : ' . $e->getMessage();
  }

  return $allPosts;

} elseif (filter_input(INPUT_SERVER, 'REQUEST_METHOD') === 'POST') {

  try {
    $blog = new Blog();

    $postVars = array();
    parse_str(file_get_contents("php://input"), $postVars);

    $blog->setTitle($postVars["title"]);
    $blog->setBody($postVars["body"]);
    $blog->putEntry();

  } catch (Exception $e) {
    print 'Exception : ' . $e->getMessage();
  }

}

?>
