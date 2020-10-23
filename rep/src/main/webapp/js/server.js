/**
 * 
 */

var wpsclient;

var wpsui;

function showAddWPSServerDialog() {

	while ($('#main').find("#server_dialog").length > 0) {
		$("#server_dialog").remove();
		console.log("remove dialog...")
	}

	var imageUrlCog = "./resources/images/cog_add.png";

	var modalDialog = '<div class="modal fade" id="server_dialog">'
			+ '<div class="modal-dialog"  style="height:200px; max-height:500px; width:450px; max-width:600px;">'
			+ '	<div class="modal-content">'
			+ '      <div class="modal-header">'
			+ '          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
			+ '          <h5 class="modal-title"><img src="'
			+ imageUrlCog
			+ '"  width="16" height="16" border="0"> - Add WPS Server</h5>'
			+ '      </div>'
			+ '      <div class="modal-body">'
			+ '			<p> Server Name :  <br/> <input id="server_name" style=" width:400px;" value="연구교육플랫폼WPS서버" ></input>'
			+ '			<p> Server URL  :  <br/> <input id="server_url" style=" width:400px;" value="http://127.0.0.1:18080/geoserver/wps" ></input>'
			+ '		</div>'
			+ '      <div class="modal-footer">'
			+ '          <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel_buttton">Close</button>'
			+ '          <button type="button" class="btn btn-primary" id="btn-addServer-Accept">Accept</button>'
			+ '      </div>' + '	</div> ' + '</div></div>';

	$('#main').append(modalDialog);

	$('#server_dialog').modal('show');

	$('#btn-addServer-Accept')
			.on(
					'click',
					function(e) {
						$("#main-container").removeClass('hide');

						// 서버 연결...
						conWPSServerUrl = $('#server_url').val();
						conWPSServerName = $('#server_name').val();

						//var url = webProxy
						//		+ encodeURIComponent(conWPSServerUrl);
						var url = conWPSServerUrl;

						wpsclient = new wps.client({
							servers : {
								'wpsgui' : url
							}
						});

						wpsclient
								.getGroupedProcesses(
										'wpsgui',
										function(groups) {
											wpsui = new wps.ui(
													{
														client : wpsclient,
														spaceWidth : 1800,
														spaceHeight : 1000,
														scaleFactor : 0.8,
														defaultServer : 'wpsgui',
														getVectorLayers : true,
														parentContainer : $('.palette-scroll'),
														dropZone : $("#chart")
													});

											for ( var group in groups) {
												if (group == "statistics") {
													var content = wpsui
															.createProcessCategory(group);
													for (var i = 0, ii = groups[group].length; i < ii; ++i) {
														$(content)
																.append(
																		wpsui
																				.createProcess(groups[group][i]));
														console
																.log(groups[group][i]);
													}
												}
											}

											for ( var group in groups) {
												if (group != "statistics") {
													var content = wpsui
															.createProcessCategory(group);
													for (var i = 0, ii = groups[group].length; i < ii; ++i) {
														$(content)
																.append(
																		wpsui
																				.createProcess(groups[group][i]));
														console
																.log(groups[group][i]);
													}
												}
											}
										});
						/*
						
						$.ajax({
							type : 'POST',
							url : webToplevelPath + '/getScenarioList',
							dataType : 'json',    
							data: {
								'condition' : " 1=1 "
							},
							contentType: 'application/x-www-form-urlencoded; charset=utf-8',
							async: true,
							success : function(json) {
								console.log(json);
								var jsonLength = json.length;
								var editIcon = './resources/images/map_edit.png';
								for (var i = 0; i < jsonLength; i++) {
									var scenarioId = "li-scenario-" + json[i].fid;
									var scenarioItem = '<li><a href="#"  id="' + scenarioId
										+'" value="' + json[i].reqdoc +'" > ' + '	<img src="' + editIcon
										+ '" width="24" height="24" border="0"><b>- '
										+  json[i].title + '</b></a> ' + '</li>';
									$('#dropdown-scenario').append(scenarioItem);
									
									$('#' + scenarioId).click(function() {
										
										$("#scenario_dialog").remove();
										
										var imageUrlCog = "./resources/images/map_edit.png";
										
										// 삭제버튼추가..
										var modalDialog = 
											'<div id="scenario_dialog" class="embed">' +
											'<div style="height:200px; max-height:500px; width:450px; max-width:600px;">' +
										    '          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' +
										    '          <h5 class="modal-title"><img src="' + imageUrlCog + '"  width="16" height="16" border="0"> - 시나리오를 추가 합니다.</h5>' +
										    '			<p> 시나리오명 :  <br/> <input id="scenario_name" style=" width:400px;" value="' + json[i].title + '" ></input>' +
										    '			<p> 상세설명  :  <br/> <textarea id="scenario_reqdoc" style=" width:400px;" value="' + json[i].reqdoc + '"" ></textarea>' +
										    '</div></div>';

										$('#main').append(modalDialog); 
										$("#scenario_dialog").dialog({ 
											autoOpen: false,
											width: screen.width / 3,
											buttons: {
												"닫기" : function () {
													$(this).dialog("close");
													$("#scenario_dialog").remove();
												}
											}
										});
										$("#scenario_dialog").dialog("open");
									});
								}
								
								
							},    
							error : function(xhr, ajaxOptions, thrownError) {
								alert(' e : ' + xhr.status);
								alert(' e : ' + thrownError);
							}
						});
						 */
						$('#Panel-Processes').attr('style', "bottom: 10px");
						// $(this).dialog("close");
						$('#server_dialog').modal('hide');
						// $(".server_dialog").remove();
						// $("#server_dialog").remove();

						var liId = "li-openServer_"
								+ ($('#dropdown-addServer').children().length + 1);

						var onIcon = './resources/images/off.png';
						var serverItem = '<li><a href="#"  id="' + liId
								+ '" > ' + '	<img src="' + onIcon
								+ '" width="24" height="24" border="0"><b>- '
								+ conWPSServerName + '</b></a> ' + '</li>';
						$('#btn-run-process').css('display', 'block');
						$('#btn-clear').css('display', 'block');
						//$('#basic-scenario').css('display', 'block');
						$('#btn-scenario').css('display', 'block');

						$('#dropdown-addServer').append(serverItem);

						$('#' + liId).click(function() {
							if ($("#main-container").hasClass('hide')) {
								var icon = './resources/images/off.png';
								$('#' + liId).find('img')[0].src = icon;
								$("#main-container").removeClass('hide');
								$('#btn-run-process').css('display', 'block');
								$('#btn-clear').css('display', 'block');
								//$('#basic-scenario').css('display', 'block');
								//$('#btn-scenario').css('display', 'block');
							} else {
								var icon = './resources/images/on.png';
								$('#' + liId).find('img')[0].src = icon;
								$("#main-container").addClass('hide');
								$('#btn-run-process').css('display', 'none');
								$('#btn-clear').css('display', 'none');
								//$('#basic-scenario').css('display', 'none');
								//$('#btn-scenario').css('display', 'none');
							}
						});
					});

	function wpsServerUrlSetter(url) {
		globalWPSServerUrl = url;
	}
};

