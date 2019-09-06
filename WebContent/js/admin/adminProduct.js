
// 상품등록 영역 css 변경
var $active = $('#areaActive_productUpdate');

$active.click(function() {
	$active.css('display', 'none');
	$('#productUpdate').css('display', 'block');
});

$('#optionCheckY').click(function(){
	$('#optionCheckY').css('background', 'black').attr('selected');
	$('#optionCheckN').css('background', 'white').removeAttr('selected');
	$('.option1').css('display', 'table-row');
	$('.option1Detail').css('display', 'table-cell');
});
$('#optionCheckN').click(function(){
	$('#optionCheckN').css('background', 'black').attr('selected');
	$('#optionCheckY').css('background', 'white').removeAttr('selected');
	$('.option1').css('display', 'none');
	$('.option1Detail').css('display', 'table-cell');
	$('.option2').css('display', 'none');
	$('.option2Detail').css('display', 'table-cell');
	$('.option3').css('display', 'none');
	$('.option3Detail').css('display', 'table-cell');
	$('.inputOpt').val("");
});

$('#addOption1').click(function(){
	$('.option2').css('display', 'table-row');
	$('.option2Detail').css('display', 'table-cell');
});
$('#addOption2').click(function(){
	$('.option3').css('display', 'table-row');
	$('.option3Detail').css('display', 'table-cell');
});

$('#delOption1').click(function(){
	$('.option1').css('display', 'none');
	$('.option2').css('display', 'none');
	$('.option3').css('display', 'none');
	$('#input_option1').val("");
	$('#input_option2').val("");
	$('#input_option3').val("");
	$('#optionCheckN').css('background', 'black').attr('selected', 'selected');
	$('#optionCheckY').css('background', 'white');
});
$('#delOption2').click(function(){
	$('.option2').css('display', 'none');
	$('.option3').css('display', 'none');
	$('#input_option2').val("");
	$('#input_option3').val("");
});
$('#delOption3').click(function(){
	$('.option3').css('display', 'none');
	$('#input_option3').val("");
});