package world.test.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import world.test.dao.GoodsDao;
import world.test.entity.Goods;
import world.test.utils.Assert;
import world.test.utils.BeanUtil;
import world.test.utils.VariableNameUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 药品服务类
 */
@Service
public class GoodsService extends BaseService<Goods> {

    @Autowired
    protected GoodsDao GoodsDao;

    @Override
    public List<Goods> query(Goods o) {
        QueryWrapper<Goods> wrapper = new QueryWrapper();
        if (Assert.notEmpty(o)) {
            Map<String, Object> bean2Map = BeanUtil.bean2Map(o);
            for (String key : bean2Map.keySet()) {
                if (Assert.isEmpty(bean2Map.get(key))) {
                    continue;
                }
                wrapper.eq(VariableNameUtils.humpToLine(key), bean2Map.get(key));
            }
        }
        return goodsDao.selectList(wrapper);
    }

    @Override
    public List<Goods> all() {
        return query(null);
    }

    @Override
    public Goods save(Goods o) {
        if (goodsDao.selectById(o.getId()) != null) {
            goodsDao.updateById(o);
        } else {
            goodsDao.insert(o);
        }
        return goodsDao.selectById(o.getId());
    }

    @Override
    public Goods get(Serializable id) {
        return goodsDao.selectById(id);
    }

    @Override
    public int delete(Serializable id) {
        return goodsDao.deleteById(id);
    }

    public Map<String, Object> getGoodsList(String nameValue, Integer page) {

        List<Goods> goodsList = null;
        Map<String, Object> map = new HashMap<>(4);
        if (Assert.notEmpty(nameValue)) {
            goodsList = goodsDao.selectList(new QueryWrapper<Goods>().
                    eq("user_name", nameValue)
                    .last("limit " + (page - 1) * 9 + "," + page * 9));
        } else {
            goodsList = goodsDao.selectList(new QueryWrapper<Goods>()
                    .last("limit " + (page - 1) * 9 + "," + page * 9));
        }
        map.put("goodsList", goodsList);
        map.put("size", goodsList.size() < 9 ? 1 : goodsList.size() / 9 + 1);
        return map;
    }

    public Map<String, Object> getGoodsListTotal(String nameValue) {
        List<Goods> goodsList = null;
        Map<String, Object> map = new HashMap<>(4);
        if (Assert.notEmpty(nameValue)) {
            goodsList = goodsDao.selectList(new QueryWrapper<Goods>().
                    eq("user_name", nameValue)
            );
        } else {
            goodsList = goodsDao.selectList(new QueryWrapper<Goods>()
            );
        }

        map.put("goodsList", goodsList);
        map.put("size", goodsList.size() < 9 ? 1 : goodsList.size() / 9 + 1);
        return map;
    }
}