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
        addFiveElements("金", "水", "木", connect);
        addFiveElements("木", "火", "土", connect);
        addFiveElements("水", "木", "火", connect);
        addFiveElements("火", "土", "金", connect);
        addFiveElements("土", "金", "水", connect);
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
        addRealms("人间道", connect);
        addRealms("修罗道", connect);
        addRealms("佛陀道", connect);
        addRealms("畜牲道", connect);
        addRealms("天神道", connect);
        addRealms("厄鬼道", connect);
        addRelation("父母", connect);
        addRelation("子孙", connect);
        addRelation("官鬼", connect);
        addRelation("妻财", connect);
        addRelation("兄弟", connect);
        addRelation("小人", connect);
        addAffair("事业宫", connect);
        addAffair("命运宫", connect);
        addAffair("田宅宫", connect);
        addAffair("奴仆宫", connect);
        addAffair("感情宫", connect);
        addAffair("婚姻宫", connect);
        addAffair("疾厄宫", connect);
        addAffair("兄弟宫", connect);
        addAffair("驿马宫", connect);
        addAffair("子女宫", connect);
        addAffair("福德宫", connect);
        addAffair("父母宫", connect);
        addOrgan("四肢", connect);
        addOrgan("肾胃", connect);
        addOrgan("心脑", connect);
        addOrgan("肺胃", connect);
        addOrgan("肝肠", connect);
        addOrgan("脾脑", connect);
        addTiangan("甲", 1, 1, "木", "森林木", connect);
        addTiangan("乙", 2, 2, "木", "花草木", connect);
        addTiangan("丙", 3, 1, "火", "太阳火", connect);
        addTiangan("丁", 4, 2, "火", "莹蜡火", connect);
        addTiangan("戊", 5, 1, "土", "高原土", connect);
        addTiangan("己", 0, 2, "土", "河床土", connect);
        addTiangan("庚", 7, 1, "金", "斧钺金", connect);
        addTiangan("辛", 8, 2, "金", "首饰金", connect);
        addTiangan("壬", 9, 1, "水", "湖海水", connect);
        addTiangan("癸", 10, 2, "水", "雨露水", connect);
        addDizhi("子", 1, 1, "水", connect);
        addDizhi("丑", 2, 2, "土", connect);
        addDizhi("寅", 3, 1, "木", connect);
        addDizhi("卯", 4, 2, "木", connect);
        addDizhi("辰", 5, 1, "土", connect);
        addDizhi("巳", 0, 2, "火", connect);
        addDizhi("午", 7, 1, "火", connect);
        addDizhi("未", 8, 2, "土", connect);
        addDizhi("申", 9, 1, "金", connect);
        addDizhi("酉", 10, 2, "金", connect);
        addDizhi("戌", 11, 1, "土", connect);
        addDizhi("亥", 12, 2, "水", connect);
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
        addDeity("青龙", "青龙主文凭，文书，财帛，贵人，官贵，主酒色赌博，婚姻喜庆之事。性格上主为人耿直，正义，光明磊落。青龙主人固执己见，为人不活跃。受克主婚姻不顺，诸事不吉。", connect);
        addDeity("腾蛇", "腾蛇主田土，房产，奴仆之事。主慢，拖延，凝滞，阻力。主陈旧，老的，熟人，旧事。性格主为人主忠厚老实，性情倔强，处世死板，不圆滑，自我约束力强。也主诚实之人。主争斗，争执，争夺。", connect);
        addDeity("朱雀", "朱雀主口舌，官司，诉讼，信件，信息。也主文化之人。也主轻薄，轻浮，淫乱。性格象火一样急切，热情多礼，爱说爱讲，好说好笑。朱雀主口舌是非之事。", connect);
        addDeity("白虎", "白虎主凶灾，伤病，横祸，孝服，丧事，也主恶人。白虎主传送，行走，主军警，政法之人。主奔波，流动。主凶恶，强悍。受生性格沉稳，城府深，凶狠，善于心计。", connect);
        addDeity("玄武", "玄武主暗昧不明，暗失，私情，隐私，盗窃等，也主贼人，小人。主流动性职业，好淫乱，人无主见，好妄想。性格主虚而不实，做事没有诚信，说话不诚实，明一套，暗一套", connect);
        addDeity("勾陈", "勾陈主虚惊怪异之事，主鬼神，仙妖，怪事。主医巫卜相，僧道，孤寡人。性格主奸诈狡猾，心眼多。", connect);
        addStar("天权", "時辰落在天權星，性格操持志氣雄，作事差遲人也喜，一呼百喏有威風。此星在命，主人聰明，俊秀灑落，襟懷有權有勢，多智多能，若逢貴福文壽星相助者，人人欽敬，權而無權乃中命也，若逢厄破孤驛在命者，作事勞力，財帛不聚，未能先能，未會先會，浮浪中命也。", "人间道", connect);
        addStar("天艺", "天藝生人性最靈，將南作北逞多能，諱為見靈機關巧，到處和同作事勤。此星照命，主人多智多能，機巧近貴，若犯重者，主資質昏鈍，懶惰愚頑，多學少成，匠作用力之輩，若得天權貴福文壽俱全，剛柔相濟，雖為藝術，亦可成立，若為天孤可為僧道之出類者，乃中命也，若逢破厄，則藝舉無成終為下命。", "修罗道", connect);
        addStar("天奸", "大如滄海細如毛，佛口蛇心兩面刀，奸狡狠謀藏毒性，意多翻覆最難調。此星照命，主人一生勞碌，啾唧奔波，指東說西，機變難測，若得天貴福星相助，財帛豐盈，亦為上命，若逢權刃星者，必為奸權殘忍之人，言清行濁，執性凶謀，有善人之心，無容人之量，貪嗔太重，非善人也，若逢孤厄破驛，定為慳貪嫉妒之小人，乃下命也。", "修罗道", connect);
        addStar("天福", "命逢天福是生時，定然倉庫有盈餘，寬洪大量根基穩，財帛光華百福齊。此星照命，主人受福清閑，性情自在，度量寬洪，根基穩實，又得權刃相扶，衣帛充足，倉庫盈餘，堆金積玉之命，若犯重者，衣祿不多，若驛孤奸破星者，必主貪慳嫉妒，衣祿艱難之下命也。", "佛陀道", connect);
        addStar("天贵", "时辰落在天贵星，一生清贵事和同，志气不凡人出类，安然自在性明通。此星在命，主人品性清高有德，意志不凡出人众，安然自在性明通。其为人富贵清吉，大事难成，小事不拘。若命中得天权禄马星相助，荣昌富贵享不尽。但是逢孤厄，破刃照命者，见贵而未为全吉也。", "佛陀道", connect);
        addStar("天破", "時辰落在天破宮，堆金積玉也成空，夜眠算計圖家富，鈔袋誰知有蛀蟲。此星照命，主人財帛空虛，祖業耗散，若得權貴福星相助，亦為中命，如遇驛刃孤厄犯重者，作事艱難，重重破敗，浮浪東西之下命也。", "畜牲道", connect);
        addStar("天刃", "天刃為人性大剛，是非終日要爭強，持刀弄斧刑心重，好似將軍入戰場。此星照命，主人一生剛很性格，躁暴自做，自是不受人觸，受不得閑氣，風火性過端然無事，若得權貴，福星為人，不俗禮義，足以化強暴乃上命也，若逢孤破奸厄，膽大心粗，形體殘疾，不免斷髮身死乃下命也，惡星少而吉星多者，亦為中命犯重必主殘疾。", "畜牲道", connect);
        addStar("天寿", "夫妻生時命最長，上恭下敬性溫良，一聞千悟心慈善，喜怒中間有主張。此星照命，主人長壽康健，智慧聰明，作事溫良有救人之心，無傷人之意，恩中招怨，作事樸實，眾人欽敬，平生安穩，有始有終，喜怒不形，若得天權福貴刃星相助，必主寬洪大量，福壽綿綿，犯重者有壽無福，犯孤破厄星乃中命也。", "天神道", connect);
        addStar("天文", "命遇天文秀氣清，聰明智慧意惺惺，男才女秀身清吉，滿腹文章錦繡成。此星照命，主人聰明伶俐，學識過人，作事和美，若逢天貴天福天藝星相助，定主鰲頭獨占，虎榜登名，金階玉陛之人也，若遇天權天刃星者，文武多才，乃為上命，如遇破厄孤驛及犯重者，乃多學少成，不為書算文墨之輩，必為雲遊湖海之人，乃手藝術士之下命也。", "天神道", connect);
        addStar("天驿", "人道若逢天驛星，搬移離祖不曾停，身心不得片時靜，走遍天涯是未甯。此星照命，主人離鄉別井，骨肉情多勞碌，身心自成自立之命，若逢福權貴刃壽五星者，必主官供，給車馬相隨乃顯榮之命，若逢孤破厄星，猶如風吹樹葉水上浮萍，心猿意馬，奔馳不定，方外雲遊江湖上之下命也，若犯重而刃厄相沖者必為流徒之類。", "厄鬼道", connect);
        addStar("天厄", "时在厄中人混沌，惺惺作事又痴呆，此人带疾方延寿，还须劳碌作生涯。此星在命，身主困，谋为事件未得温，此人带疾方延寿，还须劳碌不见奇。其为人可能先天带暗疾，作事迟延，一生多灾多病。若逢破刃犯冲，灾病必重，若逢权贵星，主人轻疾乃中上命也，若逢孤驿奸星，主作事迟令，一生劳碌。", "厄鬼道", connect);
        addStar("天孤", "時辰若逢此天孤，六親兄弟有如無，空作空門清靜客，總有妻兒情分疏。此星照命，主一生孤獨，男人得之，六親無分，女人得之，剋子妨夫，孤星犯重者，反不為孤，必為半僧半俗，若得權福貴壽星相助，乃上命也，亦不免少年刑剋，若逢破驛奸厄刃星，必為雲水漂流下命也，凡選故出之命，要看孤星為主。", "人间道", connect);
        addJingu("大安", 1, "大安为大吉，为不不动，为青龙，为阴晴，为地震，为事业，为命，为国家，为政府，为领导，为同事，为同窗，为学士，为老师，为财富，为单位，为机构，为木，为车，为赌，为孕，为婚，为雅事，为华，为四肢，为首，为肝胆，余且不不列列。", "安泰，吉祥。身未动时,主仁。人物性格喜时，俊秀洒脱，正直大方，仁慈善良，利官近贵，凶时，缺乏主动，懒惰固执，不善变通。问人平安，问运势良，问事业稳，问感情顺，问求财有，问身体健。为事业宫，为命宫。主木，为竹，为树木，为花卉，为木旁姓氏，草头姓氏，为青绿色，为木质物，为静物，为家俱。主震巽，为肝胆，为四肢，为左腿，为风雷，为阴天，为风和日丽。主卧龙，为铁路，为火车，龙动土宫为地震。为家宅，为卧室，为床，为疗养，为尊贵，为婚姻合美，为关系融洽。", "甲", "丁", "事业宫", "命运宫", "人间道", "修罗道", "天权", "天艺", connect);
        addJingu("留连", 2, "留连为小凶，为纠缠，拖拉，回旋，暗昧，为反复，为腾蛇，为阴雨，为田宅，为奴仆，为阴私，为曲，为库，为四⻆角，为毒，为梦，为神鬼，为蛇，为四⻆角之土，为脾胃，为肿瘤，余且不不列列。", "纠缠，迟缓。卒未归时,主虚。人物性格喜时，为人精明，小心谨慎，做事稳重，多愁善感；凶时，优柔寡断，缺少主见，为人多疑，虚言善妒。寻人在外，问不得归，问运势低，问事业难，问感情忧，求财不得，论脾胃差。为田宅宫，为奴仆宫。主土，为田园，为沟渠，为土旁姓氏，为黄黑色，为土质品，为方物。为小人，为阴人，为脾胃，为思绪，为恋爱，为阴雨天，为大雾。为左臂，主财帛，钱财，沼泽地，阴地，田园。主疾病，为虚病，邪病’鬼魂冲撞。或慢性病久病。", "己", "丁", "田宅宫", "奴仆宫", "修罗道", "佛陀道", "天奸", "天福", connect);
        addJingu("速喜", 3, "速喜为中吉，为速，为快，为迅猛，为晴，为朱雀，为文书，为喜事，为喜报，为婚姻，为感情，为信息，为事件，为通知，为嘴，为火，为血，为伤，为红，为头脑，为心血管，余且不不列列。", "快速，喜庆。人来信时,主礼。人物性格喜时，心直口快，能言善变，热情大方，敬老谦让；凶时，性情急燥，喜怒无常，易说是非，奢侈浪费。问有口信，问运势开，问工作利，问感情热，问求财得。为感情宫，为婚姻宫。主火，为日，为烟火，为炉灶，为燥物，为火旁姓氏，为红色。主离，为目，为脑，为心血，为中女，为艳阳天。主礼，为有礼节人，为贵人，为文书，为喜讯，为喜鹊，为多言善辩。为人面红发黄，眉窄耳尖，病为心脑血管，亦为顺产。", "丙", "辛", "感情宫", "婚姻宫", "佛陀道", "畜牲道", "天贵", "天破", connect);
        addJingu("赤口", 4, "赤口为中凶，为口舌，为雨雪冰雹，为白虎，为伤，为打斗，为官非，为兄弟，为疾厄，为金金器，为医生，为手术，为不不顺，为灾，为病，为缺，为损，为不不足，为刑法，为司法，为竞争，为吵闹，为不安，为金金，为肺，为呼吸，为吃，余且不不列列。", "官伤，口舌。人在外时,主义。人物性格喜时，性格刚强，行事果断，豪爽义气，疾恶如仇；凶时，争强好胜，冒失冲动，专横霸道，常生不平。问有是非，问运势差，求文职逆，问感情争，求财不易，为气管病。为疾厄宫，为兄弟宫。主金，为刀，为剑，为虎，为狼，为金属物，为有口器皿，为金旁姓氏，为银白色。主兑，为少女，为喜悦，为口，为肺，为口舌，为右臂，为官司，为伤灾，为手术，为牙医，为损伤，为疾病。为猛禽。春夏为雷雨，秋为霜雹，冬为雪冰。", "庚", "癸", "疾厄宫", "兄弟宫", "畜牲道", "天神道", "天刃", "天寿", connect);
        addJingu("小吉", 5, "小吉为小吉，为动，为玄武，为雨，为努力，为子女女，为驿马，为知识，为约定，为赴约，为合作，为酒食，为出行，为变，为趋吉，为水，为肾，为记忆，为计划，余且不列。", "小利，吉庆。人在路时,主智。人物性格喜时，聪明伶俐，学识过人，人脉广泛，适应力强；凶时，随心所欲，风流浪漫，投机取巧，为人圆滑。作事有助，问运势良，问工作顺，问感情好，为求财得，问胆肾病。为迁移宫，为子女宫。主智，为水，为小溪，为流水，为水中物，为水旁姓氏，为黑色，为施舍，为江湖，为盗贼，为漂泊，为流浪，为浪漫，主桃花，主色情。为迁移，主旅游，主奔波，主建造，主拆迁。主肾，主生殖器，主右腿。主出行，主行走，主水路。", "壬", "甲", "驿马宫", "子女宫", "天神道", "厄鬼道", "天文", "天驿", connect);
        addJingu("空亡", 0, "空亡为大凶，为空，为亡，为雾，为勾陈，为无，为福德，为父母，为土，为地，为田园，为屋，为隔离，为异地，为缺失，为牢狱，为离世，余且不列。", "无果，没有。音信稀时,主信。人物性格喜时，诚实守信，心胸宽广，温厚善良，尊师敬老；凶时，性格孤僻，脾气倔强，缺少变通，作事迟钝。问人难回，问运势差，问工作失，问感情无，求财难得，为脾胃病。为福德宫，为父母宫。主土，为中心，为空室，为牢房，为墓穴，为旷野，为盆地，为黄色，为土旁姓氏，为土中物。主勾陈，为田土损失，为凶险。为阴天，为多云。为音信渺茫。为虚伪为妄想，为遗忘。为落空，为死亡，为精神病。", "戊", "乙", "福德宫", "父母宫", "厄鬼道", "人间道", "天厄", "天孤", connect);
        addAttribute("大安", "青龙", "1,4,5", "四肢", "西南", "东", "木", "小孩婆姐六畜惊，大人青面阴神。", connect);
        addAttribute("留连", "腾蛇", "2,7,8", "肾胃", "南", "北", "水", "小孩游路亡魂，大人乌面夫人。", connect);
        addAttribute("速喜", "朱雀", "3,6,9", "心脑", "西南", "南", "火", "小孩婆姐动勿惊，大人火箭将军。", connect);
        addAttribute("赤口", "白虎", "1,2,4", "肺胃", "东", "西", "金", "小孩迷魂童子，大人金神七煞。", connect);
        addAttribute("小吉", "玄武", "5,3,8", "肝肠", "西南", "东", "木", "小孩婆姐六畜惊，大人无主家神。", connect);
        addAttribute("空亡", "勾陈", "6,5,10", "脾脑", "北", "厝地", "土", "小孩土瘟神煞，大人土压夫人。", connect);
        addBodyUse("体生用", "一般小凶，有泄、消耗，也有付出、投资等含义，如果一个卦是生出，可以说未来发展不容乐观，要付出很多，当然生出也不能完全以小凶论，有时代表投资。", connect);
        addBodyUse("用生体", "一般大吉，说明事情的发展趋势很好，也可说明有外力相助。", connect);
        addBodyUse("用克体", "一般是大凶，事情发展受阻，可以说付出很多也不一定成功。但是克也有占有、克制、改造、考察等含义，所以有时不全以大凶论，例如问工作有时理解为压力，问上级对自己的感觉时也可理解为考察一段时间。", connect);
        addBodyUse("体克用", "一般小吉，这里可以理解为主动、努力、挖掘、得到等含义。例如测失物时，体克用可表示为如果主动去找，付出努力很可能找到。", connect);
        addBodyUse("比肩", "代表男性兄弟、女性姐妹、邻居、同性的同学、战友、同事、朋友、义气、社会上所有的同行业同性合作伙伴等都看比肩。", connect);
        addBodyUse("比劫", "男性姐妹、女性兄弟、街坊、异性的同学、战友、同事、朋友、义气、社会上所有的同行业异性合作伙伴等都看劫财。", connect);
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

    private static void addBodyUse(String relationship, String body_useDetail, OrmContext connect) {
        BodyUse bodyUse = new BodyUse();
        bodyUse.setRelationship(relationship);
        bodyUse.setBody_useDetail(body_useDetail);
        DatabaseUtils.insertBodyUse(bodyUse, connect);
    }

    private static void addTiangan(String tianganName, int tianganNum, int tianganYinyang, String fiveElementsName, String representative, OrmContext connect) {
        Tiangan tiangan = new Tiangan();
        tiangan.setTianganName(tianganName);
        tiangan.setTianganNum(tianganNum);
        tiangan.setTianganYinyang(tianganYinyang);
        FiveElements fiveElementsId = DatabaseUtils.queryFiveElementsByName(fiveElementsName, connect);
        tiangan.setFiveElements(fiveElementsId.getFiveElementsId());
        tiangan.setRepresentative(representative);
        DatabaseUtils.insertTiangan(tiangan, connect);

    }

    private static void addDizhi(String dizhiName, int dizhiNum, int dizhiYinyang, String fiveElementsName, OrmContext connect) {
        Dizhi dizhi = new Dizhi();
        dizhi.setDizhiName(dizhiName);
        dizhi.setDizhiNum(dizhiNum);
        dizhi.setDizhiYinyang(dizhiYinyang);
        FiveElements fiveElementsId = DatabaseUtils.queryFiveElementsByName(fiveElementsName, connect);
        dizhi.setFiveElements(fiveElementsId.getFiveElementsId());
        DatabaseUtils.insertDizhi(dizhi, connect);
    }

    private static void addAssert(int dayNum, int timeNum, String assertText, OrmContext connect) {
        Assert anAssert = new Assert();
        anAssert.setDayNum(dayNum);
        anAssert.setTimeNum(timeNum);
        anAssert.setAssertText(assertText);
        DatabaseUtils.insertAssert(anAssert, connect);
    }

    private static void addDeity(String deityName, String presentation, OrmContext connect) {
        Deity deity = new Deity();
        deity.setDeityName(deityName);
        deity.setPresentation(presentation);
        DatabaseUtils.insertDeity(deity, connect);
    }

    private static void addFiveElements(String fiveElementsName, String growFiveElement, String restrainFiveElement, OrmContext connect) {
        FiveElements fiveElements = new FiveElements();
        fiveElements.setFiveElementsName(fiveElementsName);
        fiveElements.setGrowFiveElement(growFiveElement);
        fiveElements.setRestrainFiveElement(restrainFiveElement);
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

    private static void addJingu(String jinguName, int jinguNum, String presentation, String presentationDetail, String mainTianganStr, String viceTianganStr, String dominantAffairStr, String recessiveAffairStr, String mainRealmsStr, String viceRealmsStr, String mainStarStr, String viceStarStr, OrmContext connect) {
        Jingu jingu = new Jingu();
        jingu.setJinguName(jinguName);
        jingu.setJinguNum(jinguNum);
        jingu.setPresentation(presentation);
        jingu.setPresentationDetail(presentationDetail);
        Tiangan mainTiangan = DatabaseUtils.queryTianganByName(mainTianganStr, connect);
        Tiangan viceTiangan = DatabaseUtils.queryTianganByName(viceTianganStr, connect);
        jingu.setMainTiangan(mainTiangan.getTianganId());
        jingu.setViceTiangan(viceTiangan.getTianganId());
        Affair dominantAffair = DatabaseUtils.queryAffairByName(dominantAffairStr, connect);
        Affair recessiveAffair = DatabaseUtils.queryAffairByName(recessiveAffairStr, connect);
        jingu.setDominantAffair(dominantAffair.getAffairId());
        jingu.setRecessiveAffair(recessiveAffair.getAffairId());
        Realms mainRealms = DatabaseUtils.queryRealmsByName(mainRealmsStr, connect);
        Realms viceRealms = DatabaseUtils.queryRealmsByName(viceRealmsStr, connect);
        jingu.setMainRealms(mainRealms.getRealmsId());
        jingu.setViceRealms(viceRealms.getRealmsId());
        Star mainStar = DatabaseUtils.queryStarByName(mainStarStr, connect);
        Star viceStar = DatabaseUtils.queryStarByName(viceStarStr, connect);
        jingu.setMainStar(mainStar.getStarId());
        jingu.setViceStar(viceStar.getStarId());
        DatabaseUtils.insertJingu(jingu, connect);
    }

    private static void addRelation(String relationName, OrmContext connect) {
        Relation relation = new Relation();
        relation.setRelationName(relationName);
        DatabaseUtils.insertRelation(relation, connect);
    }

    private static void addAffair(String affairName, OrmContext connect) {
        Affair affair = new Affair();
        affair.setAffairName(affairName);
        DatabaseUtils.insertAffair(affair, connect);
    }

    private static void addRealms(String readlmsName, OrmContext context) {
        Realms realms = new Realms();
        realms.setRealmsName(readlmsName);
        DatabaseUtils.insertRealms(realms, context);
    }

    private static void addStar(String starName, String detail, String realmsStr, OrmContext context) {
        Star star = new Star();
        star.setStarName(starName);
        star.setDetail(detail);
        Realms realms = DatabaseUtils.queryRealmsByName(realmsStr, context);
        star.setRealms(realms.getRealmsId());
        DatabaseUtils.insertStar(star, context);
    }

    private static void addAttribute(String jinguName, String deity, String luckyNum, String organ, String magnateOrientation, String offendOrientation, String fiveElements, String ghostsAndGods, OrmContext connect) {
        Attribute attribute = new Attribute();
        Jingu jingu = DatabaseUtils.queryJinguByName(jinguName, connect);
        attribute.setJingu(jingu.getJinguId());
        Deity deityId = DatabaseUtils.queryDeityByName(deity, connect);
        attribute.setDeity(deityId.getDeityId());
        attribute.setLuckyNum(luckyNum);
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
