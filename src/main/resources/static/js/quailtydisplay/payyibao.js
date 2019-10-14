 var dataMap={}
   var  data31009;
   var  data31010;



     var now = new Date();
                 var year = now.getFullYear(); //得到年份
                 var month = now.getMonth()+1;//得到月份
                 var date = now.getDate();//得到日期
                var today=year + "-" + month + "-" + date;





     var selectIDValue='31009';
//封装的方法开始
       // 15005,15006
var newspotitalOptions=function(id){
    var eventId = $("#selectID").val();
    var data = dataMap[eventId];
    var newspotitalOptionsss={
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    visualMap: {
        show: false,
         min: 800,
         max: 50000,
        inRange: {
            color: ['#5569be']
        }
    },
    series : [
        {
            name:'支付方式',
            type:'pie',
            radius : '80%',
            center: ['50%', '50%'],
            data:[
                {value:data31009, name:'医保'},
                {value:data31010, name:'自费'}
            ].sort(function (a, b) { return a.value - b.value; }),
            roseType: 'radius',
            label: {
                normal: {
                    textStyle: {
                        color: '#2cffff'
                    }
                }
            },
            labelLine: {
                normal: {
                    lineStyle: {
                        color: '#2cffff'
                    },
                    smooth: 0.2,
                    length: 10,
                    length2: 20
                }
            },
            itemStyle: {
                normal: {
                    color: '#2cffff',
                    shadowBlur: 200,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }

        }
    ]
};
    var newspotital_id = echarts.init(document.getElementById('newspotital'));
      newspotital_id.setOption(newspotitalOptionsss);
 }
//封装的方法结束





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
     var arr={
          eventIds:'31009,31010',  // 伟华
          startTime:today
      };

         if(selectIDValue=='31009'){
                        arr = {
                            eventIds:'31009,31010',  // 伟华
                            startTime:today
                        }

                       $.ajax({
                            type : "post",    //请求类型
                            url : "/qos/analysis/data/get/total",//请求的 URL地址
                            data:arr,
                            success: function (rawData) {
                            data31009 = rawData.result['31009']
                            data31010 = rawData.result['31010']
                             newspotitalOptions()
                           }
                        });
              }
         $("#selectID").change(function(data){
         var selectIDValue= $('#selectID option:selected') .val();
         if(selectIDValue=='31009'){
               arr = {
                   eventIds:'31009,31010',  // 伟华
                   startTime:today
               }
                  $.ajax({
                   type : "post",    //请求类型
                   url : "/qos/analysis/data/get/total",//请求的 URL地址
                   data:arr,
                   success: function (rawData) {
                   data31009 = rawData.result['31009']
                   data31010 = rawData.result['31010']
                   newspotitalOptions()
                  }
               });
     }

     })





$("#selectID").val('31009')
// 触发事件
$("#selectID").trigger("change")




})