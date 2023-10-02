package collection;

import java.util.*;

public class Tool94 {

    public static void main(String[] args) {
        //1号候选词条
        List<EnumEntry> slot1 = initSlot(
                EnumEntry.技能后弱点伤害10叠三层,
                EnumEntry.十秒弱点伤害15
        );
        //2号候选词条
        List<EnumEntry> slot2 = initSlot(
                EnumEntry.技能后弱点伤害15
        );
        //3号候选词条
        List<EnumEntry> slot3 = initSlot(
                EnumEntry.弱点伤害20,
                EnumEntry.十秒弱点伤害45
        );
        //4号候选词条
        List<EnumEntry> slot4 = initSlot(
                EnumEntry.十秒伤害15,
                EnumEntry.弱点伤害10
        );
        //按照弱点伤害流计算最高搭配
        determineTopDps(slot1,slot2,slot3,slot4);

    }


    public static List<EnumEntry> initSlot(EnumEntry... entry){
        return Arrays.asList(entry);
    }


    public static void determineTopDps(List<EnumEntry> inputSlot1,List<EnumEntry> inputSlot2,List<EnumEntry> inputSlot3,List<EnumEntry> inputSlot4){
        List<EntryPlan> planList = new ArrayList<>();
        for (int indexA = 0; indexA < inputSlot1.size(); indexA++) {
            for (int indexB = 0; indexB < inputSlot2.size(); indexB++) {
                for (int indexC = 0; indexC < inputSlot3.size(); indexC++) {
                    for (int indexD = 0; indexD < inputSlot4.size(); indexD++) {
                        //计算最大和最小伤害
                        EnumEntry enumEntryA = inputSlot1.get(indexA);
                        EnumEntry enumEntryB = inputSlot2.get(indexB);
                        EnumEntry enumEntryC = inputSlot3.get(indexC);
                        EnumEntry enumEntryD = inputSlot4.get(indexD);
                        boolean include10s = include10s(enumEntryA,enumEntryB,enumEntryC,enumEntryD);
                        int delayTime = calculateDelayTime(enumEntryA,enumEntryB,enumEntryC,enumEntryD);
                        int buffTime = calculateBuffTime(enumEntryA,enumEntryB,enumEntryC,enumEntryD);
                        int totalHarm = calculateTotalAmount("伤害",include10s,enumEntryA,enumEntryB,enumEntryC,enumEntryD);
                        int totalWeakHarm = calculateTotalAmount("弱点伤害",include10s,enumEntryA,enumEntryB,enumEntryC,enumEntryD);
                        int totalSpeed = calculateTotalAmount("射速",include10s,enumEntryA,enumEntryB,enumEntryC,enumEntryD);
                        int minDps = (100 + totalHarm) * (100 + totalSpeed);
                        int maxDps = (100 + totalHarm + totalWeakHarm) * (100 + totalSpeed);
                        EntryPlan plan = new EntryPlan(minDps,maxDps,Arrays.asList(enumEntryA,enumEntryB,enumEntryC,enumEntryD), delayTime, buffTime);
                        planList.add(plan);
                    }
                }
            }
        }
        planList.sort(Comparator.comparingInt(EntryPlan::getRightValue).thenComparingInt(EntryPlan::getLeftValue).reversed());
        for (EntryPlan plan : planList) {
            System.out.println(plan.getDesc());
        }

    }

    private static int calculateBuffTime(EnumEntry enumEntryA, EnumEntry enumEntryB, EnumEntry enumEntryC, EnumEntry enumEntryD) {
        int total = 0;
        total = Math.max(total,calculateSingleBuffTime(enumEntryA));
        total = Math.max(total,calculateSingleBuffTime(enumEntryB));
        total = Math.max(total,calculateSingleBuffTime(enumEntryC));
        total = Math.max(total,calculateSingleBuffTime(enumEntryD));
        return total;
    }

    private static int calculateSingleBuffTime(EnumEntry enumEntry){
        if(Objects.isNull(enumEntry)){
            return 0;
        }
        if(EnumEntry.空.equals(enumEntry)){
            return 0;
        }
        return Objects.isNull(enumEntry.getBuffTime()) ? 0 : enumEntry.getBuffTime();
    }

    private static int calculateDelayTime(EnumEntry enumEntryA, EnumEntry enumEntryB, EnumEntry enumEntryC, EnumEntry enumEntryD) {
        int total = 0;
        total += calculateSingleDelayTime(enumEntryA);
        total += calculateSingleDelayTime(enumEntryB);
        total += calculateSingleDelayTime(enumEntryC);
        total += calculateSingleDelayTime(enumEntryD);
        return total;
    }

    private static int calculateSingleDelayTime(EnumEntry enumEntry){
        if(Objects.isNull(enumEntry)){
            return 0;
        }
        if(EnumEntry.空.equals(enumEntry)){
            return 0;
        }
        if("+10".equals(enumEntry.getCondition())){
            return 10;
        }
        return 0;
    }

