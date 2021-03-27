package fr.esiea.ex4A.hello;

import fr.esiea.ex4A.lovemeet.Repository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RepositoryTest {


    @Test
    void repository_get_users(){
        Repository repository = new Repository();

        assertThat(repository.getUsers()).isInstanceOf(List.class);
    }
}
