var directionsDisplay;
var directionsService = new google.maps.DirectionsService();
var startLocation = null;
var endLocation = null;
var map;
var latlngSelecionada;
var infowindow = new google.maps.InfoWindow(
        {
            size: new google.maps.Size(0, 0)
        });
var polyline = new google.maps.Polyline({
    path: [],
    strokeColor: '#FF0000',
    strokeWeight: 3
});
var polylineLinha = new google.maps.Polyline({
    path: [],
    strokeColor: '#00FF00',
    strokeWeight: 3
});
function initialize() {
    directionsDisplay = new google.maps.DirectionsRenderer();
    var joaoPessoa = new google.maps.LatLng(-7.16798126869324, -34.8314046409435);
    var mapOptions = {
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.HYBRID,
        center: joaoPessoa
    }
    map = new google.maps.Map(document.getElementById('content'), mapOptions);
//    google.maps.event.addListener(map, 'click', function() {
//        infowindow.close();
//    });
    directionsDisplay.setMap(map);
}

function RenderCustomDirections(response, status) {
    if (status == google.maps.DirectionsStatus.OK) {
        waypts = [];
        var bounds = new google.maps.LatLngBounds();
        var route = response.routes[0];
        var summaryPanel = document.getElementById("directions_panel");
        startLocation = new Object();
        endLocation = new Object();

        summaryPanel.innerHTML = "";

        // For each route, display summary information.
        for (var i = 0; i < route.legs.length; i++) {
            var routeSegment = i + 1;
            //alert(route.legs[i].start_address);
            //alert(route.legs[i].end_address);
            summaryPanel.innerHTML += "<b>Route Segment: " + routeSegment + "</b><br />";
            summaryPanel.innerHTML += "Latitude: " + route.legs[i].start_location + "FIM:" + route.legs[i].end_location + "<br />";
            summaryPanel.innerHTML += route.legs[i].start_address + " PARA ";
            summaryPanel.innerHTML += route.legs[i].end_address + "<br />";
            summaryPanel.innerHTML += route.legs[i].distance.text + "<br /><br />";
            document.getElementById('localizacao').value+=route.legs[i].start_location + "/" + route.legs[i].end_location + "a";
        }
        var legs = response.routes[0].legs;
        for (i = 0; i < legs.length; i++) {
            if (i == 0) {
                startLocation.latlng = legs[i].start_location;
                startLocation.address = legs[i].start_address;
                startLocation.marker = createMarker(legs[i].start_location, "start", legs[i].start_address, "red");
                //alert(startLocation.latlng);

            } else {
                waypts[i] = new Object();
                waypts[i].latlng = legs[i].start_location;
                waypts[i].address = legs[i].start_address;
                waypts[i].marker = createMarker(legs[i].start_location, "waypoint" + i, legs[i].start_address, "red");

            }
            //alert("fim: " + endLocation.latlng);
            endLocation.latlng = legs[i].end_location;
            endLocation.address = legs[i].end_address;
            var steps = legs[i].steps;
            for (j = 0; j < steps.length; j++) {
                var nextSegment = steps[j].path;
                //alert('nextSegment' + nextSegment);
                for (k = 0; k < nextSegment.length; k++) {
                    polyline.getPath().push(nextSegment[k]);
                    bounds.extend(nextSegment[k]);
                }
            }
        }
        polyline.setMap(map);

        map.fitBounds(bounds);
        endLocation.marker = createMarker(endLocation.latlng, "end", endLocation.address, "red");

    }
    //else
    //alert(status);
}
function RenderCustomDirectionsLinha(response, status) {
    //alert('entrei 1');
    //if (status == google.maps.DirectionsStatus.OK) {
    alert('entrei');
    waypts = [];
    var bounds = new google.maps.LatLngBounds();
    var route = response.routes[0];
    startLocation = new Object();
    endLocation = new Object();

    var legs = response.routes[0].legs;
    for (i = 0; i < legs.length; i++) {
        if (i == 0) {
            startLocation.latlng = legs[i].start_location;
            startLocation.address = legs[i].start_address;
            startLocation.marker = createMarker(legs[i].start_location, "start", legs[i].start_address, "red");

        } else {
            waypts[i] = new Object();
            waypts[i].latlng = legs[i].start_location;
            waypts[i].address = legs[i].start_address;
            waypts[i].marker = createMarker(legs[i].start_location, "waypoint" + i, legs[i].start_address, "red");

        }
        endLocation.latlng = legs[i].end_location;
        endLocation.address = legs[i].end_address;
        var steps = legs[i].steps;
        for (j = 0; j < steps.length; j++) {
            var nextSegment = steps[j].path;

            for (k = 0; k < nextSegment.length; k++) {
                polylineLinha.getPath().push(nextSegment[k]);
                bounds.extend(nextSegment[k]);
            }
        }
    }
    polylineLinha.setMap(map);

    map.fitBounds(bounds);
    endLocation.marker = createMarker(endLocation.latlng, "end", endLocation.address, "red");

    //}
    //else
    //alert(status);
}


