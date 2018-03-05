package sample06.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;

@Mapper
public interface MemberMapper extends BaseMapper<MemberPO> {
	List<Map> findAll(RowBounds rowBounds, @Param("_orderby") String _orderby);

	List<Map> query1(RowBounds rowBound, @Param("cond") Map map);
}
