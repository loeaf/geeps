<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% response.setContentType("text/html; charset=utf-8"); %>
<%@ page import="com.mangosystem.rep.resourceBundle.CommonConstants" %>
<%@ page import="com.mangosystem.rep.resourceBundle.SystemConstants" %>
<%
	String webMainTitle		= CommonConstants.WEB_MAIN_TITLE;
	String webToplevelPath 	= request.getContextPath();
	String webProxy			= SystemConstants.WS_PROXY;
	String gsUrl			= SystemConstants.GS_URL;
	
	String tmsSrcSat			= SystemConstants.MAP_TMS_SRC_VWORLD_SAT_URL;
	String tmsSrcBase			= SystemConstants.MAP_TMS_SRC_VWORLD_BASE_URL;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title><%=webMainTitle%></title>
    <meta charset="utf-8" />
    <meta name="viewport"
      content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="mobile-web-app-capable" content="yes" />
    <link rel="stylesheet" type="text/css" href="<%=webToplevelPath%>/resources/wps-gui/dist/wps-gui.min.css" />
    <link rel="stylesheet" href="<%=webToplevelPath%>/resources/bootstrap/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=webToplevelPath%>/resources/jquery-ui/themes/ui-lightness/jquery-ui.css"/>
    <link rel="stylesheet" href="<%=webToplevelPath%>/resources/font-awesome/css/font-awesome.min.css"/>
  </head>
  <body>
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="brand" href="#">
            <span class="wpsbuilder">GeoServer WPS Builder</span>
          </a>
          <div class="btn-group">
            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
              data-toggle="dropdown">
              <i class="fa fa-file fa-fw"></i>File <span class="caret"></span>
            </button>
            <ul class="wpsgui dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
              <li role="presentation"><a role="menuitem" id="file-open" tabindex="-1" href="#"><i
                    class="fa fa-folder-open-o fa-fw"></i>Open from local browser storage</a></li>
              <li role="presentation"><a role="menuitem" id="file-save" tabindex="-1" href="#"><i
                    class="fa fa-save fa-fw"></i>Save to local browser storage</a></li>
              <li role="presentation"><a role="menuitem" id="import-clipboard" tabindex="-1"
                  href="#"><i class="fa fa-clipboard fa-fw"></i>Import from clipboard</a></li>
              <li role="presentation"><a role="menuitem" id="export-clipboard" tabindex="-1"
                  href="#"><i class="fa fa-copy fa-fw"></i>Export to clipboard</a></li>
            </ul>
          </div>
          <div class="btn-group btn-clear-div">
            <button id="btn-clear" type="button" class="btn btn-default"><i
                class="fa fa-eraser fa-fw"></i>Clear</button>
          </div>
          <div class="btn-group btn-clear-div">
            <button id="btn-help" type="button" class="btn btn-default"><i class="fa fa-book fa-fw"
              ></i>Help</button>
          </div>
          <span class="open-success">Loaded successfully</span>
          <span class="save-success">Saved successfully</span>
          <div class="btn-group pull-right">
            <button id="btn-run-process" type="button" class="btn btn-success"><i
                class="fa fa-play fa-fw"></i>Run Process</button>
          </div>
        </div>
      </div>
    </div>
    <div id="main-container">
      <div id="palette">
        <div class="palette-scroll" style="display:block;"></div>
        <div id="palette-search" style="display:block;">
          <i class="glyphicon glyphicon-search"></i><input id="palette-search-input" type="text"
            placeholder="filter" /><a href="#" id="palette-search-clear"><i
              class="glyphicon glyphicon-remove"></i></a>
        </div>
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

    <div id="dialog" class="hide"><form id="dialog-form" class="form-horizontal"></form></div>

    <script src="<%=webToplevelPath%>/resources/ol3/ol/ol-debug.js"></script>
    <script src="<%=webToplevelPath%>/resources/jquery/dist/jquery.cus.js"></script>
    <script src="<%=webToplevelPath%>/resources/jquery-ui/jquery-ui.cus.js"></script>
    <script src="<%=webToplevelPath%>/resources/bootstrap/dist/js/bootstap.cus.js"></script>
    <script src="<%=webToplevelPath%>/resources/d3/d3.cus.js"></script>
    <script src="<%=webToplevelPath%>/resources/jsonix/dist/jsonix-all.cus.js"></script>
    <script src="<%=webToplevelPath%>/resources/ol3/ol/highlight/highlight.pack.js"></script>
    <script src="<%=webToplevelPath%>/resources/vkBeautify/vkbeautify.cus.js"></script>
    <script src="<%=webToplevelPath%>/resources/wps-gui/dist/wps-gui_schema.js"></script>
    <script src="<%=webToplevelPath%>/resources/wps-gui/dist/wps-gui_client.js"></script>
    <script src="<%=webToplevelPath%>/resources/wps-gui/dist/wps-gui_ui.js"></script>
    
    <script>
	    var mapWmsServerUrl       = "<%= SystemConstants.MAP_WMS_SERVER_URL %>";
		var mapWmsServerVersion   = "<%= SystemConstants.MAP_WMS_SERVER_VERSION %>";
		var mapWmsServerFormat    = "<%= SystemConstants.MAP_WMS_SERVER_FORMAT %>";
		
		var webMainTitle		= "<%=webMainTitle%>";
		var webToplevelPath 	= "<%=webToplevelPath%>";
		var webProxy			= "<%=webProxy%>";
		var gsUrl				= "<%=gsUrl%>";
		
		var tmsSrcSat			= "<%=tmsSrcSat%>";
		var tmsSrcBase			= "<%=tmsSrcBase%>";

        var url = webProxy + encodeURIComponent('http://127.0.0.1:18080/geoserver/wps');
        var wpsclient = new wps.client({
          servers: {
            'wpsgui': url
          }
        });
        var wpsui;
        wpsclient.getGroupedProcesses('wpsgui', function(groups) {
          wpsui = new wps.ui({
            client: wpsclient,
            defaultServer: 'wpsgui',
            getVectorLayers: true,
            parentContainer: $('.palette-scroll'),
            dropZone: $("#chart")
          });
          // these are the old deprecated categories
          var skip = ['JTS', 'gs', 'gt'];
          var descriptions = {
            'geo': 'Single Geometry',
            'ras': 'Raster',
            'vec': 'Vector Feature Collection'
          };
          for (var group in groups) {
            if (skip.indexOf(group) === -1) {
              var content = wpsui.createProcessCategory(descriptions[group]);
              for (var i=0, ii=groups[group].length; i<ii; ++i) {
                $(content).append(wpsui.createProcess(groups[group][i]));
              }
            }
          }
        });
    </script>
  </body>
</html>