var icons = new Array();
icons["red"] = new google.maps.MarkerImage("resources/images/red-dot-pi.png",
        // This marker is 20 pixels wide by 34 pixels tall.
        new google.maps.Size(32, 32),
        // The origin for this image is 0,0.
        new google.maps.Point(0, 0),
        // The anchor for this image is at 9,34.
        new google.maps.Point(15, 30));
function getMarkerImage(iconColor) {
    if ((typeof(iconColor) == "undefined") || (iconColor == null)) {
        iconColor = "red";
    }
    if (!icons[iconColor]) {
        icons[iconColor] = new google.maps.MarkerImage("resources/images/red-dot-pi.png",
                // This marker is 20 pixels wide by 34 pixels tall.
                new google.maps.Size(32, 32),
                // The origin for this image is 0,0.
                new google.maps.Point(0, 0),
                // The anchor for this image is at 6,20.
                new google.maps.Point(31, 32));
    }
    return icons[iconColor];

}

var iconImage = new google.maps.MarkerImage('resources/images/red-dot-pi.png',
        // This marker is 20 pixels wide by 34 pixels tall.
        new google.maps.Size(32, 32),
        // The origin for this image is 0,0.
        new google.maps.Point(0, 0),
        // The anchor for this image is at 9,34.
        new google.maps.Point(31, 32));
var iconShadow = new google.maps.MarkerImage('http://www.google.com/mapfiles/shadow50.png',
        // The shadow image is larger in the horizontal dimension
        // while the position and offset are the same as for the main image.
        new google.maps.Size(37, 34),
        new google.maps.Point(0, 0),
        new google.maps.Point(9, 34));
// Shapes define the clickable region of the icon.
// The type defines an HTML &lt;area&gt; element 'poly' which
// traces out a polygon as a series of X,Y points. The final
// coordinate closes the poly by connecting to the first
// coordinate.
var iconShape = {
    coord: [9, 0, 6, 1, 4, 2, 2, 4, 0, 8, 0, 12, 1, 14, 2, 16, 5, 19, 7, 23, 8, 26, 9, 30, 9, 34, 11, 34, 11, 30, 12, 26, 13, 24, 14, 21, 16, 18, 18, 16, 20, 12, 20, 8, 18, 4, 16, 2, 15, 1, 13, 0],
    type: 'poly'
};

function calcRoute() {
    //Lat:-7.120822570554853, Lng:-34.84485626220703
    var start = document.getElementById('inicio_input').value;
    var latInicio = start.substr(4, 18);
    var lngInicio = start.substr(28, 18);
    var end = document.getElementById('fim_input').value;
    var latFim = end.substr(4, 18);
    var lngFim = end.substr(28, 18);
    //alert("Lat: " + lat);
    //alert("Lng: " + lng);
    var waypts = [];
    var checkboxArray = $("#meio_input option:selected").val();

    $("#meio_input option").each(function() {
        if (this.selected == true) {
            //alert(this.value);
            var latMeio = this.value.substr(4, 18);
            var lngMeio = this.value.substr(28, 18);
            waypts.push({
                location: new google.maps.LatLng(latMeio, lngMeio),
                stopover: true});
        }
    })

    var request = {
        origin: new google.maps.LatLng(latInicio, lngInicio),
        destination: new google.maps.LatLng(latFim, lngFim),
        waypoints: waypts,
        optimizeWaypoints: true,
        travelMode: google.maps.TravelMode.DRIVING
    };


    directionsService.route(request, RenderCustomDirections);

}

