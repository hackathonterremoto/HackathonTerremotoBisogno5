$("#createOfferForwardBtn1").live("click", function(event, ui) {
	saveOfferPage1();
});

$("#createOfferForwardBtn2").live("click", function(event, ui) {
	saveOfferPage1();
});

function saveOfferPage1() {
	// using web storage to temporarily save form data before submit
	offerForm_location_value = $("#offerForm_location").val();
	offerForm_postiLetto_value = $("#offerForm_postiLetto").val(); 
	offerForm_dispDal_value = $("#offerForm_dispDal").val();
	offerForm_dispAl_value = $("#offerForm_dispAl").val(); 
	offerForm_tipologia_1_value = $("input[@name=offerForm_tipologia_1]:checked").val();
	
	offerForm_servizi = "";
	offerForm_servizi_1 = $('#offerForm_servizi_1:checked').length ? $('#offerForm_servizi_1').val()+" " : '';
	offerForm_servizi_2 = $('#offerForm_servizi_2:checked').length ? $('#offerForm_servizi_2').val()+" " : '';
	offerForm_servizi_3 = $('#offerForm_servizi_3:checked').length ? $('#offerForm_servizi_3').val()+" " : '';
	offerForm_servizi_4 = $('#offerForm_servizi_4:checked').length ? $('#offerForm_servizi_4').val()+" " : '';
	offerForm_servizi = offerForm_servizi_1+offerForm_servizi_2+offerForm_servizi_3+offerForm_servizi_4;
	
	offerForm_disabili = "";
	offerForm_disabili_1 = $('#offerForm_disabili_1:checked').length ? $('#offerForm_disabili_1').val()+" " : '';
	offerForm_disabili_2 = $('#offerForm_disabili_2:checked').length ? $('#offerForm_disabili_2').val()+" " : '';
	offerForm_disabili = offerForm_disabili_1+offerForm_disabili_2;
	
	localStorage.setItem("offerForm_location_value", offerForm_location_value);
	localStorage.setItem("offerForm_postiLetto_value", offerForm_postiLetto_value);
	localStorage.setItem("offerForm_dispDal_value", offerForm_dispDal_value);
	localStorage.setItem("offerForm_dispAl_value", offerForm_dispAl_value);
	localStorage.setItem("offerForm_tipologia_1_value", offerForm_tipologia_1_value);
	localStorage.setItem("offerForm_servizi", offerForm_servizi);
	localStorage.setItem("offerForm_disabili", offerForm_disabili);
	
};

/*$('#createOfferPage2').live('pagebeforeshow', function(event) {
	if (enablePhonegap) {

	}
});*/

$("#offerTakePhotoBtn").live("click", function(event, ui) {
	navigator.camera.getPicture(onSuccess, onFail, { quality: 50,
	    destinationType: Camera.DestinationType.DATA_URL
	 }); 
})

function onSuccess(imageData) {
    var image = document.getElementById('myImage');
    image.src = "data:image/jpeg;base64," + imageData;
    console.log(imageData);
}

function onFail(message) {
    console.log('Failed because: ' + message);
}

/**
 * Calendario: disponibilita dal
 * rif. http://demo.mobiscroll.com/
 */
/*$(function() {
 $('#offerForm_dispDal').scroller({
 preset : 'date',
 invalid : {
 daysOfWeek : [0, 6],
 daysOfMonth : ['5/1', '12/24', '12/25']
 },
 theme : 'default',
 display : 'modal',
 mode : 'scroller',
 dateOrder : 'mmD ddy'
 });

 $('#calendarDal').click(function() {
 $('#offerForm_dispDal').scroller('calendarDal');
 return false;
 });
 $('#clear').click(function() {
 $('#offerForm_dispDal').val('');
 return false;
 });
 });*/

/**
 * Calendario: disponibilita al
 * rif. http://demo.mobiscroll.com/
 */
/*$(function() {
 $('#offerForm_dispAl').scroller({
 preset : 'date',
 invalid : {
 daysOfWeek : [0, 6],
 daysOfMonth : ['5/1', '12/24', '12/25']
 },
 theme : 'default',
 display : 'modal',
 mode : 'scroller',
 dateOrder : 'mmD ddy'
 });

 $('#calendarAl').click(function() {
 $('#offerForm_dispAl').scroller('calendarAl');
 return false;
 });
 $('#clear').click(function() {
 $('#offerForm_dispAl').val('');
 return false;
 });
 });*/

