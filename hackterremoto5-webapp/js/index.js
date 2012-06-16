var jqmScrollViewFixForDesktop = false;

// INDEX ************************************************************

// Wait for Cordova to load
document.addEventListener("deviceready", onDeviceReady, false);

// Cordova is loaded and it is now safe to make calls Cordova methods
//
function onDeviceReady() {
  checkConnection();
}

// alert dialog dismissed
function alertDismissed() {
  // do something
}

function checkConnection() {
  var networkState = navigator.network.connection.type;

  var states = {};
  states[Connection.UNKNOWN] = 'Unknown connection';
  states[Connection.ETHERNET] = 'Ethernet connection';
  states[Connection.WIFI] = 'WiFi connection';
  states[Connection.CELL_2G] = 'Cell 2G connection';
  states[Connection.CELL_3G] = 'Cell 3G connection';
  states[Connection.CELL_4G] = 'Cell 4G connection';
  states[Connection.NONE] = 'No network connection';

  if(networkState == Connection.NONE || networkState == Connection.UNKNOWN)
    navigator.notification.alert('Alcune funzionalita\' dell\'applicazione START2B 2012 necessitano di una connessione internet attiva.\n\nPer sfuttare tutte le funzionalita\' collegati a una rete WiFi se disponibile o usa la rete cellulare (GPRS, EDGE, 3G, 4G).', alertDismissed, 'No Internet!', 'OK');
}

var locale = "IT";
$('#localeswitch_it').live('click', function() {
  locale = "IT";
  $('#localeswitch_en').removeClass("ui-btn-active");
  $('#localeswitch_it').addClass("ui-btn-active");
  $('#startpage_button_credits .ui-btn-text').text(messages.IT.credits);
  $('#menuItemText_exhibitors').text(messages.IT.menu_exhibitors);
  $('#menuItemText_events').text(messages.IT.menu_events);
  $('#menuItemText_specialEvents').text(messages.IT.menu_specialevents);
  $('#menuItemText_map').text(messages.IT.menu_map);
});
$('#localeswitch_en').live('click', function() {
  locale = "EN";
  $('#localeswitch_it').removeClass("ui-btn-active");
  $('#localeswitch_en').addClass("ui-btn-active");
  $('#startpage_button_credits .ui-btn-text').text(messages.EN.credits);
  $('#menuItemText_exhibitors').text(messages.EN.menu_exhibitors);
  $('#menuItemText_events').text(messages.EN.menu_events);
  $('#menuItemText_specialEvents').text(messages.EN.menu_specialevents);
  $('#menuItemText_map').text(messages.EN.menu_map);
});

$('#credits_dialog').live('pagebeforeshow', function(event) {
  if(locale == "IT") {
    $('#credits_dialog_header').text(messages.IT.credits);
  }
  if(locale == "EN") {
    $('#credits_dialog_header').text(messages.EN.credits);
  }
});

$('#mappagescrollview').live('pagebeforeshow', function(event) {
  checkConnection();
  if(locale == "IT") {
    $('#mappagescrollview_headerText').text(messages.IT.map);
  }
  if(locale == "EN") {
    $('#mappagescrollview_headerText').text(messages.EN.map);
  }
});
// EXHIBITORS ************************************************************
var startups;
var incubators;
var sponsors;
var currentTabExhibitors;
var lastOpenedTab;
var $startupsList;
var $incubatorsList;
var $sponsorsList;

var logoFolderPath = "../img/exhibitors/";

$('.exhibitors-navbar-item').live('click', function() {
  selectedItem = $(this).attr('id');
  lastOpenedTab = selectedItem;
  if(selectedItem == 'exhibitors-navbar-first') {
    getStartupsList();
  } else if(selectedItem == 'exhibitors-navbar-second') {
    getIncubatorsList();
  } else if(selectedItem == 'exhibitors-navbar-third') {
    getSponsorsList();
  }
});

