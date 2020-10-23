/**
 * 
 */
function setActiveLayer() {
	
	// remove layer
	while (repMap.getLayers().getArray().length != 0) {
		repMap.removeLayer(repMap.getLayers().getArray()[0]);
	}
	
	for (la in olLayers) {
		repMap.addLayer(olLayers[la]);
	}
	
	repMap.getView().setCenter(initCenter);
	repMap.getView().setZoom(initZoom);
	
};

function removeLayer(layerName) {
	var mapLayers = repMap.getLayers().getArray();
    for (var idx in mapLayers) {
       var layer = mapLayers[idx];
       if (layer.get('title') == layerName) {
    	   repMap.removeLayer(layer);
           return;
       }
    }
};

function createWMSLayer(source, title, styles, baseLayer, visible, tile) {
  removeLayer(title);
  var wmsLayer = new ol.layer.Tile({
	title : title,
	type: baseLayer ? 'base' : '',
	visible : visible,
    source: new ol.source.TileWMS({
    url: "http://127.0.0.1:18080/geoserver/wms",
    params: {
      'TILED': tile,
      'VERSION': '1.1.0',
      'STYLES': styles,
      'LAYERS': source,
      'FORMAT': 'image/png'
    }
    })
  })
  return wmsLayer;
};

function loadPoints() {  
	repMap.addLayer(createWMSLayer('cite:korea_sgg', 'cite:korea_sgg', '', false, false, true));
	repMap.addLayer(createWMSLayer('cite:emd', 'cite:emd', '', false, false, true));
	repMap.addLayer(createWMSLayer('cite:sgg', 'cite:sgg', '', false, false, true));
	repMap.addLayer(createWMSLayer('cite:sid', 'cite:sid', '', false, true, true));
  
	repMap.addLayer(createWMSLayer('cite:road', 'cite:road', '', false, false, true));
  //map.addLayer(createWMSLayer('cite:building_points', 'cite:building_points', '', false, false, true));
	repMap.addLayer(createWMSLayer('cite:emd_points', 'cite:emd_points', '', false, false, true));
  
	repMap.addLayer(createWMSLayer('cite:lotteria', 'cite:lotteria', '', false, false, true));
	repMap.addLayer(createWMSLayer('cite:bugerking', 'cite:bugerking', '', false, false, true));
	repMap.addLayer(createWMSLayer('cite:dunkindonuts', 'cite:dunkindonuts', '', false, false, true));
  repMap.addLayer(createWMSLayer('cite:kfc', 'cite:kfc', '', false, false, true));
  repMap.addLayer(createWMSLayer('cite:mcdonalds', 'cite:mcdonalds', '', false, false, true));
  repMap.addLayer(createWMSLayer('cite:stations', 'cite:stations', '', false, false, true));
  repMap.addLayer(createWMSLayer('cite:bank', 'cite:bank', '', false, false, true));
  repMap.addLayer(createWMSLayer('cite:enterprise', 'cite:enterprise', '', false, false, true));
  
  repMap.addLayer(createWMSLayer('cite:pubs', 'cite:pubs', '', false, false, true));
  repMap.addLayer(createWMSLayer('cite:stores', 'cite:stores', '', false, false, true));
  repMap.addLayer(createWMSLayer('cite:theaters', 'cite:theaters', '', false, false, true));
  repMap.addLayer(createWMSLayer('cite:gasstation', 'cite:gasstation', '', false, false, true));
  repMap.addLayer(createWMSLayer('cite:apartment', 'cite:apartment', '', false, false, true));
  repMap.addLayer(createWMSLayer('cite:school', 'cite:school', '', false, false, true));
};

var point_list = ['cite:apartment', 'cite:gasstation', 'cite:school', 'cite:stores', 'cite:theaters', 'cite:pubs', 'cite:stations', 'cite:bank', 'cite:enterprise', 'cite:lotteria', 'cite:bugerking', 'cite:dunkindonuts', 'cite:kfc', 'cite:mcdonalds', 'cite:emd_points', 'cite:building_points'];
var linestring_list = ['cite:road'];
var polygon_list = ['cite:sid', 'cite:sgg', 'cite:emd', 'cite:metro', 'cite:korea_sgg'];

