package sample05.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JpaService {
	@Autowired
	MemberRepo memberRepo;

	@Transactional
	public void jap2Trans(Long[] id1s, Long[] id2s) {
		MemberEntity member1 = new MemberEntity();
		member1.setName("acer1");
		memberRepo.save(member1);
		id1s[0] = member1.getId();

		MemberEntity member2 = new MemberEntity();
		member2.setName("acer2");
		memberRepo.save(member2);
		id2s[0] = member2.getId();

		MemberEntity member3 = memberRepo.findOne(1L);
		member3.setName("3A");
		memberRepo.save(member3);

		memberRepo.delete(5L);
	}
}
