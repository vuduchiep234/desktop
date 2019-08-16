/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nttt.api.configdb;


/**
 *
 * @author lannt54
 */
public class PostgreSQLDialect extends org.hibernate.dialect.PostgreSQLDialect {

    public PostgreSQLDialect() {
        super();
//        registerColumnType(Types.CHAR, "nchar(1)");
//        registerColumnType(Types.VARCHAR, "nvarchar($l)");
//        registerColumnType(Types.NVARCHAR, "nvarchar($l)");
//        registerColumnType(Types.CLOB, "ntext");
    }
}
