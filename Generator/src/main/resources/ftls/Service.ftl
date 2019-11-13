package ${BasePackageName}${ServicePackageName};

import ${BasePackageName}${DaoPackageName}.${ClassName}Dao;
import ${BasePackageName}${EntityPackageName}.${ClassName}DO;
import ${BasePackageName}${AOPackageName}.${ClassName}SearchAO;
import ${BasePackageName}${DTOPackageName}.${ClassName}DTO;
${InterfaceImport}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pcd.common.utils.WebJsonUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.TqToolKit;

import java.util.ArrayList;
import java.util.List;

/**
 * Author ${Author}
 * Date  ${Date}
 */
@Service
public class ${ClassName}Service${Impl}{

    @Autowired
    private ${ClassName}Dao ${EntityName}Dao;

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    @Override
    public String listByQuery(${ClassName}SearchAO ${EntityName}SearchAO, int rows, int page){
        List ${Bracket}${ClassName}DTO> result = new ArrayList<>();

        PageHelper.startPage(page,rows);

        List ${Bracket}${ClassName}DO> businessDOList = ${EntityName}Dao.listByQuery(${EntityName}SearchAO);

        PageInfo${Bracket}${ClassName}DO> pageInfo = new PageInfo${Bracket}${ClassName}DO>(businessDOList);
        Integer total = Long.valueOf(pageInfo.getTotal()).intValue();

        if (businessDOList.size() > 0) {
            result = TqToolKit.copyList(businessDOList, ${ClassName}DTO.class);
        }

        return WebJsonUtils.successReturn(result, total);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    @Override
    public String getByPrimaryKey(String primaryKey){
        ${ClassName}Dao ${EntityName}DO = ${EntityName}Dao.getByPrimaryKey(primaryKey);
        ${EntityName}DTO result = new ${EntityName}DTO();
        TqToolKit.copyAttribute(${EntityName}DO, result);
        return WebJsonUtils.successReturnSingle(result);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveSelective(${EntityName}DO saveDO){
        ${EntityName}Dao.saveSelective(primaryKey);
        return WebJsonUtils.successReturn();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String update(${EntityName}DO updateDO){
        ${EntityName}Dao.update(primaryKey);
        return WebJsonUtils.successReturn();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String delete(String ids){
        List<String> primaryKeys = Arrays.asList(ids.split(","));
        for (String id : primaryKeys) {
            if (!TqToolKit.isBlank(id)) {
                ${EntityName}Dao.delete(primaryKey);
            }
        }
    }
    <#--
    ${Override}
    public int insertBatch(List<${ClassName}> ${EntityName}s){
        return ${EntityName}Dao.insertBatch(${EntityName}s);
    }
    -->
}
