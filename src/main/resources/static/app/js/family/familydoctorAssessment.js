$(function(){


    //指定图标的配置和数据
    // 21001 综合健康管理服务包签约率（收费）
       var arr = {
           eventIds:'21005,21006,21007,21008,21009,21010,21011,21012,21013,21014,21015,21016,21017,21019,21020'
       }

      $.ajax({
        type : "post",    //请求类型
        url : "http://10.9.1.41:18081/home/page/qos/data/get/total",//请求的 URL地址
        data:arr,
        success: function (res) {

             $("#eventIds21005").text(res.result['21005'])
             $("#eventIds21006").text(res.result['21006'])
             $("#eventIds21007").text(res.result['21007'])
             $("#eventIds21008").text(res.result['21008'])
             $("#eventIds21009").text(res.result['21009'])
             $("#eventIds21010").text(res.result['21010'])
             $("#eventIds21011").text(res.result['21011'])
             $("#eventIds21012").text(res.result['21012'])
             $("#eventIds21013").text(res.result['21013'])
             $("#eventIds21014").text(res.result['21014'])
             $("#eventIds21015").text(res.result['21015'])
             $("#eventIds21016").text(res.result['21016'])
             $("#eventIds21017").text(res.result['21017'])
             $("#eventIds21019").text(res.result['21019'])
             $("#eventIds21020").text(res.result['21020'])


            var mySwiper = new Swiper ('.swiper-container', {
                loop: true, // 循环模式选项
                speed: 300,
                autoplay:true,
                // 如果需要分页器
                pagination:{
                    el:'.swiper-pagination',
                }//这样写小圆点就有了
            })


         }
    });



})