function loadPointCluster() {
	while ($('#main').find("#load_wfsLayer-dialog").length > 0) {
		$("#load_wfsLayer-dialog").remove();
		console.log("remove dialog...")
	}
    var content = '<div class="modal fade" id="load_wfsLayer-dialog">';
    content += '<div class="modal-dialog" style="height:200px; max-height:500px; width:450px; max-width:600px;">';
    content += '  <div class="modal-content">';
    content += '      <div class="modal-header">';
    content += '          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>';
    content += '          <h5 class="modal-title"><img src="./resources/images/cog_add.png" width="16" height="16" border="0"> Add Point Cluster Map (WFS)</h5>';
    content += '      </div>';

    content += '      <div class="modal-body">';
    
    content += '          <div class="input-group input-group-sm">';
    content += '              <span class="input-group-addon">Input Features *</span>';
    
    content += '              <select id="inputFeatures" class="form-control">';
    for (index = 0; index < point_list.length; index++) {
        content += '                  <option>' + point_list[index] + '</option>';
    }
    content += '              </select>';
    content += '          </div>';

    content += '      </div>';

    content += '      <div class="modal-footer">';
    content += '          <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel_buttton">Cancel</button>';
    content += '          <button type="button" class="btn btn-primary" id="wfsExcute">Select</button>';
    content += '      </div>';

    content += '  </div>';
    content += '</div></div>';

    $('#main').append(content);
    
    $('#wfsExcute').on('click', function(x){
        var inputFeatures = $("#inputFeatures option:selected").text();
        
        /*
        // Define three colors that will be used to style the cluster features
        // depending on the number of features they contain.
        var colors = {
            low: "rgb(181, 226, 140)", 
            middle: "rgb(241, 211, 87)", 
            high: "rgb(253, 156, 115)"
        };
        
        // Define three rules to style the cluster features.
        var lowRule = new OpenLayers.Rule({
            filter: new OpenLayers.Filter.Comparison({
                type: OpenLayers.Filter.Comparison.LESS_THAN,
                property: "count",
                value: 15
            }),
            symbolizer: {
                fillColor: colors.low,
                fillOpacity: 0.9, 
                strokeColor: colors.low,
                strokeOpacity: 0.5,
                strokeWidth: 12,
                pointRadius: 10,
                label: "${count}",
                labelOutlineWidth: 1,
                fontColor: "#ffffff",
                fontOpacity: 0.8,
                fontSize: "12px"
            }
        });
        var middleRule = new OpenLayers.Rule({
            filter: new OpenLayers.Filter.Comparison({
                type: OpenLayers.Filter.Comparison.BETWEEN,
                property: "count",
                lowerBoundary: 15,
                upperBoundary: 50
            }),
            symbolizer: {
                fillColor: colors.middle,
                fillOpacity: 0.9, 
                strokeColor: colors.middle,
                strokeOpacity: 0.5,
                strokeWidth: 12,
                pointRadius: 15,
                label: "${count}",
                labelOutlineWidth: 1,
                fontColor: "#ffffff",
                fontOpacity: 0.8,
                fontSize: "12px"
            }
        });
        var highRule = new OpenLayers.Rule({
            filter: new OpenLayers.Filter.Comparison({
                type: OpenLayers.Filter.Comparison.GREATER_THAN,
                property: "count",
                value: 50
            }),
            symbolizer: {
                fillColor: colors.high,
                fillOpacity: 0.9, 
                strokeColor: colors.high,
                strokeOpacity: 0.5,
                strokeWidth: 12,
                pointRadius: 20,
                label: "${count}",
                labelOutlineWidth: 1,
                fontColor: "#ffffff",
                fontOpacity: 0.8,
                fontSize: "12px"
            }
        });
        
        // Create a Style that uses the three previous rules
        var style = new OpenLayers.Style(null, {
            rules: [lowRule, middleRule, highRule]
        });
                
        var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
        renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;

        var vectorLayer = new OpenLayers.Layer.Vector("Point Cluster", {
            protocol: new OpenLayers.Protocol.HTTP({
                url: PROXY_URL + WFS_SERVER_URL + "?service=WFS&version=1.1.0&request=GetFeature&typeName=" + inputFeatures + "&outputFormat=application/json",
                format: new OpenLayers.Format.GeoJSON()
            }),
            renderers: renderer,
            strategies: [
                new OpenLayers.Strategy.Fixed(),
                new OpenLayers.Strategy.AnimatedCluster({
                    distance: 45,
                    animationMethod: OpenLayers.Easing.Expo.easeOut,
                    animationDuration: 10
                })
            ],
            styleMap:  new OpenLayers.StyleMap(style)
        });
        */
        
        //var getFeatureD = '/ows?service=WFS&version=1.1.0&request=GetFeature&outputFormat=json&typeName=' + inputFeatures + "&crsName=epsg:3857";
        var getFeatureD = '/ows?service=WFS&version=1.1.0&request=GetFeature&outputFormat=json&typeName=' + inputFeatures + "&srsName=epsg:3857";
		var getFeatureUrl = webProxy + encodeURIComponent(gsUrl + getFeatureD);
		//var getFeatureUrl = webProxy + encodeURIComponent("http://59.11.215.229:8080/gxt" + getFeatureD);
		//var getFeatureUrl = webProxy + 'http%3A%2F%2F59.11.215.229%3A8881%2Fgxt%2F%2Fows%3Fservice%3DWFS%26version%3D1.1.0%26request%3DGetFeature%26outputFormat%3Djson%26typeName%3Dkrs_pa_pop_urban';
		 
		 $.ajax({
			 url: getFeatureUrl
		 })
		 .done(function(getFeatureResult) {
			 console.log("done");
			 console.log(getFeatureResult);
			 if (getFeatureResult.features != undefined) {
				 var white = [255, 255, 255, 1];
				 var blue = [0, 153, 255, 1];
				 var width = 3;
				 
				 
				 
				 var clusterSource = new ol.source.Cluster({
					  distance: 10,
					  extractStyles: false,
					  source: new ol.source.GeoJSON({
			     		   text: getFeatureResult
			     	   })
					});
				 
				 
				 function rc() {
	    	                var letters = '0123456789ABCDEF'.split('');
	    	                var color = '#';
	    	                for (var i = 0; i < 6; i++ ) {
	    	                    color += letters[Math.floor(Math.random() * 16)];
	    	                }
	    	                return color;
	    	            
				 }
				 var color = rc();
				 
				 
				 var styleCache = {};
				 var vectorl = new ol.layer.Vector({
					 source: clusterSource,
		    	     style: function(feature, resolution) {
	    	    	    var size = feature.get('features').length;
	    	    	    var style = styleCache[size];
	    	    	    if (!style) {
	    	    	      style = [new ol.style.Style({
	    	    	        image: new ol.style.Circle({
	    	    	          radius: 10,
	    	    	          stroke: new ol.style.Stroke({
	    	    	            color: '#fff'
	    	    	          }),
	    	    	          fill: new ol.style.Fill({
	    	    	            color: color
	    	    	          })
	    	    	        }),
	    	    	        text: new ol.style.Text({
	    	    	          text: size.toString(),
	    	    	          fill: new ol.style.Fill({
	    	    	            color: '#fff'
	    	    	          })
	    	    	        })
	    	    	      })];
	    	    	      styleCache[size] = style;
	    	    	    }
	    	    	    return style;
	    	    	  }
		        });
		    	vectorl.set('title', inputFeatures);
		    	repMap.addLayer(vectorl);
		        console.log(getFeatureResult);
		        //map.addLayer(createWMSLayer(inputFeatures, inputFeatures, '', false, true, true));
		        //replaceLayer(vectorLayer);
		        
		        var colors = {
		                low: "rgb(181, 226, 140)", 
		                middle: "rgb(241, 211, 87)", 
		                high: "rgb(253, 156, 115)"
		        };
		        
		        $('#load_wfsLayer-dialog').modal('hide');
		        
				 
			 } else {
				 console.log("fail");
			 }
		 })
		 .success(function(response) {
			   console.log("success");
		 })
		 .fail(function(response) {
			   console.log("fail");
			   $('#load_wfsLayer-dialog').modal('hide');
		 });
        
        
    });

    $('#load_wfsLayer-dialog').modal('show');
};





