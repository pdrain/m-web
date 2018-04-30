package cn.meilituibian.web.service;

import cn.meilituibian.web.domain.Project;
import cn.meilituibian.web.mapper.ProjectMapper;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public List<Project> projectList(Long parentId) {
        return projectMapper.projectList(parentId);
    }

    public void addProject(Project project) {
        project.setOperation(processJson(project.getOperation()));
        project.setTreatment(processJson(project.getTreatment()));
        project.setRecure(processJson(project.getRecure()));

        project.setAdvantage(processList(project.getAdvantage()));
        project.setDisadvantage(processList(project.getDisadvantage()));
        projectMapper.addProject(project);
    }

    private String processJson(String text) {
        if (StringUtils.isEmpty(text)) {
            return text;
        }
        String[] items = text.split("\\$");
        JSONArray array = new JSONArray();
        for (String item: items) {
            JSONObject object = new JSONObject();
            int index = item.indexOf(":");
            if (index == -1) {
                object.put(item, "");
            } else {
                String key = item.substring(0, index);
                String value = item.substring(index + 1);
                object.put(key, value);
            }
            array.put(object);
        }
       return array.toString();
    }

    private String processList(String text) {
        if (StringUtils.isEmpty(text)) {
            return text;
        }
        String[] items = text.split("\\$");
        String retVal = "[{0}]";
        StringBuilder content = new StringBuilder();
        for (String item: items) {
            content.append("\"" + item + "\"" );
            content.append(",");
        }
        return MessageFormat.format(retVal, content.substring(0, content.length() - 1).toString());
    }
}
