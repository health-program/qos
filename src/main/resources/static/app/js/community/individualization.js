$(function(){

    var dataLength=[];

    function sortKey(array, key) {
        return array.sort(function(a, b) {
            var x = a[key];
            var y = b[key];
            return x < y ? -1 : x > y ? 1 : 0;
        });
    }
    var arr = {
        eventIds:'V50000'
    }

    $.ajax({
        type : "post",    //请求类型
        url : URLPATH+"/home/page/qos/get/total/data",//请求的 URL地址
        data:arr,
        success: function (rawData) {
            var result=eval(rawData.message);
            result=sortKey(result,"MONTH");
            var xLabel=[];
            var yData=[];
            for(var i=0;i<result.length;i++){
                var date = new Date();
                var year = date.getFullYear();
                if (year==result[i].MONTH.substring(0,4)){
                    xLabel.push(result[i].MONTH);
                    dataLength.push(result[i].MONTH);
                    yData.push((result[i].GXHQYS/result[i].TOTAL*100).toFixed(2))
                }
            }
            var individualizationOption = {
                color: ['#FFF100'],
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
                    data: xLabel
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
                    data: yData
                }]
            };
            var individualizationID = echarts.init(document.getElementById('individualization'));
            individualizationID.setOption(individualizationOption);
            var index = 0; //播放所在下标
            var mTime = setInterval(function() {
                individualizationID.dispatchAction({
                    type: 'showTip',
                    seriesIndex: 0,
                    dataIndex: index
                });
                index++;
                if(index > dataLength.length) {
                    index = 0;
                }
            }, 1000)
            window.addEventListener("resize", function () {
                individualizationID.resize();
            });
         }
    })

})