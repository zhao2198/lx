function hxPageUtil(){
	this.token = '';
	this.baseUrl = '';
	this.init = function(){
		
	};
	this.getJson = function (url,data,callback){
		var _this = this;
        $.ajax({
            url:this.baseUrl + url,
            type:"get",
          headers: { 'Authorization': this.token },
            contentType:"application/json",
           dataType:"json",
            timeout:10000,
            data:data,
            success:function(msg){
            	 _this.call(msg.data, callback);
            },
            beforeSend: function(){ 
            }, 
            error:function(XMLHttpRequest, textStatus, errorThrown){
            	 alert(XMLHttpRequest.readyState + XMLHttpRequest.status + XMLHttpRequest.responseText);  
            }
        });
    };
    this.postJson = function(url,data,callback){
    	var _this = this;
        $.ajax({
            url:url,
            type:"post",
            headers: { 'Authorization': this.token },
            contentType:"application/json",
            dataType:"json",
            data:JSON.stringify(data),
            timeout:60000,
            success:function(msg){
            	 _this.call(msg.data, callback);
            },
            beforeSend: function(){ 
                alert("正在加载");
            },
            error:function(xhr,textstatus,thrown){

            }
        });
    };

  
    this.putJson = function(url,data,callback){
    	var _this = this;
        $.ajax({
            url:url,
            type:"put",
            headers: { 'Authorization': this.token },
            contentType:"application/json",
            dataType:"json",
            data:data,
            timeout:20000,
            success:function(msg){
            	 _this.call(msg.data, callback);
            },
            beforeSend: function(){ 
                alert("正在加载");
            },
            error:function(xhr,textstatus,thrown){

            }
        });
    };
   
    this.deleteJson = function(url,data,callback){
    	var _this = this;
    	
        $.ajax({
            url:url,
            type:"delete",
            headers: { 'Authorization': this.token },
            contentType:"application/json",
            dataType:"json",
            data:data,
            success:function(msg){
            	 _this.call(msg.data, callback);
            },
            beforeSend: function(){ 
                alert("正在加载");
            },
            error:function(xhr,textstatus,thrown){

            }
        });
    };
    this.tipOptions = {
  		  "closeButton": true,
  		  "debug": false,
  		  "progressBar": false,
  		  "preventDuplicates": false,
  		  "positionClass": "toast-top-center",
  		  "showDuration": "400",
  		  "hideDuration": "1000",
  		  "timeOut": "1000",
  		  "extendedTimeOut": "1000",
  		  "showEasing": "swing",
  		  "hideEasing": "linear",
  		  "showMethod": "fadeIn",
  		  "hideMethod": "fadeOut"
  };
    
    this.call = function(data, callback) {
    	if(data.code && data.code == '10000') {
    		callback(data);
    	}else if(data.message && data.message != ''){
    		this.alert(data.message);
    	}else{
    		callback(data);
    	}
    };
  
  this.success = function(message,_opts) {
  	if(_opts) {
  		$.extend(this.tipOptions, _opts);
  	}
  	
  	toastr.options = this.tipOptions;
  	toastr.success(message);
  };
  
  this.info = function(message,_opts) {
  	if(_opts) {
  		$.extend(this.tipOptions, _opts);
  	}
  	
  	toastr.options = this.tipOptions;
  	toastr.info(message);
  };
  
  this.warning = function(message,_opts) {
  	if(_opts) {
  		$.extend(this.tipOptions, _opts);
  	}
  	
  	toastr.options = this.tipOptions;
  	toastr.warning(message);
  };
  
  this.error = function(message,_opts) {
  	if(_opts) {
  		$.extend(this.tipOptions, _opts);
  	}
  	
  	toastr.options = this.tipOptions;
  	toastr.error(message);
  };
  
  this.alert = function(text){
  	swal({
          title: '',
          text: text,
          timer: 2000
      });
  };
  
  this.alertDelete = function(){
  	this.alert('请至少选择一条记录!');
  };
  
  this.confirm = function(text, callback){
  	swal({
          title: '',
          text: text,
          type: "warning",
          showCancelButton: true,
          confirmButtonColor: "#DD6B55",
          confirmButtonText: "确定",
          cancelButtonText: '取消！',
          closeOnConfirm: false
      }, function () {
      	callback();
      });
  };
  
  this.confirmDelete = function(callback){
  	this.confirm('确定要删除吗？',callback);
  };
}
    