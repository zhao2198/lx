Vue说明 2.0 官网 https://cn.vuejs.org/v2/guide/
1:html页面常用属性绑定标签
	v-model 双向绑定 查询及新增修改，每一个表单元素都可以绑定
	v-for  循环可以使用在任何标签之上 如TR DIV OPTION等
	:属性 或 v-bind:class   此种主要用于绑定属性 如:value="companyValue" :class="className"等
	{{val}}  用于获取值目前主要用于v-for中 如{{item.id}}  {{item.name}}
	v-if  用于判断 如 v-if="item.parentId != 10"
	v-else-if
	v-else
	更多去网站 https://cn.vuejs.org/v2/guide/
	
	
2:验证框架  parsley.js 2.2 官网    http://parsleyjs.org/
	required：要求输入
	Not blank：不能为空
	Min length：最小长度
	Max length：最大长度
	Range length：长度区间
	Min：最小值
	Max：最大值
	Range：区域值
	RegExp：正则表达式
	Equal To：等于
	Min check：检查选择的checkbox的最少数量
	Max check：检查选择的checkbox的最多数量
	Range check：检查选择的checkbox的区间数量
	Remote：ajax验证
	
	正则
	
	验证数字的正则表达式集 
验证数字：^[0-9]*$ 
验证n位的数字：^\d{n}$ 
验证至少n位数字：^\d{n,}$ 
验证m-n位的数字：^\d{m,n}$ 
验证零和非零开头的数字：^(0|[1-9][0-9]*)$ 
验证有两位小数的正实数：^[0-9]+(.[0-9]{2})?$ 
验证有1-3位小数的正实数：^[0-9]+(.[0-9]{1,3})?$ 
验证非零的正整数：^\+?[1-9][0-9]*$ 
验证非零的负整数：^\-[1-9][0-9]*$ 
验证非负整数（正整数 + 0） ^\d+$ 
验证非正整数（负整数 + 0） ^((-\d+)|(0+))$ 
验证长度为3的字符：^.{3}$ 
验证由26个英文字母组成的字符串：^[A-Za-z]+$ 
验证由26个大写英文字母组成的字符串：^[A-Z]+$ 
验证由26个小写英文字母组成的字符串：^[a-z]+$ 
验证由数字和26个英文字母组成的字符串：^[A-Za-z0-9]+$ 
验证由数字、26个英文字母或者下划线组成的字符串：^\w+$ 
验证用户密码:^[a-zA-Z]\w{5,17}$ 正确格式为：以字母开头，长度在6-18之间，只能包含字符、数字和下划线。 
验证是否含有 ^%&',;=?$\" 等字符：[^%&',;=?$\x22]+ 
验证汉字：^[\u4e00-\u9fa5],{0,}$ 
验证Email地址：/^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/
验证InternetURL：^http://([\w-]+\.)+[\w-]+(/[\w-./?%&=]*)?$ ；^[a-zA-z]+://(w+(-w+)*)(.(w+(-w+)*))*(?S*)?$ 
验证电话号码：^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$：--正确格式为：XXXX-XXXXXXX，XXXX-XXXXXXXX，XXX-XXXXXXX，XXX-XXXXXXXX，XXXXXXX，XXXXXXXX。 
验证身份证号（15位或18位数字）：^\d{15}|\d{}18$ 
验证一年的12个月：^(0?[1-9]|1[0-2])$ 正确格式为：“01”-“09”和“1”“12” 
验证一个月的31天：^((0?[1-9])|((1|2)[0-9])|30|31)$ 正确格式为：01、09和1、31。 
整数：^-?\d+$ 
非负浮点数（正浮点数 + 0）：^\d+(\.\d+)?$ 
正浮点数 ^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$ 
非正浮点数（负浮点数 + 0） ^((-\d+(\.\d+)?)|(0+(\.0+)?))$ 
负浮点数 ^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$ 
浮点数 ^(-?\d+)(\.\d+)?$

页面用法
<input type="text" class="form-control" name="fullname" 
data-parsley-trigger="change"
           data-parsley-required="true"
           data-parsley-required-message="用户名不可为空"
           data-parsley-minlength="6" 
          data-parsley-minlength-message="用户名位数不可少于6位"
           data-parsley-maxlength="29"
           data-parsley-pattern=(?!^\d+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]{4,23} 
           data-parsley-pattern-message="必须为字母或数字"    >
       JS校验
       var ok = $('#form').parsley().isValid({force: true});
    		if(!ok){
    			return;
    		}
    		其它用法自己摸索 ，更多用法及API 官网    http://parsleyjs.org/
3.公共控件
	hxAxiosPageUtil.js
	ajax请求主要方法
	getJson
	postJson
	putJson
	deleteJson  body
	deleteParamJson param
	
	tip类的主要方法
	success
	info
	warning
	error
	
	消息框主要方法
	alert
	confirm
	confirmDelete
	
	ztree的主要方法
	ztree
	
	hxCharts.js
	主要方法
	pie
	bar
	lineBar
	line
	只是对四种图形做了基本封装，后续优化
	
	其它
	组织树
	设备类型树
4.前端要求

所有公共JS及CSS已经统一引入，各页面只需要引入各自的JS及CSS即可

所有js统一使用VUE,每个页面对象为hx的子对象如：
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
        companyVO:{}
	},
	//钩子函数，当所有DOM挂载在页面上时，加载此方法，相当于window.onload=function(){}
	mounted:function(){
		//需要用$nextTick来保证所有节点挂载后才执行方法
		//this.query();
	},
	methods:{
		search:function(){
			
		},
		add : function(){
		
		}
	}
})



	
           
           