function standardDistance() {
	
	while ($('#main').find("#standardDistance-dialog").length > 0) {
		$("#standardDistance-dialog").remove();
		console.log("remove dialog...")
	}
    var content = '<div class="modal fade" id="standardDistance-dialog">';
    content += '<div class="modal-dialog" style="height:200px; max-height:500px; width:450px; max-width:600px;">';
    content += '  <div class="modal-content">';
    content += '      <div class="modal-header">';
    content += '          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>';
    content += '          <h5 class="modal-title"><img src="images/cog_add.png" width="16" height="16" border="0"> Standard Distance Process</h5>';
    content += '      </div>';

    content += '      <div class="modal-body">';
    content += '          <div class="input-group input-group-sm">';
    content += '              <span class="input-group-addon">Input Features *</span>';
    
    content += '              <select id="inputFeatures" class="form-control">';
    for (var index = 0; index < point_list.length; index++) {
        content += '                  <option>' + point_list[index] + '</option>';
    }
    content += '              </select>';
    
    content += '          </div>';
    content += '          <div class="input-group input-group-sm">';
    content += '              <span class="input-group-addon">Circle Size</span>';
    //content += '              <input type="text" id="circleSize" class="form-control" placeholder="circleSize" value="1_Standard_Deviation">';
    
    var circleSizes = ['1_Standard_Deviation', '2_Standard_Deviation', '3_Standard_Deviation'];
    content += '              <select id="circleSize" class="form-control">';
    for (index = 0; index < circleSizes.length; index++) {
        content += '                  <option>' + circleSizes[index] + '</option>';
    }
    content += '              </select>';    
    
    content += '          </div>';
    content += '          <div class="input-group input-group-sm">';
    content += '              <span class="input-group-addon">Weight Field</span>';
    content += '              <input type="text" id="weightField" class="form-control" placeholder="weightField" value="">';
    content += '          </div>';
    content += '          <div class="input-group input-group-sm">';
    content += '              <span class="input-group-addon">Case Field</span>';
    content += '              <input type="text" id="caseField" class="form-control" placeholder="caseField" value="sgg_cd">';
    content += '          </div>';

    content += '      </div>';

    content += '      <div class="modal-footer">';
    content += '          <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel_buttton">Cancel</button>';
    content += '          <button type="button" class="btn btn-primary" id="execute">Execute</button>';
    content += '      </div>';

    content += '  </div>';
    content += '</div>';
    $('#main').append(content);
    
    $('#execute').on('click', function(x){
        var inputFeatures = $("#inputFeatures option:selected").text();
        var circleSize = $("#circleSize option:selected").text();
        var weightField = $('#weightField').val();
        var caseField = $('#caseField').val();
        
        // Standard Distance process
        var xml = '';
        xml += '<?xml version="1.0" encoding="UTF-8"?>';
        xml += '<wps:Execute version="1.0.0" service="WPS" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://www.opengis.net/wps/1.0.0" xmlns:wfs="http://www.opengis.net/wfs" xmlns:wps="http://www.opengis.net/wps/1.0.0" xmlns:ows="http://www.opengis.net/ows/1.1" xmlns:gml="http://www.opengis.net/gml" xmlns:ogc="http://www.opengis.net/ogc" xmlns:wcs="http://www.opengis.net/wcs/1.1.1" xmlns:xlink="http://www.w3.org/1999/xlink" xsi:schemaLocation="http://www.opengis.net/wps/1.0.0 http://schemas.opengis.net/wps/1.0.0/wpsAll.xsd">';
        xml += '  <ows:Identifier>statistics:StandardDistance</ows:Identifier>';
        xml += '  <wps:DataInputs>';
        xml += '    <wps:Input>';
        xml += '      <ows:Identifier>inputFeatures</ows:Identifier>';
        xml += '      <wps:Reference mimeType="text/xml" xlink:href="http://geoserver/wfs" method="POST">';
        xml += '        <wps:Body>';
        xml += '          <wfs:GetFeature service="WFS" version="1.0.0" outputFormat="GML3" srsName="epsg:5181" xmlns:cite="http://www.opengeospatial.net/cite">';
        xml += '            <wfs:Query typeName="' + inputFeatures + '"/>';
        xml += '          </wfs:GetFeature>';
        xml += '        </wps:Body>';
        xml += '      </wps:Reference>';
        xml += '    </wps:Input>';
        xml += '    <wps:Input>';
        xml += '      <ows:Identifier>circleSize</ows:Identifier>';
        xml += '      <wps:Data>';
        xml += '        <wps:LiteralData>' + circleSize + '</wps:LiteralData>';
        xml += '      </wps:Data>';
        xml += '    </wps:Input>';
        xml += '    <wps:Input>';
        xml += '      <ows:Identifier>weightField</ows:Identifier>';
        xml += '      <wps:Data>';
        xml += '        <wps:LiteralData>' + weightField + '</wps:LiteralData>';
        xml += '      </wps:Data>';
        xml += '    </wps:Input>';
        xml += '    <wps:Input>';
        xml += '      <ows:Identifier>caseField</ows:Identifier>';
        xml += '      <wps:Data>';
        xml += '        <wps:LiteralData>' + caseField + '</wps:LiteralData>';
        xml += '      </wps:Data>';
        xml += '    </wps:Input>';
        xml += '  </wps:DataInputs>';
        xml += '  <wps:ResponseForm>';
        //xml += '    <wps:RawDataOutput mimeType="text/xml; subtype=gml/3.1.1">';
        xml += '    <wps:RawDataOutput mimeType="application/json">';
        xml += '      <ows:Identifier>result</ows:Identifier>';
        xml += '    </wps:RawDataOutput>';
        xml += '  </wps:ResponseForm>';
        xml += '</wps:Execute>';
        
//        var getFeatureD = '/ows?service=WFS&version=1.1.0&request=GetFeature&outputFormat=json&typeName=' + inputFeatures + "&srsName=epsg:3857";
//        var getFeatureUrl = webProxy + encodeURIComponent(gsUrl + getFeatureD);
        $.ajax({
    	  type: "POST",
    	  url: webProxy + encodeURIComponent(gsUrl + "/wps"),
    	  headers: {
              "Content-Type": "text/xml;charset=utf-8"
          },
    	  data: xml,
    	  success: standardDistanceSuccess
    	});

        function standardDistanceSuccess(res) {
        	console.log(res);
        	
        	var vectorl = new ol.layer.Vector({
    		   source: new ol.source.GeoJSON({
    			   text: res,
    		   }),
    		   projection: 'http://www.opengis.net/gml/srs/epsg.xml#5181',
    		   style: new ol.style.Style({
    			   stroke: new ol.style.Stroke({
    				   color: '#00FFFF',
    				   width: 2,
    				   opacity: '1'
    			   }),
    			   fill : new ol.style.Fill({
    				   color: '#8A2BE2',
    				   opacity: '0'
    			   })
    		   })
    	   });
    	    
	    	vectorl.set('title', inputFeatures);
	    	repMap.addLayer(vectorl);
	        //map.addLayer(createWMSLayer(inputFeatures, inputFeatures, '', false, true, true));
	        //replaceLayer(vectorLayer);
        	    
	    	$("#standardDistance-dialog").modal('hide');
	    	//while ($('#main').find("#standardDistance-dialog").length > 0) {
	    	//	$("#standardDistance-dialog").remove();
	    	//	console.log("remove dialog...")
	    	//}
        }
        /*
        var request = new OpenLayers.Request.POST({
            url: webProxy + encodeURIComponent(gsUrl + "/wps"),
            data: xml,
            headers: {
                "Content-Type": "text/xml;charset=utf-8"
            },
            callback: function (response) {
                var responseText = response.responseText;
                
                var style = new OpenLayers.Style({
                    strokeColor: "#8A2BE2",
                    strokeOpacity: 1,
                    strokeWidth: 2,
                    fillColor: "#00FFFF",
                    fillOpacity: 0.0
                });
                
                var selectedStyle = new OpenLayers.Style({
                    strokeColor: "#00FFFF",
                    strokeOpacity: 1,
                    strokeWidth: 2,
                    fillColor: "#8A2BE2",
                    fillOpacity: 0.0
                });
                
                var styleMap = new OpenLayers.StyleMap({'default': style,'select': selectedStyle});
                
                var format = new OpenLayers.Format.GeoJSON();
                var features = format.read(request.responseText, 'FeatureCollection')
                
                var renderer = OpenLayers.Util.getParameters(window.location.href).renderer;
                renderer = (renderer) ? [renderer] : OpenLayers.Layer.Vector.prototype.renderers;
                                
                var vectorLayer = new OpenLayers.Layer.Vector("StandardDistance", {
                    styleMap: styleMap,
                    strategies: [],
                    renderers: renderer
                });
                vectorLayer.addFeatures(features);
                
                map.addLayer(createWMSLayer(inputFeatures, inputFeatures, '', false, true, true));
                replaceLayer(vectorLayer);
                
                modalWindow.modal('hide');
            },
            failure: function (response) {
                alert("Something went wrong in the request");
            }
        });
        */

    });
    $("#standardDistance-dialog").modal('show');
};

