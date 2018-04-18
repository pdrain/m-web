package cn.meilituibian.web.mapper;

import cn.meilituibian.web.domain.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {
    int addProject(Project project);

    List<Project> projectList(@Param("parentId") Long parentId);
}
