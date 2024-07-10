package world.test.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import world.test.entity.Pageview;

/**
 * 分页数据库访问
 */
@Repository
public interface PageviewDao extends BaseMapper<Pageview> {

}
