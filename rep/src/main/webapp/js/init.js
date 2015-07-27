/**
 * 
 */
var conWPSServerUrl = '';
var conWPSServerName = '';
var baseMapExtent 		=  ["-14754.369140625","-44971.921875", "641805.3125","5724151"];

var globalWPSServerUrl  = '';

var initCenter = [14139000, 4500000];
var initZoom = 9;

var wfsServers = [];
var wcsServers = [];

var initAnalLayer = 'cite:korea_sgg';
var mandatoryOption = "false";

var jsonixContext = new Jsonix.Context([XLink_1_0, OWS_1_1_0, WPS_1_0_0, Filter_2_0, OWS_1_0_0, Filter_1_1_0, GML_2_1_2, WFS_1_1_0, GML_3_1_1, SMIL_2_0, SMIL_2_0_Language, WCS_1_1]);
var jsonixMarshaller = jsonixContext.createMmarshaller;
var jsonixUnMarshaller = jsonixContext.createUnmarshaller();


$(document).ready(function () {	
	$('#btn-addServer').click(showAddWPSServerDialog);
	$('#btn-addwfsServer').click(showAddWFSServerDialog);
	$('#btn-addwcsServer').click(showAddWCSServerDialog);
	
	
});