$('#exhibitorsListPage').live('pagebeforeshow', function(event) {
  //$('#exhibitorsContent .ui-scrollview-view').removeAttr("style");
  if(locale == "IT") {
    $('#exhibitors_header').text(messages.IT.exhibitors);
    $('#exhibitors-navbar-first .ui-btn-inner').text(messages.IT.exhibitors_startups);
    $('#exhibitors-navbar-second .ui-btn-inner').text(messages.IT.exhibitors_incubators);
    $('#exhibitors-navbar-third .ui-btn-inner').text(messages.IT.exhibitors_sponsors);
  }
  if(locale == "EN") {
    $('#exhibitors_header').text(messages.EN.exhibitors);
    $('#exhibitors-navbar-first .ui-btn-inner').text(messages.EN.exhibitors_startups);
    $('#exhibitors-navbar-second .ui-btn-inner').text(messages.EN.exhibitors_incubators);
    $('#exhibitors-navbar-third .ui-btn-inner').text(messages.EN.exhibitors_sponsors);
  }
});

$('#exhibitorsListPage').live('pageshow', function(event) {
  if (jqmScrollViewFixForDesktop)
    $('#exhibitorsContent').removeAttr("style");

  $('#exhibitorsContent .ui-scrollview-view').removeAttr("style");
});

$('#exhibitorsListPage').live('pagebeforeshow', function(event) {
  if(lastOpenedTab == undefined || lastOpenedTab == 'exhibitors-navbar-first') {
    getStartupsList();
    $('#exhibitors-navbar-second a').removeClass('ui-btn-active');
    $('#exhibitors-navbar-third a').removeClass('ui-btn-active');
    $('#exhibitors-navbar-first a').addClass('ui-btn-active');
  }
  if(lastOpenedTab == 'exhibitors-navbar-second') {
    getIncubatorsList();
    $('#exhibitors-navbar-first a').removeClass('ui-btn-active');
    $('#exhibitors-navbar-third a').removeClass('ui-btn-active');
    $('#exhibitors-navbar-second a').addClass('ui-btn-active');
  }
  if(lastOpenedTab == 'exhibitors-navbar-third') {
    getSponsorsList();
    $('#exhibitors-navbar-first a').removeClass('ui-btn-active');
    $('#exhibitors-navbar-second a').removeClass('ui-btn-active');
    $('#exhibitors-navbar-third a').addClass('ui-btn-active');
  }
});

$('#exhibitorsDetailsPage').live('pagebeforeshow', function(event) {
  if(locale == "IT") {
    $('#exhibitorDetails_backbutton .ui-btn-text').text(messages.IT.exhibitors);
    $('#grid-exhibitorPhone .ui-btn-text').text(messages.IT.ex_details_call);
    $('#grid-exhibitorEmail .ui-btn-text').text(messages.IT.ex_details_email);
  }
  if(locale == "EN") {
    $('#exhibitorDetails_backbutton .ui-btn-text').text(messages.EN.exhibitors);
    $('#grid-exhibitorPhone .ui-btn-text').text(messages.EN.ex_details_call);
    $('#grid-exhibitorEmail .ui-btn-text').text(messages.EN.ex_details_email);
  }
});

$('#exhibitorsDetailsPage').live('pagebeforeshow', function(event) {
  exhibitorId = getUrlVars()["id"];
  getExhibitorDetails(exhibitorId);
});

$('#exhibitorsDetailsPage').live('pageshow', function(event) {
  if (jqmScrollViewFixForDesktop)
     $('#exhibitorDetailsContainer').removeAttr("style");
});
function getUrlVars() {
  var vars = [], hash;
  var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
  for(var i = 0; i < hashes.length; i++) {
    hash = hashes[i].split('=');
    vars.push(hash[0]);
    vars[hash[0]] = hash[1];
  }
  return vars;
}

