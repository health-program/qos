var dataMap={}
var result=[];
var index = 0; //播放所在下标
var mTime=null;
$(function(){

    var arr = {
        eventIds:'V20001',
    }
    var name;
    var nameArray=[];
    var rawData;

    var totalData = [];
    var values = [];
    $.ajax({
        type : "post",    //请求类型
        url : URLPATH+"/home/page/qos/get/total/data",//请求的 URL地址
        data:arr,
        success: function (res){
            name  = res.result;
            nameArray=Object.keys(name);

            rawData = res;
            rawData = eval(rawData.message);

            dataMap['CJRS']=convertUnitChartData(rawData, 'CJRS')
            dataMap['GXYS']=convertUnitChartData(rawData, 'GXYS')
            dataMap['TNBS']=convertUnitChartData(rawData, 'TNBS')
            dataMap['YCFS']=convertUnitChartData(rawData, 'YCFS')
            dataMap['ETQYS']=convertUnitChartData(rawData, 'ETQYS')
            dataMap['LNRS']=convertUnitChartData(rawData, 'LNRS')
            dataMap['FJHS']=convertUnitChartData(rawData, 'FJHS')
            dataMap['JSZAS']=convertUnitChartData(rawData, 'JSZAS')
            dataMap['JBSCS']=convertUnitChartData(rawData, 'JBSCS')
            echartses()
        }
    })
    function convertUnitChartData(data, eventId) {
        getEventData(data,eventId);
        var unit=[];
        var value=[];
        for(var i=0;i<nameArray.length;i++){
            for(var j=0;j<totalData.length;j++){
                if (nameArray[i]==totalData[j].name){
                    var unitName=name[nameArray[i]];
                    unitName=unitName.substring(0,unitName.length-8);
                    unit.push(unitName);
                    var rate;
                    if (totalData[j].total==0){
                        rate=0;
                    }else{
                        rate=totalData[j].value*100/ totalData[j].total;
                    }

                    value.push(rate);
                }
            }
        }
        return {
            unit:unit,
            value:value
        }
    }

    function getEventData(data,eventId) {
        if (totalData.length>0){
            totalData.splice(0,totalData.length);
        }
        for (var i=0;i<data.length;i++){

            var eventData;
            switch(eventId)
            {
                case "CJRS":
                    eventData=data[i].CJRS;
                    break;
                case "GXYS":
                    eventData=data[i].GXYS;
                    break;
                case "TNBS":
                    eventData=data[i].TNBS;
                    break;
                case "YCFS":
                    eventData=data[i].YCFS;
                    break;
                case "ETQYS":
                    eventData=data[i].ETQYS;
                    break;
                case "LNRS":
                    eventData=data[i].LNRS;
                    break;
                case "FJHS":
                    eventData=data[i].FJHS;
                    break;
                case "JSZAS":
                    eventData=data[i].JSZAS;
                    break;
                case "JBSCS":
                    eventData=data[i].JBSCS;
                    break;
                default:;
            }

            totalData.push({name:data[i].MANAGEDCENTERCODE,total:data[i].TOTAL,value:eventData});
        }
    }

    $("#selection").change(function(){
        index=0;
        clearInterval(mTime);
        mTime=null;
        echartses()
    })



    var echartses=function(id){
        var eventId = $("#selection").val();
        var data = dataMap[eventId];
        //急诊人数开始
        //指定图标的配置和数据
        var keyGroupOption =  {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                },formatter: function (params) {
                    return params[0].name + '社区卫生服务中心<br/>'
                        + params[0].data.toFixed(2) + '%<br/>'
                }
            },
            grid: {
                top: '25%',
                right: '3%',
                left: '7%',
                bottom: '14%'
            },
            xAxis: [{
                type: 'category',
                data: data.unit,
                axisLine: {
                    lineStyle: {
                        color: 'rgba(255,255,255,0.12)'
                    }
                },
                axisLabel: {
                    color: '#e2e9ff',
                    textStyle: {
                        fontSize: 8
                    },
                    interval:0,
                    rotate:40
                },
            }],
            yAxis: [{
                axisLabel: {
                    formatter: '{value}',
                    color: '#e2e9ff',
                },
                axisLine: {
                    show: false
                },
                splitLine: {
                    lineStyle: {
                        color: 'rgba(255,255,255,0.12)'
                    }
                }
            }],
            series: [{
                type: 'bar',
                data: data.value,
                barWidth: '10px',
                itemStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(0,244,255,1)' // 0% 处的颜色
                        }, {
                            offset: 1,
                            color: 'rgba(0,77,167,1)' // 100% 处的颜色
                        }], false),
                        barBorderRadius: [30, 30, 30, 30],
                        shadowColor: 'rgba(0,160,221,1)',
                        shadowBlur: 4,
                    }
                }
            }]
        };
        var keyGroupsID= echarts.init(document.getElementById('keyGroups'));
        keyGroupsID.setOption(keyGroupOption);

         mTime = setInterval(function() {
            keyGroupsID.dispatchAction({
                type: 'showTip',
                seriesIndex: 0,
                dataIndex: index
            });
            index++;
            if(index > data.unit.length) {
                index = 0;
            }
        }, 5000)
        window.addEventListener("resize", function () {
            keyGroupsID.resize();
        });
    }
})