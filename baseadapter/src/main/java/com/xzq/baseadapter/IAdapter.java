package com.xzq.baseadapter;

import java.util.List;

/**
 * IAdapter
 *
 * @author xzq
 */

public interface IAdapter<T> {

    void setData(List<T> data);

    void setData(List<T> data, boolean hasNext);

    boolean addData(List<T> data, boolean hasNext);

    void onError();

    void onEmpty();

    void clear();

}
