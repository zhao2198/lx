function hxPaging(){
	this.page = {
			currentPage : 1,
			totalPage : 1,
			count : 0,
			pageSize : 20
			};
	this.paging = function(totalPage,callBack,pageId, pageSize) {
		if (pageId == undefined){
			pageId = "pager";
		}
		
		var option = {
				totalData: totalPage,
			    showData: pageSize,
			    coping: true,
			    jump: true,
			    callback: function (api) {
			        callBack(api.getCurrent());
			    }
		};
		
		$("#" + pageId).pagination(option);
		
	};
}
    