function showAddWFSServerDialog() {

	while ($('#main').find("#add-wfsserver_dialog").length > 0) {
		$("#add-wfsserver_dialog").remove();
		console.log("remove dialog...")
	}

	var imageUrlCog = "./resources/images/cog_add.png";

	var modalDialog = '<div class="modal fade" id="add-wfsserver_dialog">'
			+ '<div class="modal-dialog"  style="height:200px; max-height:500px; width:450px; max-width:600px;">'
			+ '	<div class="modal-content">'
			+ '      <div class="modal-header">'
			+ '          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
			+ '          <h5 class="modal-title"><img src="'
			+ imageUrlCog
			+ '"  width="16" height="16" border="0"> - Add WFS Server</h5>'
			+ '      </div>'
			+ '      <div class="modal-body">'
			+ '			<p> Add WFS Server - check Parameter.</p>'
			+ '			<p> Server Alias :  <br/> <input id="wfsserver_alias" style=" width:400px;" value="연구교육플랫폼 WFS Layers" ></input>'
			+ '			<p> WFS Service Url  :  <br/> <input id="wfsserver_url" style=" width:400px;" value="http://127.0.0.1:18080/geoserver/wfs" ></input>'
			+ '		</div>'
			+ '      <div class="modal-footer">'
			+ '          <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel_buttton">Close</button>'
			+ '          <button type="button" class="btn btn-primary" id="btn-addwfsServer-Accept">Accept</button>'
			+ '      </div>' + '	</div> ' + '</div></div>';

	$('#main').append(modalDialog);

	$('#add-wfsserver_dialog').modal('show');

	$('#btn-addwfsServer-Accept').on(
			'click',
			function(e) {
				//$("#main-container").removeClass('hide');
				//$('#Panel-Processes').attr('style', "bottom: 10px");
				$('#add-wfsserver_dialog').modal('hide');

				var liId = "li-wfsServer_"
						+ ($('#dropdown-addWfsServer').children().length + 1);
				var onIcon = './resources/images/brick_add.png';

				var wfsServerInfo = {
					'serverId' : liId,
					'alias' : $('#wfsserver_alias').val(),
					'url' : $('#wfsserver_url').val()
				};
				wfsServers.push(wfsServerInfo);
				var serverItem = '<li><a href="#"  id="' + liId + '" > '
						+ '	<img src="' + onIcon
						+ '" width="24" height="24" border="0"><b>- '
						+ wfsServerInfo.alias + '</b></a> ' + '</li>';
				$('#dropdown-addWfsServer').append(serverItem);
				openWfsInfDialog(liId);
				$('#' + liId).click(function(e) {
					var infoId = this.id;
					openWfsInfDialog(infoId);
				});
			});

	function openWfsInfDialog(infoId) {
		var serverInfo;
		for (var i = 0; i < wfsServers.length; i++) {
			if (infoId == wfsServers[i].serverId) {
				serverInfo = wfsServers[i];
				break;
			}
		}

		while ($("#wfs-capabilities-dialog").length > 0) {
			$("#wfs-capabilities-dialog").remove();
		}

		var featureTypes = [];
		var xmlhttp = new XMLHttpRequest();
		var getCapabilitiesUrl = serverInfo.url
				+ '?service=WFS&VERSION=1.1.0&request=GetCapabilities';
		xmlhttp.open("GET", getCapabilitiesUrl, true);
		xmlhttp.onload = function() {

			var selectOptionHtml = '<p class="form-row"><b>Selected Layer : </b></p>';
			selectOptionHtml += '<div><select class="form-control input-sm" style="margin-left: 0px; margin-bottom: 5px;" id="'
					+ infoId + '-wfsmap"></div>';
			if (this.responseXML !== null) {
				var info = jsonixUnMarshaller
						.unmarshalDocument(this.responseXML).value;
				if (info && info.featureTypeList
						&& info.featureTypeList.featureType) {
					selectOptionHtml += '<option value="" selected disabled>Select a value...</option>';
					for (var i = 0, ii = info.featureTypeList.featureType.length; i < ii; ++i) {
						var featureType = {};
						var ft = info.featureTypeList.featureType[i];
						featureType.name = ft.name;
						featureType.lowerCorner = ft.wgs84BoundingBox[0].lowerCorner;
						featureType.upperCorner = ft.wgs84BoundingBox[0].upperCorner;
						featureType.defaultSRS = ft.defaultSRS;
						featureTypes.push(featureType);

						//select box
						selectOptionHtml += '<option value="' + ft.name + '">'
								+ ft.name + '</option>';
					}
					serverInfo.featureTypes = featureTypes;
					selectOptionHtml += "</select>";

					var dataForm = "<div id=\"wfs-capabilities-dialog\" style=\"font-size:11px;\">"
							+ "<form id=\"wfs-capabilities-dialog-form\" class=\"form-horizontal\">"
							+ selectOptionHtml
							+ // select box
							"<p><b> Layer Metadata: </b> "
							+ "	<div id=\"wfs-layer-metadata1\" >"
							+ "	</div>"
							+ "<br /> "
							+ "<p><b> Layer Attributes : </b>"
							+ "	<div id=\"wfs-layer-metadata2\" style=\"max-height:250px; overflow:auto\" />"
							+ "</form>"
							+ "</div>";

					$('#main').append(dataForm);

					var metadata1TableTag = '<table id="boottable-wfs-metadata1" style="background-color:#ffffff;">'
							+ '<thead>'
							+ '	<tr>'
							+ '        <th data-field="defaultSRS">DefaultSRS</th>'
							+ '        <th data-field="lowerCorner">lowerCorner</th>'
							+ '        <th data-field="upperCorner">upperCorner</th>'
					'	</tr>' + '</thead>' + '</table>';

					$('#wfs-layer-metadata1').append(metadata1TableTag);

					var metadata2TableTag = '<table id="boottable-wfs-metadata2" data-id-field="name" style="background-color:#ffffff;">'
							+ '<thead>'
							+ '	<tr>'
							+ '        <th data-field="name">Name</th>'
							+ '        <th data-field="type">Typee</th>'
							+ '        <th data-field="maxOccurs" data-switchable="false">maxOccurs</th>'
							+ '        <th data-field="minOccurs" data-switchable="false">minOccurs</th>'
							+ '        <th data-field="nillable">Nillable</th>'
					'	</tr>' + '</thead>' + '</table>';

					$('#wfs-layer-metadata2').append(metadata2TableTag);

					$('#boottable-wfs-metadata1').bootstrapTable({
						cache : false,
						striped : false
					});

					$('#boottable-wfs-metadata2').bootstrapTable({
						cache : false,
						striped : false
					});

					$("#wfs-capabilities-dialog").dialog({
						modal : false,
						autoOpen : false,
						closeOnEscape : false,
						width : 750,
						buttons : [ {
							text : "Add Layer",
							click : function() {
								
								if ($('#'+ infoId+ '-wfsmap option:selected').val() != "") {
									repMap.addLayer(new ol.layer.Tile({
						  		    	title : $('#'+ infoId+ '-wfsmap option:selected').val(),
						  	    	    source: new ol.source.TileWMS({
						  	    	    url: serverInfo.url.replace("/wfs", "/wms"),
						  	    	    params: {
						  	    	      'TILED': 'true',
						  	    	      'VERSION': '1.1.0',
						  	    	      'LAYERS': $('#'+ infoId+ '-wfsmap option:selected').val(),
						  	    	      'FORMAT': 'image/png'
						  	    	    }
						  	    	  })
						  	    	}));
								}
								
							}
						}, {
//							text : "Download file",
//							click : function() {
//								$(this).dialog("close");
//								$("#wfs-capabilities-dialog").remove();
//							}
//						}, {
							text : "Close",
							click : function() {
								$(this).dialog("close");
								$("#wfs-capabilities-dialog").remove();
							}
						} ]
					});
					$("#wfs-capabilities-dialog").dialog("option", "title",
							serverInfo.alias).dialog("open");
					$('#' + infoId + '-wfsmap')
							.change(
									function(e) {
										console.log($('#'+ infoId+ '-wfsmap option:selected').val());
										//		                console.log(e);
										//		                console.log(this);
										var featureType = $(
												'#'
														+ infoId
														+ '-wfsmap option:selected')
												.val();
										for (var i = 0; i < featureTypes.length; i++) {
											if (featureType == featureTypes[i].name) {
												var metaDataInputdata1 = {
													'defaultSRS' : featureTypes[i].defaultSRS,
													'lowerCorner' : featureTypes[i].lowerCorner,
													'upperCorner' : featureTypes[i].upperCorner
												}
												
												$('#boottable-wfs-metadata1').bootstrapTable("load",[metaDataInputdata1]);
												break;
											}
										}
										var describeXmlhttp = new XMLHttpRequest();
										var describeUrl = serverInfo.url
												+ '?service=WFS&VERSION=1.1.0&request=DescribeFeatureType&TypeName='
												+ featureType;
										describeXmlhttp.open("GET",
												describeUrl, true);
										describeXmlhttp.onload = function() {
											console.log(this);
											console.log(featureTypes);
											//		            		  
											//		            		  var describeResult = jsonixUnMarshaller.unmarshalDocument(this.responseXML).value;
											//		            		  console.log(describeResult);
											var describeResult = xmlToJson(this.responseXML);
											var el = describeResult["xsd:schema"]["xsd:complexType"]["xsd:complexContent"]["xsd:extension"]["xsd:sequence"]["xsd:element"];
											var elLen = el.length;
											var metaDataInputdata2 = [];
											for (var i = 0; i < elLen; i++) {
												var attr = el[i]["@attributes"];
												metaDataInputdata2
														.push({ 
															'name' : attr.name,
															'type' : attr.type,
															'maxOccurs' : attr.maxOccurs,
															'minOccurs' : attr.minOccurs,
															'nillable' : attr.nillable
														});
											}
											$('#boottable-wfs-metadata2').bootstrapTable("load",metaDataInputdata2);

										}
										describeXmlhttp.send();
									});

				} else if (window.console) {
					window.console.warn('No featureTypes found on WFS server: '
							+ serverInfo.url);
				}
			} else if (window.console) {
				window.console
						.error('There was an error loading WFS 1.1.0 GetCapabilities from: '
								+ serverInfo.url);
			}
			console.log('featureTypes : ' + featureTypes);
		}
		xmlhttp.send();
	}
};

