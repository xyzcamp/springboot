package sample06.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import sample06.mybatis.MemberMapper;
import sample06.mybatis.MemberPO;

@Controller
public class MybatisController {

	@Autowired
	MemberMapper memberMapper;

	@RequestMapping("/myp1")
	@ResponseBody
	public ResponseEntity<?> myp1(Model model) {
		// 取得一筆資料
		MemberPO member = memberMapper.selectById(2L);
		model.addAttribute("one", member);

		// 取得全部
		List<MemberPO> members = memberMapper.selectList(null);
		model.addAttribute("all", members);

		// 取得部份
		EntityWrapper<MemberPO> ew1 = new EntityWrapper<MemberPO>();
		ew1.where("m_id >= {0}", 3).orderBy("m_id desc, name asc");
		List<MemberPO> members_partial = memberMapper.selectList(ew1);
		model.addAttribute("all_partial", members_partial);

		// 取得分頁
		List<Map> members_paging = memberMapper.findAll(new RowBounds(2, 3), "order by m_id asc");
		model.addAttribute("paging", members_paging);

		// query1
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("m_id", 3);
		List<Map> members_query1 = memberMapper.query1(new RowBounds(2, 3), map1);
		model.addAttribute("query1", members_query1);

		return ResponseEntity.ok().body(model);
	}

}
