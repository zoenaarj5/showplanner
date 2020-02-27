package org.kavus.inyakost.repo;

import org.kavus.inyakost.ntt.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member,Long> {
}