function showAddWCSServerDialog() {

	while ($('#main').find("#add-wcsserver_dialog").length > 0) {
		$("#add-wcsserver_dialog").remove();
		console.log("remove dialog...")
	}

	var imageUrlCog = "./resources/images/cog_add.png";

	var modalDialog = '<div class="modal fade" id="add-wcsserver_dialog">'
			+ '<div class="modal-dialog"  style="height:200px; max-height:500px; width:450px; max-width:600px;">'
			+ '	<div class="modal-content">'
			+ '      <div class="modal-header">'
			+ '          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>'
			+ '          <h5 class="modal-title"><img src="'
			+ imageUrlCog
			+ '"  width="16" height="16" border="0"> - Add WCS Server</h5>'
			+ '      </div>'
			+ '      <div class="modal-body">'
			+ '			<p> Add WCS Server - check Parameter.</p>'
			+ '			<p> Server Alias :  <br/> <input id="wcsserver_alias" style=" width:400px;" value="연구교육플랫폼 WCS Layers" ></input>'
			+ '			<p> WCS Service Url  :  <br/> <input id="wcsserver_url" style=" width:400px;" value="http://127.0.0.1:18080/geoserver/wcs" ></input>'
			+ '		</div>'
			+ '      <div class="modal-footer">'
			+ '          <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel_buttton">Close</button>'
			+ '          <button type="button" class="btn btn-primary" id="btn-addwcsServer-Accept">Accept</button>'
			+ '      </div>' + '	</div> ' + '</div></div>';

	$('#main').append(modalDialog);

	$('#add-wcsserver_dialog').modal('show');

	$('#btn-addwcsServer-Accept').on(
			'click',
			function(e) {
				$('#add-wcsserver_dialog').modal('hide');

				var liId = "li-wcsServer_"
						+ ($('#dropdown-addWcsServer').children().length + 1);
				var onIcon = './resources/images/brick_add.png';

				var wcsServerInfo = {
					'serverId' : liId,
					'alias' : $('#wcsserver_alias').val(),
					'url' : $('#wcsserver_url').val()
				};
				wcsServers.push(wcsServerInfo);
				var serverItem = '<li><a href="#"  id="' + liId + '" > '
						+ '	<img src="' + onIcon
						+ '" width="24" height="24" border="0"><b>- '
						+ wcsServerInfo.alias + '</b></a> ' + '</li>';
				$('#dropdown-addWcsServer').append(serverItem);
				openWcsInfDialog(liId);
				$('#' + liId).click(function(e) {
					var infoId = this.id;
					openWcsInfDialog(infoId);
				});
			});

	function openWcsInfDialog(infoId) {
		var serverInfo;
		for (var i = 0; i < wcsServers.length; i++) {
			if (infoId == wcsServers[i].serverId) {
				serverInfo = wcsServers[i];
				break;
			}
		}

		while ($("#wcs-capabilities-dialog").length > 0) {
			$("#wcs-capabilities-dialog").remove();
		}

		var coverages = [];
		var xmlhttp = new XMLHttpRequest();
		var getCapabilitiesUrl = serverInfo.url
				+ '?service=WCS&VERSION=1.1.1&request=GetCapabilities';
		xmlhttp.open("GET", getCapabilitiesUrl, true);
		xmlhttp.onload = function() {

			var selectOptionHtml = '<p class="form-row"><b>Selected Layer : </b></p>';
			selectOptionHtml += '<div><select class="form-control input-sm" style="margin-left: 0px; margin-bottom: 5px;" id="'
					+ infoId + '-wcsmap"></div>';
			if (this.responseXML !== null) {
				var info = jsonixUnMarshaller
						.unmarshalDocument(this.responseXML).value;
				if (info && info.contents
						&& info.contents.coverageSummary) {
					selectOptionHtml += '<option value="" selected disabled>Select a value...</option>';
					for (var i = 0, ii = info.contents.coverageSummary.length; i<ii; ++i) {
							var coverage = {};
				          if (info.contents.coverageSummary[i].content) {
				            for (var j=0, jj=info.contents.coverageSummary[i].content.length; j<jj; ++j) {
				              var content = info.contents.coverageSummary[i].content[j];
				              if (content.name.localPart === "Identifier") {
				                coverage.name = content.value;
				              }
				              if (content.name.localPart === "WGS84BoundingBox") {
				                coverage.lowerCorner = content.value.lowerCorner;
				                coverage.upperCorner = content.value.upperCorner;
				              }
				              if (coverage.name && coverage.lowerCorner && coverage.upperCorner) {
				                 coverages.push(coverage);
					              //select box
									selectOptionHtml += '<option value="' + coverage.name + '">'
											+ coverage.name + '</option>';
				              }
				            }
				          }
					}
					serverInfo.coverages = coverages;
					selectOptionHtml += "</select>";

					var dataForm = "<div id=\"wcs-capabilities-dialog\" style=\"font-size:11px;\">"
							+ "<form id=\"wcs-capabilities-dialog-form\" class=\"form-horizontal\">"
							+ selectOptionHtml
							+ // select box
							"<p><b> Layer Contents: </b> "
							+ "	<div id=\"wcs-layer-metadata\" >"
							+ "	</div>"
							+ "</form>"
							+ "</div>";

					$('#main').append(dataForm);

					var metadataTableTag = '<table id="boottable-wcs-metadata" style="background-color:#ffffff;" data-show-header="false">'
							+ '<thead>'
							+ '	<tr>'
							+ '        <th data-field="name">name</th>'
							+ '        <th data-field="value">value</th>'
					'	</tr>' + '</thead>' + '</table>';

					$('#wcs-layer-metadata').append(metadataTableTag);

					$('#boottable-wcs-metadata').bootstrapTable({
						cache : false,
						striped : false
					});

					$("#wcs-capabilities-dialog").dialog({
						modal : false,
						autoOpen : false,
						closeOnEscape : false,
						width : 750,
						buttons : [ {
							text : "Add Layer",
							click : function() {
								if ($('#'+ infoId+ '-wcsmap option:selected').val() != "") {
									repMap.addLayer(new ol.layer.Tile({
						  		    	title : $('#'+ infoId+ '-wcsmap option:selected').val(),
						  	    	    source: new ol.source.TileWMS({
						  	    	    url: serverInfo.url.replace("/wcs", "/wms"),
						  	    	    params: {
						  	    	      'TILED': 'true',
						  	    	      'VERSION': '1.1.0',
						  	    	      'LAYERS': $('#'+ infoId+ '-wcsmap option:selected').val(),
						  	    	      'FORMAT': 'image/png'
						  	    	    }
						  	    	  })
						  	    	}));
								}
							}
						}, {
//							text : "Download file",
//							click : function() {
//								$(this).dialog("close");
//								$("#wcs-capabilities-dialog").remove();
//							}
//						}, {
							text : "Close",
							click : function() {
								$(this).dialog("close");
								$("#wcs-capabilities-dialog").remove();
							}
						} ]
					});
					$("#wcs-capabilities-dialog").dialog("option", "title",
							serverInfo.alias).dialog("open");
					$('#' + infoId + '-wcsmap').change(
									function(e) {
										console.log($('#'+ infoId+ '-wcsmap option:selected').val());
										var coverage = $(	'#'+ infoId + '-wcsmap option:selected').val();
										var describeXmlhttp = new XMLHttpRequest();
										var describeUrl = serverInfo.url
												+ '?service=WCS&VERSION=1.1.1&request=DescribeCoverage&identifiers='
												+ coverage;
										describeXmlhttp.open("GET",
												describeUrl, true);
										describeXmlhttp.onload = function() {
											console.log(this);
											console.log(coverages);
											var describeInfo = jsonixUnMarshaller.unmarshalDocument(this.responseXML).value;
											console.log(describeInfo);
											var metaDataInputdata = [];
											
											function getKeywords() {
												var returntxt = '';
												for (var i = 0; i < describeInfo.coverageDescription[0].keywords[0].keyword.length; i++) {
													returntxt += describeInfo.coverageDescription[0].keywords[0].keyword[i].value;
													if (i!=(describeInfo.coverageDescription[0].keywords[0].keyword.length-1)) {
														returntxt += ", ";
													}
												}
												return returntxt;
											}
											
											var keyWords = getKeywords();
											
											metaDataInputdata.push({	'name' : 'Identifier', 'value' : describeInfo.coverageDescription[0].identifier });
											metaDataInputdata.push({	'name' : 'Title', 'value' : describeInfo.coverageDescription[0].title[0].value });
											//metaDataInputdata.push({	'name' : 'SurpportedCRS', 'value' : describeInfo.coverageDescription[0].supportedCRS });
											metaDataInputdata.push({	'name' : 'GridBaseCRS', 'value' : describeInfo.coverageDescription[0].domain.spatialDomain.gridCRS.gridBaseCRS });
											metaDataInputdata.push({	'name' : 'GridCS', 'value' : describeInfo.coverageDescription[0].domain.spatialDomain.gridCRS.gridCS });
											metaDataInputdata.push({	'name' : 'GridOffsets', 'value' : describeInfo.coverageDescription[0].domain.spatialDomain.gridCRS.gridOffsets  });
											metaDataInputdata.push({	'name' : 'GridOrigin', 'value' : describeInfo.coverageDescription[0].domain.spatialDomain.gridCRS.gridOrigin });
											metaDataInputdata.push({	'name' : 'GridType', 'value' : describeInfo.coverageDescription[0].domain.spatialDomain.gridCRS.gridType });
											metaDataInputdata.push({	'name' : 'LowerCorner', 'value' : describeInfo.coverageDescription[0].domain.spatialDomain.boundingBox[0].value.lowerCorner });
											metaDataInputdata.push({	'name' : 'UpperCorner', 'value' : describeInfo.coverageDescription[0].domain.spatialDomain.boundingBox[0].value.upperCorner });
											metaDataInputdata.push({	'name' : 'Keywords', 'value' : keyWords});
											
											$('#boottable-wcs-metadata').bootstrapTable("load",metaDataInputdata);

										}
										describeXmlhttp.send();
									});

				} else if (window.console) {
					window.console.warn('No featureTypes found on WCS server: '
							+ serverInfo.url);
				}
			} else if (window.console) {
				window.console
						.error('There was an error loading WFS 1.1.0 GetCapabilities from: '
								+ serverInfo.url);
			}
			console.log('featureTypes : ' + coverages);
		}
		xmlhttp.send();
	}

};

function xmlToJson(xml) {

	// Create the return object
	var obj = {};

	if (xml.nodeType == 1) { // element
		// do attributes
		if (xml.attributes.length > 0) {
			obj["@attributes"] = {};
			for (var j = 0; j < xml.attributes.length; j++) {
				var attribute = xml.attributes.item(j);
				obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
			}
		}
	} else if (xml.nodeType == 3) { // text
		obj = xml.nodeValue;
	}

	// do children
	if (xml.hasChildNodes()) {
		for (var i = 0; i < xml.childNodes.length; i++) {
			var item = xml.childNodes.item(i);
			var nodeName = item.nodeName;
			if (typeof (obj[nodeName]) == "undefined") {
				obj[nodeName] = xmlToJson(item);
			} else {
				if (typeof (obj[nodeName].push) == "undefined") {
					var old = obj[nodeName];
					obj[nodeName] = [];
					obj[nodeName].push(old);
				}
				obj[nodeName].push(xmlToJson(item));
			}
		}
	}
	return obj;
};