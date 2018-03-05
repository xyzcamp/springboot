package sample05.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface MemberMapper extends CrudMapper<MemberPO, Integer> {
	MemberPO findOne(Long i);

	List<MemberPO> findAll();

	List<Map> findAll(RowBounds rowBounds, @Param("_orderby") String _orderby);

}
