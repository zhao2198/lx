function hxAxiosPageUtil() {
    this.debug = false;
    this.token = '';
    this.baseUrl = '';
    this.add = 'add';
    this.update = 'update';
    this.addName = '新增';
    this.updateName = '修改';
    this.prefix = 'api';
    this.ssoUrl = '';
    this.curUser = {};
    this.domain = '';

    this.init = function () {
        axios.defaults.headers.common['Authorization'] = this.token;
        axios.defaults.headers.post['Content-Type'] = 'application/json';

        // 添加请求拦截器
        axios.interceptors.request.use(function (config) {
            // 在发送请求之前做些什么

            return config;
        }, function (error) {
            // 对请求错误做些什么
            return Promise.reject(error);
        });

        // 添加响应拦截器
        axios.interceptors.response.use(function (response) {
            // 对响应数据做点什么
            if (response.data.code == "10001") {
                window.open(hx.ctx + "/login", '_self');
            }
            return response;
        }, function (error) {
            // 对响应错误做点什么
            return Promise.reject(error);
        });
    };

    this.ssoGet = function (url, data, callback, flag) {
        var _this = this;
        axios.get(this.ssoUrl + url, {params: data})
            .then(function (response) {
                _this.call(response.data, callback, flag);
            })
            .catch(function (error) {
                //console.log(error);
            });
    }

    this.getJson = function (url, data, callback, flag) {
        var _this = this;
        if (flag == undefined) {
            flag = true;
            this.searchLoad();
        }
        axios.get(this.baseUrl + url, {params: data})
            .then(function (response) {
                _this.call(response.data, callback, flag);
            })
            .catch(function (error) {
                //console.log(error);
            });
    };
    this.postJson = function (url, data, callback) {
        var _this = this;
        this.submitLoad();
        axios.post(this.baseUrl + url, data)
            .then(function (response) {
                _this.call(response.data, callback, true);
            })
            .catch(function (error) {
                console.log(error);
            });
    };


    this.post = function (url, data, callback) {
        this.submitLoad();
        var _this = this;
        var config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        };
        var formData = new FormData();

        for (var key in data) {
            var val = data[key];
            if (val != undefined) {
                formData.append(key, val);
            }
        }
        axios.post(this.baseUrl + url, formData, config)
            .then(function (response) {
                _this.call(response.data, callback, true);
            })
            .catch(function (error) {
                // console.log(error);
            });
    };


    this.putJson = function (url, data, callback) {
        this.submitLoad();
        var _this = this;
        axios.put(this.baseUrl + url, data)
            .then(function (response) {
                _this.call(response.data, callback, true);
            })
            .catch(function (error) {
                console.log(error);
            });
    };

    //如果服务端将参数当做 java对象来封装接收则 参数格式为：{data: param}

    this.deleteJson = function (url, data, callback) {

        var _this = this;
        this.confirmDelete(function () {
            _this.submitLoad();
            axios.delete(_this.baseUrl + url, {data: data})
                .then(function (response) {
                    _this.call(response.data, callback, true);
                })
                .catch(function (error) {
                    // console.log(error);
                });
        });

    };

    //如果服务端将参数当做url 参数 接收，则格式为：{params: param}，这样发送的url将变为http:www.XXX.com?a=..&b=..

    this.deleteParamJson = function (url, data, callback) {
        var _this = this;
        this.confirmDelete(function () {
            axios.delete(_this.baseUrl + url, {params: data})
                .then(function (response) {
                    _this.call(response.data, callback);
                })
                .catch(function (error) {
                    //console.log(error);
                });
        });
    };

    this.call = function (result, callback, isLoading) {
        if (isLoading) {
            this.closeLoad();
        }
        if (result.code && result.code == '10000') {
            if (result.data == null) {
                result.data = {};
                result.data.data = {};
                result.data.paging = {};
                result.data.paging.rowCount = 0;
            }
            callback(result);
        } else if (result.message && result.message != '') {
            this.error(result.message);
        } else {
            callback(result);
        }
    };

    this.reset = function (id) {
        if (id == undefined) {
            id = "form";
        }
        $('#' + id).parsley().reset();
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

    this.success = function (message, _opts) {
        if (_opts) {
            $.extend(this.tipOptions, _opts);
        }

        if (message == undefined || message == '') {
            message = '操作成功!';
        }

        toastr.options = this.tipOptions;
        toastr.success(message);
    };

    this.saveSuccess = function () {
        this.success('保存成功!');
    };

    this.updateSuccess = function () {
        this.success('修改成功!');
    };

    this.deleteSuccess = function () {
        this.success('删除成功!');
    };

    this.info = function (message, _opts) {
        if (_opts) {
            $.extend(this.tipOptions, _opts);
        }

        toastr.options = this.tipOptions;
        toastr.info(message);
    };

    this.warning = function (message, _opts) {
        if (_opts) {
            $.extend(this.tipOptions, _opts);
        }

        toastr.options = this.tipOptions;
        toastr.warning(message);
    };

    this.error = function (message, _opts) {
        if (_opts) {
            $.extend(this.tipOptions, _opts);
        }

        toastr.options = this.tipOptions;
        toastr.error(message);
    };

    this.alert = function (text) {
        swal({
            title: '',
            text: text
        });
    };

    this.alertDelete = function () {
        this.alert('请至少选择一条记录!');
    };


    this.confirm = function (text, callback) {
        swal({
            title: '',
            text: text,
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            cancelButtonText: '取消！',
            closeOnConfirm: true
        }, function () {
            callback();
        });
    };

    this.confirmDelete = function (callback) {
        this.confirm('确定要删除吗？', callback);
    };

    this.ztree = function (id, url, callback, _opts) {
        var setting = {
            view: {
                showIcon: true,
                selectedMulti: false,     //可以多选
                showLine: true,
                expandSpeed: 'fast',
                dblClickExpand: false,
                addDiyDom: this.addDiyDom
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: callback
            }
        };

        if (_opts) {
            $.extend(setting, _opts);
        }

        var zTree = $.fn.zTree.init($("#" + id), setting);
        zTree.expandAll(true);

        this.getJson(url, {}, function (data) {
            zTree.addNodes(null, data);
            var nodes = zTree.getNodes();
            for (var i = 0; i < nodes.length; i++) { //设置节点展开
                zTree.expandNode(nodes[i], true, false, true);
            }
            if (_opts.nodes) {
                for (var i = 0; i < _opts.nodes.length; i++) {
                    var node = zTree.getNodeByParam("id", _opts.nodes[i]);
                    if (_opts.check.enable) {
                        zTree.checkNode(node, true, true);
                    } else {
                        zTree.selectNode(node);
                    }


                }
            }
        }, false);
    };

    this.addDiyDom = function (treeId, treeNode) {
        var spaceWidth = 5;
        var switchObj = $("#" + treeNode.tId + "_switch"),
            icoObj = $("#" + treeNode.tId + "_ico");
        switchObj.remove();
        icoObj.before(switchObj);

        if (treeNode.level > 1) {
            var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level) + "px'></span>";
            switchObj.before(spaceStr);
        }
        var spantxt = $("#" + treeNode.tId + "_span").html();
        if (spantxt.length > 10) {
            spantxt = spantxt.substring(0, 10) + "...";
            $("#" + treeNode.tId + "_span").html(spantxt);
        }
    }


    this.searchLoad = function () {
        $("#ngt").mLoading({
            "text": "正在加载中..."
        });//隐藏loading组件
    };

    this.submitLoad = function () {
        $("#ngt").mLoading({
            "text": "正在提交中..."
        });//隐藏loading组件
    };

    this.closeLoad = function () {
        $("#ngt").mLoading("hide");//隐藏loading组件
    };

    this.closeModel = function (id) {
        $('#' + id).modal('hide');
    };

    this.date = function (id, callback, type, _opts) {
        var dateType = type || 'date';
        var opts = {
            elem: '#' + id,
            theme: 'molv',
            type: dateType,
            done: function (val) {
                callback(val);
            }
        }
        $.extend(opts, _opts);
        laydate.render(opts);
    };
    /*
     * year	年选择器	只提供年列表选择
		month	年月选择器	只提供年、月选择
		date	日期选择器	可选择：年、月、日。type默认值，一般可不填
		time	时间选择器	只提供时、分、秒选择
		datetime	日期时间选择器	可选择：年、月、日、时、分、秒
     */
    this.dates = function (className, callback, type) {
        var dateType = type || 'date';
        lay('.' + className).each(function () {
            var _this = this;
            laydate.render({
                elem: this,
                trigger: 'click',
                type: dateType,
                theme: 'molv',
                done: function (val) {
                    callback(val, _this);
                }
            });
        });
    }
}
    