function desenhaRotaLinha() {
    //Lat:-7.120822570554853, Lng:-34.84485626220703
    var start = document.getElementById('startLinha').value;
    //alert('sera q tah aqui?');
    //alert('start'+ start);
    var latInicio = start.substring(start.indexOf("(") + 1, start.indexOf(" "));
    var lngInicio = start.substring(start.indexOf(" ") + 1, start.indexOf(")"));
    //alert("Lat Inicio: " + latInicio);
    //alert("Lng Inicio: " + lngInicio);
    var meioStart = document.getElementById('meioStartLinha').value;
    var latMeioStart = meioStart.substring(meioStart.indexOf("(") + 1, meioStart.indexOf(" "));
    var lngMeioStart = meioStart.substring(meioStart.indexOf(" ") + 1, meioStart.indexOf(")"));
    //alert("Lat meio Start: " + latMeioStart);
    //alert("Lng meio Start: " + lngMeioStart);

    var meioEnd = document.getElementById('meioEndLinha').value;
    var latMeioEnd = meioEnd.substring(meioEnd.indexOf("(") + 1, meioEnd.indexOf(" "));
    var lngMeioEnd = meioEnd.substring(meioEnd.indexOf(" ") + 1, meioEnd.indexOf(")"));
    //alert("Lat meio FIM: " + latMeioEnd);
    //alert("Lng meio Fim: " + lngMeioEnd);

    var end = document.getElementById('endLinha').value;
    var latFim = end.substring(end.indexOf("(") + 1, end.indexOf(" "));
    var lngFim = end.substring(end.indexOf(" ") + 1, end.indexOf(")"));
    //alert("Lat FIM: " + latFim);
    //alert("Lng Fim: " + lngFim);


    var flightPlanCoordinates = [
        new google.maps.LatLng(lngInicio, latInicio),
        new google.maps.LatLng(lngMeioStart, latMeioStart),
        new google.maps.LatLng(lngMeioEnd, latMeioEnd),
        new google.maps.LatLng(lngFim, latFim)
    ];
    var flightPath = new google.maps.Polyline({
        path: flightPlanCoordinates,
        strokeColor: '#00FF00',
        strokeOpacity: 1.0,
        strokeWeight: 2
    });

    flightPath.setMap(map);

}





function createMarker(latlng, label, html, color) {
//    alert("createMarker(" + latlng + "," + label + "," + html + "," + color + ")");
    var contentString = '<b>' + label + '</b><br>' + html;
    var marker = new google.maps.Marker({
        position: latlng,
        draggable: true,
        map: map,
        shadow: iconShadow,
        icon: getMarkerImage(color),
        shape: iconShape,
        title: 'Ponto de Interesse',
        zIndex: Math.round(latlng.lat() * -100000) << 5
    });
    waypts.push(marker);

    google.maps.event.addListener(marker, 'click', function() {
        $.ajax({
            type: "GET",
            url: "PontosInteresseProximos",
            data: "latlng=" + latlng,
            success: function(result) {
                //alert(result);
                latlngSelecionada = latlng;
                infowindow.setContent(result);
                infowindow.open(map, marker);
            }
        });
    });

    google.maps.event.addListener(marker, 'dragend', function() {
//        alert("drag ended! start:" + startLocation.marker.getPosition() + " end:" + endLocation.marker.getPosition());

        var request = {
            origin: startLocation.marker.getPosition(),
            destination: endLocation.marker.getPosition(),
            travelMode: google.maps.DirectionsTravelMode.WALKING
        };

        startLocation.marker.setMap(null);
        endLocation.marker.setMap(null);

        waypts = [];

        directionsService.route(request, RenderCustomDirections);
    });

    return marker;
}

google.maps.event.addDomListener(window, 'load', initialize);


function hideElement(parada) {
    document.getElementById("hiddenEl").style.display = "none";
    $.ajax({
        type: "GET",
        url: "SugerirParadasUsuarios",
        data: {parada: parada, pontos: document.getElementById("localizacao").value, latlngPI: ""+latlngSelecionada},
        success: function(retorno) {
            $('#myModal').html(retorno);
            alert(latlngSelecionada);
            //alert("teste");
        }
    })
}

function teste(parada){
    var element = 'localizacao' + parada;
    var title = 'titulo' + parada;
    var parada = document.getElementById(element).value;
    var titulo = document.getElementById(title).value;
    //alert(parada);
    var lat = parada.substring(parada.indexOf("(") + 1, parada.indexOf(" "));
    var lng = parada.substring(parada.indexOf(" ") + 1, parada.indexOf(")"));
    
    //alert(lat);
    //alert(lng);
    
    var myLatlng = new google.maps.LatLng(lng,lat);
    var marker = new google.maps.Marker({
      position: myLatlng,
      map: map,
      title: titulo
  });
}

function desenhaLinha(linha) {
    //alert(linha);
    $.ajax({
        type: "GET",
        url: "DesenharLinhas",
        data: "id_linha=" + linha,
        success: function(retorno) {
            //alert('retorno desenhar linhas');
            $('#myModal').html(retorno);
            desenhaRotaLinha();
            //alert(retorno);
        }
    });
}