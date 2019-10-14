var dataMap = {}
//  住院人次数
$(function() {

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

    var unitNameName = []
    var probabilityArray = []
    $.ajax({
        type: "post",
        //请求类型
        url: "/qos/gongwei/archives/search/all",
        //请求的 URL地址
        success: function(rawData) {
            rawData = rawData.result;
            for (var i = 0; i < rawData.length; i++) {
                unitNameName.push(rawData[i].unitName);
                probabilityArray.push(rawData[i].activeArchivesNumber / rawData[i].peopleNumber);
            }
             var permanentPopulationOption = {
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
                    data: unitNameName,

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
                dataZoom: {
                    realtime: true,
                    //拖动滚动条时是否动态的更新图表数据
                    height: 25,
                    //滚动条高度
                    start: 40,
                    //滚动条开始位置（共100等份）
                    end: 100 //结束位置（共100等份）
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