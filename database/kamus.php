<?php
	require_once "connection.php";
	$json = array();
	$var = $_POST['bIndo'];

	//$var = "kosong";
	if($var == 'kosong'){
		$sqlselect = "SELECT * FROM `kosakata` order by indonesia asc";
		$hasil = mysql_query($sqlselect);
		while($row = mysql_fetch_assoc($hasil)){
			$menu[] = $row;
		}
		if(is_array($menu)){
			$json['kamusjawa']=$menu;
			echo json_encode($json);
		}
	}else{
		$sqlselect = "select jawa from kosakata where indonesia = '".$var."'";
		$hasil = mysql_query($sqlselect);
		while($row = mysql_fetch_assoc($hasil)){
			$menu[] = $row;
		}
		if(is_array($menu)){
			$json['kamusjawa']=$menu;
			echo json_encode($json);
		}
	}
	
?>