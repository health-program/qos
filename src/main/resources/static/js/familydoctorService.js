$(function(){


    //指定图标的配置和数据
// 个性化服务签约率（收费）
         var arr = {
           eventIds:'21002'
         }
      $.ajax({
         type : "post",    //请求类型
        url : "/qos/analysis/data/get/month/instalments",//请求的 URL地址
        data:arr,
        success: function (rawData) {
       var  month21002Data=convertMonthChartData(rawData.result, '21002', true); //
       var option = {
        tooltip: {
                             trigger: 'axis',
                             axisPointer: {
                                 type: 'shadow'
                             }
                         },
        xAxis: {
            type: 'category',
            data: month21002Data.month,
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
            data: month21002Data.valuesMap['total'], // total 如果是单位，就改成该单位的id
            type: 'line',
            lineStyle: {
                color: '#f45e23',
                width: 2, //这里是为了突出显示加上的
            }
          }]
    };

    //初始化echarts实例
   var myChart1 = echarts.init(document.getElementById('individualization'));
       myChart1.setOption(option);


        window.addEventListener("resize", function () {
                              myChart1.resize();
                          });



         }
    });



})