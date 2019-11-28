var emergencyPatientsID = echarts.init(document.getElementById('emergencyPatients'));
$(function(){

    var indexLable=[];
    $.ajax({
        type: "post",    //请求类型
        url: URLPATH+"/home/page/qos/data/get/month/twoYear",//请求的 URL地址
        success: function (data) {
            var result = data.result;
            var lastDate = new Date();
            lastDate.setFullYear(lastDate.getFullYear() - 1);
            var lastMonthAttr = monthAndDate(lastDate);
            var thisDate = new Date();
            var thisMonthAttr = monthAndDate(thisDate);
            indexLable = monthDate(thisDate);
            var xDate = [];
            for (var i = 0; i < thisMonthAttr.length; i++) {
                xDate.push(thisMonthAttr[i].slice(5)+'月份');
            }

            var thisYearData = [];
            var lastYearData = [];

            for (var i = 0; i < thisMonthAttr.length; i++) {
                for (var j = 0; j < result.length; j++) {
                    if (thisMonthAttr[i] == result[j].month) {
                        thisYearData.push(result[j].count)
                    }
                }
            }

            for (var i = 0; i < lastMonthAttr.length; i++) {
                for (var j = 0; j < result.length; j++) {
                    if (lastMonthAttr[i] == result[j].month) {
                        lastYearData.push(result[j].count)
                    }
                }
            }
            var emergencyPatientsOption = {
                color: ['#7a7efc', '#21ebdd'],
                tooltip: {
                    show: true,
                    trigger: 'axis',
                    axisPointer: {
                        lineStyle: {
                            color: 'rgba(0,0,0,0)'
                        }
                    },
                    backgroundColor: "rgba(255,255,255,0.8)",
                    formatter: function (e) {
                        let html = "";
                        e.forEach(e => {
                            html += e.name + "月：" + e.value + "人<br/>";
                        })
                        return html;
                    },
                    textStyle: {
                        color: "#333"
                    }
                },
                legend: {
                    data:['去年','今年'],
                    right: '4%',
                    textStyle: {
                        fontSize: 12,
                        color: '#fff'
                    }
                },
                grid: {
                    top: 35,
                    left: '10%',
                    right: '3%',
                    bottom: 35,
                },
                xAxis: [{
                    type: 'category',
                    boundaryGap: false,
                    axisLabel: {
                        margin: 10,
                        align: "center",
                        fontSize: 9,
                        rotate: 35,
                    },
                    axisTick: {
                        show: false
                    },
                    splitLine: {
                        show: true,
                        lineStyle: {
                            color: '#0a344c'
                        }
                    },
                    axisLine: {
                        show: false,
                        lineStyle: {
                            color: '#cbd1d5'
                        }
                    },
                    data: xDate
                }],
                yAxis: [{
                    type: 'value',
                    axisTick: {
                        show: false
                    },
                    axisLine: {
                        show: false,
                        lineStyle: {
                            color: '#cbd1d5'
                        }
                    },
                    axisLabel: {
                        fontSize: 9
                    },
                    splitLine: {
                        lineStyle: {
                            color: '#0a344c'
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
                    data: lastYearData
                }, {
                    name: '今年',
                    type: 'line',
                    smooth: true,
                    showSymbol: false,
                    lineStyle: {
                        normal: {
                            width: 3
                        }
                    },
                    data: thisYearData
                }]
            };
            emergencyPatientsID.setOption(emergencyPatientsOption);
        }
    });

    var index = 0; //播放所在下标
    var mTime = setInterval(function () {
        emergencyPatientsID.dispatchAction({
            type: 'showTip',
            seriesIndex: 0,
            dataIndex: index
        });
        index++;
        if (index > indexLable.length) {
            index = 0;
        }
    }, 5000)
    window.addEventListener("resize", function () {
        emergencyPatientsID.resize();
    });



})

function monthAndDate(data){
    var dataArr = [];
    var year = data.getFullYear();
    data.setMonth(data.getMonth()+1, 1)//获取到当前月份,设置月份
    for (var i = 0; i < 13; i++) {
        data.setMonth(data.getMonth() - 1);//每次循环一次 月份值减1
        var m = data.getMonth() + 1;
        m = m < 10 ? "0" + m : m;
        dataArr.push(data.getFullYear() + "-" + (m))
    }

    return dataArr.sort().slice(0,12);
}