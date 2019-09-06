// ****************사이드 메뉴**************
function openNav() {
	document.getElementById("mySidenav").style.width = "240px";
	$('.sidenav a').css("display", "");
}

function closeNav() {
	document.getElementById("mySidenav").style.width = "0";
	$('.sidenav a').css("display", "none");
}
