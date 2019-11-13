package ${BasePackageName}${DaoPackageName};

import ${BasePackageName}${EntityPackageName}.${ClassName}DO;
import ${BasePackageName}${AOPackageName}.${ClassName}SearchAO;

import java.util.List;

/**
 * Author ${Author}
 * Date  ${Date}
 */
public interface ${ClassName}Dao {

    public List${Bracket}${ClassName}DO> listByQuery(${ClassName}SearchAO ${EntityName}SearchAO);



    <#--public ${ClassName} get(String id);

    public List<${ClassName}> findList(${ClassName} ${EntityName});

    public List<${ClassName}> findAllList();

    public int insert(${ClassName} ${EntityName});

    public int insertBatch(List<${ClassName}> ${EntityName}s);

    public int update(${ClassName} ${EntityName});

    public int delete(${ClassName} ${EntityName});-->

}