package com.tianchenxu.smallliuren.CustomPopup;

import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.tianchenxu.smallliuren.ResourceTable;

import ohos.agp.components.*;
import ohos.app.Context;

public class DateSelector extends BottomPopupView implements Component.ClickedListener {
    private static int year;
    private static int month;
    private static int day;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DateSelector(Context context) {
        super(context, null);
    }

    @Override
    protected int getImplLayoutId() {
        return ResourceTable.Layout_date_selector;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findComponentById(ResourceTable.Id_tv_confirm).setClickedListener(this);
        findComponentById(ResourceTable.Id_tv_cancel).setClickedListener(this);
    }

    @Override
    protected void onShow() {
        super.onShow();
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
    }

    @Override
    protected int getMaxHeight() {
        return (int) (XPopupUtils.getAppHeight(getContext()) * .85f);
    }

    @Override
    public void onClick(Component component) {
        switch (component.getId()) {
            case ResourceTable.Id_tv_confirm:
                DatePicker datePicker = (DatePicker) findComponentById(ResourceTable.Id_date_pick);
                year= datePicker.getYear();
                month = datePicker.getMonth();
                day = datePicker.getDayOfMonth();
                setDate(String.format("%4d-%02d-%02d", year, month, day));
                smartDismiss();
                break;
            case ResourceTable.Id_tv_cancel:
                smartDismiss();
                break;
        }
    }
}
