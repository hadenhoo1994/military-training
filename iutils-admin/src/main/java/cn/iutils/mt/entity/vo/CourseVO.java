package cn.iutils.mt.entity.vo;

import cn.iutils.mt.entity.Course;
import org.springframework.format.annotation.DateTimeFormat;
import cn.iutils.sys.entity.DataEntity;
import java.util.Date;

/**
* 排课表
* @author iutils.cn
* @version 1.0
*/
public class CourseVO extends Course {

    private static final long serialVersionUID = 1L;

    public CourseVO(){super();}

    public CourseVO(Course entity){
        super();
        setId(entity.getId());
        setBattalionId(entity.getBattalionId());
        setCompanyId(entity.getCompanyId());
        setPlatoonId(entity.getPlatoonId());
        setPlatoonName(entity.getPlatoonName());
        setProjectId(entity.getProjectId());
        setProjectName(entity.getProjectName());
        setWeek(entity.getWeek());
        setDays(entity.getDays());
        setClasses1(entity.getClasses1());
        setClasses2(entity.getClasses2());
        setClasses3(entity.getClasses3());
        setClasses4(entity.getClasses4());
        setClasses5(entity.getClasses5());
        setClasses6(entity.getClasses6());
        setClasses7(entity.getClasses7());
        setClasses8(entity.getClasses8());
        setClasses9(entity.getClasses9());
        setClasses10(entity.getClasses10());
        setClasses11(entity.getClasses11());
        setCreateBy(entity.getCreateBy());
        setCreateDate(entity.getCreateDate());
        setUpdateBy(entity.getUpdateBy());
        setUpdateDate(entity.getUpdateDate());
        setRemarks(entity.getRemarks());
        setStatus(entity.getStatus());
    }

}
