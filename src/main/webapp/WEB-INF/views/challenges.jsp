<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<!--Created by Vladimir Zhdanov on 15.03.2015-->
<head>
<<<<<<< HEAD
<TITLE>CCloud|Challenges</TITLE>
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
    <link rel="stylesheet" href="./resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="./resources/css/main.css">
    <link rel="stylesheet" href="./resources/css/settings.css"> 
 
=======
<TITLE>Challenges</TITLE>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/settings.css" />"> 
>>>>>>> origin/master
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
	var num = -4;// for numeration from 1
	function appendChallenges() {
    	num = num + 5;
        $.ajax({
            url : 'challenges-all.html?num=' + num.toString(),
            success : function(data) {
                $('#challenges-all').append(data);
        	}
        });
	}
</script>

<script type="text/javascript">
	var loading = false;
	$(window).scroll(function(){
	//if((($(window).scrollTop()+$(window).height())+2)>=$(document).height()){ //Doesn't work
	if($(document).height() <= $(window).scrollTop() + 1920){
		if(loading == false){
			loading = true;
				appendChallenges();
			loading = false;
		}
		  
	}
	});
	$(document).ready(function() { //Works
		//alert("ready") 
		appendChallenges();
		$(window).scrollTo(0);
	});
</script> 
</head>
 
<body>
<div class="main">
		<div class="nav navigator">
			<div class="container">
				<div class="row">
					<div class="col-md-3">
						<a href="#"> <i class="fa fa-cloud"></i>ChallengeCloud
						</a>
					</div>

					<div class="col-md-9">
						<ul class="pull-left">
							<li><a href="./home.jsp">Home</a></li>
							<li><a href="#">About</a></li>
							<li><a href="#">Our Team</a></li>
						</ul>
						<ul class="pull-right">
							<li><a href="#">Log out</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<br />
		<!-- For Horizontal and Vertical lines not contact -->

    <div class="row">
        <div class="col-md-3">
            <div class="menu">
                <!-- <ul class="nav nav-pills nav-stacked"> -->
                <ul class="menu">
                    <li role="presentation" class="active"><a href="#">Challenges</a></li>
                    <li role="presentation"><a href="#">Trend</a></li>
                    <li role="presentation"><a href="#">Trophy Room</a></li>
                    <li role="presentation"><a href="./settings.jsp">Settings</a></li>
                </ul>
            </div>
        </div>

<<<<<<< HEAD
        <div class="col-md-6">
            <!-- Central col -->
            <div class="row">
                <div class="heading">
                    <h1>Challenges</h1>
                </div>
                <div class="menu2">
                    <ul class="nav nav-pills">
                        <li role="presentation" class="active"><a href="#">All</a></li>
                        <li role="presentation"><a href="#">Recommendations</a></li>
                        <li role="presentation"><a href="#">Random</a></li>
                    </ul>
                </div>
                <!-- Pills -->
            </div>
            <br/>
            <!-- Challenges -->
            <div>
                <div id="challenges-all"> <!-- Ajax requested. Created by Vova on 15.03.2015 -->
                </div>
            </div>
            <!-- End of Challenges -->

        </div>
        <!-- End of Central col -->
        <div></div>
    </div>
=======
			<div class="col-md-6">
				<!-- Central col -->
				<div class="row">
					<div class="heading">
						<h1>Challenges</h1>
					</div>
					<div class="menu2">
						<ul class="nav nav-pills">
							<li role="presentation" class="active"><a href="#">All</a></li>
							<li role="presentation"><a href="#">Recommendations</a></li>
							<li role="presentation"><a href="#">Random</a></li>
						</ul>
					</div>
					<!-- Pills -->
				</div>
                <br/>
                
				<!-- Challenges -->
				<div>
        			<div id="challenges-all"> <!-- Ajax requested. Created by Vova on 15.03.2015 -->
        			</div>
				</div>
				<!-- End of Challenges -->
			</div>
			<!-- End of Central col -->
			<div></div>
		</div>
>>>>>>> origin/master
	</div>
</body>
</html>