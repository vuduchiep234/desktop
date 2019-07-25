// Cac thanh phan cua html chay xong roi moi chay JQuery
$(document).ready(function() {
	
	console.log("done");

	$('button').click(function(event) {
		$('h1').animate({
			marginLeft: 500},
			400);
	});
});