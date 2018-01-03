package cn.iutils.mt.entity.vo;

import cn.iutils.mt.entity.DiaryImg;

/**
* 日记图片表
* @author iutils.cn
* @version 1.0
*/
public class DiaryImgVO extends DiaryImg {

    private static final long serialVersionUID = 1L;

    public DiaryImgVO(DiaryImg entity){
        super();
        setId(entity.getId());
        setDiaryId(entity.getDiaryId());
        setImgUrl(entity.getImgUrl());
        setCreateBy(entity.getCreateBy());
        setCreateDate(entity.getCreateDate());
        setUpdateBy(entity.getUpdateBy());
        setUpdateDate(entity.getUpdateDate());
        setRemarks(entity.getRemarks());
        setStatus(entity.getStatus());
    }

}
