package wj.mapper;

import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import wj.entity.dataBaseMapping.Muse;
import wj.mapper.dySql.DynamicSql;

import java.util.List;
import java.util.Map;

@Repository
public interface MuseMapper {
    @SelectProvider(type = DynamicSql.class,method = "dynamicMuseSql")
    public List<Map<String,Object>> getAllMuseBy(Muse muse);
}
