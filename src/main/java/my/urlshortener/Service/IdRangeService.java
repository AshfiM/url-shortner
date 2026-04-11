package my.urlshortener.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class IdRangeService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private long currentId;
    private long maxId = 0;
    private final int rangeSize = 1000;

    public synchronized Long getNextId(){
        if (currentId >= maxId) {
            requestNewRange();
        }
        return currentId++;
    }

    public void requestNewRange(){
        String sql = "update id_generator set next_id = next_id + ? where service_name = 'url-shortener'";
        jdbcTemplate.update(sql, rangeSize);
        String fetchSql = "select next_id from id_generator where service_name = 'url-shortener'";
        Long newMaxId = jdbcTemplate.queryForObject(fetchSql, Long.class);

        this.maxId = newMaxId;
        this.currentId = newMaxId -rangeSize;
    }
}
