/**
 * 一、获取投影坐标系，建立map对象
 */
var mapViewCenter = [121, 31.3];
var pos = ol.proj.get('EPSG:4326');
var map = new ol.Map({
    //地图容器div的ID
    target: document.getElementById('map'),
    //地图容器中加载的图层
    layers: [],
    view: new ol.View({
        //设置地图投影坐标系
        projection: pos,
        //设置地图加载时的初始中心点121, 31.3
        center: mapViewCenter,
        //缩放级别
        zoom: 11,
        minZoom: 11,
        maxZoom: 20
    }),
});

/**
 * 二、建立瓦片地图层
 */
var mlayer = new ol.layer.Tile({
    source: new ol.source.TileWMS({
        url: 'http://172.16.0.157:8080/geoserver/kunst/wms', // 公司
       // url: 'http://10.1.55.14:8080/geoserver/kunshan/wms', //  服务环境
        params: {
           // 'LAYERS': 'kunshan:kunshan_oms_group_wd', //  服务环境kunshan
           'LAYERS': 'kunst:kunst_oms_group_wd', // 公司
            'FORMAT': 'image/png',
            'SRC': 'EPSG:4326'
        }
    })
});

/**
 * 三、将创建好的瓦片地图层添加进Map中
 */
map.addLayer(mlayer);

var areaLatLng = [{
        lng: '120.951942',
        lat: '31.40863',
        name: '玉山镇',
        source: {},
        feature: {},
        style: {},
        layer: {}
    },
    {
        lng: '121.089119',
        lat: '31.301611',
        name: '花桥镇',
        source: {},
        feature: {},
        style: {},
        layer: {}
    },
    {
        lng: '120.94796',
        lat: '31.287451',
        name: '张浦镇',
        source: {},
        feature: {},
        style: {},
        layer: {}
    },
    {
        lng: '121.030606',
        lat: '31.26285',
        name: '千灯镇',
        source: {},
        feature: {},
        style: {},
        layer: {}
    },
    {
        lng: '121.054574',
        lat: '31.320442',
        name: '陆家镇',
        source: {},
        feature: {},
        style: {},
        layer: {}
    },
    {
        lng: '121.037957',
        lat: '31.188573',
        name: '淀山湖镇',
        source: {},
        feature: {},
        style: {},
        layer: {}
    },
    {
        lng: '120.885567',
        lat: '31.462069',
        name: '巴城镇',
        source: {},
        feature: {},
        style: {},
        layer: {}
    },
    {
        lng: '120.981167',
        lat: '31.462264',
        name: '周市镇',
        source: {},
        feature: {},
        style: {},
        layer: {}
    },
    {
        lng: '120.908194',
        lat: '31.187015',
        name: '锦溪镇',
        source: {},
        feature: {},
        style: {},
        layer: {}
    },
    {
        lng: '120.846266',
        lat: '31.136165',
        name: '周庄镇',
        source: {},
        feature: {},
        style: {},
        layer: {}
    }
];

/**
 * 显示各镇名称
 */
for (var i = 0, len = areaLatLng.length; i < len; i++) {
    var lng = areaLatLng[i].lng;
    var lat = areaLatLng[i].lat;
    var name = areaLatLng[i].name;
    var source = areaLatLng[i].source;
    var feature = areaLatLng[i].feature;
    var style = areaLatLng[i].style;
    var layer = areaLatLng[i].layer;

    // 一、创建空的矢量容器
    source = new ol.source.Vector({});

    // 二、创建Feature，并添加进矢量容器中
    feature = new ol.Feature({
        geometry: new ol.geom.Point([lng, lat]),
        name: "areaName"
    });

    // 三、将图标特性添加进矢量中
    source.addFeature(feature);

    // 四、设置样式
    style = new ol.style.Style({
        text: new ol.style.Text({
            text: name, // 文本内容
            scale: '1.3', // 字体大小
            fill: new ol.style.Fill({ // 字体颜色
                color: '#FFF'
            })
        })
    });

    // 五、创建矢量层
    layer = new ol.layer.Vector({
        source: source,
        style: style
    });

    // 六、将矢量层添加到map中
    map.addLayer(layer);
}

