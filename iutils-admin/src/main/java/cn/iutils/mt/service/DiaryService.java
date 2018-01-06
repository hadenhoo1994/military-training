package cn.iutils.mt.service;

import cn.iutils.common.ResultJson;
import cn.iutils.common.utils.StringUtils;
import cn.iutils.mt.dao.DiaryImgDao;
import cn.iutils.mt.entity.DiaryImg;
import cn.iutils.mt.entity.UserInfo;
import cn.iutils.mt.entity.vo.DiaryVO;
import com.mzlion.core.json.fastjson.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.iutils.common.service.CrudService;
import cn.iutils.mt.dao.DiaryDao;
import cn.iutils.mt.entity.Diary;

import java.util.ArrayList;
import java.util.List;

/**
 * 日记表 Service层
 *
 * @author iutils.cn
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class DiaryService extends CrudService<DiaryDao, Diary> {
    @Autowired
    private DiaryDao diaryDao;

    @Autowired
    private DiaryImgDao diaryImgDao;

    /**
     * 提交一个新日记
     * @param userInfo
     * @param diaryVO
     */
    @Transactional(readOnly = false)
    public void putDiary(UserInfo userInfo, DiaryVO diaryVO) {
        //添加新日记
        Diary diary = new Diary();
        diary.setTitle(diaryVO.getTitle());
        diary.setContent(diaryVO.getContent());
        diary.setUserId(Integer.valueOf(userInfo.getId()));
        //保存
        saveOne(diary);
        //保存图片
        if (!StringUtils.isBlank(diaryVO.getImg1())) {
            DiaryImg diaryImg = DiaryImg.newBuilder().imgUrl(diaryVO.getImg1()).diaryId(Integer.valueOf(diary.getId())).build();
            diaryImgDao.insertSelective(diaryImg);
        }
        if (!StringUtils.isBlank(diaryVO.getImg2())) {
            DiaryImg diaryImg = DiaryImg.newBuilder().imgUrl(diaryVO.getImg2()).diaryId(Integer.valueOf(diary.getId())).build();
            diaryImgDao.insertSelective(diaryImg);
        }
        if (!StringUtils.isBlank(diaryVO.getImg3())) {
            DiaryImg diaryImg = DiaryImg.newBuilder().imgUrl(diaryVO.getImg3()).diaryId(Integer.valueOf(diary.getId())).build();
            diaryImgDao.insertSelective(diaryImg);
        }
    }


    /**
     * 获取我的日记
     * @param userId
     * @return
     */
    public List<DiaryVO> getMyDiary(String userId) {
        List<DiaryVO> diaryVOList = new ArrayList<>();
        List<Diary> diaryList = dao.findList(Diary.newBuilder().userId(Integer.valueOf(userId)).build());
        if (diaryList.size()<1){
            return null;
        }
        for (Diary diary : diaryList) {
            DiaryVO diaryVO = new DiaryVO(diary);
            List<DiaryImg> diaryImgs = diaryImgDao.findList(DiaryImg.newBuilder().diaryId(Integer.valueOf(diary.getId())).build());
            diaryVO.setImgs(diaryImgs);
            diaryVOList.add(diaryVO);
        }
        return diaryVOList;
    }
}