function addScenario() {
	
	
	//  시나리오 체크...
	if (d3.selectAll(".node_selected")[0].length==0){
		alert('Please create a scenario...');
		return;
	}
	
	if (lastExcuteDoc == "" ) {
		alert('Please create a scenario...');
		return;
	}
	
	while($('#main').find("#scenario_dialog").length > 0) {
		$("#scenario_dialog").remove();
		console.log("remove dialog...")
	}
	
	var imageUrlCog = "./resources/images/cog_add.png";
	
	// 삭제버튼추가..
	var modalDialog = 
		'<div class="modal fade" id="scenario_dialog">' +
		'<div class="modal-dialog"  style="height:200px; max-height:500px; width:450px; max-width:600px;">' +
	    '	<div class="modal-content">' +
	    '      <div class="modal-header">' +
	    '          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' +
	    '          <h5 class="modal-title"><img src="' + imageUrlCog + '"  width="16" height="16" border="0"> - 시나리오를 추가 합니다.</h5>' +
	    '      </div>' +
	    '      <div class="modal-body">' +
	    '			<p> 시나리오명 :  <br/> <input id="scenario_name" style=" width:400px;" value="시나리오 1" ></input>' +
	    '			<p> 상세설명  :  <br/> <textarea id="server_url" style=" width:400px;" value="시나리오 설명." ></textarea>' +
	    '		</div>' +
	    '      <div class="modal-footer">' +
	    '          <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel_buttton">Close</button>' +
	    '          <button type="button" class="btn btn-primary" id="btn-scenario-Accept">Accept</button>' +
	    '      </div>' +
	    '	</div> ' + 
	    '</div></div>';

	$('#main').append(modalDialog);
	
	$('#scenario_dialog').modal('show');
	
	$('#btn-scenario-Accept').on('click', function (e) {
		
        $('#scenario_dialog').modal('hide');
        
        var liId = "li-scenario_" + ($('#dropdown-scenario').children().length + 1);
        
        var onIcon = './resources/images/cog_add.png';
		var serverItem = '<li><a href="#"  id="' + liId + '" > ' + 
	    '	<img src="' + onIcon + '" width="24" height="24" border="0"><b>- ' + $('#scenario_name').val() + '</b></a> ' +
	    '</li>';
		
		$('#dropdown-scenario').append(serverItem);
		
		$('#'+liId).click(function () {
			alert('시나리오를 불러옵니다!');
			
		});
	});
	
	function wpsServerUrlSetter(url) {
		// @TODO 여기서 dropmenu의 상태를 비활성화 시켜야 한다.
		globalWPSServerUrl = url;
	}
}
