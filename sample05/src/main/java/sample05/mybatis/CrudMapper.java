package sample05.mybatis;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CrudMapper<T, ID extends Serializable> {

	void insert(@Param("po") T entity);

	void update(@Param("po") T entity, @Param("id") ID id);

	void delete(@Param("id") ID id);

}
