<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname='jsonlogin';

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 
echo "Connected successfully";

/*$sql = "CREATE DATABASE Malvi";
if ($conn->query($sql) === TRUE) {
    echo "Database created successfully";
} else {
    echo "Error creating database: " . $conn->error;
}

$conn->close();
*/
$password=$_REQUEST['password'];
$username=$_REQUEST['username'];
$email=$_REQUEST['email'];
$gender=$_REQUEST['gender'];



$sql = "INSERT INTO users (username, email,password,gender)
VALUES ('$username', '$email','$password','$gender')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>
