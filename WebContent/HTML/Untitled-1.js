window.onload = function()
{	
	
}// JavaScript Document

function open_menu() {
	  var x, m;
	  m = (document.getElementById("leftmenu") || document.getElementById("sidenav"));
	  if (m.style.display == "block") {
	    close_menu();
	  } else 
	  {
	    w3_close_all_nav();  
	    m.style.display = "block";
	    if (document.getElementsByClassName) {
	      x = document.getElementsByClassName("chapter")
	      for (i = 0; i < x.length; i++) {
	        x[i].style.visibility = "hidden";
	      }
	      x = document.getElementsByClassName("nav")
	      for (i = 0; i < x.length; i++) {
	        x[i].style.visibility = "hidden";
	      }
	      x = document.getElementsByClassName("sharethis")
	      for (i = 0; i < x.length; i++) {
	        x[i].style.visibility = "hidden";
	      }
	    }
	    fix_sidemenu();
	  }
}

function close_menu() {
	  var m;
	  m = (document.getElementById("leftmenu") || document.getElementById("sidenav"));
	  m.style.display = "none";  
	  if (document.getElementsByClassName) {
	    x = document.getElementsByClassName("chapter")
	    for (i = 0; i < x.length; i++) {
	      x[i].style.visibility = "visible";
	    }
	    x = document.getElementsByClassName("nav")
	    for (i = 0; i < x.length; i++) {
	      x[i].style.visibility = "visible";
	    }
	    x = document.getElementsByClassName("sharethis")
	    for (i = 0; i < x.length; i++) {
	      x[i].style.visibility = "visible";
	    }            
	  }
	}
	if (window.addEventListener) {
	  window.addEventListener("scroll", function () {fix_sidemenu(); });
	  window.addEventListener("resize", function () {fix_sidemenu(); });  
	  window.addEventListener("touchmove", function () {fix_sidemenu(); });  
	  window.addEventListener("load", function () {fix_sidemenu(); });
	} else if (window.attachEvent) {
	  window.attachEvent("onscroll", function () {fix_sidemenu(); });
	  window.attachEvent("onresize", function () {fix_sidemenu(); });  
	  window.attachEvent("ontouchmove", function () {fix_sidemenu(); });
	  window.attachEvent("onload", function () {fix_sidemenu(); });
}
	
function fix_sidemenu() {
	  var w, top;
	  w = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
	  top = scrolltop()
	  if (w < 993 && w > 600) 
	  {
	    if (top == 0) {
	      document.getElementById("sidenav").style.top = "144px";
	    }
	    if (top > 0 && top < 100) {
	      document.getElementById("sidenav").style.top = (144 - top) + "px";      
	    }
	    if (top > 100) {
	      document.getElementById("sidenav").style.top = document.getElementById("topnav").offsetHeight + "px";
	      document.getElementById("belowtopnav").style.paddingTop = "44px";    
	      document.getElementById("topnav").style.position = "fixed";    
	      document.getElementById("topnav").style.top = "0";
	    //  document.getElementById("googleSearch").style.position = "fixed";
	    //  document.getElementById("googleSearch").style.top = "0";
	    //  document.getElementById("google_translate_element").style.position = "fixed";
	    //  document.getElementById("google_translate_element").style.top = "0";
	    } else {
	      document.getElementById("belowtopnav").style.paddingTop = "0";
	      document.getElementById("topnav").style.position = "relative";
	 //     document.getElementById("googleSearch").style.position = "absolute";
	 //    document.getElementById("googleSearch").style.top = "";
	 //     document.getElementById("google_translate_element").style.position = "absolute";
	  //    document.getElementById("google_translate_element").style.top = "";
	    }
	    document.getElementById("leftmenuinner").style.paddingTop = "0"; //SCROLLNYTT
	  } 
	  else 
	  {
	    if (top == 0) 
	    {
	      document.getElementById("sidenav").style.top = "112px";      
	    }
	    if (top > 0 && top < 66) {
	      document.getElementById("sidenav").style.top = (112 - top) + "px";      
	    }
	    if (top > 66) 
	    {
	      document.getElementById("sidenav").style.top = "44px";
	      if (w > 992) {document.getElementById("leftmenuinner").style.paddingTop = "0px";} //SCROLLNYTT
	      document.getElementById("belowtopnav").style.paddingTop = "44px";    
	      document.getElementById("topnav").style.position = "fixed";
	      document.getElementById("topnav").style.top = "0";
	   //   document.getElementById("googleSearch").style.position = "fixed";
	   //   document.getElementById("googleSearch").style.top = "0";
	   //   document.getElementById("google_translate_element").style.position = "fixed";
	   //   document.getElementById("google_translate_element").style.top = "0";
	    } 
	    else 
	    {
	      if (w > 992) { document.getElementById("leftmenuinner").style.paddingTop = "0px";} //SCROLLNYTT
	      document.getElementById("belowtopnav").style.paddingTop = "0";
	      document.getElementById("topnav").style.position = "relative";
	    }
	  }
}

