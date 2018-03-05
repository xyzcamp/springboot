package sample05.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample05.jpa.MemberEntity;
import sample05.jpa.MemberRepo;

@Controller
public class JpaController {

	@Autowired
	MemberRepo memberRepo;

	@PersistenceContext
	private EntityManager em;

	@RequestMapping("/jpa1")
	@ResponseBody
	public ResponseEntity<?> jpa1(Model model) {
		// 取得一筆資料
		MemberEntity member = memberRepo.findOne(2L);
		model.addAttribute("one", member);

		// 取得全部
		List<MemberEntity> members = memberRepo.findAll();
		model.addAttribute("all", members);

		// 取得全部, 且排序分頁
		Sort sort = new Sort(Direction.DESC, "id");
		Pageable pageable = new PageRequest(0, 3, sort);
		Page<MemberEntity> members_page = memberRepo.findAll(pageable);
		model.addAttribute("paging", members_page);

		// repo1
		List<MemberEntity> members_repo1 = memberRepo.findById(3L);
		model.addAttribute("repo1", members_repo1);

		// repo2
		List<MemberEntity> members_repo2 = memberRepo.findByIdLessThan(3L);
		model.addAttribute("repo2", members_repo2);

		// query1
		List<MemberEntity> members_q1 = memberRepo.query1(3L);
		model.addAttribute("query1", members_q1);

		// query2
		List<MemberEntity> members_q2 = memberRepo.query2(4L);
		model.addAttribute("query2", members_q2);

		// native sql
		String sql3 = "select m_id, name, active from MemberTest t1 where t1.m_id < :x_id";
		Query q = em.createNativeQuery(sql3);
		q.unwrap(org.hibernate.Query.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		q.setParameter("x_id", 5);
		List members_q3 = q.getResultList();
		model.addAttribute("query3", members_q3);

		return ResponseEntity.ok().body(model);
	}

	@Autowired
	JpaService jpaService;
	
	@RequestMapping("/jpa2")
	@ResponseBody
	public ResponseEntity<?> jpa2(Model model) {
		Long[] member1_ids = {0L};
		Long[] member2_ids = {0L};
		jpaService.jap2Trans(member1_ids, member2_ids);

		return ResponseEntity.ok().body("OK. member1.id=" + member1_ids[0] + ", member2.id=" + member2_ids[0]);
	}
	
	

}
