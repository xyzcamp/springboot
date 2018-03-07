package sample06.mybatis;

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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

@Controller
public class MybatisController {

	@Autowired
	MemberMapper memberMapper;

	@Autowired
	private SqlSession sqlSession;

	@RequestMapping("/myp1")
	@ResponseBody
	public ResponseEntity<?> myp1(Model model) {
		// 取得一筆資料
		{
			MemberPO member = memberMapper.selectById(2L);
			model.addAttribute("one", member);
		}

		// 取得全部, 排序分頁
		{
			List<MemberPO> members = memberMapper.selectPage(new Pagination(2,4), null);
			model.addAttribute("paging", members);
		}

		// id<3, 排序不分頁
		{
			EntityWrapper<MemberPO> ew1 = new EntityWrapper<MemberPO>();
			ew1.where("m_id < {0}", 3).orderBy("m_id desc");

			List<MemberPO> members_query1 = memberMapper.selectList(ew1);
			model.addAttribute("query1", members_query1);
		}

		// id>3, 排序分頁
		{
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("m_id", 3);
			List<Map> members_query2 = memberMapper.query2(map1, "order by m_id asc", new RowBounds(2, 4));
			model.addAttribute("query2", members_query2);
		}

		return ResponseEntity.ok().body(model);
	}

	@RequestMapping("/myp2")
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

	@RequestMapping("/myp11")
	@ResponseBody
	public ResponseEntity<?> jpa11(Model model) {
		Long[] member1_ids = { 0L };
		Long[] member2_ids = { 0L };

		{
			MemberPO member1 = new MemberPO();
			member1.setName("myp_acer1");
			memberMapper.insert(member1);
			member1_ids[0] = member1.getM_id();
		}
		{
			MemberPO member2 = new MemberPO();
			member2.setName("myp_acer1");
			memberMapper.insert(member2);
			member2_ids[0] = member2.getM_id();
		}
		{
			MemberPO member3 = memberMapper.selectById(1L);
			member3.setName("4B");
			memberMapper.updateById(member3);
		}
		memberMapper.deleteById(5L);

		return ResponseEntity.ok().body("OK. member1.id=" + member1_ids[0] + ", member2.id=" + member2_ids[0]);
	}
}
