 var dataMap={}
//  住院人次数
$(function(){

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

    //指定图标的配置和数据
    // 21001 综合健康管理服务包签约率（收费）
       var arr = {
           eventIds:'14001,14002',
           ignoreUnitIds:'320583810343'
       }


      $.ajax({
       type : "post",    //请求类型
        url : "/qos/analysis/data/get/unit",//请求的 URL地址
        data:arr,
        success: function (rawData) {
           var dataMap14001=convertUnitChartData(rawData.result, '14001', false); //  true 比率  false总数
           var dataMap14002=convertUnitChartData(rawData.result, '14002', false); //   true 比率  false总数
         var people = {
         tooltip: {
             show: true ,//显示提示框
             // formatter:function (params) {
             //     debugger;
             //     var result = '';
             //     params.forEach(function (item) {
             //         result += item.name+"<br>" + item.marker + item.value+"人" ;
             //     });
             //     return result;
             // },
                    },
        xAxis: [{
            type: 'category',
            data:dataMap14001['unit'],
            axisLine: {
                lineStyle: {
                    color: '#19d1ff',
                    width: 1, //这里是为了突出显示加上的
                }
            },
            splitLine: {
                show: false
            },

            axisLabel: {
                   show: true,
                   textStyle: {
                       color: '#19d1ff',
                   },
                   interval:0,
                   rotate:45,
                   formatter: function(value) {
                       var reg = new RegExp('社区卫生服务中心', "g");
                       return value.replace(reg, '');
                    }
            }
        }],
        yAxis: [{
                type: 'value',
                axisLine: {
                    lineStyle: {
                        color: '#19d1ff',
                        width: 1, //这里是为了突出显示加上的
                    }
                },
                splitLine: {
                    show: false
                }
            }


        ],
        series: [{
                name: '住院(人)',
                type: 'bar',
                data: dataMap14001['values'],
                itemStyle: {
                    normal: {
                        // 定制显示（按顺序）
                        color: function(params) {
                            var colorList = ['#cf572a'];
                            return colorList[params.dataIndex]
                        }
                    },
                }
             },
            {
                name: '出院（人）',
                type: 'bar',
                data: dataMap14002['values'],
                itemStyle: {
                    normal: {
                        // 定制显示（按顺序）
                        color: function(params) {
                            var colorList = ['#28b1ff'];
                            return colorList[params.dataIndex]
                        }
                    },
                }
            }
        ]
    };

    var myChartEmergency3 = echarts.init(document.getElementById('emergency3'));
    myChartEmergency3.setOption(people);
                //住院人次数结束
         }
    });











})