    private static int calculateTotalAmount(String type, boolean include10s, EnumEntry enumEntryA, EnumEntry enumEntryB, EnumEntry enumEntryC, EnumEntry enumEntryD) {
        int total = 0;
        total += calculateAmount(type,include10s,enumEntryA);
        total += calculateAmount(type,include10s,enumEntryB);
        total += calculateAmount(type,include10s,enumEntryC);
        total += calculateAmount(type,include10s,enumEntryD);
        return total;
    }

    private static int calculateAmount(String type, boolean include10s, EnumEntry enumEntry) {
        if(Objects.isNull(enumEntry)){
            return 0;
        }
       if(EnumEntry.空.equals(enumEntry)){
           return 0;
       }
       if(!enumEntry.getType().equals(type)){
           return 0;
       }
       if("".equals(enumEntry.getCondition()) || "+10".equals(enumEntry.getCondition())){
           return enumEntry.getAmount();
       }else if (">14".equals(enumEntry.getCondition())){
           return include10s ? enumEntry.getAmount() : 0;
       }else if("使用技能".equals(enumEntry.getCondition())){
           return enumEntry.getAmount();
       }
       return 0;
    }


    private static boolean include10s(EnumEntry... enumEntryA) {
        for (EnumEntry enumEntry : enumEntryA) {
            if("+10".equals(enumEntry.getCondition())){
                return true;
            }
        }
        return false;
    }


    public static class EntryPlan {

        public EntryPlan() {
        }

        public EntryPlan(Integer leftValue, Integer rightValue, List<EnumEntry> entryList, Integer delayTotal, Integer buffTotal) {
            this.leftValue = leftValue;
            this.rightValue = rightValue;
            this.entryList = entryList;
            this.delayTotal = delayTotal;
            this.buffTotal = buffTotal;
        }

        private Integer leftValue;

        private Integer rightValue;

        private List<EnumEntry> entryList;

        private Integer delayTotal;

        private Integer buffTotal;

        private String desc;

        public Integer getLeftValue() {
            return leftValue;
        }

        public void setLeftValue(Integer leftValue) {
            this.leftValue = leftValue;
        }

        public Integer getRightValue() {
            return rightValue;
        }

        public void setRightValue(Integer rightValue) {
            this.rightValue = rightValue;
        }

        public List<EnumEntry> getEntryList() {
            return entryList;
        }

        public void setEntryList(List<EnumEntry> entryList) {
            this.entryList = entryList;
        }

        public Integer getDelayTotal() {
            return delayTotal;
        }

        public void setDelayTotal(Integer delayTotal) {
            this.delayTotal = delayTotal;
        }

        public Integer getBuffTotal() {
            return buffTotal;
        }

        public void setBuffTotal(Integer buffTotal) {
            this.buffTotal = buffTotal;
        }

