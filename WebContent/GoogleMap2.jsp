<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*, data.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<meta charset="utf-8">
<title>Map Address</title>
<style type="text/css">
html, body, #map-canvas {
	height: 100%;
	margin: 0px;
	padding: 0px;
}

#panel {
	position: absolute;
	top: 5px;
	left: 50%;
	margin-left: -180px;
	z-index: 5;
	background-color: #fff;
	padding: 5px;
	border: 1px solid #999;
}
</style>
</head>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&language=at runat=server"></script>
<script	src='//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js'></script>
<script src="js/auto_zipcode3_script.js" charset="utf-8"></script>
<script>
	//var directionsDisplay;
	//var directionsService = new google.maps.DirectionsService();
	
	var map;
	var geocoder;
	var markers = [];
	$(function() {
		geocoder = new google.maps.Geocoder();
		$('#getHospital').click(function() {
			clearMarkers();
			markers = [];
			$.ajax({
				type : "POST",
				async : false,
				url : "Hospital",
				dataType : "json",
				data: $('#searchHospital').serializeArray(),
				success : function(Jdata) {
					var NumOfJData = Jdata.length;
					for (var i = 0; i < NumOfJData; i++) {
						decodeAddress(Jdata[i]);
					}
				},
				error : function() {
					alert("ERROR!!!");
				}
			});
		});
	});
	
	// Sets the map on all markers in the array.
	function setAllMap(map) {
	  for (var i = 0; i < markers.length; i++) {
		  markers[i].setMap(map);
	  }
	}

	// Removes the markers from the map, but keeps them in the array.
	function clearMarkers() {
	  setAllMap(null);
	}
	
	function initialize() {
		//geocoder = new google.maps.Geocoder(); //轉換地址Geocoder
		var latlng = new google.maps.LatLng(25.046507, 121.517537);
		var mapOptions = {
			zoom : 15,
			center : latlng
		}

		map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
		//directionsDisplay = new google.maps.DirectionsRenderer();  //導航DirectionsRenderer
		//directionsDisplay.setMap(map);

		//var marker = new google.maps.Marker({
			//map : map,
			//position : map.getCenter()
		//});
		//var infowindow = new google.maps.InfoWindow();

		//infowindow.setContent('${hospital[0].name}<br>${hospital[0].phone}<br>${hospital[0].addr}');
		//google.maps.event.addListener(marker, 'click', function() {
			//infowindow.open(map, marker);
		//});
		
		//console.log('${hospital.size()}');
		//decodeAddress('${hospital[0].addr}');

	}
	
	function decodeAddress(hospital) {
		geocoder.geocode({'address' : hospital.address}, function(results, status) {
			if (status == google.maps.DirectionsStatus.OK) {
				var position = results[0].geometry.location;
				map.setCenter(position);
				var marker = new google.maps.Marker({
					map : map,
					position : position
				});
				var infowindow = new google.maps.InfoWindow();				
				infowindow.setContent(hospital.name + '<br/>' + hospital.phone+ '<br/>' + hospital.address);
				google.maps.event.addListener(marker, 'click', function() {
					infowindow.open(map, marker);
				});
				markers.push(marker);
			}
			else if (status == google.maps.GeocoderStatus.OVER_QUERY_LIMIT){
				setTimeout(function() {
					decodeAddress(hospital);
	            }, 200);
			}
		});
	}

	function codeAddress() {
		var address = document.getElementById('addr').value;
		geocoder.geocode({'address' : address}, function(results, status) {
			if (status == google.maps.DirectionsStatus.OK) {
				map.setCenter(results[0].geometry.location);
				var marker = new google.maps.Marker({
					map : map,
					position : results[0].geometry.location
				});
				markers.push(marker);
			} else {
				alert('請重新輸入住址或名稱');
			}
		});		
	}
	
	function city_tchange()
	{
		
		var fm = eval("document.searchHospital");

		i = fm.city.selectedIndex;
		
		fm.cityArea.length = cityarea_account[i] - cityarea_account[i-1]+1;
		
		var index = cityarea_account[i-1] + 1;
		
		fm.cityArea.options[0].selected=true;
		fm.cityArea.options[0].value="%";
		fm.cityArea.options[0].text="查詢"+fm.city.value+"全區";
		for (j = 1; j < fm.cityArea.length; j ++) 
		{
			fm.cityArea.options[j].value = cityarea[index + j-1];
			fm.cityArea.options[j].text = cityarea[index + j-1];
		}
	}

	function check_data()
	{
		var s1 = document.getElementById('city').value;
		var s2 = document.getElementById('cityArea').value;  //區域

		var s = '選擇的地區是：\n' + s1 + ' ' + s2;
		alert(s);
		
	} 
	//google.maps.event.addDomListener(window, 'load', initialize);
	//google.maps.event.addDomListener(window, 'load', decodeAddress);
</script>
<body onload="initialize()">
	<div id="panel">
		<form id="searchHospital" name="searchHospital">
		<select name="city" ID="city" onChange="city_tchange();" class="select">
			<option value="%"       >請選擇縣市</option>
			<option value="基隆市" >基隆市</option>
			<option value="臺北市" >臺北市</option>
			<option value="新北市" >新北市</option>
			<option value="桃園市" >桃園市</option>
			<option value="新竹市" >新竹市</option>
			<option value="新竹縣" >新竹縣</option>
			<option value="苗栗縣" >苗栗縣</option>
			<option value="臺中市" >臺中市</option>
			<option value="彰化縣" >彰化縣</option>
			<option value="南投縣" >南投縣</option>
			<option value="雲林縣" >雲林縣</option>
			<option value="嘉義市" >嘉義市</option>
			<option value="嘉義縣" >嘉義縣</option>
			<option value="臺南市" >臺南市</option>
			<option value="高雄市" >高雄市</option>
			<option value="屏東縣" >屏東縣</option>
			<option value="臺東縣" >臺東縣</option>
			<option value="花蓮縣" >花蓮縣</option>
			<option value="宜蘭縣" >宜蘭縣</option>
			<option value="澎湖縣" >澎湖縣</option>
			<option value="金門縣" >金門縣</option>
			<option value="連江縣" >連江縣</option>
		</select>
		<select name="cityArea" ID="cityArea"  onChange="" size="1" class="select">
			<option value="%"  ></option>
		</select>
		<p><input type="button" id="getHospital" value="查詢"></p>
		</form>
		<input id="addr" type="textbox" placeholder="請輸入住址或名稱"> <input
			type="button" value="搜尋" onclick="codeAddress()">
	</div>
	<div id="map-canvas"></div>
</body>
</html>