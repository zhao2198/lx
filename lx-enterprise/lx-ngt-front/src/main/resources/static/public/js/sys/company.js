//@ sourceURL=/public/js/company/company.js
hx.company = new Vue({
	el:'#ngt',
	data:{
		list:[],
		page:{
             total:0,//总页数
             size:10,//每页显示条目个数不传默认10
             curPage:1//当前页码
            
        },
        companyVO:{},
        companyName:'',
        showList:true,
        action:'',//add update
        actionName:'',
        dictItemList:[],
        dictTypes:["1000"],//数据字典
        allCheck:false
	},
	//钩子函数，当所有DOM挂载在页面上时，加载此方法，相当于window.onload=function(){}
	mounted:function(){
		//需要用$nextTick来保证所有节点挂载后才执行方法
		//this.query();
		this.search();
		this.getDictItem();
	},
	methods:{
		search:function(){
			var _this = this;
			 hx.getJson('/company/list',this.getQueryData(),function(result){
				 _this.list = result.data.data;
				 _this.page.total = result.data.paging.rowCount;
			
      		 });
		},
		reset:function(){
			this.companyName='';
			this.search();
		},
		pageFn:function(curPage, pageSize){
			 this.page.curPage=curPage;
			 this.page.size=pageSize;
			 this.search();
			 this.allCheck=false;
		},
		getQueryData: function(){
			var obj = {};
			obj.currentPage = this.page.curPage;
			obj.pageSize = this.page.size;
			obj.name = this.companyName;
			return obj;
		},
		initAdd:function(){
			this.action = hx.add;
			this.actionName = hx.addName;
			this.toAdd();
			this.companyVO.type = '';
			
		},
		initUpdate:function(id){
			this.action = hx.update;
			this.actionName = hx.updateName;
			this.toAdd();
			this.getCompany(id);
			
			
		},
		getCompany:function(id) {
			var _this = this;
			hx.getJson('/company/info/' + id,{},function(result){
				//_this.companyVO = result.data;
				var companyVO = result.data;
				if(companyVO) {
					var company = {};
					company.id = companyVO.id;
					company.type = companyVO.type;
					company.name = companyVO.name;
					
					_this.companyVO = company;
				}
				
     		 });
		},
		cancle:function(){
			this.showList = true;
		},
		getDictItem : function(){
			var _this = this;
			
			 _this.dictItemList.push({"id":0,"name":"单一"});
			 _this.dictItemList.push({"id":1,"name":"集团"});
//			var types = '';
//			this.dictTypes.forEach(function(dictType){
//				types += 'types='+dictType + "&";
//			})
//			hx.getJson('/common/dictitem?' + types,{},function(result){
//				 _this.dictItemList = result.data;
//     		 });
		},
		save:function() {
			var ok = $('#form').parsley().isValid({force: true});
    		if(!ok){
    			return;
    		}
    		var url = '/company/create';
    		var _this = this;
    		if(this.action == 'update') {
    			url = '/company/update';
    			hx.putJson(url,this.companyVO,function(result){
    				hx.updateSuccess();  
    				_this.toList();
        		});
    		}else{
    			hx.postJson(url,this.companyVO,function(result){
    				hx.saveSuccess();  
    				_this.toList();
        		});
    		}
		},
		deleteItem:function(id){
			var ary = new Array();
			ary.push(id);
			this.deleteBatch(ary);
			
			
		},
		deleteBatch : function(ary){
			if(ary.length == 0) {
				hx.alertDelete();
				return;
			}
			var _this = this;
			hx.deleteJson("/company/delete_batch",ary,function(result){
				hx.deleteSuccess();  
				_this.toList();
    		});
		},
		deleteAll : function(){
			var ary = new Array();
			this.list.forEach(function(item,index){
				if(item.ischecked){
					ary.push(item.id);
				}
			});
			this.deleteBatch(ary);
		},
		checkAll : function(){
			//根据传参决定是全选还是取消全选
			this.allCheck = !this.allCheck;
			this.selectedAll = this.allCheck;
			var _this = this;//用ES5方法解决this指向问题
			//forEach()，val为数据的每一项，index为每一项的索引
			this.list.forEach(function(val,index){
				//通过局部注册来注册data里的每一项ischecked属性。
				if(typeof val.ischecked === 'undefined'){
					_this.$set(val,'ischecked',_this.selectedAll);
				}else {
					val.ischecked = _this.selectedAll;
				}
			});
			
		},
		selectedItem:function(item){
			if(typeof item.ischecked === 'undefined'){
				//局部$set方法，在item里注册ischecked属性，赋值为true
				this.$set(item,'ischecked',true);
			}else {
				//点击反转属性值
				item.ischecked = !item.ischecked;
			};
		},
		toList:function(){
			this.showList = true;
			this.companyVO = {};
			this.search();
		},
		toAdd:function(){
			this.showList = false;
			this.companyVO = {};
			hx.reset();
		}
	}
})