function getIncubatorsList() {
  if(incubators == undefined || incubators.length < 1) {
    $.getJSON('../data/incubators.json', function(data) {
      $('#exhibitorsList li').remove();
      incubators = data.exhibitors;
      currentTabExhibitors = incubators;
      $.each(incubators, function(index, exhibitor) {
        $('#exhibitorsList').append('<li><a href="exhibitordetails.html?id=' + exhibitor.id + '" data-transition="none">' + exhibitor.name + '</a></li>');
      });
      $('#exhibitorsList').listview('refresh');
      $incubatorsList = $('#exhibitorsList li');
    });
  } else {
    $('#exhibitorsList li').remove();
    currentTabExhibitors = incubators;
    $('#exhibitorsList').append($incubatorsList);
    $('#exhibitorsList').listview('refresh');
  }

}

function getSponsorsList() {
  if(sponsors == undefined || sponsors.length < 1) {
    $.getJSON('../data/sponsors.json', function(data) {
      $('#exhibitorsList li').remove();
      sponsors = data.exhibitors;
      currentTabExhibitors = sponsors;
      $.each(sponsors, function(index, exhibitor) {
        $('#exhibitorsList').append('<li><a href="exhibitordetails.html?id=' + exhibitor.id + '" data-transition="none">' + exhibitor.name + '</a></li>');
      });
      $('#exhibitorsList').listview('refresh');
      $sponsorsList = $('#exhibitorsList li');
    });
  } else {
    $('#exhibitorsList li').remove();
    currentTabExhibitors = sponsors;
    $('#exhibitorsList').append($sponsorsList);
    $('#exhibitorsList').listview('refresh');
  }

}

function getStartupsList() {
  if(startups == undefined || startups.length < 1) {
    $.getJSON('../data/startups.json', function(data) {
      $('#exhibitorsList li').remove();
      startups = data.exhibitors;
      currentTabExhibitors = startups;
      $.each(startups, function(index, exhibitor) {
        $('#exhibitorsList').append('<li><a href="exhibitordetails.html?id=' + exhibitor.id + '" data-transition="none">' + exhibitor.name + '</a></li>');
      });
      $('#exhibitorsList').listview('refresh');
      $startupsList = $('#exhibitorsList li');
    });
  } else {
    $('#exhibitorsList li').remove();
    currentTabExhibitors = startups;
    $('#exhibitorsList').append($startupsList);
    $('#exhibitorsList').listview('refresh');
  }

}

function getExhibitorDetails(exhibitorId) {
  exhibitor = currentTabExhibitors[exhibitorId - 1];
  if(exhibitor.logo != "")
    $('#exhibitorLogo').attr('src', logoFolderPath + exhibitor.logo);
  else {
    $('#exhibitorLogo').remove();
    $('#exhibitorLogoBox').remove();
  }

  $('#exhibitorName').text(exhibitor.name);
  $('#exhibitorStand').text(exhibitor.stand);
  if(exhibitor.address != "")
    $('#exhibitorAddress').text(exhibitor.address);
  else
    $('#exhibitorAddress').remove();
  if(exhibitor.address2 != "")
    $('#exhibitorAddress2').text(exhibitor.address2);
  else
    $('#exhibitorAddress2').remove();
  if(exhibitor.phone != "")
    $('#exhibitorPhone').html('<b>Tel:</b> ' + exhibitor.phone);
  else
    $('#exhibitorPhone').remove();
  if(exhibitor.fax != "")
    $('#exhibitorFax').html('<b>Fax:</b> ' + exhibitor.fax);
  else
    $('#exhibitorFax').remove();
  if(exhibitor.www != "")
    $('#exhibitorWWW').html('<b>www:</b> ' + exhibitor.www);
  else
    $('#exhibitorWWW').remove();
  if(exhibitor.email != "")
    $('#exhibitorEmail').html('<b>email:</b> ' + exhibitor.email);
  else
    $('#exhibitorEmail').remove();
  if(exhibitor.descriptionIT != "")
    $('#exhibitorDescriptionIT').html(exhibitor.descriptionIT);
  else
    $('#exhibitorDescriptionIT').remove();
  if(exhibitor.descriptionEN != "")
    $('#exhibitorDescriptionEN').html(exhibitor.descriptionEN);
  else
    $('#exhibitorDescriptionEN').remove();

  $('#grid-exhibitorPhone').attr('href', 'tel:' + exhibitor.phone);
  $('#grid-exhibitorEmail').attr('href', 'mailto:' + exhibitor.email);
  $('#grid-exhibitorWWW').attr('href', exhibitor.www);
}

