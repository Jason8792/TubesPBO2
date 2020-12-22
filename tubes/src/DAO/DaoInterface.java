package DAO;

import java.util.List;

public interface DaoInterface<E> {
    public int AddData(E data);
    public int DelData(E data);
    public int UpdData(E data);
    public List<E> ShowData();

}
