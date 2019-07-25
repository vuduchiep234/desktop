jQuery(document).ready(function($) {
	$('#submit').click(function(event) {
		window.location.href = "/payment/administrative/step2";
	});

	$('#paymentAdministrative').click(function(event) {
		window.location.href = "/payment/administrative/step1";
	});

	$('#transaction').click(function(event) {
		window.location.href = "/payment/resultTransaction";
	});
});