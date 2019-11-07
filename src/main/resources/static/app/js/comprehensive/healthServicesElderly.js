$(function(){
    var arr={
        eventIds:'V30007'
    }
    $.ajax({
        type : "post",    //请求类型
        url : URLPATH+"/home/page/qos/get/total/data",//请求的 URL地址
        async: false,
        data:arr,
        success: function (res){

            var name  = res.result;
            var nameArray=[]
            for(var i in name){
                nameArray.push(name[i])
            }
            var rawData = res;
            var rawData = eval(rawData.message);
            var lv=[]
            for(var i=0;i<rawData.length;i++){
                lv.push(((rawData[i].GUIDENUMBER/rawData[i].TOTAL)*100).toFixed(2))
            }

            var healthServicesElderlyOption = {
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
                    data: nameArray,
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
                    name: '老年人中医药健康服务覆盖率',
                    data: lv,
                    type: 'bar'
                }]
            };

            //初始化echarts实例
            var healthServicesElderlyID = echarts.init(document.getElementById('healthServicesElderly'));
            healthServicesElderlyID.setOption(healthServicesElderlyOption);
            var index = 0; //播放所在下标
            var mTime = setInterval(function() {
                healthServicesElderlyID.dispatchAction({
                    type: 'showTip',
                    seriesIndex: 0,
                    dataIndex: index
                });
                index++;
                if(index > nameArray.length) {
                    index = 0;
                }
            }, 5000)
            window.addEventListener("resize", function () {
                healthServicesElderlyID.resize();
            });
        }
    })
})
