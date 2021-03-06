$(function(){

    // 21001 综合健康管理服务包签约率（收费）
         var arr = {
           eventIds:'21001'
         }
      $.ajax({
        type : "post",    //请求类型
        url : "/analysis/data/get/month/instalments",//请求的 URL地址
        data:arr,
        success: function (rawData) {
       var  month21001Data=convertMonthChartData(rawData.result, '21001', true);
       var option = {
        tooltip: {
           trigger: 'axis',
           axisPointer: {
              type: 'shadow'
           },formatter: function (params) {
               return params[0].name + '<br/>'
               + params[0].data + '%<br/>'
       }
        },
        xAxis: {
            type: 'category',
            data: month21001Data.month,
            axisLine: {
                lineStyle: {
                    color: '#657ca8',
                    width: 1, //这里是为了突出显示加上的
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#19d1ff'
                },
                 interval:0,
                 rotate:40
            }
          },
        yAxis: {
            splitLine: {
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: '#657ca8',
                    width: 1, //这里是为了突出显示加上的
                }
            },
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#19d1ff'
                }
            }
        },
         series:[{
            data: month21001Data.valuesMap['total'], // total 如果是单位，就改成该单位的id
            type: 'line',
            lineStyle: {
                color: '#f45e23',
                width: 2, //这里是为了突出显示加上的
            }
          }]
    };

    //初始化echarts实例
    var myChart = echarts.init(document.getElementById('chartmain'));
    myChart.setOption(option);
    window.addEventListener("resize", function () {
            myChart.resize();
        });

         }
    });
})