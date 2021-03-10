package Tool.Dao;

import Tool.Pojo.Yhmyysxx;

import java.util.List;

public interface YhmyysxxDao {
    public List<Yhmyysxx> getAll();
    public List<Yhmyysxx> getDataList(String ysdz);
}
