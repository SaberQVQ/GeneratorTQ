<script type="text/javascript">
    function ACTION() {
        var p = this;
        var SubmitForm; //表单对象
        var data_option_save = "{'submitUrl':'${EntityName}/saveSelective'}";
        var data_option_update = "{'url':'${EntityName}/getByPrimaryKey','submitUrl':'${EntityName}/updateByPrimaryKey'}";
        var event_json = ${Events};

        //窗口缩放尺寸设置
        this.resize = function (e, info) {
            p.find(".formBox").setRealSize(null, p.WRAPER.height() - p.find(".submitButs").outerHeight(true));
        };

        this.DESTROY = function () {
            $(p.WINDOW).unbind("WINDOW_RESIZE", p.resize);
            if (SubmitForm) {
                SubmitForm.destroy();
                SubmitForm = null;
            }
        };

        this.READY = function () {
            //绑定窗体缩放事件
            $(p.WINDOW).bind("WINDOW_RESIZE", p.resize);
            //表单格式化处理
            var formOption = {"event": event_json.add, "addNew": p.CONDITION["id"] ? false : true};
            var formCondition = {"id": p.CONDITION["id"]};

            var $form = p.find("#Form");
            if (!formOption["addNew"]) {
                //修改
                $form.attr("data-option", data_option_update);
            } else {
                //新增
                $form.attr("data-option", data_option_save);
            }
            // if(p.CONDITION["id"]){//编辑情况禁止修改某些
            //     p.find("[name=value]").readonly(true);
            // }
            SubmitForm = p.find("#Form").form(formOption, formCondition);
            $(SubmitForm).bind("FORM_LOADED", function () {
                //按装按钮
                p.find("#saveButton").beclick(saveForm);
            }).bind("FORM_BEFORE_SAVE", function () {
                //提交前锁按钮
                p.find("#saveButton").addClass("disable");
            }).bind("FORM_SAVED", function (e, jsonBk) {
                $.message("保存成功！", 2000);
                //刷新父窗体--
                WINDOW.run(p.CONDITION["parentId"], "refresh"); //执行父窗体refesh方法
                WINDOW.close(p.WINID); //关闭窗口
            }).bind("FORM_SAVE_ERROR", function () {
                //解按钮
                p.find("#saveButton").removeClass("disable");
            });
            SubmitForm.install();
            //表单end------------------------------------------
            p.find("a#cancelButton").beclick(function () {
                WINDOW.close(p.WINID);
            });

            //更新一下尺寸
            p.resize();
        };

        function saveForm() {
            if ($(this).hasClass("disable")) {
                return;
            }
            if (!SubmitForm.form.check()) {
                $.alert("信息填写不完整，请检查。");
                return;
            }
            $(SubmitForm).one("FORM_SAVED", function () {
                $.message("保存成功！", 2000);
                //刷新父窗体--
                WINDOW.run(p.CONDITION["parentId"], "refresh");//执行父窗体refesh方法
                WINDOW.close(p.WINID);//关闭窗口
            });
            SubmitForm.submit();
        }

    }

</script>
<!--内容部分start-->
<!--表单-->

<div class="formBox">
    <form id="Form">
        <table width="100%" border="0" cellspacing="0" cellpadding="5" class="formtab">
            <tr>
                <th colspan="4" class="formTitle">按钮信息</th>
            </tr>
            <tr>
                <th width="80" align="right"><i>condition1</i></th>
                <td><input name="key" type="text" maxlength="40" style="width: 220px" class="check_must"/></td>
                <th width="80" align="right"><i>condition2</i></th>
                <td><input name="value" type="text" maxlength="40" style="width: 220px" class="check_must"/></td>
            </tr>
        </table>
    </form>
</div>
<!--提交按钮-->
<div class="submitButs"><a id="saveButton" class="but-normal but-orange"><i class="fa fa-save"></i>保存</a> <a
            id="cancelButton" class="but-normal but-yellow"><i class="fa fa-reply"></i>取消</a></div>
<!--内容部分end-->
