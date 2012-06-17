var serverURL = "http://localhost:9000/servizioAlloggi/";
var offers;
var base64Append = 'data:image/jpeg;base64,';

var geocoder;

$('#offersListPage').live('pagebeforeshow', function(event) {
  getOffersList();
});
function getOffersList() {

  navigator.geolocation.getCurrentPosition(function(position) {
    //var coord = position.coords.latitude + "," + position.coords.longitude;
    var latlng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
    geocoder = new google.maps.Geocoder();
    geocoder.geocode({
      'latLng' : latlng
    }, function(results, status) {
      if(status == google.maps.GeocoderStatus.OK) {
        if(results[1]) {
          $('#locationFound').text(results[1].formatted_address);
        } else {
          $('#locationFound').text('Posizione non trovata');
        }
      } else {
        $('#locationFound').text('Posizione non trovata');
      }
    });
    requestURL = 'offerte/latitude/' + position.coords.latitude + '/longitude/' + position.coords.longitude + '/radius/' + 10 + '/';
    //serverURL+requestURL
    $.getJSON(serverURL+requestURL, function(data) {
      $('#offersList li').remove();
      offers = data;
      $.each(offers, function(index, offer) {
        $('#offersList').append('<li><a href="searchDetails.html" data-offer-id="' + offer.entityId + '"><h3>' + offer.tipologia + '</h3><p><span class="detailsLabel">Indirizzo:</span> ' + offer.indirizzo + '</p><p><span class="detailsLabel">Posti letto:</span> ' + offer.postiLetto + '</p><p><span class="detailsLabel">Periodo disponibilità:</span> dal ' + offer.disponibileDa + ' al ' + offer.disponibileFino + '</p></a></li>');
      });
      $('#offersList').listview('refresh');
    });
  });
};


$('#offersListPage').live('pageshow', function(event) {
  $('#offersList').delegate('li', 'vclick', function(event) {
    index = $(this).index();
    localStorage.setItem("offer_location", offers[index].location);
    localStorage.setItem("offer_indirizzo", offers[index].indirizzo);
    localStorage.setItem("offer_tipologia", offers[index].tipologia);
    localStorage.setItem("offer_postiLetto", offers[index].postiLetto);
    localStorage.setItem("offer_disponibileDa", offers[index].disponibileDa);
    localStorage.setItem("offer_disponibileFino", offers[index].disponibileFino);
    localStorage.setItem("offer_servizi", offers[index].servizi);
    localStorage.setItem("offer_disabili", offers[index].disabili);
    localStorage.setItem("offer_note", offers[index].note);
    localStorage.setItem("offer_pic1", offers[index].foto1);
    localStorage.setItem("offer_pic2", offers[index].foto2);
    localStorage.setItem("offer_pic3", offers[index].foto3);
  });
});

$('#searchDetailsPage').live('pagebeforeshow', function(event) {
  $('#offer_indirizzo').text(localStorage.getItem("offer_indirizzo"));
  $('#offer_tipologia').text(localStorage.getItem("offer_tipologia"));
  $('#offer_postiLetto').text(localStorage.getItem("offer_postiLetto"));
  $('#offer_disponibileDa').text(localStorage.getItem("offer_disponibileDa"));
  $('#offer_disponibileFino').text(localStorage.getItem("offer_disponibileFino"));
  $('#offer_servizi').text(localStorage.getItem("offer_servizi"));
  $('#offer_note').text(localStorage.getItem("offer_note"));
  if(localStorage.getItem("offer_pic1"))
    $('#offer_pic1').attr('src', base64Append + localStorage.getItem("offer_pic1"));
  else
    $('#offer_pic1').remove();
  if(localStorage.getItem("offer_pic2"))
    $('#offer_pic2').attr('src', base64Append + localStorage.getItem("offer_pic2"));
  else
    $('#offer_pic2').remove();
  if(localStorage.getItem("offer_pic3"))
    $('#offer_pic3').attr('src', base64Append + localStorage.getItem("offer_pic3"));
  else
    $('#offer_pic3').remove();
});
