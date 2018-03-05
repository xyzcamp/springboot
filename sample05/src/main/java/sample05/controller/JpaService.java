package sample05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sample05.jpa.MemberEntity;
import sample05.jpa.MemberRepo;

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

		int a = 5/0;
		
		MemberEntity member2 = new MemberEntity();
		member2.setName("acer2");
		memberRepo.save(member2);
		id2s[0] = member2.getId();
	}
}
