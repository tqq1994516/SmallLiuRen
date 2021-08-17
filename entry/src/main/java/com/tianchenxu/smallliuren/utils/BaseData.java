package com.tianchenxu.smallliuren.utils;

import com.nlf.calendar.Lunar;
import com.tianchenxu.smallliuren.database.*;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;

import java.util.Calendar;
import java.util.List;

public class BaseData {
    private static final String DATABASE_NAME = "FormDatabase.db";
    private static final String DATABASE_NAME_ALIAS = "FormDatabase";
    public static void initBaseData(OrmContext connect, DatabaseHelper databaseHelper) {
        if (connect == null) {
            connect = databaseHelper.getOrmContext(DATABASE_NAME_ALIAS, DATABASE_NAME, FormDatabase.class);
        }
        setOldLunarHour(connect);
        addTiangan("甲", 1, 1, connect);
        addTiangan("乙", 2, 2, connect);
        addTiangan("丙", 3, 1, connect);
        addTiangan("丁", 4, 2, connect);
        addTiangan("戊", 5, 1, connect);
        addTiangan("己", 0, 2, connect);
        addTiangan("庚", 7, 1, connect);
        addTiangan("辛", 8, 2, connect);
        addTiangan("壬", 9, 1, connect);
        addTiangan("癸", 10, 2, connect);
        addDizhi("子", 1, 1, connect);
        addDizhi("丑", 2, 2, connect);
        addDizhi("寅", 3, 1, connect);
        addDizhi("卯", 4, 2, connect);
        addDizhi("辰", 5, 1, connect);
        addDizhi("巳", 0, 2, connect);
        addDizhi("午", 7, 1, connect);
        addDizhi("未", 8, 2, connect);
        addDizhi("申", 9, 1, connect);
        addDizhi("酉", 10, 2, connect);
        addDizhi("戌", 11, 1, connect);
        addDizhi("亥", 12, 2, connect);
        addAssert(1, 1, "大安事事昌，求财在坤方，失物去不远，宅舍保安康；行人身未动，病者主无妨，将军回田野，仔细兴推祥。", connect);
        addAssert(1, 2, "大安加留连，办事不周全，失物西北去，婚姻晚几天。", connect);
        addAssert(1, 3, "大安加速喜，事事自己起，失物当日见，婚姻自己提。", connect);
        addAssert(1, 4, "大安加赤口，办事不顺手，失物不用找，婚姻两分手。", connect);
        addAssert(1, 5, "大安加小吉，事事从己及，失物不出门，婚姻成就地。", connect);
        addAssert(1, 0, "大安加空亡，病人要上床，失物无踪影，事事不顺情。", connect);
        addAssert(2, 1, "留连加大安，办事两分张，婚姻有喜事，先苦后来甜。", connect);
        addAssert(2, 2, "留连事难成，求谋月未明，凡事只宜缓，去者未回程；失物南方见，急讨方称心，更须防口舌，人口且太平。", connect);
        addAssert(2, 3, "留连加速喜，事事由自己，婚姻有成意，失物三天里。", connect);
        addAssert(2, 4, "留连加赤口，病者死人口，失物准丢失，婚姻两分手。", connect);
        addAssert(2, 5, "留连加小吉，事事不用提，失物东南去，病者出人齐。", connect);
        addAssert(2, 0, "留连加空亡，病人准死亡，失物不见面，婚姻两分张。", connect);
        addAssert(3, 1, "速喜加大安，事事都平安，姻姻成全了，占病都相安。", connect);
        addAssert(3, 2, "速喜加留连，婚姻不可言，失物无信息，病人有仙缘。", connect);
        addAssert(3, 3, "速喜喜来临，求财向南行，失物申未午，逢人路上寻；官事有福德，病者无祸侵，田宅六畜吉，行人有喜音。", connect);
        addAssert(3, 4, "速喜加赤口，自己往外走，失物往正北，婚姻得勤走。", connect);
        addAssert(3, 5, "速喜加小吉，婚姻有人提，病人当天好，时物在家里。", connect);
        addAssert(3, 0, "速喜加空亡，婚姻有分张，病者积极治，失物不久见。", connect);
        addAssert(4, 1, "赤口加大安，办事险和难，失物东北找，婚姻指定难。", connect);
        addAssert(4, 2, "赤口加留连，办事有困难，行人在外走，失物不回还。", connect);
        addAssert(4, 3, "赤口加速喜，婚姻在自己，失物有着落，办事官事起。", connect);
        addAssert(4, 4, "赤口主口舌，官非切要防，失物急去寻，行人有惊慌；六畜多惊怪，病者出西方，更须防诅咒，恐怕染瘟疫。", connect);
        addAssert(4, 5, "赤口加小吉，办事自己提，婚姻不能成，失物无信息。", connect);
        addAssert(4, 0, "赤口加空亡，无病也上床，失物不用找，婚姻不能成。", connect);
        addAssert(5, 1, "小吉加大安，事事两周全，婚姻当日定，失物自己损。", connect);
        addAssert(5, 2, "小吉加留连，事事有反还，婚姻有人破，失物上西南。", connect);
        addAssert(5, 3, "小吉加速喜，事事从头起，婚姻能成就，失物在院里。", connect);
        addAssert(5, 4, "小吉加赤口，办事往外走，婚姻有难处，失物丢了手。", connect);
        addAssert(5, 5, "小吉最吉昌，路上好商量，阴人来报喜，失物在坤方；人立便至，交易甚是强，凡是皆和合，病者辱上苍。", connect);
        addAssert(5, 0, "小吉加空亡，病人不妥当，失物正东找，婚姻再想想。", connect);
        addAssert(0, 1, "空亡加大安，事事不周全，婚姻从和好，失物反复间。", connect);
        addAssert(0, 2, "空亡加留连，办事处处难，婚姻重新定，失物永不还。", connect);
        addAssert(0, 3, "空亡加速喜，事事怨自己，婚姻有一定，失物在家里。", connect);
        addAssert(0, 4, "空亡加赤口，办事官非有，婚姻难定准，失物往远走。", connect);
        addAssert(0, 5, "空亡加小吉，事事有猜疑，婚姻有喜事，失物回家里。", connect);
        addAssert(0, 0, "空亡事不祥，阴人多乖张，求财无利益，行人有灾秧；失物寻不见，官事有刑伤，病人逢暗鬼，禳解保安康。", connect);
        addDeity("青龙", connect);
        addDeity("玄武", connect);
        addDeity("朱雀", connect);
        addDeity("白虎", connect);
        addDeity("六合", connect);
        addDeity("勾陈", connect);
        addFiveElements("金", connect);
        addFiveElements("木", connect);
        addFiveElements("水", connect);
        addFiveElements("火", connect);
        addFiveElements("土", connect);
        addOrientation("东", connect);
        addOrientation("南", connect);
        addOrientation("西", connect);
        addOrientation("北", connect);
        addOrientation("东南", connect);
        addOrientation("西南", connect);
        addOrientation("西北", connect);
        addOrientation("东北", connect);
        addOrientation("四方", connect);
        addOrientation("中央", connect);
        addOrientation("厝地", connect);
        addOrgan("四肢", connect);
        addOrgan("肾胃", connect);
        addOrgan("心脑", connect);
        addOrgan("肺胃", connect);
        addOrgan("肝肠", connect);
        addOrgan("脾脑", connect);
        addJingu("大安", 1, connect);
        addJingu("留连", 2, connect);
        addJingu("速喜", 3, connect);
        addJingu("赤口", 4, connect);
        addJingu("小吉", 5, connect);
        addJingu("空亡", 0, connect);
        addAttribute("大安", "青龙", "1,5,7", "3,5,8", "", "四肢", "西南", "东", "木", "小孩婆姐六畜惊，大人青面阴神。", connect);
        addAttribute("留连", "玄武", "2,8,10", "", "1,6,7", "肾胃", "南", "北", "水", "小孩游路亡魂，大人乌面夫人。", connect);
        addAttribute("速喜", "朱雀", "3,6,9", "0,1,3,7", "", "心脑", "西南", "南", "火", "小孩婆姐动勿惊，大人火箭将军。", connect);
        addAttribute("赤口", "白虎", "4,7,10", "", "3,7,9", "肺胃", "东", "西", "金", "小孩迷魂童子，大人金神七煞。", connect);
        addAttribute("小吉", "六合", "1,5,7", "2,6,9", "", "肝肠", "西南", "东", "木", "小孩婆姐六畜惊，大人无主家神。", connect);
        addAttribute("空亡", "勾陈", "3,6,9", "", "4,6,8", "脾脑", "北", "厝地", "土", "小孩土瘟神煞，大人土压夫人。", connect);
    }

