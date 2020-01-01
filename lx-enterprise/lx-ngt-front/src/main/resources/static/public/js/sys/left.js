//@ sourceURL=/public/js/sys/left.js
hx.left = new Vue({
    el: '#side-menu',
    data: {
        curmissions: [],
        curPermissions: [],
        permissions: [
            {
                "text": "系统设置",
                "name": "系统设置",
                "type": 1,
                "show": 0,
                "className": "fa-gear",
                "children": [
                    {
                        "text": "用户管理",
                        "name": "用户管理",
                        "type": 2,
                        "path": "sys/user",
                        "show": 0
                    },
                    {
                        "text": "角色管理",
                        "name": "角色管理",
                        "type": 2,
                        "path": "sys/role",
                        "show": 0
                    },
                    {
                        "text": "公司管理",
                        "name": "客户公司管理",
                        "type": 2,
                        "path": "sys/company"
                    },
                    {
                        "text": "组织管理",
                        "name": "组织机构",
                        "type": 2,
                        "path": "sys/office",
                        "show": 0
                    },
                    {
                        "text": "设备分类管理",
                        "name": "设备分类管理",
                        "type": 2,
                        "path": "sys/equipment_type",
                        "show": 0
                    },
                    {
                        "text": "采集器型号",
                        "name": "采集器型号",
                        "type": 2,
                        "path": "sys/collector_model",
                        "show": 0
                    },
                    {
                        "text": "采集卡",
                        "name": "采集卡",
                        "type": 2,
                        "path": "sys/acquisition_card",
                        "show": 0
                    },
                    {
                        "text": "设备参数",
                        "name": "设备参数",
                        "type": 2,
                        "path": "sys/equipment_param",
                        "show": 0
                    },
                    {
                        "text": "电费模板",
                        "name": "电费模板",
                        "type": 2,
                        "path": "sys/elec_template",
                        "show": 0
                    }

                ]

            },
            {
                "text": "大屏投放",
                "name": "大屏投放",
                "type": 2,
                "path": "analysis/screen",
                "show": 0,
                "className": "fa-desktop",
                "children": []
            },
            {
                "text": "用能总览",
                "name": "用能总览",
                "type": 2,
                "className": "fa-line-chart",
                "path": "analysis/overview",
                "show": 0,
                "children": []
            },
            {
                "text": "用能分析",
                "name": "用能分析",
                "type": 1,
                "show": 0,
                "className": "fa-pie-chart",
                "children": [
                    {
                        "text": "能耗分析",
                        "name": "能耗分析",
                        "type": 2,
                        "path": "analysis/energy",
                        "show": 0
                    },
                    {
                        "text": "参数分析",
                        "name": "参数分析",
                        "type": 2,
                        "path": "analysis/param",
                        "show": 0
                    }
                ]

            },
            {
                "text": "在线监测",
                "name": "在线监测",
                "type": 2,
                "path": "monitor/online",
                "show": 0,
                "className": "fa-bar-chart",
                "children": []
            },
            {
                "text": "检修中心",
                "name": "检修中心",
                "type": 1,
                "show": 0,
                "className": "fa-wrench",
                "children": [
                    {
                        "text": "修复单",
                        "name": "修复单",
                        "type": 2,
                        "path": "repair/form",
                        "show": 0
                    },
                    {
                        "text": "巡检",
                        "name": "巡检",
                        "type": 2,
                        "path": "repair/patrol",
                        "show": 0
                    },
                    {
                        "text": "修复单统计",
                        "name": "修复单统计",
                        "type": 2,
                        "path": "repair/form/statis",
                        "show": 0
                    },
                    {
                        "text": "个人巡检统计",
                        "name": "个人巡检统计",
                        "type": 2,
                        "path": "repair/patrol/statis",
                        "show": 0
                    }, {
                        "text": "定保",
                        "name": "定保",
                        "type": 2,
                        "path": "repair/periodic",
                        "show": 0
                    }

                ]

            },
            {
                "text": "告警中心",
                "name": "告警中心",
                "type": 2,
                "path": "monitor/alarm",
                "show": 0,
                "className": "fa-bell",
                "children": []
            },
            {
                "text": "设备中心",
                "name": "设备中心",
                "type": 2,
                "path": "monitor/center",
                "show": 0,
                "className": "fa-tasks",
                "children": []
            },
            {
                "text": "抄表中心",
                "name": "抄表中心",
                "type": 1,
                "show": 0,
                "className": "fa-dashboard",
                "children": [
                    {
                        "text": "抄表计划",
                        "name": "抄表计划",
                        "type": 2,
                        "path": "plan/meter",
                        "show": 0
                    },
                    {
                        "text": "抄表任务",
                        "name": "抄表任务",
                        "type": 2,
                        "path": "plan/meter/task",
                        "show": 0
                    }
                ]

            },
            {
                "text": "值班中心",
                "name": "值班中心",
                "type": 1,
                "show": 0,
                "className": "fa-user-secret",
                "children": [
                    {
                        "text": "值班计划",
                        "name": "值班计划",
                        "type": 2,
                        "path": "plan/duty",
                        "show": 0
                    },
                    {
                        "text": "值班管理",
                        "name": "值班管理",
                        "type": 2,
                        "path": "plan/mission",
                        "show": 0
                    },
                    {
                        "text": "值班配置",
                        "name": "值班配置",
                        "type": 2,
                        "path": "plan/duty/config",
                        "show": 0
                    },
                    {
                        "text": "值班人员",
                        "name": "值班人员",
                        "type": 2,
                        "path": "plan/member",
                        "show": 0
                    },
                    {
                        "text": "值班记录",
                        "name": "值班记录",
                        "type": 2,
                        "path": "plan/records",
                        "show": 0
                    },
                    {
                        "text": "值班人员统计",
                        "name": "值班人员统计",
                        "type": 2,
                        "path": "plan/duty/statis",
                        "show": 0
                    }
                ]

            }
        ]
    },
    //钩子函数，当所有DOM挂载在页面上时，加载此方法，相当于window.onload=function(){}
    mounted: function () {
        this.search();
        this.init();

    },
    methods: {
        init: function () {
            var _this = this;
            hx.baseUrl = hx.ssoUrl;
            if (hx.token != '') {
                hx.getJson('user/token/' + hx.token, {}, function (result) {
                    hx.curUser = result.data;
                    hx.header.curName = hx.curUser.name;
                    hx.header.curLoginName = hx.curUser.loginName;
                    hx.header.curPhoto = hx.domain + hx.curUser.photo;
                    hx.header.companyName = hx.curUser.companyName;
                });
            }

            hx.baseUrl = hx.ngtUrl + hx.prefix;

//		      var hxBack = new hxAxiosPageUtil();
//		      hxBack.baseUrl = baseUrl + "/";


        },
        initMenu: function () {
            var url = window.location.hash;
            if (url != '' && url != '#analysis/screen') {
                url = url.substr(1);
                hxBack.getJson(url, {}, function (data) {
                    $("#content").html(data);
                    $(".nav li > a").each(function () {
                        var dataUrl = $(this).attr("data-url");
                        if (dataUrl == url) {
                            //$(this).parents("ul").show();

                            //$(this).parents("ul").collapse();
                            //$(this).parent("li").addClass("active");
                            $(this).parent("li").addClass("itemActive");
                            if ($(this).attr("type") == '1') {
                                $(this).parents("li.first").find("a")[0].click();
                            }

                        }
                    })
                });
            } else {
                hxBack.getJson('index', {}, function (data) {
                    $("#content").html(data);

                });
            }
        },
        logout: function () {
            hx.ssoGet("/user/loginout", {}, function (res) {
                if (res.code == "10000") {
                    deleteCookie("NGT_TOKEN");
                    window.open(hx.ctx + "/login", '_self');
                }
            })
        },
        clickli: function (e) {
            var event = window.event || e;
            var el = event.currentTarget;
            $("li.first").dropdown();
            // $(el).addClass("active");
        },
        clickItem: function (e) {
            var event = window.event || e;
            var el = event.currentTarget;
            var url = $(el).attr("data-url");
            $("li.menu").removeClass("active");

            if (url && url != '') {
                $("li.itemActive").removeClass("itemActive");
                $(el).parent("li").addClass("itemActive");
                window.location.hash = "#" + url;
                this.close();
                hxBack.getJson(url, {}, function (data) {
                    $("#content").html(data);

                }, false);
            }
        },
        close: function () {
            if (hx.online) {
                hx.online.websocket.close();
            }
        },
        search: function () {
            var _this = this;

            for (var j = 0; j < _this.permissions.length; j++) {
                var flag = true;
                var permisson = _this.permissions[j];
                permisson.show = 1;
                flag = false;

                var children = permisson.children;

                if (children && children.length > 0) {

                    for (var k = 0; k < children.length; k++) {

                        var child = children[k];

                        child.show = 1;
                        flag = false;
                    }
                }


            }


        }
    }
})




