function convertUnitChartData(data, eventId, isRate) {
        var edata = data[eventId],
            max = 0,
            unit = [],
            values = [];
        edata && edata.forEach(function(item) {
            var r = isRate ? getActiveRate(item) : item.count;
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



function getActiveRate(row) {
    var e = row.activeArchivesNumber,
        t = row.peopleNumber;
    if (t == 0) return 0;
    var x = e * 100 / t;
    if (x < 0.005) {
        return 0;
    }
    return x.toFixed(2) * 1;
}

$(function(){
	
	function compareBigToSmall(property){
        return function(a,b){
            var value1 = a[property];
            var value2 = b[property];
            return  value2 - value1;
        }
    };
	
	
	$.ajax({
		type : "post",    //请求类型
        url : "/qos/gongwei/archives/search/all",//请求的 URL地址
        async: false,
        success: function (rawData){
        	console.log(rawData);
        	var ev22001 = rawData.result;
        	var ev22001Sort = rawData.result.sort(compareBigToSmall('activeArchivesNumber'));
        	var ev22001DataArray = ev22001Sort.slice(0,5);
        	
        	var newast22001 = {}
            newast22001['22001']=ev22001DataArray
            var unit22001DatanewArrayunit22001DatanewArray=convertUnitChartData(newast22001, '22001', true);
        	
        	
        	var medicaladviceoption1 = {

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
            		        data: unit22001DatanewArrayunit22001DatanewArray.unit,
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
            		          barWidth : 20,
            		          name: '健康档案建档率',
            		        data: unit22001DatanewArrayunit22001DatanewArray.values,
            		        type: 'bar'
            		    }]
            		};
        	
        	//初始化echarts实例
            var medicalAdvice_id = echarts.init(document.getElementById('emergency2'));
            medicalAdvice_id.setOption(medicaladviceoption1);
        }
	})
})

/*$(function(){
    //支付方式对比
         var arr = {
         //  建档率，公开率（使用率==建档率），
              eventIds:'22001,22002'

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
         async: false,
         data:arr,
         success: function (rawData) {
         console.log(111);
         var  unit22001Data=convertUnitChartData(rawData.result, '22001', true); //
         var ev22001 = convertUnitChartData(rawData.result, '22001', true);
         var ev22001Sort = rawData.result['22001'].sort(compareBigToSmall('count'));
         var ev22001DataArray = ev22001Sort.slice(0,5);

        var newast22001 = {}
        newast22001['22001']=ev22001DataArray
        var unit22001DatanewArrayunit22001DatanewArray=convertUnitChartData(newast22001, '22001', true);




         //社区建档率
        var medicaladviceoption1 = {
      		  xAxis: {
      		        type: 'category',
      		        data: unit22001DatanewArrayunit22001DatanewArray.unit,
      		        axisLabel: {
	                    interval: 0,
	                    rotate: 20 //角度顺时针计算的
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
      		        data: [0.1,0.2,0.3,0.4,0.5],
      		        type: 'bar'
      		    }]
      		};



        

                //初始化echarts实例
                    var medicalAdvice_id = echarts.init(document.getElementById('emergency'));
                    medicalAdvice_id.setOption(medicaladviceoption1);
                   

         }
       
    });

})*/