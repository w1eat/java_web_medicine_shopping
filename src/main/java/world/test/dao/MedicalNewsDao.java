package world.test.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import world.test.entity.MedicalNews;

/**
 * 咨询数据库访问
 */
@Repository
public interface MedicalNewsDao extends BaseMapper<MedicalNews> {

}