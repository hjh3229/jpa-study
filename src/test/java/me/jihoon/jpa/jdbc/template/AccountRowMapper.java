package me.jihoon.jpa.jdbc.template;

import me.jihoon.jpa.jdbc.vo.AccountVo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<AccountVo>{
    @Override
    public AccountVo mapRow(ResultSet rs, int rowNum) throws SQLException {
        var vo = new AccountVo();
        vo.setId(rs.getInt("ID"));
        vo.setUsername(rs.getString("USERNAME"));
        vo.setPassword(rs.getString("PASSWORD"));
        return vo;
    }
}
