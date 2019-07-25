/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sso.api.util;

import java.sql.Types;
import org.hibernate.dialect.SQLServerDialect;

/**
 *
 * @author lannt54
 */
public class SQLServerNativeDialect extends SQLServerDialect{
    public SQLServerNativeDialect() {
        super();
        registerColumnType(Types.CHAR, "nchar(1)");
        registerColumnType(Types.VARCHAR, "nvarchar($l)");
        registerColumnType(Types.NVARCHAR, "nvarchar($l)");
        registerColumnType(Types.CLOB, "ntext");
    }
}
