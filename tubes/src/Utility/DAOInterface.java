package Utility;

import java.util.List;

public interface DAOInterface<E> {
    int addData(E data);
    int deleteData(E data);
    int updateData(E data);

    List<E> showData();
}