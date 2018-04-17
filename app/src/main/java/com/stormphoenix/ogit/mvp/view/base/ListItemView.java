package com.stormphoenix.ogit.mvp.view.base;

import com.stormphoenix.ogit.mvp.contract.BaseContract;

import java.util.List;

/**
 * Created by wanlei on 18-2-28.
 */
public interface ListItemView<T> extends BaseContract.View {

    void loadNewlyListItem(List<T> listItems);

    int getListItemCounts();

    void loadMoreListItem(List<T> listItems);

    void reLogin();

    void clearAllItems();
}
