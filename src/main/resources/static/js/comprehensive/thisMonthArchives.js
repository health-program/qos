
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

    //指定图标的配置和数据
    // 21001 综合健康管理服务包签约率（收费）
       var arr = {
           eventIds:'22001'
       }

    var unitName = []
    var probabilityArray = []
      $.ajax({
       type : "post",    //请求类型
        url : "/qos/gongwei/archives/search/archives/month",//请求的 URL地址
        data:arr,
        success: function (rawData) {
            rawData = rawData.result;
            for (var i = 0; i < rawData.length; i++) {
                unitName.push(rawData[i].unitName);
                probabilityArray.push(rawData[i].activeArchivesNumber);
            }

            var permanentPopulationOption = {
                tooltip: {
                    trigger: 'axis',
                    formatter:function (params) {
                        var result = '';
                        params.forEach(function (item) {
                            result += item.name+"<br>" + item.marker + item.value+"人" ;
                        });
                        return result;
                    },
                    // formatter: "{b}<br/>  {c}(人) ",
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
                        interval: 0,
                        rotate: 30,
                        formatter: function(value) {
                            var reg = new RegExp('社区卫生服务中心' , "g");
                            return value.replace(reg, '');
                        }
                    },

                    grid: {

                        left: '10%',

                        bottom: '35%'

                    },
                    data: unitName,

                    axisLine: {
                        lineStyle: {
                            color: '#19d1ff',
                            width: 1,
                            //这里是为了突出显示加上的
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
                        interval: 0,
                        rotate: 30,
                        formatter: function(value) {
                            var reg = new RegExp('社区卫生服务中心'                                    , "g");
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
                            width: 1,
                            //这里是为了突出显示加上的
                        }
                    }
                },
                series: [{
                    itemStyle: {
                        normal: {
                            // 随机显示
                            color: function(d) {
                                return "#" + '439AFF';
                            }

                        },
                    },
                    grid: {

                        left: '10%',

                        bottom: '85%'

                    },
                    barWidth: 15,
                    data: probabilityArray,
                    type: 'bar'
                }]
            };

            var permanentPopulationID = echarts.init(document.getElementById('chartmain'));
            permanentPopulationID.setOption(permanentPopulationOption);
         }
    });











})