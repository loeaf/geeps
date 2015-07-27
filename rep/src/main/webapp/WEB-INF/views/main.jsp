<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>
<%@ page import="com.mangosystem.rep.resourceBundle.CommonConstants" %>
<%@ page import="com.mangosystem.rep.resourceBundle.SystemConstants" %>
<%
	String webMainTitle				= CommonConstants.WEB_MAIN_TITLE;
	//String webToplevelPath 		= request.getContextPath();
	String webToplevelPath 			= CommonConstants.WEB_CONTEXT_PATH;
	String sessionIndetifyInfo 		= CommonConstants.SESSION_IDENTIFY_INFO;
	
	String wsUrl						= SystemConstants.WS_URL;
	String wsPort						= SystemConstants.WS_PORT;
	String webProxy					= SystemConstants.WS_PROXY;
	
	String gsUrl						= SystemConstants.GS_URL;
	
	String wpsUrl						= SystemConstants.MAP_WPS_SERVER_URL;
	String wpsVer						= SystemConstants.MAP_WPS_SERVER_VERSION;
	String wmsUrl						= SystemConstants.MAP_WMS_SERVER_URL;
	String wmsVer						= SystemConstants.MAP_WMS_SERVER_VERSION;
	String wmsFor						= SystemConstants.MAP_WMS_SERVER_FORMAT;
	String wfsUrl						= SystemConstants.MAP_WFS_SERVER_URL;
	String wfsVer						= SystemConstants.MAP_WFS_SERVER_VERSION;
	String wcsUrl						= SystemConstants.MAP_WCS_SERVER_URL;
	String wcsVer						= SystemConstants.MAP_WCS_SERVER_VERSION;

	String tmsSrcSat					= SystemConstants.MAP_TMS_SRC_VWORLD_SAT_URL;
	String tmsSrcBase					= SystemConstants.MAP_TMS_SRC_VWORLD_BASE_URL;
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title><%=webMainTitle%></title>
    <meta charset="utf-8" />
    
    <script type="text/javascript">

		var conWPSServerUrl = '';
		var conWPSServerName = '';
		
		var wpsServerUrl         = "<%=wpsUrl%>";
		var wpsServerVersion     = "<%=wpsVer%>";
		
		var mapWmsServerUrl       = "<%=wmsUrl%>";
		var mapWmsServerVersion   = "<%=wmsVer%>";
		var mapWmsServerFormat    = "<%=wmsFor%>";
		
		var wfsServerUrl       = "<%=wfsUrl%>";
		var wfsServerVersion   = "<%=wfsVer%>";
		
		var wcsServerUrl       = "<%=wcsUrl%>";
		var wcsServerVersion   = "<%=wcsVer%>";
		
		var webMainTitle			  = "<%=webMainTitle%>";
		var webToplevelPath 		  = "<%=webToplevelPath%>";
		var webProxy				  = "<%=webProxy%>";
		var gsUrl				     = "<%=gsUrl%>";
		
		var tmsSrcSat			   = "<%=tmsSrcSat%>";
		var tmsSrcBase			= "<%=tmsSrcBase%>";
