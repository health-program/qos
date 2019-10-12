

$(function(){
    //支付方式对比
         var arr = {
         //  建档率，公开率（使用率==建档率），
              eventIds:'22003'

         }
         //从大到小排序
         function compareBigToSmall(property){
             return function(a,b){
                 var value1 = a[property];
                 var value2 = b[property];
                 return  value2 - value1;
             }
         };

       $.ajax({
         type : "post",    //请求类型
         url : "/qos/analysis/data/get/unit",//请求的 URL地址
         data:arr,
         success: function (rawData) {
         console.log(111);
         var  unit22003Data=convertUnitChartData(rawData.result, '22003', true); //
         var ev22003 = convertUnitChartData(rawData.result, '22003', true);
         var ev22003Sort = rawData.result['22003'];
         var ev22003DataArray = ev22003Sort;

        var newast22003 = {}
        newast22003['22003']=ev22003DataArray
        var unit22003DatanewArrayunit22003DatanewArray=convertUnitChartData(newast22003, '22003', true);




         //社区建档率
        var medicaladviceoption2oldPeople = {
        	
        		tooltip: {
	                trigger: 'axis',
	                formatter: function(params, ticket, callback) {
	                    var html = params[0].axisValueLabel + '：';
	                    for (var i = 0; i < params.length; i++) {
	                        var param = params[i];
	                        html += '<br/>' + param.marker + param.seriesName + '：' + param.value + '%';
	                    }
	                    return html;
	                },
	                axisPointer: {
	                    type: 'shadow'
	                }
	            },	
      		  xAxis: {
      		        type: 'category',
      		        data: unit22003DatanewArrayunit22003DatanewArray.unit,
      		        axisLabel: {
                          interval: 0,
                          rotate: 0, //角度顺时针计算的
                         formatter: function(value) {
                             var reg = new RegExp('社区卫生服务中心', "g");
                                 return value.replace(reg, '');
                         }
                   },
      		        axisLine:{
                          lineStyle:{
                              color:'#19d1ff',
                              width:1,//这里是为了突出显示加上的
                          }
                      }
      		    },
      		    yAxis: {
      		        type: 'value',
      		        splitLine:{
                          show:false
                      },
                      axisLine:{
                          lineStyle:{
                              color:'#19d1ff',
                              width:1,//这里是为了突出显示加上的
                          }
                      },
                      axisLabel: {
  	                    formatter: function(val) {
	                        return val + '%';
	                    }
	                }
      		    },
      		    series: [{
      		    	  itemStyle: {
      		              normal: {
      		                  // 随机显示
      		                   color:function(d){return "#"+Math.floor(Math.random()*(256*256*256-1)).toString(16);}
      		                
      		              },
      		          },
      		          barWidth : 30,
      		        name: '老年人体检率',
      		        data: unit22003DatanewArrayunit22003DatanewArray.values,
      		        type: 'bar'
      		    }]
      		};



        

                //初始化echarts实例
                    var medicalAdvice_id_oldPeople = echarts.init(document.getElementById('emergency'));
                    medicalAdvice_id_oldPeople.setOption(medicaladviceoption2oldPeople);


         }
       
    });

})