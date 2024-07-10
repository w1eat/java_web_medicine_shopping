package world.test.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import world.test.entity.History;

/**
 * 历史数据库访问
 */
@Repository
public interface HistoryDao extends BaseMapper<History> {

}
