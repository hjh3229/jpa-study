package me.jihoon.jpa.jdbc.template;

import me.jihoon.jpa.jdbc.vo.AccountVo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class AccountTemplateDAO {

    private final JdbcTemplate jdbcTemplate;

    public AccountTemplateDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // SQL 쿼리
    private final String ACCOUNT_INSERT = "INSERT INTO account(ID, USERNAME, PASSWORD) "
            + "VALUES((SELECT coalesce(MAX(ID), 0) + 1 FROM ACCOUNT A), ?, ?)";
    private final String ACCOUNT_SELECT = "SELECT * FROM account WHERE ID = ?";


    // CRUD 기능 메소드
    public Integer insertAccount(AccountVo vo) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            var ps = con.prepareStatement(ACCOUNT_INSERT, new String[]{"id"});
            ps.setString(1, vo.getUsername());
            ps.setString(2, vo.getPassword());
            return ps;
        }, keyHolder);

        return (Integer) keyHolder.getKey();
    }

    public AccountVo selectAccount(Integer id) {
        return jdbcTemplate.queryForObject(ACCOUNT_SELECT, new AccountRowMapper(), id);
    }

}