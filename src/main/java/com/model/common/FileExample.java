package com.model.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FileExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andFileIdIsNull() {
            addCriterion("fileId is null");
            return (Criteria) this;
        }

        public Criteria andFileIdIsNotNull() {
            addCriterion("fileId is not null");
            return (Criteria) this;
        }

        public Criteria andFileIdEqualTo(Integer value) {
            addCriterion("fileId =", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotEqualTo(Integer value) {
            addCriterion("fileId <>", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThan(Integer value) {
            addCriterion("fileId >", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("fileId >=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThan(Integer value) {
            addCriterion("fileId <", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThanOrEqualTo(Integer value) {
            addCriterion("fileId <=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdIn(List<Integer> values) {
            addCriterion("fileId in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotIn(List<Integer> values) {
            addCriterion("fileId not in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdBetween(Integer value1, Integer value2) {
            addCriterion("fileId between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotBetween(Integer value1, Integer value2) {
            addCriterion("fileId not between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("userId =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("userId <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("userId >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("userId >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("userId <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("userId <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("userId in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("userId not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("userId between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("userId not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andFileFullNameIsNull() {
            addCriterion("fileFullName is null");
            return (Criteria) this;
        }

        public Criteria andFileFullNameIsNotNull() {
            addCriterion("fileFullName is not null");
            return (Criteria) this;
        }

        public Criteria andFileFullNameEqualTo(String value) {
            addCriterion("fileFullName =", value, "fileFullName");
            return (Criteria) this;
        }

        public Criteria andFileFullNameNotEqualTo(String value) {
            addCriterion("fileFullName <>", value, "fileFullName");
            return (Criteria) this;
        }

        public Criteria andFileFullNameGreaterThan(String value) {
            addCriterion("fileFullName >", value, "fileFullName");
            return (Criteria) this;
        }

        public Criteria andFileFullNameGreaterThanOrEqualTo(String value) {
            addCriterion("fileFullName >=", value, "fileFullName");
            return (Criteria) this;
        }

        public Criteria andFileFullNameLessThan(String value) {
            addCriterion("fileFullName <", value, "fileFullName");
            return (Criteria) this;
        }

        public Criteria andFileFullNameLessThanOrEqualTo(String value) {
            addCriterion("fileFullName <=", value, "fileFullName");
            return (Criteria) this;
        }

        public Criteria andFileFullNameLike(String value) {
            addCriterion("fileFullName like", value, "fileFullName");
            return (Criteria) this;
        }

        public Criteria andFileFullNameNotLike(String value) {
            addCriterion("fileFullName not like", value, "fileFullName");
            return (Criteria) this;
        }

        public Criteria andFileFullNameIn(List<String> values) {
            addCriterion("fileFullName in", values, "fileFullName");
            return (Criteria) this;
        }

        public Criteria andFileFullNameNotIn(List<String> values) {
            addCriterion("fileFullName not in", values, "fileFullName");
            return (Criteria) this;
        }

        public Criteria andFileFullNameBetween(String value1, String value2) {
            addCriterion("fileFullName between", value1, value2, "fileFullName");
            return (Criteria) this;
        }

        public Criteria andFileFullNameNotBetween(String value1, String value2) {
            addCriterion("fileFullName not between", value1, value2, "fileFullName");
            return (Criteria) this;
        }

        public Criteria andFileShortNameIsNull() {
            addCriterion("fileShortName is null");
            return (Criteria) this;
        }

        public Criteria andFileShortNameIsNotNull() {
            addCriterion("fileShortName is not null");
            return (Criteria) this;
        }

        public Criteria andFileShortNameEqualTo(String value) {
            addCriterion("fileShortName =", value, "fileShortName");
            return (Criteria) this;
        }

        public Criteria andFileShortNameNotEqualTo(String value) {
            addCriterion("fileShortName <>", value, "fileShortName");
            return (Criteria) this;
        }

        public Criteria andFileShortNameGreaterThan(String value) {
            addCriterion("fileShortName >", value, "fileShortName");
            return (Criteria) this;
        }

        public Criteria andFileShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("fileShortName >=", value, "fileShortName");
            return (Criteria) this;
        }

        public Criteria andFileShortNameLessThan(String value) {
            addCriterion("fileShortName <", value, "fileShortName");
            return (Criteria) this;
        }

        public Criteria andFileShortNameLessThanOrEqualTo(String value) {
            addCriterion("fileShortName <=", value, "fileShortName");
            return (Criteria) this;
        }

        public Criteria andFileShortNameLike(String value) {
            addCriterion("fileShortName like", value, "fileShortName");
            return (Criteria) this;
        }

        public Criteria andFileShortNameNotLike(String value) {
            addCriterion("fileShortName not like", value, "fileShortName");
            return (Criteria) this;
        }

        public Criteria andFileShortNameIn(List<String> values) {
            addCriterion("fileShortName in", values, "fileShortName");
            return (Criteria) this;
        }

        public Criteria andFileShortNameNotIn(List<String> values) {
            addCriterion("fileShortName not in", values, "fileShortName");
            return (Criteria) this;
        }

        public Criteria andFileShortNameBetween(String value1, String value2) {
            addCriterion("fileShortName between", value1, value2, "fileShortName");
            return (Criteria) this;
        }

        public Criteria andFileShortNameNotBetween(String value1, String value2) {
            addCriterion("fileShortName not between", value1, value2, "fileShortName");
            return (Criteria) this;
        }

        public Criteria andFileRealNameIsNull() {
            addCriterion("fileRealName is null");
            return (Criteria) this;
        }

        public Criteria andFileRealNameIsNotNull() {
            addCriterion("fileRealName is not null");
            return (Criteria) this;
        }

        public Criteria andFileRealNameEqualTo(String value) {
            addCriterion("fileRealName =", value, "fileRealName");
            return (Criteria) this;
        }

        public Criteria andFileRealNameNotEqualTo(String value) {
            addCriterion("fileRealName <>", value, "fileRealName");
            return (Criteria) this;
        }

        public Criteria andFileRealNameGreaterThan(String value) {
            addCriterion("fileRealName >", value, "fileRealName");
            return (Criteria) this;
        }

        public Criteria andFileRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("fileRealName >=", value, "fileRealName");
            return (Criteria) this;
        }

        public Criteria andFileRealNameLessThan(String value) {
            addCriterion("fileRealName <", value, "fileRealName");
            return (Criteria) this;
        }

        public Criteria andFileRealNameLessThanOrEqualTo(String value) {
            addCriterion("fileRealName <=", value, "fileRealName");
            return (Criteria) this;
        }

        public Criteria andFileRealNameLike(String value) {
            addCriterion("fileRealName like", value, "fileRealName");
            return (Criteria) this;
        }

        public Criteria andFileRealNameNotLike(String value) {
            addCriterion("fileRealName not like", value, "fileRealName");
            return (Criteria) this;
        }

        public Criteria andFileRealNameIn(List<String> values) {
            addCriterion("fileRealName in", values, "fileRealName");
            return (Criteria) this;
        }

        public Criteria andFileRealNameNotIn(List<String> values) {
            addCriterion("fileRealName not in", values, "fileRealName");
            return (Criteria) this;
        }

        public Criteria andFileRealNameBetween(String value1, String value2) {
            addCriterion("fileRealName between", value1, value2, "fileRealName");
            return (Criteria) this;
        }

        public Criteria andFileRealNameNotBetween(String value1, String value2) {
            addCriterion("fileRealName not between", value1, value2, "fileRealName");
            return (Criteria) this;
        }

        public Criteria andFileSuffixIsNull() {
            addCriterion("fileSuffix is null");
            return (Criteria) this;
        }

        public Criteria andFileSuffixIsNotNull() {
            addCriterion("fileSuffix is not null");
            return (Criteria) this;
        }

        public Criteria andFileSuffixEqualTo(String value) {
            addCriterion("fileSuffix =", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixNotEqualTo(String value) {
            addCriterion("fileSuffix <>", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixGreaterThan(String value) {
            addCriterion("fileSuffix >", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixGreaterThanOrEqualTo(String value) {
            addCriterion("fileSuffix >=", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixLessThan(String value) {
            addCriterion("fileSuffix <", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixLessThanOrEqualTo(String value) {
            addCriterion("fileSuffix <=", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixLike(String value) {
            addCriterion("fileSuffix like", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixNotLike(String value) {
            addCriterion("fileSuffix not like", value, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixIn(List<String> values) {
            addCriterion("fileSuffix in", values, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixNotIn(List<String> values) {
            addCriterion("fileSuffix not in", values, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixBetween(String value1, String value2) {
            addCriterion("fileSuffix between", value1, value2, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileSuffixNotBetween(String value1, String value2) {
            addCriterion("fileSuffix not between", value1, value2, "fileSuffix");
            return (Criteria) this;
        }

        public Criteria andFileUrlIsNull() {
            addCriterion("fileUrl is null");
            return (Criteria) this;
        }

        public Criteria andFileUrlIsNotNull() {
            addCriterion("fileUrl is not null");
            return (Criteria) this;
        }

        public Criteria andFileUrlEqualTo(String value) {
            addCriterion("fileUrl =", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotEqualTo(String value) {
            addCriterion("fileUrl <>", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlGreaterThan(String value) {
            addCriterion("fileUrl >", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlGreaterThanOrEqualTo(String value) {
            addCriterion("fileUrl >=", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLessThan(String value) {
            addCriterion("fileUrl <", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLessThanOrEqualTo(String value) {
            addCriterion("fileUrl <=", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLike(String value) {
            addCriterion("fileUrl like", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotLike(String value) {
            addCriterion("fileUrl not like", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlIn(List<String> values) {
            addCriterion("fileUrl in", values, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotIn(List<String> values) {
            addCriterion("fileUrl not in", values, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlBetween(String value1, String value2) {
            addCriterion("fileUrl between", value1, value2, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotBetween(String value1, String value2) {
            addCriterion("fileUrl not between", value1, value2, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNull() {
            addCriterion("introduce is null");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNotNull() {
            addCriterion("introduce is not null");
            return (Criteria) this;
        }

        public Criteria andIntroduceEqualTo(String value) {
            addCriterion("introduce =", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotEqualTo(String value) {
            addCriterion("introduce <>", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThan(String value) {
            addCriterion("introduce >", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("introduce >=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThan(String value) {
            addCriterion("introduce <", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThanOrEqualTo(String value) {
            addCriterion("introduce <=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLike(String value) {
            addCriterion("introduce like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotLike(String value) {
            addCriterion("introduce not like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceIn(List<String> values) {
            addCriterion("introduce in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotIn(List<String> values) {
            addCriterion("introduce not in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceBetween(String value1, String value2) {
            addCriterion("introduce between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotBetween(String value1, String value2) {
            addCriterion("introduce not between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("updateTime =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("updateTime <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("updateTime >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTime >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("updateTime <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("updateTime <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("updateTime in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("updateTime not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("updateTime between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("updateTime not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}