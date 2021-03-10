import Tool.Dao.PfflowinstanceDao;
import Tool.Dao.YhmyysxxDao;
import Tool.Pojo.Pfflowinstance;
import Tool.Pojo.Yhmyysxx;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MysqlDBTest {
    @Test
    public void Zhijiezhixing() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        session.select("getAll", new ResultHandler() {
            @Override
            public void handleResult(ResultContext resultContext) {
                Yhmyysxx ResultObject = ((Yhmyysxx) resultContext.getResultObject());
                System.out.println(ResultObject);
            }
        });

        session.close();
    }

    @Test
    public void UseMaper() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        YhmyysxxDao yhmyysxxDao = session.getMapper(YhmyysxxDao.class);
        List<Yhmyysxx> yummy = yhmyysxxDao.getDataList("%方圆%");

        yummy.forEach(System.out::println);

        session.close();
    }

    @Test
    public void Oracle() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"oracle");
        SqlSession session = sqlSessionFactory.openSession();

        PfflowinstanceDao pfflowinstanceDao = session.getMapper(PfflowinstanceDao.class);
        List<Pfflowinstance> pfflowinstances = pfflowinstanceDao.getAll("%账号%");

        pfflowinstances.stream()
                .map(e->new String[]{e.getTitle(),e.getState()})
                .forEach(e-> System.out.println(e[0]+" "+e[1]));

        session.close();
    }
}
