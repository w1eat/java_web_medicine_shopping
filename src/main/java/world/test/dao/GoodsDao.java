package world.test.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import world.test.entity.Goods;


/**
 * 用户数据库访问
 */
@Repository
public interface GoodsDao extends BaseMapper<Goods>  {

}
