  //全局变量
        var arr13001Month=[]
        var arr13001MonthY=[]
        var arr13002MonthY=[]
        var arr14001MonthY=[]
        var arr31007MonthY=[]
        var arr31008MonthY=[]
        var arr31004MonthY=[]
        var arr13001Month=[]
    // 赋值
        var newarr13001MonthY;
        var newarr14001MonthY;
        var newarr13002MonthY;


$(function(){
    //支付方式对比
            var now = new Date();
                    var year = now.getFullYear(); //得到年份
                    var month = now.getMonth()+1;//得到月份
                    var date = now.getDate();//得到日期
                   var today=year + "-" + month + "-" + date;



	var date1 = new Date();
		var date2 = new Date(date1);
		  date2.setDate(date1.getDate() -25);
		  var jian25tian = date2.getFullYear() + "-" + (date2.getMonth() + 1) + "-" + date2.getDate()




         var arr = {
           eventIds:'13002,14001,13001', // 13002急诊人次数  14001住院人次数   13001门诊人次数
           startTime:jian25tian,
           byUnit: 0
         }
         var arr1 = {
                    eventIds:'31007,31004,31008', //  31007急诊人次    31008门诊人次       31004入院人次
                    startTime:jian25tian,
                    byUnit: 0
         }

      //从大到小排序
         function compareBigToSmall(property){
             return function(a,b){
                 var value1 = a[property];
                 var value2 = b[property];
                 return  value2 - value1;
             }
         };
    var  isNum1;
    var  isNum2;
    function getData(){
        $.ajax({
               type : "post",    //请求类型
               url : "/qos/analysis/data/get/day/instalments",//请求的 URL地址
               data:arr,
               success: function (rawData) {
                // 13002急诊人次数  14001住院人次数   13001门诊人次数
                //  31007急诊人次    31008门诊人次       31004入院人次
                for(var i=0;i<rawData.result['13001'].length;i++){
                       arr13001Month.push(rawData.result['13001'][i].day)
                       arr13001MonthY.push(rawData.result['13001'][i].totalNum)
                }
                for(var i=0;i<rawData.result['13002'].length;i++){
                     arr13002MonthY.push(rawData.result['13002'][i].totalNum)
               }
               for(var i=0;i<rawData.result['14001'].length;i++){
                     arr14001MonthY.push(rawData.result['14001'][i].totalNum)
               }

               isNum1=1;
               if(isNum1==1){
                     $.ajax({
                        type : "post",    //请求类型
                        url : "/qos/analysis/data/get/day/instalments",//请求的 URL地址
                        data:arr1,
                        success: function (rawData) {
                             isNum2=2
                          // 13002急诊人次数  14001住院人次数   13001门诊人次数
                         //  31007急诊人次    31008门诊人次       31004入院人次
                           for(var i=0;i<rawData.result['31004'].length;i++){
                                arr31004MonthY.push(rawData.result['31004'][i].totalNum)
                              }
                            for(var i=0;i<rawData.result['31007'].length;i++){
                                arr31007MonthY.push(rawData.result['31007'][i].totalNum)
                             }
                            for(var i=0;i<rawData.result['31008'].length;i++){
                                arr31008MonthY.push(rawData.result['31008'][i].totalNum)
                            }
                            let a=arr13002MonthY, b=arr31007MonthY;
                            let newarr13002MonthY = a.map(function(v, i) {
                                return v + b[i];
                            });
                            let aa=arr14001MonthY, bb=arr31008MonthY;
                            let newarr14001MonthY = aa.map(function(v, i) {
                                 return v + bb[i];
                            });
                            let aaa=arr13001MonthY, bbb=arr31004MonthY;
                            let newarr13001MonthY = aaa.map(function(v, i) {
                                 return v + bbb[i];
                            });
                            //echarts开始
                            medicaladviceoption ={
                                      tooltip : {
                                          trigger: 'axis'
                                              },
                                      legend: {
                                           data:['急诊人次','入院人次','普通门诊人次'],
                                               textStyle:{//图例文字的样式
                                               color:'#ffffff',
                                               fontSize:16
                                        }
                                      },

                                      grid: {
                                            left: '3%',
                                            right: '8%',
                                            bottom: '3%',
                                            containLabel: true
                                             },
                                       xAxis : [
                                              {
                                             type : 'category',
                                             name:"日期",//坐标轴名称。
                                             nameLocation:'end',//坐标轴名称显示位置。
                                             boundaryGap : false,
                                             axisLine:{
                                                         lineStyle:{
                                                         color:'#ffffff',
                                                          width:1,//这里是为了突出显示加上的
                                                                     }
                                                          },
                                             data :arr13001Month
                                              }
                                             ],
                                           yAxis : [
                                              {
                                               type : 'value',
                                               name:"人数",//坐标轴名称。
                                               nameLocation:'end',//坐标轴名称显示位置。
                                               axisLine:{
                                                 lineStyle:{
                                                         color:'#ffffff',
                                                         width:1,//这里是为了突出显示加上的
                                                            }
                                                }

                                                }
                                             ],
                                            series : [
                                              {
                                                  name:'急诊人次',
                                                  type:'line',
                                                  stack: '总量',
                                                  areaStyle: {normal: {
                                                          color: new echarts.graphic.LinearGradient(0, 0, 0, 1,[{
                                                           offset: 0, color: '#74a5d4' // 0% 处的颜色
                                                           }, {
                                                           offset: 0.4, color: '#74a5d4' // 100% 处的颜色
                                                            }, {
                                                           offset: 1, color: '#74a5d4' // 100% 处的颜色
                                                            }]
                                                                               ), //背景渐变色
                                                                                }},
                                                                                data: newarr13002MonthY, // total 如果是单位，就改成该单位的id
                                                                            },
                                                                             {
                                                                                name:'入院人次',
                                                                                type:'line',
                                                                                stack: '总量',
                                                                                areaStyle: {normal: {
                                                              color: new echarts.graphic.LinearGradient(0, 0, 0, 1,[{
                                                              offset: 0, color: '#79a0d8' // 0% 处的颜色
                                                             }, {
                                                               offset: 0.4, color: '#79a0d8' // 100% 处的颜色
                                                            }, {
                                                               offset: 1, color: '#79a0d8' // 100% 处的颜色
                                                           }]
                                                               ), //背景渐变色
                                                           }},
                                                                                data: newarr14001MonthY
                                                                             },
                                                                             {
                                                                                name:'普通门诊人次',
                                                                               type:'line',
                                                                               stack: '总量',
                                                                               areaStyle: {normal: {
                                                          color: new echarts.graphic.LinearGradient(0, 0, 0, 1,[{
                                                                                   offset: 0, color: '#6e90ba' // 0% 处的颜色
                                                                               }, {
                                                          offset: 0.4, color: '#6e90ba' // 100% 处的颜色
                                                                               }, {
                                                          offset: 1, color: '#6e90ba' // 100% 处的颜色
                                                                              }]
                                                                              ), //背景渐变色
                                                                               }},
                                                                               data:arr13001MonthY
                                                                              }
                                                                        ]
                                                                    };

                                                 var myChartMedicaladvice = echarts.init(document.getElementById('medicaladvice'));
                                               	   myChartMedicaladvice.setOption(medicaladviceoption);
                                                  window.addEventListener("resize", function () {
                                                              myChartMedicaladvice.resize();
                                                         });


                             }
                            });
                     }
              }
           });
  }


    getData()


})

