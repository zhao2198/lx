//@ sourceURL=/public/js/sys/equipmentType.js
hx.equipmentType = new Vue({
    el: '#ngt',
    data: {
        list: [],
        page: {
            total: 0,//总页数
            size: 10,//每页显示条目个数不传默认10
            curPage: 1//当前页码

        },
        equipmentTypeVO: {},
        equipmentTypeName: '',
        showList: true,
        action: '',//add update
        actionName: '',
        allCheck: false,
        showBtn: false
    },
    //钩子函数，当所有DOM挂载在页面上时，加载此方法，相当于window.onload=function(){}
    mounted: function () {
        //需要用$nextTick来保证所有节点挂载后才执行方法
        this.search();

    },
    updated: function () {
        this.tree();
    },
    methods: {
        search: function () {
            var _this = this;
            hx.getJson('/equipment_type/list', {}, function (result) {
                _this.list = result.data;
                if (_this.list.length < 4) {
                    _this.showBtn = true;
                }
                _this.list.forEach(function (val, index) {
                    if (val.tid.length > 2) {
                        _this.$set(val, 'className', "gradeA odd treegrid-" + val.tid + " treegrid-parent-" + val.parentId);
                    } else {
                        _this.$set(val, 'className', "gradeA odd treegrid-" + val.tid);
                    }

                });


            });
        },
        tree: function () {
            $('#tree').treegrid();
        },
        init: function () {
            var url = '/equipment_type/init';
            var _this = this;
            hx.postJson(url, {}, function (result) {
                if (result.code == '10000') {
                    _this.showBtn = false;
                    hx.success("初始化成功！");
                }
                _this.toList();
            });
        },
        initAdd: function (item) {
            this.action = hx.add;
            this.actionName = hx.addName;
            this.toAdd();

            var equipmentTypeVO = item;
            if (equipmentTypeVO) {
                var equipmentType = {};
                equipmentType.id = '';
                equipmentType.name = '';
                equipmentType.type = equipmentTypeVO.type;
                equipmentType.parentId = equipmentTypeVO.tid;
                equipmentType.remarks = '';
                this.equipmentTypeVO = equipmentType;
            }
        },
        initUpdate: function (id) {
            this.action = hx.update;
            this.actionName = hx.updateName;
            this.toAdd();
            this.getEquipmentType(id);

        },
        getEquipmentType: function (id) {
            var _this = this;
            hx.getJson('/equipment_type/info/' + id, {}, function (result) {
                //_this.companyVO = result.data;
                var equipmentTypeVO = result.data;
                if (equipmentTypeVO) {
                    var equipmentType = {};
                    equipmentType.id = equipmentTypeVO.id;
                    equipmentType.name = equipmentTypeVO.name;
                    equipmentType.type = equipmentTypeVO.type;
                    equipmentType.parentId = equipmentTypeVO.parentId;
                    equipmentType.remarks = equipmentTypeVO.remarks;
                    _this.equipmentTypeVO = equipmentType;

                }

            });
        },
        cancle: function () {
            this.showList = true;
        },
        save: function () {
            var ok = $('#form').parsley().isValid({force: true});
            if (!ok) {
                return;
            }
            var url = '/equipment_type/create';
            var _this = this;
            if (this.action == 'update') {
                url = '/equipment_type/update';
                hx.putJson(url, this.equipmentTypeVO, function (result) {
                    hx.updateSuccess();
                    _this.toList();
                });
            } else {
                hx.postJson(url, this.equipmentTypeVO, function (result) {
                    hx.saveSuccess();
                    _this.toList();
                });
            }
        },
        deleteItem: function (id) {
            var _this = this;
            hx.deleteParamJson("/equipment_type/delete/" + id, {}, function (result) {
                hx.deleteSuccess();
                _this.toList();
            });


        },
        toList: function () {
            this.showList = true;
            this.equipmentTypeVO = {};
            this.search();
        },
        toAdd: function () {
            this.showList = false;
            this.equipmentTypeVO = {};
            hx.reset();
        }
    }
})