        public String getDesc() {
            StringBuilder stringBuilder = new StringBuilder("该方案的伤害范围为").append(leftValue).append(" ~ ").append(rightValue);
            int cdTime = delayTotal + 10;
            if(buffTotal == 0){
                stringBuilder.append(",buff全程");
            }else{
                stringBuilder.append(",buff").append(buffTotal).append("/").append(cdTime);
            }
            stringBuilder.append(",词条详情为:");
            for (EnumEntry enumEntry : entryList) {
                stringBuilder.append(enumEntry.getDesc()).append(" - ");
            }
            return stringBuilder.toString();
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static enum EnumEntry {

        空("","",1,"空"),


        射速30("","射速",30,"挑战模式射速+30%"),
        射速25("","射速",25,"挑战模式射速+25%"),
        射速20("","射速",20,"挑战模式射速+20%"),
        射速15("","射速",15,"挑战模式射速+15%"),
        射速10("","射速",10,"挑战模式射速+10%"),

        挑战伤害40("","伤害",40,"挑战模式伤害+40%"),
        挑战伤害30("","伤害",30,"挑战模式伤害+30%"),
        挑战伤害20("","伤害",20,"挑战模式伤害+20%"),
        挑战伤害10("","伤害",10,"挑战模式伤害+10%"),
        挑战伤害5("","伤害",5,"挑战模式伤害+5%"),

        弱点伤害50("","弱点伤害",50,"弱点伤害+50%"),
        弱点伤害40("","弱点伤害",40,"弱点伤害+40%"),
        弱点伤害30("","弱点伤害",30,"弱点伤害+30%"),
        弱点伤害20("","弱点伤害",20,"弱点伤害+20%"),
        弱点伤害10("","弱点伤害",10,"弱点伤害+10%"),

        十秒弱点伤害55("+10","弱点伤害",55,"技能充能时间+10s,弱点伤害+55%"),
        十秒弱点伤害45("+10","弱点伤害",45,"技能充能时间+10s,弱点伤害+45%"),
        十秒弱点伤害35("+10","弱点伤害",35,"技能充能时间+10s,弱点伤害+35%"),
        十秒弱点伤害25("+10","弱点伤害",25,"技能充能时间+10s,弱点伤害+25%"),
        十秒弱点伤害15("+10","弱点伤害",15,"技能充能时间+10s,弱点伤害+15%"),

        十秒伤害45("+10","伤害",45,"技能充能时间+10s,伤害+45%"),
        十秒伤害35("+10","伤害",35,"技能充能时间+10s,伤害+35%"),
        十秒伤害25("+10","伤害",25,"技能充能时间+10s,伤害+25%"),
        十秒伤害15("+10","伤害",15,"技能充能时间+10s,伤害+15%"),
        十秒伤害10("+10","伤害",10,"技能充能时间+10s,伤害+10%"),

        十秒射速35("+10","射速",35,"技能充能时间+10s,射速+35%"),
        十秒射速30("+10","射速",30,"技能充能时间+10s,射速+30%"),
        十秒射速25("+10","射速",25,"技能充能时间+10s,射速+25%"),
        十秒射速20("+10","射速",20,"技能充能时间+10s,射速+20%"),
        十秒射速15("+10","射速",15,"技能充能时间+10s,射速+15%"),

        十四秒弱点伤害55(">14","弱点伤害",55,"技能充能时间>14s时,弱点伤害+55%"),
        十四秒弱点伤害45(">14","弱点伤害",45,"技能充能时间>14s时,弱点伤害+45%"),
        十四秒弱点伤害35(">14","弱点伤害",35,"技能充能时间>14s时,弱点伤害+35%"),
        十四秒弱点伤害25(">14","弱点伤害",25,"技能充能时间>14s时,弱点伤害+25%"),
        十四秒弱点伤害15(">14","弱点伤害",15,"技能充能时间>14s时,弱点伤害+15%"),


        十四秒伤害45(">14","伤害",45,"技能充能时间>14s时,伤害+45%"),
        十四秒伤害35(">14","伤害",35,"技能充能时间>14s时,伤害+35%"),
        十四秒伤害25(">14","伤害",25,"技能充能时间>14s时,伤害+25%"),
        十四秒伤害15(">14","伤害",15,"技能充能时间>14s时,伤害+15%"),
        十四秒伤害10(">14","伤害",10,"技能充能时间>14s时,伤害+10%"),

        十四秒射速35(">14","射速",35,"技能充能时间>14s时,射速+35%"),
        十四秒射速30(">14","射速",30,"技能充能时间>14s时,射速+30%"),
        十四秒射速25(">14","射速",25,"技能充能时间>14s时,射速+25%"),
        十四秒射速20(">14","射速",20,"技能充能时间>14s时,射速+20%"),
        十四秒射速15(">14","射速",15,"技能充能时间>14s时,射速+15%"),

        技能后弱点伤害55("使用技能","弱点伤害",55,"使用技能后弱点伤害+55%,持续12s",12),
        技能后弱点伤害45("使用技能","弱点伤害",45,"使用技能后弱点伤害+45%,持续12s",12),
        技能后弱点伤害35("使用技能","弱点伤害",35,"使用技能后弱点伤害+35%,持续12s",12),
        技能后弱点伤害25("使用技能","弱点伤害",25,"使用技能后弱点伤害+25%,持续12s",12),
        技能后弱点伤害15("使用技能","弱点伤害",15,"使用技能后弱点伤害+15%,持续12s",12),
        技能后弱点伤害10叠三层("使用技能","弱点伤害",10,"使用技能后弱点伤害+10%,持续12s，叠加三层",12),

        技能后伤害45("使用技能","伤害",45,"使用技能后伤害+45%,持续12s",12),
        技能后伤害35("使用技能","伤害",35,"使用技能后伤害+35%,持续12s",12),
        技能后伤害25("使用技能","伤害",25,"使用技能后伤害+25%,持续12s",12),
        技能后伤害15("使用技能","伤害",15,"使用技能后伤害+15%,持续12s",12),
        技能后伤害10("使用技能","伤害",10,"使用技能后伤害+10%,持续12s",12),

        ;

        EnumEntry() {
        }

        EnumEntry(String condition, String type, Integer amount, String desc) {
            this.condition = condition;
            this.type = type;
            this.amount = amount;
            this.desc = desc;
            this.buffTime = 0;
        }

        EnumEntry(String condition, String type, Integer amount, String desc, Integer buffTime) {
            this.condition = condition;
            this.type = type;
            this.amount = amount;
            this.desc = desc;
            this.buffTime = buffTime;
        }

        private String condition;

        private String type;

        private Integer amount;

        private String desc;

        private Integer buffTime;

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public Integer getBuffTime() {
            return buffTime;
        }

        public void setBuffTime(Integer buffTime) {
            this.buffTime = buffTime;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


}
