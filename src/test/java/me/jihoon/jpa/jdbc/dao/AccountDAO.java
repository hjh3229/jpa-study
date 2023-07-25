package me.jihoon.jpa.jdbc.dao;

import me.jihoon.jpa.jdbc.vo.AccountVo;

import java.sql.*;

public class AccountDAO {

    // JDBC 관련 변수 관리
    private Connection conn = null;

    private PreparedStatement stat = null;

    private ResultSet rs = null;

    private static final String url = "jdbc:postgresql://localhost:5432/messenger";
    private static final String username = "teasun";
    private static final String password = "pass";

    // SQL 쿼리
    private final String ACCOUNT_INSERT = "INSERT INTO ACCOUNT (id, username, password) \n" +
            "VALUES ((SELECT coalesce(MAX(ID), 0) + 1 FROM ACCOUNT A), ?, ?)";
    private final String ACCOUNT_SELECT = "SELECT * FROM account WHERE ID = ?";

    // CRUD 기능 메서드
    public Integer insertAccount(AccountVo vo) {
        var id = -1;
        try {
            String[] returnId = {"id"};
            conn = DriverManager.getConnection(url, username,password);
            stat = conn.prepareStatement(ACCOUNT_INSERT, returnId);
            stat.setString(1, vo.getUsername());
            stat.setString(2, vo.getPassword());
            stat.executeUpdate();

            try (ResultSet rs = stat.getGeneratedKeys()){
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    public AccountVo selectAccount(Integer id) {

        AccountVo vo = null;

        try {
            conn = DriverManager.getConnection(url, username,password);
            stat = conn.prepareStatement(ACCOUNT_SELECT);
            stat.setInt(1, id);
            var rs = stat.executeQuery();

            if (rs.next()) {
                vo = new AccountVo();
                vo.setId(rs.getInt("ID"));
                vo.setUsername(rs.getString("USERNAME"));
                vo.setPassword(rs.getString("PASSWORD"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vo;
    }
}