    private static void setOldLunarHour(OrmContext connect) {
        Lunar lunar = new Lunar();
        Calendar calendar = Calendar.getInstance();
        List<OldLunarHour> oldLunarHours = DatabaseUtils.queryOldLunarHour(connect);
        if (oldLunarHours.isEmpty()) {
            OldLunarHour oldLunarHour = new OldLunarHour();
            oldLunarHour.setOldLunarHourText(lunar.getTimeInGanZhi());
            oldLunarHour.setLastUpdateTime(calendar);
            DatabaseUtils.insertOldLunarHour(oldLunarHour, connect);
        } else {
            oldLunarHours.get(0).setOldLunarHourText(lunar.getTimeInGanZhi());
            oldLunarHours.get(0).setLastUpdateTime(calendar);
            DatabaseUtils.updateOldLunarHour(oldLunarHours.get(0).getOldLunarHourId(), oldLunarHours.get(0), connect);
        }
    }

    private static void addTiangan(String tianganName, int tianganNum, int tianganYinyang, OrmContext connect) {
        Tiangan tiangan = new Tiangan();
        tiangan.setTianganName(tianganName);
        tiangan.setTianganNum(tianganNum);
        tiangan.setTianganYinyang(tianganYinyang);
        DatabaseUtils.insertTiangan(tiangan, connect);
    }

