package ${BasePackageName}${businessType}.${EntityName}.${ControllerPackageName};

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ${BasePackageName}${businessType}.${EntityName}.${ClassName}Service;
import com.tp.base.action.BaseAction;

/**
 * Author ${Author}
 * Date  ${Date}
 */
@Controller
@RequestMapping(value = "/${businessType}/${EntityName}")
public class ${ClassName}Action extends BaseAction<${ClassName}Service<Map<String,Object>> , Map<String,Object>>{

    @RequestMapping
    public String index(ModelMap map){
        map.put("now", new Date());
        return "${businessType}/${EntityName}/${EntityName}" ;
    }

}