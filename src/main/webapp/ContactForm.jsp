<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contract</title>
<style type="text/css">
body{
background-image:url(images/backimg.jpg);
background-size:cover;
background-repeat: no-repeat;

}
	h1{
		text-align: center;
	}
	.main-div{
	    margin-top:1.5in;
	    background-color:white;
		background-color:#ffffff;
		opacity: 0.9;
		width: 30%;
		margin-left: 33%;
		padding-bottom: 20px;
		padding: 45px;
		border-radius: 50px;
		padding-left: 50px;
		
	}
	lable,input,select{
	background-color:white;
		margin-bottom: 30px;
		font-size: larger;
		text-align: center;
		border: none;
		border-bottom: 1px solid black;
		text-align: center;
		
	}
	button {
	padding: 20px;
	padding-left: 40px;
	margin-left: 30%;
	padding-right: 40px;
}
.message{
	margin-top: 20px;
	font-size: largest;
}

#loader{
	position: fixed;
	width: 100%;
	height: 100vh;
	background:  url(images/loading.gif) no-repeat center;
	z-index: 999;
	display: none;
	}
</style>
</head>
<body onload="SetCaptcha()">
	<div id="loader"></div>
	<div class="main-div">
	
		<form onsubmit="event.preventDefault(); Submit(); ">
				
	           <div>
	               <label for="name">Name:</label>
	               <input  type="text" name="name"  value="" id="name">
	           </div>
	           
	           <div>
	               <label for="email">Email :</label>
	               <input type="email" name="email" value="" id="email">
	           </div>
	           <div>
	               <label for="phone">Mobile No :</label>
	               <input type="text" name="phone"  value="" id="phone">
	           </div>
	           <div>
	               <table>
					  <tr>
					    <th><label for="message">Message :</label></th>
					    
					  </tr>
					  <tr>
					    <td><textarea rows="5" cols="30" placeholder="Write Your Message" id="message"></textarea></td>
					    
					  </tr>
					</table>	
	           </div>
	           <div>
	           	<table>
	           		<tr>
						<th colspan="2" style="margin-left: 20px;"><h3 id="captcha"></h3></th>
					</tr>
	           	</table>
	           	<div>
	           		<label for="user_captcha">Enter the Captcha:</label>
	           		<input type="text" id="user_captcha">
	           	</div>
	           </div>
	           
	           <button>Submit</button>
	           
	           <div >
	           		<h1 id="display"></h1>
	           </div>
	      </form>
	
	</div>
<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
		
<script type="text/javascript">
		
		var preloader = document.getElementById("loader");
		
	
		var captcha =0;
		function SetCaptcha()
		{
			captcha = Math.floor(100000 + Math.random() * 900000);
			document.getElementById("captcha").innerHTML = captcha;
		}
		
		function Submit() 
		{
			var user_captcha = document.getElementById("user_captcha").value;
			var name = document.getElementById("name").value;
			var email = document.getElementById("email").value;
			var phone = document.getElementById("phone").value;
			var message = document.getElementById("message").value;
			
			if(user_captcha == captcha)
			{
				preloader.style.display = 'block';
				
				//
				$.ajax({
					url:"ContactForm",
					method:"post",
					data:{"name":name, "email": email, "phone": phone, "message": message},
					success :function(response)
					{
						
						preloader.style.display = 'none';
						if(response == "1" )
							{
							
								document.getElementById('display').innerHTML="Message Successfully Send";
							}
						else
							{
								document.getElementById('display').innerHTML="Message Successfully Send";
							}
					}
						
				})
			}
			else
			{
				//document.getElementById('display').innerHTML="Captcha Mismatched";
				
				alert("Captcha Mismatched");
				setCatpcha();
				document.getElementById("user_captcha").value = "";
			}
			
		}
		
</script>
</body>
</html>