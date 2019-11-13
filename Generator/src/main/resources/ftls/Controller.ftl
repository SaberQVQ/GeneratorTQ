package ${BasePackageName}${ControllerPackageName};

import ${BasePackageName}${EntityPackageName}.${ClassName}DO;
import ${BasePackageName}${ServicePackageName}.${ClassName}Service;
import ${BasePackageName}${AOPackageName}.${ClassName}SearchAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.MediaType;
import com.pcd.common.utils.WebConversionUtils;

import java.util.List;

/**
 * Author ${Author}
 * Date  ${Date}
 */
@RestController
@RequestMapping(value = "/${EntityName}")
public class ${ClassName}Controller {

    @Autowired
    private ${ClassName}Service ${EntityName}Service;

    /**
     * 根据条件查询数据列表 带分页
     */
    @RequestMapping(value="/listByQuery", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String list(@RequestParam(value = "content") String content, int rows, int page) {
        ${ClassName}SearchAO searchAO = WebConversionUtils.getConditionJavaObject(content, ${ClassName}SearchAO.class);
        return ${EntityName}Service.listByQuery(searchAO, rows, page);
    }

    /**
     * 根据主键查询数据信息
     */
    @RequestMapping(value = "/getByPrimaryKey", method = RequestMethod.GET)
    public String getByPrimaryKey(@RequestParam("content") String content) {
        JSONObject conditionJson = WebConversionUtils.getConditionJson(content);
        String primaryKey = conditionJson.getString("id");
        return ${EntityName}Service.getByPrimaryKey(primaryKey);
    }

    /**
     * 新增数据
     */
    @RequestMapping(value = "/saveSelective", method = RequestMethod.POST)
    public String saveSelective(@RequestParam("content") String content) {
        ${EntityName}DO saveAO = WebConversionUtils.getValueJavaObject(content, ${EntityName}DO.class);
        return ${EntityName}Service.saveSelective(saveAO);
    }

    /**
     * 更新数据
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("content") String content) {
        ${EntityName}DO updateAO = WebConversionUtils.getValueJavaObject(content, ${EntityName}DO.class);
        return ${EntityName}Service.update(updateAO);
    }

    /**
     * 删除数据
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("content") String content) {
        String ids = WebConversionUtils.getConditionJson(content).getString("ids");
        return ${EntityName}Service.delete(ids);
    }

<#--/**
 * 批量新增数据
 */
@RequestMapping(value = "/insertBatch", method = RequestMethod.POST)
public String insertBatch(@RequestBody List<${ClassName}> ${EntityName}s) {
    if (${EntityName}Service.insertBatch(${EntityName}s) > 0) {
        return "success";
    } else {
        return "failed";
    }
}-->

}
