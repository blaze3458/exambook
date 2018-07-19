
$(document).on("click", "#next_button", function() {
	
	var radios = document.getElementsByName('radio');
	for (var i = 0, length = radios.length; i < length; i++)
    {
     if (radios[i].checked)
     {
      console.log("Checked: "+radios[i].value);
      var checked = radios[i].value;
      $.ajax({
    	    url:"get_next_quest",
    	    type:"POST",
    	    dataType:'checked',
    	    data: {checked:checked},
    	    success:function(data){
    	    },
    	});
      radios[i].checked = false;
      break;
     }
    }
	
    $.get("get_next_quest", function(responseJson) {
    	console.log(responseJson);
    	$.each(responseJson, function(index,value) {
    		console.log(index + ":"+value);
    		if(index == 0)
    			$("#quest").text(value);
    		else if(index == 5)
    			$("#quest-number").text(value);
    		else if(index == 6)
    			for (var i = 0, length = radios.length; i < length; i++)
    			{
    				if(radios[i].value == value)
    					radios[i].checked = true;
    		    }
    		else
    			$("#button_"+index).text(value);
 
    	});
    });
});

$(document).on("click", "#prev_button", function() {
	
	var radios = document.getElementsByName('radio');
	for (var i = 0, length = radios.length; i < length; i++)
    {
     if (radios[i].checked)
     {
      console.log("Checked: "+radios[i].value);
      var checked = radios[i].value;
      $.ajax({
    	    url:"get_prev_quest",
    	    type:"POST",
    	    dataType:'checked',
    	    data: {checked:checked},
    	    success:function(data){
    	    },
    	});
      
      radios[i].checked = false;
      break;
     }
    }
	
    $.get("get_prev_quest", function(responseJson) {   
    	console.log(responseJson);
    	$.each(responseJson, function(index,value) {
    		console.log(index + ":"+value);
    		if(index == 0)
    			$("#quest").text(value);
    		else if(index == 5)
    			$("#quest-number").text(value);
    		else if(index == 6)
    			for (var i = 0, length = radios.length; i < length; i++)
    		    {
    				if(radios[i].value == value)
    					radios[i].checked = true;
    		    }
    		else
    			$("#button_"+index).text(value);
    	});
    });
});

$(document).on("click", "#finish_button", function(){
	
	var radios = document.getElementsByName('radio');
	var checked;
	for (var i = 0, length = radios.length; i < length; i++)
    {
     if (radios[i].checked)
     {
      console.log("Checked: "+radios[i].value);
      checked = radios[i].value;
      radios[i].checked = false;
      break;
     }
    }
	 $.ajax({
			url:"FinishExam",
			type:"POST",
			dataType:'checked',
			data: {checked:checked},
	    success:function(data){},
	});
	 
	 document.getElementById("finish").submit();
});


$(document).on("click", "#exam_results", function(){
	$.get("GetResults", function(responseJson) {
		console.log(responseJson);
		$("#content").text("My Exam Results");
		$("#content").append("<ul class=\"results\"></ul>");
    	$.each(responseJson, function(index,value) {
    		if(index % 2 == 0)
    			$(".results").append("<li class=\"result-list\">"+value+" ");
    		else
    			$(".results").append("<center>"+value+"</center></li>");
    	});
	});
});

$(document).on("click", "#show_old_questions", function(){
	$.get("GetOldQuestions", function(responseJson) {
		console.log(responseJson);
		$("#content").text("My old questions");
		$("#content").append("<ul class=\"results\"></ul>");
    	$.each(responseJson, function(index,value) {
    		$(".results").append("<li class=\"result-list\">"+value+"</li> <ul id="+value+" class=\"old-quest\"></ul>");
    		PostFunc(value);
    	});
	});
});

function PostFunc(value){
	var id = "#"+value;
	console.log(id);
	$.post("GetOldQuestions",{name:value},function(response){
		$.each(response, function(index2,value2){
			
			console.log(index2+":"+value2);
			$(id).append("<li>"+value2+"</li>");
		});
	});
}

