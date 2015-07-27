var attribution = new ol.Attribution(
		{
			html : '<a target="_blank" href="http://mangosystem.com" '
					+ 'style="float: left; width: 61px; height: 17px; cursor: pointer; background-image: url(http://localhost:8080/wps/images/mangosystem.png); background-repeat: no-repeat no-repeat; " '
					+ 'title="MangoSystem"></a>' + 'ⓒ 2015 MangoSystem'
		});

var olLayers = [
		new ol.layer.Group(
				{
					'title' : 'Base maps',
					layers : [
							new ol.layer.Tile(
									{
										title : 'Open Street Map',
										type : 'base',
										source : new ol.source.OSM(
												{
													attributions : [
															new ol.Attribution(
																	{
																		html : 'Tiles &copy; <a href="http://www.opencyclemap.org/">OpenCycleMap</a>'
																	}),
															ol.source.OSM.DATA_ATTRIBUTION ]
												})
									}),
							new ol.layer.Tile(
									{
										title : 'VWorld Satellite Map',
										visible : false,
										type : 'base',
										source : new ol.source.XYZ(
												{
													attribuions : 'Data by <a href="http://map.vworld.kr/">VWORLD MAP',
													url : 'http://xdworld.vworld.kr:8080/2d/Satellite/201301/{z}/{x}/{y}.jpeg'
												})
									}),
							new ol.layer.Tile(
									{
										title : 'VWorld Base Map',
										visible : false,
										type : 'base',
										source : new ol.source.XYZ(
												{
													attribuions : 'Data by <a href="http://map.vworld.kr/">VWORLD MAP',
													url : 'http://xdworld.vworld.kr:8080/2d/Base/201411/{z}/{x}/{y}.png'
												})
									})

					]
				}), new ol.layer.Group({
			'title' : '분석 지도',
			layers : [ new ol.layer.Tile({
				title : 'cite:sid',
				source : new ol.source.TileWMS({
					url : "http://127.0.0.1:8080/geoserver/wms",
					params : {
						'TILED' : 'true',
						'VERSION' : '1.1.0',
						'LAYERS' : 'cite:sid',
						'FORMAT' : 'image/png'
					}
				})
			})

			]
		}), new ol.layer.Vector({
			source : new ol.source.Vector(),
			style : new ol.style.Style({
				fill : new ol.style.Fill({
					color : 'rgba(255, 255, 255, 0.2)'
				}),
				stroke : new ol.style.Stroke({
					color : '#ffcc33',
					width : 2
				}),
				image : new ol.style.Circle({
					radius : 7,
					fill : new ol.style.Fill({
						color : '#ffcc33'
					})
				})
			})
		}), new ol.layer.Vector({
			source : new ol.source.Vector(),
			style : new ol.style.Style({
				image : new ol.style.Icon(/** @type {olx.style.IconOptions} */
				({
					anchor : [ 0.5, 5 ],
					anchorXUnits : 'fraction',
					anchorYUnits : 'pixels',
					opacity : 0.75,
					src : './img/control_stop_square.png'
				}))
			})
		}) ];

var repMap = new ol.Map({
	target : 'repMap',
	controls : [ new ol.control.Zoom(), new ol.control.ZoomSlider(),
			new ol.control.FullScreen(),
			new ol.control.LayerSwitcher() ],
	interactions : ol.interaction.defaults({
		shiftDragZoom : true
	}),
	layers : olLayers,
	view : new ol.View({
		center : initCenter,
		zoom : initZoom
	})
});

function clickIsOptional() {
	if (mandatoryOption == "true") {
		mandatoryOption = "false";
		$('#img-isOptional').attr("src",
				$('#img-isOptional').attr("src").replace("opa", "op"));
	} else if (mandatoryOption == "false") {
		mandatoryOption = "true";
		$('#img-isOptional').attr("src",
				$('#img-isOptional').attr("src").replace("op", "opa"));
	}
}