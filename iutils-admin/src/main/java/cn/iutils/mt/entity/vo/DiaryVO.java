package cn.iutils.mt.entity.vo;

import cn.iutils.mt.entity.Diary;

/**
 * 日记表
 *
 * @author iutils.cn
 * @version 1.0
 */
public class DiaryVO extends Diary {

    private static final long serialVersionUID = 1L;

    public DiaryVO(Diary entity) {
        super();
        setId(entity.getId());
        setCreateBy(entity.getCreateBy());
        setCreateDate(entity.getCreateDate());
        setUpdateBy(entity.getUpdateBy());
        setUpdateDate(entity.getUpdateDate());
        setRemarks(entity.getRemarks());
        setStatus(entity.getStatus());
    }

}
