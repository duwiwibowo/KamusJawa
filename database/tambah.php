<?php
	require_once "connection.php";
	$json = array();
	$aksi = $_POST['action'];
	$id = $_POST['id'];
	$indo = $_POST['bIndo'];
	$jawa = $_POST['bJawa'];
	
	// $var = "minum";
	if($aksi == "insert"){
		$sql = "insert into kosakata values(null, '".$indo."','".$jawa."')";
		$q = mysql_query($sql);
		if($q==1){
			$json['status']=1;
			
		}else{
			$json['status']=0;
		}
		echo json_encode($json);
	}else if($aksi=="update"){
		$sqlselect = "update kosakata set indonesia = '".$indo."', jawa = '".$jawa."' where id = '".$id."'";
		$hasil = mysql_query($sqlselect);
		if($hasil){
			$json['status']=1;
			
		}else{
			$json['status']=0;
		}
		echo json_encode($json);
	}else if($aksi == "delete"){
		$q = mysql_query("delete from kosakata where id = '".$id."'");
		if($q == true){
			$json['status']=1;
		}else{
			$json['status']=0;
		}
		echo json_encode($json);
	}
	
	
?>