function w3_close_all_nav() {
	  //w3_close_nav("tutorials");
	 // w3_close_nav("references");
	  //w3_close_nav("examples");
	  close_menu();
	}
	(function () {
	  var x, i, a, b, c, d, m;
	  m = (document.getElementById("leftmenu") || document.getElementById("sidenav"));
	  x = m.getElementsByTagName("a");
	  d = document.location.href;
	  for (i = 0; i < x.length; i++) {
	    if (d.indexOf(x[i].href) >= 0) {
	      x[i].className = "active";
	     } else if (d.indexOf("/tags/att_") > -1) {
	       c = d.substring(d.indexOf("/tags/att_") + 10, d.lastIndexOf("_"));
	       if (x[i].href == d.substr(0, d.indexOf("/tags/")) + "/tags/tag_" + c + ".asp") {
	         x[i].className = "active";
	       }
	     }
	  }
	  sidemenuitemintoview()
	  x = document.getElementById("topnav").getElementsByTagName("A");
	  for (i = 0; i < x.length; i++) {
	    a = document.location.pathname;
	    b = x[i].pathname;
	    if (x[i].parentNode.tagName == "LI" && a.substr(0, a.indexOf("/",1)) ==  b.substr(0, b.indexOf("/",1))) {
	      x[i].className = "active";
	     }
	  }
	  if (window.addEventListener) 
	  { 
	    document.getElementById("main").addEventListener("click", w3_close_all_nav, true);
	   m.addEventListener("click", w3_close_all_nav, true);
	   // document.getElementById("right").addEventListener("click", w3_close_all_nav, true);
	  } else if (window.attachEvent) {         
	   document.getElementById("main").attachEvent("onclick", w3_close_all_nav);
	    m.attachEvent("onclick", w3_close_all_nav);
	   // document.getElementById("right").attachEvent("onclick", w3_close_all_nav);
	  }
	  if ('ontouchstart' in window || 'onmsgesturechange' in window) {
	    document.getElementById("leftmenuinnerinner").style.overflowY = "scroll";
	  }
})();
	
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

function sidemenuitemintoview() 
{
	  var a, b, i = 0;
	  a = document.getElementById("leftmenuinnerinner");
	  if (!a || !a.getElementsByClassName) {return false;}
	  b = a.getElementsByClassName("active");
	  if (b.length < 1) {return false;}  
	  while (!isIntoView(a, b[0])) {
	    i++
	    if (i > 1000) {break;}
	    a.scrollTop += 10;
	  }
}

function isIntoView(x, y)
{
	  var a = x.scrollTop;
	  var b = a + window.innerHeight;
	  var ytop = y.offsetTop;
	  var ybottom = ytop + 140;
	  return ((ybottom <= b) && (ytop >= a));
}