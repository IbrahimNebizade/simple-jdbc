package com.company;

import com.company.repository.Impl.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.jar.JarEntry;

public class Main {
    public static void main(String[] args) throws SQLException {
        var r=new JobRepositoryImpl();
        //System.out.println(r.deleteById(100l));
        System.out.println(r.allJob());
        System.out.println(r.updateJobMinMaxSalary("Programmer",new BigDecimal(5000),new BigDecimal(10000)));
        System.out.println(r.allJob());
    }
}
