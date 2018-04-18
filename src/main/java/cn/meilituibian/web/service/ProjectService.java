package cn.meilituibian.web.service;

import cn.meilituibian.web.domain.Project;
import cn.meilituibian.web.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public List<Project> projectList(Long parentId) {
        return projectMapper.projectList(parentId);
    }
}
