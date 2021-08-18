package com.tianchenxu.smallliuren.CustomPopup;

import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.tianchenxu.smallliuren.ResourceTable;

import ohos.agp.components.*;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class DateSelector extends BottomPopupView {
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
        Button confirm = (Button) findComponentById(ResourceTable.Id_confirm);
        Button cancel = (Button) findComponentById(ResourceTable.Id_cancel);
        DatePicker datePicker = (DatePicker) findComponentById(ResourceTable.Id_date_pick);
        int year= datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        cancel.setClickedListener(component -> {
            HiLogLabel hiLogLabel = new HiLogLabel(0, 0, "DateSelector");
            HiLog.info(hiLogLabel, "取消被点击");
            toggle();
        });
//        confirm.setClickedListener(component -> {
//            TextField selectedData = (TextField) findComponentById(ResourceTable.Id_selectDate);
//            selectedData.setText(String.format("%4d-%02d-%02d", year, month, day));
//            onDismiss();
//        });
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

}