// EVENTS ************************************************************
var firstDayEvents;
var secondDayEvents;
var currentDayEvents;
var lastDayEventTab;
var eventLiveImgAppend = '<img src="../img/data-icon/event-live.png" class="ui-li-icon event-status-icon">';
var eventEndedImgAppend = '<img src="../img/data-icon/event-ended.png" class="ui-li-icon event-status-icon">';

// A: convegno
// B: workshop
// C: speciale

var selectedEventType = "ALL";
$('#switchEventTypeA').live('click', function() {
  setEventTypeControlGroupState(selectedEventType, "A");
});
$('#switchEventTypeB').live('click', function() {
  setEventTypeControlGroupState(selectedEventType, "B");
});
$('#switchEventTypeC').live('click', function() {
  setEventTypeControlGroupState(selectedEventType, "C");
});
$('#switchEventTypeAll').live('click', function() {
  setEventTypeControlGroupState(selectedEventType, "ALL");
});
function setEventTypeControlGroupState(oldState, newState) {
  if(oldState == newState)
    return;
  if(oldState == "A")
    $('#switchEventTypeA').removeClass("ui-btn-active");
  else if(oldState == "B")
    $('#switchEventTypeB').removeClass("ui-btn-active");
  else if(oldState == "C")
    $('#switchEventTypeC').removeClass("ui-btn-active");
  else if(oldState == "ALL")
    $('#switchEventTypeAll').removeClass("ui-btn-active");

  if(newState == "A")
    $('#switchEventTypeA').addClass("ui-btn-active");
  else if(newState == "B")
    $('#switchEventTypeB').addClass("ui-btn-active");
  else if(newState == "C")
    $('#switchEventTypeC').addClass("ui-btn-active");
  else if(newState == "ALL")
    $('#switchEventTypeAll').addClass("ui-btn-active");
  selectedEventType = newState;

  loadDayEvents();

}

var selectedEventDay = 1;
$('.events-navbar-item').live('click', function() {
  var subMenuItem = $(this).text();
  lastDayEventTab = subMenuItem;
  if(subMenuItem.indexOf("6 giugno") > 0 || subMenuItem.indexOf("June 6th") > 0) {
    selectedEventDay = 1;
    loadDayEvents();
  }
  if(subMenuItem.indexOf("7 giugno") > 0 || subMenuItem.indexOf("June 7th") > 0) {
    selectedEventDay = 2;
    loadDayEvents();
  }

});

$('#eventsListPage').live('pageshow', function(event) {
  if (jqmScrollViewFixForDesktop)
      $('#eventsListContainer').removeAttr("style");
});

$('#eventsListPage').live('show', function(event) {
  if(lastDayEventTab == undefined) {
    getEventsList(1);
  } else if(lastDayEventTab.indexOf("6 giugno") > 0 || lastDayEventTab.indexOf("June 6th") > 0) {
    getEventsList(1);
  } else if(lastDayEventTab.indexOf("7 giugno") > 0 || lastDayEventTab.indexOf("June 7th") > 0) {
    loadDayEvents(2);
  }

});

