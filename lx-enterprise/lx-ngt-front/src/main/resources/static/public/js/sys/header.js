//@ sourceURL=/public/js/sys/header.js
hx.header = new Vue({
    el: '#header',
    data: {
        companyName: '能管通',
        curName: '',
        curLoginName: '',
        curPhoto: '',
        alarmNum: '0'
    },
    //钩子函数，当所有DOM挂载在页面上时，加载此方法，相当于window.onload=function(){}
    mounted: function () {


    },
    methods: {
        logout: function () {
            hx.ssoGet("/user/loginout", {}, function (res) {
                if (res.code == "10000") {
                    deleteCookie("NGT_TOKEN");
                    window.open(hx.ctx + "/login", '_self');
                }
            })
        },
        index: function () {
            window.location.hash = '';
            hxBack.getJson('index', {}, function (data) {
                $("#content").html(data);

            });
        },
        alarm: function () {
            window.location.hash = '#monitor/alarm';
            $("li.itemActive").removeClass("itemActive");
            $("a[data-url='monitor/alarm']").parent("li").addClass("itemActive");
            hxBack.getJson('monitor/alarm', {}, function (data) {
                $("#content").html(data);
            });
        },
        todo: function () {
            window.location.hash = '#plan/todo';
            hxBack.getJson('plan/todo', {}, function (data) {
                $("#content").html(data);

            });
        },
        center: function () {
            window.open(centerUrl + "#/Userhome");
        }
    }
})




