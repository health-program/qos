         var arr13314Month=[];
         var arr13314MonthY=[];
         var ev13319DataArray=[];
         
         String.prototype.endWith = function(str) {  
        	    var reg = new RegExp(str + "$");  
        	    return reg.test(this);  
         }

$(function(){
    //支付方式对比
         var arr = {
         //  孕妇建卡数，孕妇产后访视数
              eventIds:'13319,13314'

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
          for(var i=0;i<rawData.result['13314'].length;i++){
        	  if(rawData.result['13314'][i].unitName.endWith('服务中心')){
        		 arr13314Month.push(rawData.result['13314'][i].unitName)
                 arr13314MonthY.push(rawData.result['13314'][i].count)
        	 }
          }
          for(var i=0;i<rawData.result['13319'].length;i++){
        	   if(rawData.result['13319'][i].unitName.endWith('服务中心')){
                	ev13319DataArray.push(rawData.result['13319'][i].count)
        	 }
          }
         let a=arr13314MonthY, b=ev13319DataArray;
         let newarr13002MonthY = a.map(function(v, i) {
        	 if(b[i]==0){
        		 return '0';
        	 }else{
        		 return (v*100/b[i]).toFixed(2);
        	 }
       });
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
      		        data: arr13314Month,
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
      		                   color:function(d){return "#439aff";}
      		                
      		              },
      		          },
      		        barWidth : 30,
      		        name: '孕产妇产后访视率',
      		        data: newarr13002MonthY,
      		        type: 'bar'
      		    }]
      		};



        

                //初始化echarts实例
                    var medicalAdvice_id_oldPeople = echarts.init(document.getElementById('emergency1'));
                    medicalAdvice_id_oldPeople.setOption(medicaladviceoption2oldPeople);
                   
         }
       
    });

})