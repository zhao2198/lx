//@ sourceURL=/public/js/common/search.js
hx.search = new Vue({
	el:'body',
	data:{
        myData:[],   //储存搜索到的内容
        info:'',     //input框里输入的内容
        index:-1     //索引 实现上下选择
    },
	methods:{
		 searchUser:function (e) {

//           当按上下键时返回
           if(e.keyCode==38||e.keyCode==40)return;

//           按回车时搜索
           if(e.keyCode==13){
//               新的页面打开
               //window.open('https://www.baidu.com/s?wd='+this.info);
               this.info='';
           }

//         var _this = this;
			 hx.getJson('/user/list',{},function(result){
				 _this.myData = result.data.data;
      		 });
       },
//       按下键往下选择
       change:function () {
           this.index++;
           this.info=this.myData[this.index];  //输入框显示选择的内容
           if(this.index==this.myData.length)this.index=-1;  //当选到最后一个时索引变为-1

       },
//       按上键往上选择
       up:function () {
           this.index--;
           this.info=this.myData[this.index];
           if(this.index==-1)this.index=this.myData.length-1;
       }
	}
})


