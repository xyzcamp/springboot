package sample05.controller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample05.mybatis.MemberMapper;
import sample05.mybatis.MemberPO;

@Controller
public class MybatisController {

	@Autowired
	MemberMapper memberMapper;

	@RequestMapping("/mybatis1")
	@ResponseBody
	public ResponseEntity<?> mybatis1(Model model) {
		// 取得一筆資料
		MemberPO member = memberMapper.findOne(2L);
		model.addAttribute("one", member);

		// 取得全部
		List<MemberPO> members = memberMapper.findAll();
		model.addAttribute("all", members);

		// 取得全部
		List<Map> members_paging = memberMapper.findAll(new RowBounds(2, 3), "order by m_id desc");
		model.addAttribute("paging", members_paging);

		return ResponseEntity.ok().body(model);
	}

}
