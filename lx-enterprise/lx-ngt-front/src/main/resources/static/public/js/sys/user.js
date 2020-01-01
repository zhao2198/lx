//@ sourceURL=/public/js/sys/user.js
hx.user = new Vue({
	el:'#ngt',
	data:{
		list:[],
		roleList:[],
		page:{
             total:0,//总页数
             size:10,//每页显示条目个数不传默认10
             curPage:1//当前页码
            
        },
        userVO:{},
        file:'',
        userId:'',
        userName:'',
        loginName:'',
        officeId:'',
        showList:true,
        action:'',//add update
        actionName:'',
        allCheck:false
	},
	//钩子函数，当所有DOM挂载在页面上时，加载此方法，相当于window.onload=function(){}
	mounted:function(){
		//需要用$nextTick来保证所有节点挂载后才执行方法
		this.search();
		this.tree();
	},
	methods:{
		search:function(){
			var _this = this;
			 hx.getJson('/user/list',this.getQueryData(),function(result){
				 _this.list = result.data.data;
				 _this.page.total = result.data.paging.rowCount;
			
      		 });
		},
		reset:function(){
			this.userName='';
			this.loginName='';
			this.officeId='';
			var treeObj = $.fn.zTree.getZTreeObj("ztree");
			treeObj.cancelSelectedNode();
			this.search();
		},
		tree:function(){
			var _this = this;
			hx.ztree('ztree','/office/tree',function(event, treeId, treeNode){
				_this.officeId = treeNode.id;
				_this.page.curPage=1;
				_this.search();
			});
		},
		choseOffice:function(){
			var _this = this;
			var offset=$("#officeName").offset(); 
			$("#office").css("left",(offset.left - 200)+"px").css("top",offset.top+"px"); 
			
			hx.ztree('officeTree','/office/tree',function(event, treeId, treeNode){
//				var userVO = _this.userVO;
//				userVO.officeId = treeNode.id;
//				userVO.officeName = treeNode.name;
//				_this.userVO = userVO;
//				
				var id = treeNode.id;
				if(id.length >= 6) {
					_this.$set(_this.userVO,'officeId',treeNode.id);
					_this.$set(_this.userVO,'officeName',treeNode.name);
					
					$("#officeName").val(treeNode.name);
					$('#form').parsley().validate();
					
				}
				
//				$("#officeId").val(treeNode.id);
//				$("#officeName").val(treeNode.name);
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
			obj.name = this.userName;
			obj.loginName=this.loginName;
			obj.officeId = this.officeId;
			return obj;
		},
		initAdd:function(){
			this.action = hx.add;
			this.actionName = hx.addName;
			this.toAdd();
		},
		initUpdate:function(id){
			this.action = hx.update;
			this.actionName = hx.updateName;
			this.toAdd();
			this.getUser(id);
			
		},
		getUser:function(id) {
			var _this = this;
			hx.getJson('/user/info/' + id,{},function(result){
				//_this.companyVO = result.data;
				var userVO = result.data;
				if(userVO) {
					var user = {};
					user.id = userVO.id;
					user.officeId = userVO.officeId;
					user.loginName = userVO.loginName;
					user.no = userVO.no;
					user.name = userVO.name;
					user.email = userVO.email;
					user.phone = userVO.phone;
					user.mobile = userVO.mobile;
					user.photo = userVO.photo;
					user.loginFlag = userVO.loginFlag;
					user.remarks = userVO.remarks;
					user.officeName = userVO.officeName;
					_this.userVO = user;
					
					var photoUrl = userVO.photoUrl;
					if(photoUrl && photoUrl != '') {
						$("#image").attr("src", photoUrl); //将图片路径存入src中，显示出图片
					}
						
									
				}
				
     		 });
		},
		cancle:function(){
			this.showList = true;
		},
		save:function() {
			var ok = $('#form').parsley().isValid();
    		if(!ok){
    			return;
    		}
    		
    		event.preventDefault();
          
            
            this.userVO.file = this.file;
            
    		var url = '/user/create';
    		var _this = this;
    		if(this.action == 'update') {
    			url = '/user/update';
    			hx.post(url,this.userVO,function(result){
    				hx.updateSuccess();  
    				_this.toList();
        		});
    		}else{
    			hx.post(url,this.userVO,function(result){
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
			hx.deleteJson("/user/delete_batch",ary,function(result){
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
		checkItem:function(item){
			if(typeof item.checked === 'undefined'){
				//局部$set方法，在item里注册ischecked属性，赋值为true
				this.$set(item,'checked',true);
			}else {
				//点击反转属性值
				item.checked = !item.checked;
			};
		},
		toList:function(){
			this.showList = true;
			this.userVO = {};
			this.search();
		},
		toAdd:function(){
			this.showList = false;
			this.userVO = {};
			this.userVO.loginFlag = '1';
			hx.reset();
		},
		resetPassword:function(id){
			hx.postJson("/user/pass/reset/"+id,{},function(result){
				hx.success();  
    		});
		},
		getObjectURL:function(file){
			 var url = null;
			    if (window.createObjectURL != undefined) { // basic
			        url = window.createObjectURL(file);
			    } else if (window.URL != undefined) { // mozilla(firefox)
			        url = window.URL.createObjectURL(file);
			    } else if (window.webkitURL != undefined) { // webkit or chrome
			        url = window.webkitURL.createObjectURL(file);
			    }
			    return url;
		},
		getFile:function(event) {
            this.file = event.target.files[0];
            var objUrl = this.getObjectURL(this.file); //获取图片的路径，该路径不是图片在本地的路径
            if (objUrl) {
                $("#image").attr("src", objUrl); //将图片路径存入src中，显示出图片
                //upimg();
            }
        },
        initRole:function(item){
        	var _this = this;
        	this.userId = item.id;
        	hx.getJson('/role/user/list',{"userId":this.userId},function(result){
				 _this.roleList = result.data;
     		 });
        },
        saveUserRole : function(){
        	var obj = {};
        	var ary = new Array();
			this.roleList.forEach(function(item,index){
				if(item.checked){
					ary.push(item.id);
				}
			});
			obj.userId = this.userId;
			obj.roleIds = ary;
			
			hx.postJson('/role/user_to_role/create',obj,function(result){
				hx.success();
				hx.closeModel("role");
    		 });
        }
	}
})




