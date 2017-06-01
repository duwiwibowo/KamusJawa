<?php
	$username = "root";
	$password = "";
	$host = "localhost";
	$dbname = "kamusjawa";
	mysql_connect($host, $username, $password) or die ("connection failed");
	mysql_select_db($dbname) or die ("no database");
?>