package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    //테스트는 서로 순서와 관계가 없어야 하고 의존관계가 없이 설계되어야 한다.
    //Test를 할때는 실행순서를 정할 수 없으므로 실행순서에 따라 결과값이 바뀌는 경우에는 실패할 수 있다
    //그러므로 AfterEach를 만들어 각각의 테스트 수행후 초기화 해주는 역할을 해줘야 한다/
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
//        System.out.println("result " + (result==member)); > 값으로 볼 수 있긴함

        // 실제값, 예상값 > 여기서 실제값이랑 예상값이 다르면 오류가 발생하고 동일하면 정상실행된다.
//        Assertions.assertEquals(member, null);

        // Assertions에 ALT+ENTER 입력하면 import 가능 이후에 Assertions 입력 없이 사용 가능
        assertThat(member).isEqualTo(result);
    }


    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member2);


    }

    @Test
    public void findAll(){

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);



    }

}