//var IPPATH = 'http://10.9.1.41:18081';  // 服务器
var IPPATH = 'http://localhost:8011';   //公司

var newRes = [];
var currentIndex = 0;
/**
 * 获取元素
 */
var container = document.getElementById("popup"); // 弹框容器层
var content = document.getElementById("popup-content"); // 弹框内容
var popupCloser = document.getElementById("popup-closer"); // 关闭按钮

var popupOverlay = new ol.Overlay({
    element: container
});

/**
 * 获取医院药店数据
 */
$(function() {
    $.ajax({
        url: IPPATH + '/home/page/address/search/all',
        type: 'get',
        dataType: 'text',
        success: function(res) {

            var res = JSON.parse(res)
            console.log(res);
            newRes = res.result;

            for (var item = 0, resLen = newRes.length; item < resLen; item++) {
                newRes[item].source = {};
                newRes[item].feature = {};
                newRes[item].style = {};
                newRes[item].layer = {};

                var hmLng = newRes[item].orgLon;
                var hmLat = newRes[item].orgLat;
                var unitId = newRes[item].unitId;
                var hmSource = newRes[item].source;
                var hmFeature = newRes[item].feature;
                var hmStyle = newRes[item].style;
                var hmLayer = newRes[item].layer;
                var logoType = newRes[item].orgType;

                var iconURL = '';

                // 一、创建空的矢量容器
                hmSource = new ol.source.Vector({});

                // 二、创建Feature，并添加进矢量容器中
                hmFeature = new ol.Feature({
                    geometry: new ol.geom.Point([hmLng, hmLat]),
                    name: "iconName",
                    featureIndex: item
                });

                // 三、将图标特性添加进矢量中
                hmSource.addFeature(hmFeature);

                // 四、设置样式
                if (logoType == 2) {
                    iconURL = '../img/alldisplay/community.png';
                } else {
                    iconURL = '../img/alldisplay/hospital.png';
                }

                hmStyle = new ol.style.Style({
                    image: new ol.style.Icon({
                        opacity: 0.75,
                        src: iconURL,
                        name: 'icon'
                    })
                });

                // 五、创建矢量层
                hmLayer = new ol.layer.Vector({
                    source: hmSource,
                    style: hmStyle
                });

                // 六、将矢量层添加到map中
                map.addLayer(hmLayer);
            }

               map.addOverlay(popupOverlay);


         }
    })
});




function showPopup() {
     if (newRes && newRes.length > 0) {
        if (currentIndex == newRes.length) {
            currentIndex = 0;
        }


         var currentRes = newRes[currentIndex++];
         if(currentRes.orgType==1){
            var unitId = currentRes.unitId
            var arr={
                eventIds: '41001,41002,41003,41004,41005',
                unitId:currentRes.unitId
            }
           $.ajax({
                 type : "post",    //请求类型
                 url : "http://10.9.1.41:18081/home/page/qos/data/get/unit",//请求的 URL地址
                 data:arr,
                 success: function (rawData){
                     var res41001 = rawData.result['41001'];
                     var account41001;
                     for(var i=0;i<res41001.length;i++){
                        if(unitId == res41001[i].unitId){
                            account41001=res41001[i].count
                         }
                     }


                     var res41002 = rawData.result['41002'];
                     var account41002;
                     for(var i=0;i<res41002.length;i++){
                         if(unitId == res41002[i].unitId){
                             account41002=res41002[i].count
                         }
                     }


                     var res41003 = rawData.result['41003'];
                     var account41003;
                     for(var i=0;i<res41003.length;i++){
                         if(unitId == res41003[i].unitId){
                             account41003=res41001[i].count
                         }
                     }


                     var res41004 = rawData.result['41004'];
                     var account41004;
                     for(var i=0;i<res41004.length;i++){
                         if(unitId == res41004[i].unitId){
                             account41004=res41004[i].count
                         }
                     }



                     var res41005 = rawData.result['41005'];
                     var account41005;
                     for(var i=0;i<res41005.length;i++){
                         if(unitId == res41005[i].unitId){
                             account41005=(res41005[i].totalNum/res41005[i].eventNum).toFixed(2)
                         }
                     }


                     $("#appointmentPeople").text(account41001)
                     $("#patientsPeople").text(account41002)
                     $("#inspectorsPeople").text(account41003)
                     $("#newinspectorsPeople").text(account41004)
                     $("#averageHospitalStay").text(account41005)
                  }
              })
            var coordinate = [currentRes.orgLon, currentRes.orgLat];

            content.innerHTML = getFeatrueInfo(currentRes);
            popupOverlay.setPosition(coordinate);
        }

    }
}



