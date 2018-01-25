package cn.iutils.mt.service;

import cn.iutils.common.Page;
import cn.iutils.mt.dao.UserInfoDao;
import cn.iutils.mt.entity.vo.MonentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.iutils.common.service.CrudService;
import cn.iutils.mt.dao.MonentDao;
import cn.iutils.mt.entity.Monent;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态表 Service层
 *
 * @author iutils.cn
 * @version 1.0
 */
@Service
@Transactional(readOnly = true)
public class MonentService extends CrudService<MonentDao, Monent> {
    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * 首页的page
     *
     * @param monentPage
     * @return
     */
    public List<MonentVO> getFirstPage(Page<MonentVO> monentPage) {
        //查询心情
        Page page =  new Page<Monent>((monentPage.getPageNo()), monentPage.getPageSize());
        page.setOrderBy("a.create_date desc");
        List<Monent> monentList = findPage(page,new Monent());
        List<MonentVO> monentVOS = new ArrayList<>();
        if (monentList.size() < 1) {
            return null;
        }
        for (Monent monent : monentList) {
            MonentVO monentVO = new MonentVO(monent);
            monentVO.setUserInfo(userInfoDao.get(monent.getUserId().toString()));
            monentVOS.add(monentVO);
        }
        return monentVOS;
    }
}
