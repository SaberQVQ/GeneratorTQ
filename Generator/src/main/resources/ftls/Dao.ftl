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

    public ${ClassName}DO getByPrimaryKey(String primaryKey);

    public int insertSelective(${ClassName}DO saveDO);

    public int update(${ClassName}DO saveDO);

    public int delete(String primaryKey);

}