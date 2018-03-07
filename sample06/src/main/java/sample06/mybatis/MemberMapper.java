package sample06.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.BaseMapper;

@Mapper
public interface MemberMapper extends BaseMapper<MemberPO> {
	List<MemberPO> selectAll();

	List<Map> selectAll(@Param("_orderby") String orderby, RowBounds rowBounds);

	List<Map> query2(@Param("where") Map map, @Param("_orderby") String orderby, RowBounds rowBounds);

}
