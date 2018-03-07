package sample05.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface MemberMapper extends CrudMapper<MemberPO, Integer> {
	MemberPO selectOne(Long i);

	List<MemberPO> selectAll();

	List<Map> selectAll(@Param("_orderby") String orderby, RowBounds rowBounds);

	List<Map> query1(@Param("where") Map map, @Param("_orderby") String orderby, RowBounds rowBounds);

	List<Map> query2(@Param("where") Map map, @Param("_orderby") String orderby, RowBounds rowBounds);
}