setInterval(function() {
    showPopup();
},1000);



/**
 * 动态创建popup的具体内容
 */
function getFeatrueInfo(info) {
    if(info.orgType==1){
        var unitId = info.unitId
        var arr={
            eventIds: '41001,41002,41003,41004,41005',
            unitId:info.unitId
        }
        $.ajax({
            type : "post",    //请求类型
            url : "http://localhost:8011/home/page/qos/data/get/unit",//请求的 URL地址
            data:arr,
            success: function (rawData){
                var res41001 = rawData.result['41001'];
                var account41001;
                for(var i=0;i<res41001.length;i++){
                    if(unitId == res41001[i].unitId){
                        account41001=res41001[i].count
                    }
                }

                var res41002 = rawData.result['41002'];
                var account41002;
                for(var i=0;i<res41002.length;i++){
                    if(unitId == res41002[i].unitId){
                        account41002=res41002[i].count
                    }
                }
                var res41003 = rawData.result['41003'];
                var account41003;
                for(var i=0;i<res41003.length;i++){
                    if(unitId == res41003[i].unitId){
                        account41003=res41001[i].count
                    }
                }
                var res41004 = rawData.result['41004'];
                var account41004;
                for(var i=0;i<res41004.length;i++){
                    if(unitId == res41004[i].unitId){
                        account41004=res41004[i].count
                    }
                }
                var res41005 = rawData.result['41005'];
                var account41005;
                for(var i=0;i<res41005.length;i++){
                    if(unitId == res41005[i].unitId){
                        account41005=(res41005[i].totalNum/res41005[i].eventNum).toFixed(2)
                    }
                }

                $("#appointmentPeople").text(account41001)
                $("#patientsPeople").text(account41002)
                $("#inspectorsPeople").text(account41003)
                $("#newinspectorsPeople").text(account41004)
                $("#averageHospitalStay").text(account41005)
            }
        })

    }
  //  console.log(info.orgType)
     if (info.orgType == 1) {
          return "<div class='info-wrapper'>" +
            "<p>" +
            "<div class='newlh3'><img src='../img/alldisplay/representative.png'/><span class='text-00ffff'>"+info.orgName+"</span></div>"+
            "<div><img src='../img/alldisplay/address.png' class='mapaddress'/><span class='text-00ffff'>"+info.orgAddress+"</span></div>"+
            "<br/>"+
            "<p  class='newlh3'>" + "<span class='fz14 text-ffffff'>预约人次数：</span>" +
            "<span class='fz14 text-ffffff' id='appointmentPeople'></span></p>" +
            "<p class='fz14 text-ffffff newlh3'>" + "<span class='fz14 ffffff'>诊疗人次数：</span>" +
            "<span class='text-ffffff' id='patientsPeople'></span></p>" +
            "<p class='newlh3'>" + "<span class='fz14 text-ffffff'>检查人次数：</span>" +
            "<span class='fz14 text-ffffff' id='inspectorsPeople'></span></p>" +
             "<p class='newlh3'>" + "<span class='fz14 text-ffffff'>检验人次数：</span>" +
             "<span class='fz14 text-ffffff' id='newinspectorsPeople'></span></p>" +
             "<p>" + "<span class='fz14 text-ffffff'>医院平均住院日：</span>" +
             "<span class='fz14 text-ffffff' id='averageHospitalStay'></span></p>" +
            "</div>";
    }
}