$(document).on("click","#new_course", function(){
	$("#content").load("forms.txt #add_class");
});

$(document).on("click","#new_student", function(){
	$("#content").load("forms.txt #add_student");
	$.get("add_student",function(responseJson){
		$("#teacher-students").text("");
		$.each(responseJson, function(index,value) {
			$("#teacher-students").append("<p>"+value+"</p>");
		});
	});
});

$(document).on("click","#new_quest", function(){
	$("#content").load("forms.txt #add_question");
	$.get("GetCourses", function(responseJson) {
		console.log(responseJson);
		$("#courses").first().text("<p>ADD QUESTIONS</p>");
    	$.each(responseJson, function(index,value) {
    		$("#courses").first().append("<option value=\""+value+"\">"+value+"</option>");
    	});
	});
});

$(document).on("click ","#assign_course", function(){
	$("#content").load("forms.txt #assign_course_form");
	$.get("GetCourses", function(responseJson) {
		console.log(responseJson);
		$("#course_select").first().text("");
    	$.each(responseJson, function(index,value) {
    		$("#course_select").first().append("<option value=\""+value+"\">"+value+"</option>");
    	});
	});
});

$(document).on("change","#course_select",function(){
	var value = $("#course_select").val();
	$.get("assign_course",{val:value},function(responseJson){
		$(".student-checkbox").text("");
		$.each(responseJson, function(index,value) {
			$(".student-checkbox").append("<p><input type=\"checkbox\" name = stu_id value = \""+index+"\">"+value+"</p>");
		});
	});
});

$(document).on("change","#courses",function(){
	var value = $("#courses").val();
	$.post("GetOldQuestions",{name:value},function(responseJson){
		$("#add_old_questions").text("");
		$("#add_old_questions").append("<ul class=\"results\"></ul>");
    	$.each(responseJson, function(index,value) {
    		$(".results").append("<li class=\"result-list\">"+value+"</li> <ul id="+value+" class=\"old-quest\"></ul>");
    		PostFunc(value);
    	});
	});	
});

$(document).on("click", "#change_password", function(){document.getElementById("change_pass").submit();});

window.onload = function()
{	
	window.addEventListener("scroll", function () {fix_sidemenu(); });
}
function fix_sidemenu() {
	
	var top;
	top = scrolltop()
	var nav = $('.sidenav');
	var contentNav = nav.css("top");
	
	if(top == 0)
		nav.css("top","105px");
	
	else if(top > 0 && top < 105)
	{
		var x = 105 - top;
		nav.css("top",x);
	}
	
	else if(top > 105)
		nav.css("top","0px");
	
	console.log(top);	
}

function scrolltop() 
{
	var top = 0;
	if (typeof(window.pageYOffset) == "number") 
	{top = window.pageYOffset;} 
	
	else if (document.body && document.body.scrollTop) 
	{top = document.body.scrollTop;} 
	
	else if (document.documentElement && document.documentElement.scrollTop) 
	{top = document.documentElement.scrollTop;}
	
	return top;
}

function countChar(val) {
    var len = val.value.length;
    if (len >= 500) {
      val.value = val.value.substring(0, 500);
    } else {
      $('#charNum').text(len+"/"+500);
    }
  };
 
$(document).on('submit','#add_student',function(){
	   var input = $("[name=ID]");
	   if(validate_stu(input.val())== false)
	    {
	    	   var thisAlert = $(input).parent();

	    	    $(thisAlert).addClass('alert-validate-id');
	    	    
	    	    return false;
	    }
	 return true;
});

function validate_stu (input) {

        if(input.trim().match(/([A-Z][0-9]{1,4})\.([0-9]{1,6})/) == null) {
            return false;
        }
        if(input.trim() == ''){
            return false;
        }
}