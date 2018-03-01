<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<style type="text/css">
#background{
	 background-color:#FFFF00;
}
</style>
<title>FormValidation</title>
<script type="text/javascript">
function ValidateInput(){
	var customerName=document.validationform.customerName.value.trim();
	var SSN=document.validationform.socialsecuritynumber.value.trim();
	var regex="/^\(?[0-9]{3}\)?[-.]?([0-9]{3})+[-.]?([0-9]{4})$/";
	if(customerName.length<4 || customerName.length>50 || customerName==""){
		//document.write(customerName.length);
		alert('CustomerName should be minimum of length 4');
		return false;
		//sleep(2000);
		//document.validationform.customerName.focus();
	}
	 if(SSN==""){
		//document.write(customerName.length);
		alert('SSN Number cannot be left blank');
		//sleep(2000);
		document.validationform.socialsecuritynumber.focus();
		return false;
	}
	else if(SSN.match(regex)){
		document.write("matched");
		return true;
		
	}
	else{
		alert('ERROR in phone number validation');
		document.validationform.socialsecuritynumber.focus();
		return false;
	}
}

//ValidateInput();
//document.validationform.addEventListener('click', ValidateInput,false);


</script>
</head>
<body id="background">
<div >
<h1 align="center" font-style="bold" font-size="16px"> Welcome to Registration Form </h1>
<h1 align="center" font-style="italic" font-size="10px">Registration Form</h1>
<form name="validationform" method="post" action="/login" onSubmit="return ValidateInput()">
<table align="center">
<tr>
<td><label> CustomerName: </label></td>
<td>
<input type="text" id="customerName" name="customerName" placeHolder="Vaishnavi"/>  </td>
 </tr>
 <tr>
 <td>
<label> Social Security Number:</label></td> 
<td><input type="text" id="socialsecuritynumber" name="socialsecuritynumber" placeHolder="(312)-(987)-(9867)"/> </td>
</tr>
<tr>
<td>
<label>Zip code:</label>
</td>
<td>
<input type="text" id="zipcode" name="zipcode" placeHolder="56789-890"/> </td>
</tr>
<tr>
<td>
<label>Email Address:</label>
</td>
<td>
<input type="text" id="emailAddress" name="emailAddress" placeHolder="emailAddress"/> </td>
</tr>
<tr>
<td>
<label>Street Address:</label>
</td>
<td>
<input type="text" id="streetAddress" name="streetAddress" placeHolder="streetAddress"/> </td>
</tr>
<tr>
<td>
<label>City</label>
</td>
<td>
<input type="text" id="city" name="city" placeHolder="city"/> </td>
</tr>
<tr>
<td>
<label>State:</label>
</td>
<td>
<select id="state" name="state">
                    <option value="01">IL</option>
                    <option value="02">OH </option>
                    <option value="03">CA</option>
                    <option value="04">TX</option>
                    <option value="05">FL</option>
</select> </td>
</tr>
<tr>
<td>
<div align="right" >
<input type="Submit" class="btn btn-primary" value="Submit" id="submit"/>
</div>
</td>
</tr>
</table>
</form>
</div>
</body>
</html>