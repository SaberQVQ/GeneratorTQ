<script type="text/javascript">
    function ACTION(){
        var p=this;
        var SearchForm;	//定义查询表单
        var listObj;
        var data_option_list = ${listByQueryUrl};//button/listByQuery
        var data_option_remove = ${deleteByPrimaryKeyUrl};//button/deleteButtonByPrimaryKey

        //窗口缩放尺寸设置
        this.resize=function(e,info){
            p.find(".datalist").height(p.WRAPER.height()-p.find(".pageTopBox").outerHeight(true));
            listObj.datagrid("resize");
        };

        //卸载时销毁方法
        this.DESTROY=function(){
            $(p.WINDOW).unbind("WINDOW_RESIZE",p.resize);
        };

        //加载后执行
        this.READY=function(){
            //绑定窗体缩放事件
            $(p.WINDOW).bind("WINDOW_RESIZE",p.resize);

            //表单处理
            SearchForm=new formDeal(p.find("#searchForm"));//创建表单
            SearchForm.parse(function(){
                SearchForm.bindData(p.CONDITION);//绑定默认查询条件
            });//格式化表单

            listObj=p.find("#tt");
            //表格例子------------------------------------------EasyUI组件
            var clumoption= ${ColumnOption};

            //     [[
            //     {field:'id',title:'编号',width:40,fixed:true,align:"center",checkbox:true},
            //     {field:'key',title:'操作码',width:80,resizable:true,align:"center", sortable: "true"},
            //     {field:'value',title:'操作名称',width:80,resizable:true,align:"center", sortable: "true"}
            // ]];

            //默认查询条件
            var searchParam=formatPostParam(creatPostParam(${ListName}));//"ROLE_LIST"

            listObj.datagrid({
                url:data_option_list,
                queryParams:searchParam, //附加参数对象
                loadFilter:formatHostJson,  //格式过滤器
                fit:true,
                showFooter:true,//显示总计
                rownumbers:true,//行号
                singleSelect:false,//单选
                remoteSort: false,//本地排序
                fitColumns:true,//宽度自适应
                striped:true,//条纹
                pagination:true,//分页
                pageSize:20,
                pageList:[20,30,40,50,60,100],
// 			onDblClickRow:dataGridClick,
                columns:clumoption
            }).datagrid('getPager').pagination({  //设置显示列切换按钮
                buttons:[{
                    iconCls:'icon-clumconfig',
                    handler:function(e){ClumChocie(listObj,$(this),e)}
                }]
            });

            //安装按钮点击事件
            p.find(".actionBox a").bind("click",butAction);

            //绑定查询按钮点击
            p.find("#searchButton").bind("click",StartSearch);
            p.find("#clearButton").bind("click",function(){
                SearchForm.clear();//清除查询表单条件
                StartSearch();	//重新查询
            });
            //更新一下尺寸
            p.resize();
        };

        function butAction(){
            var but=$(this);
            switch(but.attr("id")){
                case "addNew":
                    var targetPage=${AddPage};// "/PAGES/TP_Pages/TP_button_form.html"
                    WINDOW.open({"title":"新增","width":800,"height":200,"url":targetPage,"lock":true},{"parentId":p.WINID},$(this));
                    break;
                case "editSelected":
                    var ids=listObj.getDatagridSelectedIds("id");
                    if(!ids){
                        $.alert("请选择!");
                        break;
                    }
                    if(ids.split(",").length>1){
                        $.alert("仅能编辑一项!");
                        break;
                    }
                    var idParam = {"id":ids};
                    var targetPage=${EditPage};//"PAGES/TP_Pages/TP_button_form.html"
                    WINDOW.open({"title":"编辑","width":800,"height":200,"url":targetPage,"lock":true},$.extend({"parentId":p.WINID},idParam),$(this));
                    break;
                case "deleteSelected":
                    dealRows(${DelEvent},"确定删除所选按钮吗?");//"DELETE_ROLE"
                    break;
            }
        }

        //行处理
        function dealRows(pevent,msg){
            var ids=listObj.getDatagridSelectedIds("id");
            if(!ids){
                $.alert("请选择!");
                return;
            }
            var next=function(type){
                if(type){
                    updateRow(pevent,ids);
                }
            };
            $.confirm(msg,next)

        }

        function updateRow(pevent,ids){
            var Loader=new AJAXObj();
            $(Loader).one("JSON_LOADED",p.refresh);
            var postdata=creatPostParam(pevent,{"ids":ids});
            if(pevent == "DELETE_ROLE"){
                Loader.Post(data_option_remove, postdata);
            }
        }

// 	//datagrid行点击
// 	function dataGridClick(rowIndex,rowData){
// 		var tr=$.getDatagridTr(listObj,rowIndex);
// 		WINDOW.open({"title":"查看SIM卡信息","width":800,"height":0.8,"url":"pages/Market_sim_detail.html","lock":true},$.extend({"parentId":p.WINID},rowData),tr);
// 	}

        //查询
        function StartSearch(){
            if(SearchForm.check()){ //数据校验
                var searchCondition=SearchForm.getData();//获取表单条件
                //刷新DataGrid
                var searchParam=formatPostParam(creatPostParam("ROLE_LIST",searchCondition));
                listObj.datagrid("reload",searchParam) //附加参数对象);
            }
        }

        //子窗口刷新父页面组件
        this.refresh=function(){
            listObj.datagrid("reload");
        }
    }
</script>

<!--内容部分start-->
<div class="pageTopBox">
    <div id="dataGridActionBox"  style="padding:3px" >
        <!--按钮-->
        <div style="margin-bottom:3px" class="actionBox">
            <a class="but-small but-green" id="addNew"><i class="fa fa-user-plus"></i>新增</a>
            <a class="but-small but-green" id="editSelected"><i class="fa fa-edit"></i>编辑</a>
            <a class="but-small but-orange" id="deleteSelected"><i class="fa fa-remove"></i>删除</a>
        </div>
    </div>
    <!--查询-->
    <div class="searchBox">
        <form id="searchForm">
            <table border="0" cellspacing="0" cellpadding="3" width="100%">
                <tr>
                    <td><ul>
                            <li> <i>condition1:</i>
                                <input  type="text" name="key" style="width:180px" />
                            </li>
                            <li> <i>condition2:</i>
                                <input  type="text" name="value" style="width:180px" />
                            </li>
                        </ul>
                        <div class="cl"></div></td>
                    <td width="93" align="right" valign="top" nowrap="nowrap">
                        <a  id="searchButton" class="but-small but-red"><i class="fa fa-search"></i> 查询</a>
                        <a  id="clearButton" class="but-small but-compact but-yellow"><i class="fa fa-undo"></i></a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<!--表格-->
<div class="datalist">
    <div id="tt"> </div>
</div>
<!--内容部分end-->