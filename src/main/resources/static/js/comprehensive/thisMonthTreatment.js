$(function(){
    function getRateNum(item, fixed) {
        var a = item.eventNum,
            b = item.totalNum,
            c = 0;
        if (a && b) {
            c = a / b * 100;
        }
        return c.toFixed(fixed || 2);
    }

    function convertUnitChartData(data, eventId, isRate) {
        var edata = data[eventId],
            max = 0,
            unit = [],
            values = [];
        edata && edata.forEach(function(item) {
            var r = isRate ? getRateNum(item) : item.count;
            max = Math.max(r, max);
            values.push(r);
            unit.push(item.unitName);
        });
        return {
            max: max,
            unit: unit,
            values: values
        }
    }

    var unitNameArray=[];
    var teamNumArray=[];
       var arr = {
               eventIds:'13003'
           }
      $.ajax({
       type : "post",    //请求类型
        url : "/qos/analysis/data/get/unit",//请求的 URL地址
        data:arr,
         success: function (rawData) {
            rawData = rawData.result;  //  unitName
               var dataMap=convertUnitChartData(rawData, '13003', false); //
            var emergencyOption = {
                   tooltip: {
                                 trigger: 'axis',
                                 axisPointer: {
                                     type: 'shadow'
                                 }
                             },
                     xAxis: {
                         type: 'category',
                            axisLabel: {
                                                         show: true,
                                                         textStyle: {
                                                             color: '#19d1ff',
                                                        },
                                                          interval:0,
                                                          rotate:30,
                                                          formatter: function(value) {
                                                               var reg = new RegExp('社区卫生服务中心'                                    , "g");
                                                           	return value.replace(reg, '');
                                                        }
                                                     },

                           grid:{
                                 left:'10%',
                                 bottom:'35%'

                             },
                         data:dataMap['unit'],
                         axisLine: {
                             lineStyle: {
                                 color: '#19d1ff',
                                 width: 1, //这里是为了突出显示加上的
                             }
                         },

                          grid: {
                                         left: 45,
                                         right: 20,
                                         bottom: 10,
                                         containLabel: true
                                     },
                          axisLabel: {
                                    show: true,
                                    textStyle: {
                                         color: '#19d1ff',
                                    },
                                    interval:0,
                                    rotate:30,
                                    formatter: function(value) {
                                         var reg = new RegExp('社区卫生服务中心', "g");
                                     	return value.replace(reg, '');
                                      }
                          },
                     },
                   yAxis: {
                         type: 'value',
                         splitLine: {
                             show: false
                         },
                         axisLine: {
                             lineStyle: {
                                 color: '#19d1ff',
                                 width: 1, //这里是为了突出显示加上的
                             }
                         }
                     },
                     series: [{
                         itemStyle: {
                             normal: {
                                 // 随机显示
                                 color: function(d) { return "#" + '439AFF'; }

                             },
                         },
                         grid:{
                             left:'10%',

                         bottom:'85%'

                         },
                         barWidth: 15,
                         data:dataMap['values'],
                         type: 'bar'
                     }]
                 };
                 //初始化echarts实例
              var emergencyID = echarts.init(document.getElementById('emergency'));
               emergencyID.setOption(emergencyOption);
                   //急诊人数结束
         }
    });











})