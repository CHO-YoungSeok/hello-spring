package zerothStone.hello_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zerothStone.hello_spring.domain.Member;

import java.util.Optional;
import java.util.OptionalInt;

public interface SpringDataMemberRepository extends JpaRepository<Member, Long>,
        MemberRepository  {

    Optional<Member> findByName(String name);
}
