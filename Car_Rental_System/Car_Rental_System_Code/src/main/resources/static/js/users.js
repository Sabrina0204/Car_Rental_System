
    $('#table1').bootstrapTable({
        url: 'users', //接口名称
        method: 'get',
        contentType:"application/x-www-form-urlencoded",//method为post时必须加上这个,否则接收不到参数
        dataType: 'json',
        queryParams:function queryParams(params) {   //设置查询参数

            var param = {

                pageSize: params.size,   //每页多少条数据
                pageNumber: params.current, //从第几条数据开始
                searchText: params.search  //搜索框
            };
            return param;
        },
        height: 560,
        toolbar: '#toolbar',                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        pagination: true,                   //是否显示分页（*）
        maintainSelected: true,             //设置为 true 在点击分页按钮或搜索按钮时，将记住checkbox的选择项
        sidePagination: "server",          //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [5, 10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: true,                //设置为 true启用 全匹配搜索，否则为模糊搜索
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: "id",                  //每一行的唯一标识，一般为主键列\
        sortStable: true,
        columns: [
            {checkbox: true},
            {field: 'id', title: '药品编码'},
            {field: 'username', title: '药品名称'},
            {field: 'password', title: '生产批号'},
            {field: 'age', title: '生产厂家'},
            {field: 'gender', title: '危险级别'},
            {field: 'address', title: '包装'}
        ]
    });