$(function () {
    generatorBycsChart(bycsChart);
});
var bycsChart = echarts.init(document.getElementById('contraceptionEcharts'));

let LEN;
function generatorBycsChart(chart) {
    let eventIds = '13104,13103';

    $.ajax({
        type : "post",    //请求类型
        url : URLPATH+"/home/page/qos/data/get/month/instalments",//请求的 URL地址
        data:{
            'eventIds':eventIds
        },
        success: function (res) {
            var res = res.result;
            let byyValue = [];
            let bytValue = [];
            let time = [];
            let byyValueTotal = 0;
            let bytValueTotal = 0;
            if (res) {
                let month13104 = convertMonthChartData(res, '13104', false);
                let month13103 = convertMonthChartData(res, '13103', false);
                  LEN = month13103.month
                byyValue = month13104.valuesMap['total'];
                bytValue = month13103.valuesMap['total'];
                time = month13104.month;
                byyValueTotal = byyValue.reduce((previousValue, currentValue) => previousValue + currentValue );
                bytValueTotal = bytValue.reduce((previousValue, currentValue) => previousValue + currentValue );
            }
            if (byyValueTotal === 0 && bytValueTotal === 0) {
                showChartInfo(chart,'暂无数据');
                return false;
            }
            chart.hideLoading();
            let  option = {
                color: ['#3398DB','#6087BF'],
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    },
                    formatter: function (params) {
                        return params[0].name + '月&nbsp;发放数量: <br/>'
                            + params[0].marker + params[0].seriesName + ':'+params[0].data+'粒<br/>'
                            + params[1].marker + params[1].seriesName + ':'+ Math.abs(params[1].data)+'个<br/>'
                    }
                },

                legend: {
                    top: 'top',
                    data:[ '避孕套','避孕药'],
                    textStyle: {
                        color: '#ffffff'
                    }
                },
                grid: {
                    left: '2%',
                    right: '2%',
                    top: '10%',
                    containLabel: true
                },
                xAxis : [
                    {
                        type : 'value',
                        position : 'bottom',
                        axisLine: {
                            lineStyle: {
                                type: 'solid',
                                color: '#00CEFF',//左边线的颜色
                                width:'1'//坐标线的宽度
                            }
                        },
                        axisLabel:{
                            formatter: function (data) {
                                return (Math.abs(data));
                            },
                            textStyle: {
                                color: '#00e4ff'
                            }
                        }
                    }
                ],
                yAxis : [
                    {

                        data : time,
                        axisLine: {
                            show:false
                        },
                        axisLabel:{
                            textStyle: {
                                color: '#00CEFF'
                            }
                        }
                    }
                ],
                series : [
                    {
                        name:'避孕药',
                        type:'bar',
                        barWidth: '90%',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true
                            }
                        },
                        data:byyValue
                    },
                    {
                        name:'避孕套',
                        type:'bar',
                        barWidth: '90%',
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                formatter:function(v){return Math.abs(v.data)}
                            }
                        },
                        data:bytValue.map(value => ~value+1)
                    }
                ]
            };
            chart.setOption(option,true);
        }
    })


    var index = 0; //播放所在下标
    var mTime = setInterval(function() {
        bycsChart.dispatchAction({
            type: 'showTip',
            seriesIndex: 0,
            dataIndex: index
        });
        index++;
        if(index > LEN.length) {
            index = 0;
        }
    }, 5000)
    window.addEventListener("resize", function () {
        bycsChart.resize();
    });

}