//		var baseMapExtent 		=  ["124.57221818364839","33.14122692848491", "130.96902929464568","38.62228683479944"];
		var baseMapExtent 		=  ["-14754.369140625","-44971.921875", "641805.3125","5724151"];
		
		var globalWPSServerUrl  = '';
		var initCenter = [14139000, 4500000];
		var initZoom = 9;
		var mandatoryOption = "false";
		
		
	</script>
     
    <script type="text/javascript" src="<%=webToplevelPath%>/resources/ol3/ol/ol-debug.js"></script>
    <script type="text/javascript" src="<%=webToplevelPath%>/resources/ol3/build/ol3-layerswitcher.js"></script>
    <script type="text/javascript" src="<%=webToplevelPath%>/resources/jquery/dist/jquery.cus.js"></script>
    <script type="text/javascript" src="<%=webToplevelPath%>/resources/jquery-ui/jquery-ui.cus.js"></script>
    <!-- 
    <script type="text/javascript" src="<%=webToplevelPath%>/resources/ol3/ol/ol-debug.js"></script>
    <script type="text/javascript" src="<%=webToplevelPath%>/resources/ol3/build/ol3-layerswitcher.js"></script>
    <script type="text/javascript" src="<%=webToplevelPath%>/resources/jquery/dist/jquery.cus.js"></script>
    <script type="text/javascript" src="<%=webToplevelPath%>/resources/jquery-ui/jquery-ui.cus.js"></script>
    -->
    
    <script type="text/javascript" src="<%=webToplevelPath%>/resources/bootstrap/dist/js/bootstap.cus.js"></script>
    <script type="text/javascript" src="<%=webToplevelPath%>/resources/d3/d3.cus.js"></script>
    <script type="text/javascript" src="<%=webToplevelPath%>/resources/jsonix/dist/jsonix-all.cus.js"></script>
    <script type="text/javascript" src="<%=webToplevelPath%>/resources/ol3/ol/highlight/highlight.pack.js"></script>
    <script type="text/javascript" src="<%=webToplevelPath%>/resources/vkBeautify/vkbeautify.cus.js"></script>
    <script type="text/javascript" src="<%=webToplevelPath%>/resources/wps-gui/dist/wps-gui_schema.js"></script>
    <script type="text/javascript" src="<%=webToplevelPath%>/resources/wps-gui/dist/wps-gui_client_c.js"></script>
    <script type="text/javascript" src="<%=webToplevelPath%>/resources/wps-gui/dist/wps-gui_ui_c.js"></script>
    
    
    <script type="text/javascript" src="<%=webToplevelPath%>/js/init.js"></script>
    <script type="text/javascript" src="<%=webToplevelPath%>/js/server.js"></script>
    <script type="text/javascript" src="<%=webToplevelPath%>/js/layers.js"></script>
    <!-- 
    <script type="text/javascript" src="<%=webToplevelPath%>/js/map.js"></script>
     -->
    <link rel="stylesheet" type="text/css" href="<%=webToplevelPath%>/resources/ol3/css/ol.css"/>
    <link rel="stylesheet" type="text/css" href="<%=webToplevelPath%>/resources/ol3/css/ol3-layerswitcher.css" />
    <link rel="stylesheet" type="text/css" href="<%=webToplevelPath%>/resources/bootstrap/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=webToplevelPath%>/resources/jquery-ui/themes/ui-lightness/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=webToplevelPath%>/resources/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="<%=webToplevelPath%>/css/main.css"/>
    
    <script type="text/javascript" src="<%=webToplevelPath%>/resources/bootstrap/plugins/bootstrap-table/src/bootstrap-table.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=webToplevelPath%>/resources/bootstrap/plugins/bootstrap-table/src/bootstrap-table.css" />
	
  </head>
  <body>
  <div id="repMap" class="map">
	  <script type="text/javascript" src="<%=webToplevelPath%>/js/map.js"></script>
    </div>

    <div id="panel-Top" class="panel-Top ui-state-default container-fluid"  role="navigation">
		<ul class="nav navbar-nav">
			<li class="dropdown">
            	<a href="#" class="dropdown-toggle" data-toggle="dropdown">WPS Server
            	<img src="<%=webToplevelPath%>/resources/images/bat_proc.png" width="24" height="24" border="0"> <b class="caret"></b></a>
            	<ul id="dropdown-addServer" class="dropdown-menu" style="min-width:300px;">
	                <li><a href="#" id="btn-addServer" >
	                	<img src="<%=webToplevelPath%>/resources/images/database_add.png" width="24" height="24" border="0"> - Add WPS Server</a>
	                </li>
	                <li role="presentation" class="divider"></li>
	            </ul>
        	</li>

	        <li class="dropdown">
	        	<a href="#" class="dropdown-toggle" data-toggle="dropdown">WFS Layers
	            <img src="<%=webToplevelPath%>/resources/images/wfs-layer.png" width="24" height="24" border="0"> <b class="caret"></b></a>
        		<ul id="dropdown-addWfsServer" class="dropdown-menu"  style="min-width:300px;">
        			<li>
	              	<a id="btn-addwfsServer">
	             		<img src="<%=webToplevelPath%>/resources/images/database_add.png" width="24" height="24" border="0"> - Add WFS Server</a>
	             </li>
        		  <li role="presentation" class="divider"></li>
	        	</ul>
        	</li>
        	
			<li class="dropdown">
            	<a id="basic-scenario" href="#" class="dropdown-toggle" data-toggle="dropdown">WCS Layers 
	            <img src="<%=webToplevelPath%>/resources/images/wcs-layer.png" width="24" height="24" border="0"> <b class="caret"></b></a>
	            <ul id="dropdown-addWcsServer" class="dropdown-menu" style="min-width:300px;">
		            	<li>
		              	<a id="btn-addwcsServer">
		             		<img src="<%=webToplevelPath%>/resources/images/database_add.png" width="24" height="24" border="0"> - Add WCS Server</a>
		             </li>
		             <li role="presentation" class="divider"></li>
                </ul>
          </li>
        	<li class="button">
        		<a id="btn-run-process" href="#" class="dropdown-toggle">Run 
	            <img src="<%=webToplevelPath%>/resources/images/running_process.png" width="24" height="24" border="0"> </a>
        	</li>
        	<li class="button">
        		<a id="btn-clear" href="#" class="dropdown-toggle">Clear 
	            <img src="<%=webToplevelPath%>/resources/images/edit-clear.png" width="24" height="24" border="0"> </a>
        	</li>
	  	</ul>
	</div>
     <div id="main-container" class="hide">
      <div id="Panel-Processes">
        <div class="palette-scroll" style="display:block;"></div>
        <div id="palette-search" style="display:block;">
          <i class="glyphicon glyphicon-search"></i><input id="palette-search-input" type="text"
            placeholder="filter" /><a href="#" id="palette-search-clear"><i
              class="glyphicon glyphicon-remove"></i></a>
        </div>
        <div style="position: absolute; top: 35px; left: 10px;">
	        <input type="checkbox" onclick="clickIsOptional()"> Mandatory Option Only </div>
        </div>
      <div id="workspace">
        <ul id="workspace-tabs" class="red-ui-tabs">
          <li class="red-ui-tab active" style="width: 100px"><a href="#chart"
              class="red-ui-tab-label" title="Builder">Builder</a></li>
          <li class="red-ui-tab" style="width: 100px"><a href="#tab-xml" class="red-ui-tab-label"
              title="XML">XML</a></li>
        </ul>
        <div id="workspace-add-tab"><span id="btn-workspace-add-tab"></span></div>
        <div id="workspace-content">
          <div id="chart" class="ui-droppable"></div>
          <div id="tab-xml" style="display:none"><pre><code class="xml"></code></pre></div>
        </div>
      </div>
      <div id="chart-zoom-controls">
        <div class="btn-group">
          <a class="btn btn-mini" title="Zoom out" id="btn-zoom-out" href="#"><i class="glyphicon glyphicon-zoom-out"
            ></i></a>
          <a class="btn btn-mini" title="Reset zoom" id="btn-zoom-zero" href="#"><i class="glyphicon glyphicon-th"
            ></i></a>
          <a class="btn btn-mini" title="Zoom in" id="btn-zoom-in" href="#"><i class="glyphicon glyphicon-zoom-in"
            ></i></a>
        </div>
      </div>
      <div id="sidebar">
        <ul id="sidebar-tabs" class="red-ui-tabs">
          <li class="red-ui-tab active" style="width:46%"><a href="#tab-inputs"
              class="red-ui-tab-label" title="Inputs">Inputs</a></li>
          <li class="red-ui-tab" style="width:46%"><a href="#tab-results" class="red-ui-tab-label"
              title="Console">Console</a></li>
        </ul>
        <div id="sidebar-content">
          <div id="tab-inputs"></div>
          <div id="tab-results" style="display:none"></div>
        </div>
      </div>
      <div id="sidebar-separator"></div>
     </div>  
     <div id="main" />
	 <div id="dialog" class="hide"><form id="dialog-form" class="form-horizontal"></form></div>
  </body>
</html>