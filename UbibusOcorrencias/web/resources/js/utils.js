/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
                     
function handlePointClick(event) {
    document.getElementById('latitude').value = event.latLng.lat();
    document.getElementById('longitude').value = event.latLng.lng();
    dialog.show();
}
            
function cancel() {
    dialog.hide();
    return false;
}

function getEndereco() {
    var latStr = document.getElementById('latitude').value;
    var lngStr = document.getElementById('longitude').value;
    var geocoder = new google.maps.Geocoder();
    var latLng = new google.maps.LatLng(parseFloat(latStr), parseFloat(lngStr));
    geocoder.geocode( {
        'latLng': latLng
    }, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            // Chama o remoteCommand "remoteCommandReverse", 
            // transformando as coordenadas em parameters para o contexto jsf
            // estes dados serao utilizados para gravar no banco o endereco referente a ocorrencia
            remoteCommandReverse([ {
                name : 'status',
                value : 'OK'
            }, {
                name : 'postal_code',
                value : getResult(results,'postal_code')
            }, {
                name : 'route',
                value : getResult(results,'route')
            }, {
                name : 'sublocality',
                value : getResult(results,'sublocality')
            }]);
        }
    });
}

function getResult(results, type){
    var ret;
    for(var i=0; i < results.length;i++){
        for(var j=0;j < results[i].address_components.length; j++){
            for(var k=0; k < results[i].address_components[j].types.length; k++){
                if(results[i].address_components[j].types[k] == type){
                    ret = results[i].address_components[j].long_name;
                    break;
                }
            }
        }
    }
    return ret;
}

function handleComplete(xhr, status, args) {
    $('#page_status_start').css('display','block');
    if(!args.validationFailed){
        getEndereco();
    } else {
        $('#page_status_start').css('display','none');
        return;
    }
}