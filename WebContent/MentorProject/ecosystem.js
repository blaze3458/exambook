var local_selected = 0;
window.addEventListener("resize",setScrollSize);
window.onload = function(){
	
	var rep_buttons = document.getElementsByClassName("view-reply-button");
	
	for(var i = 0; i<rep_buttons.length; i++)
		rep_buttons[i].addEventListener("click",function(){
			//var reply = this.parentNode.parentNode.parentNode;
			var reply = findParentBySelector(this, ".reply-container");
			var replies = reply.querySelectorAll(".reply-container .reply-container");
			if(this.getAttribute("aria-checked") == "false"){
				for(var k = 0; k< replies.length; k++){
					replies[k].style.display = "flex";
				}
				this.setAttribute("aria-checked","true");
				this.innerHTML ="View all "+replies.length+" replies";
			}
			else{
				for(var k = 0; k< replies.length; k++){
					replies[k].style.display = "none";
				}
				this.setAttribute("aria-checked","false");
				this.innerHTML ="Hide replies";
			}
		});
	
	var checks = document.getElementsByClassName("checkbox");
	for(var i = 0; i<checks.length; i++)
	{
		if(checks[i].getAttribute("aria-labelledby") == "check_all")
			checks[i].addEventListener("click",clickCheckbox);
		else
			checks[i].addEventListener("click",function()
			{
				if(this.getAttribute("aria-checked") == "false")
				{
					this.setAttribute("aria-checked","true");
					var par = this.parentElement.parentElement.parentElement;
					par.style.backgroundColor = '#E4F5FF';
					local_selected = local_selected + 1;	
				}
				else
				{
					this.setAttribute("aria-checked","false");
					var par = this.parentElement.parentElement.parentElement;
					par.style.backgroundColor = 'transparent';
					local_selected = local_selected - 1;
				}
				printSelected();
			});
	}
	
	var mails = document.querySelectorAll('[role="mail-button"]');
	for(var i = 0; i<mails.length; i++)
	{
		mails[i].addEventListener("click",clickMail);
	}
	
	setScrollSize();
	setReplies();
}

function setScrollSize()
{
	var heightSize = window.innerHeight;
	var widthSize = window.innerWidth;
	
	document.getElementsByClassName("scroll-container")[0].style.height = heightSize - 159+"px";
	
	var top = parseInt(window.getComputedStyle(document.getElementsByClassName("topnav")[0]).getPropertyValue("width"));
	var left = parseInt(window.getComputedStyle(document.getElementsByClassName("left-menu-container")[0]).getPropertyValue("width"));
	var pad_right = parseInt(window.getComputedStyle(document.getElementsByClassName("popup-inner")[0]).getPropertyValue("padding-right"));
	
	pad_right =pad_right+ parseInt(window.getComputedStyle(document.getElementsByClassName("replies-container")[0]).getPropertyValue("padding-right"));
var pad_left = parseInt(window.getComputedStyle(document.getElementsByClassName("replies-container")[0]).getPropertyValue("padding-left"));
	console.log("Top:"+top+" left:"+left+" padRight:"+pad_right);
	
	document.getElementsByClassName("replies-scroll-container")[0].style.width = top - (left+pad_right+pad_left+10)+"px";
}

function setReplies(){
	var rep = document.getElementsByClassName("reply-container");
	
	for(var i = 0; i<rep.length; i++)
	{
		var x = rep[i].querySelectorAll(".reply-container .reply-container");
		console.log(x.length);
		if(x.length >= 1){
			for(var k = 0; k< x.length; k++){
				x[k].style.display = "none";
			}
		}
		else{
			var show_button = rep[i].getElementsByClassName("view-reply-button");
			show_button[0].style.display = "none";
		}
	}
}

function clickMail(){
	console.log(this.getAttribute("aria-labelledby"));
	//Mail açmak için
}

function clickCheckbox(){
	var checks = document.getElementsByClassName("checkbox");
	var mail = document.getElementsByClassName("inbox-mail");
	var x = document.querySelector('[aria-labelledby="check_all"]');
	
	if(x.getAttribute("aria-checked") == "false")
	{
		x.setAttribute("aria-checked","true");
		local_selected = mail.length;
		for(var i = 0; i<mail.length; i++)
			mail[i].style.backgroundColor  = '#E4F5FF';
	}
	else
	{
		x.setAttribute("aria-checked","false");
		local_selected = 0;
		for(var i = 0; i<mail.length; i++)
			mail[i].style.backgroundColor  = 'white';
	}
	for(var i = 0; i<checks.length; i++)
	{
		if(x.getAttribute("aria-checked") == "false" )
		{
			checks[i].setAttribute("aria-checked","false");
		}
		else
		{
			checks[i].setAttribute("aria-checked","true");
		}
	}
	printSelected();
}

function printSelected(){
	
	var selected_text = document.getElementById("selected_mail");
	
	if(local_selected == 0)
		selected_text.style.visibility = "hidden";
	else
		selected_text.style.visibility = "visible";
			
	selected_text.innerHTML = local_selected + " Selected";
}

function collectionHas(a, b) { //helper function (see below)
    for(var i = 0, len = a.length; i < len; i ++) {
        if(a[i] == b) return true;
    }
    return false;
}
function findParentBySelector(elm, selector) {
    var all = document.querySelectorAll(selector);
    var cur = elm.parentNode;
    while(cur && !collectionHas(all, cur)) { //keep going up until you find a match
        cur = cur.parentNode; //go up
    }
    return cur; //will return null if not found
}


//Butonlar
//connect-linkedin
//logout
//start



//