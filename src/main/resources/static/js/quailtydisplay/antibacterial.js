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



/*
         {
                                        name: 'Line 3',
                                        type: 'pie',
                                        clockWise: true,
                                        radius: ['50%', '60%'],
                                        itemStyle: dataStyle,
                                        hoverAnimation: false,

                                        data: [{
                                            value: 2632321,
                                            name: '已婚未育'
                                        }, {
                                            value: 2212343,
                                            name: '总数',
                                            tooltip: {
                                                show: false
                                            },
                                            itemStyle: placeHolderStyle
                                        }]
                                    }

*/

           var seriesValue=[];
           for(var i=0;i<rawData.result.length;i++){
                  seriesValue.push({
                                  name: 'Line 3',
                                                                       type: 'pie',
                                                                       clockWise: true,
                                                                       radius: ['50%', '60%'],
                                                                       itemStyle: dataStyle,
                                                                       hoverAnimation: false,
                             data:[
                                  {
                                     value:rawData.result[i]['userRate'],
                                     name:rawData.result[i]['unitName']
                                  }
                                ]
                     });

            }




    /*    series.push({
        　　　　name:nameArray[i],
        　　　　type:'line',
        　　　　data:dataArry[d].split(','),
        　　　　symbol:'circle',
        　　　　symbolSize:'7',
        　　　　smooth:true
        　　})*/




    var antibacterialOptions ={
                color: ['#4DFFE3','#4DE0FF','#4DFF8F','#ADFF4D'],
                tooltip : {
                    show: true,
                    formatter: "{b} : {c}"
                },

                legend: {
                    top: "13.5%",
                    x: 'right',
                    left: "42%",
                    itemWidth:0,itemHeight:0,
                    data: nameArray,
                    itemGap: 38,
                    textStyle: {
                        color: '#fff',
                        align:'right',
                        x: 'right',
                        textAlign:'right'
                    },

                    selectedMode: true,
                    orient: "vertical",

                },
              series: seriesValue
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