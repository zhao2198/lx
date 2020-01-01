//@ sourceURL=/public/js/sys/office.js
hx.office = new Vue({
	el:'#ngt',
	data:{
		list:[],
        officeVO:{},
        showList:true,
        action:'',//add update
        actionName:''
	},
	//钩子函数，当所有DOM挂载在页面上时，加载此方法，相当于window.onload=function(){}
	mounted:function(){
		//需要用$nextTick来保证所有节点挂载后才执行方法
		
		this.search();
		
	},
	updated:function(){
		 
		this.tree(); 
		
	},
	methods:{
		search:function(){
			var _this = this;
			 hx.getJson('/office/list',{},function(result){
				 _this.list = result.data;
				 _this.list.forEach(function(val,index){
					 if(index != 0) {
						 _this.$set(val,'className',"gradeA odd treegrid-"+val.id+" treegrid-parent-" + val.parentId);
					 }else{
						 _this.$set(val,'className',"gradeA odd treegrid-"+val.id);
					 }
					
				});
				
      		 });
		},
		tree : function(){
			 $('#tree').treegrid();
		},
		initAdd:function(item){
			this.action = hx.add;
			this.actionName=hx.addName;
			this.toAdd();
			var officeVO = item;
			if(officeVO) {
				var office = {};
				office.id = '';
				office.parentId = officeVO.id;
				office.name = '';
				office.remarks = '';
				this.officeVO = office;
			}
			
		},
		initUpdate:function(id){
			this.action = hx.update;
			this.actionName= hx.updateName;
			this.toAdd();
			this.getOffice(id);
			
		},
		getOffice:function(id) {
			var _this = this;
			hx.getJson('/office/info/' + id,{},function(result){
				//_this.companyVO = result.data;
				var officeVO = result.data;
				if(officeVO) {
					var office = {};
					office.id = officeVO.id;
					office.parentId = officeVO.parentId;
					office.name = officeVO.name;
					office.remarks = officeVO.remarks;
					_this.officeVO = office;
				}
				
     		 });
		},
		cancle:function(){
			this.showList = true;
		},
		save:function() {
			var ok = $('#form').parsley().isValid({force: true});
    		if(!ok){
    			return;
    		}
    		var url = '/office/create';
    		var _this = this;
    		if(this.action == 'update') {
    			url = '/office/update';
    			hx.putJson(url,this.officeVO,function(result){
    				hx.updateSuccess();  
    				_this.toList();
        		});
    		}else{
    			hx.postJson(url,this.officeVO,function(result){
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
			hx.deleteJson("/office/delete_batch",ary,function(result){
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
		toList:function(){
			this.showList = true;
			this.officeVO = {};
			this.search();
		},
		toAdd:function(){
			this.showList = false;
			this.officeVO = {};
		}
	}
})