    private static void addDizhi(String dizhiName, int dizhiNum, int dizhiYinyang, OrmContext connect) {
        Dizhi dizhi = new Dizhi();
        dizhi.setDizhiName(dizhiName);
        dizhi.setDizhiNum(dizhiNum);
        dizhi.setDizhiYinyang(dizhiYinyang);
        DatabaseUtils.insertDizhi(dizhi, connect);
    }

    private static void addAssert(int dayNum, int timeNum, String assertText, OrmContext connect) {
        Assert anAssert = new Assert();
        anAssert.setDayNum(dayNum);
        anAssert.setTimeNum(timeNum);
        anAssert.setAssertText(assertText);
        DatabaseUtils.insertAssert(anAssert, connect);
    }

    private static void addDeity(String deityName, OrmContext connect) {
        Deity deity = new Deity();
        deity.setDeityName(deityName);
        DatabaseUtils.insertDeity(deity, connect);
    }

    private static void addFiveElements(String fiveElementsName, OrmContext connect) {
        FiveElements fiveElements = new FiveElements();
        fiveElements.setFiveElementsName(fiveElementsName);
        DatabaseUtils.insertFiveElements(fiveElements, connect);
    }

    private static void addOrgan(String organName, OrmContext connect) {
        Organ organ = new Organ();
        organ.setOrganName(organName);
        DatabaseUtils.insertOrgan(organ, connect);
    }

    private static void addOrientation(String orientationName, OrmContext connect) {
        Orientation orientation = new Orientation();
        orientation.setOrientationName(orientationName);
        DatabaseUtils.insertOrientation(orientation, connect);
    }

    private static void addJingu(String jinguName, int jinguNum, OrmContext connect) {
        Jingu jingu = new Jingu();
        jingu.setJinguName(jinguName);
        jingu.setJinguNum(jinguNum);
        DatabaseUtils.insertJingu(jingu, connect);
    }

    private static void addAttribute(String jinguName, String deity, String luckyNum, String successNum, String ominousNum, String organ, String magnateOrientation, String offendOrientation, String fiveElements, String ghostsAndGods, OrmContext connect) {
        Attribute attribute = new Attribute();
        Jingu jingu = DatabaseUtils.queryJinguByName(jinguName, connect);
        attribute.setJingu(jingu.getJinguId());
        Deity deityId = DatabaseUtils.queryDeityByName(deity, connect);
        attribute.setDeity(deityId.getDeityId());
        attribute.setLuckyNum(luckyNum);
        attribute.setSuccessNum(successNum);
        attribute.setOminousNum(ominousNum);
        Organ organId = DatabaseUtils.queryOrganByName(organ, connect);
        attribute.setOrgan(organId.getOrganId());
        Orientation magnateOrientationId = DatabaseUtils.queryOrientationByName(magnateOrientation, connect);
        attribute.setMagnateOrientation(magnateOrientationId.getOrientationId());
        Orientation offendOrientationId = DatabaseUtils.queryOrientationByName(offendOrientation, connect);
        attribute.setOffendOrientation(offendOrientationId.getOrientationId());
        FiveElements fiveElementsId = DatabaseUtils.queryFiveElementsByName(fiveElements, connect);
        attribute.setFiveElements(fiveElementsId.getFiveElementsId());
        attribute.setGhostsAndGods(ghostsAndGods);
        DatabaseUtils.insertAttribute(attribute, connect);
    }
}