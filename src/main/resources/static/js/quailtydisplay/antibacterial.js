      var dataStyle = {
            normal: {
                label: {
                    show: false
                },
                labelLine: {
                    show: false
                }
            }
      };
      var placeHolderStyle = {
        normal: {
            color: 'rgba(0,0,0,0)',
            label: {
                show: false
            },
            labelLine: {
                show: false
            }
        },
        emphasis: {
            color: 'rgba(0,0,0,0)'
        }
    };

$(function(){


    $.ajax({
        type : "post",    //请求类型
        url : "/qos/countantibiotics/find/percent",//请求的 URL地址
        success: function (rawData) {

           var  nameArray=[];
           var  dataArray=[];

           for(var i=0;i<rawData.result.length;i++){
                nameArray.push(rawData.result[i]['unitName'])
                dataArray.push(rawData.result[i]['userRate'])
           }








    var antibacterialOptions ={
          tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
           },
                                              xAxis: {
                                                  type: 'category',



                                                  data: nameArray,

                                                  axisLine: {
                                                      lineStyle: {
                                                          color: '#19d1ff',
                                                          width: 1,
                                                          //这里是为了突出显示加上的
                                                      }
                                                  },

                                                  grid: {
                                                      left: 15,
                                                      right: 5,
                                                      bottom: 30,
                                                      containLabel: true
                                                  },
                                                  axisLabel: {
                                                      show: true,
                                                      textStyle: {
                                                          color: '#19d1ff',
                                                      },
                                                      interval: 0,
                                                      rotate: 30,
                                                      formatter: function(value) {
                                                          var reg = new RegExp('昆山市', "g");
                                                          return value.replace(reg, '');
                                                      }
                                                  },
                                              },

                                              yAxis: {
                                                  splitNumber:4,
                                                  minInterval: 6,
                                                  type: 'value',
                                                  splitLine: {
                                                      show: false
                                                  },
                                                  axisLine: {
                                                      lineStyle: {
                                                          color: '#19d1ff',
                                                          width: 2,
                                                          //这里是为了突出显示加上的
                                                      }
                                                  }
                                              },
                                              series: [{
                                                  itemStyle: {
                                                      normal: {
                                                          // 随机显示
                                                          color: function(d) {
                                                              return "#" + '439AFF';
                                                          }

                                                      },
                                                  },

                                                  barWidth: 15,
                                                  data: dataArray,
                                                  type: 'bar'
                                              }]
                                          };

     var antibacterial = echarts.init(document.getElementById('antibacterial'));
        antibacterial.setOption(antibacterialOptions);
                //初始化echarts实例
          window.addEventListener("resize", function () {
                  antibacterial.resize();
          });
       }
     });
 })