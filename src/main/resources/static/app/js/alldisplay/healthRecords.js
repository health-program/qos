var healthRecordsID = echarts.init(document.getElementById('healthRecords'));
$(function(){
    var indexLable=[];
    $.ajax({
        type: "post",    //请求类型
        url:URLPATH+"/data/display/get/month/archives/rate",//请求的 URL地址
        success: function (data) {
            var result=data.result;
            var xData=[];
            var value=[];
            for (var i=0;i<result.length;i++){
                xData.push(result[i].month);
                indexLable.push(result[i].month);
                value.push(result[i].rate.toFixed(5)*100);
            }
            var healthRecords = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    },
                    formatter: function (e) {
                        let html = "";
                        e.forEach(e => {
                            html += e.name + "：" + e.value.toFixed(2) + "%";
                        })
                        return html;
                    },
                },
                grid: {
                    top: '25%',
                    right: '1%',
                    left: '7%',
                    bottom: '20%'
                },
                xAxis: [{
                    type: 'category',
                    data: xData,
                    axisLine: {
                        lineStyle: {
                            color: 'rgba(255,255,255,0.12)'
                        }
                    },
                    axisLabel: {
                        color: '#e2e9ff',
                        textStyle: {
                            fontSize: 12
                        },
                        interval: 0,
                        rotate: 40
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
                    data: value,
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
                            // barBorderRadius: [30, 30, 30, 30],
                            shadowColor: 'rgba(0,160,221,1)',
                            shadowBlur: 4,
                        }
                    }
                }]
            };



            healthRecordsID.setOption(healthRecords);
        }
    })

    var index = 0; //播放所在下标
    var mTime = setInterval(function() {
        healthRecordsID.dispatchAction({
            type: 'showTip',
            seriesIndex: 0,
            dataIndex: index
        });
        index++;
        if(index > indexLable.length) {
            index = 0;
        }
    }, 5000)
     window.addEventListener("resize", function () {
        healthRecordsID.resize();
    });
})