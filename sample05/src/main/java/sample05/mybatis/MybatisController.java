package sample05.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample05.jpa.MemberEntity;

@Controller
public class MybatisController {

	@Autowired
	MemberMapper memberMapper;

	@Autowired
	private SqlSession sqlSession;

	@RequestMapping("/my1")
	@ResponseBody
	public ResponseEntity<?> my1(Model model) {
		// 取得一筆資料
		{
			MemberPO member = memberMapper.selectOne(2L);
			model.addAttribute("one", member);
		}

		// 取得全部, 排序分頁
		{
			List<Map> members_paging = memberMapper.selectAll("order by m_id desc", new RowBounds((2 - 1) * 4, 4));
			model.addAttribute("paging", members_paging);
		}

		// id<3, 排序不分頁
		{
			Map<String, Object> where = new HashMap<String, Object>();
			where.put("m_id", 3);
			List<Map> members_query = memberMapper.query1(where, "order by m_id desc", new RowBounds(0, 0));
			model.addAttribute("query1", members_query);
		}

		// id>3, 排序分頁
		{
			Map<String, Object> where = new HashMap<String, Object>();
			where.put("m_id", 3);
			List<Map> members_query = memberMapper.query2(where, "order by m_id desc", new RowBounds((2 - 1) * 4, 4));
			model.addAttribute("query2", members_query);
		}

		return ResponseEntity.ok().body(model);
	}

	@RequestMapping("/my2")
	@ResponseBody
	public ResponseEntity<?> my2(Model model) {
		// query2
		{
			Map<String, Object> sqlParam = new HashMap<String, Object>();
			Map<String, Object> where = new HashMap<String, Object>();
			sqlParam.put("where", where);
			
			sqlParam.put("_orderby", "order by m_id desc");
			where.put("m_id", 3);

			List<Map> result = sqlSession.selectList("sample05.mybatis.MemberMapper.query2", sqlParam,
					new RowBounds((2 - 1) * 4, 4));
			model.addAttribute("query2", result);
		}

		return ResponseEntity.ok().body(model);
	}

}
