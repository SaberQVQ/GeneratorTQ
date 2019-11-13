package ${BasePackageName}${InterfacePackageName};

import ${BasePackageName}${EntityPackageName}.${ClassName}DO;
import ${BasePackageName}${AOPackageName}.${ClassName}SearchAO;

import java.util.List;

/**
 * Author ${Author}
 * Date  ${Date}
 */
public interface ${ClassName}Service {

    public String listByQuery(${ClassName}SearchAO ${EntityName}SearchAO, int rows, int page);

    public String getByPrimaryKey(String primaryKey);

    public String saveSelective(${EntityName}DO saveDO);

    public String update(${EntityName}DO updateDO);

    public String delete(String ids);

    <#--

    public int insertBatch(List<${ClassName}> ${EntityName}s);

 -->

}
