<?php
function write($txt)
{
	$my_file = fopen("84data18.txt", "w+") or die("Error: Unable to open file!");
	fwrite($my_file, $txt);
	fclose($my_file);
}
function read()
{
	$my_file = fopen("84data18.txt", "r") or die("Error: Unable to open file!");
	$text = fread($my_file, filesize("84data18.txt"));
	fclose($my_file);
	return $text;
}
function read_history()
{
	$my_file = fopen("84data_h18.txt", "r") or die("Error: Unable to open file!");
	$text = fread($my_file, filesize("84data_h18.txt"));
	fclose($my_file);
	return $text;
}
if(isset($_GET['var1']))
{
	echo read();
}
else if(isset($_GET['var2']))
{
	$str=$_GET['txt'];
	write($str);
	echo "done";
}
else if(isset($_GET['var3']))
{
	echo read_history();
}
else if(isset($_GET['var4']))
{
	echo put_history();
}
else
	echo "Error: Could not complete request"

?>