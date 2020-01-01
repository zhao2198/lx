//@ sourceURL=/public/js/sys/equipmentParam.js
hx.equipmentParam = new Vue({
	el:'#ngt',
	data:{
		list:[],
		page:{
             total:0,//总页数
             size:10,//每页显示条目个数不传默认10
             curPage:1//当前页码
            
        },
        equipmentParamVO:{},
        equipmentParamName:'',
        showList:true,
        showAdd:false,
        showTransfer:false,
        action:'',//add update
        actionName:'',
        dictItemList:[],
        dictTypes:["equipment_param"],//数据字典
        allCheck:false,
        transferVO:{}
	},
	//钩子函数，当所有DOM挂载在页面上时，加载此方法，相当于window.onload=function(){}
	mounted:function(){
		//需要用$nextTick来保证所有节点挂载后才执行方法
		this.search();
	},
	methods:{
		search:function(){
			var _this = this;
			 hx.getJson('/equ_param/list',this.getQueryData(),function(result){
				 _this.list = result.data.data;
				 _this.page.total = result.data.paging.rowCount;
				 
				// var p = new hxPaging();
				// p.paging(result.data.paging.rowCount,hx.company.search,'pager',10);
				 //p.paging(120,hx.company.search,'pager',10);
      		 });
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
			obj.name = this.equipmentParamName;
			return obj;
		},
		initAdd:function(){
			this.action = hx.add;
			this.actionName=hx.addName;
			this.toAdd();
			this.getDictItem();
		},
		initUpdate:function(id){
			this.action = hx.update;
			this.actionName=hx.updateName;
			this.toAdd();
			this.getEquipmentParam(id);
			this.getDictItem();
			
		},
		getEquipmentParam:function(id) {
			var _this = this;
			hx.getJson('/equ_param/info/' + id,{},function(result){
				//_this.companyVO = result.data;
				var equipmentParamVO = result.data;
				if(equipmentParamVO) {
					var equipmentParam = {};
					equipmentParam.id = equipmentParamVO.id;
					equipmentParam.name = equipmentParamVO.name;
					equipmentParam.type = equipmentParamVO.type;
					equipmentParam.code = equipmentParamVO.code;
					equipmentParam.ismain = equipmentParamVO.ismain;
					equipmentParam.unit = equipmentParamVO.unit;
					equipmentParam.remarks = equipmentParamVO.remarks;
					equipmentParam.enableFlag = equipmentParamVO.enableFlag;
					_this.equipmentParamVO = equipmentParam;
				}
     		 });
		},
		reset:function(){
			this.equipmentParamName='';
			this.search();
		},
		cancle:function(){
			this.showList = true;
			this.showAdd = false;
			this.showTransfer=false;
		},
		getDictItem : function(){
			var _this = this;
			var types = '';
			this.dictTypes.forEach(function(dictType){
				types += 'types='+dictType + "&";
			})
			hx.getJson('/common/dictitem?' + types,{},function(result){
				 _this.dictItemList = result.data;
     		 });
		},
		save:function() {
			var ok = $('#form').parsley().isValid({force: true});
    		if(!ok){
    			return;
    		}
    		var url = '/equ_param/create';
    		var _this = this;
    		if(this.action == 'update') {
    			url = '/equ_param/update';
    			hx.putJson(url,this.equipmentParamVO,function(result){
    				hx.updateSuccess();  
    				_this.toList();
        		});
    		}else{
    			hx.postJson(url,this.equipmentParamVO,function(result){
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
			hx.deleteJson("/equ_param/delete_batch",ary,function(result){
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
			this.showAdd = false;
			this.showTransfer = false;
			this.equipmentParamVO = {};
			this.search();
		},
		toAdd:function(){
			this.showList = false;
			this.showAdd = true;
			this.showTransfer = false;
			this.equipmentParamVO = {};
		 
		}
		
	}
})


