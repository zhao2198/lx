//@ sourceURL=/public/js/sys/elecTemplate.js
hx.elecTemplate = new Vue({
	el:'#ngt',
	data:{
		list:[],
		page:{
             total:0,//总页数
             size:10,//每页显示条目个数不传默认10
             curPage:1//当前页码
        },
        elecTemplateVO:{},
        powerPrice:{},//电度电价对象
        basicPrice:{},//基本电价对象
        forcePrice:{},//力调电价对象
        powerPriceLadderPrice:{},//电度电价阶梯电价对象
        templateName:'',
        transformerList:[],
        sharpTimeArr:[],
        peakTimeArr:[],
        valleyTimeArr:[],
        flatTimeArr:[],
        sharpTimeInputArr:[],
        peakTimeInputArr:[],
        valleyTimeInputArr:[],
        flatTimeInputArr:[],
        sharpTimes:'',
        peakTimes:'',
        flatTimes:'',
        valleyTimes:'',
        showPowerPriceOnly:false,  //电度电价显示隐藏
        showPowerPriceLadder:false, //阶梯电价隐藏
        showBasicPrice:true,	//显示隐藏基本电价
        showList:true,
        action:'',//add update
        dictItemList:[],
        dictTypes:["1000"],//数据字典
        allCheck:false,
        checkList:[],
        totalCheckList:[],
        timeState:'',
        electricCostForceValue:'',//功率因数标准,
        forceChecked:true,		//功率因数选中状态
        forceChecked1:false,
        forceChecked2:false,
        basicSecondStart:'',
        basicThirdStart:'',
        basicFourthStart:'',
        file:'',
        showTransformer:true,
        showTemplate:true,
        transformerAction:'',
        transformerConfigVO:{},
//        operatingCapacity:'',
//        ratedCapacity:'',
//        contractCapacity:'',
        transformerId:'',
	},
	//钩子函数，当所有DOM挂载在页面上时，加载此方法，相当于window.onload=function(){}
	mounted:function(){
		this.search();
		this.showTransformer = false;
		this.showTemplate = false;
	},
	methods:{
		search:function(){
			var _this = this;
			 hx.getJson('/electric/price/template/list',this.getQueryData(),function(result){
				 _this.list = result.data.data;
				 _this.page.total = result.data.paging.rowCount;
      		 });
		},
		pageFn:function(curPage, pageSize){
			 this.page.curPage=curPage;
			 this.page.size=pageSize;
			 this.search();
		},
		getQueryData: function(){
			var obj = {};
			obj.currentPage = this.page.curPage;
			obj.pageSize = this.page.size;
			obj.keyword = this.templateName;
			return obj;
		},
		radioChange:function(){
			this.showPowerPriceOnly = !this.showPowerPriceOnly;
		},
		basicPirceRadioChange:function(){
			this.showBasicPrice = !this.showBasicPrice;
		},
		choseTimes:function(timeState){
			this.timeState = timeState;//给时间段类型赋值
			var _this = this;
			var offset=$("#timesId").offset(); 
			$("#timesDiv").css("left",(offset.left - 200)+"px").css("top",offset.top+"px"); 
			this.initCheck(timeState);
		},
		initCheck:function(timeState){
			this.checkList = [];
			for(var i=0;i<24;i++){
				var hour=(i<10?"0"+i:i);
				var check = {};
				check.value = i;
				check.text = hour+':00 ~ '+(i+1<10?"0"+(i+1):(i+1))+':00';
				check.ischecked =false;
				check.disabled=false;
				this.checkList.push(check);
			}
			for (var i = 0; i < this.checkList.length; i++) {
				for (var j = 0; j < this.totalCheckList.length; j++) {
					if(this.checkList[i].value==this.totalCheckList[j]){
						this.checkList[i].ischecked=true;
						this.checkList[i].disabled=true;
					}
				}
				if(timeState=='sharp'){
					for (var k = 0; k < this.sharpTimeArr.length; k++) {
						if(this.checkList[i].value==this.sharpTimeArr[k]){
							this.checkList[i].disabled=false;
						}
					}
				}else if(timeState == 'peak'){
					for (var k = 0; k < this.peakTimeArr.length; k++) {
						if(this.checkList[i].value==this.peakTimeArr[k]){
							this.checkList[i].disabled=false;
						}
					}
				}else if(timeState == 'flat'){
					for (var k = 0; k < this.flatTimeArr.length; k++) {
						if(this.checkList[i].value==this.flatTimeArr[k]){
							this.checkList[i].disabled=false;
						}
					}
				}else if(timeState == 'valley'){
					for (var k = 0; k < this.valleyTimeArr.length; k++) {
						if(this.checkList[i].value==this.valleyTimeArr[k]){
							this.checkList[i].disabled=false;
						}
					}
				}
				
			}
			
		},
		selectTimes:function(){    //页面点击确认的时候，判断选中的时间段，给尖峰平谷数组赋值
			var _this = this;
			_this.checkList.forEach(function(val,index){
				//通过局部注册来注册data里的每一项ischecked属性。
				if(val.ischecked && val.disabled == false) {
					if(_this.timeState == 'sharp' && _this.sharpTimeArr.indexOf(val.value)<0) {
						_this.sharpTimeArr.push(val.value);
						_this.sharpTimeInputArr.push(val.value+'时');
						_this.sharpTimes =_this.sharpTimeInputArr;
						_this.totalCheckList.push(val.value);
					}else if(_this.timeState == 'peak' && _this.peakTimeArr.indexOf(val.value)<0) {
						_this.peakTimeArr.push(val.value);
						_this.peakTimeInputArr.push(val.value+'时');
						_this.peakTimes =_this.peakTimeInputArr;
						_this.totalCheckList.push(val.value);
					}else if(_this.timeState == 'flat' && _this.flatTimeArr.indexOf(val.value)<0){
						_this.flatTimeArr.push(val.value);
						_this.flatTimeInputArr.push(val.value+'时');
						_this.flatTimes =_this.flatTimeInputArr;
						_this.totalCheckList.push(val.value);
					}else if(_this.timeState == 'valley' && _this.valleyTimeArr.indexOf(val.value)<0){
						_this.valleyTimeArr.push(val.value);
						_this.valleyTimeInputArr.push(val.value+'时');
						_this.valleyTimes = _this.valleyTimeInputArr;
						_this.totalCheckList.push(val.value);
					}
				}
				if(!val.ischecked&&_this.timeState == 'sharp'&& _this.sharpTimeArr.indexOf(val.value)>=0){
					_this.totalCheckList.ngtRemove(val.value);
					_this.sharpTimeArr.ngtRemove(val.value);
					_this.sharpTimeInputArr.ngtRemove(val.value+'时');
					_this.sharpTimes=_this.sharpTimeInputArr;
				}
				if(!val.ischecked&&_this.timeState == 'peak'&& _this.peakTimeArr.indexOf(val.value)>=0){
					_this.totalCheckList.ngtRemove(val.value);
					_this.peakTimeArr.ngtRemove(val.value);
					_this.peakTimeInputArr.ngtRemove(val.value+'时');
					_this.peakTimes =_this.peakTimeInputArr;
				}
				if(!val.ischecked&&_this.timeState == 'flat'&& _this.flatTimeArr.indexOf(val.value)>=0){
					_this.totalCheckList.ngtRemove(val.value);
					_this.flatTimeArr.ngtRemove(val.value);
					_this.flatTimeInputArr.ngtRemove(val.value+'时');
					_this.flatTimes =_this.flatTimeInputArr;
				}
				if(!val.ischecked&&_this.timeState == 'valley'&& _this.valleyTimeArr.indexOf(val.value)>=0){
					_this.totalCheckList.ngtRemove(val.value);
					_this.valleyTimeArr.ngtRemove(val.value);
					_this.valleyTimeInputArr.ngtRemove(val.value+'时');
					_this.valleyTimes =_this.valleyTimeInputArr ;
				}
			});
			this.checkList = [];
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
		initAdd:function(){
			this.action = 'add';
			this.toAdd();
			this.getDictItem();
			this.getTransformerList();
		},
		initUpdate:function(id){
			this.action = 'update';
			this.showList = false;
			this.showTransformer = false;
			this.showTemplate = true;
			this.getElecTemplate(id);
			this.getTransformerList();
			this.getDictItem();
		},
		getTransformerList:function(){
			var _this = this;
			hx.getJson('/equipment/transformer/list',{},function(result){
				_this.transformerList = result.data;
			});
		},
		getElecTemplate:function(id) {
			var _this = this;
			hx.getJson('/electric/price/template/info/'+id,{},function(result){
				var elecTemplateVO = result.data;
				if(elecTemplateVO) {
					var powerPrice = {};	//电度电价vo
					var basicPrice= {};	//基本电价vo
					var forcePrice = {};//力调电价vo
					var powerPriceLadderPrice = {};//阶梯电价vo
					
					_this.elecTemplateVO.id = elecTemplateVO.id;
					_this.elecTemplateVO.equipmentId = elecTemplateVO.equipmentId;
					_this.elecTemplateVO.templateCode = elecTemplateVO.templateCode;
					_this.elecTemplateVO.templateName = elecTemplateVO.templateName;
					//电度对象处理开始
					powerPrice.type = elecTemplateVO.powerPrice.type;
					if(elecTemplateVO.powerPrice.type=="0"){
						_this.showPowerPriceOnly = true;
					}else if(elecTemplateVO.powerPrice.type=="1"){
						_this.showPowerPriceOnly = false;
					}
					//sharpTimes等实际为页面显示所需要，真正传到后台的是sharpTimeArr
					var sharpArr = elecTemplateVO.powerPrice.sharpTimeArr;
					var peakArr = elecTemplateVO.powerPrice.peakTimeArr;
					var flatArr = elecTemplateVO.powerPrice.flatTimeArr;
					var valleyArr = elecTemplateVO.powerPrice.valleyTimeArr;
					sharpArr = sharpArr.map(s=> +s);	//将数组中的元素从字符串转为数值型
					peakArr = peakArr.map(s=> +s);
					flatArr = flatArr.map(s=> +s);
					valleyArr = valleyArr.map(s=> +s);
					_this.sharpTimeArr = sharpArr;
			        _this.peakTimeArr = peakArr;
			        _this.valleyTimeArr = flatArr;
			        _this.flatTimeArr = valleyArr;
					if(sharpArr!=null){
						_this.sharpTimeArr = sharpArr;
						sharpArr.forEach(function(val,index){
							_this.sharpTimes += val+"时,";
						});
					}
					if(peakArr!=null){
						_this.peakTimeArr = peakArr;
						peakArr.forEach(function(val,index){
							_this.peakTimes += val+"时,";
						});
					}
					if(flatArr!=null){
						_this.flatTimeArr = flatArr;
						flatArr.forEach(function(val,index){
							_this.flatTimes += val+"时,";
						});
					}
					if(valleyArr!=null){
						_this.valleyTimeArr = valleyArr;
						valleyArr.forEach(function(val,index){
							_this.valleyTimes += val+"时,";
						});
					}
					if(sharpArr!=null&&peakArr!=null&&flatArr!=null&&valleyArr!=null){
						_this.totalCheckList = _this.totalCheckList.concat(sharpArr,peakArr,flatArr,valleyArr);
					}
					powerPrice.isladder = elecTemplateVO.powerPrice.isladder;
					powerPrice.flatPrice = elecTemplateVO.powerPrice.flatPrice;
					powerPrice.peakPrice = elecTemplateVO.powerPrice.peakPrice;
					powerPrice.sharpPrice = elecTemplateVO.powerPrice.sharpPrice;
					powerPrice.valleyPrice = elecTemplateVO.powerPrice.valleyPrice;
					powerPrice.value = elecTemplateVO.powerPrice.value;
					//电度对象处理结束，基本对象开始
					basicPrice.electricCostBasicType = elecTemplateVO.basicPrice.electricCostBasicType;
					if(elecTemplateVO.basicPrice.electricCostBasicType=="0"){
						_this.showBasicPrice = true;
					}else if(elecTemplateVO.basicPrice.electricCostBasicType=="1"){
						_this.showBasicPrice = false;
					}
					basicPrice.electricCostBasicValue = elecTemplateVO.basicPrice.electricCostBasicValue;
					basicPrice.firstEnd = elecTemplateVO.basicPrice.firstEnd;
					basicPrice.firstLadderPrice = elecTemplateVO.basicPrice.firstLadderPrice;
					basicPrice.firstStart = elecTemplateVO.basicPrice.firstStart;
					
					basicPrice.fourthEnd = elecTemplateVO.basicPrice.fourthEnd;
					basicPrice.fourthLadderPrice = elecTemplateVO.basicPrice.fourthLadderPrice;
//					basicPrice.fourthStart = elecTemplateVO.basicPrice.fourthStart;
					_this.basicFourthStart = elecTemplateVO.basicPrice.fourthStart;
					basicPrice.fourthStart = _this.basicFourthStart;
					basicPrice.secondEnd = elecTemplateVO.basicPrice.secondEnd;
					basicPrice.secondLadderPrice = elecTemplateVO.basicPrice.secondLadderPrice;
//					basicPrice.secondStart = elecTemplateVO.basicPrice.secondStart;
					_this.basicSecondStart = elecTemplateVO.basicPrice.secondStart;
					basicPrice.secondStart = _this.basicSecondStart;
					basicPrice.thirdEnd = elecTemplateVO.basicPrice.thirdEnd;
					basicPrice.thirdLadderPrice = elecTemplateVO.basicPrice.thirdLadderPrice;
//					basicPrice.thirdStart = elecTemplateVO.basicPrice.thirdStart;
					_this.basicThirdStart = elecTemplateVO.basicPrice.thirdStart;
					basicPrice.thirdStart = _this.basicThirdStart;
					//基本对象结束，力调对象开始
					forcePrice.electricCostForceUrl = elecTemplateVO.forcePrice.electricCostForceUrl;
					forcePrice.electricCostForceValue = elecTemplateVO.forcePrice.electricCostForceValue;
					if(elecTemplateVO.forcePrice.electricCostForceValue=="0.80"){
						_this.forceChecked=true;
						_this.forceChecked1=false;
						_this.forceChecked2=false;
					}else if(elecTemplateVO.forcePrice.electricCostForceValue=="0.85"){
						_this.forceChecked=false;
						_this.forceChecked1=true;
						_this.forceChecked2=false;
					}else if(elecTemplateVO.forcePrice.electricCostForceValue=="0.90"){
						_this.forceChecked=false;
						_this.forceChecked1=false;
						_this.forceChecked2=true;
					}
					//给对象赋值
					_this.forcePrice = forcePrice;
					_this.powerPrice = powerPrice;
					_this.basicPrice = basicPrice;
					_this.elecTemplateVO.forcePrice = _this.forcePrice;
					_this.elecTemplateVO.powerPrice = _this.powerPrice;
					_this.elecTemplateVO.basicPrice = _this.basicPrice;
				}
     		 });
		},
		cancle:function(){
			this.showList = true;
			this.showTransformer = false;
			this.showTemplate = false;
			this.sharpTimeArr=[];
	        this.peakTimeArr=[];
	        this.valleyTimeArr=[];
	        this.flatTimeArr=[];
	        this.sharpTimeInputArr=[];
	        this.peakTimeInputArr=[];
	        this.valleyTimeInputArr=[];
	        this.flatTimeInputArr=[];
	        this.sharpTimes='';
	        this.peakTimes='';
	        this.flatTimes='';
	        this.valleyTimes='';
	        this.checkList=[];
	        this.totalCheckList=[];
	        this.timeState='';
	        this.powerPrice={};
	        this.basicPrice={};
	        this.forcePrice={};
	        this.powerPriceLadderPrice={};
	        this.templateName='';
		},
		getDictItem : function(){
			var _this = this;
			var types = '';
			this.dictTypes.forEach(function(dictType){
				types += 'types='+dictType + "&";
			});
			hx.getJson('/common/dictitem?' + types,{},function(result){
				 _this.dictItemList = result.data;
     		 });
		},
		save:function() {
			$('#form').parsley().validate();
			var ok = $('#form').parsley().isValid({force: true});
    		if(!ok){
    			return;
    		}
    		var url = '/electric/price/template/create';
    		var _this = this;
    		if(_this.totalCheckList.length<24){
    			hx.alert("请检查并选择完整的24小时时间段！");
    			return;
    		}
    		if(_this.sharpTimeArr.length==0){
    			hx.alert("请选择尖时段！");
    			return;
    		}
    		if(_this.peakTimeArr.length==0){
    			hx.alert("请选择峰时段！");
    			return;
    		}
    		if(_this.flatTimeArr.length==0){
    			hx.alert("请选择平时段！");
    			return;
    		}
    		if(_this.valleyTimeArr.length==0){
    			hx.alert("请选择谷时段！");
    			return;
    		}
    		//处理电度对象
			this.powerPrice.sharpTimeArr = this.sharpTimeArr;//电度对象中的尖属性赋值
			this.powerPrice.peakTimeArr = this.peakTimeArr;//电度对象中的峰属性赋值
			this.powerPrice.flatTimeArr = this.flatTimeArr;//电度对象中的平属性赋值
			this.powerPrice.valleyTimeArr = this.valleyTimeArr;//电度对象中的谷属性赋值
			/*if(this.showPowerPriceOnly){//0单一1自定义
				this.powerPrice.type='0';
			}else{
				this.powerPrice.type = '1';
			}*/
			this.powerPrice.type = this.showPowerPriceOnly?"0":"1";
			//处理电度电价中的阶梯电价
			if(!this.showPowerPriceLadder){
				this.powerPrice.isladder = '0';
			}else{
				this.powerPrice.isladder = '1';
			}
			this.elecTemplateVO.powerPrice = this.powerPrice;	//电度对象赋给总对象
			//处理基本对象
			this.basicPrice.electricCostBasicType = this.showBasicPrice?"0":"1";
			
			
			
			this.elecTemplateVO.basicPrice = this.basicPrice; //基本对象赋值总对象
			//处理力调对象
//			this.forcePrice.electricCostForceValue = this.electricCostForceValue; 
			if(this.forceChecked){
    			this.forcePrice.electricCostForceValue='0.80';
    		}
			if(this.forceChecked1){
				this.forcePrice.electricCostForceValue='0.85';
			}
			if(this.forceChecked2){
				this.forcePrice.electricCostForceValue='0.90';
			}
			this.elecTemplateVO.forcePrice = this.forcePrice;
    		
    		if(this.action == 'update') {
    			url = '/electric/price/template/update/'+_this.elecTemplateVO.id;
    			hx.putJson(url,this.elecTemplateVO,function(result){
    				hx.success("修改成功！");  
    				_this.toList();
    				_this.cancle();
        		});
    		}else{
    			hx.postJson(url,this.elecTemplateVO,function(result){
    				hx.success("添加成功！");  
    				_this.toList();
    				_this.cancle();
        		});
    		}
		},
		deleteItem:function(id){
			var ary = new Array();
			ary.push(id);
			this.deleteBatch(ary);
			
			
		},
		deleteById:function(item){
			var _this = this;
			hx.getJson('/electric/price/template/is/delete/' + item.equipmentId,{},function(result){
				 if(result.code==10000){
					 hx.alert("此模板正在使用中，无法删除！");
				 }else{
					 hx.deleteJson("/electric/price/template/delete/"+item.id,{},function(result){
							hx.success("删除成功！");  
							_this.toList();
			    		});
				 }
    		});
		},
		deleteBatch : function(ary){
			if(ary.length == 0) {
				hx.alertDelete();
				return;
			}
			var _this = this;
			hx.deleteJson("/electric/price/template/delete/{id}",ary,function(result){
				hx.success("删除成功！");  
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
		toList:function(){
			this.showList = true;
			this.showTransformer = false;
			this.showTemplate = false;
			this.elecTemplateVO = {};
			this.search();
			this.sharpTimeArr=[];
			this.peakTimeArr=[];
			this.valleyTimeArr=[];
			this.flatTimeArr=[]
			this.sharpTimes='';
			this.peakTimes='';
			this.flatTimes='';
			this.valleyTimes='';
			this.powerPrice={};
	        this.basicPrice={};
	        this.forcePrice={};
	        this.powerPriceLadderPrice={};
	        this.transformerConfigVO={};
		},
		changeForceValue:function(val){
			if(val=="0"){
				this.forceChecked = true;
				this.forceChecked1 = false;
				this.forceChecked2= false;
				this.electricCostForceValue = "0.80";
			}else if(val=="1"){
				this.forceChecked = false;
				this.forceChecked1 = true;
				this.forceChecked2= false;
				this.electricCostForceValue = "0.85";
			}else if(val=="2"){
				this.electricCostForceValue = "0.90";
				this.forceChecked = false;
				this.forceChecked1 = false;
				this.forceChecked2= true;
			}
		},
		toAdd:function(){
			this.showList = false;
			this.showTemplate = true;
			this.showTransformer = false;
			this.elecTemplateVO = {};
		},
		basicLadderChange:function(){
			this.basicSecondStart = this.basicPrice.firstEnd;
			this.basicPrice.secondStart = this.basicSecondStart;
			
			this.basicThirdStart = this.basicPrice.secondEnd;
			this.basicPrice.thirdStart = this.basicThirdStart;
			
			this.basicFourthStart = this.basicPrice.thirdEnd;
			this.basicPrice.fourthStart = this.basicFourthStart;
//			this.basicPrice.thirdStart = this.basicPrice.secondEnd;
//			this.basicPrice.fourthStart = this.basicPrice.thirdEnd;
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
       initTransformerAdd:function(){
//			this.transformerAction = 'add';
			this.toTransformerAdd();
			this.getTransformerList();
       },
       /*initTransformerUpdate:function(id){
			this.transformerAction = 'update';
			this.showList = false;
			this.showTransformer = true;
			this.showTemplate = false;
		},*/
       toTransformerAdd:function(){
    	   	this.showTransformer = true;
			this.showList = false;
			this.showTemplate = false;
			this.transformerConfigVO = {};
       },
       saveTransformerConfig:function(){
    	   	
    	   	var ok = $('#transformerForm').parsley().isValid({force: true});
	   		if(!ok){
	   			return;
	   		}
	   		var _this = this;
//	   		_this.transformerConfigVO.ratedCapacity = _this.ratedCapacity;
//	   		_this.transformerConfigVO.operatingCapacity = _this.operatingCapacity;
//	   		_this.transformerConfigVO.contractCapacity = _this.contractCapacity;
	   		if(_this.transformerId==""){hx.alert('请选择变压器');return;}
	   		var url = '/transformer/config/create/'+_this.transformerId;
	   		if(this.transformerAction == 'update') {
				url = '/transformer/config/update/'+_this.transformerId;
				hx.putJson(url,this.transformerConfigVO,function(result){
					hx.success("修改成功！");  
					_this.toList();
	    		});
			}else{
				hx.postJson(url,this.transformerConfigVO,function(result){
					hx.success("添加成功！");  
					_this.toList();
	    		});
			}
       },
       transformerConfigCancle:function(){
			this.showList = true;
			this.showTemplate = false;
			this.showTransformer = false;
       },
       transformerChange:function(){
    	   var _this = this;
    	   if(_this.transformerId==""){return}
    	   hx.getJson('/transformer/config/info/'+_this.transformerId,{},function(result){
    		   var transformerConfigVO = result.data;
    		   if(transformerConfigVO){
    			  
    			   _this.transformerConfigVO = transformerConfigVO;
    			   var obj = {};
//    			   _this.ratedCapacity = transformerConfigVO.ratedCapacity;
//    			   _this.operatingCapacity = transformerConfigVO.operatingCapacity;
//    			   _this.contractCapacity = transformerConfigVO.contractCapacity;
    			  
    			   obj.ratedCapacity = transformerConfigVO.ratedCapacity;
    			   obj.operatingCapacity = transformerConfigVO.operatingCapacity;
    			   obj.contractCapacity = transformerConfigVO.contractCapacity;
    			   _this.transformerConfigVO = obj;
    			  
    			   if(transformerConfigVO.ratedCapacity==null&&transformerConfigVO.operatingCapacity==null&&transformerConfigVO.contractCapacity==null){
    				   _this.transformerAction = 'add';
    			   }else{
    				   _this.transformerAction = 'update';
    			   }
    			   
    		   }
    	   });
       }
	}
});


