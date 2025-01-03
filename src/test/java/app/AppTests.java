package app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AppTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testGenresTableData() {
        // Query per verificare se ci sono righe nella tabella 'genres'
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM genres", Integer.class);

        // Verifica che ci siano dati (o che almeno il conteggio non sia null)
        assertTrue(count != null && count > 0, "The genres table is empty or not accessible!");
    }
}

