import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainRun {

    public static void main(String[] args) throws Exception {
//        testConn();
        runGenerator();
    }

    /**
     * 执行MyBatis Generator
     *
     * @throws Exception
     */
    static void runGenerator() throws Exception {
        System.out.println("开始生成");
        List<String> warnings = new ArrayList<>();
        // 如果已经存在生成过的文件是否进行覆盖
        boolean overwrite = true;
        File configFile = new File("D:\\ideaProjects\\mybatis_generator\\src\\main\\resources\\generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
        generator.generate(null);
        System.out.println("生成完毕");
    }

    static void testConn() throws Exception {
        // protocol//[hosts][/database][?properties]
        String sql = "select * from student";
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "mariadb");
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            System.out.println(conn);
            while (rs.next()) {
                System.out.println(rs.getObject(1));
                System.out.println(rs.getObject(2));
            }
        }
    }

}