$('#eventsListPage').live('pagebeforeshow', function(event) {
  if(lastDayEventTab == undefined) {
    getEventsList(1);
  } else if(lastDayEventTab.indexOf("6 giugno") > 0 || lastDayEventTab.indexOf("June 6th") > 0) {
    getEventsList(1);
  } else if(lastDayEventTab.indexOf("7 giugno") > 0 || lastDayEventTab.indexOf("June 7th") > 0) {
    loadDayEvents(2);
  }

});

$('#eventsListPage').live('pagebeforeshow', function(event) {
  if(locale == "IT") {
    $('#events_header').text(messages.IT.events);
    $('#events-navbar-first .ui-btn-inner').text(messages.IT.events_firstday);
    $('#events-navbar-second .ui-btn-inner').text(messages.IT.events_secondday);
    $('#switchEventTypeA .ui-btn-text').text(messages.IT.events_filter1);
    $('#switchEventTypeB .ui-btn-text').text(messages.IT.events_filter2);
    $('#switchEventTypeC .ui-btn-text').text(messages.IT.events_filter3);
    $('#switchEventTypeAll .ui-btn-text').text(messages.IT.events_filter4);
  }
  if(locale == "EN") {
    $('#events_header').text(messages.EN.events);
    $('#events-navbar-first .ui-btn-inner').text(messages.EN.events_firstday);
    $('#events-navbar-second .ui-btn-inner').text(messages.EN.events_secondday);
    $('#switchEventTypeA .ui-btn-text').text(messages.EN.events_filter1);
    $('#switchEventTypeB .ui-btn-text').text(messages.EN.events_filter2);
    $('#switchEventTypeC .ui-btn-text').text(messages.EN.events_filter3);
    $('#switchEventTypeAll .ui-btn-text').text(messages.EN.events_filter4);
  }
});

$('#events_labels_dialog').live('pagebeforeshow', function(event) {
  if(locale == "IT") {
    $('#events_labels_dialog_header').text(messages.IT.popup_labels);
    $('#live_event').text(messages.IT.labels_liveevent);
    $('#closed_event').text(messages.IT.labels_closedevent);
  }
  if(locale == "EN") {
    $('#events_labels_dialog_header').text(messages.EN.popup_labels);
    $('#live_event').text(messages.EN.labels_liveevent);
    $('#closed_event').text(messages.EN.labels_closedevent);
  }
});

$('#eventDetailsPage').live('pagebeforeshow', function(event) {
  eventId = getUrlVars()["id"];
  getEventDetails(eventId);
  if(locale == "IT") {
    $('#eventDetails_backbutton .ui-btn-text').text(messages.IT.menu_events);
  }
  if(locale == "EN") {
    $('#eventDetails_backbutton .ui-btn-text').text(messages.EN.menu_events);
  }
});

$('#eventDetailsPage').live('pageshow', function(event) {
  if (jqmScrollViewFixForDesktop)
      $('#eventDetailsContainer').removeAttr("style");
});

function getEventDetails(eventId) {
  event = currentDayEvents[eventId - 1];
  $('#eventTitle').text(event.title);
  $('#eventLocation').text(event.location);
  $('#eventTime').html("<b>Ore:</b> " + event.time);
  $('#eventDuration').html("<b>Durata:</b> " + event.duration);
  $('#eventDescription').html(event.description);
}

