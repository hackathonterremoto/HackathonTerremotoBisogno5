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
  offerForm_servizi_1 = $('#offerForm_servizi_1:checked').length ? $('#offerForm_servizi_1').val() + " " : '';
  offerForm_servizi_2 = $('#offerForm_servizi_2:checked').length ? $('#offerForm_servizi_2').val() + " " : '';
  offerForm_servizi_3 = $('#offerForm_servizi_3:checked').length ? $('#offerForm_servizi_3').val() + " " : '';
  offerForm_servizi_4 = $('#offerForm_servizi_4:checked').length ? $('#offerForm_servizi_4').val() + " " : '';
  offerForm_servizi = offerForm_servizi_1 + offerForm_servizi_2 + offerForm_servizi_3 + offerForm_servizi_4;
  offerForm_disabili = "";
  offerForm_disabili_1 = $('#offerForm_disabili_1:checked').length ? $('#offerForm_disabili_1').val() + " " : '';
  offerForm_disabili_2 = $('#offerForm_disabili_2:checked').length ? $('#offerForm_disabili_2').val() + " " : '';
  offerForm_disabili = offerForm_disabili_1 + offerForm_disabili_2;

  localStorage.setItem("offerForm_location_value", offerForm_location_value);
  localStorage.setItem("offerForm_postiLetto_value", offerForm_postiLetto_value);
  localStorage.setItem("offerForm_dispDal_value", offerForm_dispDal_value);
  localStorage.setItem("offerForm_dispAl_value", offerForm_dispAl_value);
  localStorage.setItem("offerForm_tipologia_1_value", offerForm_tipologia_1_value);
  localStorage.setItem("offerForm_servizi", offerForm_servizi);
  localStorage.setItem("offerForm_disabili", offerForm_disabili);

};

/*$('#createOfferPage2').live('pagebeforeshow', function(event) {

 });*/

var pictureSource;
var destinationType;

$("#offerTakePhotoBtn").live("click", function(event, ui) {
  pictureSource = navigator.camera.PictureSourceType;
  destinationType = navigator.camera.DestinationType;
  capturePhoto();
});
// Called when a photo is successfully retrieved
//
function onPhotoDataSuccess(imageData) {
  // Uncomment to view the base64 encoded image data
  // console.log(imageData);
  if($('#smallImage1').attr("src") == null || $('#smallImage1').attr("src") == '') {
    $('#smallImage1').css("display", "block");
    $('#smallImage1').attr("src", "data:image/jpeg;base64," + imageData);
  } else if($('#smallImage2').attr("src") == null || $('#smallImage2').attr("src") == '') {
    $('#smallImage2').css("display", "block");
    $('#smallImage2').attr("src", "data:image/jpeg;base64," + imageData);
  } else if($('#smallImage3').attr("src") == null || $('#smallImage3').attr("src") == '') {
    $('#smallImage3').css("display", "block");
    $('#smallImage3').attr("src", "data:image/jpeg;base64," + imageData);
  }

}

// Called when a photo is successfully retrieved
//
function onPhotoURISuccess(imageURI) {
  // Uncomment to view the image file URI
  // console.log(imageURI);

  // Get image handle
  //
  var largeImage = $('largeImage');

  // Unhide image elements
  //
  largeImage.style.display = 'block';

  // Show the captured photo
  // The inline CSS rules are used to resize the image
  //
  largeImage.src = imageURI;
}

// A button will call this function
//
function capturePhoto() {
  // Take picture using device camera and retrieve image as base64-encoded string
  navigator.camera.getPicture(onPhotoDataSuccess, onFail, {
    quality : 50,
    destinationType : destinationType.DATA_URL
  });
}

// A button will call this function
//
function capturePhotoEdit() {
  // Take picture using device camera, allow edit, and retrieve image as base64-encoded string
  navigator.camera.getPicture(onPhotoDataSuccess, onFail, {
    quality : 20,
    allowEdit : true,
    destinationType : destinationType.DATA_URL
  });
}

// A button will call this function
//
function getPhoto(source) {
  // Retrieve image file location from specified source
  navigator.camera.getPicture(onPhotoURISuccess, onFail, {
    quality : 50,
    destinationType : destinationType.FILE_URI,
    sourceType : source
  });
}

// Called if something bad happens.
//
function onFail(message) {
  alert('Failed because: ' + message);
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