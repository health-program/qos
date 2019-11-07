$(function(){
    var data21003Length;
    var arr = {
        eventIds:'21003'
    }
   $.ajax({
        type : "post",    //请求类型
        url : URLPATH+"/home/page/qos/data/get/month/instalments",//请求的 URL地址
        data:arr,
        success: function (rawData){
            var  month21003Data=convertMonthChartData(rawData.result, '21003', true); //
            data21003Length = month21003Data.month
            var comprehensiveHealthOption = {
                color: ['#8EF66A'],
                tooltip: {
                    show: true,
                    trigger: 'axis',
                    axisPointer: {
                        lineStyle: {
                            color: 'rgba(0,0,0,0)'
                        }
                    },
                    backgroundColor: "rgba(255,255,255,0.8)",
                    formatter: function (params) {
                        return params[0].name + '<br/>'
                            + params[0].data + '%<br/>'
                    },
                    textStyle: {
                        color: "#333"
                    }
                },
                grid: {
                    top: 35,
                    left: 30,
                    right: '3%',
                    bottom: 35,
                },
                xAxis: [{
                    type: 'category',
                    boundaryGap: false,
                    axisLabel: {
                        show: true,
                        fontSize: 9,
                        rotate: 30,
                    },
                    axisTick: {
                        show: false
                    },
                    splitLine: {
                        show: false,
                        lineStyle: {
                            color: '#65C6E7'
                        }
                    },
                    axisLine: {
                        show: false,
                        lineStyle: {
                            color: '#50a2c1'
                        }
                    },
                    data:month21003Data.month,
                }],
                yAxis: [{
                    type: 'value',
                    axisTick: {
                        show: false
                    },
                    axisLine: {
                        show: false,
                        lineStyle: {
                            color: '#65c6e7'
                        }
                    },
                    axisLabel: {
                        fontSize: 9
                    },
                    splitLine: {
                        lineStyle: {
                            color: '#154c77'
                        }
                    }
                }],
                series: [{
                    name: '去年',
                    type: 'line',
                    smooth: true,
                    showSymbol: false,
                    lineStyle: {
                        normal: {
                            width: 3
                        }
                    },
                    data: month21003Data.valuesMap['total'],
                }]
            };
            var comprehensiveHealthID = echarts.init(document.getElementById('comprehensiveHealth'));
            comprehensiveHealthID.setOption(comprehensiveHealthOption);
            var index = 0; //播放所在下标
            var mTime = setInterval(function() {
                comprehensiveHealthID.dispatchAction({
                    type: 'showTip',
                    seriesIndex: 0,
                    dataIndex: index
                });
                index++;
                if(index > data21003Length.length) {
                    index = 0;
                }
            }, 5000)
            window.addEventListener("resize", function () {
                comprehensiveHealthID.resize();
            });
        }
  })
})