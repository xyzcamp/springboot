package sample05.jpa;

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

@Controller
public class JpaController {

	@Autowired
	MemberRepo memberRepo;

	@RequestMapping("/jpa1")
	@ResponseBody
	public ResponseEntity<?> jpa1(Model model) {
		// 取得一筆資料
		{
			MemberEntity member = memberRepo.findOne(2L);
			model.addAttribute("one", member);
		}

		// 取得全部, 排序分頁
		{
			Sort sort = new Sort(Direction.DESC, "id");
			Pageable pageable = new PageRequest(2, 4, sort);
			
			Page<MemberEntity> members_page = memberRepo.findAll(pageable);
			model.addAttribute("paging", members_page);
		}

		// id<3,  排序不分頁
		{
			Sort sort = new Sort(Direction.DESC, "id");

			List<MemberEntity> members_repo1 = memberRepo.findByIdLessThan(3L, sort);
			model.addAttribute("repo1", members_repo1);
		}

		// id>3, 排序分頁
		{
			Sort sort = new Sort(Direction.DESC, "id");
			Pageable pageable = new PageRequest(2, 4, sort);
			
			Page<MemberEntity> members_repo2 = memberRepo.findByIdGreaterThan(3L, pageable);
			model.addAttribute("repo2", members_repo2);
		}

		return ResponseEntity.ok().body(model);
	}

	@PersistenceContext
	private EntityManager em;

	@RequestMapping("/jpa2")
	@ResponseBody
	public ResponseEntity<?> jpa2(Model model) {

		// query1
		{
			Sort sort = new Sort(Direction.DESC, "id");
			Pageable pageable = new PageRequest(2, 4, sort);
			
			List<MemberEntity> members_q1 = memberRepo.query1(3L, pageable);
			model.addAttribute("query1", members_q1);
		}

		// query2
		// native query無法有pageable
		{
			List<MemberEntity> members_q2 = memberRepo.query2(4L);
			model.addAttribute("query2", members_q2);
		}

		// native sql
		// native query無法有pageable
		{
			String sql3 = "select m_id, name, sex from MemberTest t1 where t1.m_id > :x_id";
			Query q = em.createNativeQuery(sql3);
			q.unwrap(org.hibernate.Query.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			q.setParameter("x_id", 5);
			List members_q3 = q.getResultList();
			model.addAttribute("query3", members_q3);
		}
		return ResponseEntity.ok().body(model);
	}

	@Autowired
	JpaService jpaService;

	@RequestMapping("/jpa11")
	@ResponseBody
	public ResponseEntity<?> jpa11(Model model) {
		Long[] member1_ids = { 0L };
		Long[] member2_ids = { 0L };
		jpaService.jap2Trans(member1_ids, member2_ids);

		return ResponseEntity.ok().body("OK. member1.id=" + member1_ids[0] + ", member2.id=" + member2_ids[0]);
	}

}
