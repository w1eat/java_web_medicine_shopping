package world.test.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.test.dao.MedicalNewsDao;
import world.test.entity.MedicalNews;
import world.test.utils.Assert;
import world.test.utils.BeanUtil;
import world.test.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 咨询服务类
 */
@Service
public class MedicalNewsService extends BaseService<MedicalNews> {

    @Autowired
    protected MedicalNewsDao medicalNewsDao;

    @Override
    public List<MedicalNews> query(MedicalNews o) {
        QueryWrapper<MedicalNews> wrapper = new QueryWrapper();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return medicalNewsDao.selectList(wrapper);
    }

    @Override
    public List<MedicalNews> all() {
        return query(null);
    }

    @Override
    public MedicalNews save(MedicalNews o) {
        if (Assert.isEmpty(o.getId())) {
            medicalNewsDao.insert(o);
        } else {
            medicalNewsDao.updateById(o);
        }
        return medicalNewsDao.selectById(o.getId());
    }

    @Override
    public MedicalNews get(Serializable id) {
        return medicalNewsDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return medicalNewsDao.deleteById(id);
    }
}