$(function () {
        generatorBycsChart(bycsChart);
    });
var bycsChart = echarts.init(document.getElementById('emergency'));
function generatorBycsChart(chart) {
        chart.showLoading({
            text: '数据正在努力加载...',
            textStyle: { fontSize : 30 , color: '#444' },
            effectOption: {backgroundColor: 'rgba(0, 0, 0, 0)'}
        });
        let startTime = $("#startTime").val();
        let endTime = $("#endTime").val();
        let eventIds = '13104,13103';
        $.postAjax("/qos/analysis/data/get/month/instalments",{ startTime : startTime, endTime : endTime,'eventIds':eventIds},function (res) {
            let byyValue = [];
            let bytValue = [];
            let time = [];
            let byyValueTotal = 0;
            let bytValueTotal = 0;
            if (res) {
                let month13104 = convertMonthChartData(res, '13104', false);
                let month13103 = convertMonthChartData(res, '13103', false);
                byyValue = month13104.valuesMap['total'];
                bytValue = month13103.valuesMap['total'];
                time = month13104.month;
                byyValueTotal = byyValue.reduce((previousValue, currentValue) => previousValue + currentValue );
                bytValueTotal = bytValue.reduce((previousValue, currentValue) => previousValue + currentValue );
            }
            $("#byt").text(bytValueTotal);
            $("#byy").text(byyValueTotal);
            if (byyValueTotal === 0 && bytValueTotal === 0) {
                showChartInfo(chart,'暂无数据');
                return false;
            }
            chart.hideLoading();
        let    option = {
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
                    top: 'bottom',
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
                        position : 'top',
                        axisLabel:{
                            formatter: function (data) {
                                return (Math.abs(data));
                            },
                           textStyle: {
                                   color: '#ffffff'
                             }
                        }
                    }
                ],
                yAxis : [
                    {
                        type : 'category',
                        axisTick : {show: false},
                        data : time,
                          axisLabel:{
                                                    formatter: function (data) {
                                                        return (Math.abs(data));
                                                    },
                                                   textStyle: {
                                                           color: '#ffffff'
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
        });
    }