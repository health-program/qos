$(function(){
    var xLabel=[];
    var yData=[];
    var arr={
        eventIds:'V30008'
    }
    $.ajax({
        type : "post",    //请求类型
        url : URLPATH+"/data/display/search/all",//请求的 URL地址
        async: false,
        data:arr,
        success: function (res){
            var name  = res.result.V30008;

            for(var i=0;i<name.length;i++){
                xLabel.push(name[i].unitName);
                yData.push((name[i].managenumber*100/name[i].total).toFixed(2))
            }

            var healthRateOption = {
                tooltip: {
                    trigger: 'axis',
                    formatter: function(params, ticket, callback) {
                        var html = params[0].axisValueLabel + '：';
                        for (var i = 0; i < params.length; i++) {
                            var param = params[i];
                            html += '<br/>' + param.marker + param.seriesName + '：' + param.value + '%';
                        }
                        return html;
                    },
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                grid: {
                    top: '5%',
                    right: '8%',
                    left: '10%',
                    bottom: '22%'
                },
                xAxis: {
                    type: 'category',
                    data: xLabel,
                    axisLabel: {
                        interval: 0,
                        rotate: 20, //角度顺时针计算的
                        formatter: function(value) {
                            var reg = new RegExp('社区卫生服务中心', "g");
                            return value.replace(reg, '');
                        },
                        textStyle: {
                            color: '#65C6E7'
                        }
                    },
                    axisLine:{
                        lineStyle:{
                            color:'#19d1ff',
                            width:1,
                        }
                    }
                },
                yAxis: {
                    type: 'value',
                    splitLine: {
                        show: true,
                        lineStyle: {
                            type: 'solid',
                            color:'#113d5e'
                        }
                    },
                    axisLabel: {
                        formatter: function(val) {
                            return val + '%';
                        },
                        textStyle: {
                            color: '#65C6E7'
                        }
                    },
                    axisLine:{
                        show:false
                    },
                    axisTick:{
                        show:false
                    },
                },
                series: [{
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: '#f8ba7f' // 0% 处的颜色
                            }, {
                                offset: 1,
                                color: '#fe740c' // 100% 处的颜色
                            }], false),
                            barBorderRadius: [30, 30, 30, 30],
                            shadowColor: 'rgba(0,160,221,1)',
                            shadowBlur: 4,
                        }
                    },
                    barWidth : 10,
                    name: '老年人健康管理率',
                    data: yData,
                    type: 'bar'
                }]
            };

            //初始化echarts实例
            var healthRateID = echarts.init(document.getElementById('healthRate'));
            healthRateID.setOption(healthRateOption);
            var index = 0; //播放所在下标
            var mTime = setInterval(function() {
                healthRateID.dispatchAction({
                    type: 'showTip',
                    seriesIndex: 0,
                    dataIndex: index
                });
                index++;
                if(index > xLabel.length) {
                    index = 0;
                }
            }, 5000)
            window.addEventListener("resize", function () {
                healthRateID.resize();
            });
        }
    })
})
