function hxCharts() {
    this.id = '';
    this.color = ['#1ab394', '#79d2c0', '#bababa', '#d3d3d3', '#27727B',
        '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
        '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'];
    // 全图默认背景
    // backgroundColor: 'rgba(0,0,0,0)',

    this.chart = {};
    this.theme = 'default',
        this.init = function (id, theme) {
            var _this = this;
            this.theme = theme || 'light';
            this.id = id;
            this.chart = echarts.init(document.getElementById(id), this.theme);
            //全局echarts自适应
            window.addEventListener("resize", function () {
                _this.chart.resize();
            });
        };
    this.setOption = function (option, _opts) {
        option.color = this.color;
        if (_opts) {
            $.extend(option, _opts);
        }
        this.chart.setOption(option, true);
    };
    this.pie = function (id, title, name, legendData, data, _opts) {
        this.init(id);
        var option = {
            title: {
                text: title,
                //subtext: '纯属虚构',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: legendData
            },
            series: [
                {
                    name: name,
                    type: 'pie',
                    radius: '60%',
                    center: ['50%', '60%'],
                    data: data,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        },
                        normal: {
                            label: {
                                show: true,
                                formatter: '{b} : {c} ({d}%)'
                            },
                            /* 是否展示索引线条 */
                            labelLine: {
                                normal: {
                                    show: true
                                }
                            }
                        }
                    },

                }
            ]
        };
        this.setOption(option, _opts);
    };


    this.bar = function (id, title, name, legendData, data, _opts) {
        this.init(id);
        var option = {
            title: {
                text: title,
                //subtext: '纯属虚构',
                x: 'center'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            toolbox: {
                show: true,
                feature: {
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: false},//隐藏还原按钮
                    saveAsImage: {}
                },
                right: '30'
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: legendData,
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: name
                }
            ],
            series: [
                {
                    type: 'bar',
                    data: data,
                    barWidth: 30,//柱图宽度
                    barMaxWidth: 30
                }
            ]
        };
        this.setOption(option, _opts);
    };

    this.linesAndBars = function (id, title, legendData, xdata, ydata, name, _opts) {
        this.init(id);
        var option = {
            title: {
                text: title
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                orient: 'horizontal',
                data: legendData
            },
            toolbox: {
                show: true,
                feature: {
                    mark: {show: true},
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '10%',
                containLabel: true
            },
            dataZoom: [
                {
                    show: true,
                    start: 40,
                    end: 100,
                    startValue: 0,
                    endValue: 100
                }

            ],
            xAxis: [
                {
                    type: 'category',
                    data: xdata,
                    axisLabel: {
                        interval: 0,//横轴信息全部显示  
                        rotate: -25,//-25度角倾斜显示  
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: name,
                    scale: false	//y轴是否从0开始，如果设置为true，会有bug出现
                }
            ],
            series: ydata
        };
        this.setOption(option, _opts);
    };

    this.line = function (id, title, name, lableData, data, _opts) {
        this.init(id);
        var option = {
            color: this.color,
            title: {
                text: title,
                //subtext: '纯属虚构',
                x: 'center'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow',        // 默认为直线，可选为：'line' | 'shadow'
                    animation: false
                }
            },
            toolbox: {
                show: true,
                feature: {
                    restore: {show: false},//隐藏还原按钮
                    saveAsImage: {
                        type: 'jpeg'
                    }
                },
                right: '30'
            },
            xAxis: [
                {
                    type: 'category',
                    data: lableData
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: name,
                    scale: false
                }
            ],
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            series: [{
                data: data,
                type: 'line',
                label: {
                    normal: {
                        show: false,
                    }
                },
                markPoint: {
                    symbolSize: '40',
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
            }]
        };

        this.setOption(option, _opts);
    };
    //环形图
    this.annulus = function (id, title, name, data, total, percentage, _opts) {
        this.init(id);
        var option = {
            title: {
                text: title,
                subtext: '',
                left: 'center',
                textStyle: {
                    fontsize: '5px'
                }
            },
            /* tooltip: {
                 trigger: 'item',
                 formatter: "{a} <br/>{b}: {c} ({d}%)"
             },*/
            series: [
                {
                    name: name,
                    type: 'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: true,
                            position: 'center',
                            //圆形内展示数据的值
                            textStyle: {fontSize: "12"},
                            formatter: function () {
                                return percentage + "%\n" + data.value + " kWh"
                            }
                        },
                        emphasis: {
                            show: false,
                            textStyle: {
                                fontSize: '15',
                                fontWeight: 'bold'
                            }
                        }

                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: [
                        data, total
                    ]
                }
            ]
        };
        this.setOption(option, _opts);
    };

}
    