package com.gavin11.ipersistance.parser;

import com.gavin11.ipersistance.config.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.parser
 * @description ParamMappingTokenHandler <br>
 * @date 2021/4/3 12:40 <br>
 */
public class ParamMappingTokenHandler implements TokenHandler {

    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    @Override
    public String handleToken(String content) {
        parameterMappings.add(buildParamMapping(content));
        return "?";
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }

    private ParameterMapping buildParamMapping(String content) {
        return new ParameterMapping(content);
    }
}
