package ${BasePackageName}${businessType}.${EntityName}.${ServicePackageName};


import java.util.Map;
import org.springframework.stereotype.Service;
import com.tp.base.service.BaseServiceImpl;
${InterfaceImport}
import ${BasePackageName}${businessType}.${EntityName}.${InterfacePackageName}.${ClassName}Service;
import ${BasePackageName}${businessType}.${EntityName}.${DaoPackageName}.${ClassName}Mapper;

/**
 * Author ${Author}
 * Date  ${Date}
 */
@Service
public class ${ClassName}Service${Impl} extends BaseServiceImpl<${ClassName}Mapper, Map<String,Object>> implements ${ClassName}Mapper<Map<String,Object>>{

}
