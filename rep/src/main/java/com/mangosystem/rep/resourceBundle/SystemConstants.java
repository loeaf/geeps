package com.mangosystem.rep.resourceBundle;

import java.util.Locale;
import java.util.ResourceBundle;

public class SystemConstants {
	
	private static ResourceBundle resource = ResourceBundle.getBundle("system", Locale.getDefault());
	
	
	
	/**
	 * WEB Server
	 */
	public static final String WS_URL 				= resource.getString("WS_URL");;
	public static final String WS_PORT 			= resource.getString("WS_PORT");;
	public static final String WS_PROXY			= resource.getString("WS_PROXY");;
	
	
	/**
	 * GIS Server INFO
	 */
	public static final String  GS_URL 			= resource.getString("GS_URL");
	public static final String  GS_REST_URI 		= resource.getString("GS_REST_URI");
	public static final String  GS_USERNAME 		= resource.getString("GS_USERNAME");
	public static final String  GS_PASSWORD 		= resource.getString("GS_PASSWORD");
	public static final String  GS_WORKSPACE		= resource.getString("GS_WORKSPACE");
	public static final String  GS_STORE_DB		= resource.getString("GS_STORE_DB");
	
	
	/**
	 * JDBC DataStore INFO (Direct Connection)
	 */
	public static final String  DS_DBTYPE 		= resource.getString("DS_JDBC_DBTYPE");
	public static final String  DS_HOST 			= resource.getString("DS_JDBC_HOST");
	public static final int 	DS_PORT 			= Integer.parseInt(resource.getString("DS_JDBC_PORT"));
	public static final String  DS_SCHEMA 		= resource.getString("DS_JDBC_SCHEMA");
	public static final String  DS_DATABASE 		= resource.getString("DS_JDBC_DATABASE");
	public static final String  DS_USER 			= resource.getString("DS_JDBC_USER");
	public static final String  DS_PASSWD 		= resource.getString("DS_JDBC_PASSWD");
	
	/**
	 * WPS SEVER
	 */
	public static final String  MAP_WPS_SERVER_URL 			= resource.getString("map.wps.server.url");
	public static final String  MAP_WPS_SERVER_VERSION 		= resource.getString("map.wps.server.version");
	
	/**
	 * WMS SEVER
	 */
	public static final String  MAP_WMS_SERVER_URL 			= resource.getString("map.wms.server.url");
	public static final String  MAP_WMS_SERVER_VERSION 		= resource.getString("map.wms.server.version");
	public static final String  MAP_WMS_SERVER_FORMAT 		= resource.getString("map.wms.server.format");
	
	/**
	 * WFS SEVER
	 */
	public static final String  MAP_WFS_SERVER_URL 			= resource.getString("map.wfs.server.url");
	public static final String  MAP_WFS_SERVER_VERSION 		= resource.getString("map.wfs.server.version");
	
	/**
	 * WCS SEVER
	 */
	public static final String  MAP_WCS_SERVER_URL 			= resource.getString("map.wcs.server.url");
	public static final String  MAP_WCS_SERVER_VERSION 		= resource.getString("map.wcs.server.version");
	
	/**
	 * TMS MAP SOURCE
	 */
	public static final String  MAP_TMS_SRC_VWORLD_SAT_URL 	= resource.getString("map.tms.source.vworld.satelite.url");
	public static final String  MAP_TMS_SRC_VWORLD_BASE_URL	= resource.getString("map.tms.source.vworld.basemap.url");
}