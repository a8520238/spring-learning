package com.demo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true) //链式写法
//所有实体类都需要加上序列化
public class Dept implements Serializable {
    private Long deptno;
    private String dname;

    //一个服务对应一个数据库，一个服务对应一个数据库，同一个信息可能存在不同的数据库
    private  String db_source;

    public Dept(String dname) {
        this.dname = dname;
    }

//    public Dept() {
//
//    }

//    public Long getDeptno() {
//        return deptno;
//    }
//
//    public void setDeptno(Long deptno) {
//        this.deptno = deptno;
//    }
//
//    public String getDname() {
//        return dname;
//    }
//
//    public void setDname(String dname) {
//        this.dname = dname;
//    }
//
//    public String getDb_source() {
//        return db_source;
//    }
//
//    public void setDb_source(String db_source) {
//        this.db_source = db_source;
//    }
    /*
    * Dept dept = new Dept();
    * dept.setA(1).setB(2);
    * 上面就叫链式写法
    * */
}
