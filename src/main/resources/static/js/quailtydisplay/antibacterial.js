$(function(){


    $.ajax({
        type : "post",    //请求类型
        url : "/qos/countantibiotics/find/percent",//请求的 URL地址
        success: function (rawData) {


           var  nameArray=[];

           for(var i=0;i<rawData.result.length;i++){
                nameArray.push(rawData.result[i]['unitName'])
           }


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

    var antibacterialOption ={
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
            series: [
                {
                name: 'Line 4',
                type: 'pie',
                clockWise: true,
                hoverAnimation: false,
                radius: ['65%', '75%'],
                itemStyle: dataStyle,

            data: [{
                value: 7645434,
                name: '已婚已育'
            }, {
                value: 3612343,
                name: '总数',
                tooltip: {
                    show: false
                },
                itemStyle: placeHolderStyle
            }

            ]
        }, {
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
            ]
        };


         var antibacterial = echarts.init(document.getElementById('antibacterial'));
             antibacterial.setOption(antibacterialOption);
                //初始化echarts实例
          window.addEventListener("resize", function () {
                  medicalAdvice_id.resize();
          });





         }
     });










})