function loadDayEvents() {
  $('#eventsList li').remove();
  if(selectedEventDay == 1) {
    currentDayEvents = firstDayEvents;
  } else if(selectedEventDay == 2) {
    currentDayEvents = secondDayEvents;
    $('#events-navbar-second').addClass('ui-btn-active');
    $('#events-navbar-first').removeClass('ui-btn-active');
  }
  filteredEventsByType = [];
  $.each(currentDayEvents, function(index, event) {
    if(selectedEventType == "ALL") {
      filteredEventsByType = currentDayEvents;
      return false;
    } else if(event.type == selectedEventType) {
      filteredEventsByType.push(event);
    }
  });
  now = new Date();
  $.each(filteredEventsByType, function(index, event) {
    fulldateSplit = event.fulldate.split("/");
    eventTimeSplit = event.time.split(":");
    eventTimeHours = eventTimeSplit[0];
    eventTimeMinutes = eventTimeSplit[1];
    eventDurationSplit = event.duration.split(":");
    eventDurationHours = eventDurationSplit[0];
    eventDurationMinutes = eventDurationSplit[1];
    eventStart = new Date();
    eventStart.setFullYear(fulldateSplit[2], fulldateSplit[1] - 1, fulldateSplit[0]);
    eventStart.setHours(eventTimeHours);
    eventStart.setMinutes(eventTimeMinutes);
    eventEnd = new Date(eventStart);
    eventEnd.setHours(eventEnd.getHours() + parseInt(eventDurationHours));
    eventEnd.setMinutes(eventEnd.getMinutes() + parseInt(eventDurationMinutes));
    eventStatusImgAppend = "";
    if(now < eventStart) {
    } else if(now > eventStart && now < eventEnd) {
      eventStatusImgAppend = eventLiveImgAppend;
    } else if(now > eventStart) {
      eventStatusImgAppend = eventEndedImgAppend;
    }
    $('#eventsList').append('<li><a href="eventdetails.html?id=' + event.id + '" data-transition="none">' + eventStatusImgAppend + '<h6>' + event.title + '</h6><p>' + event.time + ' - ' + event.location + '</p></a></li>');
  });
  $('#eventsList').listview('refresh');

};

function getEventsList(dayNumber) {
  if(locale == "IT") {
    $.getJSON('../data/events.json', function(data) {
      events = data.events;
      firstDayEvents = events[0].firstday;
      secondDayEvents = events[1].secondday;
      loadDayEvents(dayNumber);
    });
  } else if(locale == "EN") {
    $.getJSON('../data/events_en.json', function(data) {
      events = data.events;
      firstDayEvents = events[0].firstday;
      secondDayEvents = events[1].secondday;
      loadDayEvents(dayNumber);
    });
  }

}

// specialevents ************************************************************
var specialevents;
var imgFolderPath = "../img/";

$('#specialEventsPage').live('pagebeforeshow', function(event) {
  if(locale == "IT") {
    $('#specialevents_header').text(messages.IT.special_events);
  }
  if(locale == "EN") {
    $('#specialevents_header').text(messages.EN.special_events);
  }
});

$('#specialEventsPage').live('pagebeforeshow', function(event) {
  getSpecialEvents();
});

$('#specialEventDetailsPage').live('pageshow', function(event) {
  if (jqmScrollViewFixForDesktop)
      $('#specialEventDetailsContainer').removeAttr("style");

});
function getSpecialEvents() {
  $.getJSON('../data/specialevents.json', function(data) {
    specialevents = data.events;
  });
}


$('#specialEventDetailsPage').live('pagebeforeshow', function(event) {
  if(locale == "IT") {
    $('#specialEventDetails_backbutton .ui-btn-text').text(messages.IT.special_events);
  }
  if(locale == "EN") {
    $('#specialEventDetails_backbutton .ui-btn-text').text(messages.EN.special_events);
  }
  eventId = getUrlVars()["id"];
  getSpecialEventDetails(eventId);
});
function getSpecialEventDetails(eventId) {
  event = specialevents[eventId - 1];
  //$('#specialEventTitle').text(event.name);
  $('#specialEventLogo').attr('src', imgFolderPath + event.logo);
  $('#specialEventLocationIT').text(event.locationIT);
  $('#specialEventTimeIT1').text(event.timeIT1);
  $('#specialEventTimeIT2').text(event.timeIT2);
  $('#specialEventDescriptionIT').html(event.descriptionIT);

  $('#specialEventLocationEN').text(event.locationEN);
  $('#specialEventTimeEN1').text(event.timeEN1);
  $('#specialEventTimeEN2').text(event.timeEN2);
  $('#specialEventDescriptionEN').html(event.descriptionEN);
}