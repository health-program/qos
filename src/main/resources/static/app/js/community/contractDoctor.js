$(function(){

    var data = ['工作', '操作', '抢修', '用电', '设备', '现场', '到岗']
    var arr = {
        eventIds:'21004'
    }
    var month21004DataLen;
    $.ajax({
        type : "post",    //请求类型
        url : URLPATH+"/home/page/qos/data/get/month/instalments",//请求的 URL地址
        data:arr,
        success: function (rawData){
            var  month21004Data=convertMonthChartData(rawData.result, '21004', true); //
            month21004DataLen=month21004Data.month
            var contractDoctorOption = {
                color: ['#ff3a00'],
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
                        margin: 20,
                        align: "center",
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
                    data: month21004Data.month,
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
                    data:month21004Data.valuesMap['total'],
                }]
            };
            var contractDoctorID = echarts.init(document.getElementById('contractDoctor'));
            contractDoctorID.setOption(contractDoctorOption);
            var index = 0; //播放所在下标
            var mTime = setInterval(function() {
                contractDoctorID.dispatchAction({
                    type: 'showTip',
                    seriesIndex: 0,
                    dataIndex: index
                });
                index++;
                if(index > data.length) {
                    index = 0;
                }
            }, 5000)
            window.addEventListener("resize", function () {
                contractDoctorID.resize();
            });
        }
    })

})